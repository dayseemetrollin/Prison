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

import io.github.sirfaizdat.prison.internal.commands.Command;
import io.github.sirfaizdat.prison.internal.entity.CommandSender;
import io.github.sirfaizdat.prison.utils.ChatColor;
import mkremins.fanciful.FancyMessage;

/**
 * @author SirFaizdat
 */
public class PrisonCommand {

    @Command(identifier = "prison", description = "Manage the core of Prison.", onlyPlayers = false)
    public void prisonCommand(CommandSender sender) {
        sender.dispatchCommand("prison help"); // Run the help command
    }

    @Command(identifier = "prison version", description = "Version information about Prison.", onlyPlayers = false)
    public void versionCommand(CommandSender sender) {
        sender.sendMessage("&8---- &3/prison version &8----");
        sender.sendMessage("&7Running &3Prison v" + Prison.instance.getPlatform().getPluginVersion() + "&7.");
        // Show development build information
        if (Prison.instance.isDevBuild())
            sender.sendMessage("&7You are running a &3development build&7, which may be unstable.");
        // Show alerts information
        if (Prison.instance.getAlerts().getAlerts().size() > 0)
            new FancyMessage("There are ").color(ChatColor.GRAY)
                    .then(Prison.instance.getAlerts().getAlerts().size() + " alerts").color(ChatColor.AQUA).command("/prison alerts").tooltip("Click to run /prison alerts")
                    .then(".").color(ChatColor.GRAY)
                    .send(sender);
        sender.sendMessage("&8---- &3/prison version &8----");
    }

    @Command(identifier = "prison alerts", description = "Show all Prison alerts.", onlyPlayers = false)
    public void alertsCommand(CommandSender sender) {
        sender.sendMessage("&8---- &3/prison alerts &8----");
        if (Prison.instance.getAlerts().getAlerts().size() == 0) sender.sendMessage("&7No alerts.");
        else Prison.instance.getAlerts().getAlerts().forEach(sender::sendMessage);
        sender.sendMessage("&8---- &3/prison alerts &8----");
    }

}
