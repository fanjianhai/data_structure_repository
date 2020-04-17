package com.xiaofan.test

class Node[T](var data: T, var next: Option[Node[T]])

class StackDemo[T] {

  var headOpt: Option[Node[T]] = None
  var size = 0

  def clear() = {
    headOpt = None
    size = 0
  }

  def push(data: T) = {
    val newHead = new Node(data, headOpt)
    headOpt = Some(newHead)
    size += 1
  }

  def pop(): Option[Node[T]] = {
    headOpt match {
      case None => None
      case Some(head) => {
        headOpt = head.next
        size -= 1
        Some(head)
      }
    }
  }

  def mkString(): String = {
    headOpt.map(head => {
      var node = head
      val result = new StringBuilder

      while (node.next.isDefined) {
        result.append(node.data).append(" ")
        node = node.next.get
      }

      result.append(node.data)
      result.mkString
    }).getOrElse("")
  }
}


object StackDemo{
  def main(args: Array[String]): Unit = {
    val sd = new StackDemo[Int];
    sd.push(100)
    sd.push(200)
    print(sd.mkString())

  }
}


class BrowserDemo(var currentPageOpt: Option[String], val backStack: StackDemo[String], val forwardStack: StackDemo[String]) {

  def this() = this(None, new StackDemo[String], new StackDemo[String])

  def open(page: String) = {
    currentPageOpt.foreach(backStack.push)
    forwardStack.clear()
    currentPageOpt = Some(page)
  }

  def canGoBack(): Boolean = backStack.size > 0

  // 后退
  def goBack = deal(backStack.pop, forwardStack)

  def canGoForward: Boolean = forwardStack.size > 0

  // 前进
  def goForward = deal(forwardStack.pop, backStack)

  // 处理后退和前进逻辑
  def deal(data: Option[Node[String]], stack: StackDemo[String]): Unit = {
    data.foreach(page => {
      stack.push(currentPageOpt.get)
      currentPageOpt = Some(page.data)
    })
  }
}

object BrowserDemo {
  def main(args: Array[String]): Unit = {
    val bd = new BrowserDemo()
    bd.open("http://www.baidu.com")
    bd.open("http://www.jd.com")
    bd.open("http://www.taobao.com")
    print("forwardStackInfo: " + bd.forwardStack.mkString())
    print("backwardStackInfo: " + bd.backStack.mkString())

    bd.goBack
    print("forwardStackInfo: " + bd.forwardStack.mkString())
    print("backwardStackInfo: " + bd.backStack.mkString())

  }
}
