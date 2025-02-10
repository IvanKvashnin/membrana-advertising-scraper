package tech.membrana.advertising;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import tech.membrana.advertising.configuration.ChromeExtensionConfig;
import tech.membrana.advertising.scraper.HarScraper;

@Slf4j
@SpringBootTest
@Import({HarScraper.class, ChromeExtensionConfig.class})
public class RBCTest {
    @Autowired
    private HarScraper harScraper;


    @Test
    void rbcTest() {
        var url = "https://www.rbc.ru";

        harScraper.scrapeOriginalHarFile(url);
        harScraper.scrapeHarWithAdBlock(url);
        harScraper.scrapeHarWithAdGuard(url);
    }
}
