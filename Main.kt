private val numbers = arrayOf(12, 444, 54643, 3155, 667543, 8637, 0, 369, 7516, 335)
private val maxColumn = 4
private val maxDigit = 6

fun main() {
    // Modified number from function putStarToNumber
    val modifiedNumbers = java.util.ArrayList<String>()

    // Looping each number
    for (number in numbers) {
        // Add modified number to modifiedNumbers
        modifiedNumbers.add(putStarToNumber(number))
    }

    // Final result
    val output = createTable(modifiedNumbers)

    println(output)
}

/**
 * Add * to number
 *
 * @param num Number from [numbers]
 */
fun putStarToNumber(num: Int): String {
    // Convert to string
    val string = "$num"

    // Detect length of the number
    val length = string.length

    // Result
    var stars = ""

    // Total star to be added to number
    var i = (maxDigit - length)

    // Looping down from [i] to 0
    while (i > 0) {
        // Add star to [stars]
        stars += "*"

        i--
    }

    return stars + num
}

/**
 * Create table
 *
 * @param modifiedNumbers List of modified number
 */
fun createTable(modifiedNumbers: java.util.ArrayList<String>): String {
    var col = 1
    var row = 1
    var index = 1
    var modifiedNumbersIndex = 0

    var output = ""

    while (index <= 24) {

        // Detect row types (odd for lines, even for numbers)
        if (row % 2 == 1) { // Line row
            if (col == 1) {
                output += "+======+"
            } else if (col > 1 && col < maxColumn) {
                output += "======+"
            } else if (col == maxColumn) {
                output += "======+\n"

                col = 0
                row += 1
            }
            col += 1
        } else { // Number row
            // Prevent ArrayIndexOutOfBoundsException
            if (modifiedNumbersIndex < modifiedNumbers.size) {
                val modifiedNumber = modifiedNumbers[modifiedNumbersIndex]

                if (col == 1) { // Start column
                    output += "|$modifiedNumber|"
                } else if (col > 1 && col < maxColumn) { // Middle column
                    output += "$modifiedNumber|"

                    if (modifiedNumbersIndex >= (modifiedNumbers.size - (modifiedNumbers.size % maxColumn))) {
                        output += "\n"

                        col = 0
                        row += 1
                    }
                } else if (col == maxColumn) { // End column
                    output += "$modifiedNumber|\n"

                    col = 0
                    row += 1
                }
                col += 1
                modifiedNumbersIndex++
            }
        }

        index++
    }

    return output
}