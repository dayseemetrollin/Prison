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

package io.github.sirfaizdat.prison.spigot;

import io.github.sirfaizdat.prison.internal.Scheduler;

/**
 * @author SirFaizdat
 */
public class SpigotScheduler implements Scheduler {

    private SpigotPrison spigotPrison;

    public SpigotScheduler(SpigotPrison spigotPrison) {
        this.spigotPrison = spigotPrison;
    }

    @Override
    public int scheduleSync(long delay, Runnable task) {
        return spigotPrison.getServer().getScheduler().runTaskLater(spigotPrison, task, delay).getTaskId();
    }

    @Override
    public int scheduleSyncRepeating(long delay, long period, Runnable task) {
        return spigotPrison.getServer().getScheduler().runTaskTimer(spigotPrison, task, delay, period).getTaskId();
    }

    @Override
    public int scheduleAsync(long delay, Runnable task) {
        return spigotPrison.getServer().getScheduler().runTaskLaterAsynchronously(spigotPrison, task, delay).getTaskId();
    }

    @Override
    public int scheduleAsyncRepeating(long delay, long period, Runnable task) {
        return spigotPrison.getServer().getScheduler().runTaskTimerAsynchronously(spigotPrison, task, delay, period).getTaskId();
    }

}
