package week.week_2023_01_02;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Code_04_LeetCode_2193
 * @Author Duys
 * @Description
 * @Date 2023/1/16 10:02
 **/
// 2193. 得到回文串的最少操作次数
public class Code_04_LeetCode_2193 {
    // 思路：
    // 1.选择左边得字符串为基准，看看最右边相同得字符串移动到对称位置，需要几步，以及变换后得到字符串是什么情况
    public int minMovesToMakePalindrome(String s) {
        int n = s.length();
        // 位置记录
        List<List<Integer>> indexs = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            indexs.add(new ArrayList<>());
        }
        for (int i = 0, j = 1; i < n; i++, j++) {
            int index = s.charAt(i) - 'a';
            indexs.get(index).add(j);
        }
        int[] arr = new int[n + 1];
        IndexTree it = new IndexTree(n);
        for (int i = 0, l = 1; i < n; i++, l++) {
            if (arr[l] == 0) { // 说明当前位置没动过
                int cur = s.charAt(i) - 'a';
                // 拿到相同字符最右侧的位置
                int r = indexs.get(cur).remove(indexs.get(cur).size() - 1);
                if (l == r) { // 只有一个位置需要填
                    arr[l] = (n + 1) / 2; // 就是中点位置了
                    it.add(l, -1);
                } else {
                    // 统计0到l的累加，看看哪些位置已经动过了
                    int ksum = it.sum(l);
                    arr[l] = ksum; // 左边改在ksum的位置。
                    arr[r] = n - ksum + 1; // 右边在对应位置
                    it.add(r, -1);
                }
            }
        }
        return number(arr, new int[n + 1], 1, n);
    }

    public int number(int[] arr, int[] help, int l, int r) {
        if (l >= r) {
            return 0;
        }
        int m = l + ((r - l) >> 1);
        return number(arr, help, l, m) + number(arr, help, m + 1, r) + merge(arr, help, l, m, r);
    }


    public int merge(int[] arr, int[] help, int l, int m, int r) {
        int i = r;
        int p1 = m;
        int p2 = r;
        int ans = 0;
        while (p1 >= l && p2 > m) {
            ans += arr[p1] > arr[p2] ? (p2 - m) : 0;
            help[i--] = arr[p1] > arr[p2] ? arr[p1--] : arr[p2--];
        }
        while (p1 >= l) {
            help[i--] = arr[p1--];
        }
        while (p2 > m) {
            help[i--] = arr[p2--];
        }
        for (i = l; i <= r; i++)
            arr[i] = help[i];
        return ans;
    }

    class IndexTree {
        int[] tree;
        int n;

        public IndexTree(int size) {
            tree = new int[size + 1];
            n = size;
            for (int i = 1; i <= n; i++) {
                add(i, 1);
            }
        }

        public int sum(int i) {
            int ans = 0;
            while (i > 0) {
                ans += tree[i];
                i -= (i & (-i));
            }
            return ans;
        }

        public void add(int i, int v) {
            while (i < tree.length) {
                tree[i] += v;
                i += (i & (-i));
            }
        }
    }
}
