import com.logansrings.booklibrary.*;

class BootStrap {
	
	def init = { servletContext ->
		environments {
			development {
				new Author(firstName:"DevFirstName",lastName:"DevLastName").save()
				def author = Author.findByLastName("DevLastName")
				new Book(title:"DevTitle", author: author).save()
//				def book = Book.findByTitle("DevTitle")
//				book.setAuthor author
//				new Book(title:"DevTitle", 
//					new Author(firstName:"DevFirstName",lastName:"DevLastName")).save()
			}
		}
	}
	def destroy = {
	}
}
