package custom.code_2022_12;

import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName LeetCode_1805
 * @Author Duys
 * @Description
 * @Date 2022/12/6 9:27
 **/
// 1805. 字符串中不同整数的数目
public class LeetCode_1805 {

    public static void main(String[] args) {
        String str = "a123bc34d8ef34";
        System.out.println(numDifferentIntegers(str));
    }

    public static int numDifferentIntegers(String word) {
        char[] arr = word.toCharArray();
        Set<String> cont = new HashSet<>();
        int n = arr.length, l = 0, r = 0;
        while (r < n && l < n) {
            if (l < n && !(arr[l] >= '0' && arr[l] <= '9')) {
                l++;
            }
            if (l == n) {
                break;
            }
            r = l;
            while (r < n && arr[r] >= '0' && arr[r] <= '9') {
                r++;
            }
            while (r - l > 1 && arr[l] == '0') {
                l++;
            }
            if (r != l) {
                cont.add(new String(arr, l, r - l));
            }
            l = r;
        }
        return cont.size();
    }
}
