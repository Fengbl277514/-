package 第一章_哈希与哈希表.BitMap;

import java.util.ArrayList;
import java.util.BitSet;

//查找一个数组中的重复元素
public class Demo {
    public static ArrayList<Integer> check(int[] arr){
        //bitmap位图的java实现
        BitSet bitSet=new BitSet();

        ArrayList<Integer> arrayList=new ArrayList<>();
        for (int i : arr) {
            if (bitSet.get(i)){
                //重复就返回
                arrayList.add(i);
            }else {
                //没有就将该位变为true
                bitSet.set(i,true);
            }
        }
        return arrayList;
    }



    public static void main(String[] args) {
        int[] arr={1,2,3,4,5,6,3,3,4,6,6,34,2,23,3};
        System.out.println(check(arr));
    }
}
