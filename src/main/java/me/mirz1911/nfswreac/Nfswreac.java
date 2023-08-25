package me.mirz1911.nfswreac;

import java.util.concurrent.ThreadLocalRandom;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.plugin.java.JavaPlugin;


public final class Nfswreac extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        System.out.println("hehe boi");
        getServer().getPluginManager().registerEvents(this, this);
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }



    @EventHandler (priority = EventPriority.NORMAL)
    public void onPlayerDamage (EntityDamageEvent e){

        if (System.currentTimeMillis()>(4000 + lastTime)) {
            if (e.getEntity().getType() == EntityType.PLAYER) {
                Player p = (Player) e.getEntity();
                Sound[] hurts = {Sound.ENTITY_PIG_HURT, Sound.ENTITY_PIG_AMBIENT, Sound.ENTITY_ZOMBIFIED_PIGLIN_HURT};
                String[] swears = {"pantek", "asu", "kontol", "jancok", "titid", "ngentod", "babi", "anjing"};
                String swr = swears[ThreadLocalRandom.current().nextInt(swears.length)];
                Sound hrt = hurts[ThreadLocalRandom.current().nextInt((hurts.length))];
                float[] pitch = {10, 20, 30};
                float pth = pitch[ThreadLocalRandom.current().nextInt(pitch.length)];
                p.chat(swr);
                p.playSound(p.getLocation(), hrt, 110, pth);
            }
        }
        lastTime = System.currentTimeMillis();
    }

    @EventHandler
    public void onPlayerKill (PlayerDeathEvent d){
        String[] taunts = {"LMAO so trash", "gg ez", "ez dek", "cacat anjir", "die noob", "imagine dying lol", "ur so noob LMAO", "?"};
        String taunt = taunts[ThreadLocalRandom.current().nextInt(taunts.length)];
        if (d.getEntity() instanceof Player){
            Player killed = d.getPlayer();
            if (d.getEntity().getKiller() instanceof  Player){
                Player killer = d.getPlayer().getKiller();
                String killername = d.getPlayer().getKiller().getName();
                String[] comps = {"anjing lo " + killername, killername + " KONTOL", "jancok " + killername + " kek asu"};
                String cps = comps[ThreadLocalRandom.current().nextInt(comps.length)];
                killer.chat(taunt);
                killed.chat(cps);
                killer.playSound(killer.getLocation(), Sound.ENTITY_WITCH_CELEBRATE, 300, -40);
                killed.playSound(killed.getLocation(), Sound.ENTITY_PIG_DEATH, 200, 20);
            }
            else {
                killed.playSound(killed.getLocation(), Sound.ENTITY_PIG_DEATH, 200, 20);
            }


        }




    }
    private long  lastTime = System.currentTimeMillis();
    @EventHandler
    public void playerGrabitem (PlayerPickupItemEvent e) {

        if (System.currentTimeMillis()>(6000 + lastTime)){
            String in = e.getItem().getName();
            Player p = e.getPlayer();
            String[] ini = {"Aku punya " + in, "Lah dapet " + in, "Njir, " + in + " buat apa coba?"};
            String inir = ini[ThreadLocalRandom.current().nextInt(ini.length)];
            p.chat (inir);
            p.playSound(p.getLocation(), Sound.ENTITY_WITCH_CELEBRATE, 100, 40);
        }
        lastTime = System.currentTimeMillis();
    }

    @EventHandler
    public void onPlayerJoin (PlayerJoinEvent e){
        Bukkit.getScheduler().runTaskLater(this, new Runnable() {
            public void run() {
                Player joined = e.getPlayer();
                joined.chat("Lah");
                joined.playSound(joined.getLocation(), Sound.ITEM_GOAT_HORN_SOUND_0, 100, 50);
            }
        }, 5*20);

    }
}
