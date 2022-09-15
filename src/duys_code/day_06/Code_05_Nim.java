package duys_code.day_06;

/**
 * @ClassName Code_05_Nim
 * @Author Duys
 * @Description nim 博弈
 * @Date 2021/9/27 16:16
 **/
public class Code_05_Nim {

    /**
     * Nim博弈
     * 给定一个正数数组arr
     * 先手和后手每次可以选择在一个位置拿走若干值，
     * 值要大于0，但是要小于该处的剩余
     * 谁最先拿空arr，谁赢。根据arr，返回谁赢
     */

    public static void win(int[] arr) {
        int eor = 0;
        for (int i = 0; i < arr.length; i++) {
            eor ^= arr[i];
        }
        if (eor == 0) {
            System.out.println("先手赢");
        } else {
            System.out.println("后手赢");
        }
    }
}
