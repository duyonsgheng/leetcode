package custom.code_2023_02;

import java.util.Arrays;

/**
 * @ClassName LeetCode_2325
 * @Author Duys
 * @Description
 * @Date 2023/2/1 10:31
 **/
public class LeetCode_2325 {
    public static String decodeMessage(String key, String message) {
        char[] keys = key.toCharArray();
        // t h e q u i c k t h a
        int[] mes = new int[26];
        Arrays.fill(mes, -1);
        int index = 0;
        for (int i = 0; i < keys.length; i++) {
            if (keys[i] >= 'a' && keys[i] <= 'z') {
                if (mes[keys[i] - 'a'] == -1) {
                    mes[keys[i] - 'a'] = index++;
                }
            }
        }
        StringBuffer buffer = new StringBuffer();
        char[] chars = message.toCharArray();
        for (char c : chars) {
            if (c >= 'a' && c <= 'z') {
                buffer.append((char) (mes[c - 'a'] + 'a'));
            } else {
                buffer.append(c);
            }
        }
        return buffer.toString();
    }

    public static void main(String[] args) {
        String key = "the quick brown fox jumps over the lazy dog", message = "vkbs bs t suepuv";
        System.out.println(decodeMessage(key, message));
        key = "eljuxhpwnyrdgtqkviszcfmabo";
        message = "zwx hnfx lqantp mnoeius ycgk vcnjrdb";
        System.out.println(decodeMessage(key, message));
    }
}
