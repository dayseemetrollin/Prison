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

import io.github.sirfaizdat.prison.events.Event;
import io.github.sirfaizdat.prison.events.ModuleFailEvent;
import io.github.sirfaizdat.prison.internal.Platform;
import io.github.sirfaizdat.prison.internal.commands.PluginCommand;
import io.github.sirfaizdat.prison.internal.entity.Player;
import io.github.sirfaizdat.prison.internal.events.EventListener;
import io.github.sirfaizdat.prison.internal.events.EventType;
import io.github.sirfaizdat.prison.internal.integration.SelectionIntegration;
import io.github.sirfaizdat.prison.internal.world.World;
import io.github.sirfaizdat.prison.spigot.events.SpigotModuleFailEvent;
import io.github.sirfaizdat.prison.spigot.integration.WorldEditIntegration;
import io.github.sirfaizdat.prison.utils.ChatColor;
import io.github.sirfaizdat.prison.utils.TextUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;

import java.io.File;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author SirFaizdat
 */
public class SpigotPlatform implements Platform {

    private SpigotPrison spigotPrison;
    private List<PluginCommand> commands = new ArrayList<>();

    public SpigotPlatform(SpigotPrison spigotPrison) {
        this.spigotPrison = spigotPrison;
    }

    @Override
    public int schedule(long delay, long period, Runnable task) {
        return spigotPrison.getServer().getScheduler().scheduleSyncRepeatingTask(spigotPrison, task, delay, period);
    }

    @Override
    public void listen(EventType type, EventListener runnable) {
        spigotPrison.listener.register(type, runnable);
    }

    @Override
    public void fire(Event event) {
        if(event instanceof ModuleFailEvent) Bukkit.getServer().getPluginManager().callEvent(new SpigotModuleFailEvent((ModuleFailEvent) event));
        else log("&c&lError: &7Core event " + event.getClass().getName() + " attempted to fire, but the implementation does not support it!");
    }

    @Override
    public void registerCommand(PluginCommand command) {
        spigotPrison.commandMap.register(command.getLabel(), "prison", new Command(command.getLabel(), command.getDescription(), command.getUsage(), Arrays.asList()) {
            @Override
            public boolean execute(CommandSender sender, String commandLabel, String[] args) {
                return spigotPrison.prison.getCommandHandler().onCommand(new SpigotCommandSender(sender), command, commandLabel, args);
            }
        });
        commands.add(command);
    }

    @Override
    public List<PluginCommand> getCommands() {
        return commands;
    }

    @Override
    public void reload() {
    }

    @Override
    public void log(String message, Object... format) {
        ConsoleCommandSender sender = Bukkit.getConsoleSender();
        message = TextUtils.parse("&3Prison &8> " + message, format);
        if (sender == null) Bukkit.getLogger().info(ChatColor.stripColor(message));
        else sender.sendMessage(message);
    }

    @Override
    public Optional<World> getWorld(String name) {
        if (Bukkit.getWorld(name) == null) return Optional.empty();
        return Optional.of(new SpigotWorld(Bukkit.getWorld(name)));
    }

    @Override
    public Optional<Player> getPlayer(String name) {
        if (Bukkit.getPlayer(name) == null || !Bukkit.getPlayer(name).isOnline()) return Optional.empty();
        return Optional.of(new SpigotPlayer(Bukkit.getPlayer(name)));
    }

    @Override
    public Optional<Player> getPlayer(UUID uuid) {
        if (Bukkit.getPlayer(uuid) == null || !Bukkit.getPlayer(uuid).isOnline()) return Optional.empty();
        return Optional.of(new SpigotPlayer(Bukkit.getPlayer(uuid)));
    }

    @Override
    public List<Player> getOnlinePlayers() {
        return Bukkit.getOnlinePlayers().stream().map((Function<org.bukkit.entity.Player, SpigotPlayer>) SpigotPlayer::new).collect(Collectors.toList());
    }

    @Override
    public File getPluginFolder() {
        return spigotPrison.getDataFolder();
    }

    @Override
    public String getPluginVersion() {
        return spigotPrison.getDescription().getVersion();
    }

    @Override
    public SelectionIntegration getSelectionIntegration() {
        return new WorldEditIntegration();
    }

}
