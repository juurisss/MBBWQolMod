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

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;
import scala.reflect.internal.Trees.If;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.client.Minecraft;

@Mod(modid = mbbwqol.MODID, version = mbbwqol.VERSION)
public class mbbwqol {

    public static final String MODID = "mbbwqol";
    public static final String VERSION = "1.0";
    private final Minecraft mc = Minecraft.getMinecraft();

    @EventHandler
    public void onInit(FMLInitializationEvent event) {
        ClientCommandHandler.instance.registerCommand(new CommandST());
        ClientCommandHandler.instance.registerCommand(new CommandMST());
        ClientCommandHandler.instance.registerCommand(new CommandCredits());
        ClientCommandHandler.instance.registerCommand(new CommandAutoGG());
        ClientCommandHandler.instance.registerCommand(new CommandAutoGL());
        ClientCommandHandler.instance.registerCommand(new CommandAJA());
        AutoGG autoGG = new AutoGG();
        MinecraftForge.EVENT_BUS.register(autoGG);
        AutoGL autoGL = new AutoGL();
        MinecraftForge.EVENT_BUS.register(autoGL);
        MinecraftForge.EVENT_BUS.register((Object)this);
    }

    @SubscribeEvent
    public void onServerConnect(FMLNetworkEvent.ClientConnectedToServerEvent event) {
        if (Minecraft.getMinecraft().getCurrentServerData() == null) return;
        if (Minecraft.getMinecraft().getCurrentServerData().serverIP.toLowerCase().contains("mineberry.")) {
            new Thread(() -> {
                try {
                    while (Minecraft.getMinecraft().thePlayer == null) {
                        Thread.sleep(100);
                    }
                    Thread.sleep(2000);

                    String username = Minecraft.getMinecraft().thePlayer.getName();

                    String messageFormat = "\u00A7l\u00A72====\u00A7r\u00A72QOL Mod (For Mineberry BW)\u00A7l\u00A72====\n\n" +
                    "\u00A7r\u00A7eHello \u00A7r\u00A7a%s\u00A7r\u00A7e!\n\n" +
                    "\u00A7r\u00A7eCommands:\n" +
                    "\u00A7r\u00A7a\u2192 \u00A7r/st - View the stats of a player\n\u00A7r\u00A7eAliases: \u00A7rnone\n\n" +
                    "\u00A7r\u00A7a\u2192 \u00A7r/mst - View the Metrics of a player\n\u00A7r\u00A7eAliases: \u00A7rnone\n\n" +
                    "\u00A7r\u00A7a\u2192 \u00A7r/autogl - (on/off/setmsg) Send a message once the game starts\n\u00A7r\u00A7eAliases: \u00A7r/gl\n\n" +
                    "\u00A7r\u00A7a\u2192 \u00A7r/autogg - (on/off/setmsg) Send a message once the game ends\n\u00A7r\u00A7eAliases: \u00A7r/gg\n\n" +
                    "\u00A7r\u00A7a\u2192 \u00A7r/aja - (on/off) Enable/disable cmsg functionality\n\u00A7r\u00A7eAliases: \u00A7r/autojoinassist\n\n" +
                    "\u00A7r\u00A7bThis Mod Was Made By \u00A7r\u00A73EjurisYT\n";

                    String messageText = String.format(messageFormat, username);
                    IChatComponent message = new ChatComponentText(messageText);
                    Minecraft.getMinecraft().thePlayer.addChatMessage(message);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}