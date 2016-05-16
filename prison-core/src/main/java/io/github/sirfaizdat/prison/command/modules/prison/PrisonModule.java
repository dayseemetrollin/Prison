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

package io.github.sirfaizdat.prison.command.modules.prison;

import com.sk89q.intake.parametric.Module;
import com.sk89q.intake.parametric.binder.Binder;
import io.github.sirfaizdat.prison.platform.interfaces.CommandSender;
import io.github.sirfaizdat.prison.platform.interfaces.Player;

/**
 * @author SirFaizdat
 */
public class PrisonModule implements Module {

    @Override
    public void configure(Binder binder) {
        binder.bind(CommandSender.class).toProvider(new CommandSenderProvider());
        binder.bind(Player.class).toProvider(new PlayerProvider());
        binder.bind(Player.class).annotatedWith(Sender.class).toProvider(new PlayerSenderProvider());
    }

}
