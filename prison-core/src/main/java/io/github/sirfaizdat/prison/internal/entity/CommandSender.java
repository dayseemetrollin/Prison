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

/**
 * Represents something that sent a command.
 * This could be a command block, the console, or a player.
 *
 * @author SirFaizdat
 * @since 3.0
 */
public interface CommandSender {

    /**
     * Returns the name of the command sender.
     */
    String getName();

    /**
     * Returns the {@link Type} of the command sender.
     */
    Type getType();

    /**
     * Checks if the command sender has a permission.
     *
     * @param perm The permission to check.
     * @return true if the sender has a permission, false otherwise.
     */
    boolean hasPermission(String perm);

    /**
     * Returns true if the command sender is an op, false otherwise.
     */
    boolean isOp();

    /**
     * Send a message to the command sender.
     *
     * @param message The message to send.
     */
    void sendMessage(String message);

    /**
     * Sen multiple messages to the command sender.
     * Each entry in the array is sent on a new line.
     *
     * @param messages The messages to send.
     */
    void sendMessage(String[] messages);

    /**
     * The type of command sender.
     */
    enum Type {
        /**
         * The command sender is a player.
         */
        PLAYER,
        /**
         * The command sender is a command block.
         */
        BLOCK,
        /**
         * The command sender is a console.
         */
        CONSOLE;
    }

}
