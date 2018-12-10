package br.ufrn.dimap.orchestrator.web.controller.ranking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/ranking")
public class RankingController {

    private final RankingSubscriberManager rankingSubscriberManager;

    @Autowired
    public RankingController(RankingSubscriberManager rankingSubscriberManager) {
        this.rankingSubscriberManager = rankingSubscriberManager;
    }


    @RequestMapping("/index")
    public String index(){
        return "ranking/index";
    }

    @RequestMapping("/realTime")
    public SseEmitter registerRealtimeUpdate(HttpSession httpSession){
        String sessionId = httpSession.getId();
        
        return rankingSubscriberManager.registerSubscriber(sessionId);
    }

}
