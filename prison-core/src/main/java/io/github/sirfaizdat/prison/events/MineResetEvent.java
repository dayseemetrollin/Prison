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

package io.github.sirfaizdat.prison.events;

import io.github.sirfaizdat.prison.mines.Mine;

/**
 * Triggered when a {@link io.github.sirfaizdat.prison.mines.Mine} is reset.
 *
 * @author SirFaizdat
 * @since 3.0
 */
public class MineResetEvent extends Event {

    private Mine mine;

    public MineResetEvent(Mine mine) {
        this.mine = mine;
    }

    public Mine getMine() {
        return mine;
    }
}
