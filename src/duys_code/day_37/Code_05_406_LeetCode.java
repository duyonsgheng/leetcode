package duys_code.day_37;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName Code_05_406_LeetCode
 * @Author Duys
 * @Description https://leetcode-cn.com/problems/queue-reconstruction-by-height/
 * @Date 2021/12/16 13:08
 **/
public class Code_05_406_LeetCode {
    //假设有打乱顺序的一群人站成一个队列，数组 people 表示队列中一些人的属性（不一定按顺序）。每个 people[i] = [hi, ki] 表示第 i 个人的身高为 hi ，
    //前面 正好 有 ki 个身高大于或等于 hi 的人。

    // 1.首先按照身高降序排序
    // 2.按照需要的人数升序排序
    public int[][] reconstructQueue(int[][] people) {
        int n = people.length;
        // 有多少人
        Unit[] units = new Unit[n];
        for (int i = 0; i < n; i++) {
            units[i] = new Unit(people[i][0], people[i][1]);
        }
        // 身高不一样，就身高降序
        Arrays.sort(units, (a, b) -> a.h != b.h ? (b.h - a.h) : (a.k - b.k));
        List<Unit> ans = new ArrayList<>();
        // 每个人去了他对应需求的位置
        for (Unit unit : units) {
            ans.add(unit.k, unit);
        }
        int[][] res = new int[n][2];
        int index = 0;
        for (Unit unit : ans) {
            res[index][0] = unit.h;
            res[index++][1] = unit.k;
        }
        return res;
    }

    public static class Unit {
        public int h;
        public int k;

        public Unit(int h1, int k1) {
            h = h1;
            k = k1;
        }
    }
}
