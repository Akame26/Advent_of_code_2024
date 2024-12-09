package resolution;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Day2 {
    private final List<List<Integer>> reports;

    public Day2(List<List<Integer>> reports){
        this.reports = new ArrayList<>(reports);
    }

    private List<List<Integer>> getTotalSafeReports(){
        List<List<Integer>> totalSafeReports = new ArrayList<>();
//        AtomicInteger totalSafeReports = new AtomicInteger();
        this.reports.forEach(report ->{
            String orderType = "";
//            System.out.println("Report " + report.toString());

            for(int i = 0; i< report.size()-1;i++){
                if(i == 0){
                    orderType = (report.get(i) < report.get(i+1)) ? "+" : "-";
                }
                //Case where the order from the two numbers is different from the initial order
                if(!((report.get(i) < report.get(i+1)) ? "+" : "-").equals(orderType)){
                    break;
                }


                int opeTotal = Math.abs(report.get(i)-report.get(i+1));
//                System.out.println("Operation Total: " + opeTotal);
                //Case where the diff between the two number excceds the desired diff
                if(opeTotal < 1 || opeTotal > 3){
                    break;
                }

                if(i == report.size()-2){
//                        System.out.println("Incrementing totalSafeReports");
                    totalSafeReports.add(report);
                }
            }
        });
        return totalSafeReports;
    }

    private int getTotalSafeOneMoreReports(List<List<Integer>>remainingCases){
        AtomicInteger totalRemainingSafeReports = new AtomicInteger();
        AtomicInteger oneMoreChance = new AtomicInteger(0);
        remainingCases.forEach((report)->{
            System.out.println("\nInitial Report "+report);
            //Case 1 variations
            Map<String,Integer> variationTotal = new HashMap<>(){{
                put("",0);
                put("+",0);
                put("-",0);
            }};
            Map<String,Integer> variationCauseValue = new HashMap<>(){{
                put("",0);
                put("+",0);
                put("-",0);
            }};

            for (int i=0;i<report.size()-1;i++){
                String variation = report.get(i) < report.get(i+1) ? "+": (report.get(i) > report.get(i+1)) ? "-": "";
                variationTotal.put(variation,variationTotal.get(variation)+1);
                variationCauseValue.put(variation,report.get(i));
            }

            int variationmaxType =  variationTotal.values().stream().mapToInt(v->v).max().orElse(0);
            System.out.println("Max number of variation: "+variationmaxType);
            if(variationmaxType != report.size()-1){
                oneMoreChance.getAndIncrement();
                if( ((report.size()-1) - variationmaxType ) == 1){
                    List<String> variationAux =  variationTotal.entrySet().stream().filter(entry -> entry.getValue() == variationmaxType ).map(Map.Entry::getKey).toList();
                    int levelToRemove = 0;
                    if("".equals(variationAux.get(0))){
                        oneMoreChance.getAndIncrement();
                    } else {
                        System.out.println("Predominent Order: "+variationAux.get(0));
                        switch (variationAux.get(0)){
                            case"+":{
                                levelToRemove = variationCauseValue.get(variationTotal.get("") == 0 ? "-": "");
                                break;
                            }
                            case "-":{
                                levelToRemove = variationCauseValue.get(variationTotal.get("") == 0 ? "+": "");
                                break;
                            }
                        }
                        System.out.println("Remove level number: "+levelToRemove);
                        //Get position where it does make the inflection
                        int levelPos = 0;
                        if(Collections.frequency(report,levelToRemove) < 2){
                            levelPos = report.indexOf(levelToRemove);
                        } else {
                            levelPos = (report.lastIndexOf(levelToRemove) == report.size()-1) ? report.lastIndexOf(levelToRemove) : report.indexOf(levelToRemove);
                        }
                        System.out.println("Position: "+levelPos);
                        report.remove(levelPos);
//                        report.remove((levelPos == 0) ? Integer.valueOf(levelToRemove) : report.get(levelPos+1));
//                        report.remove(Integer.valueOf(levelToRemove) );
                    }
                } else {
                    oneMoreChance.getAndIncrement();
                }
            }

            if(oneMoreChance.get() != 2){
                //Case 2 Difference between 2 numbers
                System.out.println("");

            }
            System.out.println("Final Report " + report);
            oneMoreChance.set(0);
        });

        return totalRemainingSafeReports.get();
    }

    public void resolutionDay2(){
        System.out.println("Resolution of part 1");
        List<List<Integer>> totalSafeResults = this.getTotalSafeReports();
        System.out.println("Total number of reports: "+totalSafeResults.size());

        System.out.println("\nResolution of part 2");
        List<List<Integer>> remainingCases = this.reports.stream().filter(report -> !totalSafeResults.contains(report)).toList();
        System.out.println("Total number of reports: " + (this.getTotalSafeOneMoreReports(remainingCases) + totalSafeResults.size()));
//        System.out.println("Total number of reports: " + this.getTotalSafeOneMoreReports(remainingCases));
    }

}
