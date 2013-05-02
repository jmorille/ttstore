package eu.ttbox.batch.core.voter;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConsensusBasedDecisionManager<T> implements DecisionManager<T> {

	private static Logger log = LoggerFactory.getLogger(ConsensusBasedDecisionManager.class);

	List<DecisionVoter<T>> decisionVoters;

	private boolean allowIfAllAbstainDecisions = false;

	private boolean allowIfEqualGrantedDeniedDecisions = true;
	
	public void setDecisionVoters(List<DecisionVoter<T>> voters) {
		this.decisionVoters = voters;
	}

	public void setAllowIfAllAbstainDecisions(boolean allowIfAllAbstainDecisions) {
		this.allowIfAllAbstainDecisions = allowIfAllAbstainDecisions;
	}

	 
	public void setAllowIfEqualGrantedDeniedDecisions(boolean allowIfEqualGrantedDeniedDecisions) {
		this.allowIfEqualGrantedDeniedDecisions = allowIfEqualGrantedDeniedDecisions;
	}

	@Override
	public boolean decide(T subject) {
		int grant = 0;
        int deny = 0;
        int abstain = 0;

        for (DecisionVoter<T> voter : decisionVoters) {
            int result = voter.vote(subject);

            if (log.isDebugEnabled()) {
				log.debug("Voter: {} , returned: {} ", voter, result);
			}

            switch (result) {
            case DecisionVoter.ACCESS_GRANTED:
                grant++;

                break;

            case DecisionVoter.ACCESS_DENIED:
                deny++;

                break;

            default:
                abstain++;

                break;
            }
        }

        if (grant > deny) {
            return true;
        }

        if (deny > grant) {
           return false;
        }

        if ((grant == deny) && (grant != 0)) {
            if (this.allowIfEqualGrantedDeniedDecisions) {
                return true;
            } else {
               return false;
            }
        }

        // To get this far, every AccessDecisionVoter abstained
        
		return allowIfAllAbstainDecisions;
	}

	
 
}
