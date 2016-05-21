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

import io.github.sirfaizdat.prison.internal.entity.Player;

/**
 * Different event types.
 *
 * @author SirFaizdat
 * @since 3.0
 */
public enum EventType {

    /**
     * Called when a player joins.
     * Event data:
     * <table>
     * <col width="35%"/>
     * <col width="65%"/>
     * <thead>
     * <tr><th>Key</th><th>Description</th></tr>
     * <thead>
     * <tbody>
     * <tr><td>"player"</td><td>The {@link Player} that joined.</td></tr>
     * </tbody>
     * </table>
     */
    PLAYER_JOIN,

    /**
     * Called when a player leaves.
     * Event data:
     * <table>
     * <col width="35%"/>
     * <col width="65%"/>
     * <thead>
     * <tr><th>Key</th><th>Description</th></tr>
     * <thead>
     * <tbody>
     * <tr><td>"player"</td><td>The {@link Player} that quit.</td></tr>
     * </tbody>
     * </table>
     */
    PLAYER_QUIT;

}
