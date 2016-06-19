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

import io.github.sirfaizdat.prison.events.Event;
import io.github.sirfaizdat.prison.events.MineResetEvent;
import io.github.sirfaizdat.prison.events.ModuleFailEvent;
import io.github.sirfaizdat.prison.internal.events.EventListener;
import io.github.sirfaizdat.prison.spigot.events.SpigotMineResetEvent;
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

    /*
     * You are not expected to understand the following code, but I'll try to explain it as best as I can.
     *
     * This class exists to call the prison-core EventListeners whenever the event is fired through Bukkit's event system.
     * Before the event reaches this stage, it has already been fired from Prison's event system to Bukkit's event system through the Platform's fire() method.
     * This is basically responding to that firing. Here, the listeners are registered and stored by event class type. They're looped through and called, and given new instances of
     * Prison's events so that they can use them platform independently.
     */

    SpigotPrison spigotPrison;
    Map<Class<? extends Event>, List<EventListener>> listeners = new HashMap<>();

    public SpigotListener(SpigotPrison spigotPrison) {
        this.spigotPrison = spigotPrison;
        spigotPrison.getServer().getPluginManager().registerEvents(this, spigotPrison);
    }

    public void register(Event event, EventListener listener) {
        // Create the list if it's not already there.
        if (!listeners.containsKey(event.getClass())) {
            listeners.put(event.getClass(), Arrays.asList(listener));
            return;
        }
        // Add it and store it
        List<EventListener> listenerList = listeners.get(event.getClass());
        listenerList.add(listener);
        listeners.put(event.getClass(), listenerList);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        if (!listeners.containsKey(io.github.sirfaizdat.prison.events.PlayerJoinEvent.class)) return;
        listeners.get(io.github.sirfaizdat.prison.events.PlayerJoinEvent.class).forEach(eventListener -> eventListener.handle(new io.github.sirfaizdat.prison.events.PlayerJoinEvent(new SpigotPlayer(e.getPlayer()))));
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        if (!listeners.containsKey(io.github.sirfaizdat.prison.events.PlayerQuitEvent.class)) return;
        listeners.get(io.github.sirfaizdat.prison.events.PlayerQuitEvent.class).forEach(eventListener -> eventListener.handle(new io.github.sirfaizdat.prison.events.PlayerQuitEvent(new SpigotPlayer(e.getPlayer()))));
    }

    @EventHandler
    public void onModuleFail(SpigotModuleFailEvent e) {
        if (!listeners.containsKey(ModuleFailEvent.class)) return;
        listeners.get(ModuleFailEvent.class).forEach(eventListener -> eventListener.handle(new ModuleFailEvent(e.getModule(), e.getFailReason())));
    }

    @EventHandler
    public void onMineReset(SpigotMineResetEvent e) {
        if (!listeners.containsKey(MineResetEvent.class)) return;
        listeners.get(MineResetEvent.class).forEach(eventListener -> eventListener.handle(new MineResetEvent(e.getMine())));
    }

}
