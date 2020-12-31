package 第一章_哈希与哈希表.并查集结构;

import java.util.Scanner;

//食物链 并查集补集
public class Test3 {
   private int[] fa;

    public Test3(int n) {
        fa=new int[3*n];
        for (int i = 0; i < 3*n; i++) {
            fa[i]=i;
        }
    }

    public int find(int i){
        return i==fa[i]?i:(fa[i]=find(fa[i]));
    }

    public boolean isSameSet(int i,int j){
        return fa[i]==fa[j];
    }

    public void union(int i,int j){
        int x=fa[i];
        int y=fa[j];
        if (x!=y){
            fa[x]=y;
        }
    }
    //并查集的补集策略  这里是放大三倍，0~n代表统一种类，n~2n代表其食物，2n~3n代表其天敌
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        String[] strings=scanner.nextLine().split(" ");
        int n=Integer.parseInt(strings[0]);
        int k=Integer.parseInt(strings[1]);
        Test3 t=new Test3(n);
        int total=0;
        for (int i = 0; i < k; i++) {
            String[] s = scanner.nextLine().split(" ");
            int r=Integer.parseInt(s[0]);
            int x=Integer.parseInt(s[1]);
            int y=Integer.parseInt(s[2]);
            if (x>=n||y>=n){
                total++;
                continue;
            }
            if (r==1){
                //如果x是y的食物或者y是x的食物，说明就是假话
                if (t.isSameSet(x,y+n)||t.isSameSet(x+n,y)){
                    total++;
                }else {
                    //否在合并
                    t.union(x,y);
                    t.union(x+n,y+n);
                    t.union(x+2*n,y+2*n);
                }
            }else {
                //如果xy是同一种或者是y吃x 就是假话
                if (t.isSameSet(x,y)||t.isSameSet(x,y+n)) {
                    total++;
                } else {
                    //是真话，按食物链合并
                    t.union(n+x,y);
                    t.union(x,2*n+y);
                    t.union(y+n,2*n+x);
                }
            }
        }
        System.out.println(total);
    }
}
