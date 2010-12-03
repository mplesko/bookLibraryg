package com.logansrings.booklibrary

class Book {
	
	String title
	Author author

	static belongsTo = Author
	static constraints = {
		title(blank:false)
	}
	

	String toString() { "${this.title}"}
}
