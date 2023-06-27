package week.week_2023_05_05;

/**
 * @ClassName Code_04_StringHash
 * @Author Duys
 * @Description
 * @Date 2023/6/5 9:18
 **/
// 字符串哈希原理和实现
// 课上讲的时候有错误，代码没错，解释有错
// 重新解释一下:
// 比如p = 233, 也就是课上说的选择的质数进制
// " 3 1 2 5 6 ..."
//   0 1 2 3 4
// hash[0] = 3 * p的0次方
// hash[1] = 3 * p的1次方 + 1 * p的0次方
// hash[2] = 3 * p的2次方 + 1 * p的1次方 + 2 * p的0次方
// hash[3] = 3 * p的3次方 + 1 * p的2次方 + 2 * p的1次方 + 5 * p的0次方
// hash[4] = 3 * p的4次方 + 1 * p的3次方 + 2 * p的2次方 + 5 * p的1次方 + 6 * p的0次方
// 次方是倒过来的，课上讲错了
// 所以hash[i] = hash[i-1] * p + arr[i]，这个方式就可以得到上面说的意思
// 于是，你想得到子串"56"的哈希值
// 子串"56"的哈希值 = hash[4] - hash[2]*p的2次方(就是子串"56"的长度次方)
// hash[4] = 3 * p的4次方 + 1 * p的3次方 + 2 * p的2次方 + 5 * p的1次方 + 6 * p的0次方
// hash[2] = 3 * p的2次方 + 1 * p的1次方 + 2 * p的0次方
// hash[2] * p的2次方 = 3 * p的4次方 + 1 * p的3次方 + 2 * p的2次方
// 所以hash[4] - hash[2] * p的2次方 = 5 * p的1次方 + 6 * p的0次方
// 这样就得到子串"56"的哈希值了
// 抱歉，课上讲错了。应该是上面的方式。
// 所以，子串s[l...r]的哈希值 = hash[r] - hash[l-1] * p的(r-l+1)次方
// 也就是说，hash[l-1] * p的(r-l+1)次方，正好和hash[r]所代表的信息，前面对齐了
// 减完之后，正好就是子串s[l...r]的哈希值
// 代码是对的，后续的题目实现也是对的，就是解释错了，抱歉抱歉！
public class Code_04_StringHash {

    public static int MAXN = 100005;
    public static int[] pow = new int[MAXN];
    public static int[] hash = new int[MAXN];
    public static int base = 499;

    public static void build(String str, int n) {
        pow[0] = 1;
        for (int i = 1; i < n; i++) {
            pow[i] = pow[i - 1] * base;
        }
        hash[0] = str.charAt(0) - 'a' + 1;
        for (int i = 1; i < n; i++) {
            hash[i] = hash[i - 1] * base + str.charAt(i) - 'a' + 1;
        }
    }

    public static boolean hashCheck(int n, int l1, int l2, int len) {
        int r1 = l1 + len - 1;
        int r2 = l2 + len - 1;
        if (r1 >= n || r2 >= n) {
            return false;
        }
        return hash(l1, r1) == hash(l2, r2);
    }

    //
    public static long hash(int l, int r) {
        long ans = hash[r];
        ans -= l == 0 ? 0 : (hash[l - 1] * pow[r - l + 1]);
        return ans;
    }
}
