package hope.class89;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author Mr.Du
 * @ClassName Code05_CourseScheduleIII
 * @date 2024年08月22日 下午 04:39
 */
// 课程表III
// 这里有n门不同的在线课程，按从1到n编号
// 给你一个数组courses
// 其中courses[i]=[durationi, lastDayi]表示第i门课将会持续上durationi天课
// 并且必须在不晚于lastDayi的时候完成
// 你的学期从第 1 天开始
// 且不能同时修读两门及两门以上的课程
// 返回你最多可以修读的课程数目
// 测试链接 : https://leetcode.cn/problems/course-schedule-iii/
public class Code05_CourseScheduleIII {
    public static int scheduleCourse(int[][] courses) {
        // 根据课程的截止时间排序
        Arrays.sort(courses, (a, b) -> a[1] - b[1]);
        // 搞一个大根堆
        PriorityQueue<Integer> heap = new PriorityQueue<>((a, b) -> b - a);
        int time = 0;
        for (int i = 0; i < courses.length; i++) {
            if (time + courses[i][0] <= courses[i][1]) {
                heap.add(courses[i][0]); // 把课程的代价加进去
                time += courses[i][0];// 同时当前来的时间增加
            } else {
                // 看看是不是能把堆顶较大的代价的划掉
                if (!heap.isEmpty() && heap.peek() > courses[i][0]) {
                    time += courses[i][0] - heap.poll();
                    heap.add(courses[i][0]);
                }
            }
        }
        return time;
    }
}
