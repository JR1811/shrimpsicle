package net.shirojr.shrimpsicle.init;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.shirojr.shrimpsicle.Shrimpsicle;
import net.shirojr.shrimpsicle.block.DecoBlock;
import net.shirojr.shrimpsicle.block.DecoRotationalBlock;
import net.shirojr.shrimpsicle.block.custom.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

@SuppressWarnings("unused")
public interface ShrimpsicleBlocks {
    List<Block> ALL_BLOCKS = new ArrayList<>();

    DecoBlock BANANA = registerBlock("banana", (settings, key) ->
                    new DecoBlock(settings, Block.createCuboidShape(4, 0, 4, 12, 6, 12)),
            createDecoSettings(AbstractBlock.Settings.create()),
            false
    );

    DecoBlock COCONUT = registerBlock("coconut", (settings, key) ->
                    new DecoBlock(settings, Block.createCuboidShape(5, 0, 5, 11, 4, 11)),
            createDecoSettings(AbstractBlock.Settings.create()),
            false
    );

    NutsBlock NUTS = registerBlock("nuts", (settings, key) ->
                    new NutsBlock(settings, Block.createCuboidShape(0, 0, 0, 16, 12, 16)),
            createDecoSettings(AbstractBlock.Settings.create()),
            true
    );

    CrabBlock CRAB = registerBlock("crab", (settings, key) ->
                    new CrabBlock(settings, Block.createCuboidShape(4, 0, 4, 12, 2, 12)),
            createWeakDecoSettings(AbstractBlock.Settings.create()),
            true
    );

    DecoBlock PINEAPPLE = registerBlock("pineapple", (settings, key) ->
                    new DecoBlock(settings, Block.createCuboidShape(5, 0, 5, 11, 6, 11)),
            createDecoSettings(AbstractBlock.Settings.create()),
            false
    );

    AnanasBlock ANANAS = registerBlock("ananas", (settings, blockRegistryKey) ->
                    new AnanasBlock(settings, Block.createCuboidShape(5, 0, 5, 11, 6, 11)),
            createDecoSettings(AbstractBlock.Settings.create()),
            true
    );

    FlamingoBlock FLAMINGO = registerBlock("flamingo", (settings, blockRegistryKey) ->
                    new FlamingoBlock(settings, Block.createCuboidShape(6, 0, 6, 10, 11, 10)),
            createDecoSettings(AbstractBlock.Settings.create()),
            false
    );

    DecoRotationalBlock GECKO = registerBlock("gecko", (settings, blockRegistryKey) ->
                    new DecoRotationalBlock(settings),
            createWeakDecoSettings(AbstractBlock.Settings.create().noCollision()),
            true
    );

    ShellBlock SHELL = registerBlock("shell", (settings, blockRegistryKey) ->
                    new ShellBlock(settings, Block.createCuboidShape(4, 0, 4, 12, 2, 12)),
            createDecoSettings(AbstractBlock.Settings.create()),
            true
    );

    LushPlantBlock LUSH_PLANT = registerBlock("lush_plant", (settings, blockRegistryKey) ->
                    new LushPlantBlock(settings),
            AbstractBlock.Settings.create()
                    .noCollision()
                    .ticksRandomly()
                    .breakInstantly()
                    .sounds(BlockSoundGroup.GRASS)
                    .pistonBehavior(PistonBehavior.DESTROY)
                    .dropsNothing(),
            true
    );

    ShrubBlock SHRUB = registerBlock("shrub", (settings, blockRegistryKey) ->
                    new ShrubBlock(settings),
            AbstractBlock.Settings.create()
                    .noCollision()
                    .ticksRandomly()
                    .breakInstantly()
                    .sounds(BlockSoundGroup.GRASS)
                    .pistonBehavior(PistonBehavior.DESTROY)
                    .dropsNothing(),
            true
    );

    BeachUmbrellaBlock BEACH_UMBRELLA = registerBlock("beach_umbrella", (settings, blockRegistryKey) ->
                    new BeachUmbrellaBlock(settings),
            AbstractBlock.Settings.create()
                    .breakInstantly()
                    .pistonBehavior(PistonBehavior.DESTROY)
                    .jumpVelocityMultiplier(1.5f)
                    .dropsNothing(),
            false
    );

    Block MOTHER_OF_PEARL = registerBlock("mother_of_pearl", (settings, blockRegistryKey) ->
                    new Block(settings),
            AbstractBlock.Settings.create()
                    .mapColor(MapColor.OFF_WHITE)
                    .instrument(NoteBlockInstrument.HAT)
                    .strength(0.3F)
                    .sounds(BlockSoundGroup.GLASS)
                    .luminance(state -> 7)
                    .solidBlock(Blocks::never),
            true
    );

    private static <T extends Block> T registerBlock(String name, BiFunction<Block.Settings, RegistryKey<Block>, T> blockFactory,
                                                     Block.Settings baseSettings, boolean registerBlockItem) {
        Identifier identifier = Shrimpsicle.getId(name);
        RegistryKey<Block> key = RegistryKey.of(RegistryKeys.BLOCK, identifier);

        AbstractBlock.Settings settingsWithRegistryKey = baseSettings.registryKey(key);

        T registeredBlock = Registry.register(Registries.BLOCK, identifier, blockFactory.apply(settingsWithRegistryKey, key));
        ALL_BLOCKS.add(registeredBlock);
        if (registerBlockItem) {
            RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, identifier);
            Item blockItem = Registry.register(
                    Registries.ITEM, Shrimpsicle.getId(name),
                    new BlockItem(registeredBlock, new Item.Settings().useBlockPrefixedTranslationKey().registryKey(itemKey))
            );
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
