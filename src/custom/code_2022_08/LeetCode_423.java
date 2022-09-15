package custom.code_2022_08;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName LeetCode_423
 * @Author Duys
 * @Description
 * @Date 2022/8/9 16:33
 **/
//423. 从英文中重建数字
public class LeetCode_423 {

    // e	0 1 3 5 7 8 9
    //f	4 5
    //g	8
    //h	3 8
    //i	5 6 8 9
    //n	1 7 9
    //o	0 1 2 4
    //r	0 3 4
    //s	6 7
    //t	2 3 8
    //u	4
    //v	5 7
    //w	2
    //x	6
    //z	0
    // 先词频统计
    // 观察唯一性 0中的z只出现了1次 8中的g也只出现了依次 6中的s也只出现了依次
    public String originalDigits(String s) {
        if (s == null || s.length() <= 0) {
            return s;
        }
        char[] str = s.toCharArray();
        Map<Character, Integer> countMap = new HashMap<>();
        for (char c : str) {
            countMap.put(c, countMap.getOrDefault(c, 0) + 1);
        }
        int[] count = new int[10];
        // 先算单个
        count[0] = countMap.getOrDefault('z', 0);
        count[8] = countMap.getOrDefault('g', 0);
        count[2] = countMap.getOrDefault('w', 0);
        count[6] = countMap.getOrDefault('x', 0);
        count[4] = countMap.getOrDefault('u', 0);

        // 算多个
        count[3] = countMap.getOrDefault('h', 0) - count[8];
        count[7] = countMap.getOrDefault('s', 0) - count[6];
        count[5] = countMap.getOrDefault('f', 0) - count[4];

        count[1] = countMap.getOrDefault('o', 0) - count[0] - count[2] - count[4];
        count[9] = countMap.getOrDefault('i', 0) - count[5] - count[8] - count[6];
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < count[i]; j++) {
                builder.append(i);
            }
        }
        return builder.toString();
    }
}
