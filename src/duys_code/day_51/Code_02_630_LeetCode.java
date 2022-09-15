package duys_code.day_51;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @ClassName Code_02_630_LeetCode
 * @Author Duys
 * @Description 力扣：https://leetcode-cn.com/problems/course-schedule-iii/
 * @Date 2021/11/5 16:41
 **/
public class Code_02_630_LeetCode {
    /**
     * 输入: [[100, 200], [200, 1300], [1000, 1250], [2000, 3200]]
     * 输出: 3
     * 解释:
     * 这里一共有 4 门课程, 但是你最多可以修 3 门:
     * 首先, 修第一门课时, 它要耗费 100 天，你会在第 100 天完成, 在第 101 天准备下门课。
     * 第二, 修第三门课时, 它会耗费 1000 天，所以你将在第 1100 天的时候完成它, 以及在第 1101 天开始准备下门课程。
     * 第三, 修第二门课时, 它会耗时 200 天，所以你将会在第 1300 天时完成它。
     * 第四门课现在不能修，因为你将会在第 3300 天完成它，这已经超出了关闭日期。
     */
    public static int scheduleCourse(int[][] courses) {
        // 首先根据截止时间排序
        Arrays.sort(courses, (a, b) -> a[1] - b[1]);
        // 花费时间的大根堆
        PriorityQueue<Integer> heap = new PriorityQueue<>((a, b) -> b - a);
        // 当前来到的时间点
        int times = 0;
        for (int[] arr : courses) {
            // 当前时间+当前课的花费时间 还小于了截止时间，表示可以选择
            if (arr[0] + times <= arr[1]) {
                heap.add(arr[0]);
                times += arr[0];
            }
            // 当前时间 + 当前花费 > 当前的截止时间，这时候，如果当前课要当选，只有淘汰调之前的某些课
            else {
                if (!heap.isEmpty() && heap.peek() > arr[0]) {
                    heap.add(arr[0]);
                    // 对应的时间也应该回调
                    times += arr[0] - heap.poll();
                }
            }
        }
        return heap.size();
    }
}
