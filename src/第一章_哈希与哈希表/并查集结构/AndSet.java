package 第一章_哈希与哈希表.并查集结构;

public class AndSet {
    //find父节点
    public static int find(int i,int[] father){
        if (father[i]==i){
            return i;
        }else {
            //路径压缩
            father[i]=find(father[i],father);
            return father[i];
        }
    }

    //合并
    public static void merge(int i,int j,int[] fa,int[] rank){
        int x=find(i,fa);
        int y=find(j,fa);
        if (rank[i]>=rank[j]){
            fa[j]=y;
        }else {
            fa[i]=x;
        }
        if (rank[i]==rank[j]&&x!=y){
            rank[y]++;
        }
    }

    public static void main(String[] args) {
        int[] fa=new int[5];
        int[] rank=new int[5];//秩
        int n=5;
        //并查集初始化
        for (int i = 0; i < n; i++) {
            fa[i]=i;
            rank[i]=1;
        }


    }
}
