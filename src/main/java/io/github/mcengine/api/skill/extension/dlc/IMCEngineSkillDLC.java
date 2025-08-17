package io.github.mcengine.api.skill.extension.dlc;

import org.bukkit.plugin.Plugin;

/**
 * Represents a Skill-based DLC module that can be dynamically loaded into the MCEngine.
 * <p>
 * Implement this interface to integrate downloadable content focused on skill mechanics.
 */
public interface IMCEngineSkillDLC {

    /**
     * Called when the Skill DLC is loaded by the engine.
     *
     * @param plugin The plugin instance providing context.
     */
    void onLoad(Plugin plugin);

    /**
     * Called when the Skill DLC is unloaded or disabled by the engine.
     *
     * @param plugin The plugin instance providing context.
     */
    void onDisload(Plugin plugin);

    /**
     * Sets a unique ID for this Skill DLC instance.
     *
     * @param id The unique ID assigned by the engine.
     */
    void setId(String id);
}
