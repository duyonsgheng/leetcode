package custom.code_2022_09;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName LeetCode_811
 * @Author Duys
 * @Description
 * @Date 2022/9/22 18:06
 **/
// 811. 子域名访问计数
public class LeetCode_811 {
    // cpdomains = ["900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org"]
    public static List<String> subdomainVisits(String[] cpdomains) {
        Map<String, Integer> count = new HashMap<>();
        for (String w : cpdomains) {
            String[] cur = w.split(" ");
            int cnt = Integer.valueOf(cur[0]);
            String str = cur[1];
            process(count, cnt, str);
        }
        List<String> ans = new ArrayList<>();
        for (String key : count.keySet()) {
            ans.add(count.get(key) + " " + key);
        }
        return ans;
    }

    public static void process(Map<String, Integer> count, int cnt, String str) {
        String[] arr = str.split("\\.");
        int n = arr.length;
        String pre = arr[n - 1];
        count.put(pre, count.getOrDefault(pre, 0) + cnt);
        for (int i = n - 2; i >= 0; i--) {
            String cur = arr[i] + "." + pre;
            count.put(cur, count.getOrDefault(cur, 0) + cnt);
            pre = cur;
        }
    }

    public static void main(String[] args) {
        String[] arr = {"900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org"};
        subdomainVisits(arr);
    }

}
