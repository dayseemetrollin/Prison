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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author SirFaizdat
 */
public class MineComposition {

    private Map<Block, Double> compositionMap;

    public MineComposition(Map<Block, Double> compositionMap) {
        this.compositionMap = compositionMap;
    }

    public MineComposition(List<String> stringList) {
        compositionMap = new HashMap<>();
        for (String s : stringList) {
            String[] split = TextUtil.packAndSplit(s, "=");
            add(new Block(split[0]), Double.parseDouble(split[1]));
        }
    }

    public void add(Block block, double chance) {
        compositionMap.put(block, chance);
    }

    public void add(Material mat, double chance) {
        add(new Block(mat, (short) 0), chance);
    }

    public void remove(Block block) {
        compositionMap.remove(block);
    }

    public List<String> toStringList() {
        return compositionMap.entrySet().stream().map(entry -> entry.getKey().toString() + "=" + entry.getValue()).collect(Collectors.toList());
    }

    public boolean contains(Block block) {
        return compositionMap.containsKey(block);
    }

    public double getChance(Block block) {
        return compositionMap.get(block);
    }

    public double getPercentageFull() {
        double ret = 0.0D;
        for (double chance : compositionMap.values()) ret += chance;
        return ret;
    }

    public Map<Block, Double> getCompositionMap() {
        return compositionMap;
    }

}
