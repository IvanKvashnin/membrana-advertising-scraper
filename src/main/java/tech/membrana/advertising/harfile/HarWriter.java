package tech.membrana.advertising.harfile;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import net.lightbody.bmp.BrowserMobProxy;
import org.springframework.stereotype.Service;

import java.io.File;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

@Slf4j
@Service
public class HarWriter {

    @SneakyThrows
    public File write(String url, File harFileName, BrowserMobProxy proxy) {
        proxy.newHar("capture-har");
        open(url);
        proxy.getHar().writeTo(harFileName);
        log.info("HAR-файл сохранён: {}", harFileName.getAbsolutePath());
        closeWebDriver();
        proxy.stop();
        return harFileName;
    }
}
