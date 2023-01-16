package custom.code_2023_01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * @ClassName LeetCode_1418
 * @Author Duys
 * @Description
 * @Date 2023/1/3 16:19
 **/
// 1418. 点菜展示表
public class LeetCode_1418 {

    public static List<List<String>> displayTable(List<List<String>> orders) {
        TreeSet<String> names = new TreeSet<>();
        TreeMap<Integer, Map<String, Integer>> map = new TreeMap<>();
        for (List<String> order : orders) {
            Integer table = Integer.valueOf(order.get(1));
            String name = order.get(2);
            if (!names.contains(name)) {
                names.add(name);
            }
            if (!map.containsKey(table)) {
                map.put(table, new HashMap<>());
            }
            map.get(table).put(name, map.get(table).getOrDefault(name, 0) + 1);
        }
        List<List<String>> ans = new ArrayList<>();
        List<String> tables = new ArrayList<>();
        tables.add("Table");
        for (String name : names) {
            tables.add(name);
        }
        ans.add(tables);
        for (Integer number : map.keySet()) {
            List<String> other = new ArrayList<>();
            other.add(number + "");
            Map<String, Integer> map1 = map.get(number);
            for (String name : names) {
                other.add(map1.getOrDefault(name, 0) + "");
            }
            ans.add(other);
        }
        return ans;
    }

    public static void main(String[] args) {
        //  [["David","3","Ceviche"],["Corina","10","Beef Burrito"],["David","3","Fried Chicken"],
        // ["Carla","5","Water"],["Carla","5","Ceviche"],["Rous","3","Ceviche"]]
        List<List<String>> list = new ArrayList<>();
        list.add(Arrays.asList("David", "3", "Ceviche"));
        list.add(Arrays.asList("Corina", "10", "Beef Burrito"));
        list.add(Arrays.asList("David", "3", "Fried Chicken"));
        list.add(Arrays.asList("Carla", "5", "Water"));
        list.add(Arrays.asList("Carla", "5", "Ceviche"));
        list.add(Arrays.asList("Rous", "3", "Ceviche"));
        List<List<String>> list1 = displayTable(list);
        System.out.println(list1);
    }
}
