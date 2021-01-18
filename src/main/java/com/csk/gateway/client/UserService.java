package com.csk.gateway.client;

import java.util.List;

import org.springframework.stereotype.Service;

import com.csk.gateway.dto.KeyValue;
import com.csk.gateway.dto.UserRequestDTO;
import com.csk.gateway.dto.UserResponseDTO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {

	
public	UserResponseDTO addUser(UserRequestDTO userRequest,String  userId,List<KeyValue> userData ) {
	UserResponseDTO userResponseDTO = new UserResponseDTO();
	userResponseDTO.setUserId("12345");
	return userResponseDTO;
}

public	Boolean deleteUser(String  userId ) {
	UserResponseDTO userResponseDTO = new UserResponseDTO();
	userResponseDTO.setUserId("12345");
	return true;
}
}
