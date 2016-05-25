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
import io.github.sirfaizdat.prison.internal.commands.ArgumentHandler;
import io.github.sirfaizdat.prison.internal.commands.CommandArgument;
import io.github.sirfaizdat.prison.internal.commands.TransformError;
import io.github.sirfaizdat.prison.internal.entity.CommandSender;
import io.github.sirfaizdat.prison.mines.Mine;
import io.github.sirfaizdat.prison.mines.MinesModule;

/**
 * @author SirFaizdat
 */
public class MineArgumentHandler extends ArgumentHandler<Mine> {

    @Override
    public Mine transform(CommandSender sender, CommandArgument argument, String value) throws TransformError {
        if(Prison.instance.getModuleManager().getModule("Mines") == null)
            // TODO Make the /prison alerts command clickable?
            throw new TransformError("The mines module is not loaded. Type /prison alerts for details.");
        MinesModule mines = (MinesModule) Prison.instance.getModuleManager().getModule("Mines");
        Mine mine = mines.getMineByName(value);
        if (mine == null) throw new TransformError("The mine " + value + " does not exist.");
        return mine;
    }

}
