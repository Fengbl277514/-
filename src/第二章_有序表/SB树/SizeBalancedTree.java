package 第二章_有序表.SB树;
//Size Balanced Tree
//任意一个叔叔节点的子树的size不能小于其子侄节点的子树的size
public class SizeBalancedTree {
    private Node root;
    private class Node{
        Integer val;
        String key;
        Node left;
        Node right;
        //这个节点的不同key的节点个数
        Integer size;

        public Node( String key,Integer val) {
            this.val = val;
            this.key = key;
            this.size = 1;
        }
    }

    //添加一个节点
    public void put(String key,Integer val){
        if(key==null){
            return;
        }
        Node nearKeyNode = findLastNode(key);
        if (nearKeyNode!=null&&nearKeyNode.key.equals(key)){
            nearKeyNode.val=val;
        }else {
            root=add(root,key,val);
        }
    }


    //获取一个值
    public Integer get(String key){
        Node node= findNode(key);
        if (node==null){
            throw new RuntimeException("没找到key值对应的value");
        }else {
            return node.val;
        }
    }

    //删除一个值
    public void remove(String key){
        if (key==null){
            throw new RuntimeException("没找到key值对应的value");
        }
        if (containsKey(key)){
            root=delete(root,key);
        }
    }

    //是否包含key值
    public boolean containsKey(String key){
        return findNode(key)!=null;
    }

    //add方法,返回新的头节点
    private Node add(Node cur,String key,Integer val){
        if (cur==null){
            return new Node(key,val);
        }else {
            cur.size++;
            if (cur.key.compareTo(key)<0){
                cur.right= add(cur.right,key,val);
            }else {
                cur.left= add(cur.left,key,val);
            }
            return balance(cur);
        }
    }

    //Balance方法，在添加节点后对经过的所有节点都进行平衡，并返回新的当前头节点
    private Node balance(Node cur){
        if (cur==null){
            return null;
        }
        //ll情况
        if (cur.right!=null&&cur.left!=null&&cur.left.left!=null&&cur.right.size<cur.left.left.size){
            cur=rightRotate(cur);
            cur.right= balance(cur.right);
            cur=balance(cur);
        }
        //lr情况
        else if (cur.right!=null&&cur.left!=null&&cur.left.right!=null&&cur.right.size<cur.left.right.size){
            cur.left=leftRotate(cur.left);
            cur=rightRotate(cur);
            cur.left=balance(cur.left);
            cur.right=balance(cur.right);
            cur=balance(cur);
        }
        //rr情况
        else if (cur.left!=null&&cur.right!=null&&cur.right.right!=null&&cur.left.size<cur.right.right.size){
            cur=leftRotate(cur);
            cur.left=balance(cur.left);
            cur=balance(cur);
        }
        //rl情况
        else if(cur.right!=null&&cur.left!=null&&cur.right.left!=null&&cur.left.size<cur.right.left.size){
            cur.right=rightRotate(cur.right);
            cur=leftRotate(cur);
            cur.right=balance(cur.right);
            cur.left=balance(cur.left);
            cur=balance(cur);
        }
        return cur;
    }

    //删除一个节点，并返回新的头部
    private Node delete(Node cur,String key){
        cur.size--;
        if (cur.key.compareTo(key)<0){
            cur.right=delete(cur.right,key);
        }else if (cur.key.compareTo(key)>0){
            cur.left=delete(cur.left,key);
        }else {
            if (cur.left==null&&cur.right==null){
                cur=null;
            }else if (cur.right==null&&cur.left!=null){
                cur=cur.left;
            }else if (cur.left==null&&cur.right!=null){
                cur=cur.right;
            }else {
                Node pre=null;
                Node des=cur.right;
                while (des.left!=null){
                    des.size--;
                    pre=des;
                    des=des.left;
                }
                if (pre!=null){
                    pre.left=des.right;
                    des.right=cur.right;
                }
                des.left=cur.left;
                des.size=(des.left!=null?des.left.size:0)+(des.right!=null?des.right.size:0)+1;
                cur=des;
            }
        }
        return cur;
    }



    //查找一个节点
    private Node findNode(String key){
        Node cur=root;
        while (cur!=null){
            if (cur.key.compareTo(key)>0){
                cur=cur.left;
            }else if (cur.key.compareTo(key)<0){
                cur=cur.right;
            }else {
                return cur;
            }
        }
        return cur;
    }

    //查找要插入的节点
    private Node findLastNode(String key){
        Node cur=root;
        Node pre=null;
        while (cur!=null){
            pre=cur;
            if (cur.key.compareTo(key)>0){
                cur=cur.left;
            }else if (cur.key.compareTo(key)<0){
                cur=cur.right;
            }else {
               break;
            }
        }
        return pre;
    }

    //一个节点左旋,返回新的头节点
    private Node leftRotate(Node cur){
        Node right=cur.right;
        cur.right=right.left;
        right.left=cur;
        right.size=cur.size;
        cur.size=(cur.left==null?0:cur.left.size)+(cur.right==null?0:cur.right.size)+1;
        return right;
    }

    //一个节点右旋,返回新的头节点
    private Node rightRotate(Node cur){
        Node left=cur.left;
        cur.left=left.right;
        left.right=cur;
        left.size=cur.size;
        cur.size=(cur.left==null?0:cur.left.size)+(cur.right==null?0:cur.right.size)+1;
        return left;
    }
}
