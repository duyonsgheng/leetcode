package custom.code_2022_11;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName LeetCode_1023
 * @Author Duys
 * @Description
 * @Date 2022/11/3 16:11
 **/
// 1023. 驼峰式匹配
public class LeetCode_1023 {
    // queries = ["FooBar","FooBarTest","FootBall","FrameBuffer","ForceFeedBack"],
    // pattern = "FoBa"
    public static List<Boolean> camelMatch1(String[] queries, String pattern) {
        List<Boolean> ans = new ArrayList<>();
        List<String> pats = build(pattern);
        // Fo  Ba
        for (String str : queries) {
            // Foo Bar
            ans.add(patch(str, pats));
        }
        return ans;
    }

    public static boolean patch(String str, List<String> pats) {
        List<String> strs = build(str);
        int n = strs.size();
        if (n != pats.size()) {
            return false;
        }
        for (int i = 0; i < n; i++) {
            String source = strs.get(i);
            String pa = pats.get(i);
            int q = 0;
            for (int t = 0; t < source.length(); t++) {
                if (q >= pa.length()) {
                    break;
                }
                if (q < pa.length() && source.charAt(t) == pa.charAt(q)) {
                    q++;
                }
            }
            if (q < pa.length()) {
                return false;
            }
        }
        return true;
    }

    public static List<String> build(String str) {
        char[] arr = str.toCharArray();
        int l = 0;
        int r = 0;
        int n = arr.length;
        // F o B a
        // 0 1 2 3
        List<String> list = new ArrayList<>();
        while (r < n) {
            if (arr[r] >= 'A' && arr[r] <= 'Z') {
                l = r;
                break;
            }
            r++;
        }
        while (r < n) {
            if (arr[r] >= 'A' && arr[r] <= 'Z' && l != r) {
                list.add(str.substring(l, r));
                l = r;
            }
            r++;
        }
        if (l < r) {
            list.add(str.substring(l, r));
        }
        return list;
    }

    public static void main(String[] args) {
        //["uAxaqlzahfialcezsLfj","cAqlzyahaslccezssLfj","AqlezahjarflcezshLfj","AqlzofahaplcejuzsLfj","tAqlzahavslcezsLwzfj","AqlzahalcerrzsLpfonj","AqlzahalceaczdsosLfj","eAqlzbxahalcezelsLfj"]
        //"AqlzahalcezsLfj"
        String[] strs = {"uAxaqlzahfialcezsLfj", "cAqlzyahaslccezssLfj", "AqlezahjarflcezshLfj", "AqlzofahaplcejuzsLfj", "tAqlzahavslcezsLwzfj", "AqlzahalcerrzsLpfonj", "AqlzahalceaczdsosLfj", "eAqlzbxahalcezelsLfj"};
        String pa = "AqlzahalcezsLfj";
        System.out.println(camelMatch1(strs, pa));
        System.out.println(camelMatch(strs, pa));

    }

    public static List<Boolean> camelMatch(String[] queries, String pattern) {
        List<Boolean> ans = new ArrayList<>();
        char[] chars = pattern.toCharArray();
        int len = chars.length;
        for (int i = 0; i < queries.length; i++) {
            int index = 0;
            boolean cur = true;
            //         uAxaqlzahfialcezsLfj
            //chars =  AqlzahalcezsLfj
            for (char c : queries[i].toCharArray()) {
                // 如果匹配上了就跳下一个
                if (index < len && c == chars[index]) {
                    index++;
                } else if (c >= 'A' && c <= 'Z') { // 如果存在多余的大写字母，不行了
                    cur = false;
                    break;
                }
            }
            ans.add(cur ? index == len : false);
        }
        return ans;
    }
}
