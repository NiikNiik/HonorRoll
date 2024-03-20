//Niko AJani
// 2/6/2024
//This project will tell the user the threshold of making the honors society, and all the
//students who meet that threshold. The students will be read from a text file that the user
// provides.

package com.example.projectonetwo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

class Student {
    private String name;
    private int creditHours;
    private int qualityPoints;

    public Student(String name, int creditHours, int qualityPoints) {
        this.name = name;
        this.creditHours = creditHours;
        this.qualityPoints = qualityPoints;
    }

    public double gpa() {
        return (double) qualityPoints / creditHours;
    }

    public boolean eligibleForHonorSociety() {
        return gpa() > Student.getGpaThreshold();
    }

    public String toString() {
        return "Name: " + name + ", GPA: " + gpa();
    }

    public static void setGpaThreshold(double threshold) {
        GpaThresholdHolder.setGpaThreshold(threshold);
    }

    public static double getGpaThreshold() {
        return GpaThresholdHolder.getGpaThreshold();
    }
}

class Undergraduate extends Student {
    private String gradeLevel;

    public Undergraduate(String name, int creditHours, int qualityPoints, String year) {
        super(name, creditHours, qualityPoints);
        this.gradeLevel = year;
    }

    @Override
    public boolean eligibleForHonorSociety() {
        return super.eligibleForHonorSociety() && (gradeLevel.equals("Junior") || gradeLevel.equals("Senior"));
    }

    @Override
    public String toString() {
        return super.toString() + ", Year: " + gradeLevel;
    }
}

class Graduate extends Student {
    private String degree;

    public Graduate(String name, int creditHours, int qualityPoints, String degree) {
        super(name, creditHours, qualityPoints);
        this.degree = degree;
    }

    @Override
    public boolean eligibleForHonorSociety() {
        return super.eligibleForHonorSociety() && degree.equals("Masters");
    }

    @Override
    public String toString() {
        return super.toString() + ", Degree: " + degree;
    }
}

class GpaThresholdHolder {
    private static double gpaThreshold;

    public static double getGpaThreshold() {
        return gpaThreshold;
    }

    public static void setGpaThreshold(double threshold) {
        gpaThreshold = threshold;
    }
}

public class Project2 {
    public static void main(String[] args) {
        ArrayList<Student> students = new ArrayList<>();

        try {
            Scanner scanner = new Scanner(new File("students.txt"));
            double totalGpa = 0;
            int count = 0;

            while (scanner.hasNextLine()) {
                //read each line of code
                String[] studentInfo = scanner.nextLine().split(" ");
                //get the name from student array
                String name = studentInfo[0];
                // get the credit hours
                int creditHours = Integer.parseInt(studentInfo[1]);
                //get the quality points
                int qualityPoints = Integer.parseInt(studentInfo[2]);

                if (studentInfo[3].equals("Junior") || studentInfo[3].equals("Senior")) {
                    Undergraduate undergrad = new Undergraduate(name, creditHours, qualityPoints, studentInfo[3]);
                    students.add(undergrad);
                } else {
                    Graduate grad = new Graduate(name, creditHours, qualityPoints, studentInfo[3]);
                    students.add(grad);
                }

                totalGpa += students.get(count).gpa();
                count++;
            }

            //Once all the student data is read in, the threshold for honor society membership should then be
            //set to the midpoint of the average gpa and the highest possible gpa of 4.0
            double averageGpa = totalGpa / count;
            Student.setGpaThreshold((4.0 + averageGpa) / 2.0);

            System.out.println("Threshold for Honor Society: " + Student.getGpaThreshold());

            System.out.println("Students eligible for Honor Society");

            for (Student student : students) {
                if (student.eligibleForHonorSociety()) {
                    System.out.println(student.toString());
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            e.printStackTrace();
        }
    }
}