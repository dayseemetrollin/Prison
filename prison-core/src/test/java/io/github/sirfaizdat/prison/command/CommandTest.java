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

import com.sk89q.intake.Command;
import com.sk89q.intake.Require;
import com.sk89q.intake.parametric.annotation.Text;
import io.github.sirfaizdat.prison.Prison;
import io.github.sirfaizdat.prison.command.modules.prison.Sender;
import io.github.sirfaizdat.prison.platform.Configuration;
import io.github.sirfaizdat.prison.platform.Location;
import io.github.sirfaizdat.prison.platform.Platform;
import io.github.sirfaizdat.prison.platform.interfaces.*;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * This dirty test is to verify that each facet of sk89q's Intake command library is being implemented properly.
 * It is a dirty test because it doesn't assert anything and almost always requires a user to monitor its output.
 * Therefore, this is not a real unit test and shall be shamed to the depths of code hell once it is reworked.
 *
 * @author SirFaizdat
 * @since 3.0
 */
public class CommandTest {

    TestPlatform testPlatform;

    @Before
    public void setUp() throws Exception {
        testPlatform = new TestPlatform();
        new Prison(testPlatform);
        Prison.instance.getCommandManager().registerCommands(new TestCommands());
    }

    @Test
    public void testCommand() throws Exception {
        testPlatform.dispatchCommand(new TestCommandSender(), "test", new String[]{});
    }

    @Test
    public void testPlayerSender() throws Exception {
        testPlatform.dispatchCommand(new TestPlayer(), "testPlayer", new String[]{});
    }

    @Test
    public void testArguments() throws Exception {
        testPlatform.dispatchCommand(new TestPlayer(), "testArgs", new String[]{new TestPlayer().getName(), "Hello, my friend."});
    }

    @Test
    public void testNoPermission() throws Exception {
        testPlatform.dispatchCommand(new TestCommandSender(true), "testPerms", new String[]{});
    }

    // The commands
    // Everything has to be public, because that's how Intake wants it.

    public class TestCommands {

        @Command(aliases = "test", desc = "Say hi to the sender")
        public void testCommand(CommandSender sender) {
            sender.sendMessage("Hi!");
        }

        @Command(aliases = "testPlayer", desc = "Test the player sender")
        public void testPlayerCommand(@Sender  Player sender) {
            sender.sendMessage("Player's hi!");
        }

        @Command(aliases = "testArgs", desc = "Say something to another player.")
        public void testArgsCommand(@Sender Player sender, Player who, @Text String message) {
            who.sendMessage(String.format("%s -> %s: %s", sender.getName(), "you", message));
            sender.sendMessage(String.format("%s -> %s: %s", "you", sender.getName(), message));
        }

        @Command(aliases = "testPerms", desc = "Deny access because we're meanies.")
        @Require("permission.test")
        public void testPermsCommand(CommandSender sender) {
        }

    }

    // Mocked implementations

    private class TestPlatform implements Platform {

        @Override
        public Configuration getConfiguration() {
            return null;
        }

        @Override
        public World getWorld(String name) {
            return null;
        }

        @Override
        public Player getPlayer(String name) {
            return new TestPlayer();
        }

        @Override
        public List<Player> getOnlinePlayers() {
            return null;
        }

        @Override
        public Player getPlayer(UUID uid) {
            return null;
        }

        @Override
        public String getVersion() {
            return null;
        }

        @Override
        public File getDataFolder() {
            return null; // Might need to make an actual folder
        }

        @Override
        public EconomyIntegration getEconomyIntegration() {
            return null;
        }

        @Override
        public PermissionIntegration getPermissionIntegration() {
            return null;
        }

        @Override
        public SelectionIntegration getSelectionIntegration() {
            return null;
        }

        @Override
        public void registerCommands() {

        }

        @Override
        public void print(String message) {

        }

    }

    private class TestCommandSender implements CommandSender {

        boolean hasPermission = false;

        public TestCommandSender(boolean hasPermission) {
            this.hasPermission = hasPermission;
        }

        public TestCommandSender() {
        }

        @Override
        public String getName() {
            return "Test" + new Random().nextInt(10);
        }

        @Override
        public boolean hasPermission(String permission) {
            return hasPermission;
        }

        @Override
        public void sendMessage(String message) {
            System.out.println(message);
        }

    }

    private class TestPlayer extends TestCommandSender implements Player {

        @Override
        public UUID getUniqueID() {
            return UUID.randomUUID();
        }

        @Override
        public String getDisplayName() {
            return getName();
        }

        @Override
        public void setDisplayName(String name) {
        }

        @Override
        public Location getLocation() {
            return null;
        }

        @Override
        public void setLocation(Location loc) {
        }

    }

}