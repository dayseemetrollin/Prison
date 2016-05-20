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

import io.github.sirfaizdat.prison.Prison;
import io.github.sirfaizdat.prison.mines.composition.MineComposition;
import io.github.sirfaizdat.prison.mines.resets.ResetMethod;
import io.github.sirfaizdat.prison.mines.resets.ResetMethods;
import io.github.sirfaizdat.prison.platform.Location;
import io.github.sirfaizdat.prison.platform.interfaces.World;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * @author SirFaizdat
 */
public class Mine {

    private String name;
    private Location minLoc, maxLoc;
    private ResetMethod resetMethod;
    private MineComposition composition;

    public Mine(String name, Location minLoc, Location maxLoc, ResetMethod resetMethod, MineComposition composition) {
        this.name = name;
        this.minLoc = minLoc;
        this.maxLoc = maxLoc;
        this.resetMethod = resetMethod;
        this.composition = composition;
    }

    public static Mine load(File loc) throws IOException, ParseException, MineLoadException {
        JSONParser parser = new JSONParser();
        Object object = parser.parse(new FileReader(loc));
        JSONObject obj = (JSONObject) object;

        String name = (String) obj.get("name");

        // Load the world
        String worldName = (String) obj.get("world");
        World world = Prison.instance.getPlatform().getWorld(worldName);
        if (world == null) throw new MineLoadException("the world " + worldName + " does not exist.");

        // Load the minimum location
        // For some reason, integers are saved as longs. So, we just make the long into an int.
        int minX = Math.toIntExact((long) obj.get("minX"));
        int minY = Math.toIntExact((long) obj.get("minY"));
        int minZ = Math.toIntExact((long) obj.get("minZ"));
        Location minLoc = new Location(world, minX, minY, minZ);

        // Load the maximum location
        int maxX = Math.toIntExact((long) obj.get("maxX"));
        int maxY = Math.toIntExact((long) obj.get("maxY"));
        int maxZ = Math.toIntExact((long) obj.get("maxZ"));
        Location maxLoc = new Location(world, maxX, maxY, maxZ);

        // Load the reset method
        String resetMethodName = (String) obj.get("resetMethod");
        ResetMethod method = ResetMethods.getInstance().getResetMethodByName(resetMethodName);
        if (method == null) {
            Prison.instance.getPlatform().print("&eReset method " + resetMethodName + " not found; using default method.");
            method = ResetMethods.getInstance().getResetMethodByName(
                    Prison.instance.getPlatform().getConfiguration().getString("default-reset-method"));
        }

        // Load the composition
        List<String> compStringList = (List<String>) obj.get("composition");
        MineComposition comp = new MineComposition(compStringList);

        return new Mine(name, minLoc, maxLoc, method, comp);
    }

    @SuppressWarnings("unchecked")
    public void save(File loc) throws IOException {
        // First, we add it...
        JSONObject obj = new JSONObject();
        obj.put("name", name);
        obj.put("world", minLoc.getWorld().getName());

        // minLoc serialization
        obj.put("minX", minLoc.getBlockX());
        obj.put("minY", minLoc.getBlockY());
        obj.put("minZ", minLoc.getBlockZ());

        // maxLoc serialization
        obj.put("maxX", maxLoc.getBlockX());
        obj.put("maxY", maxLoc.getBlockY());
        obj.put("maxZ", maxLoc.getBlockZ());

        // resetMethod serialization
        obj.put("resetMethod", resetMethod.name());

        // composition serialization
        obj.put("composition", composition.toStringList());

        // ... and now we write it
        if (!loc.exists()) loc.createNewFile();
        FileWriter writer = new FileWriter(loc);
        writer.write(obj.toJSONString());
        writer.flush();
        writer.close();
    }

    public String getName() {
        return name;
    }

    public Location getMinLoc() {
        return minLoc;
    }

    public Location getMaxLoc() {
        return maxLoc;
    }

    public ResetMethod getResetMethod() {
        return resetMethod;
    }

    public MineComposition getComposition() {
        return composition;
    }
}
