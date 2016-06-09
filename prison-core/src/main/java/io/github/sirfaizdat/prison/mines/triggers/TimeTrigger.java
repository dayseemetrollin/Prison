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
 * @author SirFaizdat
 */
public class TimeTrigger implements Trigger {

    private Map<Mine, Double> mines = new HashMap<>();

    @Override
    public void register(Mine mine) {
        if (mines.containsKey(mine)) return;
        mines.put(mine, mine.getResetInterval());
    }

    @Override
    public void unregister(Mine mine) {
        if (!mines.containsKey(mine)) return;
        mines.remove(mine);
    }

    @Override
    public void trigger(Mine mine) {
        // Since the mine's reset interval can change at runtime,
        // it's important to check for an illegal value and correct it.
        if (mines.get(mine) > mine.getResetInterval()) {
            mines.put(mine, mine.getResetInterval());
            return;
        }

        mines.put(mine, mines.get(mine) - 1); // Update the entry

        // Reset the mine if it's time
        if (mines.get(mine) <= 0) {
            Prison.instance.getPlatform().getScheduler().scheduleSync(0, () -> mine.getResetMethod().run(mine)); // Mines must be reset synchronously, and we're in an async thread
            mines.put(mine, mine.getResetInterval());
        }
    }

    @Override
    public String name() {
        return "time";
    }

}
