package net.shirojr.shrimpsicle.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.*;
import org.jetbrains.annotations.Nullable;

public class BeachUmbrellaBlock extends Block {
    public static final EnumProperty<Part> PART = EnumProperty.of("part", Part.class);

    public BeachUmbrellaBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState().with(PART, Part.TOP));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(PART);
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        double poleWidth = 7;
        return switch (state.get(PART)) {
            case BOTTOM, MID -> createCuboidShape(poleWidth, 0, poleWidth, 16 - poleWidth, 16, 16 - poleWidth);
            case TOP -> VoxelShapes.union(
                    createCuboidShape(poleWidth, 0, poleWidth, 16 - poleWidth, 11, 16 - poleWidth),
                    createCuboidShape(0, 5, 0, 16, 10, 16)
            );
        };
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(PART, Part.BOTTOM);
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        super.onPlaced(world, pos, state, placer, itemStack);
        BlockPos.Mutable posWalker = pos.mutableCopy();
        posWalker.move(Direction.UP);
        world.setBlockState(posWalker.toImmutable(), state.with(PART, Part.MID));
        posWalker.move(Direction.UP);
        world.setBlockState(posWalker.toImmutable(), state.with(PART, Part.TOP));
    }

    @Override
    protected BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        switch (state.get(PART)) {
            case BOTTOM -> {
                if (!state.canPlaceAt(world, pos)) {
                    world.updateNeighbors(pos.up(), this);
                    return Blocks.AIR.getDefaultState();
                }
            }
            case MID -> {
                if (!world.getBlockState(pos.down()).getBlock().equals(this)) {
                    if (state.get(PART).equals(Part.MID)) {
                        world.updateNeighbors(pos.up(), this);
                    }
                    world.updateNeighbors(pos.down(), this);
                    return Blocks.AIR.getDefaultState();
                }
            }
            case TOP -> {
                world.updateNeighbors(pos.down(), this);
                return Blocks.AIR.getDefaultState();
            }
        }

        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }

    @Override
    protected boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        BlockPos posBelow = pos.down();
        BlockState stateBelow = world.getBlockState(posBelow);
        return switch (state.get(PART)) {
            case TOP, MID -> stateBelow.isOf(this);
            case BOTTOM -> stateBelow.isSideSolidFullSquare(world, posBelow, Direction.UP);
        };
    }

    @Override
    protected boolean canPathfindThrough(BlockState state, NavigationType type) {
        return false;
    }

    @Override
    public BlockState onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        if (!world.isClient && (player.isCreative() || !player.canHarvest(state))) {
            BlockPos.Mutable posWalkerDown = pos.mutableCopy();
            while (world.getBlockState(posWalkerDown.toImmutable()).contains(PART)) {
                BlockPos blockPos = posWalkerDown.toImmutable();
                BlockState stateBelow = world.getBlockState(blockPos);
                world.setBlockState(blockPos, Blocks.AIR.getDefaultState(), Block.NOTIFY_ALL | Block.SKIP_DROPS);
                world.syncWorldEvent(player, WorldEvents.BLOCK_BROKEN, blockPos, Block.getRawIdFromState(stateBelow));
                posWalkerDown.move(Direction.DOWN);
            }

            BlockPos.Mutable posWalkerUp = pos.up().mutableCopy();
            while (world.getBlockState(posWalkerUp.toImmutable()).contains(PART)) {
                BlockPos blockPos = posWalkerUp.toImmutable();
                BlockState stateBelow = world.getBlockState(blockPos);
                world.setBlockState(blockPos, Blocks.AIR.getDefaultState(), Block.NOTIFY_ALL | Block.SKIP_DROPS);
                world.syncWorldEvent(player, WorldEvents.BLOCK_BROKEN, blockPos, Block.getRawIdFromState(stateBelow));
                posWalkerUp.move(Direction.UP);
            }
        }
        return super.onBreak(world, pos, state, player);
    }


    public enum Part implements StringIdentifiable {
        BOTTOM("bottom"),
        MID("mid"),
        TOP("top");

        private final String name;

        Part(String name) {
            this.name = name;
        }

        @Override
        public String asString() {
            return name;
        }

        @SuppressWarnings("unused")
        @Nullable
        public static Part fromString(String name) {
            for (Part entry : Part.values()) {
                if (entry.asString().equals(name)) return entry;
            }
            return null;
        }
    }
}
