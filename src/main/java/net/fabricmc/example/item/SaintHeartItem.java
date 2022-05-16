package net.fabricmc.example.item;

import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;


public class SaintHeartItem extends SwordItem {
    public SaintHeartItem(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand){
        playerEntity.getItemCooldownManager().set(this, 600);
        playerEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 100, 4));
        return TypedActionResult.success(playerEntity.getStackInHand(hand));
    }

}