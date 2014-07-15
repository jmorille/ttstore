package eu.ttbox.batch.core.voter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class AffirmativeBasedDecisionManager<T> implements DecisionManager<T> {

	private static Logger log = LoggerFactory.getLogger(AffirmativeBasedDecisionManager.class);

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
		int deny = 0;

		for (DecisionVoter<T> voter : decisionVoters) {
			int result = voter.vote(subject);

			if (log.isDebugEnabled()) {
				log.debug("Voter: {} , returned: {} ", voter, result);
			}

			switch (result) {
			case DecisionVoter.ACCESS_GRANTED:
				return true;

			case DecisionVoter.ACCESS_DENIED:
				deny++;
				break;

			default:
				break;
			}
		}

		if (deny > 0) {
			return false;
		}
		return allowIfAllAbstainDecisions;
	}

}
