package week.week_2021_12_05;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Code_01_LeetCode_851
 * @Author Duys
 * @Description
 * @Date 2022/4/12 17:48
 **/
// 有一组 n 个人作为实验对象，从 0 到 n - 1 编号，其中每个人都有不同数目的钱，以及不同程度的安静值（quietness）。为了方便起见，我们将编号为x的人简称为 "personx"。
//给你一个数组 richer ，其中 richer[i] = [ai, bi] 表示 personai比 personbi更有钱。另给你一个整数数组 quiet ，
//其中quiet[i] 是 person i 的安静值。richer 中所给出的数据 逻辑自洽（也就是说，在 person x 比 person y 更有钱的同时，不会出现 person y 比 person x 更有钱的情况 ）。
//现在，返回一个整数数组 answer 作为答案，其中answer[x] = y的前提是，在所有拥有的钱肯定不少于personx的人中，persony是最安静的人（也就是安静值quiet[y]最小的人）。
//链接：https://leetcode-cn.com/problems/loud-and-rich
public class Code_01_LeetCode_851 {

    public int[] loudAndRich(int[][] richer, int[] quiet) {
        // richer[i] = {a,b} a比b有钱
        // quiet[i] =k，i的安静值是k
        int n = quiet.length;
        List<List<Integer>> next = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            next.add(new ArrayList<>());
        }
        // 图的遍历
        // 入度
        int[] in = new int[n];
        for (int[] r : richer) {
            // 找到比r[0]钱更少的人
            next.get(r[0]).add(r[1]);
            in[r[1]]++;// 钱更少的人，入度增加
        }
        // 所有入度为0的人压入队列
        int[] queue = new int[n];
        int l = 0;
        int r = 0;
        for (int i = 0; i < n; i++) {
            if (in[i] == 0) {
                queue[r++] = i;
            }
        }

        // ans[i] =j ，比i更有钱的人，安静值最小的是j
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = i;
        }
        // 如果队列不为空
        while (l < r) {
            // 弹出入度为0的
            int cur = queue[l++];
            // 我的影响
            List<Integer> nexts = next.get(cur);
            for (int ne : nexts) {
                if (quiet[ans[ne]] > quiet[ans[cur]]) {
                    ans[ne] = ans[cur];
                }
                if (--in[ne] == 0) {
                    queue[r++] = ne;
                }
            }
        }
        return ans;
    }
}
