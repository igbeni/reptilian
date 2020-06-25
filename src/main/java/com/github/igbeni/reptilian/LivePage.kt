package com.github.igbeni.reptilian

/**
 * Interface of a live page.
 *
 * @created 06/24/2020 - 14:43
 * @project reptilian
 * @author iggor
 */
interface LivePage : WebPage {
    fun snapshot(): WebPage
}
