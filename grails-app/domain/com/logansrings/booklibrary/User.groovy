package com.logansrings.booklibrary

class User {
	
	static hasMany = [userBooks:UserBook]
	
	String userName
	String password
	
    static constraints = {
		userName(blank:false, unique:true)
		password(length:4..8)
    }
}
