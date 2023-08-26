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

package com.ejurisyt.mbbwqol;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import java.util.Arrays;
import java.util.List;

public class CommandAutoGG extends CommandBase {

    @Override
    public String getCommandName() {
        return "autogg";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/autogg <on/off/setmsg> [message]";
    }

    @Override
    public List<String> getCommandAliases() {
        return Arrays.asList("autoGG", "gg", "AutoGoodGame");
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        if (sender instanceof EntityPlayer) {
            EntityPlayer player = (EntityPlayer) sender;

            if (args.length >= 1) {
                if (args[0].equalsIgnoreCase("on")) {
                    AutoGG.autoGGEnabled = true;
                    player.addChatMessage(new ChatComponentText("AutoGG enabled."));
                } else if (args[0].equalsIgnoreCase("off")) {
                    AutoGG.autoGGEnabled = false;
                    player.addChatMessage(new ChatComponentText("AutoGG disabled."));
                } else if (args[0].equalsIgnoreCase("setmsg") && args.length >= 2) {
                    String customMessage = String.join(" ", Arrays.copyOfRange(args, 1, args.length));
                    AutoGG.customGGMessage = customMessage;
                    player.addChatMessage(new ChatComponentText("Custom GG message set: " + customMessage));
                } else {
                    player.addChatMessage(new ChatComponentText(getCommandUsage(sender)));
                }
            } else {
                player.addChatMessage(new ChatComponentText(getCommandUsage(sender)));
            }
        }
    }
    
    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }
}
