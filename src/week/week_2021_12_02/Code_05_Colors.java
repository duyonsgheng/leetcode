package week.week_2021_12_02;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Code_05_Colors
 * @Author Duys
 * @Description
 * @Date 2022/4/19 13:03
 **/
// 来自美团
// 给定一棵多叉树的头节点head
// 每个节点的颜色只会是0、1、2、3中的一种
// 任何两个节点之间的都有路径
// 如果节点a和节点b的路径上，包含全部的颜色，这条路径算达标路径
// (a -> ... -> b)和(b -> ... -> a)算两条路径
// 求多叉树上达标的路径一共有多少？
// 点的数量 <= 10^5
public class Code_05_Colors {


    public static class Node {
        public int color;
        public List<Node> nexts;

        public Node(int c) {
            color = c;
            nexts = new ArrayList<>();
        }
    }

    // 依然是二叉树递归套路
    public static Info process(Node head) {
        Info ans = new Info();
        // 头节点是啥颜色
        int hc = 1 << head.color;
        ans.colors[hc] = 1;
        if (head.nexts.isEmpty()) {
            return ans;
        }
        // 头节点有几个下级节点
        int n = head.nexts.size();
        // 0位置不用
        Info[] infos = new Info[n + 1];
        for (int i = 1; i <= n; i++) {
            infos[i] = process(head.nexts.get(i - 1));
            ans.allPath += infos[i].allPath;
        }
        long[][] lefts = new long[n + 2][16];
        for (int i = 1; i <= n; i++) {
            for (int status = 1; status < 16; status++) {
                lefts[i][status] = lefts[i - 1][status] + infos[i].colors[status];
            }
        }
        long[][] rights = new long[n + 2][16];
        for (int i = n; i >= 1; i--) {
            for (int status = 1; status < 16; status++) {
                rights[i][status] = rights[i + 1][status] + infos[i].colors[status];
            }
        }
        for (int status = 1; status < 15; status++) {
            ans.colors[status | hc] += rights[1][status];
        }
        ans.allPath += ans.colors[15] << 1;
        for (int from = 1; from <= n; from++) {
            for (int fromStatus = 1; fromStatus < 16; fromStatus++) {
                for (int toStatus = 1; toStatus < 16; toStatus++) {
                    if ((fromStatus | toStatus | hc) == 15) {
                        ans.allPath += infos[from].colors[fromStatus] * (lefts[from - 1][toStatus] + rights[from + 1][toStatus]);
                    }
                }
            }
        }
        return ans;
    }

    public static class Info {
        // 当前子树有多少合法路径
        public long allPath;
        // 一定要从头节点出发的情况下，到当前子树了，一共出现了多少颜色。
        public long[] colors;

        public Info() {
            allPath = 0;
            colors = new long[16];
        }
    }
}
