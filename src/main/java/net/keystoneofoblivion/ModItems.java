package net.keystoneofoblivion;

import net.keystoneofoblivion.item.OblivionKeystoneItem;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.Rarity;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;

public final class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(KeystoneOfOblivion.MODID);

    public static final DeferredItem<OblivionKeystoneItem> OBLIVION_KEYSTONE = ITEMS.registerItem(
            "oblivion_keystone",
            props -> new OblivionKeystoneItem(props
                    .stacksTo(1)
                    .rarity(Rarity.RARE)
                    .fireResistant()
                    .component(ModDataComponents.BOUND_ITEMS.get(), List.of())
                    .component(ModDataComponents.CONSUMPTION_MODE.get(), 0)
                    .component(ModDataComponents.ACTIVE.get(), true)
                    .component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true)));

    private ModItems() {}
}
