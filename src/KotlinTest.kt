import java.io.File
import java.nio.file.Files
import java.util.*

fun main() {
    val fileA = File("input/a_example.txt")

    var lines = Scanner(fileA)

    var descriptionHeader = lines.nextLine().split(" ")
    var header = Header(descriptionHeader[0].toInt(), descriptionHeader[1].toInt(), descriptionHeader[3].toInt())
    var books = mutableSetOf<Book>()
    var libs = mutableListOf<Librairie>()
    var scoresLivres = lines.nextLine().split(" ").forEachIndexed { index, score ->
        books.add(Book(index, score.toInt()))
    }


    while (lines.hasNextLine()) {
        var libDesc = lines.nextLine().split(" ")
        var booksLibs = mutableSetOf<Book>()
        lines.nextLine().split(" ").forEachIndexed { index, s ->
            booksLibs.add(books.find { it.id == s.toInt() }!!)
        }

        libs.add(Librairie(booksLibs, libDesc[1].toInt(), libDesc[2].toInt()))
    }

    function(header, libs)
}