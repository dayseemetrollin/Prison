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

package io.github.sirfaizdat.prison.command;

import com.sk89q.intake.argument.Namespace;
import com.sk89q.intake.util.auth.Authorizer;
import io.github.sirfaizdat.prison.platform.interfaces.CommandSender;

/**
 * Determines whether a user is allowed to perform a command.
 * @author SirFaizdat
 * @since 3.0
 */
public class CommandAuthorizer implements Authorizer {

    @Override
    public boolean testPermission(Namespace namespace, String permission) {
        CommandSender sender = (CommandSender) namespace.get("sender");
        if (sender == null) return false;
        return sender.hasPermission(permission);
    }

}
