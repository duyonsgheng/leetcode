package week.week_2022_04_02;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName Code_01_SumOfValuesAboutPrimes
 * @Author Duys
 * @Description
 * @Date 2022/4/13 14:02
 **/
// 来自携程
// 给出n个数字，你可以任选其中一些数字相乘，相乘之后得到的新数字x
// x的价值是x的不同质因子的数量
// 返回所有选择数字的方案中，得到的x的价值之和
public class Code_01_SumOfValuesAboutPrimes {

    // 暴力解答
    // 尝试所有的乘积
    public static long sumOfValues1(int[] arr) {
        return process1(arr, 0, 1);
    }

    public static long process1(int[] arr, int index, int pre) {
        if (index == arr.length) {
            return primes(pre).size();
        }
        long p1 = process1(arr, index + 1, pre);
        long p2 = process1(arr, index + 1, pre * arr[index]);
        return p1 + p2;
    }

    // 我们收集所有的数字得到的质数因子
    // 例如数组 14个数
    // 比如含有2的质数因子是9个，不含有2的质数因子是 5个
    // 那么所有的可能性就是(2^9 - 1) * 2^5
    public static long sumOfValues2(int[] arr) {
        Map<Long, Long> counts = new HashMap<>();
        for (int num : arr) {
            for (long n : primes(num)) {
                // 统计所有的质数因子，有哪些元素包含了当前质数因子
                counts.put(n, counts.getOrDefault(n, 0l) + 1);
            }
        }
        int n = arr.length;
        long ans = 0;
        for (long count : counts.values()) {
            long not = n - count;
            ans += (power(2, count) - 1) * power(2, not);
        }
        return ans;
    }

    // 5^5
    public static long power(long num, long n) {
        if (n == 0) {
            return 1;
        }
        long ans = 1;
        // 101
        // 010
        // 1. ans = 5 , num = 25, n =2
        // 2. ans = 5 , num = 125 * 5 ,n =1
        while (n > 0) {
            if ((n & 1) != 0) {
                ans *= num;
            }
            num *= num;
            n >>= 1;
        }
        return ans;
    }


    public static List<Long> primes(long num) {
        List<Long> ans = new ArrayList<>();
        // 算一个数的所有质数因子，算到根号就可以停
        for (long i = 2; i * i <= num && num > 1; i++) {
            if (num % i == 0) {
                ans.add(i);
                while (num % i == 0) {
                    num /= i;
                }
            }
        }
        if (num != 1) {
            ans.add(num);
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(power(5, 5));
        System.out.println(125 * 125 * 5);
    }

}
