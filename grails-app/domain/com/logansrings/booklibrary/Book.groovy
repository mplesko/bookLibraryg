package com.logansrings.booklibrary

class Book implements Comparable {
	
	static belongsTo = Author
	static hasMany = [userBooks:UserBook]
	
	String title
	Author author

	static constraints = {
		title(blank:false)
	}
	
	int compareTo(obj) {
		this.toString().compareTo(obj.toString())
	}

	String toString() { "${this.title}"}
}
