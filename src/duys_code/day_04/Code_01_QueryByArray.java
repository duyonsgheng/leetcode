package duys_code.day_04;

import java.util.Arrays;

/**
 * @ClassName Code_01_QueryByArray
 * @Author Duys
 * @Description
 * @Date 2021/9/22 14:44
 **/
public class Code_01_QueryByArray {
    /**
     * 头条原题：
     * 数组为{3, 2, 2, 3, 1}，查询为(0, 3, 2)
     * 意思是在数组里下标0~3这个范围上，有几个2？答案返回2。
     * 假设给你一个数组arr，
     * 对这个数组的查询非常频繁，都给出来
     * 请返回所有查询的结果
     */


    public class QueryBox1 {
        int[] arr;

        public QueryBox1(int[] array) {
            arr = Arrays.copyOf(array, array.length);
        }
    }

    /**
     * 做一个预处理结构。把原数组所有的数的下标都给记录下来，放到一个HashMap<Integer,ArrayList<Integer>> map 结构中
     */
}
