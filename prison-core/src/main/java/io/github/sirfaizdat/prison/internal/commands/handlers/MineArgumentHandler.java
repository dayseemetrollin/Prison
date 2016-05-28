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
import io.github.sirfaizdat.prison.mines.MineModule;
import io.github.sirfaizdat.prison.mines.ResetMethod;

/**
 * @author SirFaizdat
 */
public class MineArgumentHandler extends ArgumentHandler<Mine> {

    public MineArgumentHandler() {
        setMessage("module_not_enabled", "The mines module is not enabled");
        setMessage("mine_not_found", "The mine %1 could not be found");
    }

    @Override
    public Mine transform(CommandSender sender, CommandArgument argument, String value) throws TransformError {
        MineModule module = (MineModule) Prison.instance.getModuleManager().getModule("Mines");
        if(module == null || !module.isEnabled()) throw new TransformError(argument.getMessage("module_not_enabled"));

        Mine mine = module.getMine(value);
        if(mine == null) throw new TransformError(argument.getMessage("mine_not_found", value));

        return mine;
    }
}
