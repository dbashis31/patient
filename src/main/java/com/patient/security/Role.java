package com.patient.security;

import java.util.HashMap;

public class Role {
	
	private Role () {
		
	}
	
	static HashMap<String, String> roleKeys = new HashMap<>();
    // Open AM role configuration 
	static {
		roleKeys.put("CP_PUBLIC", "CP_PUBLIC");		
		roleKeys.put("CP_EMPLOYER", "CP_EMPLOYER");		
	}
}