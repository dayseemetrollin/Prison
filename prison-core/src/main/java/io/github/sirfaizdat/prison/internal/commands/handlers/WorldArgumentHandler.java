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

package io.github.sirfaizdat.prison.internal.commands.handlers;

import io.github.sirfaizdat.prison.Prison;
import io.github.sirfaizdat.prison.internal.commands.*;
import io.github.sirfaizdat.prison.internal.entity.CommandSender;
import io.github.sirfaizdat.prison.internal.entity.Player;
import io.github.sirfaizdat.prison.internal.world.World;

import java.util.Optional;

public class WorldArgumentHandler extends ArgumentHandler<World> {
    public WorldArgumentHandler() {
        setMessage("world_not_found", "The world \"%1\" was not found");

        addVariable("sender", "The command executor", new ArgumentVariable<World>() {
            @Override
            public World var(CommandSender sender, CommandArgument argument, String varName) throws CommandError {
                if (!(sender instanceof Player))
                    throw new CommandError(argument.getMessage("cant_as_console"));

                return ((Player) sender).getLocation().getWorld();
            }
        });
    }

    @Override
    public World transform(CommandSender sender, CommandArgument argument, String value) throws TransformError {
        Optional<World> world = Prison.instance.getPlatform().getWorld(value);
        if (!world.isPresent())
            throw new TransformError(argument.getMessage("world_not_found", value));
        return world.get();
    }
}
