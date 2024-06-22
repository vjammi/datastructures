package io.dev.v2.interval;

import java.util.Arrays;
import java.util.Comparator;

  /**
    252. Meeting Rooms
    Given an array of meeting time intervals where intervals[i] = [starti, endi],
    determine if a person could attend all meetings.

    Example 1:
    Input: intervals = [[0,30],[5,10],[15,20]]
    Output: false

    Example 2:
    Input: intervals = [[7,10],[2,4]]
    Output: true
  */
public class MeetingRooms {

 private class SortIntervalsByStartTime implements Comparator<int[]> {
  public int compare(int[] interval1, int[] interval2){
   Integer startTime1  = interval1[0];
   Integer startTime2  = interval2[0];
   return startTime1.compareTo(startTime2);
  }
 }

  /**
     int[][] intervals = [0,30], [5,10], [15,20]
     canAttendAllMeetings = false;

                       0  5  10  15  20  25  30
         interval #1   |----------------------|
         interval #2      |---|
         interval #3             |----|

     Explanation: Cannot attend all meetings because there are overlapping intervals

     Sort the intervals based on start time [O(nlogn)]
     Iterate through the sorted intervals   [O(n)]
       Return false - if  currentStartTime < previousIntervalEndTime
       else continue - if currentStartTime >= previousIntervalEndTime
   */
  public boolean canAttendMeetings(int[][] intervals) {
    Arrays.sort(intervals, new SortIntervalsByStartTime());

    int[] previousInterval = null;
    for (int i=0; i<intervals.length; i++){

      int[] interval = intervals[i];
      if (previousInterval == null){
         previousInterval = interval;
         System.out.println("CanAttend meeting for interval "+ interval[0] + ", " +interval[1]);
      }else{
        if (interval[0] < previousInterval[1]) {
         System.out.println("canAttendAllMeetings = false for interval " + interval[0] + ", " + interval[1]);
         return false;
        }else{
         System.out.println("CanAttend meeting for interval "+ interval[0] + ", " +interval[1]);
         previousInterval = interval;
        }
      }

     }
    return true;
   }


  public static void main(String[] args) {
   MeetingRooms obj = new MeetingRooms();

   int[][] intervals = {{0,30}, {5,10}, {15,20}};
   obj.canAttendMeetings(intervals);

  }
}
