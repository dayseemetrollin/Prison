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

import java.io.IOException;
import java.util.HashMap;

import com.sk89q.intake.Command;
import com.sk89q.intake.Require;
import com.sk89q.intake.parametric.annotation.Optional;
import com.sk89q.intake.parametric.annotation.Range;
import io.github.sirfaizdat.prison.Prison;
import io.github.sirfaizdat.prison.command.modules.prison.Sender;
import io.github.sirfaizdat.prison.mines.composition.Block;
import io.github.sirfaizdat.prison.mines.composition.MineComposition;
import io.github.sirfaizdat.prison.mines.resets.ResetMethod;
import io.github.sirfaizdat.prison.mines.resets.ResetMethods;
import io.github.sirfaizdat.prison.platform.ChatColor;
import io.github.sirfaizdat.prison.platform.Location;
import io.github.sirfaizdat.prison.platform.TextUtil;
import io.github.sirfaizdat.prison.platform.interfaces.CommandSender;
import io.github.sirfaizdat.prison.platform.interfaces.Player;
import io.github.sirfaizdat.prison.platform.interfaces.SelectionIntegration;

/**
 * @author SirFaizdat
 */
public class MinesCommand {

    MinesModule minesModule;

    public MinesCommand(MinesModule minesModule) {
        this.minesModule = minesModule;
        Prison.instance.getCommandManager().registerCommands("mines", this);
    }

    @Command(aliases = "create", desc = "Create a new mine from your WorldEdit selection.")
    @Require("mines.create")
    public void createMine(@Sender Player sender, String name) {
        if (minesModule.getMineByName(name) != null) {
            sender.sendMessage(ChatColor.RED + "A mine with that name already exists. Please choose a different name.");
            return;
        }

        SelectionIntegration.Selection selection = Prison.instance.getPlatform().getSelectionIntegration().getSelection(sender);
        if (selection == null) {
            sender.sendMessage(ChatColor.RED + "You must have a WorldEdit region selected. This will be your mine region. To " +
                    "select it, type //wand and follow the on-screen instructions.");
            return;
        }
        Location minLoc = selection.getMin();
        Location maxLoc = selection.getMax();

        // TODO Configuration is broken
//        Mine mine = new Mine(name, minLoc, maxLoc, ResetMethods.getInstance().getResetMethodByName(Prison.instance.getPlatform().getConfiguration().getString("default-reset-method")), new MineComposition(new HashMap<>()));
        Mine mine = new Mine(name, minLoc, maxLoc, ResetMethods.getInstance().getResetMethodByName("total"), new MineComposition(new HashMap<>()));
        minesModule.addLoadedMine(mine);
        try {
            mine.save(minesModule.getMineFile(name));
        } catch (IOException e) {
            sender.sendMessage(ChatColor.RED + "An internal error occurred while trying to save the new mine: " + e.getMessage());
            e.printStackTrace();
            return;
        }

        sender.sendMessage(TextUtil.parse("&7Successfully created the mine &b%s&7.", name));
    }

    @Command(aliases = "reset", desc = "Reset a mine.")
    @Require("mines.reset")
    public void resetMine(CommandSender sender, Mine mine, @Optional("default") String resetMethod) {
        ResetMethod method = resetMethod.equalsIgnoreCase("default") ? mine.getResetMethod()
                : ResetMethods.getInstance().getResetMethodByName(resetMethod);
        if (method == null) {
            sender.sendMessage(ChatColor.RED + "A reset method with that name does not exist.");
            return;
        }

        if (!method.run(mine))
            sender.sendMessage(ChatColor.RED + "Something went wrong while resetting the mine. Check the console for details.");
        else sender.sendMessage(ChatColor.GRAY + "The mine has been reset successfully.");
    }

    @Command(aliases = "addblock", desc = "Add a block to the mine.")
    @Require("mines.composition.add")
    public void addBlockToMine(@Sender Player sender, String mineName, String block, @Range(min = 0.0D, max = 100.0) double chance) {
        // Get the mine
        Mine mine = minesModule.getMineByName(mineName);
        if (mine == null) {
            sender.sendMessage(ChatColor.RED + "A mine with that name does not exist.");
            return;
        }

        // Get the block
        Block b;
        try {
            b = new Block(block);
        } catch (IllegalArgumentException e) {
            // Our own custom error message is still set, through the IllegalArgumentException's message.
            sender.sendMessage(TextUtil.parse("&cAn error occurred while running the command. &b[%s]", e.getMessage()));
            return;
        }

        // Ensure it hasn't been added already
        if (mine.getComposition().contains(b)) {
            sender.sendMessage(TextUtil.parse("&cAn error occurred while running the command. &b[%s]", "The mine already contains that block"));
            return;
        }

        // Add it in
        mine.getComposition().add(b, chance / 100);
        // Validate the composition
        if (mine.getComposition().getPercentageFull() >= 1.0D) {
            sender.sendMessage(TextUtil.parse("&cAn error occurred while running the command. &b[%s]", "The mine is already full"));
            mine.getComposition().remove(b); // Won't work, "get 'em out" - Donald J. Trump
            return;
        }

        // And save the mine.
        try {
            mine.save(minesModule.getMineFile(mine.getName()));
        } catch (IOException e) {
            sender.sendMessage(TextUtil.parse("&cAn error occurred while running the command. &b[%s]", "The mine could not be saved, check the console for details"));
            e.printStackTrace();
            return;
        }

        sender.sendMessage(ChatColor.GRAY + "Successfully added the block.");
    }

}