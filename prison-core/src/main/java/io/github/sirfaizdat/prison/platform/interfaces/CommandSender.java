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
 * Methods for interacting with command senders (things that send commands).
 *
 * @author SirFaizdat
 * @since 3.0
 */
public interface CommandSender {

    /**
     * @return The name of this command sender.
     */
    String getName();

    /**
     * Returns whether or not the sender has this permission.
     *
     * @param permission The permission to check.
     * @return true if the sender has the permission, false otherwise.
     */
    boolean hasPermission(String permission);

    /**
     * Send a message to the command sender.
     * This may include colors.
     *
     * @param message The message to send.
     */
    void sendMessage(String message);

    /**
     * Send an array of messages to the command sender.
     * This may include colors. Each entry in the array will be sent in its own line.
     *
     * @param messages The messages to send.
     */
    default void sendMessage(String[] messages) {
        for (String msg : messages) sendMessage(msg);
    }

}
