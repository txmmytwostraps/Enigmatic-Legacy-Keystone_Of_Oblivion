package net.keystoneofoblivion;

import net.keystoneofoblivion.item.DislocationRing;
import net.keystoneofoblivion.item.IronRing;
import net.keystoneofoblivion.item.MagnetRing;
import net.keystoneofoblivion.item.OblivionKeystoneItem;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;

public final class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(KeystoneOfOblivion.MODID);

    public static final DeferredItem<OblivionKeystoneItem> OBLIVION_KEYSTONE = ITEMS.register(
            "oblivion_keystone",
            () -> new OblivionKeystoneItem(new Item.Properties()
                    .stacksTo(1)
                    .rarity(Rarity.RARE)
                    .fireResistant()
                    .component(ModDataComponents.BOUND_ITEMS.get(), List.of())
                    .component(ModDataComponents.CONSUMPTION_MODE.get(), 0)
                    .component(ModDataComponents.ACTIVE.get(), true)
                    .component(DataComponents.ENCHANTMENT_GLINT_OVERRIDE, true)));

    public static final DeferredItem<IronRing> IRON_RING = ITEMS.register(
            "iron_ring",
            () -> new IronRing(new Item.Properties().stacksTo(1)));

    public static final DeferredItem<MagnetRing> MAGNET_RING = ITEMS.register(
            "magnet_ring",
            () -> new MagnetRing(new Item.Properties().stacksTo(1).rarity(Rarity.RARE)));

    public static final DeferredItem<DislocationRing> DISLOCATION_RING = ITEMS.register(
            "dislocation_ring",
            () -> new DislocationRing(new Item.Properties().stacksTo(1).rarity(Rarity.EPIC)));

    private ModItems() {}
}
