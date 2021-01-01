package 第三章_KMP和Mancher算法.KMP;

public class Kmp {
//    //返回str1包含str2的位置，没有就返回-1
//    public int getIndexOf(String str1,String str2){
//        char[] chars1 = str1.toCharArray();
//        char[] chars2 = str2.toCharArray();
//        int[] next=getNextArray(chars2);
//        int x=0,y=0;
//        while (x<chars1.length&&y<chars2.length){
//            if (chars1[x]==chars2[y]){//匹配
//                x++;
//                y++;
//            }else if(y==0){//y=0，说明都不匹配，x++  等价于chars2[y]=-1
//                x++;
//            }else {//跳转
//                y=next[y];
//            }
//        }
//        return y==chars2.length?x-y:-1;
//    }
//
//    public int[] getNextArray(char[] chars){
//        if (chars.length==1){
//            return new int[]{-1};
//        }
//        int[] next=new int[chars.length];
//        next[0]=-1;
//        next[1]=0;
//        int i=2;
//        int cn=0;//等价于next[i-1] 初始就是next[2-1]=0
//        while (i<chars.length){
//            //如果，chars[cn]=char[i-1] 就说明找到了，同时，cn++，i++；注意只能是++cn，而不是cn+1，因为下一个i是从cn开始的
//            if (chars[i-1]==chars[cn]){
//                next[i++]=++cn;
//            }else if (cn==0){//没找到匹配的
//                next[i++]=0;
//            }else {//一直继续
//                cn=next[cn];
//            }
//        }
//        return next;
//    }
//






    public int getIndexOf(String str1,String str2){
        if (str1.length()<str2.length()){
            return -1;
        }
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();
        int x=0;
        int y=0;
        int[] next=getNextArray(chars1);
        while (x<chars1.length&&y<chars2.length){
            if (chars1[x]==chars2[y]){
                x++;
                y++;
            }else if (next[y]==-1){
                x++;
            }else {
                y=next[y];
            }
        }
        return y==chars2.length?x-y:-1;
    }

    public int[] getNextArray(char[] chars){
        if (chars.length==1){
            return new int[]{-1};
        }
        int[] next=new int[chars.length];
        next[0]=-1;
        next[1]=0;
        int i=2;
        int cn=0;
        while (i<chars.length){
            if (chars[i-1]==chars[cn]){
                next[i++]=++cn;
            }else if (cn==0){
                i++;
            }else {
                cn=next[cn];
            }
        }
        return next;
    }
}
