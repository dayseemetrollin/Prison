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

package io.github.sirfaizdat.prison.mines.resets;

import java.util.ArrayList;
import java.util.List;

/**
 * @author SirFaizdat
 */
public class ResetMethods {

    private List<ResetMethod> resetMethods = new ArrayList<>();
    private static ResetMethods instance;

    public static ResetMethods getInstance() {
        if(instance == null) instance = new ResetMethods();
        return instance;
    }

    public void addResetMethod(ResetMethod method) {
        resetMethods.add(method);
    }

    public ResetMethod getResetMethodByName(String name) {
        for(ResetMethod resetMethod : resetMethods) if (resetMethod.name().equalsIgnoreCase(name)) return resetMethod;
        return null;
    }

    public List<ResetMethod> getResetMethods() {
        return resetMethods;
    }

}
