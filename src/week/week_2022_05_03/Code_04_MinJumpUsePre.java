package week.week_2022_05_03;

/**
 * @ClassName Code_01_MinJumpUsePre
 * @Author Duys
 * @Description
 * @Date 2022/5/16 10:56
 **/

// 为了给刷题的同学一些奖励，力扣团队引入了一个弹簧游戏机
// 游戏机由 N 个特殊弹簧排成一排，编号为 0 到 N-1
// 初始有一个小球在编号 0 的弹簧处。若小球在编号为 i 的弹簧处
// 通过按动弹簧，可以选择把小球向右弹射 jump[i] 的距离，或者向左弹射到任意左侧弹簧的位置
// 也就是说，在编号为 i 弹簧处按动弹簧，
// 小球可以弹向 0 到 i-1 中任意弹簧或者 i+jump[i] 的弹簧（若 i+jump[i]>=N ，则表示小球弹出了机器）
// 小球位于编号 0 处的弹簧时不能再向左弹。
// 为了获得奖励，你需要将小球弹出机器。
// 请求出最少需要按动多少次弹簧，可以将小球从编号 0 弹簧弹出整个机器，即向右越过编号 N-1 的弹簧。
// 测试链接 : https://leetcode-cn.com/problems/zui-xiao-tiao-yue-ci-shu/
public class Code_04_MinJumpUsePre {

    // 当前来到最后一个位置，要去外边，只需要dp[jump[i]+i]+1
    // 这是一个n^2的解答
    public static int minJump1(int[] jump) {
        int[] dp = new int[jump.length];
        // 从后往前推
        dp[jump.length - 1] = 1;

        for (int i = jump.length - 2; i >= 0; i--) {
            // 来到i位置，如果当前可以去的最大距离都已经超出了，只需要1步
            dp[i] = jump[i] + i >= jump.length ? 1 : dp[jump[i] + i] + 1;
            // 实际是从前往后的，我当前位置填了，看看后面的位置是不是需要更新、。如果后面的比我大了。我需要更新，因为来到当前需要3步，我能去到的位置，如果是>4步的都要更新
            // 意思就是我之前去的是5步，当前位置3步就可以来到，那么我实际能去之前5步的位置，其实只需要4步就可以到
            // 所以这里是看看我从当前位置确定了，我能不能把后面的位置推的更小
            for (int j = i + 1; j < jump.length && dp[j] >= dp[i] + 1; j++) {
                dp[j] = dp[i] + 1;
            }
        }
        return dp[0];
    }

    // 宽度优先遍历。但是我们需要记录，我来到i位置的时候 0~i-1 哪些位置已经去过了，哪些位置可以去的。使用indexTree
    // 按层遍历
    public static int minJump(int[] jump) {
        if (jump == null || jump.length <= 0) {
            return 0;
        }
        if (jump.length == 1) {
            return 1;
        }
        int n = jump.length;
        int[] queue = new int[n];// 数组做队列来玩儿
        int l = 0;
        int r = 0;
        queue[r++] = 0;//一开始在0位置上
        IndexTree indexTree = new IndexTree(n);

        // 初始的时候全部是1
        for (int i = 1; i < n; i++) {
            indexTree.add(i, 1);
        }

        int ans = 0;
        // 当前队列还有东西
        while (l != r) {
            int tmp = r;
            for (; l < tmp; l++) {
                int cur = queue[l];
                int to1 = cur + jump[cur];
                if (to1 >= n) {
                    return ans + 1;
                }
                // 当前位置不为0，表示可以去
                if (indexTree.value(to1) != 0) {
                    queue[r++] = to1;
                    indexTree.add(to1, -1);
                }
                // 看看 0 到 cur哪些位置可以去
                while (indexTree.sum(cur - 1) != 0) {
                    int find = find(indexTree, cur - 1);
                    indexTree.add(find, -1);
                    queue[r++] = find;
                }
            }
            ans++;
        }
        return -1;
    }

    public static int find(IndexTree it, int right) {
        int left = 0;
        int mid = 0;
        int find = 0;
        while (left <= right) {
            mid = (left + right) / 2;
            // 看看那一片区域 还有没去过的位置
            if (it.sum(mid) > 0) {
                find = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return find;
    }

    public static class IndexTree {
        private int[] tree;
        private int size;

        public IndexTree(int n) {
            size = n;
            tree = new int[size + 1];
        }

        public int value(int index) {
            if (index == 0) {
                return sum(index);
            } else {
                return sum(index) - sum(index - 1);
            }
        }

        public int sum(int i) {
            int index = i + 1;
            int ans = 0;
            while (index > 0) {
                ans += tree[index];
                index -= index & -index;
            }
            return ans;
        }

        public void add(int index, int v) {
            int i = index + 1;
            while (i <= size) {
                tree[i] += v;
                index += index & -index;
            }
        }
    }
}
