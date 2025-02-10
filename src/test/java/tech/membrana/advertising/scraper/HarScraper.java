package tech.membrana.advertising.scraper;//package tech.membrana.advertising.scraper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Import;
import tech.membrana.advertising.configuration.ChromeExtensionConfig;
import tech.membrana.advertising.configuration.ProxyConfig;
import tech.membrana.advertising.harfile.HarWriter;

import java.io.File;

import static tech.membrana.advertising.harfile.HarFile.*;


@Slf4j
@TestConfiguration
@RequiredArgsConstructor
@Import({HarWriter.class, ProxyConfig.class, ChromeExtensionConfig.class})
public class HarScraper {
    private final HarWriter harWriter;
    private final ProxyConfig proxyConfig;
    private final ChromeExtensionConfig chromeExtensionConfig;

    public File scrapeOriginalHarFile(String url) {
        var proxy = proxyConfig.setUpProxy();
        return harWriter.write(url, originalFile(), proxy);
    }

    public File scrapeHarWithAdBlock(String url) {
        var adBlock = chromeExtensionConfig.setAddBlock();
        var proxy = proxyConfig.setupProxyWithExtension(adBlock);
        return harWriter.write(url, adBlockFile(), proxy);
    }

    public File scrapeHarWithAdGuard(String url) {
        var adGuard = chromeExtensionConfig.setAddGuard();
        var proxy = proxyConfig.setupProxyWithExtension(adGuard);
        return harWriter.write(url, adGuardFile(), proxy);
    }
}
