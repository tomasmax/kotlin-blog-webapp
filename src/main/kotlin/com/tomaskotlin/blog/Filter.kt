package com.tomaskotlin.blog

sealed class Filter {
    object None : Filter()
    class ByType(val type: Type) : Filter()
}

class RenderedFilter(val value: String)