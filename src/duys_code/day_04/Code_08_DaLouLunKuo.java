package duys_code.day_04;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @ClassName Code_08_DaLouLunKuo
 * @Author Duys
 * @Description 力扣： https://leetcode-cn.com/problems/the-skyline-problem/
 * @Date 2021/9/22 20:25
 **/
public class Code_08_DaLouLunKuo {

    /**
     * 高度的变化引起的轮廓线的变化，不论高度是在增加，还是在减小，只要发生变化，那么轮廓线就会产生，
     * 输入：buildings = [[2,9,10],[3,7,15],[5,12,12],[15,20,10],[19,24,8]]
     * 输出：[[2,10],[3,15],[7,12],[12,0],[15,10],[20,8],[24,0]]
     * 解释：
     * 图 A 显示输入的所有建筑物的位置和高度，
     * 图 B 显示由这些建筑物形成的天际线。图 B 中的红点表示输出列表中的关键点。
     */
    public static List<List<Integer>> getSkyline(int[][] buildings) {
        int N = buildings.length;
        Node[] nodes = new Node[N * 2];
        for (int i = 0; i < N; i++) {
            // eg:[2,9,10]
            // 从2开始 9 结束 ，高度是 10 ，那么2就是高度增加 9就是高度减少
            nodes[i * 2] = new Node(buildings[i][0], buildings[i][2], true);
            nodes[i * 2 + 1] = new Node(buildings[i][1], buildings[i][2], false);
        }
        // 排序，根据在x轴的位置，依次排序
        Arrays.sort(nodes, new NodeComparator());
        // 用有序表
        TreeMap<Integer, Integer> heightTimes = new TreeMap<>(); // key - 高度。value - 次数
        TreeMap<Integer, Integer> xHeight = new TreeMap<>(); // 高度变化的折线.key - x轴的点，value - 高度
        for (int i = 0; i < nodes.length; i++) {
            // 如果是增加的
            if (nodes[i].isAdd) {
                if (!heightTimes.containsKey(nodes[i].h)) {
                    heightTimes.put(nodes[i].h, 1);
                } else {
                    heightTimes.put(nodes[i].h, heightTimes.get(nodes[i].h) + 1);
                }
            } else {
                // 如果发现之前的高度已经只有一次了，那么就直接去掉，因为我们要使用这个记录来描绘折线
                if (heightTimes.get(nodes[i].h) == 1) {
                    heightTimes.remove(nodes[i].h);
                } else {
                    heightTimes.put(nodes[i].h, heightTimes.get(nodes[i].h) - 1);
                }
            }
            // 开始描绘折线
            if (heightTimes.isEmpty()) {
                // 如果发现高度的次数已经回归0了。那么折线就从0开始描绘
                xHeight.put(nodes[i].x, 0);
            } else {
                xHeight.put(nodes[i].x, heightTimes.lastKey());
            }
        }
        List<List<Integer>> ans = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : xHeight.entrySet()) {
            int curX = entry.getKey();
            int curHeight = entry.getValue();
            // ans.get(ans.size() - 1).get(1) != curHeight [2,9,10] 9是高度从10 变为0的地方
            if (ans.isEmpty() || ans.get(ans.size() - 1).get(1) != curHeight) {
                ans.add(new ArrayList<>(Arrays.asList(curX, curHeight)));
            }
        }
        return ans;
    }

    public static class NodeComparator implements Comparator<Node> {

        @Override
        public int compare(Node o1, Node o2) {

            // 在X轴的位置越小的越靠前
            return o1.x - o2.x;
        }
    }

    public static class Node {
        // x轴上的位置
        public int x;
        // 是否是高度增加
        public boolean isAdd;
        // 高度
        public int h;

        public Node(int x, int h, boolean isAdd) {
            this.x = x;
            this.h = h;
            this.isAdd = isAdd;
        }
    }
}
