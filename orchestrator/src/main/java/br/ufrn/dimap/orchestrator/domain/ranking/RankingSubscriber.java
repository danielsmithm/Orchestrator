package br.ufrn.dimap.orchestrator.domain.ranking;

import br.ufrn.dimap.orchestrator.domain.ranking.Ranking;
import br.ufrn.dimap.orchestrator.domain.token.TokenValidatedEvent;

import java.util.Date;

public class RankingSubscriber {

    private String sessionId;
    private Date initialDate;

    public RankingSubscriber(String sessionId, Date initialDate) {
        this.sessionId = sessionId;
        this.initialDate = initialDate;
    }

    public boolean isInterstedInTopic(TokenValidatedEvent tokenValidatedEvent){

    	if(initialDate != null){
			Date validationDate = tokenValidatedEvent.getValidationDate();

			if(validationDate != null){
				return validationDate.after(initialDate);
			}

		}else{
    		return true;
		}

        return false;
    }

    public String getSessionId() {
        return sessionId;
    }

	public Date getInitialDate() {
		return initialDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sessionId == null) ? 0 : sessionId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RankingSubscriber other = (RankingSubscriber) obj;
		if (sessionId == null) {
			if (other.sessionId != null)
				return false;
		} else if (!sessionId.equals(other.sessionId))
			return false;
		return true;
	}
    
    
}
