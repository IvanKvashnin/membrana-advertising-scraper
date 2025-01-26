package tech.membrana.advertising.configuration;

import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.context.annotation.Configuration;

import java.net.URL;

import static java.util.Objects.requireNonNull;

@Configuration
public class ChromeConfig {
    public ChromeOptions setAddBlock() {
        return setUpChromeExtension(requireNonNull(getClass().getClassLoader().getResource("adblock")));
    }

    public ChromeOptions setAddGuard() {
        return setUpChromeExtension(requireNonNull(getClass().getClassLoader().getResource("adguard")));
    }

    private static ChromeOptions setUpChromeExtension(URL extensionPath) {
        var options = new ChromeOptions();
        options.addArguments("--load-extension=" + extensionPath.getPath());
        return options;
    }
}
