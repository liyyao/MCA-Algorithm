package major.greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 2、一些项目要占用一个会议室宣讲，会议室不能同时容纳两个项目的宣讲。给你每一个项目
 * 开始的时间和结束的时间，你来安排宣讲的日程，要求会议室进行的宣讲的场次最多。返回最多
 * 的宣讲场次。
 */
public class T02_MeetingRoom {

    private static class Meeting {
        int start;
        int end;

        public Meeting(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    /**
     * 暴力
     * @param arr
     * @return
     */
    public static int getMaxMeeting2(Meeting[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        return process(arr, 0, 0);
    }

    //还剩下的会议都放在arr里
    //done之前已经安排了多少会议的数量
    //deadline目前来到的时间点是什么
    //目前来到deadline的时间点，已经安排了done多的会议，剩下的会议arr可以自由安排
    //返回能安排的最多会议数量
    private static int process(Meeting[] arr, int done, int deadline) {
        if (arr == null || arr.length == 0) {
            return done;
        }
        int max = done;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].start >= deadline) {
                Meeting[] next = removeMeeting(arr, i);
                max = Math.max(max, process(next, done + 1, arr[i].end));
            }
        }
        return max;
    }

    private static Meeting[] removeMeeting(Meeting[] arr, int index) {
        Meeting[] ans = new Meeting[arr.length - 1];
        int ansIndex = 0;
        for (int i = 0; i < arr.length; i++) {
            if (i != index) {
                ans[ansIndex++] = arr[i];
            }
        }
        return ans;
    }

    static class MeetingComparator implements Comparator<Meeting> {
        @Override
        public int compare(Meeting m1, Meeting m2) {
            return m1.end - m2.end;
        }
    }

    /**
     * 贪心
     * @param arr
     * @return
     */
    public static int getMaxMeeting(Meeting[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        Arrays.sort(arr, new MeetingComparator());
        int deadline = 0;
        int meeting = 0;
        for (Meeting value : arr) {
            if (value.start >= deadline) {
                meeting++;
                deadline = value.end;
            }
        }
        return meeting;
    }

    //for test
    public static Meeting[] generateMeeting(int meetingSize, int timeMax) {
        Meeting[] ans = new Meeting[(int) (Math.random() * (meetingSize + 1))];
        for (int i = 0; i < ans.length; i++) {
            int r1 = (int) (Math.random() * (timeMax + 1));
            int r2 = (int) (Math.random() * (timeMax + 1));
            if (r1 == r2) {
                ans[i] = new Meeting(r1, r1 + 1);
            } else {
                ans[i] = new Meeting(Math.min(r1, r2), Math.max(r1, r2));
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int testTimes = 1000000;
        int timeMax = 20;
        int meetingSize = 12;
        System.out.println("测试开始");
        for (int i = 0; i < testTimes; i++) {
            Meeting[] meetings = generateMeeting(meetingSize, timeMax);
            if (getMaxMeeting(meetings) != getMaxMeeting2(meetings)) {
                System.out.println("出错了");
            }
        }
        System.out.println("测试结束");
    }
}
