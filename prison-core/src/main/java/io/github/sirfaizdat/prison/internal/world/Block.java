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

package io.github.sirfaizdat.prison.internal.world;

import io.github.sirfaizdat.prison.utils.TextUtils;

/**
 * Represents a block in the world.
 *
 * @author SirFaizdat
 * @since 3.0
 */
public class Block {

    private Material material;
    private byte data;

    public Block(Material material, byte data) {
        this.material = material;
        this.data = data;
    }

    public Block(Material material) {
        this.material = material;
        this.data = 0;
    }

    public Block(String s) {
        String[] split = TextUtils.packAndSplit(s, ":");
        this.material = Material.matchMaterial(split[0]);
        if (material == null) throw new IllegalArgumentException(split[0] + " is not a material");

        if (split.length < 2) data = 0;
        else
            try {
                this.data = Byte.parseByte(split[1]);
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

    public byte getData() {
        return data;
    }

    public void setData(byte data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Block)) return false;

        Block block = (Block) o;

        if (data != block.data) return false;
        return material == block.material;

    }

    @Override
    public int hashCode() {
        int result = material.hashCode();
        result = 31 * result + (int) data;
        return result;
    }

    @Override
    public String toString() {
        return material.name() + ":" + data;
    }
}
