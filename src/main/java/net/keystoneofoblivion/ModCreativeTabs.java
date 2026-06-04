package net.keystoneofoblivion;

import net.minecraft.ChatFormatting;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, KeystoneOfOblivion.MODID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> MAIN_TAB = CREATIVE_MODE_TABS.register(
            "main",
            () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.keystone_of_oblivion").withStyle(ChatFormatting.DARK_PURPLE))
                    .icon(() -> new ItemStack(ModItems.OBLIVION_KEYSTONE.get()))
                    .displayItems((params, output) -> output.accept(ModItems.OBLIVION_KEYSTONE.get()))
                    .build());

    private ModCreativeTabs() {}
}
