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

package io.github.sirfaizdat.prison.platform.interfaces;

import io.github.sirfaizdat.prison.platform.Location;

/**
 * Methods to interact with the server's selection plugin.
 *
 * @author SirFaizdat
 * @since 3.0
 */
public interface SelectionIntegration {

    /**
     * Returns a player's current {@link Selection}.
     *
     * @param player The {@link Player}
     * @return The {@link Selection}, or null if the player hasn't selected anything yet.
     */
    Selection getSelection(Player player);

    class Selection {

        Location min, max;

        public Selection(Location min, Location max) {
            this.min = min;
            this.max = max;
        }

        public Location getMin() {
            return min;
        }

        public Location getMax() {
            return max;
        }

    }

    /**
     * @return The name of the plugin that is providing the selections.
     */
    String getPluginName();

}
