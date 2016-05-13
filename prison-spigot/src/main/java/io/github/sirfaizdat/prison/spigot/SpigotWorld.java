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

import io.github.sirfaizdat.prison.platform.Location;
import io.github.sirfaizdat.prison.platform.Material;
import io.github.sirfaizdat.prison.platform.interfaces.World;
import org.apache.commons.lang3.tuple.ImmutablePair;

/**
 * @author SirFaizdat
 */
public class SpigotWorld implements World {

    org.bukkit.World bWorld;

    public SpigotWorld(org.bukkit.World bWorld) {
        this.bWorld = bWorld;
    }

    @Override
    public String getName() {
        return bWorld.getName();
    }

    @Override
    public ImmutablePair<Material, Integer> getBlockAt(Location loc) {
        Material mat = Material.valueOf(bWorld.getBlockAt(SpigotUtils.getBukkitLocation(loc)).getType().name());
        int data = bWorld.getBlockAt(SpigotUtils.getBukkitLocation(loc)).getData();
        return new ImmutablePair<>(mat, data);
    }

    @Override
    public void setBlockAt(Location loc, Material mat, int data) {
        bWorld.getBlockAt(SpigotUtils.getBukkitLocation(loc)).setType(SpigotUtils.getBukkitMaterial(mat));
        bWorld.getBlockAt(SpigotUtils.getBukkitLocation(loc)).setData((byte) data);
    }
}
