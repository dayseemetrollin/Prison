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

package io.github.sirfaizdat.prison.mines.resets;

import io.github.sirfaizdat.prison.mines.Mine;
import io.github.sirfaizdat.prison.platform.Location;
import io.github.sirfaizdat.prison.platform.Material;

/**
 * @author SirFaizdat
 */
public class ResetMethodWool implements ResetMethod {

    @Override
    public boolean run(Mine mine) {
        int minX = mine.getMinLoc().getBlockX();
        int minY = mine.getMinLoc().getBlockY();
        int minZ = mine.getMinLoc().getBlockZ();
        int maxX = mine.getMaxLoc().getBlockX();
        int maxY = mine.getMaxLoc().getBlockY();
        int maxZ = mine.getMaxLoc().getBlockZ();

        for (int x = minX; x <= maxX; x++)
            for (int y = minY; y <= maxY; y++)
                for (int z = minZ; z <= maxZ; z++)
                    mine.getMaxLoc().getWorld().setBlockAt(new Location(mine.getMaxLoc().getWorld(), x, y, z), Material.WOOL, 0);
        return true;
    }

    public String name() {
        return "wool";
    }
}
