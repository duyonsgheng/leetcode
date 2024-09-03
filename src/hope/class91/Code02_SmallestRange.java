package hope.class91;

import java.util.List;
import java.util.TreeSet;

/**
 * @author Mr.Du
 * @ClassName Code02_SmallestRange
 * @date 2024年09月03日 下午 04:08
 */
// 最小区间
// 你有k个非递减排列的整数列表
// 找到一个最小区间，使得k个列表中的每个列表至少有一个数包含在其中
// 测试链接 : https://leetcode.cn/problems/smallest-range-covering-elements-from-k-lists/
public class Code02_SmallestRange {
    // 使用有序表，然后每一个数组从小打大进入，弹出的时候，就把当前数组的下一个数加进来
    public static class Node {
        public int v; // 值
        public int i; // 当前值来自哪个数组
        public int j; // 当前值来自i号数组的什么位置

        public Node(int a, int b, int c) {
            v = a;
            i = b;
            j = c;
        }
    }

    public static int[] smallestRange(List<List<Integer>> nums) {
        int k = nums.size();
        // 根据值排序，但是有序表值相同就只会保留一个，所以要其他的值也参与排序策略
        TreeSet<Node> set = new TreeSet<>((a, b) -> a.v != b.v ? (a.v - b.v) : (a.i - b.i));
        for (int i = 0; i < k; i++) {
            set.add(new Node(nums.get(i).get(0), i, 0));
        }
        int r = Integer.MAX_VALUE; // 记录最窄区间的宽度
        int a = 0; // 最窄区间的开头
        int b = 0; // 最窄区间的结尾
        Node max, min;
        while (set.size() == k) {
            max = set.last(); // 有序表中最大的
            min = set.pollFirst(); // 有序表中最小的，要弹出，下一个数需要进来了
            if (max.v - min.v < r) {
                a = min.v;
                b = max.v;
                r = max.v - min.v;
            }
            // 准备把弹出的对应数组的下一个加进来
            if (min.j + 1 < nums.get(min.i).size()) {
                set.add(new Node(nums.get(min.i).get(min.j + 1), min.i, min.j + 1));
            }
        }
        return new int[]{a, b};
    }
}
