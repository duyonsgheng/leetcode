package custom.code_2022_08;

/**
 * @ClassName LeetCode_457
 * @Author Duys
 * @Description
 * @Date 2022/8/12 15:10
 **/
// 457. 环形数组是否存在循环
public class LeetCode_457 {
    public boolean circularArrayLoop(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                continue;
            }
            int slow = i;
            int fast = nextIndex(nums, i);
            while (nums[slow] * nums[fast] > 0 && nums[slow] * nums[nextIndex(nums, fast)] > 0) {
                if (slow == fast) {
                    if (slow != nextIndex(nums, slow)) {
                        return true;
                    } else break;
                }
                slow = nextIndex(nums, slow);
                fast = nextIndex(nums, nextIndex(nums, fast));
            }
            int add = i;
            while (nums[add] * nums[nextIndex(nums, add)] > 0) {
                int tmp = add;
                add = nextIndex(nums, add);
                nums[tmp] = 0;
            }
        }
        return false;
    }

    public int nextIndex(int[] arr, int cur) {
        int n = arr.length;
        return ((cur + arr[cur]) % n + n) % n;
    }
}
