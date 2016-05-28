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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.github.sirfaizdat.prison.internal.Platform;
import io.github.sirfaizdat.prison.internal.commands.CommandHandler;
import io.github.sirfaizdat.prison.internal.commands.PluginCommand;
import io.github.sirfaizdat.prison.internal.modules.ModuleManager;
import io.github.sirfaizdat.prison.utils.Alerts;

/**
 * The entry point for an implementation of Prison.
 *
 * @author SirFaizdat
 * @since 3.0
 */
public class Prison {

    public static Prison instance;
    private Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();
    private Platform platform;
    private Alerts alerts;
    private CommandHandler commandHandler;
    private ModuleManager moduleManager;

    public Prison(Platform platform) {
        instance = this;
        this.platform = platform;
        this.alerts = new Alerts();
        this.commandHandler = new CommandHandler();
        this.moduleManager = new ModuleManager();

        ConfigurationLoader.getInstance().loadConfiguration();
        commandHandler.registerCommands(new PrisonCommand());
    }

    public void cleanUp() {
        moduleManager.unregisterAll();
    }

    // The getters and setters

    public Platform getPlatform() {
        return platform;
    }

    public Configuration getConfiguration() {
        return ConfigurationLoader.getInstance().getConfiguration();
    }

    public Alerts getAlerts() {
        return alerts;
    }

    public CommandHandler getCommandHandler() {
        return commandHandler;
    }

    public ModuleManager getModuleManager() {
        return moduleManager;
    }

    public PluginCommand getCommand(String label) {
        for (PluginCommand command : platform.getCommands())
            if (command.getLabel().equalsIgnoreCase(label)) return command;
        return null;
    }

    public boolean isDevBuild() {
        return getPlatform().getPluginVersion().contains("-SNAPSHOT");
    }

    public Gson getGson() {
        return gson;
    }

}