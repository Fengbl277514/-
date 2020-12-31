package 第二章_有序表.跳表;

public class Test {
    public static void main(String[] args) {
        SkipListMap listMap=new SkipListMap();
        listMap.put("a",1);
        listMap.put("b",2);
        listMap.put("c",4);
        listMap.put("d",5);
        listMap.put("d",2);
        listMap.delete("d");
        System.out.println(listMap.get("c"));
    }
}
