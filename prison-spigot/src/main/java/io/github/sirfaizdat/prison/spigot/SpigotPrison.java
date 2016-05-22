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
import io.github.sirfaizdat.prison.internal.item.Enchantment;
import io.github.sirfaizdat.prison.internal.item.ItemStack;
import io.github.sirfaizdat.prison.internal.world.Location;
import io.github.sirfaizdat.prison.internal.world.Material;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @author SirFaizdat
 */
public class SpigotPrison extends JavaPlugin {

    Prison prison;
    SpigotPlatform platform;
    SpigotListener listener;
    CommandMap commandMap;

    @Override
    public void onEnable() {
        initCommandMap();
        platform = new SpigotPlatform(this);
        listener = new SpigotListener(this);
        prison = new Prison(platform);
    }

    @Override
    public void onDisable() {
    }

    private void initCommandMap() {
        final Field bukkitCommandMap;
        try {
            bukkitCommandMap = Bukkit.getServer().getClass().getDeclaredField("commandMap");

            bukkitCommandMap.setAccessible(true);
            commandMap = (CommandMap) bukkitCommandMap.get(Bukkit.getServer());
        } catch (NoSuchFieldException | IllegalAccessException e) {
            platform.log("&c&lReflection error: &7Ensure that you're using the latest version of Spigot and Prison.");
            e.printStackTrace();
        }
    }

    // Wrapper-ing methods

    public static Location wrapLocation(org.bukkit.Location location) {
        return new Location(new SpigotWorld(location.getWorld()), location.getX(), location.getY(), location.getZ(), location.getPitch(), location.getYaw());
    }

    public static org.bukkit.Location wrapLocation(Location location) {
        return new org.bukkit.Location(Bukkit.getWorld(location.getWorld().getName()), location.getX(), location.getY(), location.getZ(), location.getPitch(), location.getYaw());
    }

    public static ItemStack wrapItemStack(org.bukkit.inventory.ItemStack itemStack) {
        return new ItemStack(Material.valueOf(itemStack.getType().name()), itemStack.getData().getData(), itemStack.getAmount(), wrapEnchants(itemStack.getEnchantments()));
    }

    public static org.bukkit.inventory.ItemStack wrapItemStack(ItemStack itemStack) {
        org.bukkit.inventory.ItemStack ret =  new org.bukkit.inventory.ItemStack(org.bukkit.Material.valueOf(itemStack.getMaterial().name()), itemStack.getAmount(), itemStack.getData());
        ret.addEnchantments(wrapEnchantsToBukkit(itemStack.getEnchantments()));
        return ret;
    }

    private static Map<Enchantment, Integer> wrapEnchants(Map<org.bukkit.enchantments.Enchantment, Integer> enchants) {
        Map<Enchantment, Integer> ret = new HashMap<>();
        enchants.forEach((enchantment, level) -> ret.put(Enchantment.getByName(enchantment.getName()), level));
        return ret;
    }

    private static Map<org.bukkit.enchantments.Enchantment, Integer> wrapEnchantsToBukkit(Map<Enchantment, Integer> enchants) {
        Map<org.bukkit.enchantments.Enchantment, Integer> ret = new HashMap<>();
        enchants.forEach((enchantment, level) -> ret.put(org.bukkit.enchantments.Enchantment.getByName(enchantment.getName()), level));
        return ret;
    }

}
