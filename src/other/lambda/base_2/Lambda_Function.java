package other.lambda.base_2;

import java.util.function.Function;

/**
 * @ClassName Lambda_Funn
 * @Author Duys
 * @Description
 * @Date 2022/6/20 13:37
 **/
public class Lambda_Function {
    // Function 给一个输入，要求返回一个输出
    public static void main(String[] args) {
        method1(str -> Integer.valueOf(str), a -> a + 10);
        String str = "duys,29";
        // 第一个函数，切割
        // 第二个函数，拿到第一个函数的结果进行转换
        // 第三个函数，拿到第二个函数的结果进行运算
        method2(str, s1 -> s1.split(",")[1], s2 -> Integer.valueOf(s2), a -> a * 1000);
    }

    public static void method1(Function<String, Integer> f1, Function<Integer, Integer> f2) {
        int num = f1.andThen(f2).apply("10");
        System.out.println("method1 : " + num);
    }

    public static void method2(String s1, Function<String, String> f1, Function<String, Integer> f2, Function<Integer, Integer> f3) {
        int num = f1.andThen(f2).andThen(f3).apply(s1);
        System.out.println("method2 : " + num);
    }
}
