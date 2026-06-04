package net.keystoneofoblivion;

import net.neoforged.neoforge.common.ModConfigSpec;

public final class Config {
    public static final ModConfigSpec SPEC;
    public static final ModConfigSpec.IntValue SOFTCAP;
    public static final ModConfigSpec.IntValue HARDCAP;

    static {
        ModConfigSpec.Builder b = new ModConfigSpec.Builder();
        b.push("OblivionKeystone");
        SOFTCAP = b.comment(
                        "Soft cap for Keystone of the Oblivion. When it's reached, the list view seen in its Ctrl tooltip",
                        "will be fixed at this amount of items and become chaotic and unreadable. Required since monitors",
                        "are not infinitely large these days.")
                .defineInRange("softcap", 25, 1, Integer.MAX_VALUE);
        HARDCAP = b.comment(
                        "Hard cap for Keystone of the Oblivion. When it's reached, you will no longer be able to add new",
                        "items to its list via crafting. Required to prevent potential performance issues with",
                        "ridiculously large lists.")
                .defineInRange("hardcap", 250, 1, Integer.MAX_VALUE);
        b.pop();
        SPEC = b.build();
    }

    private Config() {}
}
