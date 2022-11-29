package custom.code_2022_11;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName LeetCode_1239
 * @Author Duys
 * @Description
 * @Date 2022/11/29 9:57
 **/
// 1239. 串联字符串的最大长度
public class LeetCode_1239 {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("aa");
        list.add("bb");
        System.out.println(maxLength(list));
    }

    public static int maxLength(List<String> arr) {
        int ans = 0;
        List<Integer> masks = new ArrayList<>();
        masks.add(0);
        for (String s : arr) {
            int cur = 0;
            for (int i = 0; i < s.length(); i++) {
                int mask = s.charAt(i) - 'a';
                if ((cur & (1 << mask)) != 0) {
                    cur = 0;
                    break;
                }
                cur |= (1 << mask);
            }
            // 当前的字符串不行
            if (cur == 0) {
                continue;
            }
            // 校验一下子
            int n = masks.size();
            for (int i = 0; i < n; i++) {
                int mask = masks.get(i);
                if ((mask & cur) == 0) { // 说明和之前的没有重复
                    masks.add(cur | mask);
                    ans = Math.max(ans, Integer.bitCount(cur | mask));
                }
            }
        }
        return ans;
    }
}
