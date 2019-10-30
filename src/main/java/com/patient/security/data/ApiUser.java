package com.patient.security.data;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 
 * @author Debashis Patra
 *
 */
@EqualsAndHashCode(callSuper=true)
@ToString
public class ApiUser extends User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	private Long userId;
	@Getter
	@Setter
	private String activeStatus;
	@Getter
	@Setter
	private String fullName;
	@Getter
	@Setter
	private String providerCode;
	@Getter
	@Setter
	private String providerName;
	@Getter
	@Setter
	private List<String> programCode;
	@Getter
	@Setter
	private String sessionId;

	public ApiUser(String username, Collection<? extends GrantedAuthority> authorities) {
		super(username, username, true, true, true, true, authorities);
	}

}