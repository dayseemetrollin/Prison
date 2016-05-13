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

import io.github.sirfaizdat.prison.Prison;
import io.github.sirfaizdat.prison.platform.Configuration;
import io.github.sirfaizdat.prison.platform.Platform;
import io.github.sirfaizdat.prison.platform.interfaces.Player;
import io.github.sirfaizdat.prison.platform.interfaces.World;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * The Spigot implementation of Prison.
 *
 * @author SirFaizdat
 */
public class SpigotPrison extends JavaPlugin implements Platform {

    Prison prison;

    @Override
    public void onEnable() {
        prison = new Prison(this);
        prison.getCommandManager().registerCommands(new SpigotCommands());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof org.bukkit.entity.Player) dispatchCommand(new SpigotPlayer((org.bukkit.entity.Player) sender), label, args);
        else dispatchCommand(new SpigotCommandSender(sender), label, args);
        return true;
    }

    @Override
    public Configuration getConfiguration() {
        return new SpigotConfiguration(this);
    }

    @Override
    public World getWorld(String name) {
        return new SpigotWorld(Bukkit.getWorld(name));
    }

    @Override
    public Player getPlayer(String name) {
        return new SpigotPlayer(Bukkit.getPlayer(name));
    }

    @Override
    public List<Player> getOnlinePlayers() {
        List<Player> players = new ArrayList<>();
        for(org.bukkit.entity.Player bPlayer : Bukkit.getOnlinePlayers()) players.add(new SpigotPlayer(bPlayer));
        return players;
    }

    @Override
    public Player getPlayer(UUID uid) {
        return new SpigotPlayer(Bukkit.getPlayer(uid));
    }

}
