package week.week_2023_05_04;

/**
 * @ClassName Code_03_LeetCode_946
 * @Author Duys
 * @Description
 * @Date 2023/5/25 9:49
 **/
// 946. 验证栈序列
// https://leetcode.cn/problems/validate-stack-sequences/
public class Code_03_LeetCode_946 {
    // 本来就是一个栈的基本使用
    // 当前栈顶元素和当前元素一样，栈顶就弹出，当前数跳下一个
    // 那么怎么能把额外的空间占用降低到最小呢
    // 使用源数组来做
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        int n = pushed.length;
        int size = 0;
        for (int i = 0, j = 0; i < n; i++) {
            pushed[size++] = pushed[i];
            // 如果栈顶元素和当前元素一样，弹出栈顶，当前元素跳下一个
            while (size > 0 && j < n && pushed[size - 1] == popped[j]) {
                size--;
                j++;
            }
        }
        return size == 0;
    }
}
