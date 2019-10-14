package com.tomaskotlin.blog

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BlogApplication

fun main(args: Array<String>) {
	//runApplication<BlogApplication>(*args)
	runApplication2<BlogApplication>(*args)
	val name = args[0]
	printMessage("Hello $name")
}

private fun printMessage(message: String){
	message.println()
}

inline fun <reified T> runApplication2(vararg args: String) {
	SpringApplication.run(T::class.java, *args)
}
