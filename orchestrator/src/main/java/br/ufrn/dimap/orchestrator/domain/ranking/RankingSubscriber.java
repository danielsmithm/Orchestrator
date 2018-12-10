package br.ufrn.dimap.orchestrator.domain.ranking;

import br.ufrn.dimap.orchestrator.domain.ranking.Ranking;
import br.ufrn.dimap.orchestrator.domain.token.TokenValidatedEvent;

public class RankingSubscriber {

    private String sessionId;

    public RankingSubscriber(String sessionId) {
        this.sessionId = sessionId;
    }

    public boolean isInterstedInTopic(TokenValidatedEvent tokenValidatedEvent){


        return true;
    }

    public String getSessionId() {
        return sessionId;
    }
}
