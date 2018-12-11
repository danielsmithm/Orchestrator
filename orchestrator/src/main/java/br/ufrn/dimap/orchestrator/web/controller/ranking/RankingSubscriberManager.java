package br.ufrn.dimap.orchestrator.web.controller.ranking;

import br.ufrn.dimap.orchestrator.domain.ranking.RankingSubscriber;
import br.ufrn.dimap.orchestrator.domain.token.TokenValidatedEvent;
import br.ufrn.dimap.orchestrator.service.RankingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

@Component
public class RankingSubscriberManager {

    private final EmitterStore emitterStore;

    private final RankingService rankingService;

    private Set<RankingSubscriber> rankingSubscribers;

    @Autowired
    public RankingSubscriberManager(EmitterStore emitterStore, RankingService rankingService) {
        this.emitterStore = emitterStore;
        this.rankingService = rankingService;
        this.rankingSubscribers = ConcurrentHashMap.newKeySet();
    }

    @EventListener(TokenValidatedEvent.class)
    public void informInterestedSubscribers(TokenValidatedEvent tokenValidatedEvent){
        List<RankingSubscriber> subscribersToNotify = rankingSubscribers.parallelStream()
                .filter(rankingSubscriber -> rankingSubscriber.isInterstedInTopic(tokenValidatedEvent))
                .collect(Collectors.toList());

        subscribersToNotify.forEach(rankingSubscriber -> notifySubscriber(rankingSubscriber));
    }

    private void notifySubscriber(RankingSubscriber rankingSubscriber) {

        try {
            emitterStore.notifyEmmiter(rankingService.generateRanking(rankingSubscriber.getInitialDate()),rankingSubscriber.getSessionId());
        } catch (EmmiterNotFoundException e) {
            rankingSubscribers.remove(rankingSubscriber);
        }

    }

    public SseEmitter registerSubscriber(String sessionId, LocalDateTime sinceDateTime){
        Date initialDate = Date.from(sinceDateTime.atZone(ZoneId.systemDefault()).toInstant());

        RankingSubscriber rankingSubscriber = new RankingSubscriber(sessionId,initialDate);

        SseEmitter emmiter = emitterStore.createEmitterForSessionId(sessionId);

        if (!rankingSubscribers.add(rankingSubscriber)) {
        	rankingSubscribers.remove(rankingSubscriber);
        	rankingSubscribers.add(rankingSubscriber);
        }
        
        return emmiter;

    }

}
