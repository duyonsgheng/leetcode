package custom.code_2022_12;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName LeetCode_1361
 * @Author Duys
 * @Description
 * @Date 2022/12/14 9:22
 **/
// 1361. 验证二叉树
public class LeetCode_1361 {
    static int[] parent;
    static int sets;

    public static boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {
        int[] father = new int[n];
        parent = new int[n];
        sets = n;
        Arrays.fill(father, -1);
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        for (int i = 0; i < n; i++) {
            if (leftChild[i] == rightChild[i] && leftChild[i] != -1) {
                return false;
            }
            if (!union(father, i, leftChild[i]) || !union(father, i, rightChild[i])) {
                return false;
            }
        }
        return sets == 1 ? true : false;
    }

    public static boolean union(int[] father, int f, int c) {
        if (c == -1) {
            return true;
        }
        if (father[c] != -1) {
            return false;
        }
        int pf = find(f), pc = find(c);
        if (pf == pc) {
            return false;
        }
        parent[c] = f;
        father[c] = f;
        sets--;
        return true;
    }

    public static int find(int a) {
        while (parent[a] != a) {
            a = parent[a];
        }
        return a;
    }


    public static void main(String[] args) {
        // n = 4, leftChild = [1, -1, 3, -1],rightChild = [2, -1, -1, -1];
        //System.out.println(validateBinaryTreeNodes(4, new int[]{1, -1, 3, -1}, new int[]{2, -1, -1, -1}));
        // 3
        //[1,-1,-1]
        //[-1,-1,1]
        System.out.println(validateBinaryTreeNodes(3, new int[]{1, -1, -1}, new int[]{-1, -1, 1}));
    }

    public static boolean validateBinaryTreeNodes1(int n, int[] leftChild, int[] rightChild) {
        UnionFind uf = new UnionFind(n);
        for (int i = 0; i < n; i++) {
            if (leftChild[i] != -1 && leftChild[i] == rightChild[i]) {
                return false;
            }
            if (!uf.union(i, leftChild[i]) || !uf.union(i, rightChild[i])) {
                return false;
            }
        }
        return uf.getSets() == 1 ? true : false;
    }

    static class UnionFind {
        int[] parent;
        int[] help;
        int[] father;
        int sets;

        public UnionFind(int n) {
            parent = new int[n];
            father = new int[n];
            help = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                father[i] = -1;
            }
            sets = n;
        }

        public boolean isOnce(int a, int b) {
            return find(a) == find(b);
        }

        public int getSets() {
            return sets;
        }

        public boolean union(int f, int c) {
            if (c == -1) {
                return true;
            }
            if (father[c] != -1) {
                return false;
            }
            int pf = find(f), pc = find(c);
            if (pf == pc) {
                return false;
            }
            parent[c] = f;
            father[c] = f;
            sets--;
            return true;
        }

        public int find(int a) {
            int hi = 0;
            while (a != parent[a]) {
                help[hi++] = a;
                a = parent[a];
            }
            for (hi--; hi >= 0; hi--) {
                parent[help[hi]] = a;
            }
            return a;
        }
    }
}
