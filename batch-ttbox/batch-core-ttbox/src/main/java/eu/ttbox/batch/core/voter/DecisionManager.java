package eu.ttbox.batch.core.voter;

public interface DecisionManager<T> {

	boolean decide(T subject);

}