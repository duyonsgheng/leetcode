package duys.class_06_04;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * @ClassName CustomHeap
 * @Author Duys
 * @Description 手动改写堆
 * 系统提供的堆无法做到的事情：
 * 1）已经入堆的元素，如果参与排序的指标方法变化，
 * 系统提供的堆无法做到时间复杂度O(logN)调整！都是O(N)的调整！
 * 2）系统提供的堆只能弹出堆顶，做不到自由删除任何一个堆中的元素，
 * 或者说，无法在时间复杂度O(logN)内完成！一定会高于O(logN)
 * 根本原因：无反向索引表
 * @Date 2021/6/4 14:19
 **/
public class CustomHeap_code_01 {


    public static void main(String[] args) {

    }
    /**
     * 题目：
     * 给定一个整型数组，int[] arr；和一个布尔类型数组，boolean[] op
     * 两个数组一定等长，假设长度为N，arr[i]表示客户编号，op[i]表示客户操作
     * arr = [ 3   ,   3   ,   1   ,  2,      1,      2,      5…
     * op = [ T   ,   T,      T,     T,      F,      T,       F…
     * 依次表示：3用户购买了一件商品，3用户购买了一件商品，1用户购买了一件商品，2用户购买了一件商品，
     * 1用户退货了一件商品，2用户购买了一件商品，5用户退货了一件商品…
     * 规则：1.商品不能为0，否则认为当前事件无效 2.每次只能最多K个用户得奖
     */
    /**
     * 思路：
     * 1）建立反向索引表
     * 2）建立比较器
     * 3）核心在于各种结构相互配合，非常容易出错
     */


    /**
     * 加强堆
     *
     * @param <T>
     */
    public class HeapGreater<T> {
        // 存放元数据的动态数组
        private ArrayList<T> heap;
        // 元数据反向索引表，在动态数组中的位置
        private HashMap<T, Integer> indexMap;
        // 元数据个数
        private int heapSize;
        // 元数据比较器
        private Comparator<? super T> comp;

        public HeapGreater(Comparator<T> comp) {
            this.heap = new ArrayList();
            this.indexMap = new HashMap<>();
            this.heapSize = 0;
            this.comp = comp;
        }

        public boolean isEmpty() {
            return heapSize == 0;
        }

        public int size() {
            return heapSize;
        }

        public boolean contains(T obj) {
            return indexMap.containsKey(obj);
        }

        public T peek() {
            return heap.get(0);
        }

        public void push(T obj) {
            heap.add(obj);
            indexMap.put(obj, heapSize);
            heapInsert(heapSize++);
        }

        public T pop() {
            T ans = heap.get(0);
            swap(0, heapSize - 1);
            indexMap.remove(ans);
            heap.remove(--heapSize);
            heapify(0);
            return ans;
        }

        public void remove(T obj) {
            // 获取堆中最后一个元素
            T replace = heap.get(heapSize - 1);
            // 获取需要删除元素在数组上的索引位置
            int index = indexMap.get(obj);
            // 反向索引表中干掉当前需删除的对象
            indexMap.remove(obj);
            // 堆减少一个元素
            heap.remove(--heapSize);
            // 边界-如果最后一个元素就是要删除的，不用交换
            if (obj != replace) {
                // 用之前获取到的最后一个去替换之前位置上的元素
                heap.set(index, replace);
                // 更新最后一个元素的反向索引
                indexMap.put(replace, index);
                // 重新调整堆结构
                resign(replace);
            }
        }


        // 从任意位置开始，把堆结构调整正确
        public void resign(T obj) {
            heapInsert(indexMap.get(obj));
            heapify(indexMap.get(obj));
        }

        // 请返回堆上的所有元素
        public List<T> getAllElements() {
            List<T> ans = new ArrayList<>();
            for (T c : heap) {
                ans.add(c);
            }
            return ans;
        }

        private void heapInsert(int index) {
            // 比较器中，如果返回 -1 表示 前者大于了后者
            while (comp.compare(heap.get(index), heap.get((index - 1) / 2)) < 0) {
                swap(index, (index - 1) / 2);
                index = (index - 1) / 2;
            }
        }

        private void heapify(int index) {
            int left = index * 2 + 1;
            while (left < heapSize) {
                int best = left + 1 < heapSize && comp.compare(heap.get(left + 1), heap.get(left)) < 0 ? (left + 1) : left;
                best = comp.compare(heap.get(best), heap.get(index)) < 0 ? best : index;
                if (best == index) {
                    break;
                }
                swap(best, index);
                index = best;
                left = index * 2 + 1;
            }
        }

        private void swap(int i, int j) {
            T o1 = heap.get(i);
            T o2 = heap.get(j);
            // 堆里位置交换
            heap.set(i, o2);
            heap.set(j, o1);
            // 反向索引表也要交换
            indexMap.put(o2, i);
            indexMap.put(o1, j);
        }
    }
}
