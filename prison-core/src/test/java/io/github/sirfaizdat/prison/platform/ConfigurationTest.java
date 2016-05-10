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

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.*;
import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * @author SirFaizdat
 */
public class ConfigurationTest {

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();
    private TestConfiguration conf;

    @Before
    public void setUp() throws Exception {
        conf = new TestConfiguration(folder.newFile());
    }

    @Test
    public void testDefaults() throws Exception {
        conf.setDefault("testInt", 24);
        conf.setDefault("testDouble", 3.14D);
        conf.setDefault("testFloat", 2.23F);
        conf.setDefault("testString", "Test");
        conf.setDefault("testList", Arrays.asList("Test", "test!"));
        conf.saveDefaults();
    }

    @Test
    public void testValues() throws Exception {
        // Defaults must be set, even though we already tested these
        conf.setDefault("testInt", 24);
        conf.setDefault("testDouble", 3.14D);
        conf.setDefault("testFloat", 2.23F);
        conf.setDefault("testString", "Test");
        conf.setDefault("testList", Arrays.asList("Test", "test!"));
        conf.saveDefaults();

        conf.load();
        assertEquals(24, conf.getInt("testInt"));
        assertEquals(3.14D, conf.getDouble("testDouble"), 0D);
        assertEquals(2.23F, conf.getFloat("testFloat"), 0F);
        assertEquals("Test", conf.getString("testString"));
        assertEquals(Arrays.asList("Test", "test!"), conf.getStringList("testList"));
    }

    private class TestConfiguration extends Configuration {

        private File file;

        public TestConfiguration(File file) {
            this.file = file;
        }

        @SuppressWarnings("unchecked")
        @Override
        public void load() {
            JSONParser parser = new JSONParser();
            try {
                Object obj = parser.parse(new FileReader(file));
                JSONObject json = (JSONObject) obj;
                json.putAll(entries);
            } catch (IOException | ParseException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void saveDefaults() {
            JSONObject obj = new JSONObject(defaults);
            try {
                FileWriter writer = new FileWriter(file);
                writer.write(obj.toJSONString());
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}