package duys_code.day_33;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * @ClassName Code_02_210_LeetCode
 * @Author Duys
 * @Description https://leetcode-cn.com/problems/course-schedule-ii/
 * @Date 2021/12/6 11:38
 **/
public class Code_02_210_LeetCode {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // 一个课程对应一个实例
        int[] ans = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            ans[i] = i;
        }
        if (prerequisites == null || prerequisites.length == 0) {
            return ans;
        }
        Map<Integer, Course> map = new HashMap<>();
        for (int[] pre : prerequisites) {
            int to = pre[0];
            int from = pre[1];
            if (!map.containsKey(to)) {
                map.put(to, new Course(to));
            }
            if (!map.containsKey(from)) {
                map.put(from, new Course(from));
            }
            Course t = map.get(to);
            Course f = map.get(from);
            t.in++; // to的入度++
            f.nexts.add(t); // 把to加入到from的下一级
        }
        int need = map.size();
        Queue<Course> zeroQueue = new LinkedList<>();
        int index = 0;
        for (int i = 0; i < numCourses; i++) {
            if (!map.containsKey(i)) {
                ans[index++] = i;
            } else {
                if (map.get(i).in == 0) {
                    zeroQueue.add(map.get(i));
                }
            }
        }
        int count = 0;
        while (!zeroQueue.isEmpty()) {
            Course poll = zeroQueue.poll();
            count++;
            ans[index++] = poll.name;
            for (Course next : poll.nexts) {
                if (--next.in == 0) {
                    zeroQueue.add(next);
                }
            }
        }
        if (count == need) {
            return ans;
        }
        return new int[0];
    }

    public static class Course {
        // 课程编号
        public int name;
        // 入度
        public int in;
        public ArrayList<Course> nexts;

        public Course(int n) {
            name = n;
            in = 0;
            nexts = new ArrayList<>();
        }
    }

}
