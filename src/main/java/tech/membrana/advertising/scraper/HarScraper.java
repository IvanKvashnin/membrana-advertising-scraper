package tech.membrana.advertising.scraper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tech.membrana.advertising.configuration.ChromeExtensionConfig;
import tech.membrana.advertising.configuration.ProxyConfig;
import tech.membrana.advertising.harfile.HarWriter;

import java.io.File;

import static tech.membrana.advertising.harfile.HarFile.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class HarScraper {
    private final HarWriter harWriter;
    private final ProxyConfig proxyConfig;
    private final ChromeExtensionConfig chromeExtensionConfig;

    public File scrapeOriginalHarFile(String url) {
        var proxy = proxyConfig.setUpProxy();
        return harWriter.write(url, originalHarFile(), proxy);
    }

    public File scrapeHarWithAdBlock(String url) {
        var adBlock = chromeExtensionConfig.setAddBlock();
        var proxy = proxyConfig.setupProxyWithExtension(adBlock);
        return harWriter.write(url, adBlockHarFile(), proxy);
    }

    public File scrapeHarWithAdGuard(String url) {
        var adGuard = chromeExtensionConfig.setAddGuard();
        var proxy = proxyConfig.setupProxyWithExtension(adGuard);
        return harWriter.write(url, adGuardHarFile(), proxy);
    }
}
