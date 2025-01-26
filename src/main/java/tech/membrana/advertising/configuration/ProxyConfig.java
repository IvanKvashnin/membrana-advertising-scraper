package tech.membrana.advertising.configuration;

import com.codeborne.selenide.Configuration;
import net.lightbody.bmp.BrowserMobProxy;
import net.lightbody.bmp.BrowserMobProxyServer;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.remote.DesiredCapabilities;

import static org.openqa.selenium.remote.CapabilityType.PROXY;

@org.springframework.context.annotation.Configuration
public class ProxyConfig {

    public BrowserMobProxy setupProxy() {
        var proxy = new BrowserMobProxyServer();
        proxy.start(0);

        Proxy seleniumProxy = new Proxy();
        var proxyStr = "localhost:" + proxy.getPort();
        seleniumProxy.setHttpProxy(proxyStr);
        seleniumProxy.setSslProxy(proxyStr);

        Configuration.browserCapabilities = new DesiredCapabilities();
        Configuration.browserCapabilities.setCapability(PROXY, seleniumProxy);

        return proxy;
    }
}
