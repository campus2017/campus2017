package com.qunar.wireless.mlx;

import com.machinepublishers.jbrowserdriver.JBrowserDriver;
import com.machinepublishers.jbrowserdriver.Settings;
import com.machinepublishers.jbrowserdriver.Timezone;

/**
 * Created by mlx on 2016-12-23.
 */
public class CrawlerJdb implements ICrawler {

    public String getHtml(String url){
        // This will block for the page load and any
        // associated AJAX requests
        driver.get(url);
        // Returns the page source in its current state, including
        // any DOM updates that occurred after page load
        String html = driver.getPageSource();
        return html;
    }

    public void close() {
        // Close the browser. Allows this thread to terminate.
        driver.quit();
    }

    public int getStatus() {
        // You can get status code unlike other Selenium drivers.
        // It blocks for AJAX requests and page loads after clicks
        // and keyboard events.
        return driver.getStatusCode();
    }

    public CrawlerJdb(){
        // You can optionally pass a Settings object here,
        // constructed using Settings.Builder
        driver = new JBrowserDriver(Settings.builder().
                timezone(Timezone.AMERICA_NEWYORK).build());
    }

    private JBrowserDriver driver;
}