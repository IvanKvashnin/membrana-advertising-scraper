package tech.membrana.advertising.configuration;//package tech.membrana.advertising.configuration;

import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.boot.test.context.TestComponent;

import java.io.File;
import java.net.URL;

import static java.util.Objects.requireNonNull;

@TestComponent
public class ChromeExtensionConfig {
    public ChromeOptions setAddBlock() {
        return setUpChromeExtension(requireNonNull(getClass().getClassLoader().getResource("AdBlock.crx")));
    }

    public ChromeOptions setAddGuard() {
        return setUpChromeExtension(requireNonNull(getClass().getClassLoader().getResource("AdGuard.crx")));
    }

    private static ChromeOptions setUpChromeExtension(URL extensionPath) {
        var options = new ChromeOptions();
        options.setAcceptInsecureCerts(true);
        options.addExtensions(new File(extensionPath.getPath()));
        return options;
    }
}
