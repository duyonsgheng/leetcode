package week.week_2022_02_04;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName Code_05_GroupsOfStrings
 * @Author Duys
 * @Description
 * @Date 2022/3/23 17:25
 **/
public class Code_05_GroupsOfStrings {
    // 给你一个下标从0开始的字符串数组words。每个字符串都只包含 小写英文字母。words中任意一个子串中，每个字母都至多只出现一次。
    //如果通过以下操作之一，我们可以从 s1的字母集合得到 s2的字母集合，那么我们称这两个字符串为 关联的：
    //往s1的字母集合中添加一个字母。
    //从s1的字母集合中删去一个字母。
    //将 s1中的一个字母替换成另外任意一个字母（也可以替换为这个字母本身）。
    //数组words可以分为一个或者多个无交集的 组。如果一个字符串与另一个字符串关联，那么它们应当属于同一个组。
    //注意，你需要确保分好组后，一个组内的任一字符串与其他组的字符串都不关联。可以证明在这个条件下，分组方案是唯一的。
    //请你返回一个长度为 2的数组ans：
    //
    //ans[0]是words分组后的总组数。
    //ans[1]是字符串数目最多的组所包含的字符串数目。
    //来源：力扣（LeetCode）
    //链接：https://leetcode-cn.com/problems/groups-of-strings


    public static int[] groupStrings(String[] words) {
        int n = words.length;
        UnionFind uf = new UnionFind(n);
        int[] strs = new int[n];
        Map<Integer, Integer> stands = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int status = 0; // 我们把每一个word转成一个status
            for (char c : words[i].toCharArray()) {
                status |= 1 << (c - 'a');
            }
            strs[i] = status;
            // 如果存在了，就直接合并
            if (stands.containsKey(status)) {
                uf.union(stands.get(status), i);
            } else {
                stands.put(status, i);
            }
        }
        // 该去尝试使用规则了
        for (int i = 0; i < n; i++) {
            int yes = strs[i];
            int no = ~(yes) & ((1 << 26) - 1);
            int tmpYes = yes;
            int tmpNo = no;
            int rightOneYes = 0;
            int rightOneNo = 0;
            // 尝试使用三种规则
            // 替换一个字符
            while (tmpYes != 0) {
                rightOneYes = tmpYes & -tmpYes;
                uf.union(i, stands.get(yes ^ rightOneYes));
                tmpYes ^= rightOneYes;
            }

            // 添加一个字符
            while (tmpNo != 0) {
                rightOneNo = tmpNo & -tmpNo;
                uf.union(i, stands.get(yes | rightOneNo));
                tmpNo ^= rightOneNo;
            }

            // 删除一个字符
            tmpYes = yes;
            while (tmpYes != 0) {
                rightOneYes = tmpYes & -tmpYes;
                tmpNo = no;
                while (tmpNo != 0) {
                    rightOneNo = tmpNo & -tmpNo;
                    uf.union(i, stands.get((yes ^ rightOneYes) | rightOneNo));
                    tmpNo ^= rightOneNo;
                }
                tmpYes ^= rightOneYes;
            }
        }
        return new int[]{uf.sets(), uf.maxSize()};
    }

    // 先来一个并查集
    public static class UnionFind {
        private int[] parent;
        private int[] size;
        private int[] help;

        public UnionFind(int n) {
            parent = new int[n];
            size = new int[n];
            help = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = i;
            }
        }

        private int find(int i) {
            int hi = 0;
            while (i != parent[i]) {
                help[hi++] = i;
                i = parent[i];
            }
            for (hi--; hi >= 0; hi--) {
                // 这一条链路的都是同一个父
                parent[help[hi]] = i;
            }
            return i;
        }

        public void union(Integer i, Integer j) {
            if (i == null || j == null) {
                return;
            }
            int f1 = find(i);
            int f2 = find(j);
            if (f1 != f2) {
                if (size[f1] > size[f2]) {
                    parent[f2] = f1;
                    size[f1] += size[f2];
                } else {
                    parent[f1] = f2;
                    size[f2] += size[f1];
                }
            }
        }

        public int sets() {
            int ans = 0;
            for (int i = 0; i < parent.length; i++) {
                ans += parent[i] == i ? 1 : 0;
            }
            return ans;
        }

        public int maxSize() {
            int ans = 0;
            for (int s : size) {
                ans = Math.max(ans, s);
            }
            return ans;
        }
    }
}
