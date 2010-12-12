
import com.logansrings.booklibrary.*

class Junk {

	static main(args) {
		def user = User.findById(7)
		def book = Book.findById(2)
		new UserBook(user, book).save()

	}

}
