package 第四章.滑动窗口;

/*滑动窗口算法可以解决字符串或者数组的一些子部分问题，比如一些要求连续的子部分
同时可以提高效率，减低时间复杂度，将嵌套问题优化。
在有些字符串情况下，比kmp算法还要高效*/

//该算法的作用就是将我们多层嵌套的循环语句根据局部最优解来转换为单个的循环语句，从而减少时间复杂性。
//滑动窗口算法的的时间复杂度为线性的(O(n)),我们可以用此算法来查找最大/最小k-子序列，XOR，乘积，总和等一些列问题。

import 第三章_KMP和Mancher算法.Mancher.Manacher;

import java.util.LinkedList;
import java.util.List;

//
/*
//滑动窗口最大值
Input: nums = [1,3,-1,-3,5,3,6,7], and k = 3
Output: [3,3,5,5,6,7]
Explanation:

Window position                Max
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
 */
//解题思路  滑动窗口是
//基于双端队列（java里是linkedList）实现
// 双端队列从头严格按从大到小，不符合就弹出
public class Demo {
    public static int[] maxNums(int[] arr,int w){
        LinkedList<Integer> queue=new LinkedList<>();
        int l=-1;
        int[] res=new int[arr.length-w+1];
        int i=0;
        for (int r = 0; r < arr.length; r++) {
            while (!queue.isEmpty()&&arr[queue.peekLast()]<arr[r]){
                queue.pollLast();
            }
            queue.add(r);
            if (r-l==w){
                res[i++]=arr[queue.peekFirst()];
                l++;
            }
            while (queue.peekFirst()<=l){
                queue.pollFirst();
            }
        }
        return res;
    }

    public static void main(String[] args) {

        int[] arr={7,2,3,4,236,3,5,6,1,9};
        System.out.println(maxNums(arr,4));
    }
}
