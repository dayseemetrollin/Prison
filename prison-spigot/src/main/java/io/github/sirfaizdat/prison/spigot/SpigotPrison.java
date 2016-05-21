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

import io.github.sirfaizdat.prison.Prison;
import io.github.sirfaizdat.prison.internal.item.ItemStack;
import io.github.sirfaizdat.prison.internal.world.Location;
import io.github.sirfaizdat.prison.internal.world.Material;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * @author SirFaizdat
 */
public class SpigotPrison extends JavaPlugin {

    @Override
    public void onEnable() {
        new Prison(new SpigotPlatform(this));
    }

    @Override
    public void onDisable() {
    }

    // Wrapper-ing methods

    public static Location wrapLocation(org.bukkit.Location location) {
        return new Location(new SpigotWorld(location.getWorld()), location.getX(), location.getY(), location.getZ(), location.getPitch(), location.getYaw());
    }

    public static org.bukkit.Location wrapLocation(Location location) {
        return new org.bukkit.Location(Bukkit.getWorld(location.getWorld().getName()), location.getX(), location.getY(), location.getZ(), location.getPitch(), location.getYaw());
    }

    public static ItemStack wrapItemStack(org.bukkit.inventory.ItemStack itemStack) {
        // TODO Implement enchants
        return new ItemStack(Material.valueOf(itemStack.getType().name()), itemStack.getData().getData(), itemStack.getAmount());
    }

    public static org.bukkit.inventory.ItemStack wrapItemStack(ItemStack itemStack) {
        // TODO Implement enchants
        return new org.bukkit.inventory.ItemStack(org.bukkit.Material.valueOf(itemStack.getMaterial().name()), itemStack.getAmount(), itemStack.getData());
    }

}
