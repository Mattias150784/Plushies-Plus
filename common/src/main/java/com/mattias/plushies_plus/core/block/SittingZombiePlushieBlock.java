package com.mattias.plushies_plus.core.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class SittingZombiePlushieBlock extends Block {

    private static final VoxelShape SHAPE = Block.box(6.0D, 0.0D, 7.0D, 9.0D, 12.0D, 8.0D);

    public SittingZombiePlushieBlock(Properties $$0) {
        super($$0);
    }

    @Override
    public VoxelShape getShape(BlockState $$0, BlockGetter $$1, BlockPos $$2, CollisionContext $$3) {
        return super.getShape($$0, $$1, $$2, $$3);
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
