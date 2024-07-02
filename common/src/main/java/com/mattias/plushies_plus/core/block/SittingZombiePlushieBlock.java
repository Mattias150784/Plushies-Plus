package com.mattias.plushies_plus.core.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class SittingZombiePlushieBlock extends Block {
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
                                                    //X1 Y1 Z1 X2 Y2 Z2
    private static final VoxelShape SHAPE = Block.box(4.0D, 0.0D, 4.0D, 12.0D, 8.0D, 12.0D);

    public SittingZombiePlushieBlock(Properties $$0) {
        super($$0);
        this.registerDefaultState((BlockState)((BlockState)((BlockState)this.stateDefinition.any()).setValue(FACING, Direction.NORTH)));
    }
    @Override
    public BlockState rotate(BlockState p_48722_, Rotation p_48723_) {
        return (BlockState)p_48722_.setValue(FACING, p_48723_.rotate((Direction)p_48722_.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState p_48719_, Mirror p_48720_) {
        return p_48719_.rotate(p_48720_.getRotation((Direction)p_48719_.getValue(FACING)));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_48725_) {
        p_48725_.add(new Property[]{FACING});
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext p_48689_) {
        return (BlockState)this.defaultBlockState().setValue(FACING, p_48689_.getHorizontalDirection().getOpposite());
    }


    @Override
    public VoxelShape getShape(BlockState $$0, BlockGetter $$1, BlockPos $$2, CollisionContext $$3) {
        return SHAPE;
    }

    public static void handleServerInteraction (ServerLevel level, BlockPos pos) {
                level.playSound(null, pos, SoundEvents.ZOMBIE_AMBIENT, SoundSource.PLAYERS);

    }


    @Override
    public InteractionResult use(BlockState $$0, Level level, BlockPos pos, Player $$3, InteractionHand $$4, BlockHitResult $$5) {
        if (!level.isClientSide()) {
            handleServerInteraction((ServerLevel) level, pos);
        }

       return InteractionResult.SUCCESS;
    }
}
