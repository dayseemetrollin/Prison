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

import io.github.sirfaizdat.prison.internal.entity.CommandSender;
import io.github.sirfaizdat.prison.utils.ChatColor;
import io.github.sirfaizdat.prison.utils.TextUtils;
import org.bukkit.block.CommandBlock;
import org.bukkit.entity.Player;

/**
 * @author SirFaizdat
 */
public class SpigotCommandSender implements CommandSender {

    private org.bukkit.command.CommandSender bCommandSender;

    public SpigotCommandSender(org.bukkit.command.CommandSender bCommandSender) {
        this.bCommandSender = bCommandSender;
    }

    @Override
    public String getName() {
        return bCommandSender.getName();
    }

    @Override
    public Type getType() {
        if(bCommandSender instanceof Player) return Type.PLAYER;
        else if(bCommandSender instanceof CommandBlock) return Type.BLOCK;
        return Type.CONSOLE;
    }

    @Override
    public boolean hasPermission(String perm) {
        return bCommandSender.hasPermission(perm);
    }

    @Override
    public boolean isOp() {
        return bCommandSender.isOp();
    }

    @Override
    public void sendMessage(String message) {
        bCommandSender.sendMessage(TextUtils.parse(message));
    }

    @Override
    public void sendMessage(String[] messages) {
        for (String message : messages) sendMessage(message);
    }
}
