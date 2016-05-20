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

package io.github.sirfaizdat.prison.platform;

/**
 * @author SirFaizdat
 */
public class TextUtil {

    public static String parse(String msg, String... format) {
        return ChatColor.translateAlternateColorCodes('&', String.format(msg, format));
    }

    public static String[] packAndSplit(String original, String splitAt) {
        return original.replaceAll(" ", "").split(splitAt);
    }

}
