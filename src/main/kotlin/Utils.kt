import kotlin.io.path.Path
import kotlin.io.path.readLines

/**
 * Reads lines from the given input txt file.
 */
 fun readInput(name: String) = Path("src/main/kotlin/inputs/$name.txt").readLines()

inline fun <T> Iterable<T>.productOf(selector: (T) -> Long): Long {
    var product: Long = 1.toLong()
    for (element in this) {
        product *= selector(element)
    }
    return product
}

fun String.splitNonEmpty(): List<String> = trim()
    .split("\\s+".toRegex())


fun <T> Sequence<T>.repeatInfinitely() = sequence { while (true) yieldAll(this@repeatInfinitely) }

