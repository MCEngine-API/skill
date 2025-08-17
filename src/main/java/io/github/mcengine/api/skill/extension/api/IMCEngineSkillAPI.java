package io.github.mcengine.api.skill.extension.api;

import org.bukkit.plugin.Plugin;

/**
 * Represents a Skill-based API module that can be dynamically loaded into the MCEngine.
 * <p>
 * Implement this interface to expose skill-related APIs to the engine and other modules.
 */
public interface IMCEngineSkillAPI {

    /**
     * Called when the Skill API is loaded by the engine.
     *
     * @param plugin The plugin instance providing context.
     */
    void onLoad(Plugin plugin);

    /**
     * Called when the Skill API is unloaded or disabled by the engine.
     *
     * @param plugin The plugin instance providing context.
     */
    void onDisload(Plugin plugin);

    /**
     * Sets a unique ID for this Skill API instance.
     *
     * @param id The unique ID assigned by the engine.
     */
    void setId(String id);
}
