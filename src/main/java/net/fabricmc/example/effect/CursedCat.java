package net.fabricmc.example.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;


import java.util.UUID;

import static net.fabricmc.example.ExampleMod.CURSED_CAT;

public class CursedCat extends StatusEffect {
    public CursedCat() {
        super(StatusEffectCategory.BENEFICIAL, 0x000000);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        // 每秒回复3%血量
        entity.setHealth((float) (entity.getHealth() + 0.0015 * (amplifier + 1) * entity.getMaxHealth()));
        if (entity.getHealth() <= 0.1 * entity.getMaxHealth()) {
            // 血量低于10%时，消除该效果并回满血量
            entity.setHealth(entity.getMaxHealth());
            entity.removeStatusEffect(CURSED_CAT);
        }
    }
}
