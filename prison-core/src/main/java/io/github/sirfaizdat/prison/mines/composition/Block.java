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

package io.github.sirfaizdat.prison.mines.composition;

import io.github.sirfaizdat.prison.platform.Material;
import io.github.sirfaizdat.prison.platform.TextUtil;

/**
 * @author SirFaizdat
 */
public class Block {

    private Material material;
    private short data;

    public Block(Material material, short data) {
        this.material = material;
        this.data = data;
    }

    public Block(String s) {
        String[] split = TextUtil.packAndSplit(s, ":");
        this.material = Material.matchMaterial(split[0]);
        if (material == null) throw new IllegalArgumentException(split[0] + " is not a material");

        if (split.length < 2) data = 0;
        else
            try {
                this.data = Short.parseShort(split[1]);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(split[1] + " is not a number");
            }
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public short getData() {
        return data;
    }

    public void setData(short data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Block)) return false;
        Block b = (Block) obj;
        return b.getMaterial() == this.getMaterial() && b.getData() == this.getData();
    }

    /**
     * Returns the hashcode value for this block.
     * The hash code for a Block is computed by adding the ID of the block's Material to the block's data value.
     *
     * @return The hash code value for this Block
     */
    @Override
    public int hashCode() {
        return getMaterial().getId() + getData();
    }

    @Override
    public String toString() {
        return material.name() + ":" + data;
    }
}
