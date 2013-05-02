package eu.ttbox.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.flex.remoting.RemotingDestination;
import org.springframework.flex.remoting.RemotingInclude;
import org.springframework.flex.security3.AuthenticationResultUtils;
import org.springframework.stereotype.Service;

import eu.ttbox.domain.dao.user.UserDAO;

@Service("userService")
@RemotingDestination(value = "userServiceDest", channels={"my-amf","my-secure-amf"})
public class UserServiceImpl {

	@Autowired
	@Qualifier("userDAO")
	UserDAO userDAO;

	@RemotingInclude
    public Map<String, Object> getAuthentication() {
        return AuthenticationResultUtils.getAuthenticationResult();
    }

 	

}
