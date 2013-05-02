package eu.ttbox.batch.core.voter;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UnanimousBasedBasedDecisionManager<T> implements
		DecisionManager<T> {

	private static Logger log = LoggerFactory
			.getLogger(UnanimousBasedBasedDecisionManager.class);

	List<DecisionVoter<T>> decisionVoters;

	private boolean allowIfAllAbstainDecisions = false;

 
	public void setDecisionVoters(List<DecisionVoter<T>> voters) {
		this.decisionVoters = voters;
	}

	public void setAllowIfAllAbstainDecisions(boolean allowIfAllAbstainDecisions) {
		this.allowIfAllAbstainDecisions = allowIfAllAbstainDecisions;
	}

 
	@Override
	public boolean decide(T subject) {
		int grant = 0;
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
				return false;

			default:
				abstain++;

				break;
			}
		}

		// To get this far, there were no deny votes
		if (grant > 0) {
			return true;
		}

		// To get this far, every AccessDecisionVoter abstained
		return allowIfAllAbstainDecisions;
	}

}
