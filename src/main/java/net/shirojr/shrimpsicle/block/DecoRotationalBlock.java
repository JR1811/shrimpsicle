package net.shirojr.shrimpsicle.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.block.enums.BlockFace;
import net.minecraft.block.enums.BlockHalf;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

public class DecoRotationalBlock extends WallMountedBlock {
    public static final MapCodec<DecoRotationalBlock> CODEC = createCodec(DecoRotationalBlock::new);
    public static final EnumProperty<BlockHalf> HALF = Properties.BLOCK_HALF;

    public DecoRotationalBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState()
                .with(FACING, Direction.NORTH)
                .with(FACE, BlockFace.WALL)
                .with(HALF, BlockHalf.BOTTOM)
        );
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        super.appendProperties(builder);
        builder.add(FACE, FACING, HALF);
    }

    @Override
    protected MapCodec<? extends WallMountedBlock> getCodec() {
        return CODEC;
    }

    @Override
    protected VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        BlockFace side = state.get(FACE);
        Direction direction = state.get(FACING);

        return switch (side) {
            case FLOOR -> Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 2.0, 16.0);
            case WALL -> switch (direction) {
                case WEST -> Block.createCuboidShape(14.0, 0.0, 0.0, 16.0, 16.0, 16.0);
                case EAST -> Block.createCuboidShape(0.0, 0.0, 0.0, 2.0, 16.0, 16.0);
                case NORTH -> Block.createCuboidShape(0.0, 0.0, 14.0, 16.0, 16.0, 16.0);
                case SOUTH -> Block.createCuboidShape(0.0, 0.0, 0.0, 16.0, 16.0, 2.0);
                default -> VoxelShapes.empty();
            };
            case CEILING -> Block.createCuboidShape(0.0, 14.0, 0.0, 16.0, 16.0, 16.0);
        };
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockState state = super.getPlacementState(ctx);
        if (state != null && state.get(FACE).equals(BlockFace.WALL)) {
            state = state.with(HALF, ctx.getHitPos().y - (double)ctx.getBlockPos().getY() > 0.5 ? BlockHalf.TOP : BlockHalf.BOTTOM);
        }
        return state;
    }

    @Override
    protected boolean isTransparent(BlockState state, BlockView world, BlockPos pos) {
        return state.getFluidState().isEmpty();
    }

    @Override
    protected boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return canPlaceAt(world, pos, getDirection(state).getOpposite());
    }

    public static boolean canPlaceAt(WorldView world, BlockPos pos, Direction direction) {
        BlockPos blockPos = pos.offset(direction);
        return world.getBlockState(blockPos).isSideSolidFullSquare(world, blockPos, direction.getOpposite());
    }

    @Override
    protected BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (!canPlaceAt(state, world, pos)) {
            return Blocks.AIR.getDefaultState();
        }
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }
}
