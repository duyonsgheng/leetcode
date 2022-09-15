package duys_code.day_27;

import java.util.Arrays;

/**
 * @ClassName Code_01_PickBonds
 * @Author Duys
 * @Description
 * @Date 2021/11/22 10:59
 **/
public class Code_01_PickBonds {
    /**
     * 题意：
     * 每一个项目都有三个数，[a,b,c]表示项目a和b乐队参演，花费为c
     * 给定很多个项目int[][] programs
     * 每一个乐队可能在多个项目里都出现了，但是只能挑一次
     * nums是可以挑选的项目数量，所以一定会有nums*2只乐队被挑选出来
     * 返回一共挑nums轮(也就意味着一定请到所有的乐队)，最少花费是多少？
     * nums < 9, programs长度小于500，每组测试乐队的全部数量一定是nums*2，且标号一定是0 ~ nums*2-1
     */

    /**
     * 1、每一个项目都有三个数。[a,b,c]表示 a和b乐队都参与，花费是c
     * 2.每一个乐队可能在多个项目里出现，但是只能挑选一次
     * 3.nums是可以挑选的项目数量，而每一个项目都有两个乐队，所以一定会有nums*2支乐队当选
     * 4.乐队数量是nums*2 那么标号就是0~nums*2 -1
     */

    public static int minCost(int[][] programs, int nums) {
        if (programs == null || programs[0] == null || nums == 0) {
            return 0;
        }
        // 有效的
        int size = clean(programs);
        int[] map1 = init(1 << (nums << 1));
        int[] map2 = null;

        if ((nums & 1) == 0) {
            process(programs, size, 0, 0, 0, nums >> 1, map1);
            map2 = map1;
        } else {
            // 拆分选择，比如当前是 9 先选则 map1 是 4，map2是5
            process(programs, size, 0, 0, 0, nums >> 1, map1);
            map2 = init(1 << (nums << 1));
            process(programs, size, 0, 0, 0, nums - (nums >> 1), map2);
        }
        int mask = (1 << (nums << 1)) - 1;
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < map1.length; i++) {
            if (map1[i] != Integer.MAX_VALUE && map2[mask & (~i)] != Integer.MAX_VALUE) {
                ans = Math.min(map1[i] + map2[mask & (~i)], ans);
            }
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    // 从左往右尝试
    public static void process(int[][] pro, int size, int index, int status, int cost, int rest, int[] map) {
        // 还需要挑选几个乐队
        if (rest == 0) {
            map[status] = Math.min(cost, map[status]);
        } else {
            if (index != size) {
                // 当前位置的乐队不要
                process(pro, size, index + 1, status, cost, rest, map);
                // 搞一个状态，二进制位置来表示乐队是否选了，把已经选了的乐队，对应的位置置为1
                int pick = 0 | (1 << pro[index][0]) | (1 << pro[index][1]);
                // 说明和status不冲突
                if ((pick & status) == 0) {
                    process(pro, size, index + 1, status | pick, cost + pro[index][2], rest - 1, map);
                }
            }
        }
    }

    // 洗掉多余的无效的参数
    public static int clean(int[][] pro) {
        int x = 0;
        int y = 0;
        // 数组的内部按照乐队的编号排序
        for (int[] p : pro) {
            x = Math.min(p[0], p[1]);
            y = Math.max(p[0], p[1]);
            p[0] = x;
            p[1] = y;
        }
        Arrays.sort(pro, (a, b) -> a[0] != b[0] ? (a[0] - b[0]) : (a[1] != b[1] ? (a[1] - b[1]) : (a[2] - b[2])));
        // 编号最小的乐队
        x = pro[0][0];
        y = pro[0][1];
        int n = pro.length;
        for (int i = 1; i < n; i++) {
            // 相同的乐队，可以不需要留下了，因为花费最小的我已经记录下来了
            if (pro[i][0] == x && pro[i][1] == y) {
                pro[i] = null;
            } else {
                x = pro[i][0];
                y = pro[i][1];
            }
        }
        int size = 1;
        for (int i = 1; i < n; i++) {
            if (pro[i] != null) {
                pro[size++] = pro[i];
            }
        }
        return size;
    }

    public static int[] init(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = Integer.MAX_VALUE;
        }
        return arr;
    }
}
