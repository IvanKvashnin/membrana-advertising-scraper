package tech.membrana.advertising.configuration;

import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.net.URL;

import static java.util.Objects.requireNonNull;

@Configuration
public class ChromeExtensionConfig {
    public ChromeOptions setAddBlock() {
        return setUpChromeExtension(requireNonNull(getClass().getClassLoader().getResource("AdBlock.crx")));
    }

    public ChromeOptions setAddGuard() {
        return setUpChromeExtension(requireNonNull(getClass().getClassLoader().getResource("AdGuard.crx")));
    }

    private static ChromeOptions setUpChromeExtension(URL extensionPath) {
        var options = new ChromeOptions();
        options.addExtensions(new File(extensionPath.getPath()));
        return options;
    }
}
