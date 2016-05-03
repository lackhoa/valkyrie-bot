package com.VDK.AI;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

/**
 * Made up from pairs of Signal and Reaction.</n>
 * Signal: The information from the game.</n>
 * Reaction: A command made based on the signal.
 */
public class Plan implements Serializable {
    private HashMap<String, String> content;
    public HashMap<String, String> getContent() { return content; }
    public Set<String> getSignalList() { return content.keySet(); }
    public Plan() { content = new HashMap<>(); }

    /**
     * Create a new plan with the same content as the initial
     * @param plan The initial plan
     */
    public Plan(Plan plan) { content = new HashMap<String, String>(plan.content); }
    /**
     * Add a pair of signal and reaction to the plan (or replace the reaction if the signal already exist)
     * @param signal
     * @param reaction
     */
    public void update(String signal, String reaction)
    {
        content.put(signal, reaction);
    }
    public boolean hasReactionTo(String signal) { return content.containsKey(signal); }
    /**
     * Getting a reaction from a signal
     * @param signal
     * @return The corresponding signal
     */
    public String getReaction(String signal) { return content.get(signal); }
    @Override
    public String toString()
    {
        StringBuffer result = new StringBuffer();
        for(String reaction : content.keySet())
        {
            result.append("\"" + reaction + "\"" + " : " + "\"" + content.get(reaction) + "\"" + "\n");
        }
        return result.toString();
    }
}
