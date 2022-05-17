package net.fabricmc.example;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.example.effect.*;
import net.fabricmc.example.item.combat.*;
import net.fabricmc.example.item.misc.TheOneTeachMeJava;
import net.fabricmc.example.item.misc.ThePotatoTower;
import net.fabricmc.example.material.*;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExampleMod implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("modid");

	// 物品组实例
	public static final ItemGroup POTATO_ART = FabricItemGroupBuilder.build(
			new Identifier("dawn", "potato_art"),
			() -> new ItemStack(Items.BAKED_POTATO));

	// 一般物品的实例
	public static final Item COMPRESSED_BAKED_POTATO_1X = new Item(new FabricItemSettings().group(POTATO_ART));
	public static final Item COMPRESSED_BAKED_POTATO_2X = new Item(new FabricItemSettings().group(POTATO_ART));
	public static final Item COMPRESSED_BAKED_POTATO_3X = new Item(new FabricItemSettings().group(POTATO_ART));
	public static final Item COMPRESSED_BAKED_POTATO_4X = new Item(new FabricItemSettings().group(POTATO_ART).maxCount(16));
	public static final Item ULTIMATE_COMPRESSED_BAKED_POTATO = new Item(new FabricItemSettings().group(POTATO_ART).maxCount(1));
	public static  Item POTATO_CORE = new Item(new FabricItemSettings().group(POTATO_ART).maxCount(1).rarity(Rarity.RARE));
	public static final Item STEEL_INGOT = new Item(new FabricItemSettings().group(ItemGroup.MISC));
	public static final Item HALLOWED_STEEL_INGOT = new Item(new FabricItemSettings().group(ItemGroup.MISC).rarity(Rarity.RARE));




	// 特殊物品的实例
	public static final TheOneTeachMeJava THEONE_TEACH_ME_JAVA = new TheOneTeachMeJava(new FabricItemSettings().maxCount(1024));
	public static ToolItem SLEEPING_BEAUTY = new SwordItem(BasicWeaponMaterial.INSTANCE, 23, -2.4F, new Item.Settings().group(ItemGroup.COMBAT));
	public static ToolItem KATANA = new KatanaItem(SteelMaterial.INSTANCE, 11, -3.2F, new Item.Settings().group(ItemGroup.COMBAT));
	public static ToolItem TEST_SOUL_STEEL_ITEM = new SoulSteelSwordItem(SoulSteelMaterial.INSTANCE, 0, -2.4F, new Item.Settings().group(ItemGroup.COMBAT));
	public static ToolItem CURSE_BLADE = new CurseBladeItem(SoulSteelMaterial.INSTANCE, 23, -3.2F, new Item.Settings().group(ItemGroup.COMBAT));
	public static ToolItem SAINT_HEART = new SaintHeartItem(BasicWeaponMaterial.INSTANCE, 42, -3.2F, new Item.Settings().group(ItemGroup.COMBAT));
	public static ToolItem JUDAH_OATH = new JudahOathItem(BasicWeaponMaterial.INSTANCE, 119, -3.2F, new Item.Settings().group(ItemGroup.COMBAT));
	public static ToolItem SOLAR_SWORD = new SolarSwordItem(BasicWeaponMaterial.INSTANCE, 0, 0.0F, new Item.Settings().group(ItemGroup.COMBAT));
	public static ToolItem THE_ULTIMATE_COMPRESSED_BAKED_POTATO_SWORD = new TheUltimateCompressedBakedPotatoSwordItem(UltimateCompressedBakedPotatoMaterial.INSTANCE, 12, 9.0F, new Item.Settings().group(POTATO_ART));
	public static ToolItem THE_GREAT_BLESSED_ULTIMATE_COMPRESSED_BAKED_POTATO_SWORD = new TheUltimateCompressedBakedPotatoSwordItem(UltimateCompressedBakedPotatoMaterial.INSTANCE, 22, 19.0F, new Item.Settings().group(POTATO_ART));
	public static final ThePotatoTower THE_POTATO_TOWER = new ThePotatoTower(new FabricItemSettings().group(POTATO_ART).maxCount(1).rarity(Rarity.RARE));
	public static ToolItem SHUTSU_CRANE = new ShutsuCraneItem(HallowedSteelMaterial.INSTANCE, 15, -2.0F, new Item.Settings().group(ItemGroup.COMBAT).rarity(Rarity.RARE));


	// 状态效果的实例
	public static final StatusEffect CURSED_CAT = new CursedCat();
	public static final StatusEffect DARK_EROSION = new DarkErosion();
	public static final StatusEffect PING_999MS = new Ping999ms();
	public static final StatusEffect CURSE_OF_THE_POTATO_TOWER_EFFECT = new CurseOfThePotatoTower();
	public static final StatusEffect FRAGILE = new Fragile();
	public static final StatusEffect OVERLOAD = new Overload();



	// 食物的实例
	public static final Item BLESSED_POTATO = new Item(new FabricItemSettings().group(POTATO_ART).maxCount(1).rarity(Rarity.EPIC)
			.food(new FoodComponent.Builder().hunger(10).saturationModifier(10.0F)
					.statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 72000, 3), 1.0F)
					.build()));

	public static final Item CURSE_OF_THE_POTATO_TOWER_ITEM = new Item(new FabricItemSettings().group(POTATO_ART).maxCount(1).rarity(Rarity.RARE)
			.food(new FoodComponent.Builder().hunger(0)
					.statusEffect(new StatusEffectInstance(CURSE_OF_THE_POTATO_TOWER_EFFECT, 6000, 0), 1.0F)
					.build()));


	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		LOGGER.info("Hello Fabric world!");
		Registry.register(Registry.ITEM, new Identifier("dawn", "theone_teach_me_java"), THEONE_TEACH_ME_JAVA);
		Registry.register(Registry.ITEM, new Identifier("dawn", "sleeping_beauty"), SLEEPING_BEAUTY);
		Registry.register(Registry.ITEM, new Identifier("dawn", "katana"), KATANA);
		Registry.register(Registry.ITEM, new Identifier("dawn", "test_soul_steel_item"), TEST_SOUL_STEEL_ITEM);
		Registry.register(Registry.ITEM, new Identifier("dawn", "curse"), CURSE_BLADE);
		Registry.register(Registry.ITEM, new Identifier("dawn", "saint_heart"), SAINT_HEART);
		Registry.register(Registry.ITEM, new Identifier("dawn", "judah_oath"), JUDAH_OATH);
		Registry.register(Registry.ITEM, new Identifier("dawn", "solar_sword"), SOLAR_SWORD);
		Registry.register(Registry.ITEM, new Identifier("dawn", "compressed_baked_potato_1x"), COMPRESSED_BAKED_POTATO_1X);
		Registry.register(Registry.ITEM, new Identifier("dawn", "compressed_baked_potato_2x"), COMPRESSED_BAKED_POTATO_2X);
		Registry.register(Registry.ITEM, new Identifier("dawn", "compressed_baked_potato_3x"), COMPRESSED_BAKED_POTATO_3X);
		Registry.register(Registry.ITEM, new Identifier("dawn", "compressed_baked_potato_4x"), COMPRESSED_BAKED_POTATO_4X);
		Registry.register(Registry.ITEM, new Identifier("dawn", "ultimate_compressed_baked_potato"), ULTIMATE_COMPRESSED_BAKED_POTATO);
		Registry.register(Registry.ITEM, new Identifier("dawn", "the_ultimate_compressed_baked_potato_sword"), THE_ULTIMATE_COMPRESSED_BAKED_POTATO_SWORD);
		Registry.register(Registry.ITEM, new Identifier("dawn", "the_great_blessed_ultimate_compressed_baked_potato_sword"), THE_GREAT_BLESSED_ULTIMATE_COMPRESSED_BAKED_POTATO_SWORD);
		Registry.register(Registry.ITEM, new Identifier("dawn", "potato_core"), POTATO_CORE);
		Registry.register(Registry.ITEM, new Identifier("dawn", "blessed_potato"), BLESSED_POTATO);
		Registry.register(Registry.ITEM, new Identifier("dawn", "the_potato_tower"), THE_POTATO_TOWER);
		Registry.register(Registry.ITEM, new Identifier("dawn", "curse_of_the_potato_tower"), CURSE_OF_THE_POTATO_TOWER_ITEM);
		Registry.register(Registry.ITEM, new Identifier("dawn", "steel_ingot"), STEEL_INGOT);
		Registry.register(Registry.ITEM, new Identifier("dawn", "hallowed_steel_ingot"), HALLOWED_STEEL_INGOT);
		Registry.register(Registry.ITEM, new Identifier("dawn", "shutsu_crane"), SHUTSU_CRANE);


		// 状态效果
		Registry.register(Registry.STATUS_EFFECT, new Identifier("dawn", "cursed_cat"), CURSED_CAT);
		Registry.register(Registry.STATUS_EFFECT, new Identifier("dawn", "dark_erosion"), DARK_EROSION);
		Registry.register(Registry.STATUS_EFFECT, new Identifier("dawn", "ping_999ms"), PING_999MS);
		Registry.register(Registry.STATUS_EFFECT, new Identifier("dawn", "curse_of_the_potato_tower"), CURSE_OF_THE_POTATO_TOWER_EFFECT);
		Registry.register(Registry.STATUS_EFFECT, new Identifier("dawn", "fragile"), FRAGILE);
		Registry.register(Registry.STATUS_EFFECT, new Identifier("dawn", "overload"), OVERLOAD);


		// 添加攻击事件
		AttackEntityCallback.EVENT.register((playerEntity, world, hand, entity, hitResult) ->
		{
			// 百分比伤害与总伤害不叠加，两者取最高
			float final_damage = (float) playerEntity.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE);
			float final_true_damage = 0.0F;
			float percent_damage = 0.0F;
			float damage_multiplier = 1.0F;
			/* theOne教我Java! */
			if (!playerEntity.isSpectator() &&
					playerEntity.getMainHandStack().isOf(THEONE_TEACH_ME_JAVA))
			// 这段代码的效果是令玩家在使用物品THEONE_TEACH_ME_JAVA攻击敌人时，额外对敌人造成100点伤害，并施加5秒的缓慢X
			{
				final_damage += 100.0F;
				((LivingEntity) entity).addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 100, 9));
			}
			/* 魂钢类武器 */
			if (!playerEntity.isSpectator() && entity instanceof LivingEntity &&
					(
							playerEntity.getMainHandStack().isOf(TEST_SOUL_STEEL_ITEM) ||
							playerEntity.getMainHandStack().isOf(CURSE_BLADE)
					)
			)
			// 魂钢类武器伤害机制：若武器自身伤害小于目标当前血量的1/3，则对目标造成相当于其当前血量1/3的伤害，否则按照武器自身伤害造成伤害
			{
				percent_damage += 0.33F;
			}
			/* 「诅咒」 */
			if (!playerEntity.isSpectator() && entity instanceof LivingEntity &&
					playerEntity.getMainHandStack().isOf(CURSE_BLADE) &&
					playerEntity.hasStatusEffect(CURSED_CAT)
			)
			// 在拥有诅咒之猫效果时，使用「诅咒」攻击敌人会触发以下效果
			{
				// 施加缓慢和虚弱效果
				((LivingEntity) entity).addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 10, 2, false, false));
				((LivingEntity) entity).addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 20, 2, false, false));
				// 额外造成攻击力10%的真实伤害
				final_true_damage += (float) (0.1*playerEntity.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE));
				// 额外造成30%生命值（×效果等级）的攻击伤害
				final_damage +=
						(float) (playerEntity.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE)+
								(playerEntity.getStatusEffect(CURSED_CAT).getAmplifier()+1)*0.3*playerEntity.getHealth());
				// 播放音效
				world.playSound((PlayerEntity)null, entity.getX(), entity.getY(), entity.getZ(), SoundEvents.ENTITY_WITHER_SPAWN, SoundCategory.NEUTRAL, 0.5F, 0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F));
			}
			/* 太阳剑 */
			if (!playerEntity.isSpectator() && entity instanceof LivingEntity &&
					playerEntity.getMainHandStack().isOf(SOLAR_SWORD)) {
				if (((LivingEntity) entity).hasStatusEffect(DARK_EROSION)) {
					// 每次攻击提高一级暗蚀效果
					int dark_erosion_level = ((LivingEntity) entity).getStatusEffect(DARK_EROSION).getAmplifier();
					((LivingEntity) entity).addStatusEffect(new StatusEffectInstance(DARK_EROSION, 20, dark_erosion_level+1));
				}
				// 第一次攻击施加1级暗蚀效果
				else ((LivingEntity) entity).addStatusEffect(new StatusEffectInstance(DARK_EROSION, 20, 0));
				// 根据暗蚀层数提高太阳剑攻击伤害倍率
				int dark_erosion_level = ((LivingEntity) entity).getStatusEffect(DARK_EROSION).getAmplifier();
				final_damage *= (float) (1+0.5*dark_erosion_level);
			}
			/* 土豆剑 */
			if (!playerEntity.isSpectator() && entity instanceof LivingEntity &&
					(playerEntity.getMainHandStack().isOf(THE_GREAT_BLESSED_ULTIMATE_COMPRESSED_BAKED_POTATO_SWORD)
							|| playerEntity.getMainHandStack().isOf(THE_ULTIMATE_COMPRESSED_BAKED_POTATO_SWORD))
			)
			// 使用土豆剑攻击敌人会触发以下效果
			{
				if (playerEntity.hasStatusEffect(PING_999MS)) {
					// 拥有Ping:999ms效果时
					((LivingEntity) entity).addStatusEffect(new StatusEffectInstance(PING_999MS, 800, 1, false, false));
					// 额外造成攻击力等量的真实伤害
					final_true_damage += (float) playerEntity.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE);
					// 播放音效
					world.playSound((PlayerEntity)null, entity.getX(), entity.getY(), entity.getZ(), SoundEvents.BLOCK_GLASS_BREAK, SoundCategory.NEUTRAL, 0.5F, 0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F));
				} else if (!((LivingEntity) entity).hasStatusEffect(PING_999MS)) {
					((LivingEntity) entity).addStatusEffect(new StatusEffectInstance(PING_999MS, 200, 0, false, false));
				}
			}
			/* 土豆神塔 */
			if (!playerEntity.isSpectator() && entity instanceof LivingEntity &&
					playerEntity.hasStatusEffect(CURSE_OF_THE_POTATO_TOWER_EFFECT)
			) {
				// 在拥有土豆神塔的诅咒时，攻击敌人掉落0~3个土豆
				world.playSound((PlayerEntity)null, entity.getX(), entity.getY(), entity.getZ(), SoundEvents.ENTITY_SNOWBALL_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F));
				if (!world.isClient) {
					int potato_amount = (int) (world.getRandom().nextFloat()*3);
					for (int i=1; i<=potato_amount; i++) {
						ItemEntity itemEntity = new ItemEntity(world, entity.getX(), entity.getY()+2, entity.getZ(), new ItemStack(Items.POTATO), (world.getRandom().nextDouble()-0.5)/3, 0.5D, (world.getRandom().nextDouble()-0.5)/3);
						world.spawnEntity(itemEntity);
					}
				}
			}
			/* 伤害倍率 */
			if (!playerEntity.isSpectator() && entity instanceof LivingEntity) {
				if (((LivingEntity) entity).hasStatusEffect(FRAGILE)) {
					damage_multiplier += 0.05*(((LivingEntity) entity).getStatusEffect(FRAGILE).getAmplifier()+1);
				}
			}
			/* 造成伤害 */
			if (!playerEntity.isSpectator() && entity instanceof LivingEntity) {
				final_damage *= damage_multiplier;
				final_true_damage *= damage_multiplier;

				if (((LivingEntity) entity).getHealth() > final_true_damage) {
					((LivingEntity) entity).setHealth(((LivingEntity) entity).getHealth() - final_true_damage);
				} else entity.damage(DamageSource.player(playerEntity), 9999.0F);

				entity.damage(DamageSource.player(playerEntity), final_damage);
				entity.damage(DamageSource.player(playerEntity), percent_damage * ((LivingEntity) entity).getHealth());
			}
			/* 伤害输出 */
			boolean show_detail_damage = true;
			if (show_detail_damage) {
				LOGGER.info("Final Damage: " + final_damage);
				LOGGER.info("Percent Damage: " + percent_damage);
				float real_percent_damage = percent_damage*((LivingEntity) entity).getHealth();
				LOGGER.info("Real Percent Damage: " + real_percent_damage);
				LOGGER.info("True Damage: " + final_true_damage);

				float real_damage;
				if (real_percent_damage>=final_damage) {
					real_damage = real_percent_damage + final_true_damage;
				}
				else {
					real_damage = final_damage + final_true_damage;
				}
				LOGGER.info("-----------------------------");
				LOGGER.info("> Real Damage: " + real_damage);
				LOGGER.info("-----------------------------");
			}

			return ActionResult.PASS;
		});
	}

}
