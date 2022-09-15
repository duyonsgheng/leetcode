package duys_code.day_03;

import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName Code_02_HowManyTypes
 * @Author Duys
 * @Description
 * @Date 2021/9/18 15:25
 **/
public class Code_02_HowManyTypes {
    /*
     * 只由小写字母（a~z）组成的一批字符串，都放在字符类型的数组String[] arr中，
     * 如果其中某两个字符串，所含有的字符种类完全一样，就将两个字符串算作一类 比如：baacba和bac就算作一类
     * 虽然长度不一样，但是所含字符的种类完全一样（a、b、c） 返回arr中有多少类？
     *
     */
    // 1.把arr种的每一个元素排序然后去重后让如缓存
    public static int getTypes1(String[] arr) {
        Set<String> set = new HashSet<>();
        for (String str : arr) {
            char[] chars = str.toCharArray();
            boolean[] map = new boolean[26];
            for (int i = 0; i < chars.length; i++) {
                map[chars[i] - 'a'] = true;
            }
            String key = "";
            for (int i = 0; i < 26; i++) {
                if (map[i]) {
                    key += String.valueOf((char) (i + 'a'));
                }
            }
            set.add(key);
        }
        return set.size();
    }

    // 我们使用一个int 类型来代表这个元素 ，因为a-z只有26个位置
    public static int getTypes2(String[] arr) {
        Set<Integer> set = new HashSet<>();
        for (String str : arr) {
            char[] chars = str.toCharArray();
            int key = 0;
            for (int i = 0; i < chars.length; i++) {
                key |= (1 << (chars[i] - 'a'));
            }
            set.add(key);
        }
        return set.size();
    }
}
