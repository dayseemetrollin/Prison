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

import com.google.common.io.Files;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.github.sirfaizdat.prison.internal.Platform;
import io.github.sirfaizdat.prison.internal.commands.CommandHandler;
import io.github.sirfaizdat.prison.internal.commands.PluginCommand;
import io.github.sirfaizdat.prison.internal.modules.ModuleManager;
import io.github.sirfaizdat.prison.mines.MinesModule;
import io.github.sirfaizdat.prison.utils.Alerts;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;

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
    private Configuration configuration;
    private Alerts alerts;
    private CommandHandler commandHandler;
    private ModuleManager moduleManager;

    public Prison(Platform platform) {
        instance = this;
        this.platform = platform;
        this.loadConfig();
        this.alerts = new Alerts();
        this.commandHandler = new CommandHandler();
        moduleManager = new ModuleManager();

        commandHandler.registerCommands(new PrisonCommand());
        moduleManager.register(new MinesModule());
    }

    public void cleanUp() {
        moduleManager.unregisterAll();
    }

    // Configuration stuff

    private void loadConfig() {
        File configFile = new File(getPlatform().getPluginFolder(), "config.json");
        try {
            if (!configFile.exists()) {
                writeNewConfig();
                return;
            }

            String json = String.join("\n", Files.readLines(configFile, Charset.defaultCharset()));
            if(validateConfigVersion(json, configFile)) return;
            configuration = gson.fromJson(json, Configuration.class);
        } catch (IOException e) {
            alerts.alert("&c&lAlert: &7Failed to load the configuration file. Check the console for details.");
            getPlatform().log("&cFailed to read/write the config.json file. &8Reason: %s", e.getMessage());
            e.printStackTrace();
        }

    }

    private void writeNewConfig() throws IOException {
        configuration = new Configuration();
        String json = gson.toJson(configuration);
        Files.write(json, new File(getPlatform().getPluginFolder(), "config.json"), Charset.defaultCharset());
    }

    private boolean validateConfigVersion(String json, File configFile) throws IOException {
        if (!json.toLowerCase().contains("  \"version\": " + Configuration.VERSION)) { // Hopefully the user won't attempt to change the config line.
            // The configuration is out of date
            String fileName = "old-config-" + new SimpleDateFormat("yyyyMMddhhmm'.json'").format(new Date()); // old-config-timestamp.json
            configFile.renameTo(new File(getPlatform().getPluginFolder(), fileName)); // Rename the file
            writeNewConfig();
            alerts.alert("&c&lAlert: &7The configuration file has been recreated. I saved your old configuration file for your reference; remember to reconfigure Prison!");
            return true;
        }
        // The configuration is not out of date
        return false;
    }

    // The getters and setters

    public Platform getPlatform() {
        return platform;
    }

    public Configuration getConfiguration() {
        return configuration;
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