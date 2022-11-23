package custom.code_2022_11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName LeetCode_1202
 * @Author Duys
 * @Description
 * @Date 2022/11/23 15:10
 **/
// 1202. 交换字符串中的元素
public class LeetCode_1202 {
    public static String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        int n = s.length();
        UnionFind uf = new UnionFind(n);
        for (List<Integer> p : pairs) {
            uf.union(p.get(0), p.get(1));
        }
        // 同一个集合的挂到一起来
        Map<Integer, List<Character>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int p = uf.find(i);
            if (!map.containsKey(p)) {
                map.put(p, new ArrayList<>());
            }
            map.get(p).add(s.charAt(i));
        }
        // 然后对每一个集合里面的进行排序，大的排在前面
        for (int key : map.keySet()) {
            Collections.sort(map.get(key), (a, b) -> b.compareTo(a));
        }
        StringBuffer bu = new StringBuffer();
        for (int i = 0; i < n; i++) {
            int p = uf.find(i);
            List<Character> cur = map.get(p);
            // 取每一个对应的最小的
            // 这里会有一个问题，就是删除list的最后一个要比删除第一个快很多
            bu.append(cur.remove(cur.size() - 1));
        }
        return bu.toString();
    }

    static class UnionFind {
        private int[] parent;
        private int[] size;
        private int[] help;

        public UnionFind(int n) {
            parent = new int[n];
            size = new int[n];
            help = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        public void union(int a, int b) {
            int pa = find(a);
            int pb = find(b);
            if (pa == pb) {
                return;
            }
            if (size[pa] >= size[pb]) {
                size[pa] += size[pb];
                parent[pb] = pa;
            } else {
                size[pb] += size[pa];
                parent[pa] = pb;
            }
        }

        private int find(int x) {
            int hi = 0;
            while (x != parent[x]) {
                help[hi++] = x;
                x = parent[x];
            }
            for (hi--; hi >= 0; hi--)
                parent[help[hi]] = x;
            return x;
        }
    }

    public static void main(String[] args) {
        List<Integer> ans = new ArrayList<>();
        ans.add(2);
        ans.add(6);
        ans.add(3);
        ans.add(4);
        ans.add(15);
        Collections.sort(ans, (a, b) -> a - b);
        System.out.println(ans.remove(0));
        System.out.println(ans.remove(0));
    }
}
