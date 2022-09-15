package other.lambda.base_2;

/**
 * @ClassName Lambda_Runnable
 * @Author Duys
 * @Description
 * @Date 2022/6/20 13:01
 **/
public class Lambda_Runnable {
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("yes");
            }
        }).start();
        new Thread(() -> System.out.println("asdasd")).start();
    }

}
