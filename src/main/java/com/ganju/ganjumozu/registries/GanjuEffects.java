package com.ganju.ganjumozu.registries;

import com.ganju.ganjumozu.Ganju;
import com.ganju.ganjumozu.registries.effects.GoldenCurseEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class GanjuEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS =
            DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, Ganju.MODID);

    public static final RegistryObject<MobEffect> GOLDEN_CURSE = MOB_EFFECTS.register("golden_curse",
            () -> new GoldenCurseEffect(MobEffectCategory.NEUTRAL, 0x36ebab));

}
