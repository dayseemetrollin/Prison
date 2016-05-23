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

import io.github.sirfaizdat.prison.internal.events.EventData;
import io.github.sirfaizdat.prison.internal.events.EventListener;
import io.github.sirfaizdat.prison.internal.events.EventType;
import io.github.sirfaizdat.prison.spigot.events.SpigotModuleFailEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Listens for various events, and calls the {@link Runnable}s registered by prison-core when necessary.
 *
 * @author SirFaizdat
 */
public class SpigotListener implements Listener {

    SpigotPrison spigotPrison;
    Map<EventType, List<EventListener>> listeners = new HashMap<>();

    public SpigotListener(SpigotPrison spigotPrison) {
        this.spigotPrison = spigotPrison;
        spigotPrison.getServer().getPluginManager().registerEvents(this, spigotPrison);
    }

    public void register(EventType type, EventListener listener) {
        // Create the list if it's not already there.
        if (!listeners.containsKey(type)) {
            listeners.put(type, Arrays.asList(listener));
            return;
        }
        // Add it and store it
        List<EventListener> listenerList = listeners.get(type);
        listenerList.add(listener);
        listeners.put(type, listenerList);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        if (!listeners.containsKey(EventType.PLAYER_JOIN)) return;
        EventData data = new EventData();
        data.put("player", spigotPrison.platform.getPlayer(e.getPlayer().getUniqueId()));
        listeners.get(EventType.PLAYER_JOIN).forEach(eventListener -> eventListener.handle(data));
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        if (!listeners.containsKey(EventType.PLAYER_QUIT)) return;
        EventData data = new EventData();
        data.put("player", e.getPlayer());
        listeners.get(EventType.PLAYER_QUIT).forEach(eventListener -> eventListener.handle(data));
    }

    @EventHandler
    public void onModuleFail(SpigotModuleFailEvent e) {
        if (!listeners.containsKey(EventType.MODULE_FAIL)) return;
        EventData data = new EventData();
        data.put("module", e.getModule());
        data.put("reason", e.getFailReason());
        listeners.get(EventType.MODULE_FAIL).forEach(eventListener -> eventListener.handle(data));
    }

}
