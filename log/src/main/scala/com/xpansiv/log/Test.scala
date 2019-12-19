package com.xpansiv.log

import com.xpansiv.`macro`.Log


class Test {

  @Log
  def testMethod(argList: List[String]): List[String] = {
    val ret = argList.map(a => testPrivate(a))
    val num = 4
    ret
  }

  @Log
  private [this] def testPrivate(arg: String): String = {
    println(s"do I expand twice? $arg")
    arg + " : I'm from the private method!"
  }

}


