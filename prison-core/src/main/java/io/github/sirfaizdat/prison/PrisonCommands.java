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
import com.sk89q.intake.parametric.annotation.Switch;
import io.github.sirfaizdat.prison.module.Module;
import io.github.sirfaizdat.prison.platform.ChatColor;
import io.github.sirfaizdat.prison.platform.interfaces.CommandSender;

import java.util.ArrayList;
import java.util.List;

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
            "&3/prison modules &8- &7Shows information about the running modules.",
            "&3/prison update &8- &7Updates the plugin to the latest version.",
            "&3/prison metrics &7[-d to disable] &8 - &7Displays the status of, or disables, Metrics.",
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
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7You are running &3Prison v" + Prison.instance.getPlatform().getVersion() + "&7."));
        if (Prison.instance.getPlatform().getVersion().contains("SNAPSHOT"))
            sender.sendMessage(ChatColor.DARK_GRAY + "Note: You are running a development build, which are by no means stable. Please report any issues to the issue tracker.");
    }

    @Command(aliases = "modules", desc = "Shows information about modules")
    public void modulesCommand(CommandSender sender) {
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8--- &3/prison modules &8---"));
        sender.sendMessage("&7There are " + Prison.instance.getModuleManager().getModules().size() + " modules loaded.");
        for(Module m : Prison.instance.getModuleManager().getModules())
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&3" + m.getName() + " &8- &a" + m.getStatus()));
        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8--- &3/prison modules &8---"));
    }

    @Command(aliases = "update", desc = "Update the plugin to the latest version.")
    public void updateCommand(CommandSender sender) {
        sender.sendMessage("Update functionality is coming soon.");
    }

    @Command(aliases = "metrics", desc = "Check the status of Metrics.")
    public void metricsCommand(CommandSender sender, @Switch('d') boolean disable) {
        if(disable) sender.sendMessage("I'll disable it once you add it!");
        else sender.sendMessage("Obviously, Metrics isn't running because you haven't added it yet. SirFaizdat, you're such an idiot.");
    }

}
