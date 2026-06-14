package net.keystoneofoblivion.client;

import net.keystoneofoblivion.KeystoneOfOblivion;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.CreativeModeInventoryScreen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ScreenEvent;
import top.theillusivec4.curios.api.client.ICuriosScreen;

@EventBusSubscriber(modid = KeystoneOfOblivion.MODID, value = Dist.CLIENT)
public final class MagnetClientEvents {

    @SubscribeEvent(priority = EventPriority.LOW)
    public static void onScreenInit(ScreenEvent.Init.Post event) {
        Screen screen = event.getScreen();
        if (screen instanceof InventoryScreen || screen instanceof CreativeModeInventoryScreen || screen instanceof ICuriosScreen) {
            AbstractContainerScreen<?> gui = (AbstractContainerScreen<?>) screen;
            boolean isCreative = screen instanceof CreativeModeInventoryScreen;
            event.addListener(MagnetRingInventoryButton.getInstance(gui, isCreative));
        }
    }

    private MagnetClientEvents() {}
}
