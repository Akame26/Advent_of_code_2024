package main;

import resolution.Day1;
import resolution.Day2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AdventOfCode {

    public static void main(String[] args) {
        System.out.println("Advent of code - Day 1");
        //Day 1 Resolution
        List<Integer> leftSide = new ArrayList<>();
        List<Integer> rightSide = new ArrayList<>();
        FileReader fr;
        BufferedReader br;
        try {
            fr = new FileReader("inputs\\input_day_1.txt");
            br = new BufferedReader(fr);
            String line = br.readLine();
            while(line != null){
                String[] values = line.split(" {3}");
                leftSide.add(Integer.valueOf(values[0]));
                rightSide.add(Integer.valueOf(values[1]));
                line = br.readLine();
            }
            br.close();
            fr.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Day1 day1Resolution = new Day1(leftSide,rightSide);
        day1Resolution.resolutionDay1();

        //Day 2 Resolution
        System.out.println("Advent of code - Day 2");
        List<List<Integer>> reports = new ArrayList<>();
        try{
            fr = new FileReader("inputs\\input_day_2.txt");
            br = new BufferedReader(fr);
            String line = br.readLine();
            while(line != null){
                String[] values = line.split(" ");
                int[] valuesConverted = Arrays.stream(values).mapToInt(Integer::parseInt).toArray();
                reports.add(new ArrayList<>(){{
                    addAll(Arrays.stream(valuesConverted).boxed().toList());
                }});
                line = br.readLine();
            }
            br.close();
            fr.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Day2 day2Resolution = new Day2(reports);
        day2Resolution.resolutionDay2();

        //Day 3
        
    }

}