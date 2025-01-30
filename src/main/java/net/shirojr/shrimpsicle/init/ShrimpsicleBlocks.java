package net.shirojr.shrimpsicle.init;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.shirojr.shrimpsicle.Shrimpsicle;
import net.shirojr.shrimpsicle.block.custom.DecoBlock;
import net.shirojr.shrimpsicle.block.custom.DecoRotationalBlock;

public class ShrimpsicleBlocks {
    public static DecoBlock BANANA = registerBlock("banana", new DecoBlock(AbstractBlock.Settings.create()),false);
    public static DecoBlock COCONUT = registerBlock("coconut", new DecoBlock(AbstractBlock.Settings.create()),false);

    public static DecoRotationalBlock GECKO = registerBlock("gecko", new DecoRotationalBlock(AbstractBlock.Settings.create()), true);


    private static <T extends Block> T registerBlock(String name, T block, boolean registerBlockItem) {
        T registeredBlock = Registry.register(Registries.BLOCK, Shrimpsicle.getId(name), block);
        if (registerBlockItem) {
            Registry.register(Registries.ITEM, Shrimpsicle.getId(name), new BlockItem(registeredBlock, new Item.Settings()));
        }
        return registeredBlock;
    }

    public static void initialize() {
        // static initialisation
    }
}
