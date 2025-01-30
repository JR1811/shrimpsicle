package net.shirojr.shrimpsicle.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Block;
import net.minecraft.block.enums.BlockFace;
import net.minecraft.data.client.*;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.Direction;
import net.shirojr.shrimpsicle.init.ShrimpsicleBlocks;

public class ShrimpsicleModelProvider extends FabricModelProvider {
    public ShrimpsicleModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.blockStateCollector.accept(generateRotationalDecoBlock(ShrimpsicleBlocks.GECKO));
        blockStateModelGenerator.blockStateCollector.accept(
                VariantsBlockStateSupplier.create(
                                ShrimpsicleBlocks.BANANA, BlockStateVariant.create().put(VariantSettings.MODEL, ModelIds.getBlockModelId(ShrimpsicleBlocks.BANANA)))
                        .coordinate(BlockStateModelGenerator.createNorthDefaultHorizontalRotationStates())
        );
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {

    }

    private static BlockStateSupplier generateRotationalDecoBlock(Block block) {
        return VariantsBlockStateSupplier.create(block)
                .coordinate(
                        BlockStateVariantMap.create(Properties.BLOCK_FACE, Properties.HORIZONTAL_FACING)
                                .register(BlockFace.FLOOR, Direction.EAST, BlockStateVariant.create().put(VariantSettings.Y, VariantSettings.Rotation.R90))
                                .register(BlockFace.FLOOR, Direction.WEST, BlockStateVariant.create().put(VariantSettings.Y, VariantSettings.Rotation.R270))
                                .register(BlockFace.FLOOR, Direction.SOUTH, BlockStateVariant.create().put(VariantSettings.Y, VariantSettings.Rotation.R180))
                                .register(BlockFace.FLOOR, Direction.NORTH, BlockStateVariant.create())
                                .register(
                                        BlockFace.WALL,
                                        Direction.EAST,
                                        BlockStateVariant.create()
                                                .put(VariantSettings.Y, VariantSettings.Rotation.R90)
                                                .put(VariantSettings.X, VariantSettings.Rotation.R90)
                                                .put(VariantSettings.UVLOCK, true)
                                )
                                .register(
                                        BlockFace.WALL,
                                        Direction.WEST,
                                        BlockStateVariant.create()
                                                .put(VariantSettings.Y, VariantSettings.Rotation.R270)
                                                .put(VariantSettings.X, VariantSettings.Rotation.R90)
                                                .put(VariantSettings.UVLOCK, true)
                                )
                                .register(
                                        BlockFace.WALL,
                                        Direction.SOUTH,
                                        BlockStateVariant.create()
                                                .put(VariantSettings.Y, VariantSettings.Rotation.R180)
                                                .put(VariantSettings.X, VariantSettings.Rotation.R90)
                                                .put(VariantSettings.UVLOCK, true)
                                )
                                .register(
                                        BlockFace.WALL, Direction.NORTH, BlockStateVariant.create().put(VariantSettings.X, VariantSettings.Rotation.R90).put(VariantSettings.UVLOCK, true)
                                )
                                .register(
                                        BlockFace.CEILING,
                                        Direction.EAST,
                                        BlockStateVariant.create().put(VariantSettings.Y, VariantSettings.Rotation.R270).put(VariantSettings.X, VariantSettings.Rotation.R180)
                                )
                                .register(
                                        BlockFace.CEILING,
                                        Direction.WEST,
                                        BlockStateVariant.create().put(VariantSettings.Y, VariantSettings.Rotation.R90).put(VariantSettings.X, VariantSettings.Rotation.R180)
                                )
                                .register(BlockFace.CEILING, Direction.SOUTH, BlockStateVariant.create().put(VariantSettings.X, VariantSettings.Rotation.R180))
                                .register(
                                        BlockFace.CEILING,
                                        Direction.NORTH,
                                        BlockStateVariant.create().put(VariantSettings.Y, VariantSettings.Rotation.R180).put(VariantSettings.X, VariantSettings.Rotation.R180)
                                )
                );
    }
}
