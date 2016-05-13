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

package io.github.sirfaizdat.prison.spigot;

import io.github.sirfaizdat.prison.platform.Configuration;

/**
 * @author SirFaizdat
 */
public class SpigotConfiguration extends Configuration {

    private SpigotPrison prison;

    public SpigotConfiguration(SpigotPrison prison) {
        this.prison = prison;
    }

    @Override
    public void load() {
        prison.reloadConfig();
        entries = prison.getConfig().getValues(true);
    }

    @Override
    public void saveDefaults() {
        prison.getConfig().addDefaults(defaults);
        prison.saveDefaultConfig();
    }

}
