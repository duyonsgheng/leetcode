package duys_code.day_33;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * @ClassName Code_01_207_LeetCode
 * @Author Duys
 * @Description https://leetcode-cn.com/problems/course-schedule/
 * @Date 2021/12/6 11:28
 **/
public class Code_01_207_LeetCode {


    // prerequisites[0] 依赖 prerequisites[1]
    // 先根据课程建立一张图
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (prerequisites == null || prerequisites.length <= 0) {
            return true;
        }
        // 一个课程对应一个实例
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
        for (Course node : map.values()) {
            if (node.in == 0) {
                zeroQueue.add(node);
            }
        }
        int count = 0;
        while (!zeroQueue.isEmpty()) {
            Course poll = zeroQueue.poll();
            count++;
            for (Course next : poll.nexts) {
                if (--next.in == 0) {
                    zeroQueue.add(next);
                }
            }
        }
        return count == need;
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
