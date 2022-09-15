package duys.class_06_30;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @ClassName NumberOfIslands_II
 * @Author Duys
 * @Description 力扣 305题
 * @Date 2021/7/1 13:32
 **/
public class NumberOfIslands_II_02 {

    public static List<Integer> numIslands(int m, int n, int[][] board) {
        UnionFind un = new UnionFind();
        List<Integer> ans = new ArrayList<>();
        for (int[] pos : board) {
            ans.add(un.content(pos[0], pos[1]));
        }
        return ans;
    }

    public static class UnionFind {

        private HashMap<String, String> parent;
        private HashMap<String, Integer> size;
        private ArrayList<String> help;
        private int stes;

        public UnionFind() {
            parent = new HashMap<>();
            size = new HashMap<>();
            help = new ArrayList<>();
            stes = 0;
        }

        public String findParent(String cur) {
            while (cur != parent.get(cur)) {
                cur = parent.get(cur);
                help = new ArrayList<>();
            }
            for (String str : help) {
                parent.put(str, cur);
            }
            help.clear();
            return cur;
        }

        public void union(String key1, String key2) {
            if (!(parent.containsKey(key1) && parent.containsKey(key2))) {
                return;
            }
            String p1 = findParent(key1);
            String p2 = findParent(key2);
            if (p1.equals(p2)) {
                return;
            }
            int size1 = size.get(key1);
            int size2 = size.get(key2);
            if (size1 >= size2) {
                parent.put(key2, key1);
                size.put(key1, size1 + size2);
            } else {
                parent.put(key1, key2);
                size.put(key2, size1 + size2);
            }
            stes--;
        }

        public int content(int i, int j) {
            String key = i + "_" + j;
            if (parent.containsKey(key)) {
                return stes;
            }
            parent.put(key, key);
            size.put(key, 1);
            stes++;

            //上下左右去联合
            String up = i - 1 + "_" + j;
            String down = i + 1 + "_" + j;
            String left = i + "_" + (j - 1);
            String right = i + "_" + (j + 1);
            union(key, up);
            union(key, down);
            union(key, left);
            union(key, right);
            return stes;
        }
    }
}
