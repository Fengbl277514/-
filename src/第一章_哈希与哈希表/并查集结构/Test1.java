package 第一章_哈希与哈希表.并查集结构;

import java.util.Scanner;

//亲戚关系
public class Test1 {
    private static int[] fa;
    public static void init(int n){
        int[] x=new int[n];
        for (int i = 0; i < n; i++) {
            x[i]=i;
        }
        fa=x;
    }

    public static int find(int i){
        return i==fa[i]?i:(fa[i]=find(fa[i]));
    }

    public static void merge(int i ,int j){
        fa[find(i)]=find(j);
    }

    public static boolean isSameSet(int i,int j){
        return find(i)==find(j);
    }

    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        String[] s = scanner.nextLine().split(" ");
        int n=Integer.parseInt(s[0]);
        int m=Integer.parseInt(s[1]);
        int p=Integer.parseInt(s[2]);
        init(n);
        for (int i=0;i<m;i++){
            String[] str = scanner.nextLine().split(" ");
            merge(Integer.parseInt(str[0]),Integer.parseInt(str[1]));
        }

        for (int i=0;i<p;i++){
            String[] str = scanner.nextLine().split(" ");
            System.out.println(isSameSet(Integer.parseInt(str[0]),Integer.parseInt(str[1])));
        }
    }
}
