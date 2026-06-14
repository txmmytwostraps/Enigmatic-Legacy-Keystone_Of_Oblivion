package net.keystoneofoblivion;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;

@Mod(KeystoneOfOblivion.MODID)
public final class KeystoneOfOblivion {
    public static final String MODID = "keystone_of_oblivion";

    public KeystoneOfOblivion(IEventBus modBus, ModContainer container) {
        ModDataComponents.COMPONENTS.register(modBus);
        ModAttachments.ATTACHMENT_TYPES.register(modBus);
        ModItems.ITEMS.register(modBus);
        ModSounds.SOUNDS.register(modBus);
        ModRecipeSerializers.RECIPE_SERIALIZERS.register(modBus);
        ModCreativeTabs.CREATIVE_MODE_TABS.register(modBus);
        ModLootModifiers.LOOT_MODIFIERS.register(modBus);

        container.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }
}
