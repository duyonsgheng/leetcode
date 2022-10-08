package custom.code_2022_09;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName LeetCode_842
 * @Author Duys
 * @Description
 * @Date 2022/9/28 16:11
 **/
// 842. 将数组拆分成斐波那契序列
public class LeetCode_842 {
    public List<Integer> splitIntoFibonacci(String num) {
        List<Integer> ans = new ArrayList<>();
        process(num.toCharArray(), ans, 0);
        return ans;
    }

    // 当前来到index位置做决策
    // 之前做的决策再list中
    public boolean process(char[] arr, List<Integer> ans, int index) {
        if (index == arr.length && ans.size() >= 3) {
            return true;
        }
        for (int i = index; i < arr.length; i++) {
            // 如果两位字符以及两位以上的话，不能以0打头
            if (arr[index] == '0' && i > index) {
                break;
            }
            // 截取有效区间的数，组成数字
            long cur = sub(arr, index, i + 1);
            if (cur > Integer.MAX_VALUE) {
                break;
            }
            // 截取太大了，不需要截取了
            if (ans.size() >= 2 && ans.get(ans.size() - 1) + ans.get(ans.size() - 2) < cur) {
                break;
            }
            // 可以继续了
            if (ans.size() <= 1 || cur == ans.get(ans.size() - 1) + ans.get(ans.size() - 2)) {
                ans.add((int) cur);
                if (process(arr, ans, i + 1)) {
                    return true;
                }
                ans.remove(ans.size() - 1);
            }
        }
        return false;
    }

    public long sub(char[] arr, int i, int j) {
        long ans = 0;
        for (int s = i; s < j; s++) {
            ans = ans * 10 + arr[s] - '0';
        }
        return ans;
    }
}
