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

package io.github.sirfaizdat.prison.utils.adapters;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.github.sirfaizdat.prison.Prison;
import io.github.sirfaizdat.prison.internal.world.World;

import java.io.IOException;
import java.util.Optional;

/**
 * @author SirFaizdat
 */
public class AdapterWorld extends TypeAdapter<World> {

    @Override
    public void write(JsonWriter out, World value) throws IOException {
        out.value(value.getName());
    }

    @Override
    public World read(JsonReader in) throws IOException {
        Optional<World> world = Prison.instance.getPlatform().getWorld(in.nextString());
        if(!world.isPresent()) return null;
        return world.get();
    }

}
