package de.epax.maxeytv.util;

import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.ChunkGenerator;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class VoidMapGeneratorUtil extends ChunkGenerator {

    @Override
    public @NotNull ChunkData generateChunkData(
            @NotNull World world,
            @NotNull Random random,
            int chunkX,
            int chunkZ,
            @NotNull BiomeGrid biome
    ) { ChunkData chunk = createChunkData(world);
        if (chunkX == 0 && chunkZ == 0) {
            chunk.setBlock(0, 64, 0, Material.GRASS_BLOCK);
        }
        return createChunkData(world);
    }

}
