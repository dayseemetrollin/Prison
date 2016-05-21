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

import io.github.sirfaizdat.prison.internal.Platform;
import io.github.sirfaizdat.prison.internal.entity.Player;
import io.github.sirfaizdat.prison.internal.world.World;
import io.github.sirfaizdat.prison.utils.ChatColor;
import io.github.sirfaizdat.prison.utils.TextUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author SirFaizdat
 */
public class SpigotPlatform implements Platform {

    private SpigotPrison spigotPrison;

    public SpigotPlatform(SpigotPrison spigotPrison) {
        this.spigotPrison = spigotPrison;
    }

    @Override
    public int schedule(long delay, long period, Runnable task) {
        return spigotPrison.getServer().getScheduler().scheduleSyncRepeatingTask(spigotPrison, task, delay, period);
    }

    @Override
    public void reload() {
    }

    @Override
    public void log(String message, Object... format) {
        ConsoleCommandSender sender = Bukkit.getConsoleSender();
        message = TextUtils.parse("&3Prison &8> " + message, format);
        if(sender == null) Bukkit.getLogger().info(ChatColor.stripColor(message));
        else sender.sendMessage(message);
    }

    @Override
    public Optional<World> getWorld(String name) {
        if(Bukkit.getWorld(name) == null) return Optional.empty();
        return Optional.of(new SpigotWorld(Bukkit.getWorld(name)));
    }

    @Override
    public Optional<Player> getPlayer(String name) {
        if(Bukkit.getPlayer(name) == null || !Bukkit.getPlayer(name).isOnline()) return Optional.empty();
        return Optional.of(new SpigotPlayer(Bukkit.getPlayer(name)));
    }

    @Override
    public Optional<Player> getPlayer(UUID uuid) {
        if(Bukkit.getPlayer(uuid) == null || !Bukkit.getPlayer(uuid).isOnline()) return Optional.empty();
        return Optional.of(new SpigotPlayer(Bukkit.getPlayer(uuid)));
    }

    @Override
    public List<Player> getOnlinePlayers() {
        List<Player> ret = new ArrayList<>();
        for(org.bukkit.entity.Player player : Bukkit.getOnlinePlayers()) ret.add(new SpigotPlayer(player));
        return ret;
    }

    @Override
    public File getPluginFolder() {
        return spigotPrison.getDataFolder();
    }

}
