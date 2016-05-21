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

package io.github.sirfaizdat.prison.internal.entity;

import io.github.sirfaizdat.prison.internal.item.ItemStack;
import io.github.sirfaizdat.prison.internal.world.Location;

import java.util.Optional;
import java.util.UUID;

/**
 * Represents an in-game player.
 *
 * @author SirFaizdat
 * @since 3.0
 */
public interface Player extends CommandSender {

    /**
     * Returns the {@link UUID} of the player.
     */
    UUID getUniqueID();

    /**
     * Returns the location of the player.
     */
    Location getLocation();

    /**
     * Sets the location of the player to the one specified.
     *
     * @param location The new {@link Location}.
     */
    void teleport(Location location);

    /**
     * Returns the {@link ItemStack} that's in the player's main hand.
     */
    ItemStack getItemInMainHand();

    /**
     * Returns the {@link ItemStack} that's in the player's off hand.
     * If there is nothing in the off hand (i.e. no off hand is present), then the {@link java.util.Optional} will contain nothing.
     */
    Optional<ItemStack> getItemInOffHand();

}
