package custom.code_2023_05;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * @ClassName LeetCode_1807
 * @Author Duys
 * @Description
 * @Date 2023/5/23 10:21
 **/
// 1807. 替换字符串中的括号内容
public class LeetCode_1807 {
    public static String evaluate(String s, List<List<String>> knowledge) {
        Map<String, String> map = new HashMap<>();
        for (List<String> keys : knowledge) {
            map.put(keys.get(0), keys.get(1));
        }
        char[] arr = s.toCharArray();
        int n = arr.length;
        StringBuffer buffer = new StringBuffer();
        StringBuffer cur = new StringBuffer();
        for (int i = 0; i < n; ) {
            if (arr[i] == '(') {
                i++;
                while (i < n && arr[i] != ')') {
                    cur.append(arr[i++]);
                }
                i++;
                buffer.append(map.getOrDefault(cur.toString(), "?"));
                cur.delete(0, cur.length());
            } else {
                buffer.append(arr[i]);
                i++;
            }
        }
        return buffer.toString();
    }

    public static void main(String[] args) {
        // s = "(name)is(age)yearsold", knowledge = [["name","bob"],["age","two"]]
        List<List<String>> knowledge = new ArrayList<>();
        List<String> s1 = new ArrayList<>();
        s1.add("name");
        s1.add("bob");
        List<String> s2 = new ArrayList<>();
        s2.add("age");
        s2.add("two");
        knowledge.add(s2);
        knowledge.add(s1);
        System.out.println(evaluate("(name)is(age)yearsold", knowledge));
        knowledge.clear();
        s1.clear();
        s1.add("a");
        s1.add("b");
        knowledge.add(s1);
        System.out.println(evaluate("hi(name)", knowledge));

        knowledge.clear();
        s1.clear();
        s1.add("a");
        s1.add("yes");
        knowledge.add(s1);
        System.out.println(evaluate("(a)(a)(a)aaa", knowledge));

        
    }
}
