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

import java.util.Optional;

public class PlayerArgumentHandler extends ArgumentHandler<Player> {
    public PlayerArgumentHandler() {
        setMessage("player_not_online", "The player %1 is not online");

        addVariable("sender", "The command executor", new ArgumentVariable<Player>() {
            @Override
            public Player var(CommandSender sender, CommandArgument argument, String varName) throws CommandError {
                if (!(sender instanceof Player))
                    throw new CommandError(argument.getMessage("cant_as_console"));

                return ((Player) sender);
            }
        });
    }

    @Override
    public Player transform(CommandSender sender, CommandArgument argument, String value) throws TransformError {
        Optional<Player> p = Prison.instance.getPlatform().getPlayer(value);
        if (!p.isPresent())
            throw new TransformError(argument.getMessage("player_not_online", value));
        return p.get();
    }
}
