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
import com.sk89q.intake.Intake;
import com.sk89q.intake.dispatcher.Dispatcher;
import com.sk89q.intake.fluent.CommandGraph;
import com.sk89q.intake.parametric.Injector;
import com.sk89q.intake.parametric.Module;
import com.sk89q.intake.parametric.ParametricBuilder;
import com.sk89q.intake.parametric.provider.PrimitivesModule;
import io.github.sirfaizdat.prison.command.modules.prison.PrisonModule;

/**
 * Sets up sk89q's Intake library.
 *
 * @author SirFaizdat
 * @since 3.0
 */
public class CommandManager {

    private CommandGraph commandGraph;

    public CommandManager() {
        Injector injector = Intake.createInjector();
        injector.install(new PrimitivesModule());
        injector.install(new PrisonModule());

        ParametricBuilder builder = new ParametricBuilder(injector);
        builder.setAuthorizer(new CommandAuthorizer());

        commandGraph = new CommandGraph().builder(builder);
    }

    /**
     * Registers all {@link Command} annotated methods in an object as commands
     * under a root command.
     *
     * @param root The root command. For example, if you want to create commands under the command "/mines", the root would be "mines".
     * @param commands The object containing the commands to register.
     */
    public void registerCommands(String root, Object commands) {
        commandGraph.commands().group(root).registerMethods(commands);
    }

    /**
     * Registers all {@link Command} annotated methods in an object as commands under a
     * sub root command under a root command.
     *
     * @param root The root command. For example, if you want to create commands under the command "/mines settings", the root would be "mines".
     * @param subroot The sub root command. For example, if you want to create commands under the command "/mines settings", the sub root would be "settings".
     * @param commands The object containing the commands to register.
     */
    public void registerCommands(String root, String subroot, Object commands) {
        commandGraph.commands().group(root).group(subroot).registerMethods(commands);
    }

    /**
     * Registers all {@link Command} annotated methods in an object as commands at the top level.
     * @param commands The object containing the commands to register.
     */
    public void registerCommands(Object commands) {
        commandGraph.commands().registerMethods(commands);
    }

    public void registerModule(Module m) {
        commandGraph.getBuilder().getInjector().install(m);
    }

    public Dispatcher getDispatcher() {
        return commandGraph.getDispatcher();
    }

}
