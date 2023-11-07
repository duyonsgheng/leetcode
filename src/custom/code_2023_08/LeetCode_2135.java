package custom.code_2023_08;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Mr.Du
 * @ClassName LeetCode_2135
 * @date 2023年08月30日
 */
// 2135. 统计追加字母可以获得的单词数
// https://leetcode.cn/problems/count-words-obtained-after-adding-a-letter/
public class LeetCode_2135 {
    public int wordCount(String[] startWords, String[] targetWords) {
        Map<Integer, Integer> target = new HashMap();
        for(String s : targetWords){
            int c = code(s);
            target.put(c, target.getOrDefault(c, 0) + 1);
        }

        int ans = 0;
        for(String s : startWords){
            int c = code(s);
            for(int i = 0; i < 26; i++){
                int letter = 1 << i, search = c | letter;
                if((c & letter) == 0 && target.containsKey(search)){
                    ans += target.get(search);
                    target.remove(search);
                }
            }
        }

        return ans;
    }

    private int code(String s){
        int res = 0;
        for(char c : s.toCharArray()){
            res |= (1 << (c - 'a'));
        }
        return res;
    }
}
