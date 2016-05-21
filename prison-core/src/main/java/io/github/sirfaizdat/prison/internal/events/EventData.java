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

package io.github.sirfaizdat.prison.internal.events;

import java.util.HashMap;
import java.util.Map;

/**
 * Stores data about an event.
 *
 * @author SirFaizdat
 * @since 3.0
 */
public class EventData {

    private Map<String, Object> entries = new HashMap<>();

    public Object get(String key) {
        return entries.get(key);
    }

    public boolean containsKey(String key) {
        return entries.containsKey(key);
    }

    public void put(String key, Object val) {
        entries.put(key, val);
    }

    public void clear() {
        entries.clear();
    }

}
