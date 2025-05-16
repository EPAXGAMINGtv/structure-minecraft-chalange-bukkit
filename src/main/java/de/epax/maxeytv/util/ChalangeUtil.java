package de.epax.maxeytv.util;

import de.epax.maxeytv.Maxeytv;
import de.epax.maxeytv.commands.GenerateVoidMap;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.structure.Structure;
import org.joml.Vector3d;

import java.util.Random;
import java.util.Vector;

public class ChalangeUtil {
    public static boolean  started = false;
    public static Maxeytv plugin;
    public static void startChallange(Player p) {
        started = true;
        p.sendMessage(ChatColor.GREEN + "Generating VoidMap");
        p.sendMessage(ChatColor.GREEN + "Initzilising Timer!");
        makestartTimer(p);

    }
    public static void endChallange() {
        started = false;
    }
    public static void makestartTimer(Player p) {
        new BukkitRunnable() {
            int seconds = 10;
            boolean worldCreated = false;  // Flag, ob Welt schon erstellt wurde
            World world = null;

            @Override
            public void run() {
                if (seconds > 0) {
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        player.sendTitle("§e" + seconds, "§7Spiel startet gleich...", 0, 20, 0);
                        player.sendMessage("§7» §e" + seconds + " Sekunden bis zum Start!");
                    }
                    seconds--;
                } else {
                    if (!worldCreated) {
                        WorldCreator wc = new WorldCreator("voidworld");
                        wc.generator(new VoidMapGeneratorUtil());
                        world = Bukkit.createWorld(wc);
                        worldCreated = true;

                        for (Player player : Bukkit.getOnlinePlayers()) {
                            player.sendTitle("§aGO!", "", 0, 40, 10);
                            player.sendMessage("§a» Das Spiel beginnt jetzt!");
                            player.teleport(new Location(world, 0.5, 65, 0.5));
                        }

                        int structureX = 0;
                        int structureY = 100;
                        int structureZ = 0;


                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                placeStructure(p, "village_plains", structureX, structureY, structureZ);
                            }
                        }.runTaskLater(plugin, 70L);

                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                Location loc = new Location(world, structureX + 8.5, structureY + 1, structureZ + 8.5); // Mitte mit Offset
                                teleportToNewStructure(p,world);
                            }
                        }.runTaskLater(plugin, 140L);

                        initilizeTimer(180);
                        cancel();
                    }
                }
            }
        }.runTaskTimer(plugin, 0L, 20L);
    }


    public static void placeStructure(CommandSender sender, String structureName, int x, int y, int z) {
        String command = String.format("place structure minecraft:%s %d %d %d", structureName, x, y, z);
        Bukkit.dispatchCommand(sender, command);
    }
    public static void initilizeTimer(int cooldown) {
        if(!started) {
            return;
        }
        new BukkitRunnable() {
            int timeLeft = cooldown;

            @Override
            public void run() {
                if (timeLeft > 0) {
                    String msg = "<gray>⏳ Nächste Struktur wird in <yellow>" + timeLeft + " Sekunden bei dir gespawnt</yellow>...";
                    Bukkit.getOnlinePlayers().forEach(player ->
                            player.sendActionBar(MiniMessage.miniMessage().deserialize(msg))
                    );
                    timeLeft--;
                } else {
                    Bukkit.getOnlinePlayers().forEach(player -> {
                        player.sendActionBar(MiniMessage.miniMessage().deserialize("<green> Eine neue Struktur ist gespawnt!"));
                        Random random = new Random();
                        int structureID = random.nextInt(25);
                        String structureName = switch (structureID) {
                            case 0 -> "ancient_city";
                            case 1 -> "bastion_remnant";
                            case 2 -> "buried_treasure";
                            case 3 -> "desert_pyramid";
                            case 4 -> "end_city";
                            case 5 -> "fortress";
                            case 6 -> "igloo";
                            case 7 -> "jungle_pyramid";
                            case 8 -> "mansion";
                            case 9 -> "mineshaft";
                            case 10 -> "nether_fossil";
                            case 11 -> "ocean_ruin";
                            case 12 -> "pillager_outpost";
                            case 13 -> "ruined_portal";
                            case 14 -> "shipwreck";
                            case 15 -> "swamp_hut";
                            case 16 -> "stronghold";
                            case 17, 18 -> "temple";
                            case 19 -> "village_desert";
                            case 20 -> "village_plains";
                            case 21 -> "village_savanna";
                            case 22 -> "village_snowy";
                            case 23 -> "village_taiga";
                            case 24 -> "witch_hut";
                            default -> "village_plains";
                        };
                        clearWorld(player.getWorld());
                        teleportToNewStructure(player,player.getWorld());
                        Vector3d pos = new Vector3d(player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ());
                        placeStructure((CommandSender) player, structureName, (int) pos.x, (int) pos.y, (int) pos.z

                        );
                    });
                    initilizeTimer(cooldown);
                    cancel();
                }
            }
        }.runTaskTimer(plugin, 0L, 20L);
    }

    public static void generateRandomStructure(Player player) {
        player.teleport(plugin.getServer().getWorlds().get(0).getSpawnLocation());
    }

    public static void clearWorld(World world) {
        int minX = -128;
        int maxX = 128;
        int minZ = -128;
        int maxZ = 128;
        int minY = world.getMinHeight();
        int maxY = world.getMaxHeight();
        for (int x = minX; x <= maxX; x++) {
            for (int z = minZ; z <= maxZ; z++) {
                for (int y = minY; y <= maxY; y++) {
                    Block block = world.getBlockAt(x, y, z);
                    if (block.getType() != Material.AIR) {
                        block.setType(Material.AIR, false);
                    }
                }
            }
        }
    }

    public static void teleportToNewStructure(Player player, World world) {
        Location spawn = world.getSpawnLocation();
        int chunkRadius = 10;
        int minY = world.getMinHeight();
        int maxY = world.getMaxHeight();

        Location teleportLocation = null;

        outer:
        for (int chunkX = spawn.getChunk().getX() - chunkRadius; chunkX <= spawn.getChunk().getX() + chunkRadius; chunkX++) {
            for (int chunkZ = spawn.getChunk().getZ() - chunkRadius; chunkZ <= spawn.getChunk().getZ() + chunkRadius; chunkZ++) {
                Chunk chunk = world.getChunkAt(chunkX, chunkZ);
                if (!chunk.isLoaded()) chunk.load();
                for (int x = 0; x < 16; x++) {
                    for (int z = 0; z < 16; z++) {
                        int worldX = chunkX * 16 + x;
                        int worldZ = chunkZ * 16 + z;

                        for (int y = maxY; y >= minY; y--) {
                            Block block = world.getBlockAt(worldX, y, worldZ);
                            if (block.getType().isSolid() && block.getType() != Material.BEDROCK && block.getType() != Material.BARRIER) {
                                teleportLocation = new Location(world, worldX + 0.5, y + 1, worldZ + 0.5);
                                break outer;
                            }
                        }
                    }
                }
            }
        }

        if (teleportLocation != null) {
            player.teleport(teleportLocation);
            player.sendMessage("§a» Du wurdest zur Struktur teleportiert.");
        } else {
            player.sendMessage("§c» Keine Struktur gefunden im Umkreis von 10 Chunks.");
        }
    }



}
