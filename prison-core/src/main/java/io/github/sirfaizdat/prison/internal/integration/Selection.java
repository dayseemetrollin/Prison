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

package io.github.sirfaizdat.prison.internal.integration;

import io.github.sirfaizdat.prison.internal.world.Location;

/**
 * Represents a selection in the world.
 *
 * @author SirFaizdat
 * @since 3.0
 */
public class Selection {

    private Location min, max;

    public Selection(Location min, Location max) {
        this.min = min;
        this.max = max;
    }

    public Location getMin() {
        return min;
    }

    public void setMin(Location min) {
        this.min = min;
    }

    public Location getMax() {
        return max;
    }

    public void setMax(Location max) {
        this.max = max;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Selection)) return false;

        Selection selection = (Selection) o;

        if (!min.equals(selection.min)) return false;
        return max.equals(selection.max);

    }

    @Override
    public int hashCode() {
        int result = min.hashCode();
        result = 31 * result + max.hashCode();
        return result;
    }

}
