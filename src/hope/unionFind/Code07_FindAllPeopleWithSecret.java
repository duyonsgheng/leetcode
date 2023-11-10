package hope.unionFind;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Mr.Du
 * @ClassName Code07_FindAllPeopleWithSecret
 * @date 2023年11月10日 10:31
 */

// 找出知晓秘密的所有专家
// 给你一个整数 n ，表示有 n 个专家从 0 到 n - 1 编号
// 另外给你一个下标从 0 开始的二维整数数组 meetings
// 其中 meetings[i] = [xi, yi, timei] 表示专家 xi 和专家 yi 在时间 timei 要开一场会
// 一个专家可以同时参加 多场会议 。最后，给你一个整数 firstPerson
// 专家 0 有一个 秘密 ，最初，他在时间 0 将这个秘密分享给了专家 firstPerson
// 接着，这个秘密会在每次有知晓这个秘密的专家参加会议时进行传播
// 更正式的表达是，每次会议，如果专家 xi 在时间 timei 时知晓这个秘密
// 那么他将会与专家 yi 分享这个秘密，反之亦然。秘密共享是 瞬时发生 的
// 也就是说，在同一时间，一个专家不光可以接收到秘密，还能在其他会议上与其他专家分享
// 在所有会议都结束之后，返回所有知晓这个秘密的专家列表
// 你可以按 任何顺序 返回答案
// 链接测试 : https://leetcode.cn/problems/find-all-people-with-secret/
public class Code07_FindAllPeopleWithSecret {
    // 分析，在每一个时刻开会的人中，只要有存在知晓秘密的人，那么会议结束，跟知道秘密的人开会的人都知道了秘密。
    public List<Integer> findAllPeople(int n, int[][] meetings, int firstPerson) {
        build(n, firstPerson);
        // 根据开会的时间排序
        Arrays.sort(meetings, (a, b) -> a[2] - b[2]);
        int m = meetings.length;
        for (int l = 0, r; l < m; ) {
            r = l;
            // 看看那些会议是同一时刻开的
            while (r + 1 < m && meetings[l][2] == meetings[r + 1][2]) {
                r++;
            }
            for (int i = l; i <= r; i++) {
                union(meetings[i][0], meetings[i][1]);
            }
            // 如果当前会议结束了，还有不知道秘密的几何，则把他们撤销
            for (int i = l, a, b; i <= r; i++) {
                a = meetings[i][0];
                b = meetings[i][1];
                if (!secret[find(a)]) {
                    father[a] = a;
                }
                if (!secret[find(b)]) {
                    father[b] = b;
                }
            }
            l = r + 1;
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (secret[find(i)]) {
                ans.add(i);
            }
        }
        return ans;
    }

    public static int MAXN = 100001;
    public static int[] father = new int[MAXN];
    public static boolean[] secret = new boolean[MAXN]; // 以当前人为头节点的几何是知道秘密的

    public static void build(int n, int f) {
        for (int i = 0; i < n; i++) {
            father[i] = i;
            secret[i] = false;
        }
        father[f] = 0;
        secret[0] = true;
    }

    public static int find(int a) {
        if (a != father[a]) {
            father[a] = find(father[a]);
        }
        return father[a];
    }

    public static void union(int a, int b) {
        int fa = find(a);
        int fb = find(b);
        if (fa == fb) {
            return;
        }
        father[fa] = fb;
        secret[fb] |= secret[fa];
    }
}
