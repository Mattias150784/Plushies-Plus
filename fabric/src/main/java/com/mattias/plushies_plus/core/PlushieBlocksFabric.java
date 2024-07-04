package com.mattias.plushies_plus.core;

import com.mattias.plushies_plus.Constants;
import com.mattias.plushies_plus.core.block.PlushieBlock;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.PushReaction;

public class PlushieBlocksFabric {

    // todo: figure out a system for grouping poses?
    //       - different blocks for groups of poses? (sitting, standing, holding item, etc.)

    public static final Block ZOMBIE_PLUSHIE = registerBlock("zombie_plushie", new PlushieBlock(SoundEvents.ZOMBIE_AMBIENT, PlushieSoundsFabric.SQUEAK, BlockBehaviour.Properties.of().strength(0.5F).noOcclusion().sound(SoundType.WOOL).ignitedByLava().forceSolidOn().pushReaction(PushReaction.DESTROY)));

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(Constants.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(Constants.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
    }

    public static void register() {}
}
