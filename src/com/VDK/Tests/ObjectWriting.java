package com.VDK.Tests;

import com.VDK.AI.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ObjectWriting {
    public static void main(String[] args){
        // This is the object we need to read and write:
        EvolutionPlanner evolutionPlanner;

        evolutionPlanner = new EvolutionPlanner();
        evolutionPlanner.getPlan0().update("a", "1");
        evolutionPlanner.getPlan0().update("b", "2 ");
        evolutionPlanner.getPlan0().update("c", "3");
        evolutionPlanner.getPlan0().update("d", "4");
        // Write the object
        try {
            FileOutputStream fout = new FileOutputStream("Tests\\Evolution.obj");
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(evolutionPlanner);
            oos.close();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        evolutionPlanner = null;
        // Read the object
        try {
            FileInputStream fileInStream = new FileInputStream("Tests\\Evolution.obj");
            ObjectInputStream objInStream = new ObjectInputStream(fileInStream);
            evolutionPlanner = (EvolutionPlanner) objInStream.readObject();
            objInStream.close();
        } catch(Exception ex){
            ex.printStackTrace();
        }
        System.out.print(evolutionPlanner);
    }
}
