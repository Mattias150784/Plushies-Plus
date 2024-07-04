package com.mattias.plushies_plus.core;

import com.mattias.plushies_plus.Constants;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.level.block.SoundType;

public class PlushieSoundsFabric {

    public static final SoundEvent SQUEAK = registerSoundEvent("squeak");

    private static SoundEvent registerSoundEvent(String name) {
        ResourceLocation identifier = new ResourceLocation(Constants.MOD_ID, name);
        return Registry.register(BuiltInRegistries.SOUND_EVENT, identifier, SoundEvent .createVariableRangeEvent(identifier));
    }

    public static void register() {}
}
