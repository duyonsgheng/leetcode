package custom.code_2022_11;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName LeetCode_990
 * @Author Duys
 * @Description
 * @Date 2022/11/1 17:44
 **/
// 990. 等式方程的可满足性
// 并查集
public class LeetCode_990 {

    public boolean equationsPossible(String[] equations) {
        UnionFind uf = new UnionFind();
        for (String str : equations) {
            int last = str.charAt(0) - 'a';
            int end = str.charAt(3) - 'a';
            String substring = str.substring(1, 3);
            if (substring.equals("==")) {
                uf.union(last, end);
            }
        }
        for (String str : equations) {
            int last = str.charAt(0) - 'a';
            int end = str.charAt(3) - 'a';
            String substring = str.substring(1, 3);
            if (substring.equals("!=")) {
                if (uf.isOnce(last, end)) {
                    return false;
                }
            }
        }
        return true;
    }

    class UnionFind {
        public int[] parent;
        public int[] size;
        public int[] help;

        public UnionFind() {
            parent = new int[26];
            size = new int[26];
            help = new int[26];
            for (int i = 0; i < 26; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        public void union(int a, int b) {
            int fa = find(a);
            int fb = find(b);
            if (fa == fb) {
                return;
            }
            if (size[fa] >= size[fb]) {
                size[fa] += size[fb];
                parent[fb] = fa;
            } else {
                size[fb] += size[fa];
                parent[fa] = fb;
            }
        }

        public boolean isOnce(int a, int b) {
            return find(a) == find(b);
        }

        private int find(int ch) {
            int hi = 0;
            while (ch != parent[ch]) {
                help[hi++] = ch;
                ch = parent[ch];
            }
            for (hi--; hi >= 0; hi--) {
                parent[help[hi]] = ch;
            }
            return ch;
        }
    }

    public static void main(String[] args) {
        String a = "a==b";
        System.out.println(a.substring(1, 3));
    }
}
