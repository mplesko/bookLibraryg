package com.logansrings.booklibrary

class UserBook implements Comparable {
	
	static belongsTo = [user:User, book:Book]
	 
	String bookString() { "${this.book}"}
	
//	Book getBook() { ${this.book}}
	
	int compareTo(obj) {
		this.book.toString().compareTo(obj.book.toString())
	}
}
