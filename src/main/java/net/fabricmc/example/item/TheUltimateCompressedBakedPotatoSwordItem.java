package net.fabricmc.example.item;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import static net.fabricmc.example.ExampleMod.PING_999MS;

public class TheUltimateCompressedBakedPotatoSwordItem extends SwordItem {
    public TheUltimateCompressedBakedPotatoSwordItem(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand){
        playerEntity.getItemCooldownManager().set(this, 100);
        playerEntity.addStatusEffect(new StatusEffectInstance(PING_999MS, 400, 0));
        return TypedActionResult.success(playerEntity.getStackInHand(hand));
    }

}