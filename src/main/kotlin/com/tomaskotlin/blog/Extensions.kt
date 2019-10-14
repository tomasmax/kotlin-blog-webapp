package com.tomaskotlin.blog

import org.springframework.ui.Model

fun String.println() {
    println(this)
}

fun String.toSlug(): String = toLowerCase().replace(' ', '-')

operator fun Model.set(name:String, value: Any) = addAttribute(name, value)


fun Article.render(titleRenderer:(Article)-> String): HtmlController.RenderedArticle {
    return HtmlController.RenderedArticle(titleRenderer(this), content, slug)
}