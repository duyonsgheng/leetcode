package week.week_2022_07_03;

/**
 * @ClassName Code_02_LeetCode_678
 * @Author Duys
 * @Description
 * @Date 2022/7/21 8:50
 **/
// 678. 有效的括号字符串
// 给定一个只包含三种字符的字符串：（，）和 *，写一个函数来检验这个字符串是否为有效字符串。有效字符串具有如下规则：
//  1.任何左括号 (必须有相应的右括号 )。
//  2.任何右括号 )必须有相应的左括号 (。
//  3.左括号 ( 必须在对应的右括号之前 )。
//  4.*可以被视为单个右括号 )，或单个左括号 (，或一个空字符串。
//  5.一个空字符串也被视为有效字符串。
// 字符串大小将在 [1，100] 范围内。
//链接：https://leetcode.cn/problems/valid-parenthesis-string
public class Code_02_LeetCode_678 {

    // 首先看到的是数据量，我们可以用递归来改动态规划，实在不行，挂一个缓存就行
    // 普通的解- 动态规划
    public boolean checkValidString(String s) {
        if (s == null || s.length() < 1) {
            return false;
        }
        int n = s.length();
        int dp[][] = new int[n + 1][n + 1];
        return process(s.toCharArray(), 0, 0, dp);
    }

    public boolean process(char[] str, int index, int count, int dp[][]) {
        if (index == str.length) {
            return count == 0;
        }
        if (count < 0) {
            return false;
        }
        if (dp[index][count] != 0) {
            return dp[index][count] == 1;
        }
        boolean asn = false;
        if (str[index] == '(') {
            asn = process(str, index + 1, count + 1, dp);
        } else if (str[index] == ')') {
            asn = process(str, index + 1, count - 1, dp);
        } else {
            // 说明当前的是 * 几种选择了
            // 可以是左括号，可以是右括号，可以啥也不是
            asn = process(str, index + 1, count + 1, dp)
                    || process(str, index + 1, count - 1, dp)
                    || process(str, index + 1, count, dp);
        }
        dp[index][count] = asn ? 1 : -1;
        return asn;
    }

    // 但是这个题有贪心的点
    // 依然是使用count来区分，但是我们可以通过 * 号 来计算count的上限和下限，下限只要能达到0，就说明可以做到
    public boolean checkValidString1(String s) {
        if (s == null || s.length() < 1) {
            return false;
        }
        char[] str = s.toCharArray();
        int max = 0;// count的上限
        int min = 0;// count的下限
        for (char x : str) {
            if (x == '(') {
                max++;
                min++;
            } else {
                if (x == ')' && max <= 0) { // 光是 ) 导致不合法了，超了
                    return false;
                }
                if (x == ')') { // 左括号，没啥说的，count --
                    max -= 1;
                } else { // 如果是 * 号，能变成右括号，能推高 count
                    max += 1;
                }
                // 如果是，如果是 * 号 我们的min 可能会减少
                if (min > 0) {
                    min -= 1;
                }
            }
        }
        return min == 0;
    }
}
