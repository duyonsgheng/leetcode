package custom.code_2023_02;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * @ClassName LeetCode_1797
 * @Author Duys
 * @Description
 * @Date 2023/2/9 10:34
 **/
// 1797. 设计一个验证系统
public class LeetCode_1797 {

    static class AuthenticationManager {
        Map<String, Integer> map;
        int time;

        public AuthenticationManager(int timeToLive) {
            map = new HashMap<>();
            time = timeToLive;
        }

        public void generate(String tokenId, int currentTime) {
            map.put(tokenId, currentTime + time);
        }

        public void renew(String tokenId, int currentTime) {
            if (!map.containsKey(tokenId)) {
                return;
            }
            if (map.get(tokenId) >= currentTime) {
                map.put(tokenId, currentTime + time);
            }
        }

        public int countUnexpiredTokens(int currentTime) {
            int ans = 0;
            for (String token : map.keySet()) {
                if (map.get(token) > currentTime) {
                    ans++;
                }
            }
            return ans;
        }
    }
}
