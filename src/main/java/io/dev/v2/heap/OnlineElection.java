package io.dev.v2.heap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 911. Online Election
 * You are given two integer arrays persons and times. In an election, the ith vote was cast for persons[i] at time times[i].
 * For each query at a time t, find the person that was leading the election at time t. Votes cast at time t will count towards our query.
 * In the case of a tie, the most recent vote (among tied candidates) wins.
 *
 * Implement the TopVotedCandidate class:
 *  TopVotedCandidate(int[] persons, int[] times) Initializes the object with the persons and times arrays.
 *  int q(int t) Returns the number of the person that was leading the election at time t according to the mentioned rules.
 *
 * Example 1:
 * Input
 * ["TopVotedCandidate", "q", "q", "q", "q", "q", "q"]
 * [[[0, 1, 1, 0, 0, 1, 0], [0, 5, 10, 15, 20, 25, 30]], [3], [12], [25], [15], [24], [8]]
 * Output
 * [null, 0, 1, 1, 0, 0, 1]
 *
 * Explanation
 * TopVotedCandidate topVotedCandidate = new TopVotedCandidate([0, 1, 1, 0, 0, 1, 0], [0, 5, 10, 15, 20, 25, 30]);
 * topVotedCandidate.q(3); // return 0, At time 3, the votes are [0], and 0 is leading.
 * topVotedCandidate.q(12); // return 1, At time 12, the votes are [0,1,1], and 1 is leading.
 * topVotedCandidate.q(25); // return 1, At time 25, the votes are [0,1,1,0,0,1], and 1 is leading (as ties go to the most recent vote.)
 * topVotedCandidate.q(15); // return 0
 * topVotedCandidate.q(24); // return 0
 * topVotedCandidate.q(8); // return 1
 **/
public class OnlineElection {

    /**
      class TopVotedCandidate {
        public TopVotedCandidate(int[] persons, int[] times) {
        }

        public int q(int t) {
            return 0;
        }
    }*/

    class Vote {
        int person, time;
        Vote(int p, int t) {
            person = p;
            time = t;
        }
    }

    // Approach 1: List of Lists + Binary Search
    //We can store the votes in a list A of lists of votes. Each vote has a person and a timestamp, and A[count] is a list of the count-th votes received for that person.
    //Then, A[i][0] and A[i] are monotone increasing, so we can binary search on them to find the most recent vote by time.
    class TopVotedCandidate1 {
        List<List<Vote>> A;
        public TopVotedCandidate1(int[] persons, int[] times) {
            A = new ArrayList();

            Map<Integer, Integer> count = new HashMap();
            for (int i = 0; i < persons.length; ++i) {
                int p = persons[i], t = times[i];
                int c = count.getOrDefault(p, 0) + 1;

                count.put(p, c);
                while (A.size() <= c)
                    A.add(new ArrayList<Vote>());

                A.get(c).add(new Vote(p, t));
            }
        }

        public int q(int t) {
            // Binary search on A[i][0].time for smallest i
            // such that A[i][0].time > t
            int lo = 1, hi = A.size();
            while (lo < hi) {
                int mi = lo + (hi - lo) / 2;
                if (A.get(mi).get(0).time <= t)
                    lo = mi + 1;
                else
                    hi = mi;
            }
            int i = lo - 1;

            // Binary search on A[i][j].time for smallest j
            // such that A[i][j].time > t
            lo = 0; hi = A.get(i).size();
            while (lo < hi) {
                int mi = lo + (hi - lo) / 2;
                if (A.get(i).get(mi).time <= t)
                    lo = mi + 1;
                else
                    hi = mi;
            }
            int j = Math.max(lo-1, 0);
            return A.get(i).get(j).person;
        }
    }


    //    class Vote {
    //        int person, time;
    //        Vote(int p, int t) {
    //            person = p;
    //            time = t;
    //        }
    //    }

    // Approach 2: Precomputed Answer + Binary Search
    // As the votes come in, we can remember every event (winner, time) when the winner changes. After, we have a sorted
    // list of these events that we can binary search for the answer.
    class TopVotedCandidate2 {
        List<Vote> A;
        public TopVotedCandidate2(int[] persons, int[] times) {
            A = new ArrayList();
            Map<Integer, Integer> count = new HashMap();
            int leader = -1;  // current leader
            int m = 0;  // current number of votes for leader

            for (int i = 0; i < persons.length; ++i) {
                int p = persons[i], t = times[i];
                int c = count.getOrDefault(p, 0) + 1;
                count.put(p, c);

                if (c >= m) {
                    if (p != leader) {  // lead change
                        leader = p;
                        A.add(new Vote(leader, t));
                    }

                    if (c > m) m = c;
                }
            }
        }

        public int q(int t) {
            int lo = 1, hi = A.size();
            while (lo < hi) {
                int mi = lo + (hi - lo) / 2;
                if (A.get(mi).time <= t)
                    lo = mi + 1;
                else
                    hi = mi;
            }

            return A.get(lo - 1).person;
        }
    }




    public static void main(String[] args) {
        // Your TopVotedCandidate object will be instantiated and called as such:
        //TopVotedCandidate obj = new TopVotedCandidate(persons, times);
        //int param_1 = obj.q(t);
    }

}
