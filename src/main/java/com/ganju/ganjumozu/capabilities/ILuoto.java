package com.ganju.ganjumozu.capabilities;

import net.minecraftforge.common.capabilities.AutoRegisterCapability;

@AutoRegisterCapability
public interface ILuoto {
    float getLuotoStored();
    float getLoutoCapacity();
    void setLuotoStored(float value);
    void setLuotoCapacity(float value);
    void unlockLouto();
    boolean hasUnlockedLouto();
}
