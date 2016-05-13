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
import org.bukkit.Bukkit;

/**
 * Utilities for converting Prison classes to Spigot classes.
 *
 * @author SirFaizdat
 */
public class SpigotUtils {

    private SpigotUtils() {

    }

    public static org.bukkit.World getBukkitWorld(World world) {
        return Bukkit.getWorld(world.getName());
    }

    public static org.bukkit.Location getBukkitLocation(Location loc) {
        return new org.bukkit.Location(getBukkitWorld(loc.getWorld()), loc.getX(), loc.getY(), loc.getZ(), loc.getPitch(), loc.getYaw());
    }

    public static Location getPrisonLocation(org.bukkit.Location loc) {
        return new Location(new SpigotWorld(loc.getWorld()), loc.getX(), loc.getY(), loc.getZ(), loc.getPitch(), loc.getYaw());
    }

    public static org.bukkit.Material getBukkitMaterial(Material mat) {
        return org.bukkit.Material.matchMaterial(mat.name());
    }

}
