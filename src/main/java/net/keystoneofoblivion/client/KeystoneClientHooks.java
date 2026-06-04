package net.keystoneofoblivion.client;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;

public final class KeystoneClientHooks {
    public static void showModeOverlay(Component text) {
        Minecraft mc = Minecraft.getInstance();
        if (mc != null && mc.gui != null) {
            mc.gui.setOverlayMessage(text, false);
        }
    }

    private KeystoneClientHooks() {}
}
