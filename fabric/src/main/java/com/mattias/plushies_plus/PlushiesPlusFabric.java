package com.mattias.plushies_plus;

import com.mattias.plushies_plus.core.block.StandingZombiePlushieBlock;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class PlushiesPlusFabric implements ModInitializer {
    
    @Override
    public void onInitialize() {
        PlushiesPlus.init();

        registerAll();
    }

    public static final Block ZOMBIE_PLUSHIE = registerBlock("zombie_plushie", new StandingZombiePlushieBlock(BlockBehaviour.Properties.of().strength(0.8F).noOcclusion().sound(SoundType.WOOL).ignitedByLava()));

    public static final CreativeModeTab PLUSHIES_PLUS_TAB = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,
            new ResourceLocation(Constants.MOD_ID, "plushies_plus"),
            FabricItemGroup.builder().title(Component.translatable("itemGroup.plushiesPlus"))
                    .icon(() -> new ItemStack(ZOMBIE_PLUSHIE)).displayItems((displayContext, entries) -> {
                        entries.accept(ZOMBIE_PLUSHIE);
                    }).build());

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(Constants.MOD_ID, name), block);
    }
    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(Constants.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
    }

    public static void registerAll() {}
}
