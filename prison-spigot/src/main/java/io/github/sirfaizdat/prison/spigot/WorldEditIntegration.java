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

package io.github.sirfaizdat.prison.spigot;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import io.github.sirfaizdat.prison.platform.Location;
import io.github.sirfaizdat.prison.platform.interfaces.Player;
import io.github.sirfaizdat.prison.platform.interfaces.SelectionIntegration;
import org.bukkit.Bukkit;

/**
 * @author SirFaizdat
 */
public class WorldEditIntegration implements SelectionIntegration {

    private WorldEditPlugin plugin;

    boolean load() {
        if(!Bukkit.getPluginManager().isPluginEnabled("WorldEdit")) return false;
        plugin = (WorldEditPlugin) Bukkit.getPluginManager().getPlugin("WorldEdit");
        return true;
    }

    @Override
    public Selection getSelection(Player player) {
        com.sk89q.worldedit.bukkit.selections.Selection weSelection = plugin.getSelection(Bukkit.getPlayer(player.getName()));
        if(weSelection == null) return null;
        Location min = SpigotUtils.getPrisonLocation(weSelection.getMinimumPoint());
        Location max = SpigotUtils.getPrisonLocation(weSelection.getMaximumPoint());
        return new Selection(min, max);
    }

    @Override
    public String getPluginName() {
        return "WorldEdit";
    }

}
