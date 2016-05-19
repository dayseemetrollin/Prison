/*
 * Prison is a Minecraft plugin for the prison gamemode.
 * Copyright (C) 2016 SirFaizdat
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package io.github.sirfaizdat.prison;

import com.sk89q.intake.Command;
import io.github.sirfaizdat.prison.platform.ChatColor;
import io.github.sirfaizdat.prison.platform.interfaces.CommandSender;

/**
 * Commands for Prison.
 *
 * @author SirFaizdat
 * @since 3.0
 */
public class PrisonCommands {

    private String[] helpMessage = {
            "&8---- &3/prison help &8----",
            "&7The prison command allows you to manage core plugin features.",
            "&3/prison help &8- &7Displays this message.",
            "&3/prison version &8- &7Shows version information for Prison.",
            "&3/prison update &8- &7Updates the plugin to the latest version.",
            "&8---- &3/prison help &8----"
    };

    // Description isn't needed for this one, it's ignored anyway.
    @Command(aliases = "", desc = "")
    public void rootCommand(CommandSender sender) {
        helpCommand(sender);
    }

    @Command(aliases = "help", desc = "Display the help screen.")
    public void helpCommand(CommandSender sender) {
        for(String s : helpMessage) sender.sendMessage(ChatColor.translateAlternateColorCodes('&', s));
    }

    @Command(aliases = "version", desc = "Shows version information for Prison.")
    public void versionCommand(CommandSender sender) {
        sender.sendMessage("You are running Prison v" + Prison.instance.getPlatform().getVersion() + ".");
        if (Prison.instance.getPlatform().getVersion().contains("SNAPSHOT"))
            sender.sendMessage("Note: You are running a development build, which are by no means stable. Please report any issues to the issue tracker.");
    }

    @Command(aliases = "update", desc = "Update the plugin to the latest version.")
    public void updateCommand(CommandSender sender) {
        sender.sendMessage("Update functionality is coming soon.");
    }

}
