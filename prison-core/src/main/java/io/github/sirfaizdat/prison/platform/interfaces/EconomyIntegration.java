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
 * Methods for interacting with the server's economy system.
 *
 * @author SirFaizdat
 * @since 3.0
 */
public interface EconomyIntegration {

    /**
     * Returns the balance of a player.
     *
     * @param player The {@link Player} to check the balance of.
     * @param world  The {@link World} to check the balance in. If the economy plugin does not support worlds, then this argument will be ignored.
     * @return The balance of the player, or Double.MIN_VALUE if the player does not have a balance (called an account on some plugins).
     */
    double getBalance(Player player, World world);

    /**
     * Returns the balance of a player. If the economy plugin supports worlds, then the player's current world is used.
     *
     * @param player The {@link Player} to check the balance of.
     * @return The balance of the player, or Double.MIN_VALUE if the player does not have a balance (called an account on some plugins).
     */
    double getBalance(Player player);

    /**
     * Set the balance of a player.
     *
     * @param player The {@link Player} to set the balance of.
     * @param amount The amount to set the balance to.
     */
    void setBalance(Player player, double amount);

    /**
     * Returns true if the amount of money the player has is greater than or equal to the amount specified.
     *
     * @param player The {@link Player}.
     * @param world  The {@link World} to check the balance in. If the economy plugin does not support worlds, then this argument will be ignored.
     * @param amount The amount to compare.
     * @return true if the player's balance is greater than or equal too (>=) the amount, false otherwise (or if the player does not have a balance/account).
     */
    default boolean hasBalance(Player player, World world, double amount) {
        return getBalance(player, world) >= amount;
    }

    /**
     * Returns true if the amount of money the player has is greater than or equal to the amount specified.
     * If the economy plugin supports worlds, then the player's current world is used.
     *
     * @param player The {@link Player}.
     * @param amount The amount to compare.
     * @return true if the player's balance is greater than or equal too (>=) the amount, false otherwise (or if the player does not have a balance/account).
     */
    default boolean hasBalance(Player player, double amount) {
        return hasBalance(player, player.getLocation().getWorld(), amount);
    }

    /**
     * @return The name of the plugin that is providing the economy.
     */
    String getPluginName();

}
