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

import io.github.sirfaizdat.prison.internal.commands.CommandArgument;
import io.github.sirfaizdat.prison.internal.commands.TransformError;
import io.github.sirfaizdat.prison.internal.entity.CommandSender;

public class DoubleArgumentHandler extends NumberArgumentHandler<Double> {
    public DoubleArgumentHandler() {
        setMessage("parse_error", "The parameter [%p] is not a number");
    }

    @Override
    public Double transform(CommandSender sender, CommandArgument argument, String value) throws TransformError {
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            throw new TransformError(argument.getMessage("parse_error"));
        }
    }
}
