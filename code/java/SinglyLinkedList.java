/**
 * 1. 单链表的插入、删除、查找操作
 * 2. 链表中存储的都是int类型数据
 * 3. 判断回文
 */
public class SinglyLinkedList {

    private Node head = null;

    public Node findByValue(int value) {
        Node p = head;
        while(p != null && p.data != value) {
            p = p.next;
        }
        return p;
    }

    public Node findByIndex(int index) {
        Node p = head;
        int pos = 0;
        while(p != null && pos != index) {
            p = p.next;
            ++pos;
        }
        return p;
    }

    /**
     * 无头结点
     * 表头部插入
     * 这种操作将与输入的顺序相反， 逆序
     */
    public void insertToHead(int value) {
        Node newNode = new Node(value, null);
        insertToHead(newNode);
    }

    public void insertToHead(Node newNode) {
        if (head == null) {
            head = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
    }

    public void insertTail(int value) {
        Node newNode = new Node(value, null);
        // 空链表， 可以插入新节点作为head， 也可以不操作
        if (head == null) {
            head = newNode;
        } else {
            Node q = head;
            while(q.next != null) {
                q = q.next;
            }
            q.next = newNode;

        }
    }

    public void insertAfter(Node p, int value) {
        Node newNode = new Node(value, null);
        insertAfter(p, newNode);
    }

    public void insertAfter(Node p, Node newNode) {
        if (p == null) return;
        newNode.next = p.next;
        p.next = newNode;
    }

    public void insertBefore(Node p, Node newNode) {
        if (p == null) return;
        if (head == p) {
            insertToHead(newNode);
            return;
        }
        Node q = head;
        while(q != null && q.next != p) {
            q = q.next;
        }

        if (q == null) return;

        newNode.next = p;
        q.next = newNode;
    }

    public void insertBefore(Node p, int value) {
        Node newNode = new Node(value, null);
        insertBefore(p,newNode);
    }

    public void deleteByNode(Node p) {
        if(p == null || head == null) return;
        if (p == head) {
            head = head.next;
            return;
        }

        Node q = head;
        while(q != null && q.next != p) {
            q = q.next;
        }
        if (q == null) return;
        q.next = q.next.next;
    }

    /**
     * 删除第一个值相等的结点
     * @param value
     */
    public void deleteByValue(int value) {
        if (head == null) return;
        Node p = head;
        Node q = null;
        while(p != null && p.data != value) {
            q = p;
            p = p.next;
        }
        if (p == null) return;
        if (q == null) {
            head = head.next;
        } else {
            q.next = q.next.next;
        }
    }

    public void printAll() {
        Node p = head;
        while(p != null) {
            System.out.print(p.data + " ");
            p = p.next;
        }
        System.out.println();
    }

    public static Node createNode(int value) {
        return new Node(value, null);
    }

    // 判断true或者false
    public boolean IFResult(Node left, Node right) {
        Node l = left;
        Node r = right;
        System.out.println("left_:"+l.data);
        System.out.println("right_:"+r.data);
        while (l != null && r != null) {
            if (l.data == r.data) {
                l = l.next;
                r = r.next;
                continue;
            }else {
                break;
            }
        }
        if (l == null && r == null) {
            return true;
        } else {
            return false;
        }
    }

    public boolean palindrome() {
        if (head == null) {
            return false;
        } else {
            System.out.println("开始执行找到中间节点");
            Node p = head;
            Node q = head;
            // 只有一个结点
            if (p.next == null) {
                return true;
            }
            while(q.next != null && q.next.next != null) {
                p = p.next;
                q = q.next.next;
            }

            System.out.println("中间节点" + p.data);
            System.out.println("开始执行奇数节点的回文判断");

            Node leftLink = null;
            Node rightLink = null;
            if(q.next == null) {
                //　p 一定为整个链表的中点，且节点数目为奇数
                rightLink = p.next;
                leftLink = inverseLinkList(p).next;
                System.out.println("左边第一个节点"+leftLink.data);
                System.out.println("右边第一个节点"+rightLink.data);
            } else {
                //p q　均为中点
                rightLink = p.next;
                leftLink = inverseLinkList(p);
            }
            return IFResult(leftLink, rightLink);
        }
    }

    // 无头结点的链表翻转
    public Node inverseLinkList(Node p) {
        Node pre = null;
        Node r = head;
        System.out.println("z---" + r.data);
        Node next = null;
        while (r != p) {
            next = r.next;
            r.next = pre;
            pre = r;
            r = next;
        }
        r.next = pre;
        return r;
    }
    // 带头结点的链表翻转
    public Node inverseLinkList_head(Node p) {
        // head 为新建的一个头结点
        Node head = new Node(9999, null);
        // p 为原来整个链表的头结点，现在head指向 整个链表
        head.next = p;
        // 带头结点的链表翻转等价于从第二个元素开始重新头插法建立链表
        Node cur = p.next;
        p.next = null;
        Node next = null;

        while (cur != null) {
            next = cur.next;
            cur.next = head.next;
            head.next = cur;
            System.out.println("first " + head.data);
            cur = next;
        }
        // 返回左半部分的重点之前的那个结点，从此处开始同步像两边比较
        return head;

    }


    public static class Node {
        private int data;
        private Node next;

        public Node() {
        }

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }

        public int getData() {
            return data;
        }
    }

    public boolean isLoop(Node head) {
        if(head == null) return false;
        Node slow = head;
        Node fast = head;
        
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        SinglyLinkedList link = new SinglyLinkedList();
        int data[] = {1,2,5,3,1};
        for (int i =0; i < data.length; i++) {
            link.insertTail(data[i]);
        }
        link.printAll();

        if (link.palindrome()){
            System.out.println("回文");
        }else{
            System.out.println("不是回文");
        }
    }
}