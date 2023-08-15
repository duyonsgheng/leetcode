package custom.code_2023_08;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Mr.Du
 * @ClassName LeetCode_2043
 * @date 2023年08月02日
 */
// 2043. 简易银行系统
// https://leetcode.cn/problems/simple-bank-system/
public class LeetCode_2043 {
    class Bank {
        long[] accs;
        int n;

        public Bank(long[] balance) {
            n = balance.length;
            accs = balance;
        }

        public boolean transfer(int account1, int account2, long money) {
            if (account1 - 1 > n || account1 - 1 < 0 || account2 - 1 > n || account2 - 1 < 0) {
                return false;
            }
            if (accs[account1 - 1] < money) {
                return false;
            }
            accs[account1 - 1] -= money;
            accs[account2 - 1] += money;
            return true;
        }

        public boolean deposit(int account, long money) {
            if (account - 1 > n || account - 1 < 0) {
                return false;
            }
            accs[account - 1] += money;
            return true;
        }

        public boolean withdraw(int account, long money) {
            if (account - 1 > n || account - 1 < 0) {
                return false;
            }
            if (accs[account - 1] < money) {
                return false;
            }
            accs[account - 1] -= money;
            return true;
        }
    }
}
