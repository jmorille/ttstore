package eu.ttbox.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.flex.remoting.RemotingDestination;
import org.springframework.flex.remoting.RemotingInclude;
import org.springframework.stereotype.Service;

import eu.ttbox.domain.dao.menu.MenuDAO;
import eu.ttbox.domain.model.service.Menu;

@Service("serviceConsulter")
@RemotingDestination(value = "serviceConsulterDest")
public class ServiceConsulterImpl {

	@Autowired
	@Qualifier("menuDAO")
	MenuDAO menuDAO;


	@RemotingInclude
	public List<Menu> findServiceMenu() {
		List<Menu> result = menuDAO.findAllActive();
		return result;
	}
	
	@RemotingInclude
 	public String sayHello(String pLogin) {
		return "Bienvenue à toi " + pLogin;
	}

}
