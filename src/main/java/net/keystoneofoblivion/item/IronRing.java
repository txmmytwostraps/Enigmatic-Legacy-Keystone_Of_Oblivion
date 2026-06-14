package net.keystoneofoblivion.item;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

/**
 * Basic curio ring that grants +1 armor while worn. Crafting base for the Magnet Ring.
 */
public class IronRing extends Item implements ICurioItem {

    public IronRing(Properties props) {
        super(props);
    }

    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(SlotContext context, ResourceLocation id, ItemStack stack) {
        ImmutableMultimap.Builder<Holder<Attribute>, AttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(Attributes.ARMOR, new AttributeModifier(id, 1, AttributeModifier.Operation.ADD_VALUE));
        return builder.build();
    }

    @Override
    public boolean canEquip(SlotContext context, ItemStack stack) {
        return !MagnetHelper.hasCurio(context.entity(), this) && ICurioItem.super.canEquip(context, stack);
    }
}
