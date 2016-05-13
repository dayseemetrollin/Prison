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

import com.sk89q.intake.Intake;
import com.sk89q.intake.dispatcher.Dispatcher;
import com.sk89q.intake.dispatcher.SimpleDispatcher;
import com.sk89q.intake.parametric.Injector;
import com.sk89q.intake.parametric.Module;
import com.sk89q.intake.parametric.ParametricBuilder;
import io.github.sirfaizdat.prison.platform.Platform;
import io.github.sirfaizdat.prison.platform.interfaces.CommandSender;

/**
 * Loads up all the commands and argument parsers (providers).
 * Prison makes use of sk89q's Intake library, allowing for simple command declarations
 * using methods. See the CommandTest for examples.
 *
 * @author SirFaizdat
 * @since 3.0
 */
public class CommandManager {

    // TODO subcommand support

    private Injector injector;
    private ParametricBuilder parametricBuilder;
    private Dispatcher dispatcher;

    public CommandManager() {
        injector = Intake.createInjector();
        injector.install(new DefaultModule());

        parametricBuilder = new ParametricBuilder(injector);
        dispatcher = new SimpleDispatcher();
        parametricBuilder.setAuthorizer((namespace, permission) -> namespace.containsKey("sender") && ((CommandSender) namespace.get("sender")).hasPermission(permission));
    }

    /**
     * Install a new module, which binds providers to their respective classes/annotations.
     *
     * @param module The {@link Module} implementation.
     */
    public void installModule(Module module) {
        injector.install(module);
    }

    /**
     * Register all commands within an object.
     *
     * @param obj The object containing the annotated command methods.
     */
    public void registerCommands(Object obj) {
        parametricBuilder.registerMethodsAsCommands(dispatcher, obj);
    }

    /**
     * Returns the {@link Dispatcher} which is responsible for running the commands.
     * Note that the {@link Platform} contains a {@link Platform#dispatchCommand(CommandSender, String, CharSequence[])} method
     * which handles the dirty logic for you, so use it to call commands.
     *
     * @return The {@link Dispatcher}, for calling commands.
     */
    public Dispatcher getDispatcher() {
        return dispatcher;
    }

}
