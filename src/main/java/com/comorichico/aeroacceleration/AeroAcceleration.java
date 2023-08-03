package com.comorichico.aeroacceleration;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

public final class AeroAcceleration extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        float pitch = player.getLocation().getPitch();
        Vector velocity = player.getVelocity();

        // エリトラで滑空中かどうかを判定
        boolean isGliding = player.isGliding();

        if (isGliding && pitch < -10) {
            // 上を向いている場合は加速
            player.setVelocity(velocity.multiply(1.05));

            // 最大10倍の速度に制限
            double currentSpeed = player.getVelocity().length();
            if (currentSpeed > 10.0) {
                player.setVelocity(velocity.normalize().multiply(10.0));
            }
        }
    }
}
