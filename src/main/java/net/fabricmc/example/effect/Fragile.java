package net.fabricmc.example.effect;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class Fragile extends StatusEffect {
    public Fragile() {
        super(StatusEffectCategory.HARMFUL, 0x55FFFF);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
}
