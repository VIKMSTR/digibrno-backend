package org.hackujbrno.digibrno.servicebackend.control;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hackujbrno.digibrno.servicebackend.cityboard.CityBoardDataRegistry;
import org.hackujbrno.digibrno.servicebackend.cityboard.CityBoardEvent;
import org.hackujbrno.digibrno.servicebackend.cityboard.CityBoardEventCustom;
import org.hackujbrno.digibrno.servicebackend.cityboard.CityBoardEventFullJSON;
import org.hackujbrno.digibrno.servicebackend.common.DataFetching;
import org.hackujbrno.digibrno.servicebackend.events.EventDataRegistry;
import org.hackujbrno.digibrno.servicebackend.events.EventEnvelopeJSON;
import org.hackujbrno.digibrno.servicebackend.events.EventsFullObjectJSON;
import org.hackujbrno.digibrno.servicebackend.news.NewsDataRegistry;
import org.hackujbrno.digibrno.servicebackend.news.SingleNews;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@EnableAsync
@RestController
public class ControlService {

    @Autowired
    EventDataRegistry dataRegistry;
    @Autowired
    CityBoardDataRegistry cityBoardDataRegistry;
    @Autowired
    NewsDataRegistry newsDataRegistry;

    Logger logger = LoggerFactory.getLogger(ControlService.class);

    @GetMapping("/refresh")
    public void refreshData() throws Exception {
        logger.info("Data refresh requested...");
            dataRegistry.events = getEventsFromRemote();
            cityBoardDataRegistry.data = getCityBoardEvents();
            newsDataRegistry.data = getNews();
    }

    @EventListener(classes = ApplicationStartedEvent.class )
    public void listenToStart(ApplicationStartedEvent event) throws Exception {
        logger.info("App starting, fetching new data.");
        dataRegistry.events = getEventsFromRemote();
        cityBoardDataRegistry.data = getCityBoardEvents();
        newsDataRegistry.data = getNews();
        logger.info("Data updated!");
    }

    private EventEnvelopeJSON[] getEventsFromRemote() throws Exception{
        var response = DataFetching.getDataFromRemote("https://services6.arcgis.com/fUWVlHWZNxUvTUh8/arcgis/rest/services/Events/FeatureServer/0/query?where=1%3D1&outFields=*&outSR=4326&f=json");
        EventsFullObjectJSON fullObjectJSON = new ObjectMapper().readValue(response.body(),EventsFullObjectJSON.class);
        return fullObjectJSON.features;
    }

    private List<CityBoardEventCustom> getCityBoardEvents() throws Exception {
        var response = DataFetching.getDataFromRemote("http://edeska.brno.cz/eDeska/opendata");
        CityBoardEventFullJSON fullObjectJSON = new ObjectMapper().readValue(response.body(),CityBoardEventFullJSON.class);
        if (fullObjectJSON == null || fullObjectJSON.getInformace() == null) {
            return new ArrayList<>();
        }
        return Arrays.stream(fullObjectJSON.getInformace()).map(CityBoardEvent::convertToCustomFormat).toList();
    }

    private List<SingleNews> getNews() throws Exception {
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

    @Async
    @Scheduled( fixedDelay = 2*60*60*1000, initialDelay = 2*60*60*1000) //every 2hrs
    public void scheduleFixedRateTask()  {
        logger.info("Scheduled data update started");
        try {
            dataRegistry.events = getEventsFromRemote();
            cityBoardDataRegistry.data = getCityBoardEvents();
            newsDataRegistry.data = getNews();
            logger.info("Data updated!");
        } catch (Exception e) {
            logger.error("Error when fetching data.");
        }
    }


}
