package duys_code.day_02;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeMap;

/**
 * @ClassName Code_01_Monny
 * @Author Duys
 * @Description
 * @Date 2021/9/15 16:11
 **/
public class Code_01_Money {

    /**
     * 给定数组hard和money，长度都为N
     * hard[i]表示i号的难度， money[i]表示i号工作的收入
     * 给定数组ability，长度都为M，ability[j]表示j号人的能力
     * 每一号工作，都可以提供无数的岗位，难度和收入都一样
     * 但是人的能力必须>=这份工作的难度，才能上班
     * 返回一个长度为M的数组ans，ans[j]表示j号人能获得的最好收入
     */

    /**
     * 有序表：把工作和能力放到一个Node里，然后根据难度和报酬进行排序
     * 比如难度3 报酬 10 难度3 报酬5的两份工作，那么难度3 报酬5的这份工作其实可以不用进行选择了，直接放弃
     */
    public static class Job {
        public int hard;
        public int money;

        public Job(int h, int m) {
            hard = h;
            money = m;
        }
    }

    public static class MyCon implements Comparator<Job> {
        @Override
        public int compare(Job o1, Job o2) {
            return o1.hard == o2.hard ? o2.money - o1.money : o1.hard - o2.hard;
        }
    }

    public static int[] getMoney(Job[] job, int[] ability) {
        // 这个排序结束后，每一份难度相同的工作，报酬高的就在前面
        Arrays.sort(job, new MyCon());

        TreeMap<Integer, Integer> map = new TreeMap<>();
        // 防止有的没有
        map.put(0, 0);
        map.put(job[0].hard, job[0].money);
        Job pre = job[0];
        for (int i = 0; i < job.length; i++) {
            // 如果上一份工作的难度和当前难度相同，那么不用加了，因为每一份相同难度的工作，报酬最高的一定在第一位
            if (job[i].hard != pre.hard && job[i].money > pre.money) {
                map.put(job[i].hard, job[i].money);
                pre = job[i];
            }
        }
        int[] ans = new int[ability.length];
        for (int i = 0; i < ans.length; i++) {
            // ability[i] 号人的能力值，<= ability[i] 离他最近的难度的工作，
            Integer money = map.floorKey(ability[i]);
            // 严格来说这里需要判空，但是我在上面加了一个 0 0 组合，如果没有就是0
            ans[i] = map.get(money);
        }
        return ans;
    }
}
