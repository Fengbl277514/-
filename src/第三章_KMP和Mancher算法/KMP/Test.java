package 第三章_KMP和Mancher算法.KMP;

import 第三章_KMP和Mancher算法.KMP.Kmp;

public class Test {
    public static void main(String[] args) {
        Kmp kmp=new Kmp();
        String str1="zcdssdasdas";
        String str2="dss";
        System.out.println(kmp.getIndexOf(str1,str2));
    }
}
