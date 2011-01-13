package com.logansrings.booklibrary

class Author implements Comparable {
	
	static hasMany = [book:Book]

	String firstName
	String lastName
	
    static constraints = {
		firstName(blank:false)
		lastName(blank:false)
    }

	int compareTo(obj) {
		this.toString().compareTo(obj.toString())
	}

	String toString() { "${this.lastName}, ${this.firstName}"}
}
