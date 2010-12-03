package com.logansrings.booklibrary

class Author {
	
	static hasMany = [book:Book]

	String firstName
	String lastName
	
    static constraints = {
		firstName(blank:false)
		lastName(blank:false)
    }
	
	String toString() { "${this.lastName}, ${this.firstName}"}
}
