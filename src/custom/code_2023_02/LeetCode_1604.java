package custom.code_2023_02;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * @ClassName LeetCode_1604
 * @Author Duys
 * @Description
 * @Date 2023/2/7 10:46
 **/
// 1604. 警告一小时内使用相同员工卡大于等于三次的人
public class LeetCode_1604 {
    public static List<String> alertNames(String[] keyName, String[] keyTime) {
        Map<String, List<Integer>> timeMap = new HashMap<>();
        int n = keyName.length;
        for (int i = 0; i < n; i++) {
            String name = keyName[i];
            String time = keyTime[i];
            timeMap.putIfAbsent(name, new ArrayList<>());
            int hour = (time.charAt(0) - '0') * 10 + (time.charAt(1) - '0');
            int minute = (time.charAt(3) - '0') * 10 + (time.charAt(4) - '0');
            timeMap.get(name).add(hour * 60 + minute);
        }
        List<String> res = new ArrayList<>();
        Set<String> keySet = timeMap.keySet();
        for (String name : keySet) {
            List<Integer> list = timeMap.get(name);
            Collections.sort(list);
            int size = list.size();
            for (int i = 2; i < size; i++) {
                int time1 = list.get(i - 2), time2 = list.get(i);
                int difference = time2 - time1;
                if (difference <= 60) {
                    res.add(name);
                    break;
                }
            }
        }
        Collections.sort(res);
        return res;
    }

    public static void main(String[] args) {
        System.out.println(alertNames(new String[]{"daniel", "daniel", "daniel", "luis", "luis", "luis", "luis"},
                new String[]{"10:00", "10:40", "11:00", "09:00", "11:00", "13:00", "15:00"}));
        System.out.println(alertNames(new String[]{"alice", "alice", "alice", "bob", "bob", "bob", "bob"},
                new String[]{"12:01", "12:00", "18:00", "21:00", "21:20", "21:30", "23:00"}));
        System.out.println(alertNames(new String[]{"john", "john", "john"},
                new String[]{"23:58", "23:59", "00:01"}));
        System.out.println(alertNames(new String[]{"leslie", "leslie", "leslie", "clare", "clare", "clare", "clare"},
                new String[]{"13:00", "13:20", "14:00", "18:00", "18:51", "19:30", "19:49"}));
        // ["a",    "a",     "a",    "a",    "a",    "b",    "b",    "b",    "b",    "b",    "b"]
        //["23:20","11:09","23:30","23:02","15:28","22:57","23:40","03:43","21:55","20:38","00:19"]

        System.out.println(alertNames(new String[]{"a", "a", "a", "a", "a", "b", "b", "b", "b", "b", "b"},
                new String[]{"23:20", "11:09", "23:30", "23:02", "15:28", "22:57", "23:40", "03:43", "21:55", "20:38", "00:19"}));

        // ["a",    "a",    "a",    "a",    "b",    "b",    "b",    "b",    "b",    "b",    "c",    "c",    "c",   "c"]
        //["01:35","08:43","20:49","00:01","17:44","02:50","18:48","22:27","14:12","18:00","12:38","20:40","03:59","22:24"]
        // "02:50","14:12","17:44","18:00","18:48","22:27",
    }
}
