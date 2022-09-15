package other.lambda.base_2;

import java.util.Arrays;
import java.util.Collections;
import java.util.function.Supplier;

/**
 * @ClassName Lambda_Supplier
 * @Author Duys
 * @Description
 * @Date 2022/6/20 13:04
 **/
public class Lambda_Supplier {

    // Supplier - 提供一个服务
    public static void main(String[] args) {
        int[] arr = {2, 3, 1, 6, 9, 12};
        int max = findMax(() -> {
            int tmp = arr[0];
            for (int i = 1; i < arr.length; i++) {
                tmp = Math.max(tmp, arr[i]);
            }
            return tmp;
        });
        System.out.println(max);
    }

    public static int findMax(Supplier<Integer> supplier) {
        return supplier.get();
    }
}
