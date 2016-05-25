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
import io.github.sirfaizdat.prison.internal.world.Block;
import io.github.sirfaizdat.prison.internal.world.Location;
import io.github.sirfaizdat.prison.mines.resets.ResetMethod;
import io.github.sirfaizdat.prison.mines.resets.ResetMethods;
import io.github.sirfaizdat.prison.utils.ChatColor;
import io.github.sirfaizdat.prison.utils.TextUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Optional;

/**
 * @author SirFaizdat
 */
public class MinesCommand {

    private MinesModule minesModule;

    public MinesCommand(MinesModule minesModule) {
        this.minesModule = minesModule;
    }

    @Command(
            identifier = "mines create",
            description = "Create a new mine.",
            permissions = "mines.create"
    )
    public void createMine(Player sender, @Arg(name = "name") String name) {
        if (minesModule.getMineByName(name) != null) {
            sender.sendMessage(ChatColor.RED + "A mine with that name already exists. Please choose a different name.");
            return;
        }

        Optional<Selection> selection = Prison.instance.getPlatform().getSelectionIntegration().getSelection(sender);
        if (!selection.isPresent()) {
            sender.sendMessage(ChatColor.RED + "You must have a WorldEdit region selected. This will be your mine region. To " +
                    "select it, type //wand and follow the on-screen instructions.");
            return;
        }
        Location minLoc = selection.get().getMin();
        Location maxLoc = selection.get().getMax();

        Mine mine = new Mine(name, minLoc, maxLoc, ResetMethods.getInstance().getResetMethodByName(Prison.instance.getConfiguration().defaultResetMethod), new MineComposition(new HashMap<>()));
        minesModule.addLoadedMine(mine);
        try {
            mine.save(minesModule.getMineFile(name));
        } catch (IOException e) {
            sender.sendMessage(ChatColor.RED + "An internal error occurred while trying to save the new mine: " + e.getMessage());
            e.printStackTrace();
            return;
        }

        sender.sendMessage(TextUtils.parse("&7Successfully created the mine &b%s&7.", name));
    }

    @Command(
            identifier = "mines reset",
            description = "Reset a mine.",
            permissions = "mines.reset",
            onlyPlayers = false
    )
    public void resetMine(CommandSender sender, @Arg(name = "mine") Mine mine, @Arg(name = "resetMethod", def = "default") ResetMethod resetMethod) {
        if (!resetMethod.run(mine))
            sender.sendMessage(ChatColor.RED + "Something went wrong while resetting the mine. Check the console for details.");
        else sender.sendMessage(ChatColor.GRAY + "The mine has been reset successfully.");
    }

    @Command(
            identifier = "mines addblock",
            description = "Add a block to the mine.",
            permissions = "mines.addblock",
            onlyPlayers = false
    )
    public void addBlockToMine(CommandSender sender, @Arg(name = "mine") Mine mine, @Arg(name = "block") String block, @Arg(name = "chance", verifiers = "min[1]|max[100]") double chance) {
        // Get the block
        Block b;
        try {
            b = new Block(block);
        } catch (IllegalArgumentException e) {
            // Our own custom error message is still set, through the IllegalArgumentException's message.
            sender.sendMessage(TextUtils.parse("&cAn error occurred while running the command. &b[%s]", e.getMessage()));
            return;
        }

        // Ensure it hasn't been added already
        if (mine.getComposition().contains(b)) {
            sender.sendMessage(TextUtils.parse("&cAn error occurred while running the command. &b[%s]", "The mine already contains that block"));
            return;
        }

        // Add it in
        mine.getComposition().add(b, chance / 100);
        // Validate the composition
        if (mine.getComposition().getPercentageFull() > 1.0D) {
            sender.sendMessage(TextUtils.parse("&cAn error occurred while running the command. &b[%s]", "The mine is already full - " + mine.getComposition().getPercentageFull()));
            mine.getComposition().remove(b); // Won't work, "get 'em out" - Donald J. Trump
            return;
        }

        // And save the mine.
        try {
            mine.save(minesModule.getMineFile(mine.getName()));
        } catch (IOException e) {
            sender.sendMessage(TextUtils.parse("&cAn error occurred while running the command. &b[%s]", "The mine could not be saved, check the console for details"));
            e.printStackTrace();
            return;
        }

        sender.sendMessage(ChatColor.GRAY + "Successfully added the block.");
    }
}