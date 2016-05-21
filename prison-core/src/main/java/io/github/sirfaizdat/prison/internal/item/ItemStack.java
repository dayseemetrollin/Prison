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

package io.github.sirfaizdat.prison.internal.item;

import io.github.sirfaizdat.prison.internal.world.Material;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an item stack in the game.
 *
 * @author SirFaizdat
 */
public class ItemStack {

    private Material material;
    private short data;
    private int amount;
    private List<Enchantment> enchantments;

    public ItemStack(Material material, short data, int amount, List<Enchantment> enchantments) {
        this.material = material;
        this.data = data;
        this.amount = amount;
        this.enchantments = enchantments;
    }

    public ItemStack(Material material, short data, int amount) {
        this(material, data, amount, new ArrayList<>());
    }

    public ItemStack(Material material, int amount) {
        this(material, (short) 0, amount);
    }


    public Material getMaterial() {
        return material;
    }

    public short getData() {
        return data;
    }

    public int getAmount() {
        return amount;
    }

    public List<Enchantment> getEnchantments() {
        return enchantments;
    }

}
