package duys_code.day_35;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName Code_03_412_LeetCode
 * @Author Duys
 * @Description https://leetcode-cn.com/problems/fizz-buzz/
 * @Date 2021/12/8 13:20
 **/
public class Code_03_412_LeetCode {

    public List<String> fizzBuzz(int n) {
        List<String> ans = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (i % 15 == 0) {
                ans.add("FizzBuzz");
            } else if (i % 5 == 0) {
                ans.add("Buzz");
            } else if (i % 3 == 0) {
                ans.add("Fizz");
            } else {
                ans.add("" + i);
            }
        }
        return ans;
    }
}
