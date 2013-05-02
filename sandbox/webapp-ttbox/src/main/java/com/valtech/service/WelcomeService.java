package com.valtech.service;

import org.springframework.flex.remoting.RemotingDestination;
import org.springframework.stereotype.Service;

//pour déclarer le bean comme étant managé par spring
@Service
// pour déclarer le bean comme étant accessible par Flex
@RemotingDestination(value = "welcomeServiceDest")
public class WelcomeService {

	public String sayHello(String pLogin) {
		return "Bienvenue à toi " + pLogin;
	}
}
