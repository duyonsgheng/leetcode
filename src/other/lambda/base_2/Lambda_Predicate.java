package other.lambda.base_2;

import java.util.function.Predicate;

/**
 * @ClassName Lambda_Predicate
 * @Author Duys
 * @Description TODO
 * @Date 2022/6/20 13:35
 **/
public class Lambda_Predicate {
    public static void main(String[] args) {
        and(s -> s.contains("d"), s2 -> s2.contains("u"));

        or(s -> s.contains("d"), s2 -> s2.contains("m"));
    }

    public static void and(Predicate<String> s1, Predicate<String> s2) {
        boolean duys = s1.and(s2).test("duys");
        System.out.println("校验两个字符是否合规：" + duys);
    }

    public static void or(Predicate<String> s1, Predicate<String> s2) {
        boolean duys = s1.or(s2).test("duys");
        System.out.println("校验两个字符是否合规：" + duys);
    }
}
