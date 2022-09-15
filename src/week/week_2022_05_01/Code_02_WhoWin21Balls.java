package week.week_2022_05_01;

/**
 * @ClassName Code_02_WhoWin21Balls
 * @Author Duys
 * @Description
 * @Date 2022/5/7 15:52
 **/
// 甲乙两个人去拿数，谁先拿到偶数谁赢了
// 来自微众
// 人工智能岗
// 一开始有21个球，甲和乙轮流拿球，甲先、乙后
// 每个人在自己的回合，一定要拿不超过3个球，不能不拿
// 最终谁的总球数为偶数，谁赢
// 请问谁有必胜策略
public class Code_02_WhoWin21Balls {
    // 博弈问题，都是考验递归

    public static String win(int balls) {
        return process(0, balls, 0, 0);
    }

    // cur - 当前是谁的回合 0 -甲 1- 乙
    // rest - 还剩下多少个数
    // jia - 甲拿了多少了
    // yi - 乙拿了多少了
    public static String process(int cur, int rest, int jia, int yi) {
        if (rest == 0) {
            return (jia & 1) == 0 ? "甲" : "乙";
        }
        // 甲的回合
        if (cur == 0) {
            for (int i = 1; i <= Math.min(rest, 3); i++) {
                String next = process(1, rest - i, jia + i, yi);
                if (next.equals("甲")) {
                    return "甲";
                }
            }
            return "乙";
        }
        // 乙的回合
        else {
            for (int i = 1; i <= Math.min(rest, 3); i++) {
                String next = process(0, rest - i, jia, yi + i);
                if (next.equals("乙")) {
                    return "乙";
                }
            }
            return "甲";
        }
    }

    public static void main(String[] args) {
        System.out.println(win(9));
    }
}
