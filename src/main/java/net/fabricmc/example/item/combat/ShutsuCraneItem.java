package net.fabricmc.example.item.combat;

import com.google.common.collect.Multimap;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.List;

import static net.fabricmc.example.ExampleMod.CURSED_CAT;
import static net.fabricmc.example.ExampleMod.OVERLOAD;

public class ShutsuCraneItem extends SwordItem {
    public ShutsuCraneItem(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand){
        playerEntity.getItemCooldownManager().set(this, 20);
        if (playerEntity.hasStatusEffect(OVERLOAD)) {
            playerEntity.removeStatusEffect(OVERLOAD);
        }
        else {
            playerEntity.addStatusEffect(new StatusEffectInstance(OVERLOAD, 32767, 0));
        }
        return TypedActionResult.success(playerEntity.getStackInHand(hand));
    }
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.add( new TranslatableText("item.dawn.shutsu_crane.tooltip") );
        tooltip.add( new TranslatableText("item.dawn.shutsu_crane.tooltip_empty_line") );
        tooltip.add( new TranslatableText("item.dawn.shutsu_crane.tooltip_overload_line1").formatted(Formatting.GRAY) );
        tooltip.add( new TranslatableText("item.dawn.shutsu_crane.tooltip_overload_line2").formatted(Formatting.DARK_GREEN) );
        tooltip.add( new TranslatableText("item.dawn.shutsu_crane.tooltip_overload_line3").formatted(Formatting.DARK_GREEN) );
        tooltip.add( new TranslatableText("item.dawn.shutsu_crane.tooltip_overload_line4").formatted(Formatting.DARK_GREEN) );
        tooltip.add( new TranslatableText("item.dawn.shutsu_crane.tooltip_overload_line5").formatted(Formatting.RED) );
    }
}