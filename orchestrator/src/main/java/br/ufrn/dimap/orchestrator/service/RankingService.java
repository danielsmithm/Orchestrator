package br.ufrn.dimap.orchestrator.service;

import br.ufrn.dimap.orchestrator.domain.ranking.Ranking;
import br.ufrn.dimap.orchestrator.domain.token.TokenValidatedEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Date;

@Service
public class RankingService {

    public Ranking generateRanking(){
        Ranking ranking = new Ranking();

        ranking.setData("Testando");

        return ranking;
    }


}
