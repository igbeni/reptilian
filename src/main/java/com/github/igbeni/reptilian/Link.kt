package com.github.igbeni.reptilian

/**
 * Represents an anchor on a [WebPage].
 *
 * @author iggor
 * @created 06/24/2020 - 14:06
 * @project reptilian
 */
data class Link(val text: String = "", val href: String? = "") {

    /**
     * Checks whether this link is valid (is not equivalent to its parent, has the right format and points to a page on
     * the same website).
     *
     * @return ture if valid, false otherwise.
     */
    fun valid(parentLoc: String): Boolean {
        if (href != null && !href.startsWith("mailto")) {
            val slashIndex = parentLoc.indexOf('/', 8)
            var domain = parentLoc
            if (slashIndex != -1) {
                domain = parentLoc.substring(0, slashIndex)
            }
            return href.startsWith(domain)
        }
        return false
    }

}
