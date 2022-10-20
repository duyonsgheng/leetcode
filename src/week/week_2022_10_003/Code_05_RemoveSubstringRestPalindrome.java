package week.week_2022_10_003;

/**
 * @ClassName Code_05_RemoveSubstringRestPalindrome
 * @Author Duys
 * @Description
 * @Date 2022/10/20 14:39
 **/
// 给定一个字符串str
// 如果删掉连续一段子串，剩下的字符串拼接起来是回文串
// 那么该删除叫做有效的删除
// 返回有多少种有效删除
// 字符串长度 <= 3000
public class Code_05_RemoveSubstringRestPalindrome {
    // 思路：
    // 1.先生成manacher串，找到每个位置对应的回文半径
    // 2.从两端往中间开始，找到不能成回文的位置
    // 3.然后枚举删除的哪一段
    public int goodStr(String str) {
        if (str.length() <= 1) {
            return 0;
        }
        int[] pArr = manacher(str);
        char[] arr = str.toCharArray();
        int n = str.length();
        int range = 0;
        for (int l = 0, r = n - 1; l <= r && arr[l] == arr[r]; l++, r--)
            range++;
        int ans = 0;
        // 枚举删除的哪一段？
        for (int l = 0; l < n; l++) {
            for (int r = l; r < n; r++) {
                if (l < n - r - 1) { // 如果左边比右边少
                    // 1 2 3 xxxxxxx  3 2 1
                    if (range >= l && check(pArr, r + 1, n - l - 1)) {
                        ans++;
                    }
                } else if (l > n - r - 1) { // 如果左边多
                    if (range >= n - r - 1 && check(pArr, n - r - 1, l - 1)) {
                        ans++;
                    }
                } else {
                    if (range >= l) {
                        ans++;
                    }
                }
            }
        }
        return ans - 1;// 去掉空集
    }

    // 判断原字符串，l到r这一段是不是回文？
    public boolean check(int[] parr, int l, int r) {
        int n = r - l + 1;
        l = l * 2 + 1;
        r = r * 2 + 1;
        return parr[(l + r) / 2] >= n;
    }

    public int[] manacher(String str) {
        char[] arr = manacherString(str);
        // 回文半径数组
        int[] pArr = new int[arr.length];
        int C = -1; // 回文中心点
        int R = -1; // 右边界能扩展到哪儿
        for (int i = 0; i < str.length(); i++) {
            pArr[i] = R > i ? Math.min(pArr[2 * C - i], R - i) : 1;
            // 看看R能扩到那儿
            while (i + pArr[i] < str.length() && i - pArr[i] >= 0) {
                if (arr[i + pArr[i]] == arr[i - pArr[i]]) {
                    pArr[i]++;
                } else
                    break;
            }
            if (i + pArr[i] > R) {
                R = i + pArr[i];
                C = i;
            }
        }
        return pArr;
    }

    public char[] manacherString(String str) {
        char[] arr = str.toCharArray();
        char[] manacherStr = new char[str.length() * 2 + 1];
        int index = 0;
        for (int i = 0; i < str.length(); i++) {
            manacherStr[i] = (i & 1) == 0 ? '#' : arr[index++];
        }
        return manacherStr;
    }

}
