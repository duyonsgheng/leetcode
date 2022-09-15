package custom.code_2022_09;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * @ClassName LeetCode_690
 * @Author Duys
 * @Description
 * @Date 2022/9/7 16:01
 **/
// 690. 员工的重要性
public class LeetCode_690 {
    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();
        Employee e1 = new Employee();
        e1.id = 1;
        e1.importance = 5;
        e1.subordinates = Arrays.asList(2, 3);

        Employee e2 = new Employee();
        e2.id = 2;
        e2.importance = 3;

        Employee e3 = new Employee();
        e3.id = 3;
        e3.importance = 3;
        e1.subordinates = Arrays.asList(2, 3);
        employees.add(e1);
        employees.add(e2);
        employees.add(e3);
        System.out.println(getImportance(employees, 1));
    }

    public static int getImportance(List<Employee> employees, int id) {
        Map<Integer, Integer> map = new HashMap<>();
        Map<Integer, Set<Integer>> next = new HashMap<>();
        for (Employee employee : employees) {
            int cur = employee.id;
            int curAns = employee.importance;
            List<Integer> nexts = employee.subordinates;
            if (!map.containsKey(cur)) {
                map.put(cur, curAns);
            }
            if (!next.containsKey(cur)) {
                next.put(cur, new HashSet<>());
            }
            if (nexts != null && nexts.size() > 0) {
                next.get(cur).addAll(nexts);
            }

        }
        int ans = map.get(id);
        Set<Integer> nextIds = next.get(id);
        for (Integer nid : nextIds) {
            ans += process(map, next, nid);
        }
        return ans;
    }

    public static int process(Map<Integer, Integer> map, Map<Integer, Set<Integer>> next, Integer cur) {
        if (!map.containsKey(cur)) {
            return 0;
        }
        if (!next.containsKey(cur)) {
            return map.get(cur);
        }
        int ans = map.get(cur);
        for (Integer id : next.get(cur)) {
            ans += process(map, next, id);
        }
        return ans;
    }


    static class Employee {
        public int id;
        public int importance;
        public List<Integer> subordinates;
    }
}
