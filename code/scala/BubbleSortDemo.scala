package com.xiaofan.data_structure

object BubbleSortDemo {
  import scala.util.control.Breaks.{breakable, break}


  object Sorts {

    def bubbleSort(items: Array[Int]): Array[Int] = {
      val length = items.length
      if( length <= 1) return items

      // 最后一次交换的位置
      var lastExchange = 0
      // 无序数据的边界,每次只需要比较到这里即可退出
      var sortBorder = length - 1

      breakable {
        for (i <- Range(0, length)) {
          var exit = false
          for (j <- Range(0, sortBorder)) {
            if (items(j) > items(j + 1)) {
              val temp = items(j)
              items(j) = items(j + 1)
              items(j + 1) = temp
              // 此次冒泡有数据交换
              exit = true
              // 更新最后一次交换的位置
              lastExchange = j
            }
          }

          sortBorder = lastExchange
          if (!exit) {
            break
          }
        }
      }

      items
    }

    def main(args: Array[String]): Unit = {
      val arr = Array(1,2,7,4,5,6)
      arr.foreach(print)
      val result = bubbleSort(arr)
      println()
      result.foreach(print)
    }
  }
}
