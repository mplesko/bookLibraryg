package com.logansrings.booklibrary

class Book {
	
	static belongsTo = Author
	static hasMany = [userBooks:UserBook]
	
	String title
	Author author

	static constraints = {
		title(blank:false)
	}
	

	String toString() { "${this.title}"}
}
