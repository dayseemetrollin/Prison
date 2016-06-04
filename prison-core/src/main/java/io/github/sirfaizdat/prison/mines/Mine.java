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

package io.github.sirfaizdat.prison.mines;

import com.google.common.io.Files;
import io.github.sirfaizdat.prison.Prison;
import io.github.sirfaizdat.prison.internal.world.Block;
import io.github.sirfaizdat.prison.internal.world.Location;
import io.github.sirfaizdat.prison.internal.world.World;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * @author SirFaizdat
 */
public class Mine {

    private String name;
    private World world;
    private Location start, end;
    private ResetMethod resetMethod;
    private Map<String, String> extraData;
    private Map<Block, Double> composition;

    public Mine(String name, World world, Location start, Location end, ResetMethod resetMethod) {
        this.name = name;
        this.world = world;
        this.start = start;
        this.end = end;
        this.resetMethod = resetMethod;
        this.composition = new HashMap<>();
        this.extraData = new HashMap<>();
    }

    public void save(File file) throws IOException {
        String json = ((MineModule) Prison.instance.getModuleManager().getModule("Mines")).getGson().toJson(this);
        Files.write(json, file, Charset.defaultCharset());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public Location getStart() {
        return start;
    }

    public void setStart(Location start) {
        this.start = start;
    }

    public Location getEnd() {
        return end;
    }

    public void setEnd(Location end) {
        this.end = end;
    }

    public ResetMethod getResetMethod() {
        return resetMethod;
    }

    public void setResetMethod(ResetMethod resetMethod) {
        this.resetMethod = resetMethod;
    }

    public Map<Block, Double> getComposition() {
        return composition;
    }

    public void addComposition(Block block, double chance) {
        this.composition.put(block, chance);
    }

    public void removeComposition(Block block) {
        this.composition.remove(block);
    }

    public Map<String, String> getExtraData() {
        if(extraData == null) extraData = new HashMap<>();
        return extraData;
    }

    public void addExtraData(String key, String value) {
        extraData.put(key, value);
    }

    public void removeExtraData(String key) {
        extraData.remove(key);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Mine)) return false;

        Mine mine = (Mine) o;

        if (!name.equals(mine.name)) return false;
        if (!world.equals(mine.world)) return false;
        if (!start.equals(mine.start)) return false;
        if (!end.equals(mine.end)) return false;
        if (!resetMethod.equals(mine.resetMethod)) return false;
        return composition != null ? composition.equals(mine.composition) : mine.composition == null;

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + world.hashCode();
        result = 31 * result + start.hashCode();
        result = 31 * result + end.hashCode();
        result = 31 * result + resetMethod.hashCode();
        result = 31 * result + (composition != null ? composition.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Mine{" +
                "name='" + name + '\'' +
                ", world=" + world +
                ", start=" + start +
                ", end=" + end +
                ", resetMethod=" + resetMethod.name() +
                ", composition=" + composition.size() +
                '}';
    }

}
