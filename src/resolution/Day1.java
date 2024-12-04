package resolution;

import java.util.*;
import java.util.stream.Collectors;


public class Day1 {
    private final List<Integer> leftSideList;
    private final List<Integer> rightSideList;

    public Day1(List<Integer> leftSideList,List<Integer> rightSideList) {
        this.leftSideList = new ArrayList<>(leftSideList);
        this.rightSideList = new ArrayList<>(rightSideList);
    }

    /**
     * Description: Get the difference for all the locationIDs of each side
     *              Makes the difference for each of the smallest locationID for the left side with the smallest number of the right side
     * @return List<Integer>
     */
    public List<Integer> diffListValues(){
        List<Integer> locationIDsDiff = new ArrayList<>();

        //Sort each list to get the locationIDs in ascending order
        Collections.sort(this.leftSideList);
        Collections.sort(this.rightSideList);

        //Get the difference for each of smallest number
        for(int position = 0; position < this.leftSideList.size();position++){
            int smallestLeftLocationID = this.leftSideList.get(position);
            int smallestRightLocationID = this.rightSideList.get(position);
            int diffSmallestLocationID = Math.abs(smallestLeftLocationID-smallestRightLocationID);
            locationIDsDiff.add(diffSmallestLocationID);
        }

        return locationIDsDiff;
    }

    public List<Integer> simmListValues(){
        List<Integer> locationIDsDiff = new ArrayList<>();
        HashMap<Integer, Integer> similarityStore = new HashMap<Integer, Integer>();

        //Get the difference for each of smallest number
        for(int position = 0; position < this.leftSideList.size();position++){
            int smallestLeftLocationID = this.leftSideList.get(position);
            List<Integer> similarityTotalTimes = this.rightSideList.stream().filter(locationID -> smallestLeftLocationID == locationID).toList();
            int similarityTotal = 0;
            if(similarityStore.containsKey(smallestLeftLocationID)){
                similarityTotal = similarityStore.get(smallestLeftLocationID);
            } else {
                similarityTotal = smallestLeftLocationID * similarityTotalTimes.size();
                similarityStore.put(smallestLeftLocationID,similarityTotal);
            }

            locationIDsDiff.add(similarityTotal);
        }

        return locationIDsDiff;
    }

    public Integer getTotalLocationIDDiff(){
        List<Integer> locationIDsDiff = this.diffListValues();
        return locationIDsDiff.stream().reduce(0,Integer::sum);
    }

    public Integer getTotalLocationIDSim(){
        List<Integer> locationIDsSim = this.simmListValues();
        return locationIDsSim.stream().reduce(0,Integer::sum);
    }

    public void resolutionDay1(){
        //Part 1
        int totalDiff = this.getTotalLocationIDDiff();
        System.out.println("Total diff sum: "+totalDiff);

        //Part 2
        int totalSim = this.getTotalLocationIDSim();
        System.out.println("Total sim sum: "+totalSim);
    }
}
