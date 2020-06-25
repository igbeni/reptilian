package com.github.igbeni.reptilian

/**
 * Interface for a web page.
 *
 * @author iggor
 * @created 06/24/2020 - 14:04
 * @project reptilian
 */
interface WebPage {
    /**
     * Get the filename of this webpage. E.g. index.html
     * @return String filename.
     */
    /**
     * Set the name of this webpage.
     * @param name Given name.
     */
    var name: String?

    /**
     * Page's url.
     * @return String url. e.g. http://charles.amihaiemil.com/index.html
     */
    /**
     * Set url.
     * @param url to be set.
     */
    var url: String?

    /**
     * Get the title of the page.
     * @return String title
     */
    /**
     * Set the page title.
     * @param title to be set on this web page.
     */
    var title: String?

    /**
     * Get all the text content of the page.
     * @return String text content of the page
     */
    /**
     * Set the text content.
     * @param textContent Content to be set
     */
    var textContent: String?

    /**
     * Fetch all the anchors (links) from the page.
     * @return Set of links
     */
    /**
     * Set the anchors on a page.
     * @param links Set of links to be set
     */
    var links: Collection<Link>

}
