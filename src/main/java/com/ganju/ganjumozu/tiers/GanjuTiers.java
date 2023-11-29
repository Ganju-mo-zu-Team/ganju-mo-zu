package com.ganju.ganjumozu.tiers;

import com.ganju.ganjumozu.api.items.GanjuTier;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Supplier;

public enum GanjuTiers implements GanjuTier {
    BAMBOO("bamboo", 0, 59, 2.0F, 0.0F, 15, () -> Ingredient.of(Items.IRON_INGOT));

    private final String name;
    private final int level;
    private final int uses;
    private final float speed;
    private final float damage;
    private final int enchantmentValue;
    private final LazyLoadedValue<Ingredient> repairIngredient;

    GanjuTiers(String name, int level, int uses, float speed, float damage, int enchantmentValue, Supplier<Ingredient> ingredientSupplier) {
        this.level = level;
        this.uses = uses;
        this.speed = speed;
        this.damage = damage;
        this.enchantmentValue = enchantmentValue;
        this.name = name;
        this.repairIngredient = new LazyLoadedValue<>(ingredientSupplier);
    }

    @Override
    public String getName() {
        return name;
    }

    public int getUses() {
        return this.uses;
    }

    public float getSpeed() {
        return this.speed;
    }

    public float getAttackDamageBonus() {
        return this.damage;
    }

    public int getLevel() {
        return this.level;
    }

    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }
}
