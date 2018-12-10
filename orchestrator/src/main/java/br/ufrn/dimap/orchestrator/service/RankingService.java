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
    	
    	int c = (int) Math.round(Math.random() * 3);
    	
    	Ranking ranking = null;
    	
    	if (c % 3 == 0) { 
    		ranking = new Ranking();
	        ranking.addRankedApplication(1,"app1","app1", "Orchestradeiro1",5.0,1,1,2);
	        ranking.addRankedApplication(2,"app2","app2", "Orchestradeiro2",3.0,1,1,0);
	        ranking.addRankedApplication(3,"app3","app3", "Orchestradeiro3",3.0,2,1,0);
    	}
    	
    	if (c % 3 == 1) { 
    		ranking = new Ranking();
	        ranking.addRankedApplication(1,"app2","app2", "Orchestradeiro2",5.0,1,1,2);
	        ranking.addRankedApplication(2,"app1","app1", "Orchestradeiro1",3.0,1,1,0);
	        ranking.addRankedApplication(3,"app3","app3", "Orchestradeiro3",3.0,2,1,0);
    	}
    	
    	if (c % 3 == 2) { 
    		ranking = new Ranking();
	        ranking.addRankedApplication(1,"app3","app3", "Orchestradeiro3",5.0,1,1,2);
	        ranking.addRankedApplication(2,"app1","app1", "Orchestradeiro1",3.0,1,1,0);
	        ranking.addRankedApplication(3,"app2","app2", "Orchestradeiro2",3.0,2,1,0);
    	}
    	
        return ranking;
    }

}
