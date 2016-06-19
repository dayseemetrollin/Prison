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

import io.github.sirfaizdat.prison.Prison;
import io.github.sirfaizdat.prison.mines.Mine;

import java.util.HashMap;
import java.util.Map;

/**
 * A trigger that resets a mine after an amount of seconds.
 *
 * @author SirFaizdat
 * @since 3.0
 */
public class TimeTrigger implements Trigger {

    private Map<Mine, Double> mines = new HashMap<>();

    @Override
    public void register(Mine mine) {
        if (mines.containsKey(mine)) return;
        // A reset interval of 0.0 indicates that the mine is using the default reset interval (i.e. synchronized)
        mines.put(mine, mine.getResetInterval() == 0.0 ? Prison.instance.getConfiguration().defaultResetInterval : mine.getResetInterval());
    }

    @Override
    public void unregister(Mine mine) {
        if (!mines.containsKey(mine)) return;
        mines.remove(mine);
    }

    @Override
    public boolean trigger(Mine mine) {
        // A reset interval of 0.0 indicates that the mine is using the default reset interval (i.e. synchronized)
        double resetInterval = mine.getResetInterval();
        if(resetInterval == 0.0) resetInterval = Prison.instance.getConfiguration().defaultResetInterval;

        // Since the mine's reset interval can change at runtime,
        // it's important to check for an illegal value and correct it.
        if (mines.get(mine) > resetInterval) {
            mines.put(mine, resetInterval);
            return false;
        }

        mines.put(mine, mines.get(mine) - 1); // Update the entry

        // Reset the mine if it's time
        if (mines.get(mine) <= 0) {
            Prison.instance.getPlatform().getScheduler().scheduleSync(0, () -> mine.getResetMethod().run(mine)); // Mines must be reset synchronously, and we're in an async thread

            // A reset interval of 0.0 indicates that the mine is using the default reset interval (i.e. synchronized)
            mines.put(mine, mine.getResetInterval() == 0.0 ? Prison.instance.getConfiguration().defaultResetInterval : mine.getResetInterval());
            return true;
        }
        return false;
    }

    @Override
    public String name() {
        return "time";
    }

}
