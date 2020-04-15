package com.xiaofan.test

import scala.util.control.Breaks._

class Node(var data: Int, var next: Option[Node])

class SinglyLinkedListScala(var headOpt: Option[Node]) {

  def this() = this(None)

  def findByValue(value: Int): Option[Node] = {
    headOpt.flatMap(head => {
      var node = head
      while (!node.data.equals(value) && node.next.isDefined) {
        node = node.next.get
      }

      if (node.data.equals(value)) {
        return Some(node)
      } else {
        return None
      }
    })
  }

  def insertToHead(value: Int): Unit = {
    val newNode = new Node(value, None)
    insertToHead(newNode)
  }

  def insertToHead(newNode: Node): Unit = {
    headOpt match {
      case None =>
        // it's an empty linked list, make new node as head
        headOpt = Some(newNode)
      case Some(head) =>
        newNode.next = Some(head)
        headOpt = Some(newNode)
    }
  }

  def insertTail(value: Int): Unit = {
    val newNode = new Node(value, None)
    insertTail(newNode)
  }

  def insertTail(newNode: Node): Unit = {
    headOpt match {
      case None =>
        // it's an empty linked list, make new node as head
        headOpt = Some(newNode)
      case Some(head) =>
        // need to start fo find from head to current tail
        var node = head;
        while(node.next.isDefined) {
          node = node.next.get
        }
        // now node is the tail as node.next is None, add new tail
        node.next = Some(newNode)
    }
  }

  def deleteByNode(node: Node): Unit = {
    headOpt.map(head => {
      if (head.equals(node)) {
        headOpt = node.next
      } else {
        var p: Node = head;
        while(p.next.isDefined && !p.next.get.equals(node)) {
          p = p.next.get
        }

        if(p.next.isEmpty) {
          throw new IllegalArgumentException("could not find given node.")
        }
        p.next = node.next


      }
    })
  }

  def mkString(): String = {
    headOpt.map(head => {
      var node = head
      val result = new StringBuilder

      while(node.next.isDefined) {
        result.append(node.data)
        node = node.next.get
      }

      result.append(node.data)
      result.mkString
    }).getOrElse("")
  }

  // 回文判断
  def isPalindrome(): Boolean = {
    headOpt match {
      case None => false
      case Some(head) =>
        var p: Node = head
        var q: Node = head

        if (p.next.isEmpty) {
          return true
        }

        while(q.next.isDefined && q.next.get.next.isDefined) {
          p = p.next.get
          q = q.next.get.next.get
        }

        var leftLink: Option[Node] = None
        var rightLink: Option[Node] = None
        q.next match {
          case None =>
            rightLink = p.next
            leftLink = inverseLink(p).next
          case Some(_) =>
            rightLink = p.next
            leftLink = Some(inverseLink(p))
        }
        compareLinkedNodes(leftLink, rightLink)

    }
  }

  def compareLinkedNodes(leftLink: Option[Node], rightLink:Option[Node]): Boolean = {
    var left = leftLink
    var right = rightLink
    breakable {
      while(left.isDefined && right.isDefined) {
        if(!left.get.data.equals(right.get.data)) {
          break
        }
        left = left.get.next
        right = right.get.next
      }
    }
    left.isEmpty && right.isEmpty
  }

  // 翻转链表
  def inverseLink(node: Node): Node = {
    if(headOpt.isEmpty) {
      throw new IllegalArgumentException("list is empty.")
    }

    var pre: Option[Node] = None
    var next: Option[Node] = None
    var current: Option[Node] = headOpt

    while (current.isDefined && !current.get.equals(node)) {
      next = current.get.next
      current.get.next = pre
      pre = current
      current = next
    }

    current.get.next = pre
    current.get
  }
}

object SinglyLinkedListScala {
  def main(args: Array[String]): Unit = {
    val lst = new SinglyLinkedListScala()
    lst.insertTail(1)
    lst.insertTail(2)
    lst.insertTail(3)
    lst.insertTail(1)
    val result = lst.mkString()
    print(result)

    print(lst.isPalindrome())
  }
}