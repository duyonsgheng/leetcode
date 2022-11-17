package custom.code_2022_11;

/**
 * @ClassName LeetCode_1144
 * @Author Duys
 * @Description
 * @Date 2022/11/16 17:20
 **/
// 1144. 递减元素使数组呈锯齿状
public class LeetCode_1144 {
    public int movesToMakeZigzag(int[] nums) {
        int n = nums.length;
        int ans1 = 0; // 奇数位置大
        int ans2 = 0; // 偶数位置大
        int[] arr = nums.clone();
        for (int i = 0; i < n; i++) {
            if ((i % 2) == 1) {// 奇数位置
                // 当前i是奇数位置，那么后面比当前大，则偶数位置不满足，需要增加ans2
                if (i + 1 < n && nums[i] >= nums[i + 1]) {
                    ans2 += nums[i] - nums[i + 1] + 1;
                    nums[i] = nums[i + 1] - 1;
                }
                // 当前i为奇数，而奇数位置大不满足
                if (i + 1 < n && arr[i] <= arr[i + 1]) {
                    ans1 += arr[i + 1] - arr[i] + 1;
                    arr[i + 1] = arr[i] - 1;
                }
            } else { // 偶数位置
                // 当前i是偶数位置，当前奇数位置大的时候，不满足偶数大
                if (i + 1 < n && nums[i] <= nums[i + 1]) {
                    ans2 += nums[i + 1] - nums[i] + 1;
                    nums[i + 1] = nums[i] - 1;
                }
                // 当前i为奇数，而奇数位置大不满足
                if (i + 1 < n && arr[i] >= arr[i + 1]) {
                    ans1 += arr[i] - arr[i + 1] + 1;
                    arr[i] = arr[i + 1] - 1;
                }
            }
        }
        return Math.min(ans1, ans2);
    }

    public int movesToMakeZigzag1(int[] nums) {
        int ans1 = 0, ans2 = 0;
        int last1 = nums[0], last2 = nums[0];
        boolean flag = true; // 为ture，表示偶数位置大
        for (int i = 1; i < nums.length; i++) {
            if (flag) {
                // 当前大于之前的
                if (nums[i] >= last1) {
                    ans1 += nums[i] - last1 + 1;
                    last1--;
                } else {
                    last1 = nums[i];
                }
                // 如果偶数位置小了。
                if (nums[i] <= last2) {
                    ans2 += last2 - nums[i] + 1;
                }
                last2 = nums[i];
            } else {
                if (nums[i] <= last1) {
                    ans1 += last1 - nums[i] + 1;
                }
                last1 = nums[i];
                if (nums[i] >= last2) {
                    ans2 += nums[i] - last2 + 1;
                    last2--;
                } else {
                    last2 = nums[i];
                }
            }
            flag = !flag;
        }
        return Math.min(ans1, ans2);
    }
}
