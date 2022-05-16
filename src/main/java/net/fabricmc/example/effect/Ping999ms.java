package net.fabricmc.example.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

import static net.fabricmc.example.ExampleMod.CURSED_CAT;
import static net.fabricmc.example.ExampleMod.LOGGER;

public class Ping999ms extends StatusEffect {
    public Ping999ms() {
        super(StatusEffectCategory.BENEFICIAL, 0x000000);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        // 2%概率给予缓慢X效果
        World world = entity.getWorld();
        if (world.getRandom().nextFloat() <= 0.02+0.01*amplifier) {
            entity.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 20, 9, false, false));
        }
        // 移除不知道为什么会持续出现的“00:00 缓慢X”效果
        if (entity.hasStatusEffect(StatusEffects.SLOWNESS)) {
            if (entity.getStatusEffect(StatusEffects.SLOWNESS).getDuration()==0)
                entity.removeStatusEffect(StatusEffects.SLOWNESS);
        }
    }
}