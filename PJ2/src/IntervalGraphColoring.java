public class IntervalGraphColoring {
    static int usedRooms = 0;
    static int arrangedActivities = 0;
    static class Activity{
        int start;
        int end;
        int roomNum;
        int number;
        boolean isArranged;

        Activity(int start,int end,int number,boolean isArranged){
            this.start = start;
            this.end = end;
            this.number = number;
            this.isArranged = isArranged;
        }
    }
    static class Room{
        int startTime = 0;
        int endTime = 0;

    }

    //将活动以结束时间递增排序
    public static void sort(Activity[] activities,int p, int r){
        if(p<r){
            int i = p-1;
            int j = p;
            for(;j<r;j++){
                if(activities[j].end<=activities[r].end){
                    i++;
                    Activity temp = activities[i];
                    activities[i] = activities[j];
                    activities[j] = temp;
                }
            }
            Activity temp2 = activities[r];
            activities[r] = activities[i+1];
            activities[i+1] = temp2;
            sort(activities,p,i);
            sort(activities,i+1,r);
        }
    }

    public static int selectRoom(Activity[] activities,Room[] rooms ){

        for(int i=0; i<activities.length;i++){
            for(int j = 0;j<rooms.length;j++){
                if(activities[i].start>=rooms[j].endTime&&(activities[i].isArranged==false)){
                    if(rooms[j].endTime==0){
                        usedRooms++;
                    }
                    activities[i].roomNum = j;
                    activities[i].isArranged = true;
                    rooms[j].endTime = activities[i].end;

                    arrangedActivities ++;
                }
            }
        }
        return usedRooms;
    }

    public static void main(String[] args){
        Activity a1 = new Activity(0,6,1,false);
        Activity a2 = new Activity(3,8,2,false);
        Activity a3 = new Activity(1,4,3,false);
        Activity a4 = new Activity(5,7,4,false);
        Activity a5 = new Activity(3,5,5,false);

        Activity a6 = new Activity(5,9,6,false);
        Activity a7 = new Activity(6,10,7,false);
        Activity a8 = new Activity(8,11,8,false);
        Activity a9 = new Activity(8,12,9,false);
        Activity a10 = new Activity(2,13,10,false);

        Activity[] activities = {a1,a2,a3,a4,a5,a6,a7,a8,a9,a10};
        Room[] rooms = new Room[activities.length];
        for(int i=0;i<rooms.length;i++){
            rooms[i] = new Room();
        }

        sort(activities,0,activities.length-1);
        selectRoom(activities,rooms);
        for(int i = 0;i<activities.length;i++){
            System.out.println("Activity "+activities[i].number+" is in the room "+activities[i].roomNum);
        }
        System.out.println("The least number of usedRoom is: "+usedRooms);
    }

}
