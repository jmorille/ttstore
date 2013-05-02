package eu.ttbox.service.search.elasticsearch;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.node.NodeBuilder;
import org.springframework.beans.factory.FactoryBean;

public class ElasticClientFactory  implements FactoryBean<Client> {

	private Settings settings;

	private Client client;

	private List<InetSocketTransportAddress> transportAdresses;
 
	
	public Client getClient() {
		return client;
	}

	public void setSettings(Settings settings) {
		this.settings = settings;
	}

	
	
	public Settings getSettings() {
		return settings;
	}

	public List<InetSocketTransportAddress> getTransportAdresses() {
		return transportAdresses;
	}

	public void setTransportAdresses(
			List<InetSocketTransportAddress> transportAdresses) {
		this.transportAdresses = transportAdresses;
	}

	@PostConstruct
	public void afterPropertiesSet() throws Exception {
		if (transportAdresses!=null) {
			client = createTransportClient();
		} else {
			client = createNodeClient();
		}
		
	}
	
	@PreDestroy
	public void destroy() {
		client.close();
	}
	
	 
	private Client createNodeClient() {
		return NodeBuilder.nodeBuilder().settings(settings).node().client();
	}

	private Client createTransportClient() {
		TransportClient tclient = new TransportClient(settings);
		if (transportAdresses != null && !transportAdresses.isEmpty()) {
			for (InetSocketTransportAddress tranpAddr : transportAdresses) {
				tclient.addTransportAddress(tranpAddr);
			}
		}
		return tclient;
	}

	@Override
	public Client getObject() throws Exception {
		return client;
	}

	@Override
	public Class<?> getObjectType() {
		return Client.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}


}
