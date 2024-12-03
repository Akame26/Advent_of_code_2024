package main;

import resolution.Day1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AdventOfCode {

    public static void main(String[] args) {
        System.out.println("Advent of code - Day 1");
        //Example
//        List<Integer> leftSide = new ArrayList<>(){{
//            add(3);
//            add(4);
//            add(2);
//            add(1);
//            add(3);
//            add(3);
//        }};
//        List<Integer> rightSide = new ArrayList<>(){{
//            add(4);
//            add(3);
//            add(5);
//            add(3);
//            add(9);
//            add(3);
//        }};
        List<Integer> leftSide = new ArrayList<>();
        List<Integer> rightSide = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader("inputs\\input_day_1.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            while(line != null){
                String[] values = line.split(" {3}");
                leftSide.add(Integer.valueOf(values[0]));
                rightSide.add(Integer.valueOf(values[1]));
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
            fileReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Day1 day1Resolution = new Day1(leftSide,rightSide);
        Integer totalSum = day1Resolution.getTotalLocationIDDiff();

        System.out.println("Total sum: "+totalSum);

    }

}