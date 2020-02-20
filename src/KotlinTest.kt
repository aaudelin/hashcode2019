import java.io.File
import java.nio.file.Files
import java.nio.file.Path
import java.util.*

fun main() {

    var dir = File("input/")

    dir.listFiles().forEach {
        println("File ${it.name} debut")
        writeFile(readFile("input/"+it.name), "output/"+it.name)
        println("File ${it.name} fini")
    }

//    var name = "b_read_on.txt"
//    var name = "a_example.txt"

//    writeFile(readFile("input/"+name), "output/"+name)

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