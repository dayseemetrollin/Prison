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

/**
 * Methods for interacting with server permissions plugins.
 *
 * @author SirFaizdat
 * @since 3.0
 */
public interface PermissionIntegration {

    /**
     * Returns the rank of a player in a world on a ladder.
     *
     * @param player The {@link Player}.
     * @param world  The {@link World}to check in. Not case sensitive.
     * @param ladder The name of the ladder to retrieve the rank from.
     * @return The name of the player's rank, or null if the player, ladder, or world couldn't be found.
     */
    String getPlayerRank(Player player, World world, String ladder);

    /**
     * Returns the rankk of a player in a world on the default ladder.
     *
     * @param player The {@link Player}
     * @param world  The {@link World}to check in. Not case sensitive.
     * @return The name of the player's rank, or null if the player or world couldn't be found.
     */
    String getPlayerRank(Player player, World world);

    /**
     * Returns a list of the ranks that a player has in a world on a ladder.
     *
     * @param player The {@link Player}
     * @param world  The {@link World}to check in. Not case sensitive.
     * @param ladder The name of the ladder to retrieve the list from.
     * @return The array of rank names, or null if the player, ladder, or world couldn't be found.
     */
    String[] getRanks(Player player, World world, String ladder);

    /**
     * Returns a list of the ranks that a player has in a world on the default ladder.
     *
     * @param player The {@link Player}
     * @param world  The {@link World}to check in. Not case sensitive.
     * @return The array of rank names, or null if the player or world couldn't be found.
     */
    String[] getRanks(Player player, World world);

    /**
     * Returns a list of all the ranks on the server, in a ladder.
     *
     * @param ladder The ladder to list ranks for.
     * @return The array of rank names, or null if the ladder couldn't be found.
     */
    String[] getAllRanks(String ladder);

    /**
     * Returns a list of all the arnks on the server, in the default ladder.
     *
     * @return The array of rank names.
     */
    String[] getAllRanks();

    /**
     * Set the rank of a player in a world.
     *
     * @param player The {@link Player}
     * @param rank   The new rank.
     * @param world  The world to set the rank in.
     * @throws IllegalArgumentException If the player, rank, or world does not exist.
     */
    void setRank(Player player, String rank, World world);

}
