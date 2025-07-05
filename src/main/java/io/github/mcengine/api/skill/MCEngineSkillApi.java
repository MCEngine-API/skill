package io.github.mcengine.api.skill;

import org.bukkit.NamespacedKey;
import org.bukkit.plugin.Plugin;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * API class for skill-related item creation in MCENGINE.
 */
public class MCEngineSkillApi {

    /**
     * Sets only the NamespacedKey "mcengine:skill_id" with the given skill ID in the item's persistent data container.
     * Does not modify any other metadata (such as display name or lore).
     *
     * @param plugin  The plugin instance. Must not be null. Used to namespace the persistent key.
     * @param item    The base {@link ItemStack} to modify. Must not be null.
     * @param skillId The skill ID to attach. If null or empty, skill ID will not be attached.
     * @return A new {@link ItemStack} with the NamespacedKey set, or the original clone if no skillId is given.
     * @throws IllegalArgumentException if {@code item} or {@code plugin} is null.
     */
    @NotNull
    public static ItemStack createItem(@NotNull Plugin plugin, @NotNull ItemStack item, @Nullable String skillId) {
        if (plugin == null) throw new IllegalArgumentException("Plugin cannot be null!");
        if (item == null) throw new IllegalArgumentException("ItemStack cannot be null!");

        NamespacedKey skillIdKey = new NamespacedKey("mcengine", "skill_id");

        ItemStack result = item.clone();
        ItemMeta meta = result.getItemMeta();

        if (meta != null && skillId != null && !skillId.isEmpty()) {
            try {
                meta.getPersistentDataContainer().set(skillIdKey, PersistentDataType.STRING, skillId);
            } catch (NoClassDefFoundError | NoSuchMethodError e) {
                // Do nothing for legacy servers
            }
            result.setItemMeta(meta);
        }

        return result;
    }

    /**
     * Checks if the given {@link ItemStack} has a skill ID set,
     * either via persistent data or item lore (legacy support).
     *
     * @param plugin The plugin instance. Must not be null. Used to namespace the persistent key.
     * @param item   The {@link ItemStack} to check. Can be null.
     * @return true if the skill ID is present; false otherwise.
     */
    public static boolean isSkillSet(@NotNull Plugin plugin, @Nullable ItemStack item) {
        if (plugin == null || item == null) return false;
        ItemMeta meta = item.getItemMeta();
        if (meta == null) return false;

        NamespacedKey skillIdKey = new NamespacedKey("mcengine", "skill_id");

        // Try persistent data (modern)
        try {
            if (meta.getPersistentDataContainer().has(skillIdKey, PersistentDataType.STRING)) {
                return true;
            }
        } catch (NoClassDefFoundError | NoSuchMethodError ignored) {}

        // Fallback: check legacy lore
        List<String> lore = meta.getLore();
        if (lore != null) {
            for (String line : lore) {
                if (line != null && line.startsWith("§7Skill: §e")) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Retrieves the skill ID from the given {@link ItemStack},
     * from persistent data if available, or from lore if not (legacy support).
     *
     * @param plugin The plugin instance. Must not be null. Used to namespace the persistent key.
     * @param item   The {@link ItemStack} to check. Can be null.
     * @return The skill ID, or null if not found.
     */
    @Nullable
    public static String getItemSkillId(@NotNull Plugin plugin, @Nullable ItemStack item) {
        if (plugin == null || item == null) return null;
        ItemMeta meta = item.getItemMeta();
        if (meta == null) return null;

        NamespacedKey skillIdKey = new NamespacedKey("mcengine", "skill_id");

        // Try persistent data (modern)
        try {
            if (meta.getPersistentDataContainer().has(skillIdKey, PersistentDataType.STRING)) {
                return meta.getPersistentDataContainer().get(skillIdKey, PersistentDataType.STRING);
            }
        } catch (NoClassDefFoundError | NoSuchMethodError ignored) {}

        // Fallback: check legacy lore
        List<String> lore = meta.getLore();
        if (lore != null) {
            for (String line : lore) {
                if (line != null && line.startsWith("§7Skill: §e")) {
                    // Extract the skillId
                    String raw = line.substring("§7Skill: §e".length());
                    int legacyIdx = raw.indexOf(" §c[Legacy]");
                    if (legacyIdx != -1) {
                        raw = raw.substring(0, legacyIdx);
                    }
                    return raw.trim();
                }
            }
        }
        return null;
    }
}
