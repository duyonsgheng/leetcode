package week.week_2022_03_02;

import java.util.Arrays;
import java.util.Map;

/**
 * @ClassName Code_01_MeetingCheck
 * @Author Duys
 * @Description
 * @Date 2022/3/10 9:45
 **/
// 来自字节飞书团队
// 在字节跳动，大家都使用飞书的日历功能进行会议室的预订，遇到会议高峰时期，
// 会议室就可能不够用，现在请你实现一个算法，判断预订会议时是否有空的会议室可用。
// 为简化问题，这里忽略会议室的大小，认为所有的会议室都是等价的，
// 只要空闲就可以容纳任意的会议，并且：
// 1. 所有的会议预订都是当日预订当日的时段
// 2. 会议时段是一个左闭右开的时间区间，精确到分钟
// 3. 每个会议室刚开始都是空闲状态，同一时间一个会议室只能进行一场会议
// 4. 会议一旦预订成功就会按时进行
// 比如上午11点到中午12点的会议即[660, 720)
// 给定一个会议室总数m
// 一个预定事件由[a,b,c]代表 :
// a代表预定动作的发生时间，早来早得; b代表会议的召开时间; c代表会议的结束时间
// 给定一个n*3的二维数组，即可表示所有预定事件
// 返回一个长度为n的boolean类型的数组，表示每一个预定时间是否成功
public class Code_01_MeetingCheck {


    // 1 根据题意得到会议室总数是m
    // 2 会议开始时间和结束时间，结束时间是 720，实际到719就结束了。
    // 3 比如1-9目前已经有两个会议了，如果还想在1-9上安排一个会议，那么就需要3个会议室

    public static boolean[] reserveMeeting(int[][] meetings, int m) {
        // 会议的总共场次
        int n = meetings.length;
        // 会议的开头时间和结尾时间，这是为了做离散化，比如 开始时间 100万，结束时间200万，这种的没必要搞这么大
        int[] ranks = new int[n << 1];
        for (int i = 0; i < n; i++) {
            ranks[i] = meetings[i][1];
            // 这里因为会议结束时间是一个开区间
            ranks[i + n] = meetings[i][2] - 1;
        }
        Arrays.sort(ranks);
        //
        int[][] reMeetings = new int[n][4];
        int max = 0;
        for (int i = 0; i < n; i++) {
            reMeetings[i][0] = i; // 第几场会议
            reMeetings[i][1] = meetings[i][0]; // 预定时间
            reMeetings[i][2] = rank(ranks, meetings[i][1]); // 预定会议的开始时间
            reMeetings[i][3] = rank(ranks, meetings[i][2]); // 预定会议的结束时间
            max = Math.max(max, reMeetings[i][3]); // 最大的结束时间
        }
        SegmentTree segmentTree = new SegmentTree(max);
        // 根据预定时间排个序
        Arrays.sort(reMeetings, (a, b) -> a[1] - b[1]);
        boolean[] ans = new boolean[n];
        for (int[] me : reMeetings) {
            // 这个时间节点上安排的会议小于m，还可继续安排
            if (segmentTree.queryMax(me[2], me[3]) < m) {
                ans[me[0]] = true;
                segmentTree.add(me[2], me[3], 1);
            }
        }
        return ans;
    }

    // >= num最左的位置
    public static int rank(int[] arr, int num) {
        int l = 0;
        int r = arr.length - 1;
        int m = 0;
        int ans = 0;
        while (l <= r) {
            m = l + (r - l) >> 1;
            if (arr[m] == num) {
                ans = m;
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return ans + 1;
    }

    // 不由分说，先搞出线段树
    public static class SegmentTree {
        private int n;
        private int[] max;
        private int[] lazy;

        public SegmentTree(int maxSize) {
            n = maxSize;
            max = new int[n << 2];
            lazy = new int[n << 2];
        }

        public void add(int L, int R, int C) {
            add(L, R, C, 1, n, 1);
        }

        public int queryMax(int L, int R) {
            return queryMax(L, R, 1, n, 1);
        }

        private int queryMax(int L, int R, int l, int r, int rt) {
            // 全部囊括了
            if (L <= l && R >= r) {
                return max[rt];
            }
            int mid = l + (r - l) / 2;
            // 往下去更新去吧
            pushDown(rt, mid - l + 1, r - mid);
            int ans = 0;
            if (L <= mid) {
                ans = Math.max(ans, queryMax(L, R, l, mid, rt << 1));
            }
            if (R > mid) {
                ans = Math.max(ans, queryMax(L, R, mid + 1, r, rt << 1 | 1));
            }
            return ans;
        }

        private void add(int L, int R, int C, int l, int r, int rt) {
            if (L <= l && r <= R) { // 实际要操作的区间已经大于了当前线段树包含的整个区间。那么全部lazy吼住，不下发
                max[rt] += C;
                lazy[rt] += C;
                return;
            }
            int mid = l + (r - l) / 2;
            // 往下去更新去吧
            pushDown(rt, mid - l + 1, r - mid);
            if (L <= mid) {
                // 左边有一部分
                add(L, R, C, l, mid, rt << 1);
            }
            // 右边有一部分
            if (R > mid) {
                add(L, R, C, mid + 1, r, rt << 1 | 1);
            }
            // 父节点更新
            pushUp(rt);
        }

        private void pushUp(int rt) {
            // 两个孩子节点的最大值赋给父亲节点
            max[rt] = Math.max(max[rt << 1], max[rt << 1 | 1]);
        }

        private void pushDown(int rt, int l, int r) {
            if (lazy[rt] != 0) {
                // 下发之前的懒更新信息
                lazy[rt << 1] += lazy[rt];
                max[rt << 1] += lazy[rt];

                lazy[rt << 1 | 1] += lazy[rt];
                max[rt << 1 | 1] += lazy[rt];

                lazy[rt] = 0;
            }
        }
    }

}
