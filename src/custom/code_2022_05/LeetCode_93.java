package custom.code_2022_05;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @ClassName LeetCode_93
 * @Author Duys
 * @Description
 * @Date 2022/5/17 11:24
 **/
// 93. 复原 IP 地址
// 有效 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。
//例如："0.1.2.201" 和 "192.168.1.1" 是 有效 IP 地址，但是 "0.011.255.245"、"192.168.1.312" 和 "192.168@1.1" 是 无效 IP 地址。
//给定一个只包含数字的字符串 s ，用以表示一个 IP 地址，返回所有可能的有效 IP 地址，这些地址可以通过在 s 中插入'.' 来形成。
//你不能重新排序或删除 s 中的任何数字。你可以按 任何 顺序返回答案。
//链接：https://leetcode.cn/problems/restore-ip-addresses
public class LeetCode_93 {
    //输入：s = "101023"
    //输出：["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
    public static List<String> restoreIpAddresses(String s) {
        int len = s.length();
        List<String> res = new ArrayList<>();
        // 如果长度不够，不搜索
        if (len < 4 || len > 12) {
            return res;
        }
        List<String> path = new ArrayList<>(4);
        dfs(s, len, 0, 0, path, res);
        return res;
    }

    // len - 总长度
    // split - 分割了几段了
    // begin - 从哪些开始
    private static void dfs(String s, int len, int split, int begin, List<String> path, List<String> res) {
        if (begin == len) {
            if (split == 4) {
                res.add(String.join(".", path));
            }
            return;
        }
        // 剪枝
        // 看到剩下的不够了，就退出，len - begin 表示剩余的还未分割的字符串的位数
        if (len - begin < (4 - split) || len - begin > 3 * (4 - split)) {
            return;
        }
        for (int i = 0; i < 3; i++) {
            if (begin + i >= len) {
                break;
            }
            int ipSegment = get(s, begin, begin + i);
            if (ipSegment == -1) {
                continue;
            }
            // 在判断是 ip 段的情况下，才去做截取
            path.add(ipSegment + "");
            dfs(s, len, split + 1, begin + i + 1, path, res);
            path.remove(path.size() - 1);
        }
    }

    private static int get(String s, int left, int right) {
        int len = right - left + 1;
        // 大于 1 位的时候，不能以 0 开头
        if (len > 1 && s.charAt(left) == '0') {
            return -1;
        }
        // 转成 int 类型
        int res = 0;
        for (int i = left; i <= right; i++) {
            res = res * 10 + s.charAt(i) - '0';
        }
        if (res > 255) {
            return -1;
        }
        return res;
    }

    public static void main(String[] args) {
        List<String> stringList = restoreIpAddresses("101023");
        System.out.println();
    }
}
