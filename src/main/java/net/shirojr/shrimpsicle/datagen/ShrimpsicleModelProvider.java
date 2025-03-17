package net.shirojr.shrimpsicle.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Block;
import net.minecraft.block.enums.BlockFace;
import net.minecraft.block.enums.BlockHalf;
import net.minecraft.data.client.*;
import net.minecraft.registry.Registries;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.Direction;
import net.shirojr.shrimpsicle.Shrimpsicle;
import net.shirojr.shrimpsicle.block.custom.BeachUmbrellaBlock;
import net.shirojr.shrimpsicle.init.ShrimpsicleBlocks;

@SuppressWarnings("SameParameterValue")
public class ShrimpsicleModelProvider extends FabricModelProvider {
    public ShrimpsicleModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator generator) {
        generator.blockStateCollector.accept(generateRotationalDecoBlock(ShrimpsicleBlocks.BANANA));
        generator.blockStateCollector.accept(generateRotationalDecoBlock(ShrimpsicleBlocks.COCONUT));
        generator.blockStateCollector.accept(generateRotationalDecoBlock(ShrimpsicleBlocks.NUTS));
        generator.blockStateCollector.accept(generateRotationalDecoBlock(ShrimpsicleBlocks.CRAB));
        generator.blockStateCollector.accept(generateRotationalDecoBlock(ShrimpsicleBlocks.FLAMINGO));
        generator.blockStateCollector.accept(generateMultiRotationalDecoBlock(ShrimpsicleBlocks.GECKO));
        generator.blockStateCollector.accept(generateRotationalDecoBlock(ShrimpsicleBlocks.PINEAPPLE));
        generator.blockStateCollector.accept(generateRotationalDecoBlock(ShrimpsicleBlocks.ANANAS));
        generator.blockStateCollector.accept(generateRotationalDecoBlock(ShrimpsicleBlocks.SHELL));
        generator.blockStateCollector.accept(generateBeachUmbrellaBlock(ShrimpsicleBlocks.BEACH_UMBRELLA));
        // generator.registerSimpleCubeAll(ShrimpsicleBlocks.MOTHER_OF_PEARL);

        generator.excludeFromSimpleItemModelGeneration(ShrimpsicleBlocks.BEACH_UMBRELLA);
        generator.excludeFromSimpleItemModelGeneration(ShrimpsicleBlocks.SHRUB);


        generator.blockStateCollector.accept(
                BlockStateModelGenerator.createSingletonBlockState(
                        ShrimpsicleBlocks.LUSH_PLANT,
                        Shrimpsicle.getId("block/lush_plant")
                )
        );
        generator.registerDoubleBlock(
                ShrimpsicleBlocks.SHRUB,
                Shrimpsicle.getId("block/shrub_upper"),
                Shrimpsicle.getId("block/shrub_lower")
        );
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {

    }

    private static BlockStateSupplier generateRotationalDecoBlock(Block block) {
        return VariantsBlockStateSupplier.create(block,
                BlockStateVariant.create().put(VariantSettings.MODEL, ModelIds.getBlockModelId(block))
        ).coordinate(BlockStateModelGenerator.createNorthDefaultHorizontalRotationStates());
    }

    private static BlockStateSupplier generateMultiRotationalDecoBlock(Block block) {
        return VariantsBlockStateSupplier.create(block, BlockStateVariant.create().put(VariantSettings.MODEL,
                        Shrimpsicle.getId("block/" + Registries.BLOCK.getId(block).getPath())))
                .coordinate(
                        BlockStateVariantMap.create(Properties.BLOCK_FACE, Properties.HORIZONTAL_FACING, Properties.BLOCK_HALF)
                                .register(BlockFace.FLOOR, Direction.EAST, BlockHalf.BOTTOM, BlockStateVariant.create().put(VariantSettings.Y, VariantSettings.Rotation.R90))
                                .register(BlockFace.FLOOR, Direction.WEST, BlockHalf.BOTTOM, BlockStateVariant.create().put(VariantSettings.Y, VariantSettings.Rotation.R270))
                                .register(BlockFace.FLOOR, Direction.SOUTH, BlockHalf.BOTTOM, BlockStateVariant.create().put(VariantSettings.Y, VariantSettings.Rotation.R180))
                                .register(BlockFace.FLOOR, Direction.NORTH, BlockHalf.BOTTOM, BlockStateVariant.create())

                                .register(BlockFace.FLOOR, Direction.EAST, BlockHalf.TOP, BlockStateVariant.create().put(VariantSettings.Y, VariantSettings.Rotation.R90))
                                .register(BlockFace.FLOOR, Direction.WEST, BlockHalf.TOP, BlockStateVariant.create().put(VariantSettings.Y, VariantSettings.Rotation.R270))
                                .register(BlockFace.FLOOR, Direction.SOUTH, BlockHalf.TOP, BlockStateVariant.create().put(VariantSettings.Y, VariantSettings.Rotation.R180))
                                .register(BlockFace.FLOOR, Direction.NORTH, BlockHalf.TOP, BlockStateVariant.create())

                                .register(
                                        BlockFace.WALL,
                                        Direction.EAST,
                                        BlockHalf.BOTTOM,
                                        BlockStateVariant.create()
                                                .put(VariantSettings.Y, VariantSettings.Rotation.R90)
                                                .put(VariantSettings.X, VariantSettings.Rotation.R180)
                                                .put(VariantSettings.MODEL, Shrimpsicle.getId("block/gecko_wall"))
                                )
                                .register(
                                        BlockFace.WALL,
                                        Direction.WEST,
                                        BlockHalf.BOTTOM,
                                        BlockStateVariant.create()
                                                .put(VariantSettings.Y, VariantSettings.Rotation.R270)
                                                .put(VariantSettings.X, VariantSettings.Rotation.R180)
                                                .put(VariantSettings.MODEL, Shrimpsicle.getId("block/gecko_wall"))
                                )
                                .register(
                                        BlockFace.WALL,
                                        Direction.SOUTH,
                                        BlockHalf.BOTTOM,
                                        BlockStateVariant.create()
                                                .put(VariantSettings.Y, VariantSettings.Rotation.R180)
                                                .put(VariantSettings.X, VariantSettings.Rotation.R180)
                                                .put(VariantSettings.MODEL, Shrimpsicle.getId("block/gecko_wall"))
                                )
                                .register(
                                        BlockFace.WALL,
                                        Direction.NORTH,
                                        BlockHalf.BOTTOM,
                                        BlockStateVariant.create()
                                                .put(VariantSettings.X, VariantSettings.Rotation.R180)
                                                .put(VariantSettings.MODEL, Shrimpsicle.getId("block/gecko_wall"))
                                )


                                .register(
                                        BlockFace.WALL,
                                        Direction.EAST,
                                        BlockHalf.TOP,
                                        BlockStateVariant.create()
                                                .put(VariantSettings.Y, VariantSettings.Rotation.R270)
                                                .put(VariantSettings.X, VariantSettings.Rotation.R0)
                                                .put(VariantSettings.MODEL, Shrimpsicle.getId("block/gecko_wall"))
                                )
                                .register(
                                        BlockFace.WALL,
                                        Direction.WEST,
                                        BlockHalf.TOP,
                                        BlockStateVariant.create()
                                                .put(VariantSettings.Y, VariantSettings.Rotation.R90)
                                                .put(VariantSettings.X, VariantSettings.Rotation.R0)
                                                .put(VariantSettings.MODEL, Shrimpsicle.getId("block/gecko_wall"))
                                )
                                .register(
                                        BlockFace.WALL,
                                        Direction.SOUTH,
                                        BlockHalf.TOP,
                                        BlockStateVariant.create()
                                                .put(VariantSettings.Y, VariantSettings.Rotation.R0)
                                                .put(VariantSettings.X, VariantSettings.Rotation.R0)
                                                .put(VariantSettings.MODEL, Shrimpsicle.getId("block/gecko_wall"))
                                )
                                .register(
                                        BlockFace.WALL,
                                        Direction.NORTH,
                                        BlockHalf.TOP,
                                        BlockStateVariant.create()
                                                .put(VariantSettings.X, VariantSettings.Rotation.R0)
                                                .put(VariantSettings.Y, VariantSettings.Rotation.R180)
                                                .put(VariantSettings.MODEL, Shrimpsicle.getId("block/gecko_wall"))
                                )


                                .register(
                                        BlockFace.CEILING,
                                        Direction.EAST,
                                        BlockHalf.BOTTOM,
                                        BlockStateVariant.create().put(VariantSettings.Y, VariantSettings.Rotation.R270).put(VariantSettings.X, VariantSettings.Rotation.R180)
                                )
                                .register(
                                        BlockFace.CEILING,
                                        Direction.WEST,
                                        BlockHalf.BOTTOM,
                                        BlockStateVariant.create().put(VariantSettings.Y, VariantSettings.Rotation.R90).put(VariantSettings.X, VariantSettings.Rotation.R180)
                                )
                                .register(
                                        BlockFace.CEILING,
                                        Direction.SOUTH,
                                        BlockHalf.BOTTOM,
                                        BlockStateVariant.create().put(VariantSettings.X, VariantSettings.Rotation.R180)
                                )
                                .register(
                                        BlockFace.CEILING,
                                        Direction.NORTH,
                                        BlockHalf.BOTTOM,
                                        BlockStateVariant.create().put(VariantSettings.Y, VariantSettings.Rotation.R180).put(VariantSettings.X, VariantSettings.Rotation.R180)
                                )


                                .register(
                                        BlockFace.CEILING,
                                        Direction.EAST,
                                        BlockHalf.TOP,
                                        BlockStateVariant.create().put(VariantSettings.Y, VariantSettings.Rotation.R270).put(VariantSettings.X, VariantSettings.Rotation.R180)
                                )
                                .register(
                                        BlockFace.CEILING,
                                        Direction.WEST,
                                        BlockHalf.TOP,
                                        BlockStateVariant.create().put(VariantSettings.Y, VariantSettings.Rotation.R90).put(VariantSettings.X, VariantSettings.Rotation.R180)
                                )
                                .register(
                                        BlockFace.CEILING,
                                        Direction.SOUTH,
                                        BlockHalf.TOP,
                                        BlockStateVariant.create().put(VariantSettings.X, VariantSettings.Rotation.R180)
                                )
                                .register(
                                        BlockFace.CEILING,
                                        Direction.NORTH,
                                        BlockHalf.TOP,
                                        BlockStateVariant.create().put(VariantSettings.Y, VariantSettings.Rotation.R180).put(VariantSettings.X, VariantSettings.Rotation.R180)
                                )


                );
    }

    private static BlockStateSupplier generateBeachUmbrellaBlock(Block block) {
        return VariantsBlockStateSupplier.create(block).coordinate(
                BlockStateVariantMap.create(BeachUmbrellaBlock.PART).register(part ->
                        switch (part) {
                            case BOTTOM -> BlockStateVariant.create().put(VariantSettings.MODEL,
                                    Shrimpsicle.getId("block/beach_umbrella_bottom"));
                            case MID -> BlockStateVariant.create().put(VariantSettings.MODEL,
                                    Shrimpsicle.getId("block/beach_umbrella_mid"));
                            case TOP -> BlockStateVariant.create().put(VariantSettings.MODEL,
                                    Shrimpsicle.getId("block/beach_umbrella_top"));
                        }
                )
        );
    }
}
