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

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * The entry point for an implementation of Prison.
 *
 * @author SirFaizdat
 * @since 3.0
 */
public class Prison {

    private Platform platform;
    private Configuration configuration;

    public Prison(Platform platform) {
        this.platform = platform;
        this.loadConfig();
    }

    private void loadConfig() {
        Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();
        File configFile = new File(getPlatform().getPluginFolder(), "config.json");

        try {

            if (!configFile.exists()) {
                configuration = new Configuration();
                String json = gson.toJson(configuration);
                Files.write(json, configFile, Charset.defaultCharset());
                return;
            }

            configuration = gson.fromJson(String.join("\n", Files.readLines(configFile, Charset.defaultCharset())), Configuration.class);

        } catch (IOException e) {
            getPlatform().log("&cFailed to read/write the config.json file. &8Reason: %s", e.getMessage());
            e.printStackTrace();
        }

    }

    public Platform getPlatform() {
        return platform;
    }

    public Configuration getConfiguration() {
        return configuration;
    }
}
