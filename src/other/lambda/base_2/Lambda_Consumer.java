package other.lambda.base_2;

import java.util.function.Consumer;

/**
 * @ClassName Lambda_Consumer
 * @Author Duys
 * @Description
 * @Date 2022/6/20 13:12
 **/
public class Lambda_Consumer {
    // Consumer 消费

    public static void main(String[] args) {
        consumerStr(s -> System.out.println(s));
        consumerStr(s -> System.out.println(s), s -> System.out.println(s));
    }

    public static void consumerStr(Consumer<String> consumer) {
        consumer.accept("duys");
    }

    public static void consumerStr(Consumer<String> first, Consumer<String> secend) {
        first.andThen(secend).accept("接二连三");
    }
}
