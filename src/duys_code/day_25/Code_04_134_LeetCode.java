package duys_code.day_25;

/**
 * @ClassName Code_04_134_LeetCode
 * @Author Duys
 * @Description 力扣：https://leetcode-cn.com/problems/gas-station/
 * @Date 2021/11/17 13:42
 **/
public class Code_04_134_LeetCode {
    // 找到良好的出发位置

    // gas[i] = 2 表示当前i号加油站有2升汽油
    // cost[i] = 4 表示当前去往i号加油站需要消耗4格汽油
    public static int canCompleteCircuit(int[] gas, int[] cost) {
        if (gas == null || gas.length == 0) {
            return -1;
        }
        if (gas.length == 1) {
            return gas[0] < cost[0] ? -1 : 0;
        }
        boolean[] good = stations(cost, gas);
        for (int i = 0; i < good.length; i++) {
            if (good[i]) {
                return i;
            }
        }
        return -1;
    }

    public static boolean[] stations(int[] cost, int[] gas) {
        if (cost == null || gas == null || cost.length < 2 || gas.length != cost.length) {
            return null;
        }
        int init = changeDisArrayGetInit(cost, gas);
        if (init == -1) {
            // 没有可能
            return new boolean[cost.length];
        }
        return process(cost, init);
    }

    // 把我们的数组改造一下，把当前加油站油量-去来往当前加油站需要的油，就是我们说的良好出发点
    public static int changeDisArrayGetInit(int[] cost, int[] gas) {
        int init = -1;
        for (int i = 0; i < cost.length; i++) {
            cost[i] = gas[i] - cost[i];
            if (cost[i] >= 0) {
                init = i;
            }
        }
        return init;
    }

    public static boolean[] process(int[] costs, int init) {
        boolean[] res = new boolean[costs.length];
        // 出发点
        int start = init;
        // 当前这一轮的结束位置
        int end = nextIndex(init, costs.length);
        // 如果别人出发到我需要5，那么need就是5
        int need = 0;
        // rest 是没扩展一个位置，我的rest就要减小或者增加，因为可能下一个位置的油量是正数
        int rest = 0;
        do {
            // 当前来到的start已经在联通区里面了，可以确定后续的点一定午发转完一圈，因为已经存在的联通区，当时没有扩出去，新来的点已经在联通区里面，一定也是扩不出去的
            if (start != init && start == lastIndex(end, costs.length)) {
                break;
            }
            // 当前来到的start不在联通区里面
            // need 表示你要连到联通区来，至少需要多少油
            if (costs[start] < need) {
                need -= costs[start];
            } else {
                rest += costs[start] - need;
                need = 0;
                while (rest >= 0 && end != start) {
                    rest += costs[end];
                    end = nextIndex(end, costs.length);
                }
                if (rest >= 0) {
                    res[start] = true;
                    connectGood(costs, lastIndex(start, costs.length), init, res);
                    break;
                }
            }
            start = lastIndex(start, costs.length);
        } while (start != init);
        return res;
    }

    public static void connectGood(int[] cost, int start, int init, boolean[] res) {
        int need = 0;
        while (start != init) {
            if (cost[start] < need) {
                need -= cost[start];
            } else {
                res[start] = true;
                need = 0;
            }
            start = lastIndex(start, cost.length);
        }
    }

    // 在环形中找到上一个位置
    public static int lastIndex(int cur, int size) {
        return cur == 0 ? size - 1 : cur - 1;
    }

    // 在环形中找到下一个下标
    public static int nextIndex(int cur, int size) {
        return cur == size - 1 ? 0 : size + 1;
    }
}
