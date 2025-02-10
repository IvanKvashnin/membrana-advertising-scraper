package tech.membrana.advertising.configuration;//package tech.membrana.advertising.configuration;

import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.boot.test.context.TestComponent;

import static com.codeborne.selenide.Configuration.browserCapabilities;
import static org.openqa.selenium.remote.CapabilityType.PROXY;


@TestComponent
public class ProxyConfig {

    public BrowserMobProxy setUpProxy() {
        var proxy = new BrowserMobProxyServer();
        proxy.start(0);

        var seleniumProxy = new Proxy();
        var proxyHost = "localhost:" + proxy.getPort();
        seleniumProxy.setHttpProxy(proxyHost);
        seleniumProxy.setSslProxy(proxyHost);

        browserCapabilities = new DesiredCapabilities();
        browserCapabilities.setCapability(PROXY, seleniumProxy);

        return proxy;
    }

    public BrowserMobProxy setupProxyWithExtension(ChromeOptions chromeExtension) {
        var proxy = setUpProxy();
        browserCapabilities.merge(chromeExtension);
        return proxy;
    }
}
