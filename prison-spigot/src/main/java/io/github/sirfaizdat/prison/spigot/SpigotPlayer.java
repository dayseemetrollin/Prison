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

import io.github.sirfaizdat.prison.internal.entity.Player;
import io.github.sirfaizdat.prison.internal.item.ItemStack;
import io.github.sirfaizdat.prison.internal.world.Location;
import org.bukkit.event.player.PlayerTeleportEvent;

import java.util.Optional;
import java.util.UUID;

/**
 * @author SirFaizdat
 */
public class SpigotPlayer extends SpigotCommandSender implements Player {

    private org.bukkit.entity.Player player;

    public SpigotPlayer(org.bukkit.entity.Player player) {
        super(player);
        this.player = player;
    }

    @Override
    public UUID getUniqueID() {
        return player.getUniqueId();
    }

    @Override
    public Location getLocation() {
        return SpigotPrison.wrapLocation(player.getLocation());
    }

    @Override
    public void teleport(Location location) {
        player.teleport(SpigotPrison.wrapLocation(location), PlayerTeleportEvent.TeleportCause.PLUGIN);
    }

    @Override
    public ItemStack getItemInMainHand() {
        return SpigotPrison.wrapItemStack(player.getInventory().getItemInMainHand());
    }

    @Override
    public Optional<ItemStack> getItemInOffHand() {
        return Optional.of(SpigotPrison.wrapItemStack(player.getInventory().getItemInOffHand()));
    }

}
