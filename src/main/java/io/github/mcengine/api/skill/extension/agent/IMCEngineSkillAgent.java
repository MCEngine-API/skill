package io.github.mcengine.api.skill.extension.agent;

import org.bukkit.plugin.Plugin;

/**
 * Represents a Skill-based Agent module that can be dynamically loaded into the MCEngine.
 * <p>
 * Implement this interface to integrate skill-oriented agents into the system.
 */
public interface IMCEngineSkillAgent {

    /**
     * Called when the Skill Agent is loaded by the engine.
     *
     * @param plugin The plugin instance providing context.
     */
    void onLoad(Plugin plugin);

    /**
     * Called when the Skill Agent is unloaded or disabled by the engine.
     *
     * @param plugin The plugin instance providing context.
     */
    void onDisload(Plugin plugin);

    /**
     * Sets a unique ID for this Skill Agent instance.
     *
     * @param id The unique ID assigned by the engine.
     */
    void setId(String id);
}
