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

package io.github.sirfaizdat.prison.command.providers;

import com.google.common.collect.ImmutableList;
import com.sk89q.intake.argument.ArgumentException;
import com.sk89q.intake.argument.ArgumentParseException;
import com.sk89q.intake.argument.CommandArgs;
import com.sk89q.intake.parametric.Provider;
import com.sk89q.intake.parametric.ProvisionException;
import io.github.sirfaizdat.prison.Prison;
import io.github.sirfaizdat.prison.platform.interfaces.Player;

import javax.annotation.Nullable;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

/**
 * Provides {@link Player} objects.
 * @author SirFaizdat
 * @since 3.0
 */
public class PlayerProvider implements Provider<Player> {

    @Override
    public boolean isProvided() {
        return false;
    }

    @Nullable
    @Override
    public Player get(CommandArgs arguments, List<? extends Annotation> modifiers) throws ArgumentException, ProvisionException {
        String name = arguments.next().toLowerCase();
        Player player = Prison.instance.getPlatform().getPlayer(name);
        if(player == null) throw new ArgumentParseException("Couldn't find a user by the name '" + name + "'");
        return player;
    }

    @Override
    public List<String> getSuggestions(String prefix) {
        // List of all the users that start with the prefix specified
        List<String> ret = new ArrayList<>();
        for(Player player : Prison.instance.getPlatform().getOnlinePlayers())
            if(player.getName().toLowerCase().startsWith(prefix.toLowerCase())) ret.add(player.getName());
        return ret;
    }

}
