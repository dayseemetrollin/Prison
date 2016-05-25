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

import io.github.sirfaizdat.prison.internal.entity.Player;

import java.util.Optional;

/**
 * Integrate with the selection plugin on the server.
 * A good example is WorldEdit, but anything can be used.
 *
 * @author SirFaizdat
 * @since 3.0
 */
public interface SelectionIntegration {

    /**
     * Returns a player's selection.
     * If the player hasn't made a selection, the {@link Optional} will be null.
     * @param player The {@link Player} to retrieve the selection from.
     */
    Optional<Selection> getSelection(Player player);

    /**
     * Returns true if the integration is ready for use.
     */
    boolean hasIntegrated();

}
