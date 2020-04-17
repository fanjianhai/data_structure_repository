package com.xiaofan.recursive

import scala.collection.mutable

class RecursiveDemo {

  def calculateStepWays(steps: Int): Int = {
    val knownResults = mutable.HashMap.empty[Int, Int]
    steps match {
      case 1 => 1
      case 2 => 2
      case _ => knownResults.get(steps) match {
        case Some(result) => result
        case None => {
          val result = calculateStepWays(steps - 1) + calculateStepWays(steps - 2)
          knownResults.put(steps, result)
          result
        }
      }
    }
  }
}

object  RecursiveDemo {
  def main(args: Array[String]): Unit = {
    val rd = new RecursiveDemo()
    val finalStep = rd.calculateStepWays(3)
    println(finalStep)
  }
}

