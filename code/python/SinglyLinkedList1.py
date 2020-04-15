# 1.单链表的插入、删除、查找操作；
# 2.链表中存储的数据类型是Int
#
# Author: fan


class Node:
    """链表结构的Node结点"""

    def __init__(self, data, next_node=None):
        """Node结点的初始化方法
        参数：
            data：存储的数据
            next_node：下一个Node结点的引用地址
        """
        self.__data = data
        self.__next = next_node

    @property
    def data(self):
        """Node结点存储数据的获取
        返回：
            当前Node结点存储的数据
        """
        return self.__data

    @data.setter
    def data(self, data):
        """Node结点存储数据的设置方法
        参数：
            data：新的存储数据
        """
        self.__data = data

    @property
    def next_node(self):
        """获取Node结点的next指针值
        返回：
            next指针数据
        """
        return self.__next

    @next_node.setter
    def next_node(self, next_node):
        """Node结点next指针的修改方法
        参数：
            next：新的下一个Node结点的引用
        """
        self.__next = next_node


class SinglyLinkedList1:
    """单向链表"""

    @property
    def head(self):
        return self.__head

    @head.setter
    def head(self, head):
        self.__head = head

    def __init__(self):
        """单向链表初始化方法"""
        self.__head = None
        self.__len = 0

    def find_by_value(self, value):
        """按照数据值在单向列表中查找
        参数：
            value：查找的数据
        返回：
            Node
        """
        cur = self.__head
        while cur is not None and cur.data != value:
            cur = cur.next_node
        return cur

    def find_by_index(self, index):
        """按照索引值在链表中查找
        参数：
            index：索引值
        返回：
            Node
        """
        cur = self.__head
        pos = 0
        while cur is not None and pos != index:
            cur = cur.next_node
            pos += 1
        return cur

    def insert_to_head(self, value):
        """在链表的头部插入一个存储value数值的Node结点
        参数：
            value：将要存储的数据
        """
        cur = Node(value)
        cur.next_node = self.__head
        self.__head = cur
        self.__len += 1

    def insert_after(self, node, value):
        """在链表的某个指定Node结点之后插入一个存储value数据的Node结点
        参数：
            node：指定的一个node结点
            value：将要存储在新Node结点中的数据
        """
        if node is None:
            print("current node is invalid.")
            return

        new_node = Node(value)
        new_node.next_node = node.next_node
        node.next_node = new_node
        self.__len += 1

    def insert_before(self, node, value):
        """在链表的某个指定Node结点之前插入一个存储value数据的Node结点
        参数：
            node：指定的一个node结点
            value：将要存储在新Node结点中的数据
        """
        if node is None or self.__head is None:
            print("node is None.")
            return

        if node == self.__head:
            self.insert_to_head(value)
            return

        new_node = Node(value)
        pre = self.__head
        not_found = False

        while pre.next_node != node:
            if pre.next_node is None:
                not_found = True
                break
            else:
                pre = pre.next_node

        if not not_found:
            pre.next_node = new_node
            new_node.next_node = node
            self.__len += 1

    def delete_by_node(self, node):
        """在链表中删除指定的Node结点
        参数：
            node：指定的Node结点
        """
        if self.__head is None:
            return

        if node == self.__head:
            self.__head = node.next_node
            self.__len -= 1
            return

        pre = self.__head
        not_found = False  # 如果在整个链表当中都没有找到相应的结点，则标记为True

        while pre.next_node != node:
            if pre.next_node is None:
                not_found = True
                break
            else:
                pre = pre.next_node

        if not not_found:
            pre.next_node = node.next_node
            self.__len -= 1

    def delete_by_value(self, value):
        """在链表中删除指定存储数据的Node结点
        参数：
            value：指定的存储数据
        """
        if self.__head is None:
            return

        if self.__head.data == value:
            self.__head = self.__head.next_node
            self.__len -= 1

        pre = self.__head
        node = self.__head.next_node
        not_found = False

        while node.data != value:
            if node.next_node is None:
                not_found = True
                break
            else:
                pre = node
                node = node.next_node

        if not not_found:
            pre.next_node = node.next_node
            self.__len -= 1

    def delete_last_n_node(self, n):
        """删除链表中倒数第N个结点(0 < n <= self.__len)
        思路：
            设置快、慢两个指针、快指针，快指针先行，慢指针不动；当快指针跨了N步以后，快、慢指针同时往链表尾部移动，
            当快指针到达链表尾部的时候，慢指针所指向的就是链表倒数第N个结点
        参数：
            n：需要删除的倒数第N个结点
        """
        fast = self.__head
        slow = self.__head
        step = 1
        tmp = None

        if n <= 0 or n > self.__len:
            print("超出链表范围")
            return

        while step < n:
            fast = fast.next_node
            step += 1

        while fast.next_node is not None:
            tmp = slow
            fast = fast.next_node
            slow = slow.next_node
        else:  # python 特有的语法，当while条件不满足时会执行一次else
            if not tmp:
                self.__head = self.__head.next_node  # 倒数最后一个结点（第一个结点）
                self.__len -= 1
                return

        tmp.next_node = slow.next_node

    def find_mid_node(self):
        """查找链表中的中间结点(奇数返回中间，偶数返回左半边元素)
        思想：
            设置快、慢两种指针，快指针每次跨两步，慢指针每次跨一步，则当快指针到达链表尾部的时候，慢指针指向链表的
            中间结点
        返回：
            一个新的Node结点
        """
        if self.__head is None:
            print("空链表")
            return

        fast = self.__head
        slow = self.__head

        while fast.next_node is not None and fast.next_node.next_node is not None:
            slow = slow.next_node
            fast = fast.next_node.next_node

        return slow

    def reversed_self(self):
        """翻转链表自身"""
        if self.__head is None or self.__head.next_node is None:
            return

        pre = self.__head
        cur = self.__head.next_node

        while cur is not None:
            pre, cur = self.__reversed_with_two_node(pre, cur)

        self.__head.next_node = None
        self.__head = pre

    @staticmethod
    def __reversed_with_two_node(pre: Node, cur: Node):
        """翻转两个相邻结点
        参数：
            pre：前一个结点
            cur：当前结点
        返回：
            (pre,cur)：下一个相邻结点的元组
        """
        tmp = cur.next_node
        cur.next_node = pre
        pre = cur
        cur = tmp

        return pre, cur

    def has_ring(self):
        """检查链表当中是否有环
        思想：
            设置快、慢两种指针，快指针每次跨两步，慢指针每次跨一步，如果快指针没有与慢指针相遇而是顺利到达链表尾部
            说明没有环： 否则，存在环
        返回：
            True：有环
            False：没有环
        """
        fast = self.__head
        slow = self.__head

        while fast is not None and fast.next_node is not None:
            fast = fast.next_node
            slow = slow.next_node
            if fast == slow:
                return True

        return False

    def create_node(self, value):
        """创建一个存储value值的Node结点
        参数：
            value：将要存储在Node结点中的数据
        返回：
            Node
        """
        return Node(value)

    def print_all(self):
        """打印当前链表所有结点数据"""
        cur = self.__head

        if cur is None:
            print("当前链表还没有数据")
            return

        while cur.next_node is not None:
            print(str(cur.data) + " --> ", end="")
            cur = cur.next_node

        print(str(cur.data))


if __name__ == '__main__':
    lst = SinglyLinkedList1()

    for i in range(10):
        lst.insert_to_head(i)

    lst.print_all()
    # 查找中间结点
    node = lst.find_mid_node()
    print(node.data)
    # 删除倒数第2个结点
    lst.delete_last_n_node(2)
    lst.print_all()
    # 插入一个无效结点
    lst.insert_after(None, 10)
    # 翻转链表
    lst.reversed_self()
    lst.print_all()

    node = lst.create_node(100)
    print(node.data)
