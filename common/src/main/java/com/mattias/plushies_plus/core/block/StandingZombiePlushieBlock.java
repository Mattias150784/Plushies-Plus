package com.mattias.plushies_plus.core.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class StandingZombiePlushieBlock extends Block {

    private static final VoxelShape SHAPE = Block.box(6.0D, 0.0D, 7.0D, 9.0D, 12.0D, 8.0D);

    public StandingZombiePlushieBlock(Properties $$0) {
        super($$0);
    }

    @Override
    public VoxelShape getShape(BlockState $$0, BlockGetter $$1, BlockPos $$2, CollisionContext $$3) {
        return super.getShape($$0, $$1, $$2, $$3);
    }
}
