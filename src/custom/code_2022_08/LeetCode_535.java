package custom.code_2022_08;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName LeetCode_535
 * @Author Duys
 * @Description
 * @Date 2022/8/23 14:11
 **/
// 535. TinyURL 的加密与解密
public class LeetCode_535 {

    public class Codec {
        private int id;
        private String ip = "http://tinyurl.com/";
        private Map<Integer, String> map = new HashMap<>();

        // Encodes a URL to a shortened URL.
        public String encode(String longUrl) {
            id++;
            map.put(id, longUrl);
            return ip + id;
        }

        // Decodes a shortened URL to its original URL.
        public String decode(String shortUrl) {
            int index = shortUrl.lastIndexOf("/") + 1;
            int id_ = Integer.parseInt(shortUrl.substring(index));
            return map.get(id_);
        }
    }
}
