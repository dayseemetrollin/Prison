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
import io.github.sirfaizdat.prison.events.ModuleFailEvent;

/**
 * A module is a subsystem that performs a certain task.
 * For example, there can be a mines module, a ranks module, a shops module, etc.
 * Modules can be enabled and disabled independently of each other, so only certain parts of Prison can be enabled if the user desires.
 * Additionally, if one part of Prison fails, other parts may still run (which is the beauty of the module system).
 *
 * @author SirFaizdat
 * @since 3.0
 */
public abstract class Module {

    private String name;
    private boolean enabled = true;

    public Module(String name) {
        this.name = name;
    }

    public abstract void init();

    public abstract void deinit();

    public void fail(String failReason) {
        Prison.instance.getPlatform().fire(new ModuleFailEvent(this, failReason));
        setEnabled(false);
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Module)) return false;

        Module module = (Module) o;

        return name.equals(module.name);

    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

}
