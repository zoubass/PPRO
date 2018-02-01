package cz.eshop.service;

import cz.eshop.dao.AuthoritiesRepository;
import cz.eshop.model.Authorities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthoritiesService {
	
	@Autowired
	private AuthoritiesRepository authoritiesRepository;
	
	public Authorities findUserAuthority(String username){
		return authoritiesRepository.findByUsername(username);
	}
}
