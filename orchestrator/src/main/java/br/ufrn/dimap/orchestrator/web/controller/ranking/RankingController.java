package br.ufrn.dimap.orchestrator.web.controller.ranking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.google.type.Date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
    public SseEmitter registerRealtimeUpdate(
    		HttpSession httpSession,
    		@RequestParam(value="since", required=false) @DateTimeFormat(iso=DateTimeFormat.ISO.DATE_TIME) LocalDateTime since){
        
    	String sessionId = httpSession.getId();
        
        if (since != null) {
        	// use this date for report gen
        }

        return rankingSubscriberManager.registerSubscriber(sessionId, since);
    }

}
