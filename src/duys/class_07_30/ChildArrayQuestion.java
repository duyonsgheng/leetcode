package duys.class_07_30;


import java.util.LinkedList;

/**
 * @ClassName ChildArrayQuestion
 * @Author Duys
 * @Description 子数组问题
 * @Date 2021/7/30 14:06
 **/
public class ChildArrayQuestion {
    /**
     * 给定一个整型数组arr，和一个整数num
     * 某个arr中的子数组sub，如果想达标，必须满足：
     * sub中最大值 – sub中最小值 <= num，
     * 返回arr中达标子数组的数量
     */
    /**
     * 解法1：暴力解答
     * 1.第一个循环控制开始
     * 2.第二个循环 当子数组的开头是以第一个循环的位置开始，本次循环是枚举所有的结束位置
     * 3.第三个循环是求这个获得的子数组里面的最大值和最小值，然后比较差值 是否小于等于sum
     * O(N^3)
     */
    /**
     * 解法2：滑动窗口解决问题
     * 结论1：如果一个子数组满足 最大值和最小值之差 <= sum 那么，这个子数组的所有子数组都满足要求
     * 结论2：
     * O(N)
     * 思路：先L 在 0位置 R往右扩，一直到R到不达标的位置结束 收集一下数组个数，比如 L= 0 ， R =100 那么这期间的子数组是100个
     * 然后L往右扩一个位置，R再往右边扩，直到R扩到不达标的位置，在计算这期间的数组个数
     * 然后L再往右扩一个位置，R再往右。。。。。依次重复，直到L=R=数组长度
     * 怎么实现：两个双端队列，R每动一次 更新一下窗口内的最大值 和最小值。
     */

    public static int findChildArray(int[] arr, int sum) {
        if (arr == null || sum < 0 || arr.length == 0) {
            return 0;
        }
        // 4 5 3 6 2 1
        // 窗口额内的最大值 大 -> 小
        LinkedList<Integer> maxQueue = new LinkedList<>();
        // 窗口内的最小值 小 -> 大
        LinkedList<Integer> minQueue = new LinkedList<>();
        int N = arr.length;
        int ans = 0;
        int R = 0; // 不回退，窗口的右边，所有的L公用一个R
        int max = 0;
        int min = 0;
        for (int L = 0; L < N; L++) { // 尝试窗口从0开始。窗口的左边
            // 比如L 从0开始 R从O-N 这期间初次不达标的时候L往右扩
            while (R < N) {
                // 更新窗口内的最大值
                while (!maxQueue.isEmpty() && arr[maxQueue.peekLast()] <= arr[R]) {
                    maxQueue.pollLast();
                }
                maxQueue.addLast(R);
                // 更新最小值
                while (!minQueue.isEmpty() && arr[minQueue.peekLast()] >= arr[R]) {
                    minQueue.pollLast();
                }
                minQueue.addLast(R);
                // 如果不达标了，那么就停下，进入L 往右扩的流程
                if (arr[maxQueue.peekFirst()] - arr[minQueue.peekFirst()] > sum) {
                    break;
                } else
                    R++;
            }
            // 这里是一个左闭右开的结构，比如R来到3 发现不达标了，L =0 , 那么直接R-0就是三个
            ans += R - L;
            // 去掉两个队列中过期的数据,因为马上L就要+1 那么在+1之前就应该判断
            if (maxQueue.peekFirst() == L) {
                maxQueue.pollFirst();
            }
            if (minQueue.peekFirst() == L) {
                minQueue.pollFirst();
            }
        }
        return ans;
    }
}
