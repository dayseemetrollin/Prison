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

package io.github.sirfaizdat.prison.mines;

import io.github.sirfaizdat.prison.Prison;
import io.github.sirfaizdat.prison.events.MineResetEvent;
import io.github.sirfaizdat.prison.internal.entity.Player;
import io.github.sirfaizdat.prison.internal.events.EventListener;

/**
 * @author SirFaizdat
 */
class ResetMessageBroadcaster {

    private MineModule mineModule;

    ResetMessageBroadcaster(MineModule mineModule) {
        this.mineModule = mineModule;
        Prison.instance.getPlatform().listen(MineResetEvent.class, broadcastIndividualMineResets());
        Prison.instance.getPlatform().getScheduler().scheduleAsyncRepeating(Prison.instance.getConfiguration().defaultResetInterval * 20L, Prison.instance.getConfiguration().defaultResetInterval * 20L, broadcastSynchronizedMineResets());
    }

    private Runnable broadcastSynchronizedMineResets() {
        return () -> {
            if (Prison.instance.getConfiguration().enableMultiworld)
                for (String world : Prison.instance.getConfiguration().worlds)
                    broadcastToWorld(world, "&3All mines &7have been reset.");
            else
                for (Player player : Prison.instance.getPlatform().getOnlinePlayers())
                    player.sendMessage("&3All mines &7have been reset.");
        };
    }

    private EventListener broadcastIndividualMineResets() {
        return event -> {
            MineResetEvent e = (MineResetEvent) event;
            if (e.getMine().isSync()) return;
            Prison.instance.getPlatform().log("Reset interval: " + e.getMine().getResetInterval());
            if (Prison.instance.getConfiguration().enableMultiworld)
                for (String world : Prison.instance.getConfiguration().worlds)
                    broadcastToWorld(world, "&7Mine &3" + e.getMine().getName() + " &7has been reset.");
            else
                for (Player player : Prison.instance.getPlatform().getOnlinePlayers())
                    player.sendMessage("&7Mine &3" + e.getMine().getName() + " &7has been reset.");
        };
    }

    private void broadcastToWorld(String worldName, String msg) {
        Prison.instance.getPlatform().getOnlinePlayers().stream().filter(player -> player.getLocation().getWorld().getName().equalsIgnoreCase(worldName)).forEach(player -> player.sendMessage(msg));
    }

}
