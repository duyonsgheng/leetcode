package week.week_2022_11_03;

import java.util.Arrays;

/**
 * @ClassName Code_03_LeetCode_2459
 * @Author Duys
 * @Description
 * @Date 2022/11/17 10:09
 **/
// 来自谷歌
// 给定一个长度为N的数组，值一定在0~N-1范围，且每个值不重复
// 比如，arr = [4, 2, 0, 3, 1]
// 把0想象成洞，任何非0数字都可以来到这个洞里，然后在原本的位置留下洞
// 比如4这个数字，来到0所代表的洞里，那么数组变成 :
// arr = [0, 2, 4, 3, 1]
// 也就是原来的洞被4填满，4走后留下了洞
// 任何数字只能搬家到洞里，并且走后留下洞
// 通过搬家的方式，想变成有序的，有序有两种形式
// 比如arr = [4, 2, 0, 3, 1]，变成
// [0, 1, 2, 3, 4]或者[1, 2, 3, 4, 0]都叫有序
// 返回变成任何一种有序的情况都可以，最少的数字搬动次数
// 测试链接 : https://leetcode.cn/problems/sort-array-by-moving-items-to-empty-space/
public class Code_03_LeetCode_2459 {
    /**
     * 思路：
     * 看题意给出的信息，下标循环怼，但是得分情况，分为有0和没0的情况
     * 如果当前环内有0，那么只需要交换 环内元素个数-1次
     * 如果当前环内没有0，那么我们只能通过0来交换，多了一次交换就是 环内元素个数+1次
     * 那么根据题意：又分为两种情况，0在开始位置和结束位置分别来讨论
     */
    public static int sortArray(int[] nums) {
        int n = nums.length, ans1 = 0, ans2 = 0, m, next;
        boolean[] visited = new boolean[n];
        // 0在开始
        for (int i = 0; i < n; i++) {
            if (visited[i]) {
                continue;
            }
            visited[i] = true;
            m = 1;
            next = nums[i];
            // 开始下标循环怼了
            while (next != i) {
                m++;// 元素加1
                visited[next] = true;
                next = nums[next];
            }
            // 以防环内本来就只有一个元素，就是 4位置上是4
            if (m > 1) {
                // 如果环内有0 就不m-1 没0就m+1
                ans1 += i == 0 ? (m - 1) : (m + 1);
            }
        }
        Arrays.fill(visited, false);
        // 0在最后，那么我们就需要费点心思了，此时1需要去0的位置，2需要去1的位置，3需要去2的位置。。。。0需要去n-1的位置
        for (int i = n - 1; i >= 0; i--) {
            if (visited[i]) {
                continue;
            }
            visited[i] = true;
            m = 1;
            // 这里是这道题的核心
            next = nums[i] == 0 ? (n - 1) : (nums[i] - 1);
            while (next != i) {
                m++;
                visited[next] = true;
                next = nums[next] == 0 ? (n - 1) : (nums[next] - 1);
            }
            if (m > 1) {
                ans2 += i == n - 1 ? (m - 1) : (m + 1);
            }
        }
        return Math.min(ans1, ans2);
    }
}
