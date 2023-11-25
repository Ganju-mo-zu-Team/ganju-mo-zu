package com.ganju.ganjumozu.registries;

import com.ganju.ganjumozu.Ganju;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class GanjuTab {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Ganju.MODID);
    public static final RegistryObject<CreativeModeTab> GANJU_MAIN = CREATIVE_MODE_TABS.register("ganju_main", () -> CreativeModeTab.builder()
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(() -> GanjuItems.EXAMPLE_ITEM.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                output.accept(GanjuItems.EXAMPLE_ITEM.get());
            }).build());
}
