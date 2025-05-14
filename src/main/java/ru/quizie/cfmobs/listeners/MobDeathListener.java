package ru.quizie.cfmobs.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import ru.quizie.cfmobs.CFMobs;
import ru.quizie.cfmobs.Config;
import ru.quizie.cfmobs.data.Mob;
import ru.quizie.cfmobs.utils.ConvertNumber;

import java.util.concurrent.ThreadLocalRandom;

public class MobDeathListener implements Listener {

    @EventHandler
    private void on(EntityDeathEvent event) {
        if (!Config.isValidMob(event.getEntity().getType().toString())) return;

        final Mob mob = Config.getMob(event.getEntity().getType().toString());
        final double money = ThreadLocalRandom.current().nextDouble(mob.getMin(), mob.getMax());

        final Player killer = event.getEntity().getKiller();
        if (killer == null) return;

        killer.sendActionBar(Config.actionBar.replace("%translate-mob%", mob.getName()).replace("%money%", ConvertNumber.convent(money)));

        CFMobs.getEconomy().depositPlayer(killer, money);
    }
}
