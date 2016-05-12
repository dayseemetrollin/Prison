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

import com.sk89q.intake.parametric.AbstractModule;
import io.github.sirfaizdat.prison.command.annotations.Sender;
import io.github.sirfaizdat.prison.command.annotations.Wildcard;
import io.github.sirfaizdat.prison.command.providers.*;
import io.github.sirfaizdat.prison.platform.interfaces.CommandSender;
import io.github.sirfaizdat.prison.platform.interfaces.Player;

/**
 * Binds all default providers.
 *
 * @author SirFaizdat
 * @since 3.0
 */
public class DefaultModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(Player.class).annotatedWith(Sender.class).toProvider(new PlayerSenderProvider());
        bind(CommandSender.class).annotatedWith(Sender.class).toProvider(new CommandSenderProvider());
        bind(Player.class).toProvider(new PlayerProvider());
        bind(String.class).toProvider(new StringProvider());
        bind(String.class).annotatedWith(Wildcard.class).toProvider(new WildcardProvider());
    }

}
