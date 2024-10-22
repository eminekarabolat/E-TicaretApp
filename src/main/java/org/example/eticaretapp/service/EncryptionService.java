package org.example.eticaretapp.service;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class EncryptionService {
	public String encryptPassword(String password) {
		return BCrypt.hashpw(password, BCrypt.gensalt());
	}
	
	public Boolean checkPassword(String password, String encryptedPassword) {
		return BCrypt.checkpw(password, encryptedPassword);
	}
	
}