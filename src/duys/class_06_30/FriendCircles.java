package duys.class_06_30;

/**
 * @ClassName FriendCircles
 * @Author Duys
 * @Description 力扣原题：https://leetcode.com/problems/friend-circles/
 * @Date 2021/6/30 13:27
 **/
public class FriendCircles {
    /**
     * 题意：一个二维数组表示 两个人的认识关系，求在这个二维数组中有几个朋友圈
     */

    public static int findCircleNum(int[][] fr) {
        // 因为是一个正方形的，而且关系是以对角线对称的
        int length = fr.length;
        UnionFind find = new UnionFind(length);
        for (int i = 0; i < length; i++) {
            // 只需要矩阵的上半部分/或者下半部分
            for (int j = i + 1; j < length; j++) {
                // 为1 表示认识
                if (fr[i][j] == 1) {
                    find.union(i, j);
                }
            }
        }
        return find.sets;
    }

    // 本题使用数组代替map 建立并查集
    public static class UnionFind {
        //parent[i] = k : i 的父级是K
        private int[] parent;
        // size[i] =  k : i 所在集合的大小，当i是代表节点才有意义
        private int[] size;
        // 做路径压缩使用
        private int[] help;
        //
        private int sets;

        public UnionFind(int N) {
            parent = new int[N];
            size = new int[N];
            help = new int[N];
            sets = N;
            for (int i = 0; i < N; i++) {
                parent[i] = i;// 初始父级都是自己
                size[i] = 1; // 初始的节点大小都是1
            }
        }

        public int findParent(int i) {
            int index = 0;
            // 初始的时候都是自己，整个链路上遇到的全部压倒i下面去
            while (i != parent[i]) {
                // i 是不断的在变化，最后变成了最初入参i的最父级节点
                help[index++] = i;
                i = parent[i];
            }
            for (index--; index >= 0; index--) {
                // 把找i的父级所遇到的所有节点，全部都压倒同一个父 i 的下面去
                parent[help[index]] = i;
            }
            return i;
        }

        public void union(int i, int j) {
            // 找到i的最父级节点
            int iH = findParent(i);
            // 找到j的最父级节点
            int jH = findParent(j);
            // 如果不相等就需要进行 某一个节点数小的代表节点进行重定向了
            if (iH != jH) {
                //  如果i所在的代表节 的整个节点数 要比j的大，那么就把j所在的代表节点挂到i下面去，并且更新节点数
                if (size[iH] >= size[jH]) {
                    size[iH] += size[jH];
                    // 代表节点进行转移了
                    parent[jH] = iH;
                }
                // 如果j的更大，那么就把i所在的代表节点 挂到j下面去
                else {
                    size[jH] += size[iH];
                    // 代表节点进行转移了
                    parent[iH] = jH;
                }
                sets--;
            }
        }
    }
}
