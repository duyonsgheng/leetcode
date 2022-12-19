package custom.code_2022_12;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName LeetCode_1352
 * @Author Duys
 * @Description
 * @Date 2022/12/13 15:47
 **/
// 1352. 最后 K 个数的乘积
public class LeetCode_1352 {

    class ProductOfNumbers {
        int[] list;
        int mul;
        int zero;
        int index;

        public ProductOfNumbers() {
            list = new int[40001];
            mul = 1;
            zero = -1;
            index = 0;
        }

        public void add(int num) {
            if (num == 0) {
                zero = index;
                mul = 1;
            } else {
                mul *= num;
                list[index] = mul;
            }
            index++;
        }

        public int getProduct(int k) {
            int ans = 1;
            int l = index - 1 - k;
            if (zero > l) {
                return 0;
            }
            if (zero == l) {
                return list[index - 1];
            }
            return list[index - 1] / list[l];
        }
    }
}
