package io.github.mcengine.api.skill.extension.library;

import org.bukkit.plugin.Plugin;

/**
 * Represents a Skill-based Library module that can be dynamically loaded into the MCEngine.
 * <p>
 * Implement this interface to provide shared services and utilities for skill features.
 */
public interface IMCEngineSkillLibrary {

    /**
     * Called when the Skill Library is loaded by the engine.
     *
     * @param plugin The plugin instance providing context.
     */
    void onLoad(Plugin plugin);

    /**
     * Called when the Skill Library is unloaded or disabled by the engine.
     *
     * @param plugin The plugin instance providing context.
     */
    void onDisload(Plugin plugin);

    /**
     * Sets a unique ID for this Skill Library instance.
     *
     * @param id The unique ID assigned by the engine.
     */
    void setId(String id);
}
