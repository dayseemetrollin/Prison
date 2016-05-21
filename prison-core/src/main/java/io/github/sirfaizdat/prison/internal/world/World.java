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

package io.github.sirfaizdat.prison.internal.world;

/**
 * Represents a world on a Minecraft server.
 *
 * @author SirFaizdat
 * @since 3.0
 */
public interface World {

    /**
     * Returns the name of this world.
     * @return A {@link String}.
     */
    String getName();

    /**
     * Returns the {@link Block} at a specific {@link Location} in the world.
     * Note that the returned block is immutable. See {@link #setBlockAt(Location, Block)} to edit blocks.
     *
     * @param loc The {@link Location}.
     * @return The {@link Block}. If no block is found at the location, this will return air.
     * @see #setBlockAt(Location, Block)
     */
    Block getBlockAt(Location loc);

    /**
     * Set a block in the world.
     *
     * @param loc The {@link Location}.
     * @param block The {@link Block}.
     */
    void setBlockAt(Location loc, Block block);

}
