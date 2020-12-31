package 第一章_哈希与哈希表.并查集结构;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MergeSet {
    //元素集合
    HashMap<Integer,Element> elementMap;
    //每个元素的父节点的集合
    HashMap<Element,Element> fatherMap;
    //每个集合的大小
    HashMap<Element,Integer> mapSize;


    public MergeSet(List<Integer> list){
        elementMap=new HashMap<>();
        fatherMap=new HashMap<>();
        mapSize=new HashMap<>();
        for (Integer val : list) {
            Element e=new Element(val);
            elementMap.put(val,e);
            fatherMap.put(e,e);
            mapSize.put(e,1);
        }
    }

    public Element findHead(Element e){
        ArrayList<Element> arr=new ArrayList<>();
        while (e!=fatherMap.get(e)){
            arr.add(e);
            e=fatherMap.get(e);
        }
        //这一步是为了减少时间复杂度，让并查集的两个主要操作都是O(1)的
        for (Element element : arr) {
            fatherMap.put(element,e);
        }
        return e;
    }

    public  boolean isSameSet(Integer a,Integer b){
       if (elementMap.containsKey(a)&&elementMap.containsKey(b)){
           Element af = findHead(elementMap.get(a));
           Element head = findHead(elementMap.get(b));
           return af==head;
       }
       //不存在返回false
       return false;
    }

    public void union(Integer a,Integer b){
        if (elementMap.containsKey(a)&&elementMap.containsKey(b)){
            Element af=fatherMap.get(elementMap.get(a));
            Element bf=fatherMap.get(elementMap.get(b));
            if (af!=bf){
                int as=mapSize.get(elementMap.get(af));
                int bs=mapSize.get(elementMap.get(bf));
                if (as>bs){
                    fatherMap.put(bf,af);
                    mapSize.put(af,as+bs);
                    mapSize.remove(bf);
                }else {
                    fatherMap.put(af,bf);
                    mapSize.put(bf,as+bs);
                    mapSize.remove(af);
                }
            }
        }
    }
}
