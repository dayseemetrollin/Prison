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

import com.sk89q.intake.Command;
import com.sk89q.intake.Require;
import io.github.sirfaizdat.prison.command.annotations.Sender;
import io.github.sirfaizdat.prison.platform.interfaces.CommandSender;

/**
 * @author SirFaizdat
 */
public class SpigotCommands {

    @Command(aliases = "test", desc = "A test command, for the sake of testing.")
    @Require(value = "prison.test")
    public void testCommand(@Sender CommandSender sender) {
        sender.sendMessage("&9You are a baby blue!");
    }

}
