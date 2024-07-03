package com.mattias.plushies_plus;

import com.mattias.plushies_plus.core.PlushieBlocksFabric;
import com.mattias.plushies_plus.core.block.PlushieBlock;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
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

        PlushieBlocksFabric.register();
    }
}
