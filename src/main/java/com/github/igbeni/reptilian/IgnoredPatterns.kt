package com.github.igbeni.reptilian

import java.util.regex.Matcher
import java.util.regex.Pattern
import java.util.regex.PatternSyntaxException

/**
 * @created 06/24/2020 - 14:43
 * @project reptilian
 * @author iggor
 */
class IgnoredPatterns {

    private val patterns = arrayListOf<String>()

    /**
     * Should the given url be ignored or not?
     * @param url Tested url.
     * @return ture if it matches any pattern, false otherwise.
     */
    operator fun contains(url: String): Boolean {
        for (p in patterns) {
            if (p.equals(url, ignoreCase = true)) {
                return true
            }
            if (matchesAsteriskPattern(p.trim { it <= ' ' }, url)) {
                return true
            }
            if (matchesRegex(p.trim { it <= ' ' }, url)) {
                return true
            }
        }
        return false
    }

    /**
     * Test against asterisk pattern (e.g. *.js)
     * @param p pattern.
     * @param url Tested url string.
     * @return True if it matches.
     */
    private fun matchesAsteriskPattern(p: String, url: String): Boolean {
        if (p.contains("*")) {
            val partsIndexes: MutableList<Int> = ArrayList()
            val parts = p.split("\\*").toTypedArray()
            parts.indices.forEach { i ->
                when {
                    url.contains(parts[i]) -> {
                        val indexOfPart = url.indexOf(parts[i])
                        partsIndexes.forEach { partIndex ->
                            if (partIndex > indexOfPart) {
                                return false
                            }
                        }
                        partsIndexes.add(indexOfPart)
                    }
                    else -> {
                        return false
                    }
                }
            }
        } else {
            return false
        }
        return true
    }

    /**
     * Test against regex.
     * @param p pattern.
     * @param url Tested url string.
     * @return True if it matches.
     */
    private fun matchesRegex(p: String, url: String): Boolean {
        return try {
            val pattern: Pattern = Pattern.compile(p)
            val matcher: Matcher = pattern.matcher(url)
            matcher.matches()
        } catch (ex: PatternSyntaxException) {
            false
        }
    }
}
