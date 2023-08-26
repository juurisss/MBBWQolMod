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

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.Arrays;
import java.util.List;

public class AutoGG {

    private int tick = -1;
    private int cmsgg = -1;
    public static boolean autoGGEnabled = true;
    public static String customGGMessage = "! gg";
    public static String leavemsg = "/leave";
    public static String cmsg = "/categories";
    private final List<String> endingStrings = Arrays.asList(
            " team won!",
            "#1 - ",
            "#2 - "
    );
    private final List<String> warplobby = Arrays.asList(
            " Teleporting to lobby....",
            "Connecting to bw-lobby-"
    );

    @SubscribeEvent(priority = EventPriority.LOW)
    public void onGameEndEvent(ClientChatReceivedEvent event) {
        String message = ChatColor.stripColor(event.message.getUnformattedText());

        if (autoGGEnabled && !message.isEmpty() && isEndOfGame(message)) {
            this.tick = 2;
        } else if (autoGGEnabled && !message.isEmpty() && warpingtolobby(message)) {
            this.cmsgg = 80;
        }
    }

    @SubscribeEvent(priority = EventPriority.LOW)
    public void onGameTick(TickEvent.ClientTickEvent event) {
        if (this.tick == 0) {
            Minecraft.getMinecraft().thePlayer.sendChatMessage(customGGMessage);
            Minecraft.getMinecraft().thePlayer.sendChatMessage(leavemsg);
            this.tick = -1;
        } else if (this.tick > 0) {
            this.tick--;
        }
    
        if (this.cmsgg == 0) {
            if (!AutoGG.cmsg.isEmpty()) {
                Minecraft.getMinecraft().thePlayer.sendChatMessage(AutoGG.cmsg);
            }
            this.cmsgg = -1;
        } else if (this.cmsgg > 0) {
            this.cmsgg--;
        }
    }


    private boolean isEndOfGame(String message) {
        return this.endingStrings.stream().anyMatch(message::contains);
    }
    private boolean warpingtolobby(String message) {
        return this.warplobby.stream().anyMatch(message::contains);
    }
}
