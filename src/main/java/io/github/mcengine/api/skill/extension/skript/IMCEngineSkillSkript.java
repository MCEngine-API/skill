package io.github.mcengine.api.skill.extension.skript;

import org.bukkit.plugin.Plugin;

/**
 * Represents a Skill-based Skript module that can be dynamically loaded into the MCEngine.
 * <p>
 * Implement this interface to integrate scripted content centered around skill systems.
 */
public interface IMCEngineSkillSkript {

    /**
     * Called when the Skill Skript module is loaded by the engine.
     *
     * @param plugin The plugin instance providing context.
     */
    void onLoad(Plugin plugin);

    /**
     * Called when the Skill Skript module is unloaded or disabled by the engine.
     *
     * @param plugin The plugin instance providing context.
     */
    void onDisload(Plugin plugin);

    /**
     * Sets a unique ID for this Skill Skript instance.
     *
     * @param id The unique ID assigned by the engine.
     */
    void setId(String id);
}
