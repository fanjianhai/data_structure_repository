package com.xiaofan.test

class XFArray(capacity: Int) {

  var data: Array[Char] = new Array[Char](capacity)
  // 数组中元素的实际个数
  var length: Int = 0

  def find(index: Int): Char = {
    if(index < 0 || index >= length) {
      return (-1).toChar
    }
    data(index)
  }

  def insert(index: Int, value: Char): Boolean = {
    if(length == capacity) {
      return false
    }

    if(index < 0 || index >= capacity) {
      return false
    }
    // 注意： 取不到until后面的值，可以指定步长
    for(i <- length until index by -1) {
      data(i) = data(i - 1)
    }
    data(index) = value
    length += 1
    true
  }

  def delete(index: Int): Char = {
    if(length == 0) {
      throw new IllegalStateException("array is empty")
    }

    if(index >= length) {
      throw new IllegalStateException("index out of range, current data length is" + length)
    }

    val result = data(index)

    for(i <- index until length - 1) {
      data(i) = data(i + 1)
    }

    length -= 1
    result
  }

  def print: Unit = {
    println(data.subSequence(0, length))
  }
}


object ArrayDemo {
  def main(args: Array[String]): Unit = {
    val arrDemo = new XFArray(5)
    arrDemo.insert(0, 'a')
    arrDemo.insert(1, 'b')
    arrDemo.insert(2, 'c')
    arrDemo.insert(3, 'd')
    arrDemo.insert(4, 'e')

    arrDemo.print

    print(arrDemo.find(-1))
  }
}
