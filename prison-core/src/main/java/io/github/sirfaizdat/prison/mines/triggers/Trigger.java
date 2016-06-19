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

package io.github.sirfaizdat.prison.mines.triggers;

import io.github.sirfaizdat.prison.mines.Mine;

/**
 * Triggers tell mines to reset when a certain criteria is met.
 *
 * @author SirFaizdat
 * @since 3.0
 */
public interface Trigger {

    /**
     * Tell the trigger that a certain mine should be triggered by it.
     *
     * @param mine The {@link Mine} to register
     */
    void register(Mine mine);

    /**
     * Tell the trigger that a certain mine is no longer triggered by it.
     *
     * @param mine The {@link Mine} to register
     */
    void unregister(Mine mine);

    /**
     * Checks if the mine should be reset. If it should be reset, it is reset.
     * Called every second, asynchronously.
     *
     * @param mine The {@link Mine} to check on
     * @return true if the mine reset, and false if it didn't. If this is true, then the {@link io.github.sirfaizdat.prison.events.MineResetEvent} is fired.
     *
     */
    boolean trigger(Mine mine);

    /**
     * A human-readable name for this trigger.
     */
    String name();

}
