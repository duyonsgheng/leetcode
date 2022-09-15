package week.week_2022_08_04;

/**
 * @ClassName Code_06_MinCostMostE
 * @Author Duys
 * @Description
 * @Date 2022/8/25 13:29
 **/
// 来自网易
// 小红拿到了一个仅由r、e、d组成的字符串
// 她定义一个字符e为"好e" : 当且仅当这个e字符和r、d相邻
// 例如"reeder"只有一个"好e"，前两个e都不是"好e"，只有第三个e是"好e"
// 小红每次可以将任意字符修改为任意字符，即三种字符可以相互修改
// 她希望"好e"的数量尽可能多
// 小红想知道，自己最少要修改多少次
// 输入一个只有r、e、d三种字符的字符串
// 长度 <= 2 * 10^5
// 输出最小修改次数
public class Code_06_MinCostMostE {
    public static int minCost(String str) {
        int n = str.length();
        if (n < 3) {
            return -1;
        }
        // 转化 d=0，e=1，r=2
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            char cur = str.charAt(i);
            if (cur == 'e') {
                arr[i] = 1;
            } else if (cur == 'd') {
                arr[i] = 0;
            } else {
                arr[i] = 2;
            }
        }
        // 通过上面的转化，问题变成了：
        // 1的左右，一定要被0和2包围，这个1才是"好1"
        // 请让"好1"的尽量多，返回最少的修改代价
        int maxGood = 0;
        int minCost = Integer.MIN_VALUE;
        for (int prepre = 0; prepre < 3; prepre++) {
            for (int pre = 0; pre < 3; pre++) {
                int cost = arr[0] == prepre ? 0 : 1;
                cost += arr[1] == pre ? 0 : 1;
                int[] cur = process(arr, 2, prepre, pre);
                if (cur[0] > maxGood) {
                    maxGood = cur[0];
                    minCost = cur[1] + cost;
                } else if (cur[0] == maxGood) {
                    minCost = Math.min(minCost, cur[1] + cost);
                }
            }
        }
        return minCost;
    }

    // int[] ans ans[0] 几个好1，ans[1] 最小的代价
    // 0-d
    // e-1
    // r-2
    // 0 1 2 1 0 1 2 1 0
    public static int[] process(int[] arr, int index, int prepre, int pre) {
        // 已经来到最后了
        if (index == arr.length) {
            return new int[]{0, 0};
        }
        // p1 arr[index] 变0
        int p1V = prepre == 2 && pre == 1 ? 1 : 0;
        int p1C = arr[index] == 0 ? 0 : 1;
        int[] p1Next = process(arr, index + 1, pre, 0);
        p1V += p1Next[0];
        p1C += p1Next[1];
        // p2 arr[index] 变1
        int p2V = 0;
        int p2C = arr[index] == 1 ? 0 : 1;
        int[] p2Next = process(arr, index + 1, pre, 1);
        p2V += p2Next[0];
        p2C += p2Next[1];
        // p3 arr[index] 变2
        int p3V = prepre == 0 && pre == 1 ? 1 : 0;
        int p3C = arr[index] == 2 ? 0 : 1;
        int[] p3Next = process(arr, index + 1, pre, 2);
        p3V += p3Next[0];
        p3C += p3Next[1];
        // 开始决策，选出三种可能性中的最优解
        int maxGood = 0;
        int minCost = Integer.MIN_VALUE;
        if (p1V > maxGood) {
            maxGood = p1V;
            minCost = p1C;
        } else if (p1V == maxGood) {
            minCost = Math.min(minCost, p1C);
        }

        if (p2V > maxGood) {
            maxGood = p2V;
            minCost = p2C;
        } else if (p2V == maxGood) {
            minCost = Math.min(minCost, p2C);
        }

        if (p3V > maxGood) {
            maxGood = p3V;
            minCost = p3C;
        } else if (p3V == maxGood) {
            minCost = Math.min(minCost, p3C);
        }
        return new int[]{maxGood, minCost};
    }
}
