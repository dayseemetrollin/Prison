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

package io.github.sirfaizdat.prison.mines.methods;

import io.github.sirfaizdat.prison.internal.world.Block;
import io.github.sirfaizdat.prison.internal.world.Location;
import io.github.sirfaizdat.prison.internal.world.Material;
import io.github.sirfaizdat.prison.mines.Mine;
import io.github.sirfaizdat.prison.mines.ResetMethod;

/**
 * @author SirFaizdat
 */
public class ResetMethodTest implements ResetMethod {
    @Override
    public void run(Mine mine) {
        int minX = mine.getStart().getBlockX();
        int minY = mine.getStart().getBlockY();
        int minZ = mine.getStart().getBlockZ();
        int maxX = mine.getEnd().getBlockX();
        int maxY = mine.getEnd().getBlockY();
        int maxZ = mine.getEnd().getBlockZ();

        for (int y = minY; y <= maxY; y++)
            for (int x = minX; x <= maxX; x++)
                for (int z = minZ; z <= maxZ; z++)
                    mine.getWorld().setBlockAt(new Location(mine.getWorld(), x, y, z), new Block(Material.BRICK));
    }

    @Override
    public String name() {
        return "test";
    }
}
