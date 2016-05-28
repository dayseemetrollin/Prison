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

package io.github.sirfaizdat.prison.internal;

/**
 * Schedule tasks for the server to run.
 *
 * @author SirFaizdat
 * @since 3.0
 */
public interface Scheduler {

    /**
     * Schedules a task to be run after a delay.
     *
     * @param delay  Amount of time to wait before starting, in ticks.
     * @param task   The {@link Runnable} which runs the task.
     * @return The ID of the task, or -1 if the scheduling failed.
     */
    int scheduleSync(long delay, Runnable task);

    /**
     * Schedules a task to be run once every interval.
     *
     * @param delay  Amount of time to wait before starting, in ticks.
     * @param period Amount of time to wait before repeating, in ticks.
     * @param task   The {@link Runnable} which runs the task.
     * @return The ID of the task, or -1 if the scheduling failed.
     */
    int scheduleSyncRepeating(long delay, long period, Runnable task);

    /**
     * SChedules a task to be run asynchronously.
     *
     * @param delay The delay to wait before starting, in ticks.
     * @param task  The {@link Runnable} containing the task to run.
     * @return The ID of the task, or -1 if the scheduling failed.
     */
    int scheduleAsync(long delay, Runnable task);

    /**
     * Schedules a task to be run once every interval, asynchronously.
     *
     * @param delay  Amount of time to wait before starting, in ticks.
     * @param period Amount of time to wait before repeating, in ticks.
     * @param task   The {@link Runnable} which runs the task.
     * @return The ID of the task, or -1 if the scheduling failed.
     */
    int scheduleAsyncRepeating(long delay, long period, Runnable task);

}
