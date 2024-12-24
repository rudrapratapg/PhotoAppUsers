package com.deepak.photoapp.users.service.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class UserDTO implements Serializable {
    /**
	 * Deepak Gola
	 */
	private static final long serialVersionUID = 3894068145235989796L;
	
	private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String userId;
    private String encryptedPassword;
}
