import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;

public class MarketPlaceAppMetric {

    enum Region {

        all("None", "所有"), cn("cn", "中国"), uy("uy", "乌拉圭"), in("in", "印度"), gt("gt", "危地马拉"), ec(
                "ec", "厄瓜多尔"), co("co", "哥伦比亚"), cr("cr", "哥斯达黎加"), rs("rs",
                "塞尔维亚"), mx("mx", "墨西哥"), ve("ve", "委内瑞拉"), bd("bd", "孟加拉国"), ni(
                "ni", "尼加拉瓜"), pa("pa", "巴拿马"), br("mx", "巴西"), gr("gr", "希腊"), de(
                "de", "德国"), it("it", "意大利"), cz("cz", "捷克共和国"), jp("jp", "日本"), cl(
                "cl", "智利"), fr("fr", "法国"), pl("pl", "波兰"), pe("pe", "秘鲁"), us(
                "us", "美国"), uk("uk", "英国"), sv("sv", "萨尔瓦多"), es("es", "西班牙"), ar(
                "ar", "阿根廷"), me("me", "黑山"), hu("hu", "匈牙利"), restofworld(
                "restofworld", "其他地区");

        private Region(String slug, String name) {
            this.slug = slug;
            this.name = name;
        }

        private String slug;
        private String name;

    }

    public static void main(String[] args) {
        HttpClient client = new DefaultHttpClient();
        String baseUrl = "https://marketplace.firefox.com/api/v2/apps/search?limit=1";
        System.out.println("国家,Privileged App,Packaged App, Hosted app, 免费,Free-Inapp,premium,premium-inapp,other");

        for (Region region : Region.values()) {
            baseUrl = baseUrl + "&region=" + region.slug;
            
            
            System.out.print(region.name+",");

            String privilegedURL=baseUrl+"&app_type=privileged";
            int total=getTotalFrom(privilegedURL,client);
            System.out.print(total+",");
            String packagedURL=baseUrl+"&app_type=packaged";
            total=getTotalFrom(packagedURL,client);
            System.out.print(total+",");
            String hostedApp=baseUrl+"&app_type=hosted";
            total=getTotalFrom(hostedApp,client);
            System.out.print(total+",");      
            
            String preMiumURL=baseUrl+"&premium_types=free";
            total=getTotalFrom(preMiumURL,client);
            System.out.print(total+",");
            String freeInAppURL=baseUrl+"&premium_types=free-inapp";
            total=getTotalFrom(freeInAppURL,client);
            System.out.print(total+",");
            String premiumURL=baseUrl+"&premium_types=premium";
            total=getTotalFrom(premiumURL,client);
            System.out.print(total+",");    
            String premiumInAppURL=baseUrl+"&premium_types=premium-inapp";
            total=getTotalFrom(premiumInAppURL,client);
            System.out.print(total+",");   
            String otherURL=baseUrl+"&premium_types=other";
            total=getTotalFrom(otherURL,client);
            System.out.print(total);   
            System.out.println();
        }
    }

    public static int getTotalFrom(String url, HttpClient client) {
        try {
            HttpGet getMethod = new HttpGet(url);
            HttpResponse response = client.execute(getMethod);

            String jsonStr = EntityUtils.toString(response.getEntity());
            EntityUtils.consume(response.getEntity());

            int total = JSON
                    .parseObject(jsonStr, MarketPlaceSearchResult.class)
                    .getMeta().getTotalCount();
            return total;

        } catch (Exception e) {
            return -1;
        }
    }
}
