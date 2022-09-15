package duys.class_06_30;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName NumberOfIslands_II
 * @Author Duys
 * @Description 力扣 305题
 * @Date 2021/7/1 13:32
 **/
public class NumberOfIslands_II_01 {

    public static List<Integer> numIslands(int m, int n, int[][] board) {
        UnionFind un = new UnionFind(m, n);
        List<Integer> ans = new ArrayList<>();
        for (int[] pos : board) {
            ans.add(un.content(pos[0], pos[1]));
        }
        return ans;
    }

    public static class UnionFind {
        //parent[i] = k : i 的父级是K
        private int[] parent;
        // size[i] =  k : i 所在集合的大小，当i是代表节点才有意义
        private int[] size;
        // 做路径压缩使用
        private int[] help;

        private int col;// 列数

        private int row;
        //
        private int sets;

        public UnionFind(int m, int n) {
            col = n;
            row = m;
            sets = 0;
            // 并查集里面的数组需要囊括二维数组里面所有的数据
            int len = col * row;
            parent = new int[len];
            help = new int[len];
            size = new int[len];

        }

        public int index(int i, int j) {
            return i * col + j;
        }

        // i表示的是 并查集里面数组的下标，是通过原二维数组的位置算出来的
        public int findParent(int i) {
            int helpIndex = 0;
            while (i != parent[i]) {
                help[helpIndex++] = i;
                i = parent[i];
            }
            for (helpIndex--; helpIndex >= 0; helpIndex--) {
                parent[help[helpIndex]] = i;
            }
            return i;
        }

        public void union(int i1, int j1, int i2, int j2) {
            // 检查越界
            if (i1 < 0 || i1 >= row || i2 < 0 || i2 >= row ||
                    j1 < 0 || j1 >= col || j2 < 0 || j2 >= col) {
                return;
            }
            // 获取在并查集里数组的下标
            int index1 = index(i1, j1);
            int index2 = index(i2, j2);
            // 在size中，如果没有被初始化的，表示没有1.没有1，不能进行合并
            if (size[index1] == 0 || size[index2] == 0) {
                return;
            }
            //
            int parent1 = findParent(index1);
            int parent2 = findParent(index2);
            //
            if (parent1 == parent2) {
                return;
            }
            if (size[parent1] >= size[parent2]) {
                size[parent1] += size[parent2];
                parent[parent2] = parent1;
            } else {
                size[parent2] += size[parent1];
                parent[parent1] = parent2;
            }
            sets--;
        }

        public int content(int i, int j) {
            int index = index(i, j);
            // size位置如果为0表示还没有被初始化
            if (size[index] == 0) {
                parent[index] = index;
                size[index] = 1;
                sets++;
                // i j 位置的上下左右 去尝试合并
                union(i - 1, j, i, j);
                union(i, j - 1, i, j);
                union(i + 1, j, i, j);
                union(i, j + 1, i, j);
            }
            return sets;
        }
    }
}
