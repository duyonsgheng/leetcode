package duys_code.day_40;

import java.util.Arrays;

/**
 * @ClassName Code_05_Mod3
 * @Author Duys
 * @Description
 * @Date 2021/12/22 14:14
 **/
public class Code_05_Mod3 {

    // 来自去哪儿网
    // 给定一个arr，里面的数字都是0~9
    // 你可以随意使用arr中的数字，哪怕打乱顺序也行
    // 请拼出一个能被3整除的，最大的数字，用str形式返回

    public static String max(int[] arr) {
        if (arr == null || arr.length <= 0) {
            return "";
        }
        Arrays.sort(arr);
        // 倒叙排列
        for (int l = 0, r = arr.length - 1; l < arr.length; l++, r--) {
            int tmp = arr[l];
            arr[l] = arr[r];
            arr[r] = tmp;
        }
        if (arr[0] == 0) {
            return "";
        }
        String ans = process(arr, 0, 0);
        String res = ans.replaceAll("^(0+)", "");
        if (!res.equals("")) {
            return res;
        }
        return ans.equals("") ? ans : "0";
    }

    public static String process(int[] arr, int index, int mod) {
        if (index == arr.length) {
            // 搞不定就是 $ 符号返回
            return mod == 0 ? "" : "$";
        }
        int curMod = arr[index] % 3;
        int nextMod = nextMod(mod, curMod);
        String p1 = "$";
        // 要当前数
        String next = process(arr, index + 1, nextMod);
        if (!next.equals("$")) {
            p1 = arr[index] + next;
        }
        // 不要当前数
        String p2 = process(arr, index + 1, mod);
        if (p1.equals("$") && p2.equals("$")) {
            return "$";
        }
        if (!p1.equals("$") && !p2.equals("$")) {
            return smaller(p1, p2) ? p2 : p1;
        }
        return p1.equals("$") ? p2 : p1;
    }

    public static boolean smaller(String p1, String p2) {
        if (p1.length() != p2.length()) {
            return p1.length() < p2.length();
        }
        return p1.compareTo(p2) < 0;
    }

    // 我当前的mod是curMod
    // 我希望下一个mod能和我当前的一起来搞定mod
    public static int nextMod(int mod, int curMod) {
        if (mod == 0) {
            if (curMod == 0) {
                return 0;
            } else if (curMod == 1) {
                return 2;
            } else {
                return 1;
            }
        } else if (mod == 1) {
            if (curMod == 0) {
                return 1;
            } else if (curMod == 1) {
                return 0;
            } else {
                return 2;
            }
        } else {
            if (curMod == 0) {
                return 2;
            } else if (curMod == 1) {
                return 1;
            } else {
                return 0;
            }
        }
    }
}
