package net.shirojr.shrimpsicle;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;
import net.shirojr.shrimpsicle.init.ShrimpsicleBlocks;

public class ShrimpsicleClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(ShrimpsicleBlocks.GECKO, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ShrimpsicleBlocks.LUSH_PLANT, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ShrimpsicleBlocks.SHRUB, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ShrimpsicleBlocks.PINEAPPLE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ShrimpsicleBlocks.BEACH_UMBRELLA, RenderLayer.getCutout());

    }
}
