package ru.quizie.cfmobs;

import lombok.Getter;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import ru.quizie.cfmobs.listeners.MobDeathListener;

public final class CFMobs extends JavaPlugin {

    @Getter
    private static Economy economy;

    @Override
    public void onEnable() {
        if (!setupEconomy()) {
            getLogger().severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getLogger().severe("Error code: 2");
            return;
        }

        super.saveDefaultConfig();
        Config.load(super.getConfig());

        super.getServer().getPluginManager().registerEvents(new MobDeathListener(), this);
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        final RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        economy = rsp.getProvider();
        return true;
    }
}
