package custom.code_2022_11;

import java.util.Stack;

/**
 * @ClassName LeetCode_1209
 * @Author Duys
 * @Description
 * @Date 2022/11/23 15:58
 **/
// 1209. 删除字符串中的所有相邻重复项 II
public class LeetCode_1209 {
    public String removeDuplicates(String s, int k) {
        int n = s.length();
        if (k > n) {
            return s;
        }
        Stack<Integer> stack = new Stack<>();
        char[] arr = s.toCharArray();
        int j = 0;
        for (int i = 0; i < n; i++, j++) {
            arr[j] = arr[i];
            if (j == 0 || arr[j] != arr[j - 1]) {
                stack.push(1);// 和之前不一样了，压入一个新的
            } else {
                // 如果一样了，之前的基础上+1个
                int add = stack.pop() + 1;
                if (add == k) { // 已经满了，可以消除了，消除了就不需要再次压入了
                    j = j - k;// 消除了，把j的位置调整对
                } else {
                    stack.push(add);
                }
            }
        }
        // deeedbbcccbdaa k =3
        // 2 2
        return new String(arr, 0, j);
    }

    public static void main(String[] args) {
        String a = "duys-001";
        System.out.println(new String(a.toCharArray(), 0, 2));
    }
}
