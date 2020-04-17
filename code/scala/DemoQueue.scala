package com.xiaofan.queue

import scala.reflect.ClassTag

trait DemoQueue[T] {

  var size = 0

  def enqueue(data: T): Unit

  def deQueue: Option[T]
}


class ArrayQueue[T: ClassTag](capacity: Int) extends DemoQueue[T] {

  var items: Array[T] = new Array[T](capacity)
  var head = 0
  var tail = 0


  override def enqueue(data: T): Unit = {
    require(tail < capacity, "queue is full..")
    items(tail) = data
    tail += 1
    size += 1
  }

  override def deQueue: Option[T] = {
    if (head < tail) {
      var result = Some(items(head))
      head += 1
      size -= 1
      result
    } else {
      None
    }
  }
}

class Node[T](var data: T, var next: Option[Node[T]])

class LinkListQueue[T] extends DemoQueue[T] {

  var headOpt: Option[Node[T]] = None
  var tailOpt: Option[Node[T]] = None

  override def enqueue(data: T): Unit = {
    val node = new Node(data, None)
    size += 1
    headOpt match {
      case None => headOpt = Some(node)
      case Some(_) =>
    }

    tailOpt match {
      case None => tailOpt = Some(node)
      case Some(tail) => {
        tail.next = Some(node)
        tailOpt = Some(node)
      }
    }
  }

  override def deQueue(): Option[T] = {
    headOpt.map(head => {
      size -= 1
      headOpt = head.next
      if(headOpt.isEmpty) {
        tailOpt = None
      }
      head.data
    })
  }

}
