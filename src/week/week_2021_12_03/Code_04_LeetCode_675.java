package week.week_2021_12_03;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @ClassName Code_LeetCode_675
 * @Author Duys
 * @Description
 * @Date 2022/4/15 10:55
 **/
// 你被请来给一个要举办高尔夫比赛的树林砍树。树林由一个m x n 的矩阵表示， 在这个矩阵中：
//0 表示障碍，无法触碰
//1表示地面，可以行走
//比 1 大的数表示有树的单元格，可以行走，数值表示树的高度
//每一步，你都可以向上、下、左、右四个方向之一移动一个单位，如果你站的地方有一棵树，那么你可以决定是否要砍倒它。
//你需要按照树的高度从低向高砍掉所有的树，每砍过一颗树，该单元格的值变为 1（即变为地面）。
//你将从 (0, 0) 点开始工作，返回你砍完所有树需要走的最小步数。 如果你无法砍完所有的树，返回 -1 。
//可以保证的是，没有两棵树的高度是相同的，并且你至少需要砍倒一棵树。
//链接：https://leetcode-cn.com/problems/cut-off-trees-for-golf-event
public class Code_04_LeetCode_675 {


    public static int cutOffTree(List<List<Integer>> forest) {
        int n = forest.size();
        int m = forest.get(0).size();

        // 记录哪些地方是树
        List<int[]> cells = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int val = forest.get(i).get(j);
                if (val > 1) {
                    cells.add(new int[]{i, j, val});
                }
            }
        }
        // 按照树的高度排个序
        cells.sort((a, b) -> a[2] - b[2]);
        int ans = 0;
        int lastRow = 0;
        int lastCol = 0;
        for (int[] cell : cells) {
            int step = bestWalk2(forest, lastRow, lastCol, cell[0], cell[1]);
            if (step == -1) {
                return -1;
            }
            ans += step;
            lastRow = cell[0];
            lastCol = cell[1];
            // 已经走过了，就相当于砍掉树了，变成了平地
            forest.get(lastRow).set(lastCol, 1);
        }
        return ans;
    }

    // sr - sourceRow
    // sc - sourceCol
    // tr - targetRow
    // tc - targetCol
    public static int bestWalk(List<List<Integer>> map, int sr, int sc, int tr, int tc) {
        int n = map.size();
        int m = map.get(0).size();
        boolean[][] seen = new boolean[n][m];
        // 存的树步数和坐标
        // 这里还真不能用堆
        LinkedList<int[]> deque = new LinkedList<>();
        deque.offerFirst(new int[]{0, sr, sc});
        int[] next = new int[]{-1, 0, 1, 0, -1};
        while (!deque.isEmpty()) {
            int[] cur = deque.pollFirst();
            int step = cur[0];
            int curRow = cur[1];
            int curCol = cur[2];
            // 如果迈上去了。就砍树，并且返回

            if (curRow == tr && curCol == tc) {
                return step;
            }
            seen[curRow][curCol] = true;
            // 四个方向走去
            for (int i = 1; i < 5; i++) {
                int nextRow = curRow + next[i - 1];
                int nextCol = curCol + next[i];
                // > 1 是树。可以砍掉
                // = 1 是地面，可以走
                // = 0 障碍，走个屁
                if (nextRow >= 0 && nextRow < n && nextCol >= 0 && nextCol < m
                        && !seen[nextRow][nextCol] && map.get(nextRow).get(nextCol) > 0) {
                    //deque.offerFirst(new int[]{});
                    int[] nexts = new int[]{step + 1, nextRow, nextCol};
                    // 走的时候，离目标越来越近的话 放头部
                    if ((i == 1 && curRow > tr) || (i == 2 && curCol < tc) ||
                            (i == 3 && curRow < tr) || (i == 4 && curCol > tc)) {
                        deque.offerFirst(nexts);
                    } else {
                        deque.offerLast(nexts);
                    }
                }
            }
        }
        return -1;
    }

    public static int bestWalk2(List<List<Integer>> map, int sr, int sc, int tr, int tc) {
        int n = map.size();
        int m = map.get(0).size();
        boolean[][] seen = new boolean[n][m];
        // 存的树步数和坐标
        // 这里还真不能用堆
        PriorityQueue<int[]> heap = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        heap.add(new int[]{0, sr, sc});
        int[] next = new int[]{-1, 0, 1, 0, -1};
        while (!heap.isEmpty()) {
            int[] cur = heap.poll();
            int step = cur[0];
            int curRow = cur[1];
            int curCol = cur[2];
            // 如果迈上去了。就砍树，并且返回
            if (curRow == tr && curCol == tc) {
                return step;
            }
            seen[curRow][curCol] = true;
            // 四个方向走去
            for (int i = 1; i < 5; i++) {
                int nextRow = curRow + next[i - 1];
                int nextCol = curCol + next[i];
                // > 1 是树。可以砍掉
                // = 1 是地面，可以走
                // = 0 障碍，走个屁
                if (nextRow >= 0 && nextRow < n && nextCol >= 0 && nextCol < m
                        && !seen[nextRow][nextCol] && map.get(nextRow).get(nextCol) > 0) {
                    //deque.offerFirst(new int[]{});
                    heap.add(new int[]{step + 1, nextRow, nextCol});
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        String strs = "[69438,55243,0,43779,5241,93591,73380],[847,49990,53242,21837,89404,63929,48214],[90332,49751,0,3088,16374,70121,25385],[14694,4338,87873,86281,5204,84169,5024],[31711,47313,1885,28332,11646,42583,31460],[59845,94855,29286,53221,9803,41305,60749],[95077,50343,27947,92852,0,0,19731],[86158,63553,56822,90251,0,23826,17478],[60387,23279,78048,78835,5310,99720,0],[74799,48845,60658,29773,96129,90443,14391],[65448,63358,78089,93914,7931,68804,72633],[93431,90868,55280,30860,59354,62083,47669],[81064,93220,22386,22341,95485,20696,13436],[50083,0,89399,43882,0,13593,27847],[0,12256,33652,69301,73395,93440,0],[42818,87197,81249,33936,7027,5744,64710],[35843,0,99746,52442,17494,49407,63016],[86042,44524,0,0,26787,97651,28572],[54183,83466,96754,89861,84143,13413,72921],[89405,52305,39907,27366,14603,0,14104],[70909,61104,70236,30365,0,30944,98378],[20124,87188,6515,98319,78146,99325,88919],[89669,0,64218,85795,2449,48939,12869],[93539,28909,90973,77642,0,72170,98359],[88628,16422,80512,0,38651,50854,55768],[13639,2889,74835,80416,26051,78859,25721],[90182,23154,16586,0,27459,3272,84893],[2480,33654,87321,93272,93079,0,38394],[34676,72427,95024,12240,72012,0,57763],[97957,56,83817,45472,0,24087,90245],[32056,0,92049,21380,4980,38458,3490],[21509,76628,0,90430,10113,76264,45840],[97192,58807,74165,65921,45726,47265,56084],[16276,27751,37985,47944,54895,80706,2372],[28438,53073,0,67255,38416,63354,69262],[23926,75497,91347,58436,73946,39565,10841],[34372,69647,44093,62680,32424,69858,68719],[24425,4014,94871,1031,99852,88692,31503],[24475,12295,33326,37771,37883,74568,25163],[0,18411,88185,60924,29028,69789,0],[34697,75631,7636,16190,60178,39082,7052],[24876,9570,53630,98605,22331,79320,88317],[27204,89103,15221,91346,35428,94251,62745],[26636,28759,12998,58412,38113,14678,0],[80871,79706,45325,3861,12504,0,4872],[79662,15626,995,80546,64775,0,68820],[25160,82123,81706,21494,92958,33594,5243]";
        // String strs = "[69438,55243,0,43779,5241,93591,73380],[847,49990,53242,21837,89404,63929,48214],[90332,49751,0,3088,16374,70121,25385],[14694,4338,87873,86281,5204,84169,5024]";
        String[] split = strs.split("],");
        List<String> list = new ArrayList<>();
        for (int i = 0; i < split.length - 1; i++) {
            String str = split[i];
            list.add(str.substring(1, str.length()));
        }
        String end = split[split.length - 1];
        list.add(end.substring(1, end.length() - 1));
        List<List<Integer>> map = new ArrayList<>();
        for (String str : list) {
            String[] strings = str.split(",");
            List<Integer> list1 = new ArrayList<>();
            for (String nums : strings) {
                list1.add(Integer.valueOf(nums));
            }
            map.add(list1);
        }
        System.out.println(cutOffTree(map));
    }
}
