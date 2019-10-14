package com.tomaskotlin.blog

import com.sun.jdi.request.InvalidRequestStateException
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import java.lang.RuntimeException

@Controller
class HtmlController {

    @GetMapping("/")
    fun blog(@RequestParam(value = "filter") selectedFilter: String?, model: Model): String {
        val filters = listOf("ALL") + Type.values().map { it.name }

        model["title"] = "Blog"
        model["articles"] = ArticleRepository.getByFilter(createFilter(selectedFilter)).map() {it.render { article -> article.title }}
        model["filters"] = filters.map { RenderedFilter(it) }
        return "blog"
    }

    private fun createFilter(selectedFilter: String?): Filter {
        val res = Type.values().firstOrNull() { it.name == selectedFilter }
        return res?.let { Filter.ByType(it) } ?: Filter.None
    }

    @GetMapping("/article/{slug}")
    fun article(@PathVariable slug: String, model: Model): String {

        ArticleRepository.getArticleBySlug(slug)?.let {
            model["title"] = it.title
            model["article"] = it.render { renderTitle(it) }
        } ?: throw RuntimeException("No article found")

        return "article"
    }

    @PostMapping("filter")
    fun filter(@ModelAttribute filter: RenderedFilter): String {
        return "redirect:/?filter=${filter.value}"
    }

    fun renderTitle(article: Article): String = when (article.type) {
        Type.TEXT -> article.title
        Type.VIDEO -> "${article.title} (Video)"
    }

    class RenderedArticle(val title: String, val content:String, val slug:String)
}
