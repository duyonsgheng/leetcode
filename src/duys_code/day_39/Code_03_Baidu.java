package duys_code.day_39;

/**
 * @ClassName Code_03_Baidu
 * @Author Duys
 * @Description
 * @Date 2021/12/21 10:32
 **/
public class Code_03_Baidu {
    // 来自百度
    // 给定一个字符串str，和一个正数k
    // str子序列的字符种数必须是k种，返回有多少子序列满足这个条件
    // 已知str中都是小写字母
    // 原始是取mod
    // 本节在尝试上，最难的
    // 搞出桶来，组合公式

    // 1.不同的位置的字符组成的都算一种
    // 2.比如a 10个 b 9个 c 8个 d 6个 e 5个， k = 3
    // 3.组成的字符串可以有1个a 2个a 3个a 4个a 5个a ....等等都是算不同的

    public static int nums(String s, int k) {
        if (s == null || s.length() <= 0 || k < 0) {
            return 0;
        }
        char[] str = s.toCharArray();
        int[] buckets = new int[str.length];
        for (char c : str) {
            buckets[c - 97]++;
        }
        return process(buckets, 0, k);
    }

    // buck - 词频统计
    // index - 来到哪个位置做选择
    // rest - 还剩下几种需要选
    public static int process(int[] buck, int index, int rest) {
        if (index == buck.length) {
            return rest == 0 ? 1 : 0;
        }
        // 要当前字符，和不要当前字符
        int p1 = process(buck, index + 1, rest);
        int p2 = 0;
        if (rest > 0) {
            // 算当前的因为每个位置都是算不同的
            // 并且可以有一个有两个。。。
            p2 = cnn(buck[index]) * process(buck, index + 1, rest - 1);
        }
        return p1 + p2;
    }

    public static int cnn(int n) {
        return (1 << n) - 1;
    }
}
