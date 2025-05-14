package ru.quizie.cfmobs;

import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import ru.quizie.cfmobs.data.Mob;
import ru.quizie.cfmobs.utils.Colorizer;

import java.util.HashMap;
import java.util.Map;

public class Config {

    private static final Map<String, Mob> mobs = new HashMap<>();

    public static void load(FileConfiguration config) {
        parseMobs(config.getConfigurationSection("mobs"));

        actionBar = Colorizer.use(config.getString("actionbar"));
    }

    public static String actionBar;

    private static void parseMobs(ConfigurationSection section) {
        for (final String key : section.getKeys(true)) {
            final ConfigurationSection keySection = section.getConfigurationSection(key);
            if (keySection != null) {
                final String entityName = keySection.getName();

                try {
                    final String name = keySection.getString("name");
                    final double min = keySection.getDouble("min");
                    final double max = keySection.getDouble("max");

                    mobs.put(entityName, new Mob(name, min, max));
                } catch (Exception e) {
                    Bukkit.getLogger().severe("An error occurred when adding a mob. Check the configuration! Type of mob: " + entityName);
                    Bukkit.getLogger().severe("Error code: 1");
                    throw new RuntimeException(e);
                }
            }
        }

        Bukkit.getLogger().info("Loaded " + mobs.size() + " mobs.");
    }

    public static Mob getMob(String key) {
        return mobs.get(key);
    }

    public static boolean isValidMob(String key) {
        return mobs.containsKey(key);
    }
}
