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

package io.github.sirfaizdat.prison.platform.interfaces;

import io.github.sirfaizdat.prison.platform.Location;

import java.util.UUID;

/**
 * A player, playing on the Minecraft server. Worlds are accessed from the server using
 * {@link io.github.sirfaizdat.prison.platform.Platform}.
 *
 * @author SirFaizdat
 * @since 3.0
 */
public interface Player extends CommandSender {

    /**
     * The prefered method of identifying players. Unique IDs never change, while player names do.
     *
     * @return A {@link UUID} object containing the player's unique ID.
     */
    UUID getUniqueID();

    /**
     * The player's customized name. May include colors.
     *
     * @return The player's display name (i.e. nickname, "nick")
     */
    String getDisplayName();

    /**
     * Set the player's display name. This may include colors.
     *
     * @param name The new display name.
     */
    void setDisplayName(String name);

    /**
     * @return The {@link Location} of the player.
     */
    Location getLocation();

    /**
     * Send the player to a certain location.
     * If applicable, the teleport cause will be registered as PLUGIN.
     *
     * @param loc The {@link Location} to send the player.
     */
    void setLocation(Location loc);

}
