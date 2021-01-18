package com.csk.gateway.client;

import java.util.List;

import org.springframework.stereotype.Service;

import com.csk.gateway.dto.KeyValue;
import com.csk.gateway.dto.UserRequestDTO;
import com.csk.gateway.dto.UserResponseDTO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CustomerSearch {

	public	UserResponseDTO search(String  userId) {
		UserResponseDTO userResponseDTO = new UserResponseDTO();
		userResponseDTO.setUserId(userId);
		return userResponseDTO;
	}
	
}
