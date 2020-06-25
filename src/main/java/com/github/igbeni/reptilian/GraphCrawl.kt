package com.github.igbeni.reptilian

import com.github.igbeni.reptilian.exception.DataExportException
import org.openqa.selenium.WebDriver
import java.util.*


/**
 * @created 06/24/2020 - 14:43
 * @project reptilian
 * @author iggor
 */
internal class GraphCrawl(
        val index: Link,
        webDriver: WebDriver,
        ignoredPatterns: IgnoredPatterns,
        batchSize: Number
) : AbstractWebCrawl(webDriver, ignoredPatterns, batchSize) {

    /**
     * Crawl the website.
     * @throws DataExportException If something goes wrong during processing of
     * crawled pages.
     */
    override fun crawl() {
        if (!index.href?.let { ignoredPatterns.contains(it) }!!) {
            val pages: MutableList<WebPage> = ArrayList()
            webDriver.get(index.href)
            val indexSnapshot: WebPage = LiveWebPage(webDriver).snapshot()
            pages.add(indexSnapshot)
            val crawledLinks: MutableSet<Link> = HashSet()
            crawledLinks.add(index)
            val toCrawl: MutableList<Link> = ArrayList()
            toCrawl.addAll(indexSnapshot.links)
            checkBatchSize(pages)
            if (toCrawl.size > 0) {
                var link = toCrawl.removeAt(0)
                while (toCrawl.size > 0) {
                    if (link.href?.let { ignoredPatterns.contains(it) }!!) {
                        link = toCrawl.removeAt(0)
                        continue
                    }
                    val notCrawledAlready = crawledLinks.add(link)
                    if (notCrawledAlready) {
                        webDriver.get(link.href)
                        val snapshotCrawled: WebPage = LiveWebPage(webDriver).snapshot()
                        pages.add(snapshotCrawled)
                        checkBatchSize(pages)
                        toCrawl.addAll(snapshotCrawled.links)
                    }
                    link = toCrawl.removeAt(0)
                }
            }
//            repo().export(pages)
            webDriver.quit()
        }
    }

    /**
     * Check if the batch size has been reached. If yes, export the pages and empty the
     * list for the next batch.
     * @param pages Pages crawled so far.
     * @throws DataExportException If something goes wrong during processing of crawled pages.
     */
    @Throws(DataExportException::class)
    private fun checkBatchSize(pages: MutableList<WebPage>) {
        if (pages.size == batchSize) {
//            this.repo().export(pages)
            pages.clear()
        }
    }

}
