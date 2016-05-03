package com.VDK.StarCraftBot;

import com.VDK.AI.Commander;
import bwapi.*;
import bwta.BWTA;
import com.VDK.AI.EvolutionPlanner;
import com.VDK.AI.Plan;

import java.util.*;
import java.io.*;

public class AIModule extends DefaultBWListener {

    private final Mirror mirror = new Mirror();
    private Game game;
    private Player player;
    private final GameState gameState = new GameState();
    private final CommandManager cmdMan = new CommandManager();
    // AI things
    private Commander commander;
    // Evolution thing
    private EvolutionPlanner evolutionPlanner;
    public void run() {
        mirror.getModule().setEventListener(this);
        mirror.startGame();
    }

    public AIModule() {
        // AI stuffs:
        commander = new Commander(this.game, this.player);
        // Make the manager know of the game
        for (IGameObserver manager : cmdMan.getAllManager()) {
            gameState.addSub(manager);
        }
        gameState.addSub(commander);
        // Evolution stuffs:
        try {
            FileInputStream fileInStream = new FileInputStream("data\\Evolution Planner.obj");
            ObjectInputStream objInStream = new ObjectInputStream(fileInStream);
            evolutionPlanner = (EvolutionPlanner) objInStream.readObject();
            objInStream.close();
        } catch(Exception ex){
            ex.printStackTrace();
            evolutionPlanner = new EvolutionPlanner();
        }
        // Add the brain to the commander:
        commander.setPlan(evolutionPlanner.getPlan0());
    }

    @Override
    public void onStart() {
        game = mirror.getGame();
        game.enableFlag(0);
        game.enableFlag(1);
        game.sendText("black sheep wall");
        game.setGUI(false);
        game.setLocalSpeed(0);
        player = game.self();

        // Use BWTA to analyze map
        System.out.println("Analyzing map...");
        BWTA.readMap();
        BWTA.analyze();
        System.out.println("Map data ready");
    }

    @Override
    public void onEnd(boolean isWinner)
    {
        // Result
        int ourScore = player.getUnitScore() + player.getKillScore() + player.getBuildingScore() + player.getRazingScore() + player.gatheredMinerals() + player.gatheredGas();
        Player enemy = game.enemy();
        int theirScore = enemy.getUnitScore() + enemy.getKillScore() + enemy.getBuildingScore() + enemy.getRazingScore() + enemy.gatheredMinerals() + enemy.gatheredGas();
        double efficiency = (double)ourScore/(double)theirScore;
        // Writing out the result to help growing the AI
        try (Writer writer = new FileWriter("result\\War Records.txt", true))
        {
            // writer.write("Our score is: " + ourScore + "\n");
            // writer.write("Their score is: " + theirScore + "\n");
            // This is the crucial number: we wan't the robot to win convincingly
            // Not just trying to buy time to get higher score
            writer.write("Efficiency is: " + efficiency + "\n\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Dealing with the AI's learning: make it learn from failure!
        evolutionPlanner.reflect(commander.getPlan(), efficiency);
        // Writing the progress to a file:
        writeToFile("data\\Evolution Planner.txt" ,evolutionPlanner.toString());
        // Writing out the plans:
        if(evolutionPlanner.getState() == 1)
            writeToFile("data\\Plan 2.txt", evolutionPlanner.getPlan2().toString());
        if(evolutionPlanner.getState() == 2)
            writeToFile("data\\Plan 1.txt", evolutionPlanner.getPlan1().toString());

        // Saving the evolution object (it's so much easier than using plain text):
        try
        {
            FileOutputStream fout = new FileOutputStream("data\\Evolution Planner.obj");
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(evolutionPlanner);
            oos.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    // Overwriting a file:
    private void writeToFile(String path, String content)
    {
        try(Writer writer = new FileWriter(path, false))
        {
            writer.write(content);
        }
        catch(IOException e) { e.printStackTrace(); }
    }
    @Override
    public void onSendText(String text)
    {
        game.sendText(text);
        cmdMan.addCommand(text);
    }
    @Override
    public void onFrame() {
        if(game.getFrameCount() % 100  == 0)
        {
            gameState.notify(game, player);
            ArrayList<String> aiCommands = commander.returnCommands();
            cmdMan.addCommands(aiCommands);
            cmdMan.executeCommands();
        }
    }
}