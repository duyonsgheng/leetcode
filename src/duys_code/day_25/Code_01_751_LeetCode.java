package duys_code.day_25;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @ClassName Code_01_715_LeetCode
 * @Author Duys
 * @Description 力扣：https://leetcode-cn.com/problems/ip-to-cidr/
 * @Date 2021/11/15 20:28
 **/
public class Code_01_751_LeetCode {
    /**
     * 题意：
     * 给定一个起始 IP 地址 ip 和一个我们需要包含的 IP 的数量 n，返回用列表（最小可能的长度）表示的 CIDR块的范围。
     * CIDR 块是包含 IP 的字符串，后接斜杠和固定长度。例如：“123.45.67.89/20”。固定长度 “20” 表示在特定的范围中公共前缀位的长度。
     */
    public static List<String> ipToCIDR(String ip, int n) {
        // 把当前的ip转成一个基础的int 类型的状态，再这个状态上去扩展
        int cur = status(ip);
        // 当前的ip最右侧的1能表示2的几次方
        int maxPower = 0;
        // 再cur的基础上已经扩展了多少个ip
        int solved = 0;
        //
        int power = 0;
        List<String> ans = new ArrayList<>();
        while (n > 0) {
            maxPower = mostRightPower(cur);
            solved = 1;
            power = 0; // 每一轮总共有几个ip产生
            // 这里为了防止溢出
            while ((solved << 1) <= n && (power + 1) <= maxPower) {
                solved <<= 1;// 每次向右移动一位，就是2的次方依次升高
                power++;
            }
            ans.add(content(cur, power));
            n -= solved;
            cur += solved;
        }
        return ans;
    }

    public static String content(int status, int power) {
        StringBuilder builder = new StringBuilder();
        for (int move = 24; move >= 0; move -= 8) {
            builder.append(((status & (255 << move)) >>> move) + ".");
        }
        builder.setCharAt(builder.length() - 1, '/');
        builder.append(32 - power);
        return builder.toString();
    }

    public static HashMap<Integer, Integer> map = new HashMap<>();

    // 当前num的最右侧1表示的是2的几次方
    public static int mostRightPower(int num) {
        // map只会生成1次，以后直接用
        if (map.isEmpty()) {
            map.put(0, 32);
            for (int i = 0; i < 32; i++) {
                // 00...0000 00000001 2的0次方
                // 00...0000 00000010 2的1次方
                // 00...0000 00000100 2的2次方
                // 00...0000 00001000 2的3次方
                map.put(1 << i, i);
            }
        }
        // num & (-num) -> num & (~num+1) -> 提取出最右侧的1
        return map.get(num & (-num));
    }

    // ip -> int(32位状态)
    // 255.255.255.255
    // 8位 8位 8位 8位
    public static int status(String ip) {
        int ans = 0;
        int move = 24;
        for (String str : ip.split("\\.")) {
            // 17.23.16.5 "17" "23" "16" "5"
            // "17" -> 17 << 24
            // "23" -> 23 << 16
            // "16" -> 16 << 8
            // "5" -> 5 << 0
            ans |= Integer.valueOf(str) << move;
            move -= 8;
        }
        return ans;
    }
}
