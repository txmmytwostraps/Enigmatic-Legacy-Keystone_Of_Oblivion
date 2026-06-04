package net.keystoneofoblivion;

import com.mojang.serialization.MapCodec;
import net.keystoneofoblivion.loot.AddKeystoneLootModifier;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public final class ModLootModifiers {
    public static final DeferredRegister<MapCodec<? extends IGlobalLootModifier>> LOOT_MODIFIERS =
            DeferredRegister.create(NeoForgeRegistries.GLOBAL_LOOT_MODIFIER_SERIALIZERS, KeystoneOfOblivion.MODID);

    public static final DeferredHolder<MapCodec<? extends IGlobalLootModifier>, MapCodec<AddKeystoneLootModifier>> ADD_KEYSTONE =
            LOOT_MODIFIERS.register("add_keystone", () -> AddKeystoneLootModifier.CODEC);

    private ModLootModifiers() {}
}
