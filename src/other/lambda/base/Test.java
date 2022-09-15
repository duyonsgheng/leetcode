package other.lambda.base;

/**
 * @ClassName Test
 * @Author Duys
 * @Description
 * @Date 2022/6/20 10:00
 **/
public class Test {
    public static void main(String[] args) {
        User user = null;
        // 1.子类实现
        Factory factory = new SubClass();
        user = (User) factory.getObject();
        // 2. 匿名内部类
        factory = new Factory() {
            @Override
            public Object getObject() {
                return new User();
            }
        };
        user = (User) factory.getObject();
        // lambda
        factory = () -> {
            return new User();
        };
        user = (User) factory.getObject();

    }
}
