package org.hackujbrno.digibrno.servicebackend.news;

import org.hackujbrno.digibrno.servicebackend.common.DataFetching;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class NewsProviderService {

    @Autowired
    NewsDataRegistry dataRegistry;

    @GetMapping("/news")
    public List<SingleNews> getNews() throws Exception {
        return dataRegistry.data;
    }
}
