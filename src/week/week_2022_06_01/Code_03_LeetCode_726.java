package week.week_2022_06_01;

import java.util.TreeMap;

/**
 * @ClassName Code_03_LeetCode_726
 * @Author Duys
 * @Description
 * @Date 2022/6/9 13:40
 **/
// 726. 原子的数量
// 给你一个字符串化学式 formula ，返回 每种原子的数量 。
//输入：formula = "Mg(OH)2"
//输出："H2MgO2"
//解释：原子的数量是 {'H': 2, 'Mg': 1, 'O': 2}。
public class Code_03_LeetCode_726 {

    // 著名的算法原型 ：大厂刷题班 第8节 第一题
    // 当我遇到左括号的时候，调子过程，然后子过程返回给我它的结果，和算到了什么位置结束的。父过程拿着子过程的结果接着算
    public static String countOfAtoms(String formula) {
        if (formula == null || formula.length() <= 0) {
            return formula;
        }
        char[] s = formula.toCharArray();
        Info info = process(s, 0);
        StringBuilder builder = new StringBuilder();
        for (String key : info.countMap.keySet()) {
            builder.append(key);
            int cnt = info.countMap.get(key);
            if (cnt > 1) {
                builder.append(cnt);
            }
        }
        return builder.toString();
    }

    public static Info process(char[] str, int i) {
        TreeMap<String, Integer> countMap = new TreeMap<>();
        int cnt = 0;
        StringBuilder builder = new StringBuilder();
        Info info = null;
        while (i < str.length && str[i] != ')') {
            if (str[i] >= 'A' && str[i] <= 'Z' || str[i] == '(') {
                // 收集子过程的答案
                if (builder.length() != 0 || info != null) {
                    cnt = cnt == 0 ? 1 : cnt;
                    if (builder.length() != 0) {
                        String key = builder.toString();
                        countMap.put(key, countMap.getOrDefault(key, 0) + cnt);
                        builder.delete(0, builder.length());
                    } else {
                        for (String key : info.countMap.keySet()) {
                            countMap.put(key, countMap.getOrDefault(key, 0) + info.countMap.get(key) * cnt);
                        }
                        info = null;
                    }
                    cnt = 0;
                }
                if (str[i] == '(') {
                    info = process(str, i + 1);
                    i = info.end + 1;
                } else {
                    builder.append(str[i++]);
                }
            } else if (str[i] >= 'a' && str[i] <= 'z') {
                builder.append(str[i++]);
            } else {
                cnt = cnt * 10 + str[i++] - '0';
            }
        }

        if (builder.length() != 0 || info != null) {
            cnt = cnt == 0 ? 1 : cnt;
            if (builder.length() != 0) {
                String key = builder.toString();
                countMap.put(key, countMap.getOrDefault(key, 0) + cnt);
                builder.delete(0, builder.length());
            } else {
                for (String key : info.countMap.keySet()) {
                    countMap.put(key, countMap.getOrDefault(key, 0) + info.countMap.get(key) * cnt);
                }
                info = null;
            }
            cnt = 0;
        }
        return new Info(countMap, i);
    }

    // 子过程返回的结果信息
    public static class Info {
        // 元素对应的个数
        public TreeMap<String, Integer> countMap;
        // 算到哪个位置了
        public int end;

        public Info(TreeMap<String, Integer> map, int e) {
            countMap = map;
            end = e;
        }
    }
}
