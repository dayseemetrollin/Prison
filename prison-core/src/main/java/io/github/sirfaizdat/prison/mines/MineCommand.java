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

package io.github.sirfaizdat.prison.mines;

import io.github.sirfaizdat.prison.Prison;
import io.github.sirfaizdat.prison.internal.commands.Arg;
import io.github.sirfaizdat.prison.internal.commands.Command;
import io.github.sirfaizdat.prison.internal.entity.CommandSender;
import io.github.sirfaizdat.prison.internal.entity.Player;
import io.github.sirfaizdat.prison.internal.integration.Selection;
import io.github.sirfaizdat.prison.utils.TextUtils;

import java.io.IOException;
import java.util.Optional;

/**
 * @author SirFaizdat
 */
public class MineCommand {

    private MineModule mineModule;

    public MineCommand() {
        this.mineModule = (MineModule) Prison.instance.getModuleManager().getModule("Mines");
    }

    @Command(
            identifier = "mines create",
            description = "Create a new mine.",
            permissions = "mines.manage"
    )
    public void createMine(Player sender, @Arg(name = "name") String name, @Arg(name = "resetMethod", def = "default") ResetMethod resetMethod) {
        if (mineModule.getMine(name) != null) {
            sender.sendMessage("&c&lOops! &7A mine by that name already exists.");
            return;
        }

        Optional<Selection> selection = Prison.instance.getPlatform().getSelectionIntegration().getSelection(sender);
        if (!selection.isPresent()) {
            sender.sendMessage("&c&lOops! &7You must have a selection set. " + Prison.instance.getPlatform().getSelectionIntegration().getInstructions());
            return;
        }

        Mine mine = new Mine(name, sender.getLocation().getWorld(), selection.get().getMin(), selection.get().getMax(), resetMethod);

        // Save it
        try {
            mine.save(mineModule.getMineFile(name));
        } catch (IOException e) {
            sender.sendMessage("&c&lError: &7Failed to save the mine. Check the console for details.");
            e.printStackTrace();
            return;
        }

        mineModule.addMine(mine);
        sender.sendMessage("&7Successfully created a new mine called &3" + name + " &7in world &3" + sender.getLocation().getWorld().getName() + "&7.");
    }

    @Command(
            identifier = "mines reset",
            description = "Reset a mine.",
            onlyPlayers = false,
            permissions = "mines.manage"
    )
    public void resetMine(CommandSender sender, @Arg(name = "name") Mine mine, @Arg(name = "resetMethod", def = "default") ResetMethod resetMethod) {
        resetMethod.run(mine);
        sender.sendMessage("&7Reset the mine with method &3" + resetMethod.name() + " &7.");
    }

    @Command(
            identifier = "mines get",
            description = "Retrieve a property from a mine.",
            onlyPlayers = false,
            permissions = "mines.manage"
    )
    public void getValue(CommandSender sender, @Arg(name = "name") Mine mine, @Arg(name = "property") String property) {
        sender.sendMessage(TextUtils.parse("&7The value of &b%s&7 is &b%s&7.", property, mine.getExtraData().get(property)));
    }

    @Command(
            identifier = "mines set",
            description = "Set a property for a mine.",
            onlyPlayers = false,
            permissions = "mines.manage"
    )
    public void setValue(CommandSender sender, @Arg(name = "name") Mine mine, @Arg(name = "property") String property, @Arg(name = "value") String value) {
        mine.addExtraData(property, value);
        try {
            mine.save(mineModule.getMineFile(mine.getName()));
            sender.sendMessage(TextUtils.parse("&7Set &b%s &7to &b%s&7.", property, value));
        } catch (IOException e) {
            sender.sendMessage(TextUtils.parse("&c&lOops! &7The mine failed to save. &8Check the console for details."));
            e.printStackTrace();
        }
    }

}
