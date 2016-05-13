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

import io.github.sirfaizdat.prison.platform.Location;
import io.github.sirfaizdat.prison.platform.interfaces.Player;
import org.bukkit.event.player.PlayerTeleportEvent;

import java.util.UUID;

/**
 * @author SirFaizdat
 */
public class SpigotPlayer extends SpigotCommandSender implements Player {

    org.bukkit.entity.Player bPlayer;

    public SpigotPlayer(org.bukkit.entity.Player bPlayer) {
        super(bPlayer);
        this.bPlayer = bPlayer;
    }

    @Override
    public UUID getUniqueID() {
        return bPlayer.getUniqueId();
    }

    @Override
    public String getDisplayName() {
        return bPlayer.getDisplayName();
    }

    @Override
    public void setDisplayName(String name) {
        bPlayer.setDisplayName(name);
    }

    @Override
    public Location getLocation() {
        return SpigotUtils.getPrisonLocation(bPlayer.getLocation());
    }

    @Override
    public void setLocation(Location loc) {
        bPlayer.teleport(SpigotUtils.getBukkitLocation(loc), PlayerTeleportEvent.TeleportCause.PLUGIN);
    }

}
