package net.fabricmc.example.material;

import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public class SteelMaterial implements ToolMaterial {

    public static final SteelMaterial INSTANCE = new SteelMaterial();

    @Override
    // 耐久
    public int getDurability() {
        return 1000;
    }

    @Override
    // 挖掘速度
    public float getMiningSpeedMultiplier() {
        return 5.0F;
    }

    @Override
    // 攻击伤害
    public float getAttackDamage() {
        return 0.0F;
    }

    @Override
    // 挖掘等级
    public int getMiningLevel() {
        return 3;
    }

    @Override
    // 附魔权重(获得高级附魔的能力)
    public int getEnchantability() {
        return 15;
    }

    @Override
    // 修复用材料
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(Items.IRON_INGOT);
    }
}
