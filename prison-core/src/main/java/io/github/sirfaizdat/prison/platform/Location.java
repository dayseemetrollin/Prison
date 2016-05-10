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

package io.github.sirfaizdat.prison.platform;

import io.github.sirfaizdat.prison.platform.interfaces.World;

/**
 * A 3D position in a Minecraft world.
 *
 * @author SirFaizdat
 * @since 3.0
 */
public class Location {

    private World world;
    private double x,y, z;
    private float pitch, yaw;

    public Location(World world, double x, double y, double z, float pitch, float yaw) {
        this.world = world;
        this.x = x;
        this.y = y;
        this.z = z;
        this.pitch = pitch;
        this.yaw = yaw;
    }

    public Location(World world, double x, double y, double z) {
        this(world, x, y, z, 0.0f, 0.0f);
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public float getPitch() {
        return pitch;
    }

    public void setPitch(float pitch) {
        this.pitch = pitch;
    }

    public float getYaw() {
        return yaw;
    }

    public void setYaw(float yaw) {
        this.yaw = yaw;
    }

    public int getBlockX() {
        return (int) getX();
    }

    public int getBlockY() {
        return (int) getY();
    }

    public int getBlockZ() {
        return (int) getZ();
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Location)) return false;
        Location loc = (Location) obj;

        // Since worlds may be null, we have to make sure both the object and this have null worlds, or else they're not equal
        boolean worldsEqual;
        if(loc.getWorld() == null && getWorld() == null) worldsEqual = true;
        else worldsEqual = loc.getWorld().equals(getWorld());

        return worldsEqual && loc.getX() == getX() && loc.getY() == getY() && loc.getZ() == getZ() && loc.getPitch() == getPitch() && loc.getYaw() == loc.getYaw();
    }

    @Override
    public int hashCode() {
        return (int) ((getWorld() == null ? 1 : getWorld().getName().hashCode()) + getX() + getY() + getZ() + getPitch() + getYaw());
    }

}
