package com.VDK.AI;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Set;

/**
 * Taking care of the AI's learning
 */
public class EvolutionPlanner implements Serializable {
    private byte state;
    // There are 2 states: 1 and 2
    // State 1: Creating another new plan based on plan 0: plan 2
    // State 2: and compare the 2 plan: 1 and 2 to see which is better, then set plan 0 to the better plan
    public byte getState() { return state; }
    // Increments to the next state of the process (1 -> 2, 2 -> 1)
    private void toNextState()
    {
        if (state == 1)
            state = 2;
        else
            state = 1;
    }

    private Plan plan0;
    public Plan getPlan0() { return plan0; }
    public void setPlan0(Plan plan) { plan0 = plan; }
    private int plan0Score;
    public void setPlan0Score(int score) { plan0Score = score; }

    private Plan plan1;
    public Plan getPlan1() { return plan1; }
    public void setPlan1(Plan plan) { plan1 = plan; }
    private double plan1Efficiency;
    public void setPlan1Efficiency(double score) { plan2Efficiency = score; }

    private Plan plan2;
    public Plan getPlan2() {return plan2; }
    public void setPlan2(Plan plan) { plan2 = plan; }
    private double plan2Efficiency;
    public void setPlan2Efficiency(double score) { plan2Efficiency = score; }

    public EvolutionPlanner()
    {
        plan1 = new Plan();
        plan2 = new Plan();
        plan0 = new Plan();
        state = 1;
        plan1Efficiency = 0;
        plan2Efficiency = 0;
    }

    /**
     * Taking the result and then make some change to the current plan0 when needed
     * @param efficiency The efficiency score of the latest match
     * @param plan The new plan
     */
    public void reflect(Plan plan, double efficiency)
    {
        if (this.state == 1)
        {
            plan1Efficiency = efficiency;
            plan1 = plan;
        }

        if (this.state == 2)
        {
            // This is the final state, so we gather the result and then get the best plan
            plan2Efficiency = efficiency;
            plan2 = plan;
            if(this.plan2Efficiency > this.plan1Efficiency)
                // Beware! There are errors as well
                plan0 = modify(plan2);
            else
                // Beware! There are errors as well
                plan0 = modify(plan1);
        }
        toNextState();
    }
    // Think of it as a DNA's mistake or something
    // It's really needed for evolution. In fact, this is the most important method
    private Plan modify(Plan plan)
    {
        Plan newPlan = new Plan(plan);
        // For every 100 signals, there is an error:
        String[] signalList;
        int size = newPlan.getSignalList().size();
        signalList = newPlan.getSignalList().toArray(new String[size]);
        int maxValue = signalList.length - 1;
        int top = 100;
        while (top < maxValue)
        {
            Random random = new Random();
            int errorPosition = random.nextInt(20);
            String theSignalToChange = signalList[errorPosition];
            newPlan.update(theSignalToChange, makeRandomCommand()); // Just put a random command in and get over it!
            top += 100;
        }
        return newPlan;
    }
    private String makeRandomCommand()
    {
        ArrayList<String> possibleCommand = new ArrayList<String>();
        possibleCommand.add("t.s");
        possibleCommand.add("b.sd");
        possibleCommand.add("b.ba");
        possibleCommand.add("t.ma");
        possibleCommand.add(""); // Doing nothing is a choice, too!
        Random random = new Random();
        int index = random.nextInt(possibleCommand.size() - 1);
        return possibleCommand.get(index);
    }
    @Override
    public String toString()
    {
        return new StringBuffer("State: " + this.state + "\n")
                .append("Plan 1 efficiency score: " + this.plan1Efficiency + "\n")
                .append("Plan 2 efficiency score:" + this.plan2Efficiency + "\n").toString();
    }
}
