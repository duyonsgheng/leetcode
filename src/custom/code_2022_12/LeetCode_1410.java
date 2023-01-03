package custom.code_2022_12;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName LeetCode_1410
 * @Author Duys
 * @Description
 * @Date 2022/12/27 17:10
 **/
// 1410. HTML 实体解析器
public class LeetCode_1410 {
    // &quot;  &apos;   &amp;   &gt;   &lt;  &frasl;
    static Map<String, String> map = new HashMap<>();

    static {
        map.put("&quot;", "\"");
        map.put("&apos;", "'");
        map.put("&amp;", "&");
        map.put("&gt;", ">");
        map.put("&lt;", "<");
        map.put("&frasl;", "/");
    }

    public static String entityParser(String text) {
        StringBuffer buffer = new StringBuffer();
        char[] chars = text.toCharArray();
        int n = chars.length;
        for (int i = 0; i < n; ) {
            if (chars[i] == '&') {
                int tmp = i;
                while (tmp < n && chars[tmp] != ';') {
                    tmp++;
                }
                tmp = Math.min(tmp, n - 1);
                String key = new String(chars, i, tmp - i + 1);
                if (map.containsKey(key)) {
                    buffer.append(map.get(key));
                    i = tmp + 1;
                } else {
                    buffer.append(chars[i++]);
                }
            } else {
                buffer.append(chars[i++]);
            }
        }
        return buffer.toString();
    }

    public static void main(String[] args) {
        String str = "&&&";
        System.out.println(entityParser(str));
    }
}
