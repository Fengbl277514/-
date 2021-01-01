package 第三章_KMP和Mancher算法.Mancher;
//求字符串的最大回文子串
public class Manacher {


    public int getMancher(String s){
        if (s==null&&s.length()==0){
            return 0;
        }
        char[] str=manacherString(s.toCharArray());
        //最右点，的下一个节点
        int R=-1;
        //中心点，由R确定，和R是一起变化的
        int C=-1;
        //每个字符的回文半径
        int[] pstr=new int[str.length];
        int max=Integer.MIN_VALUE;
        for (int i = 0; i < str.length; i++) {
            //每个节点最少不需要扩展的半径，包含了两种大情况，R>i表示包含i，否则不包含，就是1
            pstr[i]=R>i?Math.min(pstr[C*2-i],R-i):1;

            while (i-pstr[i]>-1&&i+pstr[i]<str.length){
                if (str[i-pstr[i]]==str[i+pstr[i]]){
                    pstr[i]++;
                }else {
                    break;
                }
            }

            if (i+pstr[i]>R){
                R=i+pstr[i];
                C=i;
            }
            max=Math.max(max,pstr[i]);
        }
        //半径减一
        return max-1;
    }

    public char[] manacherString(char[] chars){
        char[] newChars=new char[chars.length*2+1];
        int j=0;
        for (int i = 0; i < chars.length; i++) {
            newChars[j]='#';
            newChars[j+1]=chars[i];
            j=j+2;
        }
        newChars[j]='#';
        return newChars;
    }
}
