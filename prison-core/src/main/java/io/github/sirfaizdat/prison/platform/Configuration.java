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

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * The configuration contains the keys and their respective values from a user-defined list, usually a file.
 * Each platform has a different way of storing and retrieving configuration values, but each of them should
 * have the same keys.
 *
 * @author SirFaizdat
 * @since 3.0
 */
@SuppressWarnings("unused")
public abstract class Configuration {

    protected Map<String, Object> entries = new HashMap<>();
    protected Map<String, Object> defaults = new HashMap<>();

    /**
     * Load all keys from the user-defined configuration.
     */
    public abstract void load();

    /**
     * Store the default keys in a new storage location (usually a file), if it does not exist already.
     */
    public abstract void saveDefaults();

    /**
     * Add a default to the configuration. This will not make any changes until {@link #saveDefaults()} is called.
     *
     * @param key The key, by which you will later retrieve the user-set value.
     * @param value The value to set this to by default.
     * @see #saveDefaults()
     */
    public void setDefault(String key, Object value) {
        defaults.put(key, value);
    }

    // Getters

    public Object get(String key, Object def) {
        return !entries.containsKey(key) ? def : entries.get(key);
    }

    public Object get(String key) {
        return entries.get(key);
    }

    public Object getDefault(String key) {
        return defaults.get(key);
    }

    // Primitives
    public String getString(String path) {
        Object def = getDefault(path);
        return getString(path, def != null ? def.toString() : null);
    }

    public String getString(String path, String def) {
        Object val = get(path, def);
        return (val != null) ? val.toString() : def;
    }

    public boolean isString(String path) {
        Object val = get(path);
        return val instanceof String;
    }

    public int getInt(String path) {
        Object def = getDefault(path);
        return getInt(path, (def instanceof Number) ? toInt(def) : 0);
    }

    public int getInt(String path, int def) {
        Object val = get(path, def);
        return (val instanceof Number) ? toInt(val) : def;
    }

    public boolean isInt(String path) {
        Object val = get(path);
        return val instanceof Integer;
    }

    public boolean getBoolean(String path) {
        Object def = getDefault(path);
        return getBoolean(path, (def instanceof Boolean) ? (Boolean) def : false);
    }

    public boolean getBoolean(String path, boolean def) {
        Object val = get(path, def);
        return (val instanceof Boolean) ? (Boolean) val : def;
    }

    public boolean isBoolean(String path) {
        Object val = get(path);
        return val instanceof Boolean;
    }

    public double getDouble(String path) {
        Object def = getDefault(path);
        return getDouble(path, (def instanceof Number) ? toDouble(def) : 0);
    }

    public double getDouble(String path, double def) {
        Object val = get(path, def);
        return (val instanceof Number) ? toDouble(val) : def;
    }

    public boolean isDouble(String path) {
        Object val = get(path);
        return val instanceof Double;
    }

    public float getFloat(String path) {
        Object def = getDefault(path);
        return getFloat(path, (def instanceof Number) ? toFloat(def) : 0);
    }

    public float getFloat(String path, float def) {
        Object val = get(path, def);
        return (val instanceof Number) ? toFloat(val) : def;
    }

    public boolean isFloat(String path) {
        Object val = get(path);
        return val instanceof Float;
    }

    public long getLong(String path) {
        Object def = getDefault(path);
        return getLong(path, (def instanceof Number) ? toLong(def) : 0);
    }

    public long getLong(String path, long def) {
        Object val = get(path, def);
        return (val instanceof Number) ? toLong(val) : def;
    }

    public boolean isLong(String path) {
        Object val = get(path);
        return val instanceof Long;
    }

    // Java
    public List<?> getList(String path) {
        Object def = getDefault(path);
        return getList(path, (def instanceof List) ? (List<?>) def : null);
    }

    public List<?> getList(String path, List<?> def) {
        Object val = get(path, def);
        return (List<?>) ((val instanceof List) ? val : def);
    }

    public boolean isList(String path) {
        Object val = get(path);
        return val instanceof List;
    }

    public List<String> getStringList(String path) {
        List<?> list = getList(path);

        if (list == null) {
            return new ArrayList<>(0);
        }

        return list.stream().filter(object -> (object instanceof String) || (isPrimitiveWrapper(object))).map((Function<Object, String>) String::valueOf).collect(Collectors.toList());
    }

    public List<Integer> getIntegerList(String path) {
        List<?> list = getList(path);

        if (list == null) {
            return new ArrayList<>(0);
        }

        List<Integer> result = new ArrayList<>();

        for (Object object : list) {
            if (object instanceof Integer) {
                result.add((Integer) object);
            } else if (object instanceof String) {
                try {
                    result.add(Integer.valueOf((String) object));
                } catch (Exception ignored) {
                }
            } else if (object instanceof Character) {
                result.add((int) (Character) object);
            } else if (object instanceof Number) {
                result.add(((Number) object).intValue());
            }
        }

        return result;
    }

    public List<Boolean> getBooleanList(String path) {
        List<?> list = getList(path);

        if (list == null) {
            return new ArrayList<>(0);
        }

        List<Boolean> result = new ArrayList<>();

        for (Object object : list) {
            if (object instanceof Boolean) {
                result.add((Boolean) object);
            } else if (object instanceof String) {
                if (Boolean.TRUE.toString().equals(object)) {
                    result.add(true);
                } else if (Boolean.FALSE.toString().equals(object)) {
                    result.add(false);
                }
            }
        }

        return result;
    }

    public List<Double> getDoubleList(String path) {
        List<?> list = getList(path);

        if (list == null) {
            return new ArrayList<>(0);
        }

        List<Double> result = new ArrayList<>();

        for (Object object : list) {
            if (object instanceof Double) {
                result.add((Double) object);
            } else if (object instanceof String) {
                try {
                    result.add(Double.valueOf((String) object));
                } catch (Exception ignored) {
                }
            } else if (object instanceof Character) {
                result.add((double) (Character) object);
            } else if (object instanceof Number) {
                result.add(((Number) object).doubleValue());
            }
        }

        return result;
    }

    public List<Float> getFloatList(String path) {
        List<?> list = getList(path);

        if (list == null) {
            return new ArrayList<>(0);
        }

        List<Float> result = new ArrayList<>();

        for (Object object : list) {
            if (object instanceof Float) {
                result.add((Float) object);
            } else if (object instanceof String) {
                try {
                    result.add(Float.valueOf((String) object));
                } catch (Exception ignored) {
                }
            } else if (object instanceof Character) {
                result.add((float) (Character) object);
            } else if (object instanceof Number) {
                result.add(((Number) object).floatValue());
            }
        }

        return result;
    }

    public List<Long> getLongList(String path) {
        List<?> list = getList(path);

        if (list == null) {
            return new ArrayList<>(0);
        }

        List<Long> result = new ArrayList<>();

        for (Object object : list) {
            if (object instanceof Long) {
                result.add((Long) object);
            } else if (object instanceof String) {
                try {
                    result.add(Long.valueOf((String) object));
                } catch (Exception ignored) {
                }
            } else if (object instanceof Character) {
                result.add((long) (Character) object);
            } else if (object instanceof Number) {
                result.add(((Number) object).longValue());
            }
        }

        return result;
    }

    public List<Byte> getByteList(String path) {
        List<?> list = getList(path);

        if (list == null) {
            return new ArrayList<>(0);
        }

        List<Byte> result = new ArrayList<>();

        for (Object object : list) {
            if (object instanceof Byte) {
                result.add((Byte) object);
            } else if (object instanceof String) {
                try {
                    result.add(Byte.valueOf((String) object));
                } catch (Exception ignored) {
                }
            } else if (object instanceof Character) {
                result.add((byte) ((Character) object).charValue());
            } else if (object instanceof Number) {
                result.add(((Number) object).byteValue());
            }
        }

        return result;
    }

    public List<Character> getCharacterList(String path) {
        List<?> list = getList(path);

        if (list == null) {
            return new ArrayList<>(0);
        }

        List<Character> result = new ArrayList<>();

        for (Object object : list) {
            if (object instanceof Character) {
                result.add((Character) object);
            } else if (object instanceof String) {
                String str = (String) object;

                if (str.length() == 1) {
                    result.add(str.charAt(0));
                }
            } else if (object instanceof Number) {
                result.add((char) ((Number) object).intValue());
            }
        }

        return result;
    }

    public List<Short> getShortList(String path) {
        List<?> list = getList(path);

        if (list == null) {
            return new ArrayList<>(0);
        }

        List<Short> result = new ArrayList<>();

        for (Object object : list) {
            if (object instanceof Short) {
                result.add((Short) object);
            } else if (object instanceof String) {
                try {
                    result.add(Short.valueOf((String) object));
                } catch (Exception ignored) {
                }
            } else if (object instanceof Character) {
                result.add((short) ((Character) object).charValue());
            } else if (object instanceof Number) {
                result.add(((Number) object).shortValue());
            }
        }

        return result;
    }

    public List<Map<?, ?>> getMapList(String path) {
        List<?> list = getList(path);
        List<Map<?, ?>> result = new ArrayList<>();

        if (list == null) {
            return result;
        }

        result.addAll(list.stream().filter(object -> object instanceof Map).map(object -> (Map<?, ?>) object).collect(Collectors.toList()));

        return result;
    }

    protected int toInt(Object s) {
        return Integer.parseInt(s.toString());
    }

    protected double toDouble(Object s) {
        return Double.parseDouble(s.toString());
    }

    protected long toLong(Object s) {
        return Long.parseLong(s.toString());
    }

    protected float toFloat(Object s) {
        return Float.parseFloat(s.toString());
    }

    protected boolean toBoolean(Object s) {
        return Boolean.parseBoolean(s.toString());
    }

    protected boolean isPrimitiveWrapper(Object input) {
        return input instanceof Integer || input instanceof Boolean ||
                input instanceof Character || input instanceof Byte ||
                input instanceof Short || input instanceof Double ||
                input instanceof Long || input instanceof Float;
    }

    /**
     * Returns a list of all entries in the form of a {@link Map}.
     *
     * @return The map of entries.
     */
    public Map<String, Object> getEntries() {
        return entries;
    }
}
