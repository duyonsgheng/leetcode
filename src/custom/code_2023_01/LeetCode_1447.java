package custom.code_2023_01;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

/**
 * @ClassName LeetCode_1447
 * @Author Duys
 * @Description
 * @Date 2023/1/5 17:46
 **/
// 1447. 最简分数
public class LeetCode_1447 {
    public static List<String> simplifiedFractions(int n) {
        Set<String> set = new HashSet<>();
        for (int down = 2; down <= n; down++) {
            for (int up = 1; up < down; up++) {
                if (gcd(up, down) == 1) {
                    set.add(up + "/" + down);
                }
            }
        }
        return new ArrayList<>(set);
    }

    public static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public static void main(String[] args) {
        System.out.println(simplifiedFractions(2));
        System.out.println(simplifiedFractions(3));
        System.out.println(simplifiedFractions(4));
    }
}
