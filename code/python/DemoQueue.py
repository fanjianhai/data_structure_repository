"""
    Queue based upon array
    用数组实现的队列

    Author: fan
"""

from typing import Optional


class ArrayQueue:

    def __init__(self, capacity: int):
        self._items = []
        self._capacity = capacity
        self._head = 0
        self._tail = 0

    def enqueue(self, item: str) -> bool:
        if self._tail == self._capacity:
            if self._head == 0:
                return False
            else:
                for i in range(0, self._tail - self._head):
                    self._items[i] = self._items[i + self._head]
                self._tail = self.tail - self._head
                self._head = 0

        self._items.insert(self._tail, item)
        self._tail += 1
        return True

    def dequeue(self) -> Optional[str]:
        if self._head != self._tail:
            item = self._items[self._head]
            self._head += 1
            return item
        else:
            return None

    def __str__(self):
        return " ".join(item for item in self._items[self._head: self._tail])


if __name__ == '__main__':
    arr = ArrayQueue(5)
    for i in range(5):
        arr.enqueue(str(i))  # 注意：python的int类型数据强转成字符串

    print(arr)


===========================================================================================
"""
    Queue based upon array
    用链表实现的队列

    Author: fan
"""

from typing import Optional


class Node:

    def __init__(self, data: str, next=None):
        self.data = data
        self.next = next


class LinkedQueue:

    def __init__(self):
        self._head: Optional[Node] = None
        self._tail: Optional[Node] = None

    def enqueue(self, value: str):
        new_node = Node(value)
        if self._tail:
            self._tail.next = new_node
        else:
            self._head = new_node

        self._tail = new_node

    def dequeue(self) -> Optional[str]:
        if self._head:
            value = self._head.data
            self._head = self._head.next
            if not self._head:
                self._tail = None
            return value

    def __repr__(self):
        values = []
        current = self._head
        while current:
            values.append(current.data)
            current = current.next
        return "->".join(value for value in values)


if __name__ == "__main__":
    q = LinkedQueue()
    for i in range(10):
        q.enqueue(str(i))
    print(q)

    for _ in range(3):
        q.dequeue()
    print(q)

    q.enqueue("7")
    q.enqueue("8")
    print(q)