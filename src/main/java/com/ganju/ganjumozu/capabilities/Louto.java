package com.ganju.ganjumozu.capabilities;

import net.minecraft.nbt.CompoundTag;

public class Louto implements ILuoto {
    private float stored;
    private float capacity;
    private boolean unlocked = false;
    private static final String NBT_KEY_LUOTO_STORED = "loutoStored";
    private static final String NBT_KEY_LUOTO_CAPACITY = "loutoCapacity";
    private static final String NBT_KEY_LUOTO_UNLOCKED = "loutoUnlocked";

    @Override
    public float getLuotoStored() {
        return stored;
    }

    @Override
    public float getLoutoCapacity() {
        return capacity;
    }

    @Override
    public void setLuotoStored(float value) {
        this.stored = value;
    }

    @Override
    public void setLuotoCapacity(float value) {
        this.capacity = value;
    }

    @Override
    public boolean hasUnlockedLouto() {
        return unlocked;
    }

    @Override
    public void unlockLouto() {
        unlocked = true;
    }

    public CompoundTag serializeNBT() {
        final CompoundTag tag = new CompoundTag();
        tag.putFloat(NBT_KEY_LUOTO_STORED, this.stored);
        tag.putFloat(NBT_KEY_LUOTO_CAPACITY, this.capacity);
        tag.putBoolean(NBT_KEY_LUOTO_UNLOCKED, this.unlocked);
        return tag;
    }

    public void deserializeNBT(CompoundTag nbt) {
        this.stored = nbt.getFloat(NBT_KEY_LUOTO_STORED);
        this.capacity = nbt.getFloat(NBT_KEY_LUOTO_CAPACITY);
        this.unlocked = nbt.getBoolean(NBT_KEY_LUOTO_UNLOCKED);
    }
}
