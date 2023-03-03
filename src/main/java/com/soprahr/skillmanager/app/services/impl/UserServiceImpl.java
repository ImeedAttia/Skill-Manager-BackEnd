package com.soprahr.skillmanager.app.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.soprahr.skillmanager.app.entities.DepartmentEntity;
import com.soprahr.skillmanager.app.entities.RoleEntity;
import com.soprahr.skillmanager.app.entities.UserEntity;
import com.soprahr.skillmanager.app.repositories.DepartmentRepository;
import com.soprahr.skillmanager.app.repositories.RoleRepository;
import com.soprahr.skillmanager.app.repositories.UserRepository;
import com.soprahr.skillmanager.app.services.UserService;
import com.soprahr.skillmanager.app.shared.Utils;
import com.soprahr.skillmanager.app.shared.dto.UserDto;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	DepartmentRepository departmentRepository;
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	Utils utils;
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public UserDto createUser(UserDto user) {
		UserEntity checkUser = userRepository.findByEmail(user.getEmail());
		if (checkUser != null)
			throw new RuntimeException("User Already Exists !");
		
		if (roleRepository.findById(user.getRole().getIdRole()) != null) {
			throw new RuntimeException("Role Not Found !!");
		}
		
		ModelMapper modelMapper = new ModelMapper();
		UserEntity userEntity = modelMapper.map(user, UserEntity.class);

		userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		userEntity.setUserId(utils.GenerateStringId(32));
		
		for(long depId : userEntity.getDepIds()) {
			if(departmentRepository.findById(depId).isPresent()) {
				DepartmentEntity departmentEntity = departmentRepository.findById(depId).get();
					userEntity.addDepartment(departmentEntity);
				}else {
					//if (!checkUser.getRole().getTitre().equals("Admin"))
						throw new RuntimeException("Department Not Found !!");
				}
			}
			
		UserEntity newUser = userRepository.save(userEntity);
		
		UserDto userDto = modelMapper.map(newUser, UserDto.class);
		return userDto;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserEntity userEntity = userRepository.findByEmail(email);
		if (userEntity == null)
			throw new UsernameNotFoundException(email);

		return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), new ArrayList<>());
	}

	@Override
	public UserDto getUser(String email) {
		UserEntity userEntity = userRepository.findByEmail(email);
		if (userEntity == null)
			throw new UsernameNotFoundException(email);
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userEntity, userDto);
		return userDto;
	}

	@Override
	public UserDto getUserByUserId(String UserId) {
		UserEntity userEntity = userRepository.findByUserId(UserId);
		if (userEntity == null)
			throw new UsernameNotFoundException(UserId);

		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userEntity, userDto);
		return userDto;
	}

	@Override
	public UserDto updateUser(String id, UserDto userDto) {
		UserEntity userEntity = userRepository.findByUserId(id);
		if (userEntity == null)
			throw new UsernameNotFoundException(id);

		userEntity.setFirstname(userDto.getFirstname());
		userEntity.setLastname(userDto.getLastname());
		UserEntity UserUpdated = userRepository.save(userEntity);
		UserDto user = new UserDto();
		BeanUtils.copyProperties(UserUpdated, user);
		return user;

	}

	@Override
	public void DeleteUser(String UserId) {
		UserEntity userEntity = userRepository.findByUserId(UserId);
		if (userEntity == null)
			throw new UsernameNotFoundException(UserId);
		userRepository.delete(userEntity);
	}

	@Override
	public List<UserDto> getUsers(int page, int limit, String search, int status) {
		if (page > 0)
			page -= 1;
		List<UserDto> userDto = new ArrayList<>();
		Pageable pageableRequest = PageRequest.of(page, limit);
		Page<UserEntity> userPage;
		if (search.isEmpty()) {
			userPage = userRepository.findAllUser(pageableRequest);
		} else {
			userPage = userRepository.findAllUserCriteria(pageableRequest, search, status);
		}

		List<UserEntity> users = userPage.getContent();
		for (UserEntity userEntity : users) {

			ModelMapper modelMapper = new ModelMapper();
			UserDto user = modelMapper.map(userEntity, UserDto.class);
			userDto.add(user);
		}
		return userDto;
	}

}
