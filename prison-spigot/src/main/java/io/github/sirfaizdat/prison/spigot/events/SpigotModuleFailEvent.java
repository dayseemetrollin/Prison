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

package io.github.sirfaizdat.prison.spigot.events;

import io.github.sirfaizdat.prison.events.ModuleFailEvent;
import io.github.sirfaizdat.prison.internal.modules.Module;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * @author SirFaizdat
 */
public class SpigotModuleFailEvent extends Event {

    private static HandlerList handlerList = new HandlerList();

    private ModuleFailEvent event;

    public SpigotModuleFailEvent(ModuleFailEvent event) {
        this.event = event;
    }

    public SpigotModuleFailEvent(Module m, String failReason) {
        event = new ModuleFailEvent(m, failReason);
    }

    public Module getModule() {
        return event.getModule();
    }

    public String getFailReason() {
        return event.getFailReason();
    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }

    public static HandlerList getHandlerList() {
        return handlerList;
    }

}
