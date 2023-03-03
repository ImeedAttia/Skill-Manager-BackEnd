package com.soprahr.skillmanager.app.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.soprahr.skillmanager.app.Exceptions.UserExceptions;
import com.soprahr.skillmanager.app.entities.DepartmentEntity;
import com.soprahr.skillmanager.app.entities.UserEntity;
import com.soprahr.skillmanager.app.repositories.DepartmentRepository;
import com.soprahr.skillmanager.app.repositories.UserRepository;
import com.soprahr.skillmanager.app.requests.UserRequest;
import com.soprahr.skillmanager.app.responses.ErrorMessages;
import com.soprahr.skillmanager.app.responses.UserResponses;
import com.soprahr.skillmanager.app.services.UserService;
import com.soprahr.skillmanager.app.shared.dto.UserDto;




//@CrossOrigin(origins = {"http://localhost:4200","http://domainx.com"}) pour plusieur client
//@CrossOrigin(origins = "*") pour tout client
//@CrossOrigin(origins = "http://localhost:4200") client specifique
@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	UserService userService;
	
	
	@Autowired
	UserRepository repository;
	
	@Autowired
	DepartmentRepository departmentRepository;
	
	@GetMapping(path="/{id}",produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<UserResponses> getUser(@PathVariable String id) {
		
		UserDto userDto = userService.getUserByUserId(id);
		UserResponses userResponses = new UserResponses();
		BeanUtils.copyProperties(userDto, userResponses);
		return new ResponseEntity<UserResponses>(userResponses,HttpStatus.OK);	
	}
	
	
	//@CrossOrigin(origins = {"http://localhost:4200","http://domainx.com"}) pour plusieur client
	//@CrossOrigin(origins = "*") pour tout client
	//@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping(produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public List<UserResponses>getAllUsers(@RequestParam(value="page",defaultValue = "1") int page
														,@RequestParam(value="limit",defaultValue = "50") int limit
														,@RequestParam(value="status",defaultValue = "1") int status
														,@RequestParam(value="search",defaultValue = "") String search) 
	{		
		List<UserResponses> userResponse = new ArrayList<>();		
		List<UserDto> users =  userService.getUsers(page,limit,search,status);
		ModelMapper modelMapper = new ModelMapper();
			for (UserDto userDto : users) {
				UserResponses user = modelMapper.map(userDto, UserResponses.class);
				userResponse.add(user);
			}
		return userResponse;
	}
	
	
	
	
	@PostMapping(consumes ={MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE},
				 produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<UserResponses> createUser(@Valid @RequestBody  UserRequest userRequest) throws Exception{
		
		if(userRequest.getFirstname().isEmpty() && userRequest.getEmail().isEmpty() && userRequest.getPassword().isEmpty()) throw new UserExceptions(ErrorMessages.MISSING_REQUIRED_FILED.getErrorMessage());
		
		ModelMapper modelMapper = new ModelMapper();
		UserDto userDto = modelMapper.map(userRequest, UserDto.class);
		UserDto createUser = userService.createUser(userDto);
		UserResponses userResponse = modelMapper.map(createUser, UserResponses.class);
		return new ResponseEntity<UserResponses>(userResponse,HttpStatus.CREATED);
	}
	
	
	
	
	@PutMapping(path="/{id}",consumes ={MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE},
							 produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<UserResponses> updateUser(@PathVariable String id,@RequestBody UserRequest userRequest) {
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userRequest, userDto);
		UserDto updateUser = userService.updateUser(id,userDto);
		UserResponses userResponse = new UserResponses();
		BeanUtils.copyProperties(updateUser, userResponse);
		return new ResponseEntity<UserResponses>(userResponse,HttpStatus.ACCEPTED);
	}
	
	
	
	@DeleteMapping(path="/{id}")
	public ResponseEntity<Object> deleteUser(@PathVariable String id) {
		userService.DeleteUser(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	

	@PostMapping("/add-user-and-dept")
	public ResponseEntity<?> addUserAndDepartment(@RequestBody UserEntity userRequest){
		UserEntity userEntity = repository.save( userRequest );
		
		for(long depId : userRequest.getDepIds()) {
			if(departmentRepository.findById(depId).isPresent() ) {
				DepartmentEntity departmentEntity = departmentRepository.findById(depId).get();
				userEntity.addDepartment(departmentEntity);
			}else {
				System.out.println("Departlent : "+ depId + " does not exist");
			}	
		}
		repository.save( userEntity );
		return ResponseEntity.ok().build();
	}
}
