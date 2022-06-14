package com.zzb.tutorial.webgrabdemo;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/webgrab")
public class TestController {

    final static String USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.181 Safari/537.36";

    @GetMapping(value = "/test1")
    public ResponseEntity<ResponseWrapper> getWebBaseInfo() throws IOException {
        String url = "https://movie.douban.com/top250";     // 静态页面

        String title = "";
        String description = "";
        List<String> listArray = new ArrayList<>();

        Document document = Jsoup.connect(url)
                .userAgent(USER_AGENT)
                .timeout(50000)
                .followRedirects(true)
                .get();

        // title
        title = document.getElementsByTag("title").text();

        // description
        Elements elements = document.select("meta[name=description]");
        if (elements != null && elements.size() > 0) {
            description = elements.get(0).attr("content");
        }

        // list
        Element gridViewElement = document.getElementsByClass("grid_view").get(0);
        Elements itemElements = gridViewElement.getElementsByClass("item");
        for(int j = 0; j < itemElements.size(); j++) {
            Element element1 = itemElements.get(j);

            Element hdElement = element1.getElementsByClass("hd").get(0);
            String titleText = hdElement.getElementsByClass("title").get(0).text();

            listArray.add(titleText);
        }

        Map map = new HashMap();
        map.put("title", title);
        map.put("description", description);
        map.put("requestUrl", url);
        map.put("listArray", listArray);

        return new ResponseEntity<>(new ResponseWrapper(map), HttpStatus.OK);
    }

    @GetMapping(value = "/test2")
    public ResponseEntity<ResponseWrapper> getWebBaseInfo2() {
        // TODO: 配置需要解析的url地址
        String url = "";      // 动态页面

        // TODO: 根据driver实际路径进行配置
        String chromeDriverPath = "/Users/cary/Documents/GS/ChromeDriver/chromedriver";

        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200","--ignore-certificate-errors", "--silent");
        WebDriver driver = new ChromeDriver(options);

        driver.get(url);

        String pageSource = driver.getPageSource();
        Document document = Jsoup.parse(pageSource);
        String title = document.getElementsByClass("title").get(0).text();

        driver.quit();

        Map map = new HashMap();
        map.put("title", title);
        map.put("requestUrl", url);

        return new ResponseEntity<>(new ResponseWrapper(map), HttpStatus.OK);
    }
}
