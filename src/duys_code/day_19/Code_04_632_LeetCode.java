package duys_code.day_19;

import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

/**
 * @ClassName Code_04_632_LeetCode
 * @Author Duys
 * @Description 力扣：https://leetcode-cn.com/problems/smallest-range-covering-elements-from-k-lists/
 * @Date 2021/11/4 17:57
 **/
public class Code_04_632_LeetCode {
    public static class Node {
        public int value; // 值
        public int arrid; // 哪一个数组
        public int index; // 在数组里的index

        public Node(int v, int ai, int i) {
            value = v;
            arrid = ai;
            index = i;
        }
    }

    public static class NodeComparator implements Comparator<Node> {

        @Override
        public int compare(Node o1, Node o2) {
            return o1.value != o2.value ? o1.value - o2.value : o1.arrid - o2.arrid;
        }
    }

    public static int[] smallestRange(List<List<Integer>> nums) {
        int N = nums.size();
        TreeSet<Node> orderSet = new TreeSet<>(new NodeComparator());
        for (int i = 0; i < N; i++) {
            orderSet.add(new Node(nums.get(i).get(0), i, 0));
        }
        boolean set = false;
        int a = 0;
        int b = 0;
        // 哪一个数组的元素结束了。就表示需要结算了
        while (orderSet.size() == N) {
            Node min = orderSet.first();
            Node max = orderSet.last();
            if (!set || (max.value - min.value) < (b - a)) {
                set = true;
                a = min.value;
                b = max.value;
            }
            min = orderSet.pollFirst();
            int arrid = min.arrid;
            int arrindex = min.index + 1;
            if (arrindex != nums.get(arrid).size()) {
                orderSet.add(new Node(nums.get(arrid).get(arrindex), arrid, arrindex));
            }
        }
        return new int[]{a, b};
    }
}
