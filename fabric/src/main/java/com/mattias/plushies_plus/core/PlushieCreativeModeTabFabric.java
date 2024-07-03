package com.mattias.plushies_plus.core;

import com.mattias.plushies_plus.Constants;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class PlushieCreativeModeTabFabric {

    public static final CreativeModeTab PLUSHIES_PLUS_TAB = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB,
            new ResourceLocation(Constants.MOD_ID, "plushies_plus"),
            FabricItemGroup.builder().title(Component.translatable("itemGroup.plushiesPlus"))
                    .icon(() -> new ItemStack(PlushieBlocksFabric.ZOMBIE_PLUSHIE))
                    .displayItems((displayContext, entries) -> {
                        entries.accept(PlushieBlocksFabric.ZOMBIE_PLUSHIE);
                    }).build());
}
