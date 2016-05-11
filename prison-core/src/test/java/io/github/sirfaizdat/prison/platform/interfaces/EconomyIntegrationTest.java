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

package io.github.sirfaizdat.prison.platform.interfaces;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author SirFaizdat
 */
public class EconomyIntegrationTest {

    @Test
    public void testHasBalance() throws Exception {
        TestEconomyIntegration eco = new TestEconomyIntegration(24.03);

        assertTrue(eco.hasBalance(null, null, 24.03));
        assertFalse(eco.hasBalance(null, null, 25.03));
        assertFalse(eco.hasBalance(null, null, 24.031));
    }

    private class TestEconomyIntegration implements EconomyIntegration {

        private double balance;

        public TestEconomyIntegration(double balance) {
            this.balance = balance;
        }

        @Override
        public double getBalance(Player player, World world) {
            return balance;
        }

        @Override
        public double getBalance(Player player) {
            return balance;
        }

        @Override
        public void setBalance(Player player, double amount) {
            this.balance = amount;
        }

    }

}