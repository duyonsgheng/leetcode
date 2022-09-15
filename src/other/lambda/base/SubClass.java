package other.lambda.base;

/**
 * @ClassName SubClass
 * @Author Duys
 * @Description
 * @Date 2022/6/20 9:59
 **/
public class SubClass implements Factory {
    @Override
    public Object getObject() {
        return new User();
    }
}
