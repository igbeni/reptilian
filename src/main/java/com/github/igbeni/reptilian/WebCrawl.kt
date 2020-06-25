package com.github.igbeni.reptilian

import com.github.igbeni.reptilian.exception.DataExportException

/**
 * One web crawl.
 * Represents the entry point to the crawling logic.
 *
 * @created 06/24/2020 - 14:15
 * @project reptilian
 * @author iggor
 */
interface WebCrawl {

    /**
     * Crawl the website.
     * @throws DataExportException If something goes wrong during processing of
     * crawled pages.
     */
    @Throws(DataExportException::class)
    fun crawl()
}
