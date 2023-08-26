/**
 * Copyright © 2023 EjurisYT
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.ejurisyt.statmod;

import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CommandMST extends CommandBase {

    @Override
    public String getCommandName() {
        return "mst";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/mst [username]";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        String username;

        if (args.length == 0) {
            if (sender instanceof EntityPlayer) {
                EntityPlayer player = (EntityPlayer) sender;
                username = player.getDisplayNameString();
            } else {
                throw new CommandException("Usage: /mst [username]");
            }
        } else if (args.length == 1) {
            username = args[0];
        } else {
            throw new CommandException("Usage: /mst [username]");
        }

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                String apiUrl = "https://mineberry-stats-api-2vd5b.ondigitalocean.app/api/stats/" + username;

                try {
                    URL url = new URL(apiUrl);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");

                    int responseCode = connection.getResponseCode();
                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                        String inputLine;
                        StringBuilder response = new StringBuilder();

                        while ((inputLine = in.readLine()) != null) {
                            response.append(inputLine);
                        }
                        in.close();

                        Gson gson = new Gson();
                        JsonObject statsObject = gson.fromJson(response.toString(), JsonObject.class);

                        JsonObject stats = statsObject.getAsJsonObject("stats");
                        JsonObject kills = stats.getAsJsonObject("kills");
                        JsonObject deaths = stats.getAsJsonObject("deaths");
                        JsonObject wins = stats.getAsJsonObject("wins");
                        JsonObject games = stats.getAsJsonObject("games");
                        JsonObject beds = stats.getAsJsonObject("beds");

                        String formattedMessage = String.format(
                            "\n\u00A7aMetrics \u25B8 \u00A7r\u00A7e%s\n" +
                            "\u00A7e- \u00A7fK/D Ratio: \u00A7r\u00A7e%.3f\n" +
                            "\u00A7e- \u00A7fKills/Game: \u00A7r\u00A7e%.2f\n" +
                            "\u00A7e- \u00A7fBeds/Game: \u00A7r\u00A7e%.2f\n" +
                            "\u00A7e- \u00A7fWin Rate: \u00A7r\u00A7e%.1f%%\n",
                            username,
                            (double) kills.get("value").getAsInt() / deaths.get("value").getAsInt(),
                            (double) kills.get("value").getAsInt() / games.get("value").getAsInt(),
                            (double) beds.get("value").getAsInt() / games.get("value").getAsInt(),
                            (double) wins.get("value").getAsInt() / games.get("value").getAsInt() * 100);

                        final String finalMessage = formattedMessage;

                        Minecraft.getMinecraft().addScheduledTask(new Runnable() {
                            @Override
                            public void run() {
                                Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(finalMessage));
                            }
                        });
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        thread.start();
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }
}
