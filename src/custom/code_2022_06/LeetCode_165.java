package custom.code_2022_06;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName LeetCode_165
 * @Author Duys
 * @Description
 * @Date 2022/6/30 14:52
 **/
//165. 比较版本号
public class LeetCode_165 {

    // 1.01
    // 1.00001
    public static int compareVersion(String version1, String version2) {
        if (version1 == null || version2 == null) {
            return -2;
        }
        String[] s1 = version1.split("\\.");
        String[] s2 = version2.split("\\.");
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        for (String s : s1) {
            list1.add(Integer.valueOf(s));
        }
        for (String s : s2) {
            list2.add(Integer.valueOf(s));
        }
        if (list1.size() > list2.size()) {
            int tmp = list1.size() - list2.size();
            for (int i = 0; i < tmp; i++) {
                list2.add(0);
            }
        } else if (list1.size() < list2.size()) {
            int tmp = list2.size() - list1.size();
            for (int i = 0; i < tmp; i++) {
                list1.add(0);
            }
        }
        for (int i = 0; i < list1.size(); i++) {
            if (list1.get(i) > list2.get(i)) {
                return 1;
            }
            if (list1.get(i) < list2.get(i)) {
                return -1;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        String s1 = "1.0.1";
        String s2 = "1";
        System.out.println(compareVersion(s1, s2));
    }
}
