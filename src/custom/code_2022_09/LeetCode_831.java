package custom.code_2022_09;

/**
 * @ClassName LeetCode_831
 * @Author Duys
 * @Description
 * @Date 2022/9/26 17:42
 **/
// 831. 隐藏个人信息
public class LeetCode_831 {
    public String maskPII(String s) {
        if (s.contains("@")) {
            return processEmail(s);
        }
        return processPhone(s);
    }

    public String processEmail(String s) {
        String[] split = s.split("@");
        String name = split[0].toLowerCase();
        StringBuilder builder = new StringBuilder();
        builder.append(name.substring(0, 1))
                .append("*****")
                .append(name.substring(name.length() - 1))
                .append("@")
                .append(split[1].toLowerCase());
        return builder.toString();
    }

    //	"***-***-XXXX" 如果国家代码为 0 位数字
    //	"+*-***-***-XXXX" 如果国家代码为 1 位数字
    //	"+**-***-***-XXXX" 如果国家代码为 2 位数字
    //	"+***-***-***-XXXX" 如果国家代码为 3 位数字
    public String processPhone(String s) {
        char[] arr = s.toCharArray();
        int len = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] >= '0' && arr[i] <= '9') {
                arr[len++] = arr[i];
            }
        }
        StringBuilder builder = new StringBuilder();
        if (len == 10) {
            builder.append("***-***-");
        } else if (len == 11) {
            builder.append("+*-***-***-");
        } else if (len == 12) {
            builder.append("+**-***-***-");
        } else {
            builder.append("+***-***-***-");
        }
        builder.append(new String(arr, len - 4, 4));
        return builder.toString();
    }
}
