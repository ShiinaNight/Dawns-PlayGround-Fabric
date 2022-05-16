package net.fabricmc.example.item;

import net.fabricmc.example.effect.CursedCat;
import net.fabricmc.fabric.api.client.rendering.v1.LivingEntityFeatureRendererRegistrationCallback;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.client.MinecraftClient;
import net.minecraft.command.argument.EntityArgumentType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.decoration.painting.PaintingMotive;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import static net.fabricmc.example.ExampleMod.CURSED_CAT;

public class CurseBladeItem extends SwordItem {
    public CurseBladeItem(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand){
        playerEntity.getItemCooldownManager().set(this, 40);
        if (playerEntity.hasStatusEffect(CURSED_CAT)) {
            playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 5, 5, false, false));
            playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.INVISIBILITY, 5, 0, false, false));
            playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 5, 4, false, false));
            playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 5, 0, false, false));
        }
        else playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 5, 4, false, false));
        playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 10, 4, false, false));
        return TypedActionResult.success(playerEntity.getStackInHand(hand));
    }
}