package net.shirojr.shrimpsicle.init;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.shirojr.shrimpsicle.Shrimpsicle;
import net.shirojr.shrimpsicle.block.DecoBlock;
import net.shirojr.shrimpsicle.block.DecoRotationalBlock;
import net.shirojr.shrimpsicle.block.custom.*;

import java.util.ArrayList;
import java.util.List;

public interface ShrimpsicleBlocks {
    List<Block> ALL_BLOCKS = new ArrayList<>();

    DecoBlock BANANA = registerBlock("banana", new DecoBlock(
                    createDecoSettings(AbstractBlock.Settings.create()),
                    Block.createCuboidShape(4, 0, 4, 12, 6, 12)),
            false);
    DecoBlock COCONUT = registerBlock("coconut", new DecoBlock(
                    createDecoSettings(AbstractBlock.Settings.create()),
                    Block.createCuboidShape(5, 0, 5, 11, 4, 11)),
            false);
    NutBlock NUT = registerBlock("nut", new NutBlock(
                    createDecoSettings(AbstractBlock.Settings.create()),
                    Block.createCuboidShape(0, 0, 0, 16, 12, 16)),
            true);
    DecoBlock CRAB = registerBlock("crab", new DecoBlock(
                    createWeakDecoSettings(AbstractBlock.Settings.create()),
                    Block.createCuboidShape(4, 0, 4, 12, 2, 12)),
            true);
    DecoBlock PINEAPPLE = registerBlock("pineapple", new DecoBlock(
                    createDecoSettings(AbstractBlock.Settings.create()),
                    Block.createCuboidShape(5, 0, 5, 11, 6, 11)),
            false);
    FlamingoBlock FLAMINGO = registerBlock("flamingo", new FlamingoBlock(
                    createDecoSettings(AbstractBlock.Settings.create()),
                    Block.createCuboidShape(6, 0, 6, 10, 11, 10)),
            true);
    DecoRotationalBlock GECKO = registerBlock("gecko", new DecoRotationalBlock(
                    createWeakDecoSettings(AbstractBlock.Settings.create().noCollision())),
            true);
    DecoBlock SHELL = registerBlock("shell", new DecoBlock(
                    createDecoSettings(AbstractBlock.Settings.create()),
                    Block.createCuboidShape(4, 0, 4, 12, 2, 12)),
            true);
    LushPlantBlock LUSH_PLANT = registerBlock("lush_plant", new LushPlantBlock(AbstractBlock.Settings.create()
                    .noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.GRASS).pistonBehavior(PistonBehavior.DESTROY).dropsNothing()),
            true);
    ShrubBlock SHRUB = registerBlock("shrub", new ShrubBlock(AbstractBlock.Settings.create()
                    .noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.GRASS).pistonBehavior(PistonBehavior.DESTROY).dropsNothing()),
            true);
    BeachUmbrellaBlock BEACH_UMBRELLA = registerBlock("beach_umbrella", new BeachUmbrellaBlock(AbstractBlock.Settings.create()
            .breakInstantly().pistonBehavior(PistonBehavior.DESTROY).jumpVelocityMultiplier(1.5f).dropsNothing()), false);



    private static <T extends Block> T registerBlock(String name, T block, boolean registerBlockItem) {
        T registeredBlock = Registry.register(Registries.BLOCK, Shrimpsicle.getId(name), block);
        ALL_BLOCKS.add(registeredBlock);
        if (registerBlockItem) {
            Item blockItem = Registry.register(Registries.ITEM, Shrimpsicle.getId(name), new BlockItem(registeredBlock, new Item.Settings()));
            ShrimpsicleItems.ALL_ITEMS.add(blockItem.getDefaultStack());
        }
        return registeredBlock;
    }


    private static AbstractBlock.Settings createDecoSettings(AbstractBlock.Settings settings) {
        return settings.breakInstantly().nonOpaque().pistonBehavior(PistonBehavior.DESTROY).dropsNothing();
    }

    private static AbstractBlock.Settings createWeakDecoSettings(AbstractBlock.Settings settings) {
        return createDecoSettings(settings).noCollision();
    }


    static void initialize() {
        // static initialisation
    }
}
