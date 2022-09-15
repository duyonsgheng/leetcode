package week.week_2021_12_02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName Code_01_FindAllPeopleWithSecret
 * @Author Duys
 * @Description
 * @Date 2022/4/15 14:43
 **/
// 给你一个整数 n ，表示有 n 个专家从 0 到 n - 1 编号。另外给你一个下标从 0 开始的二维整数数组 meetings ，
//其中 meetings[i] = [xi, yi, timei] 表示专家 xi 和专家 yi 在时间 timei 要开一场会。一个专家可以同时参加 多场会议 。最后，给你一个整数 firstPerson 。
//专家 0 有一个 秘密 ，最初，他在时间0 将这个秘密分享给了专家 firstPerson 。接着，这个秘密会在每次有知晓这个秘密的专家参加会议时进行传播。更正式的表达是，
//每次会议，如果专家 xi 在时间 timei 时知晓这个秘密，那么他将会与专家 yi 分享这个秘密，反之亦然。
//秘密共享是 瞬时发生 的。也就是说，在同一时间，一个专家不光可以接收到秘密，还能在其他会议上与其他专家分享。
//在所有会议都结束之后，返回所有知晓这个秘密的专家列表。你可以按 任何顺序 返回答案。
//来源：力扣（LeetCode）
//链接：https://leetcode-cn.com/problems/find-all-people-with-secret
public class Code_01_LeetCode_2092 {

    // 并查集。。。首先我们任务 0 和 firstPerson已经合并在一起了，然后看看再会议过程中，0 和 firstPerson 分别接触了哪些专家
    public List<Integer> findAllPeople(int n, int[][] meetings, int firstPerson) {
        UnionFind uf = new UnionFind(n, firstPerson);
        int m = meetings.length;

        Arrays.sort(meetings, (a, b) -> a[2] - b[2]);
        int[] help = new int[m << 1];
        help[0] = meetings[0][0];
        help[1] = meetings[0][1];
        int size = 2;// 已经有两个知道了的
        for (int i = 1; i < m; i++) {
            // 不是同时进行的
            if (meetings[i][2] != meetings[i - 1][2]) {
                // 不通的会议，去分享了
                share(help, size, uf);
                help[0] = meetings[i][0];
                help[1] = meetings[i][1];
                size = 2; // 如果不是同时进行的，分享结束后，往后的过程开始时候，size还是只有2
            } else {
                // 同时进行的会议，都可以知道秘密了
                help[size++] = meetings[i][0];
                help[size++] = meetings[i][1];
            }
        }
        share(help, size, uf);

        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!uf.know(i)) {
                continue;
            }
            ans.add(i);
        }
        return ans;
    }

    public static void share(int[] help, int size, UnionFind uf) {
        for (int i = 0; i < size; i += 2) {
            uf.union(help[i], help[i + 1]);
        }
        for (int i = 0; i < size; i++) {
            if (!uf.know(help[i])) {
                uf.isolate(help[i]);
            }
        }
    }

    public static class UnionFind {
        public int[] parent;
        public boolean[] sect; // 知道秘密与否
        public int[] help; // 帮助合并

        public UnionFind(int n, int first) {
            parent = new int[n];
            sect = new boolean[n];
            help = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
            // 最开始的时候两个合并到一起了
            parent[first] = 0;
            sect[0] = true; //
        }

        // 查找i的父是谁
        private int find(int i) {
            int helpIndex = 0;
            // 整条链路上合并
            while (i != parent[i]) {
                help[helpIndex++] = i;
                i = parent[i];
            }
            for (helpIndex--; helpIndex >= 0; helpIndex--) {
                parent[help[helpIndex]] = i;
            }
            return i;
        }

        public void union(int i, int j) {
            int ip = find(i);
            int jp = find(j);
            if (ip == jp) {
                return;
            }
            parent[jp] = ip;
            sect[ip] |= sect[jp];
        }

        public boolean know(int i) {
            return sect[find(i)];
        }

        public void isolate(int i) {
            parent[i] = i;
        }
    }
}
