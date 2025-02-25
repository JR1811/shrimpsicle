package net.shirojr.shrimpsicle.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryWrapper;
import net.shirojr.shrimpsicle.Shrimpsicle;

import java.util.concurrent.CompletableFuture;

public class ShrimpsicleBlockLootTableProvider extends FabricBlockLootTableProvider {
    public ShrimpsicleBlockLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        Registries.BLOCK.stream().filter(block -> Registries.BLOCK.getId(block).getNamespace().equals(Shrimpsicle.MOD_ID))
                .forEach(this::addDrop);
    }
}
