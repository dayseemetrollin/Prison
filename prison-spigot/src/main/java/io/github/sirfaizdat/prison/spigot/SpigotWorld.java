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

import io.github.sirfaizdat.prison.internal.world.Block;
import io.github.sirfaizdat.prison.internal.world.Location;
import io.github.sirfaizdat.prison.internal.world.World;

/**
 * @author SirFaizdat
 */
public class SpigotWorld implements World {

    private org.bukkit.World bWorld;

    public SpigotWorld(org.bukkit.World bWorld) {
        this.bWorld = bWorld;
    }

    @Override
    public String getName() {
        return bWorld.getName();
    }

    @Override
    public Block getBlockAt(Location loc) {
        return null;
    }

    @Override
    public void setBlockAt(Location loc, Block block) {

    }

}
