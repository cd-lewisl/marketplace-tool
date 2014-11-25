import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;

public class AppList {
    static String baseUrl = "https://marketplace.firefox.com";

    // static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new
    // FileOutputStream("low-memory-applist.txt")));
    public static void main(String[] args) {
        printAppName(baseUrl
                + "/api/v2/apps/search/?q=tarako&region=None&sort=downloads");
    }

    public static void printAppName(String url) {
        try {
            HttpClient client = new DefaultHttpClient();
            HttpGet getMethod = new HttpGet(url);
            HttpResponse response = client.execute(getMethod);

            String jsonStr = EntityUtils.toString(response.getEntity());
            EntityUtils.consume(response.getEntity());
            MarketPlaceSearchResult result = JSON.parseObject(jsonStr,
                    MarketPlaceSearchResult.class);
            List<MarketPlaceSearchResult.App> apps = result.getObjects();
            for (MarketPlaceSearchResult.App app : apps) {
                System.out.println(app.getName().get(app.getDefaultLocale()));
            }
            if (result.getMeta().getNext() == null) {
                return;
            }
            printAppName(baseUrl + result.getMeta().getNext());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
