package custom.code_2022_09;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @ClassName LeetCode_835
 * @Author Duys
 * @Description
 * @Date 2022/9/27 17:11
 **/
// 835. 图像重叠
public class LeetCode_835 {

    public int largestOverlap(int[][] img1, int[][] img2) {
        int n = img1.length;
        int[][] count = new int[2 * n + 1][2 * n + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (img1[i][j] == 1) {
                    for (int k = 0; k < n; k++) {
                        for (int p = 0; p < n; p++) {
                            if (img2[k][p] == 1) {
                                // 偏移量
                                count[i - k + n][j - p + n] += 1;
                            }
                        }
                    }
                }
            }
        }
        int ans = 0;
        for (int[] a1 : count) {
            for (int a2 : a1) {
                ans = Math.max(a2, ans);
            }
        }
        return ans;
    }

    public int largestOverlap1(int[][] img1, int[][] img2) {
        int n = img1.length;
        List<Node> list1 = new ArrayList<>();
        List<Node> list2 = new ArrayList<>();
        for (int i = 0; i < n * n; i++) {
            if (img1[i / n][i % n] == 1) {
                list1.add(new Node(i / n, i % n));
            }
            if (img2[i / n][i % n] == 1) {
                list2.add(new Node(i / n, i % n));
            }
        }
        Set<Node> set = new HashSet<>(list2);
        int ans = 0;
        Set<Node> seen = new HashSet<>();
        for (Node a : list1) {
            for (Node b : list2) {
                Node delta = new Node(b.x - a.x, b.y - a.y);
                if (!seen.contains(delta)) {
                    seen.add(delta);
                    int cand = 0;
                    for (Node p : list1) {
                        if (set.contains(new Node(p.x + delta.x, p.y + delta.y)))
                            cand++;
                        ans = Math.max(ans, cand);
                    }
                }
            }
        }
        return ans;
    }

    class Node {
        int x;
        int y;

        public Node(int _x, int _y) {
            x = _x;
            y = _y;
        }
    }
}
