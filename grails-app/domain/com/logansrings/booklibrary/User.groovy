package com.logansrings.booklibrary

class User {
	
	static hasMany = [book:Book]

	String userName
	String password
	
    static constraints = {
		userName(blank:false, unique:true)
		password(length:4..8)
    }
}
