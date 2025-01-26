package tech.membrana.advertising;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tech.membrana.advertising.scraper.HarScraper;

@SpringBootApplication
@RequiredArgsConstructor
public class ScraperApp implements CommandLineRunner {
    private final HarScraper harScraper;

    public static void main(String[] args) {
        SpringApplication.run(ScraperApp.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        var url = "https://wwww.yandex.ru";
        harScraper.collectHarFile(url);
        harScraper.collectHarFileWithAdBlock(url);
        harScraper.collectHarFileWithAdGuard(url);
    }
}
