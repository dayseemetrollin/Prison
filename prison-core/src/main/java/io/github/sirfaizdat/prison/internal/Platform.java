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

package io.github.sirfaizdat.prison.internal;

import io.github.sirfaizdat.prison.events.Event;
import io.github.sirfaizdat.prison.internal.commands.PluginCommand;
import io.github.sirfaizdat.prison.internal.entity.Player;
import io.github.sirfaizdat.prison.internal.events.EventListener;
import io.github.sirfaizdat.prison.internal.events.EventType;
import io.github.sirfaizdat.prison.internal.integration.SelectionIntegration;
import io.github.sirfaizdat.prison.internal.world.World;

import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Represents a platform that Prison has been implemented.
 *
 * @author SirFaizdat
 * @since 3.0
 */
public interface Platform {

    /**
     * Returns the scheduler that can be used for scheduling tasks on the server.
     */
    Scheduler getScheduler();

    // Events

    /**
     * Registers a listener for an event type.
     *
     * @param type     The {@link EventType} to listen for.
     * @param runnable The {@link EventListener} which is called when the event occurs.
     */
    void listen(EventType type, EventListener runnable);

    /**
     * Fires an event.
     *
     * @param event The {@link Event} to fire.
     */
    void fire(Event event);

    // Commands

    /**
     * Registers a command with the server implementation.
     *
     * @param command The {@link PluginCommand} to register.
     */
    void registerCommand(PluginCommand command);

    /**
     * Returns a list of all registered commands.
     */
    List<PluginCommand> getCommands();

    // Plugin operations

    /**
     * Reloads all Prison data, such as configuration.
     */
    void reload();

    /**
     * Log a message to the server's console.
     *
     * @param message The message to log. This may include colors.
     * @param format  The values to insert into {@link String#format(String, Object...)}, which will be called on the message before it is colored.
     */
    void log(String message, Object... format);

    // World

    /**
     * Returns a world instance for the specified name.
     *
     * @param name The name of the world.
     * @return The {@link World} instance, or null if a world by that name couldn't be found.
     */
    Optional<World> getWorld(String name);

    /**
     * Returns an online player.
     *
     * @param name The name of the player.
     * @return The {@link Optional} containing the player. The optional will be empty if the player is not online.
     */
    Optional<Player> getPlayer(String name);

    /**
     * Returns an online player.
     *
     * @param uuid The {@link UUID} of the player.
     * @return The {@link Optional} containing the player. The optional will be empty if the player is not online.
     */
    Optional<Player> getPlayer(UUID uuid);

    /**
     * Returns all the online players.
     */
    List<Player> getOnlinePlayers();

    // Plugin information

    /**
     * Returns the folder where the plugin should store data.
     * Implementations must initializer the data folder before it's provided.
     */
    File getPluginFolder();

    /**
     * Returns the version of Prison.
     */
    String getPluginVersion();

    // Integration

    /**
     * Returns the {@link SelectionIntegration} object from the implementation.
     */
    SelectionIntegration getSelectionIntegration();

}
