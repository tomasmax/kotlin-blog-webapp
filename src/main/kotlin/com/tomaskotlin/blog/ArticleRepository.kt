package com.tomaskotlin.blog

object ArticleRepository {
    fun getArticles(): List<Article> = (1..10).map{
        Article(
                title = "Article $it",
                content = "Content $it",
                type = if (it % 3 == 0) Type.VIDEO else Type.TEXT
        )
    }
//            listOf(
//            Article(title = "Article 1",  content = "Content 1", type = Type.TEXT),
//            Article(title = "Article 2",  content = "Content 2", type = Type.TEXT),
//            Article(title = "Article 3",  content = "Content 3", type = Type.VIDEO),
//            Article(title = "Article 4",  content = "Content 4", type = Type.VIDEO)
//    )

    fun getByFilter(filter: Filter): List<Article> = when (filter) {
        Filter.None -> getArticles()
        is Filter.ByType -> getArticles().filter { it.type == filter.type }
    }

    fun getArticleBySlug(slug: String): Article? {
        return getArticles().firstOrNull { it.slug == slug } // it.slug == slug
    }
}