package com.ganju.ganjumozu.registries;

import com.ganju.ganjumozu.Ganju;
import com.ganju.ganjumozu.registries.screens.LetterMenu;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class GanjuMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(Registries.MENU, Ganju.MODID);
    public static final Supplier<MenuType<LetterMenu>> LETTER_MENU =
            registerMenuType("letter_menu", LetterMenu::new);

    private static <T extends AbstractContainerMenu>Supplier<MenuType<T>> registerMenuType(String name, IContainerFactory<T> factory) {
        return MENUS.register(name, () -> IForgeMenuType.create(factory));
    }
}
