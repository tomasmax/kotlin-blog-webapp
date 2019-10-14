package com.tomaskotlin.blog

fun myFun() {
    val x: Int = 7
    val y: Long = x.toLong()

    val a = 7
    val b = 7L
    val c = 7F
    val d = 20.0
    val e = "$a $b $c $d"
}

abstract class Prueba {

}

open class Person(name: String, age:Int) {
    var name: String = ""
    var age: Int = 0
}

class Developer : Person {
    constructor(name:String): super(name, 20)
    constructor(age:Int): super("paco", age)
}

fun Person.apply2(body: Person.() -> Unit): Person {
    this.body()
    return this
}

fun test() {
    val dev = Developer(20)
    val dev2 = Developer("zote")

    val name = dev.name

//    val p: Person = Person().apply2{
//        name = "Tom"
//        age = 20
//    }
}

fun doOp(x: Int, y: Int, op: (Int, Int) -> Int) = op(x,y)

fun test2(a: Int, b: Int) {
    val sum = {x: Int, y: Int -> x+y}
    doOp(a, b, sum)

    val mul = {x: Int, y: Int -> x*y}
    doOp(a, b, mul)

    doOp(a, b) { x, y -> x-y}

    val plus1 :(Int) -> Int = {it+1}
    doOp(a, b) { x, _ -> x+1}
}