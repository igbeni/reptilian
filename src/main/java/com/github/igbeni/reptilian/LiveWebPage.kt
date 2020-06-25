package com.github.igbeni.reptilian

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import java.util.regex.Pattern

/**
 * @created 06/24/2020 - 14:43
 * @project reptilian
 * @author iggor
 */
class LiveWebPage(val webDriver: WebDriver) : LivePage {
    override fun snapshot(): WebPage {
        TODO("Not yet implemented")
    }

    /**
     * Set the name of this webpage.
     * @param name Given name.
     */
    override var name: String?
        get() = name()
        set(value) {}

    private fun name(): String {
        val pattern = Pattern.compile("(.+[^\\/])\\/([^\\/].*[^\\/])\\/{0,1}$")
        val matcher = pattern.matcher(url)
        return if (matcher.find()) {
            matcher.group(2)
        } else {
            "index"
        }
    }

    /**
     * Set url.
     * @param url to be set.
     */
    override var url: String?
        get() = webDriver.currentUrl
        set(value) {}

    /**
     * Set the page title.
     * @param title to be set on this web page.
     */
    override var title: String?
        get() = webDriver.title
        set(value) {}

    /**
     * Set the text content.
     * @param textContent Content to be set
     */
    override var textContent: String?
        get() = webDriver.findElement(By.tagName("body")).text
        set(value) {}

    /**
     * Set the anchors on a page.
     * @param links Set of links to be set
     */
    override var links: Collection<Link>
        get() = links()
        set(value) {}

    private fun links(): Set<Link> {
        val links: HashSet<Link> = hashSetOf()

        for (element in webDriver.findElements(By.tagName("a"))) {
            val link = Link(element.text, element.getAttribute("href"))
            if (url?.let { link.valid(it) }!!) {
                links.add(link)
            }
        }

        return links
    }
}
