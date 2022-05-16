package net.fabricmc.example.material;

import net.minecraft.item.*;
import net.minecraft.recipe.Ingredient;

public class BasicWeaponMaterial implements ToolMaterial {

    public static final BasicWeaponMaterial INSTANCE = new BasicWeaponMaterial();

    @Override
    // 耐久
    public int getDurability() {
        return 10000;
    }

    @Override
    // 挖掘速度
    public float getMiningSpeedMultiplier() {
        return 0.0F;
    }

    @Override
    // 攻击伤害
    public float getAttackDamage() {
        return 0.0F;
    }

    @Override
    // 挖掘等级
    public int getMiningLevel() {
        return 0;
    }

    @Override
    // 附魔权重(获得高级附魔的能力)
    public int getEnchantability() {
        return 10;
    }

    @Override
    // 修复用材料
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(Items.NETHER_STAR);
    }
}
