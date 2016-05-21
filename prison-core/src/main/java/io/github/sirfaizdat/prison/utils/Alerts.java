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

package io.github.sirfaizdat.prison.utils;

import io.github.sirfaizdat.prison.Prison;
import io.github.sirfaizdat.prison.internal.entity.Player;
import io.github.sirfaizdat.prison.internal.events.EventData;
import io.github.sirfaizdat.prison.internal.events.EventListener;
import io.github.sirfaizdat.prison.internal.events.EventType;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Alerts are messages sent to operators when they join.
 * They let the operator know that something is different with Prison, and that it requires their attention.
 *
 * @author SirFaizdat
 * @since 3.0
 */
public class Alerts {

    private List<String> alerts = new ArrayList<>();

    public Alerts() {
        Prison.instance.getPlatform().listen(EventType.PLAYER_JOIN, onPlayerJoin());
    }

    public void alert(String message, Object... format) {
        alerts.add(TextUtils.parse(message, format));
    }

    private EventListener onPlayerJoin() {
        return data -> {
            Optional<Player> playerOptional = (Optional<Player>) data.get("player");
            if(!playerOptional.isPresent()) return;
            Player player = playerOptional.get();
            if(player.isOp()) alerts.forEach(player::sendMessage);
        };
    }

}
