package custom.test;

/**
 * @author Mr.Du
 * @ClassName Pro
 * @date 2023年07月06日
 */
class Pro {
    public void process(Data<?> data) {
        Data<User> cur = (Data<User>) data;
        User data1 = cur.data;
        data1.name2 = "duys";
    }

    public static void main(String[] args) {
        Pro pro = new Pro();
        User user1 = new User();
        user1.name1 = "aaa";
        Data<User> data = new Data<>();
        data.data = user1;
        pro.process(data);

        System.out.println(data.data.name2);
    }
}
