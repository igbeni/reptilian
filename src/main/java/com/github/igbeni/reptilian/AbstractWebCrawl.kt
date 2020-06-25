package com.github.igbeni.reptilian

import org.openqa.selenium.WebDriver

/**
 * An abstract webcrawl, which contains the webdriver and other common data of each
 * crawl.
 *
 * @created 06/24/2020 - 14:15
 * @project reptilian
 * @author iggor
 */
abstract class AbstractWebCrawl(
        val webDriver: WebDriver,
        val ignoredPatterns: IgnoredPatterns,
        val batchSize: Number
) : WebCrawl
