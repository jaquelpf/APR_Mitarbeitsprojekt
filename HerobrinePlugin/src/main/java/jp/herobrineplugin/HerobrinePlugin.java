package jp.herobrineplugin;

import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LightningStrike;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.entity.Enderman;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.entity.Player;
import org.bukkit.entity.LightningStrike;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import javax.sql.rowset.Joinable;

public final class HerobrinePlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        //getServer().getPluginManager().registerEvents(new BlockPlaceListener(), this);
        Bukkit.getPluginManager().registerEvents(new BlockPlaceListener(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private class BlockPlaceListener implements Listener {

        @EventHandler
        public void onBlockPlace(BlockPlaceEvent event) {

            if(event.getBlockPlaced().getType() == Material.DRAGON_EGG) {
                World world = event.getBlockPlaced().getWorld();
                Location location = event.getBlockPlaced().getLocation();
                /*LightningStrike lightning = world.strikeLightning(location);*/

                Enderman enderman = world.spawn(location, Enderman.class);


                AttributeInstance health = enderman.getAttribute(Attribute.GENERIC_MAX_HEALTH);
                health.setBaseValue(2000.15);

                AttributeInstance armor = enderman.getAttribute(Attribute.GENERIC_ARMOR);
                armor.setBaseValue(95.30);


                AttributeInstance teleportation = enderman.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED);
                teleportation.setBaseValue(35.0);


                AttributeInstance attackDamage = enderman.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE);
                attackDamage.setBaseValue(90.0);

                enderman.setTarget(event.getPlayer());
                enderman.addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 1));
                enderman.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 1));
                enderman.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, Integer.MAX_VALUE, 1));

                        //if(enderman.getScoreboardTags().contains("NoDragonBreathDamage"))
                        //{
                            //if(event.getCause() == EntityDamageEvent.DamageCause.DRAGON_BREATH)
                            //{
                            //event.setCancelled(true);
                            //}
                        //}

                //if(enderman.getLastDamageCause() == EntityDamageEvent.DamageCause.DRAGON_BREATH)
                //{

                //}



                event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, Integer.MAX_VALUE, 1));

                world.playSound(location, Sound.ENTITY_LIGHTNING_BOLT_THUNDER, 1, 1);
                world.spawnEntity(location, EntityType.FIREWORK);
                world.spawnEntity(location, EntityType.DRAGON_FIREBALL);


                event.getPlayer().sendMessage("~~Herobrine wurde beschworen!~~");
                event.getPlayer().sendMessage("Ob es nun jedoch zu einem Kampf kommt entscheidet eure Location!");
                event.getPlayer().sendMessage("Ihr habt keinen Ort gewählt, an dem sich Herobrine nicht wegteleportieren kann? Spielt Minecraft nochmal durch und macht es diesesmal besser! ~ Oder sucht Herobrine :)");
            }
        }
    }


}


