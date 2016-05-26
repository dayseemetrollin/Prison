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

package io.github.sirfaizdat.prison.mines;

import io.github.sirfaizdat.prison.Prison;
import io.github.sirfaizdat.prison.internal.modules.Module;
import io.github.sirfaizdat.prison.mines.resets.ResetMethodTotal;
import io.github.sirfaizdat.prison.mines.resets.ResetMethods;
import io.github.sirfaizdat.prison.utils.Alerts;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author SirFaizdat
 */
public class MinesModule extends Module {

    public MinesModule() {
        super("Mines");
    }

    private File minesDirectory;
    private List<Mine> loadedMines = new ArrayList<>();

    @Override
    public void init() {
        if (Prison.instance.getPlatform().getSelectionIntegration() == null) {
            fail("&cSelection plugin not found");
            return;
        }

        minesDirectory = new File(Prison.instance.getPlatform().getPluginFolder(), "mines");
        if (!minesDirectory.exists()) minesDirectory.mkdirs();

        ResetMethods.getInstance().addResetMethod(new ResetMethodTotal());

        loadAll();
        Prison.instance.getCommandHandler().registerCommands(new MinesCommand(this));
    }

    @Override
    public void deinit() {

    }

    private void loadAll() {
        File[] files = minesDirectory.listFiles((dir, name) -> {
            return name.endsWith(".mine.json");
        });

        Prison.instance.getPlatform().log("Attempting to load " + files.length + " mines...");

        for (File file : files)
            try {
                Mine mine = Mine.load(file);
                loadedMines.add(mine);
            } catch (IOException | ParseException e) {
                Prison.instance.getAlerts().alert("&6&lWarning: &7Failed to read the JSON file " + file.getName() + ". Check the console for details. :(");
                Prison.instance.getPlatform().log("&eAn internal error occurred while attempting to read the JSON " +
                        "file " + file.getName() + ".");
                e.printStackTrace();
            } catch (MineLoadException e) {
                Prison.instance.getAlerts().alert("&6&lWarning: &7Failed to load the mine " + file.getName() + " because &6" + e.getMessage());
            }

        Prison.instance.getPlatform().log("Loaded " + loadedMines.size() + " mines.");
    }

    public List<Mine> getLoadedMines() {
        return loadedMines;
    }

    public void addLoadedMine(Mine m) {
        loadedMines.add(m);
    }

    public Mine getMineByName(String name) {
        for (Mine m : loadedMines) if (m.getName().equalsIgnoreCase(name)) return m;
        return null;
    }

    public File getMineFile(String name) {
        return new File(getMinesDirectory(), name + ".mine.json");
    }

    public File getMinesDirectory() {
        return minesDirectory;
    }

}
