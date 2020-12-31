package 第一章_哈希与哈希表.一致性hash;

import java.util.*;

//测试类
public class TestHash {
    private static final String IP_PREFIX="192,168.0";
    public static void main(String[] args) {
        //存放真实机器上保存的数据条数
        Map<String,Integer> map=new HashMap<>();

        //真实节点数，这里模拟10台
        List<Node> nodes=new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            map.put(IP_PREFIX+i,0);
            Node node=new Node(IP_PREFIX+i,"node"+i);
            nodes.add(node);
        }

        IHashService iHashService=new HashService();
        //每台虚拟机引入500个节点
        ConsistentHash consistentHash=new ConsistentHash(iHashService,500,nodes);
//        for (int i = 0; i < 5000; i++) {
//            //产生随机一个字符串当作一条记录，可以是其他更复杂的业务对象，比如随机字符串相当于对象的唯一标识
//            String data= UUID.randomUUID().toString()+i;
//            Node node=consistentHash.get(data);
//            map.put(node.getIp(),map.get(node.getIp())+1);
//        }
//
//        for (String s : map.keySet()) {
//            System.out.println("节点记录数"+map.get(s));
//        }
        //删除一个节点，没影响
        consistentHash.remove(nodes.get(1));

        for (int i = 0; i < 10000; i++) {
            //产生随机一个字符串当作一条记录，可以是其他更复杂的业务对象，比如随机字符串相当于对象的唯一标识
            String data= UUID.randomUUID().toString()+i;
            Node node=consistentHash.get(data);
            map.put(node.getIp(),map.get(node.getIp())+1);
        }

        for (String s : map.keySet()) {
            System.out.println("节点记录数"+map.get(s));
        }
    }
}
