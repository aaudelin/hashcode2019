import java.io.File
import java.util.*

fun main() {

    writeFile(readFile("input/a_example.txt"), "output/a_example.txt")

}

fun writeFile(librairiesToWrite: List<Librairie>, fileOuput: String) {
    // Ecrire

    val fileA = File(fileOuput)

    fileA.createNewFile()
    fileA.writeText("${librairiesToWrite.size}\n")

    librairiesToWrite.forEach {
        fileA.appendText("${it.id} ${it.books.size}\n")
        it.books.forEach {
            fileA.appendText("${it.id} ")
        }
        fileA.appendText("\n")
    }
}

fun readFile(name: String) : List<Librairie> {
    val fileA = File(name)

    var lines = Scanner(fileA)

    var descriptionHeader = lines.nextLine().split(" ")
    var header = Header(descriptionHeader[0].toInt(), descriptionHeader[1].toInt(), descriptionHeader[2].toInt())
    var books = mutableSetOf<Book>()
    var libs = mutableListOf<Librairie>()
    var scoresLivres = lines.nextLine().split(" ").forEachIndexed { index, score ->
        books.add(Book(index, score.toInt()))
    }

    var index = 0
    while (lines.hasNextLine()) {

        var libDesc = lines.nextLine().split(" ")
        var booksLibs = mutableSetOf<Book>()
        lines.nextLine().split(" ").forEachIndexed { index, s ->
            booksLibs.add(books.find { it.id == s.toInt() }!!)
        }

        libs.add(Librairie(index, booksLibs, libDesc[1].toInt(), libDesc[2].toInt()))
        index++
    }
    var javaTest = JavaTest()
    return javaTest.input(header, libs)
}