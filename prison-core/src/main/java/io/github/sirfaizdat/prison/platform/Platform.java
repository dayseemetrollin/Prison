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

package io.github.sirfaizdat.prison.platform;

import io.github.sirfaizdat.prison.platform.interfaces.Player;
import io.github.sirfaizdat.prison.platform.interfaces.World;

import java.util.UUID;

/**
 * A platform is a server software which implements the necessary interfaces which Prison needs.
 * An example of a platform is Spigot, which has a radically different API than, say, Sponge.
 *
 * The platform's implementation of this class allows Prison to access the different APIs in Prison interfaces,
 * allowing the same code to be run on any platform. Platforms must handle the implementation of the interfaces,
 * as well as the commands and other user interface features.
 *
 * @author SirFaizdat
 * @since 3.0
 */
public interface Platform {

    /**
     * Returns this platform's implementation of {@link Configuration}, where user-defined settings can be retrieved.
     *
     * @return The {@link Configuration} object.
     */
    Configuration getConfiguration();

    /**
     * Returns a world from the server.
     * @param name The name of the world. This is not case sensitive.
     * @return The {@link World}, or null if the world doesn't exist.
     */
    World getWorld(String name);

    /**
     * Returns a player from the server.
     * @param name The name of the player. This is case sensitive.
     * @return The {@link Player}, or null if the player is not online.
     */
    Player getPlayer(String name);

    /**
     * Returns a player from the server.
     * @param uid The player's unique ID.
     * @return The {@link Player}, or null if the player is not online.
     */
    Player getPlayer(UUID uid);

}