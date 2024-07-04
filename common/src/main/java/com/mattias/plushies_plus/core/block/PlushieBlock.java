package com.mattias.plushies_plus.core.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

public class PlushieBlock extends Block {

    // todo: abstract to registration
    public static final int TOTAL_POSES = 2;
    public static final int POSE_LIMIT = TOTAL_POSES - 1;

     public static final IntegerProperty POSE = IntegerProperty.create("pose", 0, POSE_LIMIT); // 1 -> second pose
//    public static final IntegerProperty POSE = BlockStateProperties.AGE_1; // exactly the same function as the above comment
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    private static final VoxelShape SHAPE = Block.box(4.0D, 0.0D, 4.0D, 12.0D, 8.0D, 12.0D);

    private @Nullable SoundEvent interactionSound = null;

    public PlushieBlock(@Nullable SoundEvent interactionSound, Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(FACING, Direction.NORTH)
                .setValue(POSE, 0));
        this.interactionSound = interactionSound;
    }

    @Override
    public BlockState rotate(BlockState blockstate, Rotation rotation) {
        return (BlockState)blockstate.setValue(FACING, rotation.rotate((Direction)blockstate.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation((Direction)state.getValue(FACING)));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> definition) {
        definition.add(new Property[]{FACING});
        definition.add(new Property[]{POSE});
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return (BlockState)this.defaultBlockState()
                .setValue(FACING, context.getHorizontalDirection().getOpposite())
                .setValue(POSE, 0);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    public BlockState updateShape(BlockState $$0, Direction $$1, BlockState $$2, LevelAccessor $$3, BlockPos $$4, BlockPos $$5) {
        return $$1 == Direction.DOWN && !$$0.canSurvive($$3, $$4) ? Blocks.AIR.defaultBlockState() : super.updateShape($$0, $$1, $$2, $$3, $$4, $$5);
    }

    @Override
    public boolean canSurvive(BlockState $$0, LevelReader $$1, BlockPos $$2) {
        return $$1.getBlockState($$2.below()).isSolid();
    }

    public static void playSoundAtServerLevelPosition(ServerLevel level, BlockPos pos, SoundEvent sound) {
        level.playSound(null, pos, sound, SoundSource.BLOCKS);
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {

        if (!level.isClientSide()) {

            if (!player.isCrouching() && this.interactionSound != null) {

                playSoundAtServerLevelPosition((ServerLevel) level, pos, this.interactionSound);

                return InteractionResult.sidedSuccess(true);
            }

            if (player.isCrouching()) {

                if (state.getValue(POSE) == POSE_LIMIT) {
                    level.setBlock(pos, state.setValue(POSE, 0), Block.UPDATE_ALL);
                }
                else {
                    level.setBlock(pos, state.setValue(POSE, state.getValue(POSE) + 1), Block.UPDATE_ALL);
                }

                return InteractionResult.sidedSuccess(true);

            }
        }

       return InteractionResult.sidedSuccess(true);
    }
}
