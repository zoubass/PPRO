package cz.eshop.service;

import cz.eshop.dao.AuthoritiesRepository;
import cz.eshop.dao.UserRepository;
import cz.eshop.dto.UserDto;
import cz.eshop.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AuthoritiesRepository authoritiesRepository;

	public void saveUser(UserDto userDto) {
		userDto.getAuthorities().setUsername(userDto.getUser().getUsername());
		userRepository.save(userDto.getUser());
		authoritiesRepository.save(userDto.getAuthorities());
	}

	public Iterable<User> findAll() {
		return userRepository.findAll();
	}

	public void removeUser(Long id) {
		userRepository.delete(id);
	}

	public boolean isNotUniqueUsername(UserDto userDto) {
		return userRepository.findByUsername(userDto.getUser().getUsername()) != null;
	}

	public User findById(Long id) {
		return userRepository.findById(id);
	}

	@Transactional
	public void editUser(UserDto userDto) {
		userRepository.save(userDto.getUser());
	}
}
