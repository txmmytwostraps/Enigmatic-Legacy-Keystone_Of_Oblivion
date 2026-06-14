# Keystone of the Oblivion — Unofficial 1.21.1 (NeoForge) Port

This is an **unofficial port** of the **Keystone of the Oblivion** from **Enigmatic Legacy** to Minecraft **1.21.1 (NeoForge)**.

---

## Ownership & Attribution

I do **not** own Enigmatic Legacy or the Keystone of the Oblivion, nor any of the original code, assets, concepts, or lore. All original work belongs to its creator, **Aizistral** (Aizistral Studios).

- **Original mod:** Enigmatic Legacy, by Aizistral
- **Original source:** https://github.com/Aizistral-Studios/Enigmatic-Legacy
- **Official download (CurseForge):** https://www.curseforge.com/minecraft/mc-mods/enigmatic-legacy
- **Creator's website:** https://aizistral.com

This is a fan-made, unofficial port. It is **not** endorsed by or officially affiliated with Aizistral. If you want the official, up-to-date mod, please get it from the links above and support the original creator.

This port is published **with the creator's permission** and in accordance with the **Enigmatic Legacy License, II Edition** (Section IV.3). See [`LICENSE.md`](LICENSE.md).

---

## What This Is

A port of the **Keystone of the Oblivion** to Minecraft 1.21.1 / NeoForge, recreating the item's original behavior, plus a few small additions (listed below).

It also ports three of Enigmatic Legacy's curio rings — the **Magnetic Ring**, its upgrade the **Dislocation Ring**, and the **Iron Ring** (their crafting base). Because these are Curios items, the mod now **requires the [Curios API](https://www.curseforge.com/minecraft/mc-mods/curios)**.

---

## Modifications From the Original

As required by **Section IV.3** of the license, the following modifications have been made relative to the original Enigmatic Legacy source:

- Ported from Minecraft **1.20.1 (Forge)** to Minecraft **1.21.1 (NeoForge)**, including the NeoForge API, registry, data components, and networking/packet changes.
- **Added:** active-state enchantment glint on the item.
- **Added:** an on-screen popup when switching modes.
- **Added:** a configurable hard cap (default value: **250**).
- **Fixed (1.0.1):** shift+right-click toggle now works while flying. The original used `isCrouching()` which returns false in creative flight; this port uses `isShiftKeyDown()` so the toggle activates anywhere shift is held.
- **Added (1.0.2):** a crafting recipe — 8 obsidian around 1 ender pearl (3×3 shaped) → 1 keystone. The original mod had no crafting recipe (loot-only). Added because survival players need a reliable way to obtain it.
- **Added (1.0.2):** the keystone is also added to Nether Fortress and Bastion Remnant loot chests (~5% chance per chest), preserving the original's "found in Nether dungeons" feel.
- **Added (1.0.3):** the **Magnetic Ring**, **Dislocation Ring**, and **Iron Ring**, ported from Enigmatic Legacy as Curios ring items. The Magnetic Ring attracts nearby dropped items toward you (8-block radius); the Dislocation Ring — its upgrade — teleports them straight into your inventory (16-block radius); the Iron Ring grants +1 armor and is the crafting base for the Magnetic Ring. Holding Shift suppresses the pull, and an inventory magnet on/off button toggles it, both as in the original. Each ring's pull range is configurable.
- **Added (1.0.3):** crafting recipes for the three rings (Iron Ring from iron ingots + nuggets; Magnetic Ring from the Iron Ring; Dislocation Ring from the Magnetic Ring), matching the unofficial 1.21 port's recipes.
- **New dependency (1.0.3):** the **Curios API** is now required, since the rings are curio items. The keystone itself is unchanged and works as before.

> Any further changes, fixes, or deviations from the original will be documented in this section as they are made.

---

## License

This port is licensed under the **Enigmatic Legacy License, II Edition** — the same license as the original work — as required by Section IV.3 of that license. The full source is publicly available and open for redistribution and modification under those terms. See [`LICENSE.md`](LICENSE.md) for the full text.

This project is distributed on a **non-commercial** basis. It is not paywalled, ad-walled, or monetized in any way.
