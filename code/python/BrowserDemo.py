"""
    Stack based upon linked list
    基于链表实现的栈

    Author: fan
"""

from typing import Optional


class Node:

    def __init__(self, data: int, next=None):
        self._data = data
        self._next = next

    @property
    def data(self):
        return self._data

    @property
    def next(self):
        return self._next

    @next.setter
    def next(self, next):
        self._next = next


class LinkedStack:
    """A stack based upon singly-linked list.
    """

    def __init__(self):
        self._top: Node = None

    def push(self, value: int):
        new_top = Node(value)
        new_top.next = self._top
        self._top = new_top

    def pop(self) -> Optional[int]:
        if self._top:
            value = self._top.data
            self._top = self._top.next
            return value

    def __str__(self) -> str:
        current = self._top
        nums = []
        while current:
            nums.append(current.data)
            current = current.next
        return " ".join(f"{num}]" for num in nums)


if __name__ == '__main__':
    stack = LinkedStack()
    for i in range(9):
        stack.push(i)
    print(stack)

    for _ in range(30):
        stack.pop()
    print(stack)

//=====================================================

"""
    a simple browser realize
    Author: fan
    解答：我们使用两个栈，X 和 Y，我们把首次浏览的页面依次压入栈 X，当点击后退按钮时，再依次从栈 X 中出栈，
    并将出栈的数据依次放入栈 Y。当我们点击前进按钮时，我们依次从栈 Y 中取出数据，放入栈 X 中。
    当栈 X 中没有数据时，那就说明没有页面可以继续后退浏览了。当栈 Y 中没有数据，
    那就说明没有页面可以点击前进按钮浏览了。
"""

from com.fanfan.data_structure.linked_stack import LinkedStack


class NewLinkedStack(LinkedStack):
    """自定义堆栈
    """
    def is_empty(self):
        return not self._top


class Browser:

    def __init__(self):
        self.forward_stack = NewLinkedStack()
        self.back_stack = NewLinkedStack()

    def can_forward(self):
        return not self.back_stack.is_empty()

    def can_back(self):
        return not self.forward_stack.is_empty()

    def open(self, url):
        print("Open new url %s" % url, end="\n")
        self.forward_stack.push(url)

    def back(self):
        if self.forward_stack.is_empty():
            return

        top = self.forward_stack.pop()
        self.back_stack.push(top)
        print("back to %s" % top, end= "\n")

    def forward(self):
        if self.back_stack.is_empty():
            return

        top = self.back_stack.pop()
        self.forward_stack.push(top)
        print("forward to %s" % top, end="\n")


if __name__ == '__main__':

    browser = Browser()
    browser.open('a')
    browser.open('b')
    browser.open('c')
    if browser.can_back():
        browser.back()

    if browser.can_forward():
        browser.forward()

    browser.back()
    browser.back()
    browser.back()