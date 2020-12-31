package 第一章_哈希与哈希表.一致性hash;

import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

//一致性hash操作
public class ConsistentHash {
    private final IHashService iHashService;

    //虚拟节点个数
    private final Integer numberOfReplicas;
    //虚拟节点和真实节点的映射关系,环形虚拟节点
    private final SortedMap<Long,Node> circle=new TreeMap<>();

    public ConsistentHash(IHashService iHashService, Integer numberOfReplicas, List<Node> nodes) {
        this.iHashService = iHashService;
        this.numberOfReplicas = numberOfReplicas;
        for (Node node : nodes) {
            add(node);
        }
    }

    //add 添加一个真实节点
    public void add(Node node){
        for (int i = 0; i < numberOfReplicas; i++) {
            circle.put(iHashService.hash(node+""+i),node);
        }
    }

    //删除一个真实节点
    public void remove(Node node){
        for (int i = 0; i < numberOfReplicas; i++) {
            circle.remove(iHashService.hash(node+""+i));
        }
    }


    //返回，数据属于那个真实结点
    public Node get(String key){
        if (circle.isEmpty()) return null;
        long hash = iHashService.hash(key);
        if (!circle.containsKey(hash)){
            //tailMap(K fromKe)方法用于返回此映射，其键大于或等于fromKey的部分视图。
            // 返回的映射受此映射支持，因此改变返回映射反映在此映射中，反之亦然。
            SortedMap<Long,Node> tailMap=circle.tailMap(hash);
            hash=tailMap.isEmpty()?circle.firstKey():tailMap.firstKey();
        }
        return circle.get(hash);
    }
}
