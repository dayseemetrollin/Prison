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

package io.github.sirfaizdat.prison.mines.trigger;

import io.github.sirfaizdat.prison.Prison;
import io.github.sirfaizdat.prison.mines.Mine;
import io.github.sirfaizdat.prison.mines.MineModule;

import java.io.IOException;

/**
 * Triggers a mine reset at a timed interval.
 *
 * @author SirFaizdat
 * @since 3.0
 */
public class TimeTrigger implements Trigger {

    private MineModule mineModule;

    public TimeTrigger() {
        this.mineModule = (MineModule) Prison.instance.getModuleManager().getModule("Mines");
    }

    @Override
    public boolean shouldReset(Mine mine) {
        if (!mine.getExtraData().containsKey("interval")) {
            mine.addExtraData("interval", "600"); // Default to every 10 minutes
            saveMine(mine);
        }

        mine.addExtraData("currentTimer", "0"); // Default to 0 seconds, since we haven't started yet

        long interval = Long.parseLong(mine.getExtraData().get("interval"));
        long currentTimer = Long.parseLong(mine.getExtraData().get("currentTimer"));

        // It's time for a reset.
        // TODO This doesn't work
        if (currentTimer >= interval) {
            mine.getResetMethod().run(mine);
            mine.addExtraData("currentTimer", "0"); // Reset the timer
            saveMine(mine);
            return true;
        }

        // Here it's not, just update the timer
        mine.addExtraData("currentTimer", String.valueOf(currentTimer + 1));
        return false;
    }

    private void saveMine(Mine mine) {
        try {
            mine.save(mineModule.getMineFile(mine.getName()));
        } catch (IOException e) {
            Prison.instance.getAlerts().alert("&c&lAlert: &7Failed to save the mine " + mine.getName() + ".");
            Prison.instance.getPlatform().log("&c&lAlert: &7Failed to save the mine " + mine.getName() + ".");
            e.printStackTrace();
        }
    }

    @Override
    public String name() {
        return "time";
    }
}
