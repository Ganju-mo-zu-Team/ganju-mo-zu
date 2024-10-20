package com.ganju.ganjumozu.registries;

import com.ganju.ganjumozu.Ganju;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class GanjuTab {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Ganju.MODID);
    public static final RegistryObject<CreativeModeTab> GANJU_MAIN = CREATIVE_MODE_TABS.register("ganju_main", () -> CreativeModeTab.builder()
            .title(Component.translatable("ganju.creative_mode_tabs.main"))
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(() -> GanjuItems.TRAINEE_STAFF.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                output.accept(GanjuItems.ARTIFACT.get());
                output.accept(GanjuItems.CRACKED_POT.get());
                output.accept(GanjuItems.TRAINEE_STAFF.get());
                output.accept(GanjuItems.LETTER.get());
                output.accept(GanjuItems.BAMBOOTANA.get());
                output.accept(GanjuBlocks.MONOLITH.get());
                output.accept(GanjuBlocks.PAPER_WALL.get());
                output.accept(GanjuBlocks.PAPER_WALL_DESIGN.get());
                output.accept(GanjuBlocks.PAPER_WALL_PATTERN.get());
            }).build());

}
