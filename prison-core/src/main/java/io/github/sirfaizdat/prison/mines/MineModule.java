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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.github.sirfaizdat.prison.Prison;
import io.github.sirfaizdat.prison.internal.modules.Module;
import io.github.sirfaizdat.prison.internal.world.World;
import io.github.sirfaizdat.prison.mines.methods.ResetMethodTest;
import io.github.sirfaizdat.prison.utils.adapters.AdapterResetMethod;
import io.github.sirfaizdat.prison.utils.adapters.AdapterWorld;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 * @author SirFaizdat
 */
public class MineModule extends Module {

    private List<Mine> mines;
    private List<ResetMethod> resetMethods;
    private File minesFolder;
    private Gson gson;

    public MineModule() {
        super("Mines");
    }

    @Override
    public void init() {
        if (!Prison.instance.getPlatform().getSelectionIntegration().hasIntegrated()) {
            fail("&cNo selection plugin found (WorldEdit)");
            return;
        }

        mines = new ArrayList<>();
        resetMethods = new ArrayList<>();
        minesFolder = new File(Prison.instance.getPlatform().getPluginFolder(), "mines");
        if (!minesFolder.exists()) minesFolder.mkdir();

        gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().registerTypeAdapter(World.class, new AdapterWorld()).registerTypeAdapter(ResetMethod.class, new AdapterResetMethod(this)).create();

        addResetMethod(new ResetMethodTest());
        loadAll();
    }

    private void loadAll() {
        File[] files = minesFolder.listFiles((dir, name) -> {
            return name.endsWith(".mine.json");
        });

        try {
            for (File file : files) {
                String json = new String(Files.readAllBytes(file.toPath()));
                Mine m = gson.fromJson(json, Mine.class);
                mines.add(m);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deinit() {
        mines.clear();
    }

    public Gson getGson() {
        return gson;
    }

    public List<Mine> getMines() {
        return mines;
    }

    public Mine getMine(String name) {
        for (Mine mine : mines) if (mine.getName().equalsIgnoreCase(name)) return mine;
        return null;
    }

    public void addMine(Mine mine) {
        if (mines.contains(mine)) return;
        mines.add(mine);
    }

    public File getMineFile(String name) {
        return new File(minesFolder, name + ".mine.json");
    }

    public List<ResetMethod> getResetMethods() {
        return resetMethods;
    }

    public ResetMethod getResetMethod(String name) {
        for (ResetMethod resetMethod : resetMethods) if (resetMethod.name().equalsIgnoreCase(name)) return resetMethod;
        return null;
    }

    public void addResetMethod(ResetMethod method) {
        resetMethods.add(method);
    }

}
