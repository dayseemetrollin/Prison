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

package io.github.sirfaizdat.prison.internal.modules;

import io.github.sirfaizdat.prison.Prison;
import io.github.sirfaizdat.prison.events.MineResetEvent;
import io.github.sirfaizdat.prison.events.ModuleFailEvent;
import io.github.sirfaizdat.prison.internal.events.EventListener;
import io.github.sirfaizdat.prison.internal.events.EventType;
import io.github.sirfaizdat.prison.utils.TextUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Manages instances of {@link Module}.
 *
 * @author SirFaizdat
 * @since 3.0
 */
public class ModuleManager {

    private List<Module> modules;
    private Map<Module, String> failedModules;

    public ModuleManager() {
        modules = new ArrayList<>();
        failedModules = new HashMap<>();
        Prison.instance.getPlatform().listen(ModuleFailEvent.class, onModuleFail());
    }

    public void register(Module m) {
        modules.add(m);
        m.init();
        m.setEnabled(true);
    }

    public void disableAll() {
        modules.forEach(module -> {
            if (!failedModules.containsKey(module)) module.deinit();
            module.setEnabled(false);
        });
        modules.clear();
        failedModules.clear();
    }

    public void unregister(Module m) {
        m.deinit();
        modules.remove(m);
    }

    public void unregisterAll() {
        modules.forEach(Module::deinit);
        modules.clear();
    }

    public Module getModule(String name) {
        for(Module m : modules) if(m.getName().equalsIgnoreCase(name)) return m;
        return null;
    }

    public List<Module> getModules() {
        return modules;
    }

    private EventListener onModuleFail() {
        return event -> {
            ModuleFailEvent e = (ModuleFailEvent) event;
            e.getModule().setEnabled(false);
            failedModules.put(e.getModule(), e.getFailReason());
            Prison.instance.getAlerts().alert("&c&lAlert: &7Module " + e.getModule().getName() + " failed to enable. Reason: &c" + e.getFailReason() + TextUtils.dotIfNotPresent(e.getFailReason(), "&7"));
        };
    }

}
