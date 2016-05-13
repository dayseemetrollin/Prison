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

package io.github.sirfaizdat.prison.spigot;

import io.github.sirfaizdat.prison.platform.ChatColor;
import io.github.sirfaizdat.prison.platform.interfaces.CommandSender;

/**
 * @author SirFaizdat
 */
public class SpigotCommandSender implements CommandSender {

    org.bukkit.command.CommandSender bSender;

    public SpigotCommandSender(org.bukkit.command.CommandSender bSender) {
        this.bSender = bSender;
    }

    @Override
    public String getName() {
        return bSender.getName();
    }

    @Override
    public boolean hasPermission(String permission) {
        return bSender.hasPermission(permission);
    }

    @Override
    public void sendMessage(String message) {
        bSender.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
    }

}
