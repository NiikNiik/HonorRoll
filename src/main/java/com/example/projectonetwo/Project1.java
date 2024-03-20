//Name: Niko Ajani
//Project 1: Basketball PLayer
//1/23/2024
//This program lets the user input a player's name, age and height. The program will then compute
// the average age of all the players and then the tallest player who is younger or equal to the
//average age.

package com.example.projectonetwo;

import java.util.ArrayList;
import java.util.Scanner;

class Height {
    //initialize the feet and inches separately
    private final int feet;
    private final int inches;

    //constructor for height
    public Height(int feet, int inches) {
        this.feet = feet;
        this.inches = inches;
    }

    //converts the feet into inches
    public int toInches() {
        return feet * 12 + inches;
    }

    @Override
    public String toString() {
        int finalInches = toInches() % 12;
        return feet + "'" + finalInches + "\"";
    }
}

class Player {
    //initialize the name, height and age variables
    private final String name;
    private final Height height;
    private final int age;

    //constructor for Player
    public Player(String name, Height height, int age) {
        this.name = name;
        this.height = height;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public Height getHeight() {
        return height;
    }

    public int getAge() {
        return age;
    }

    //converts name, height and age to strings and returns them
    @Override
    public String toString() {
        return "Name: " + name + ", Height: " + height.toString() + ", Age: " + age;
    }
}

public class Project1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Player> players = new ArrayList<>();

        //initialize total age, so that the average age can be computed later
        int totalAge = 0;

        //prompts user to enter number of players
        System.out.print("Enter the number of players: ");
        int numPlayers = scanner.nextInt();

        // lets user input player information as many times as the
        // user specified earlier
        for (int i = 0; i < numPlayers; i++) {
            System.out.println("Enter details for Player " + (i + 1));
            System.out.print("Name: ");
            String name = scanner.next();

            System.out.print("Height (feet): ");
            int feet = scanner.nextInt();

            System.out.print("Height (inches): ");
            int inches = scanner.nextInt();

            System.out.print("Age: ");
            int age = scanner.nextInt();

            Height playerHeight = new Height(feet, inches);
            Player player = new Player(name, playerHeight, age);

            players.add(player);
            totalAge += age;
        }

        // Calculates the average age among the players
        double averageAge = (double) totalAge / numPlayers;
        System.out.println("Average Age of Players: " + averageAge);

        // Finds tje tallest player whose age is less than or equal to the average age
        Player tallestPlayer = null;
        int maxHeight = 0;

        //Iterates through players
        for (Player player : players) {
            if (player.getHeight().toInches() > maxHeight && player.getAge() <= averageAge) {
                maxHeight = player.getHeight().toInches();
                tallestPlayer = player;
            }
        }

        //
        if (tallestPlayer != null) {
            System.out.println("Tallest Player whose age is less than or equal to average age:");
            System.out.println(tallestPlayer.toString());
        } else {
            System.out.println("No player meets the criteria.");
        }
    }
}
