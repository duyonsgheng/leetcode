package custom.code_2022_05;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName LeetCode_89
 * @Author Duys
 * @Description TODO
 * @Date 2022/5/17 10:03
 **/
// 89. 格雷编码
//n 位格雷码序列 是一个由 2n 个整数组成的序列，其中：
//每个整数都在范围 [0, 2n - 1] 内（含 0 和 2n - 1）
//第一个整数是 0
//一个整数在序列中出现 不超过一次
//每对 相邻 整数的二进制表示 恰好一位不同 ，且
//第一个 和 最后一个 整数的二进制表示 恰好一位不同
//给你一个整数 n ，返回任一有效的 n 位格雷码序列 。
//链接：https://leetcode.cn/problems/gray-code
public class LeetCode_89 {

    public static List<Integer> grayCode(int n) {
        List<Integer> ret = new ArrayList<>();
        // 最大就是 n个1的二进制数
        for (int i = 0; i < (1 << n); i++) {
            ret.add((i >> 1) ^ i); // 比如当前i是3  11 01 ^ 11 = 10
        }
        return ret;
    }

    public static void main(String[] args) {
        int m = (int) Math.pow(2, 16);
        List<Integer> res = grayCode(2);
        System.out.println(Integer.toBinaryString(m - 1));
    }
}
