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

package io.github.sirfaizdat.prison;

import io.github.sirfaizdat.prison.command.CommandManager;
import io.github.sirfaizdat.prison.module.ModuleManager;
import io.github.sirfaizdat.prison.platform.Platform;

/**
 * Stores instances for the rest of prison-core to use.
 * Each implementation should instantiate this upon their enable, providing their {@link Platform} implementation.
 *
 * @author SirFaizdat
 * @since 3.0
 */
public class Prison {

    public static Prison instance;
    private Platform platform;
    private CommandManager commandManager;
    private ModuleManager moduleManager;

    public Prison(Platform platform) {
        instance = this;
        this.platform = platform;
        this.commandManager = new CommandManager();
        this.moduleManager = new ModuleManager();

        this.commandManager.registerCommands("prison", new PrisonCommands());
    }

    public Platform getPlatform() {
        return platform;
    }

    public CommandManager getCommandManager() {
        return commandManager;
    }

    public ModuleManager getModuleManager() {
        return moduleManager;
    }

}
