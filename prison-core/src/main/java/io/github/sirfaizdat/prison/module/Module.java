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

/**
 * Modules are individual units within Prison that are responsible for certain functions.
 * Examples of modules include mines, ranks, shops, cells, guards, etc.
 *
 * @author SirFaizdat
 * @since 3.0
 */
public abstract class Module {

    // Conveniently provided variables

    protected String name;
    private String status;
    private boolean enabled = false;

    public Module(String name) {
        this.name = name;
    }

    // Actual inherited methods

    public abstract void init();

    public abstract void deinit();

    // Conveniently provided getters

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Module)) return false;

        Module module = (Module) o;

        if (!name.equals(module.name)) return false;
        return status != null ? status.equals(module.status) : module.status == null;

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

}
