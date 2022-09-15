package other.lambda.base_1;

/**
 * @ClassName Lambda_Test_01
 * @Author Duys
 * @Description
 * @Date 2022/6/20 12:55
 **/
public class Lambda_Test_01 {
    public static void main(String[] args) {

        IMathOperation im1 = (int a, int b) -> {
            return a + b;
        };
        System.out.println(im1.operation(1, 11));

        IMathOperation im2 = (a, b) -> a + b;
        System.out.println(im2.operation(1, 12));

        IOnceParamter io = msg -> "duys:" + msg;
        System.out.println(io.fill("yes"));
    }
}
