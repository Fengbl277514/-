package 第一章_哈希与哈希表.并查集结构;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

//关押罪犯
public class Test2 {

    static class Relation {
        int one;
        int two;
        int nv;

        public Relation(int one, int two, int nv) {
            this.one = one;
            this.two = two;
            this.nv = nv;
        }
    }

    public static int find(int i,int[] fa){
        return i==fa[i]?i:(fa[i]=find(find(i,fa),fa));
    }

    public static void union(int i,int j,int[] fa){
        fa[find(i,fa)]=find(j,fa);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        int n=Integer.parseInt(s[0]);
        int m=Integer.parseInt(s[0]);
        int[] fa=new int[ 2*n];
        for (int i=0;i<2*n;i++){
            fa[i]=i;
        }
        List<Relation> list=new ArrayList<>();
        for (int i = 0; i < m; i++) {
            String[] str = br.readLine().split(" ");
            list.add(new Relation(Integer.parseInt(str[0]),Integer.parseInt(str[1]),Integer.parseInt(str[2])));
        }
        list.sort((o1, o2) -> {
            return o1.nv-o2.nv;
        });
        for (int i=0;i<m;i++){
            Relation a=list.get(i);
            if (find(a.one,fa)==find(a.two,fa)){
                System.out.println(a.nv);
                break;
            }else {
                union(a.one,a.two+n,fa);
                union(a.two,a.one+n,fa);
                if (i==m-1){
                    System.out.println(0);
                }
            }
        }

    }
}
