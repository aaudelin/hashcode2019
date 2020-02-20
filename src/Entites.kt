data class Header(var nbBooks: Int, var nbLibs: Int, var nbDaysTotal: Int)

data class Librairie(var id: Int, var books: Set<Book>, var signupDays: Int, var booksByDay: Int)

data class Book(var id: Int, var score: Int)