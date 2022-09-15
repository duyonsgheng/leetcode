package week.week_2022_07_03;

import java.util.Arrays;

/**
 * @ClassName Code_01_LeetCode_757
 * @Author Duys
 * @Description
 * @Date 2022/7/21 8:37
 **/
// 757. 设置交集大小至少为2
// 一个整数区间[a, b](a < b) 代表着从a到b的所有连续整数，包括a和b。
//给你一组整数区间intervals，请找到一个最小的集合 S，使得 S 里的元素与区间intervals中的每一个整数区间都至少有2个元素相交。
//输出这个最小集合S的大小。
//链接：https://leetcode.cn/problems/set-intersection-size-at-least-two
public class Code_01_LeetCode_757 {

    public int intersectionSizeTwo(int[][] intervals) {
        if (intervals == null || intervals.length < 1 || intervals[0] == null || intervals[0].length < 1) {
            return -1;
        }
        // 按照结束位置大小排序，结束位置相同的，开始位置谁大谁再前面
        Arrays.sort(intervals, (a, b) -> a[1] != b[1] ? (a[1] - b[1]) : (b[0] - a[0]));
        int n = intervals.length;
        // 贪心的点，就是我尽可能再每一个区间的选择尽量大数来满足，如果当前区间和之前选择的数有重复的，则只需要一个，如果包含了，则不需要，如果没包含则需要两个
        int last = intervals[0][1];
        int pre = intervals[0][1] - 1;
        int ans = 2;
        for (int i = 1; i < n; i++) {
            // 如果我当前的开始位置都是小于之前的pre，不需要选择了，因为包含了，题目的输入参数会保证区间不是是[2,2]这种的
            if (intervals[i][0] <= pre) {
                continue;
            }
            // 下面情况说明至少需要选择一个
            ans += 1;
            // 比如当前开始是5，之前的last是4，说明啥，说明我在这个区间需要选择两个
            if (intervals[i][0] > last) {
                ans += 1;
                pre = intervals[i][1] - 1;
                last = intervals[i][1];
            }
            // 比如当前是5 ，之前的是last是5或者比5大，但是呢，当前的5已经比之前的pre大了，说明再当前区间还需要选择一个
            else {
                // 更新last。新的last更新为当前的最后一个
                pre = last;
                last = intervals[i][1];
            }
        }
        return ans;
    }
}
