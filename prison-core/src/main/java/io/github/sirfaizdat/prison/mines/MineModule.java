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
import io.github.sirfaizdat.prison.mines.triggers.TimeTrigger;
import io.github.sirfaizdat.prison.mines.triggers.Trigger;
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
    private List<Trigger> triggers;
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
        triggers = new ArrayList<>();
        minesFolder = new File(Prison.instance.getPlatform().getPluginFolder(), "mines");
        if (!minesFolder.exists()) minesFolder.mkdir();

        gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().registerTypeAdapter(World.class, new AdapterWorld()).registerTypeAdapter(ResetMethod.class, new AdapterResetMethod(this)).create();

        addResetMethod(new ResetMethodTest());
        addTrigger(new TimeTrigger());
        loadAll();

        Prison.instance.getPlatform().getScheduler().scheduleAsyncRepeating(20L, 20L, () -> {
            for(Mine mine : mines) getTrigger(mine.getTriggerName()).trigger(mine);
        });
    }

    private void loadAll() {
        File[] files = minesFolder.listFiles((dir, name) -> {
            return name.endsWith(".mine.json");
        });

        try {
            for (File file : files) {
                String json = new String(Files.readAllBytes(file.toPath()));
                Mine m = gson.fromJson(json, Mine.class);
                if (m.getTriggerName() == null || getTrigger(m.getTriggerName()) == null) setAsDefaultTrigger(m);
                getTrigger(m.getTriggerName()).register(m);
                mines.add(m);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setAsDefaultTrigger(Mine mine) throws IOException {
        // The default trigger specified in the config doesn't exist!
        if (getTrigger(Prison.instance.getConfiguration().defaultTrigger) == null) {
            mine.setTriggerName("time"); // Set to the time trigger (we know this one exists)
            mine.setResetInterval(300); // 5 minutes
            mine.save(getMineFile(mine.getName()));

            Prison.instance.getAlerts().alert("&c&lAlert: &7The default trigger you set in your configuration &7&ldoes not exist&r&7!");
            Prison.instance.getPlatform().log("&c&lError: &7The default trigger you set in your configuration &7&ldoes not exist&r&7!");
            return;
        }
        mine.setTriggerName(Prison.instance.getConfiguration().defaultTrigger);
        mine.setResetInterval(Prison.instance.getConfiguration().defaultResetInterval);
        mine.save(getMineFile(mine.getName()));
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
        getTrigger(mine.getTriggerName()).register(mine);
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

    public List<Trigger> getTriggers() {
        return triggers;
    }

    public Trigger getTrigger(String name) {
        for (Trigger trigger : triggers) if (trigger.name().equalsIgnoreCase(name)) return trigger;
        return null;
    }

    public void addTrigger(Trigger trigger) {
        triggers.add(trigger);
    }

}
