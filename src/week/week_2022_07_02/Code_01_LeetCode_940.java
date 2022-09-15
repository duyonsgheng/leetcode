package week.week_2022_07_02;

/**
 * @ClassName Code_01_LeetCode_940
 * @Author Duys
 * @Description
 * @Date 2022/7/14 9:41
 **/
// 940. 不同的子序列 II
// s 仅由小写英文字母组成
public class Code_01_LeetCode_940 {

    /**
     * 1.不能重复，算以当前字符结尾的时候有哪些子序列
     * 2.来到新得字符时，再之前已经存在的基础上加上当前字符，把之前的继承，然后还需要排除已经存在的相同字面量
     */
    public static int distinctSubseqII(String s) {
        if (s == null || s.length() < 1) {
            return 0;
        }
        int m = 1000000007;
        char[] str = s.toCharArray();
        // 小写字母
        int[] count = new int[26];
        // 之前的一共多少了
        int all = 1;// 算空集
        for (char c : str) {
            // 新增的 - 纯新增的，需要减去之前的，就是排除重复的
            int add = (all - count[c - 'a'] + m) % m;
            all = (add + all) % m;
            count[c - 'a'] = (count[c - 'a'] + add) % m;
        }
        // 需要去掉空集
        return all - 1;
    }
}
