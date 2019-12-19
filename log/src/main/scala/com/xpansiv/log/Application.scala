package com.xpansiv.log

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan(basePackages = Array("com.example"))
class Application {

  val test = new Test
  val testList1: List[String] = List("hello", "world")
  val testList2: List[String] =
    List("Renly", "Nymeria", "Burtie", "Pocket", "Rue")

  val res1 = test.testMethod(testList1)
  val res2 = test.testMethod(testList2)

//  println(res1)
//  println(res2)
}

object Application extends App {
  SpringApplication.run(classOf[Application])
}
