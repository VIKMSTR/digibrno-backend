package org.hackujbrno.digibrno.servicebackend.news;

import org.hackujbrno.digibrno.servicebackend.common.DataFetching;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class NewsProviderService {

    @GetMapping("/news")
    public List<SingleNews> getNews() throws Exception {
        String rootUrl = "https://www.brno.cz/";
        //List<String> titles = new ArrayList<String>();
        List<SingleNews> all_news = new ArrayList<>();
      //var page = DataFetching.getDataFromRemote("https://www.brno.cz/brno-aktualne/co-se-deje-v-brne/");
        Document doc = Jsoup.connect("https://www.brno.cz/brno-aktualne/co-se-deje-v-brne/").get();
        for (Element ele: doc.select(".zprava")) {
            var linkElee = ele.getElementsByTag("a").get(0);
            var subUrl = rootUrl + linkElee.attr("href");
            var title = linkElee.attr("title");
            var picDiv = ele.getElementsByClass("obrazek").get(0);
            var pic = rootUrl + picDiv.getElementsByTag("a").get(1).attr("href");
            var datum = ele.getElementsByClass("datum").get(0).text().replace("&nbsp"," ");
            var news = new SingleNews(title,subUrl,pic,datum);
            //titles.add(title);
            all_news.add(news);
        }
        return all_news;
    }
}
