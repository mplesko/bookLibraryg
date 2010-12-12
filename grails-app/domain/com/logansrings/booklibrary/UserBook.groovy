package com.logansrings.booklibrary

class UserBook {
	
	static belongsTo = [user:User, book:Book]
	
	String bookString() { "${this.book}"}
}