package com.driver;

import org.apache.commons.lang3.tuple.Pair;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Workspace extends Gmail{

    private ArrayList<Meeting> calendar; // Stores all the meetings

    public Workspace(String emailId) {
        // The inboxCapacity is equal to the maximum value an integer can store.
        super(emailId,Integer.MAX_VALUE);
        calendar = new ArrayList<>();

    }

    public void addMeeting(Meeting meeting){
        //add the meeting to calendar
        calendar.add(meeting);

    }

    public int findMaxMeetings(){
        // find the maximum number of meetings you can attend
        // 1. At a particular time, you can be present in at most one meeting
        // 2. If you want to attend a meeting, you must join it at its start time and leave at end time.
        // Example: If a meeting ends at 10:00 am, you cannot attend another meeting starting at 10:00 am
        ArrayList<Meeting>m = new ArrayList<>();
        LocalTime time_limit;

        Collections.sort(calendar,(m1,m2)->{
            return m1.getEndTime().compareTo(m2.getEndTime());
        });
        //Initially select first meeting
        Meeting meet = calendar.get(0);
        m.add(meet);
        //time_limit to check whether new meeting cn be conducted or not
        time_limit = calendar.get(0).getEndTime();

        //check for all meeting whether it can be selected or not
        for(int i =0;i< calendar.size();i++){
            if(calendar.get(i).getStartTime().compareTo(time_limit)>0){
                //add selected meeting to arrylist
                m.add(calendar.get(i));
                //update time limit
                time_limit=calendar.get(i).getEndTime();
            }
        }
        return m.size();

       /* Collections.sort(curr, Comparator.comparing(Meeting::getStartTime));

        LocalTime prevS = curr.get(0).getStartTime();
        LocalTime prevE = curr.get(0).getEndTime();

        int cnt=0;
        for(int i=0;i<curr.size();i++){
            LocalTime currS=curr.get(i).getStartTime();
            LocalTime currE=curr.get(i).getEndTime();
            if(currS.isAfter(prevE)){
                cnt++;
                prevS=currS;
                prevE=currE;
            }else{
                if(prevE.isAfter(currE)){
                    prevS=currS;
                    prevE=currE;
                }
            }
        }
        return cnt+1;*/

    }
    class myComparator implements Comparator<Meeting>{
        @Override public int compare(Meeting m1,Meeting m2)
        {
            if(m1.getEndTime().compareTo(m2.getEndTime())<0){
                //return -1 if second object is bigger than first
                return -1;
            }
            else if(m1.getEndTime().compareTo(m2.getEndTime())>0){
                //return 1 if second object is smaller than first
                return 1;
            }
            return 0;
        }
    }
}
