package duys_code.day_40;

/**
 * @ClassName Code_04_ArrsSort
 * @Author Duys
 * @Description
 * @Date 2021/12/22 14:05
 **/
public class Code_04_ArrsSort {
    // 给定两个数组A和B，长度都是N
    // A[i]不可以在A中和其他数交换，只可以选择和B[i]交换(0<=i<n)
    // 你的目的是让A有序，返回你能不能做到

    public static boolean letASorted(int[] A, int[] B) {
        return process(A, B, 0, Integer.MIN_VALUE);
    }

    public static boolean process(int[] A, int[] B, int index, int lastA) {
        if (index == A.length) {
            return true;
        }
        // 不交换
        if (A[index] >= lastA && process(A, B, index + 1, A[index])) {
            return true;
        }
        // 交换
        if (B[index] >= lastA && process(A, B, index + 1, B[index])) {
            return true;
        }
        return false;
    }
}
