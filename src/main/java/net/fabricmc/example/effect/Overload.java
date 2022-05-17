package net.fabricmc.example.effect;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class Overload extends StatusEffect {
    public Overload() {
        super(StatusEffectCategory.BENEFICIAL, 0x000000);
    }
    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
}
