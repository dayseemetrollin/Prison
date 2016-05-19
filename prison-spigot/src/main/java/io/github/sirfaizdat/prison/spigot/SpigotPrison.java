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

import com.sk89q.intake.CommandMapping;
import com.sk89q.intake.Description;
import com.sk89q.intake.dispatcher.Dispatcher;
import io.github.sirfaizdat.prison.Prison;
import io.github.sirfaizdat.prison.command.CommandManager;
import io.github.sirfaizdat.prison.platform.Configuration;
import io.github.sirfaizdat.prison.platform.Platform;
import io.github.sirfaizdat.prison.platform.interfaces.Player;
import io.github.sirfaizdat.prison.platform.interfaces.World;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;
import java.util.*;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.stream.Collectors;

/**
 * The Spigot implementation of Prison.
 *
 * @author SirFaizdat
 */
public class SpigotPrison extends JavaPlugin implements Platform {

    private Prison prison;

    @Override
    public void onEnable() {
        prison = new Prison(this);
        registerCommands();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof org.bukkit.entity.Player)
            dispatchCommand(new SpigotPlayer((org.bukkit.entity.Player) sender), label, args);
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
        return Bukkit.getOnlinePlayers().stream().map((Function<org.bukkit.entity.Player, SpigotPlayer>) SpigotPlayer::new).collect(Collectors.toList());
    }

    @Override
    public Player getPlayer(UUID uid) {
        return new SpigotPlayer(Bukkit.getPlayer(uid));
    }

    @Override
    public String getVersion() {
        return getDescription().getVersion();
    }

    @Override
    public void registerCommands() {
        // Here, we're going to get the command map so we can edit it.
        // The point of this method is to tell Spigot that these commands exist, without having to put them in plugin.yml
        // This allows for maximum laziness.
        final Field bukkitCommandMap;
        final CommandMap commandMap;
        try {
            bukkitCommandMap = Bukkit.getServer().getClass().getDeclaredField("commandMap");

            bukkitCommandMap.setAccessible(true);
            commandMap = (CommandMap) bukkitCommandMap.get(Bukkit.getServer());
        } catch (NoSuchFieldException | IllegalAccessException e) {
            getLogger().log(Level.SEVERE, "Reflection error: Ensure that you're using the latest version of Spigot and Prison.", e);
            return;
        }

        // Now, we're inserting the commands into the commandMap.
        CommandManager commandManager = prison.getCommandManager();
        Dispatcher dispatcher = commandManager.getDispatcher();
        Set<CommandMapping> mappings = dispatcher.getCommands();
        for (CommandMapping mapping : mappings)
            commandMap.register(mapping.getPrimaryAlias(), toBukkitCommand(mapping));

    }

    /**
     * Convert a {@link CommandMapping} to a {@link BukkitCommand}, to submit it to Spigot.
     *
     * @param mapping The {@link CommandMapping}
     * @return The {@link BukkitCommand}
     */
    private BukkitCommand toBukkitCommand(CommandMapping mapping) {
        Description description = mapping.getDescription();
        String desc = description.getShortDescription() == null ? ("Type /" + mapping.getPrimaryAlias() + " help for details.") : description.getShortDescription();
        String usage = description.getUsage() == null ? "No usage provided." : description.getUsage();
        return new BukkitCommand(mapping.getPrimaryAlias(), desc, usage, Arrays.asList(mapping.getAllAliases())) {
            @Override
            public boolean execute(CommandSender sender, String label, String[] args) {
                if (sender instanceof org.bukkit.entity.Player)
                    dispatchCommand(new SpigotPlayer((org.bukkit.entity.Player) sender), label, args);
                else dispatchCommand(new SpigotCommandSender(sender), label, args);
                return true;
            }
        };
    }

}
