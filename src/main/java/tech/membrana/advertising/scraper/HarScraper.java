package tech.membrana.advertising.scraper;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Service;
import tech.membrana.advertising.configuration.ChromeConfig;
import tech.membrana.advertising.configuration.ProxyConfig;

import java.io.File;
import java.io.IOException;

import static com.codeborne.selenide.Selenide.open;
import static tech.membrana.advertising.files.HarFile.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class HarScraper {
    private final ProxyConfig proxyConfig;
    private final ChromeConfig chromeConfig;

    public File collectHarFile(String url) {
        ChromeOptions options = new ChromeOptions();
        return collectHarFile(originalHarFile(), url, options);
    }

    public File collectHarFileWithAdBlock(String url) {
        return collectHarFile(adBlockHarFile(), url, chromeConfig.setAddBlock());
    }

    public File collectHarFileWithAdGuard(String url) {
        return collectHarFile(adGuardHarFile(), url, chromeConfig.setAddGuard());
    }

    private File collectHarFile(File harFile, String url, ChromeOptions chromeOptions) {
        var proxy = proxyConfig.setupProxy();
        try {
            Configuration.browserCapabilities.merge(chromeOptions);
            proxy.newHar("capture-har");
            open(url);
            proxy.getHar().writeTo(harFile);
            log.info("HAR-файл сохранён: {}", harFile.getAbsolutePath());
        } catch (IOException e) {
            log.warn("Не удалось сохранить HAR-файл: {}", e.getMessage());
        } finally {
            if (proxy != null) {
                proxy.stop();
            }
            Selenide.closeWebDriver();
        }
        return harFile;
    }
}
