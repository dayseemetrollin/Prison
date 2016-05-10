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
import io.github.sirfaizdat.prison.platform.Material;
import org.apache.commons.lang3.tuple.ImmutablePair;

/**
 * A world on the Minecraft server. Worlds are accessed from the server using
 * {@link io.github.sirfaizdat.prison.platform.Platform}.
 *
 * @author SirFaizdat
 * @since 3.0
 */
public interface World {

    /**
     * Returns the name of the world. This is how the world should be recognized.
     *
     * @return A {@link String} that equals the world name.
     */
    String getName();

    /**
     * Returns the block at the specified location.
     *
     * @param loc The {@link Location} to look for the block.
     * @return An {@link ImmutablePair}, with the left object being the material and the right object being the data value.
     * If there is no block located at the specified location, the material will be air.
     */
    ImmutablePair<Material, Integer> getBlockAt(Location loc);

    /**
     * Set the block at a specified location.
     * @param loc The {@link Location} to set the block.
     * @param mat The {@link Material} to make the block.
     * @param data The data value of this block. Most of the time, you can set this to 0.
     */
    void setBlockAt(Location loc, Material mat, int data);

}
