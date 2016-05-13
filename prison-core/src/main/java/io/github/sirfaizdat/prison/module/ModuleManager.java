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

package io.github.sirfaizdat.prison.module;

import com.google.common.base.Preconditions;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages all module instances, actions (enabling and disabling), and their status.
 *
 * @author SirFaizdat
 * @since 3.0
 */
public class ModuleManager {

    List<Module> modules = new ArrayList<>();

    /**
     * Tells the ModuleManager that your module exists, enables it, and sets its status appropriately.
     * This same method can be used to re-enable the module later, if you choose to disable it.
     *
     * @param module The {@link Module} to register/enable.
     */
    public void registerModule(Module module) {
        Preconditions.checkNotNull(module);
        if (module.isEnabled()) return;

        if (!modules.contains(module)) modules.add(module);
        module.setStatus("Enabling...");
        module.init();
        // If the module has any other status besides what we set, it means the module set its own error status.
        // Otherwise, we say that the module has enabled successfully.
        if (module.getStatus().equals("Enabling...")) module.setStatus("Enabled.");
    }

    /**
     * Tells the ModuleManager to disable the module and set its status appropriately.
     *
     * @param module The Module to disable.
     */
    public void disableModule(Module module) {
        Preconditions.checkNotNull(module);
        if (!module.isEnabled()) return;
        module.deinit();
        module.setStatus("Disabled.");
    }

    /**
     * Tells the ModuleManager to totally forget about this module.
     * It will be disabled and removed from the list. Note that, if you try to retrieve
     * this module again using {@link #getModule(String)}, you will get <code>null</code>.
     *
     * @param module The {@link Module} to unregister.
     */
    public void unregisterModule(Module module) {
        disableModule(module);
        modules.remove(module);
    }

    /**
     * Returns the {@link Module} instance by the name specified.
     *
     * @param name The name of the module (set in {@link Module#getName()}.
     * @return The {@link Module} if found, or null if the module does not exist by the name specified.
     */
    public Module getModule(String name) {
        Preconditions.checkNotNull(name);
        for (Module m : modules) if (m.getName().equalsIgnoreCase(name)) return m;
        return null;
    }

}
