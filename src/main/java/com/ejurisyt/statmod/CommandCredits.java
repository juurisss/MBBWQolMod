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
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;

public class CommandCredits extends CommandBase {

    @Override
    public String getCommandName() {
        return "credits";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/credits";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        sender.addChatMessage(new ChatComponentText(
            "\u00A7l\u00A72====QOL Mod (For Mineberry BW)====\n\n" +
            "\u00A7r\u00A7eCommands:\n" +
            "\u00A7a\u2192 /st - \u00A7rView the stats of a player\n" +
            "\u00A7a\u2192 /mst - \u00A7rView the Metrics of a player\n\n" +
            "\u00A7bThis Mod Was Made By EjurisYT\n"
        ));
    }
    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }
}
