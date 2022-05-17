package net.fabricmc.example.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.player.PlayerEntity;

public class Overload extends StatusEffect {
    public Overload() {
        super(StatusEffectCategory.BENEFICIAL, 0x000000);
    }
    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
    public EntityAttributeModifier AttackSpeedModifier = new EntityAttributeModifier("overload_attack_speed",
            1.0D, EntityAttributeModifier.Operation.MULTIPLY_TOTAL);
    public EntityAttributeModifier AttackDamageModifier = new EntityAttributeModifier("overload_attack_damage",
            0.5D, EntityAttributeModifier.Operation.MULTIPLY_TOTAL);
    public EntityAttributeModifier MovementSpeedModifier = new EntityAttributeModifier("overload_movement_speed",
            0.75D, EntityAttributeModifier.Operation.MULTIPLY_TOTAL);

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        ((PlayerEntity) entity).getHungerManager().addExhaustion((float) (0.2F*(1+0.5*amplifier)));
    }
    public void onApplied(LivingEntity entity, AttributeContainer attributes, int amplifier) {
        super.onApplied(entity, attributes, amplifier);
        entity.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_SPEED).addTemporaryModifier(AttackSpeedModifier);
        entity.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE).addTemporaryModifier(AttackDamageModifier);
        entity.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED).addTemporaryModifier(MovementSpeedModifier);
    }
    public void onRemoved(LivingEntity entity, AttributeContainer attributes, int amplifier) {
        super.onRemoved(entity, attributes, amplifier);
        entity.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_SPEED).removeModifier(AttackSpeedModifier);
        entity.getAttributeInstance(EntityAttributes.GENERIC_ATTACK_DAMAGE).removeModifier(AttackDamageModifier);
        entity.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED).removeModifier(MovementSpeedModifier);
    }
}
