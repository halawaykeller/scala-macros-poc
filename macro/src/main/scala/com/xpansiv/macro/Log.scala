package com.xpansiv.`macro`

import scala.annotation.StaticAnnotation
import scala.language.experimental.macros
import scala.reflect.macros.blackbox
import scala.reflect.runtime.universe

class Log extends StaticAnnotation {
  def macroTransform(annottees: Any*): Any = macro Logger.impl
}

object Logger {

  def impl(c: blackbox.Context)(annottees: c.Expr[Any]*): c.Expr[Any] = {
    import c.universe._

//    val cz = c.internal.enclosingOwner.asClass
//    val m = universe.runtimeMirror()

    val result = {
      annottees.map(_.tree).toList match {

        case q"$mods def $methodName[..$tpes](...$args): $returnType = { ..$body }" :: Nil => {
          val clazz = c.internal.enclosingOwner.asType.name.toString

          q"""
             $mods def $methodName[..$tpes](...$args): $returnType = {
              val t0 = System.nanoTime()
              println("----> " + $clazz + ": " + ${methodName.toString} + " " + ${args.toString})
              val result = {..$body}
              println($body)
              val t1 = System.nanoTime()
              println("<---- " + $clazz + ": " + ${methodName.toString} + " elapsed time: " + (t1 - t0).toDouble / 1000000D + "ms")
              result
            }"""
        }
        case _ => c.abort(c.enclosingPosition, "Annotation @com.xpansiv.macro.Log can be used only with methods")
      }
    }
    println(showRaw(result))
    println(c.internal.enclosingOwner.asClass)
    c.Expr[Any](result)
  }
}


