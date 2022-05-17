package net.fabricmc.example;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.client.model.FabricModelPredicateProviderRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;

import static net.fabricmc.example.ExampleMod.OVERLOAD;
import static net.fabricmc.example.ExampleMod.SHUTSU_CRANE;

public class ExampleClientMod implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        FabricModelPredicateProviderRegistry.register(SHUTSU_CRANE, new Identifier("overload"), (itemStack, clientWorld, livingEntity, i) -> {
            if (livingEntity == null) {
                return 0.0F;
            }
            if (livingEntity.hasStatusEffect(OVERLOAD)) {
                return  1.0F;
            }
            return 0.0F;
        });

    }
}
