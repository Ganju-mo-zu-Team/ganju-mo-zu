package com.ganju.ganjumozu.capabilities;

import com.ganju.ganjumozu.Ganju;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;

public class LoutoProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {
    private final Louto louto = new Louto();
    public static final ResourceLocation IDENT = new ResourceLocation(Ganju.MODID, "louto");
    private final LazyOptional<ILuoto> optional = LazyOptional.of(() -> louto);


    @Override
    @NotNull
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, Direction direction) {
        return GanjuCapabilities.LUOTO.orEmpty(cap, this.optional);
    }

    @Override
    public CompoundTag serializeNBT() {
        return this.louto.serializeNBT();
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        this.louto.deserializeNBT(nbt);
    }
}
