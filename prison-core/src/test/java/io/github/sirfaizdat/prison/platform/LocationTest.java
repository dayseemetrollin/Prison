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

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author SirFaizdat
 */
public class LocationTest {

    @Test
    public void testEquals() throws Exception {
        Location loc1 = new Location(null, 10, 10, 10);
        Location loc2 = new Location(null, 10, 10, 10);
        Location loc3 = new Location(null, 20, 10, 10);
        Location loc4 = new Location(null, 20, 10, 10, 0.5f, 1.3f);

        assertEquals(loc1, loc2);
        assertNotEquals(loc2, loc3);
        assertNotEquals(loc3, loc4);
    }

    @Test
    public void testHashCode() throws Exception {
        Location loc1 = new Location(null, 10, 10, 20);
        Location loc2 = new Location(null, 10, 10, 20);

        assertEquals(loc1.hashCode(), loc2.hashCode());
    }

    @Test
    public void testCollections() throws Exception {
        Location loc1 = new Location(null, 10, 10, 20);
        Location loc2 = new Location(null, 10, 10, 20);
        Location loc3 = new Location(null, 10, 10, 20);
        Location loc4 = new Location(null, 10, 10, 30);

        List<Location> locations = Arrays.asList(loc1, loc2);
        assertTrue(locations.contains(loc3));
        assertFalse(locations.contains(loc4));
    }

}