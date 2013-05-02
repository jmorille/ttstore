package eu.ttbox.batch.core.voter;


public interface DecisionVoter<T> {

    int ACCESS_GRANTED = 1;
    int ACCESS_ABSTAIN = 0;
    int ACCESS_DENIED = -1;
    
    int vote(T subject);
}
