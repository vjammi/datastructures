package ds.patterns.interval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
    Given an array of meeting time intervals intervals where intervals[i] = [starti, endi], return the minimum number of conference rooms required.
    Input: intervals = [[0,30],[5,10],[15,20]]    Output: 2
         [start, end]  Meeting Room
         [0,30]        #1: 1
         [5,10]        #2: 1+1
         [15,20]       #2: 2-1+1

         0, 30
         5, 10
         15, 20

         . 1 0 30
         .. 2
         ... 2 15 20

    Input: intervals = [[7,10],[2,4]]             Output: 1
    Input: intervals = [[1,5],[8,9],[8,9]]        Output: 2
         1, 5
         8, 9
         8, 9

         . 1 1 5
         ... 1 8 9
         .. 2
 */
public class MeetingRoomsII {

    class IntervalComparator implements Comparator<int[]> {
        public int compare(int[] a, int[] b){
            return a[0]-b[0];
        }
    }

    class MeetingRoom{
        int start;
        int end;

        MeetingRoom(int start, int end){
            this.start = start;
            this.end = end;
        }
    }

    public int minMeetingRooms(int[][] intervals) {
        Arrays.sort(intervals, new IntervalComparator());
        for (int[] arr: intervals)
            System.out.println(arr[0] +", "+arr[1] +"   ");

        int count = 0;
        int maxCount = 0;
        List<MeetingRoom> meetingRooms = new ArrayList<>();
        for(int i=0; i<intervals.length; i++){

            int[] interval = intervals[i];
            if (meetingRooms.size() == 0){
                meetingRooms.add(new MeetingRoom(interval[0], interval[1]));
                count++;
                maxCount = Math.max(maxCount, count);
                System.out.println(". "+ maxCount +" " +interval[0] +" " +interval[1]  );
            }else{
                for(int j=0; j<meetingRooms.size(); j++){
                    MeetingRoom meetingRoom = meetingRooms.get(j);
                    if (meetingRoom.end <= interval[0]){
                        meetingRooms.remove(meetingRoom);
                        count--;
                        break;
                    }
                }
                meetingRooms.add(new MeetingRoom(interval[0], interval[1]));
                count++;
                maxCount = Math.max(maxCount, count);
                System.out.println(".. "+ maxCount);
            }
        }

        for(int i=0; i<meetingRooms.size(); i++)
            System.out.println(meetingRooms.get(i).start +" " +meetingRooms.get(i).end);

        return maxCount;
    }
}
