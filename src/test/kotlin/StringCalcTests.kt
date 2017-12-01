import com.winterbe.expekt.should
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it

object StringCalcTests : Spek({
    describe("a string calculator") {
        it("evaluates correctly") {
            evaluate("12 + 5").should.equal(17)
            evaluate("12 + 3").should.equal(15)
            evaluate("12 + 12").should.equal(24)
            evaluate("24 + 13").should.equal(37)
            evaluate("12 - 5").should.equal(7)
            evaluate("1 - 4").should.equal(-3)
            evaluate("2 * 12").should.equal(24)
            evaluate("12 / 2").should.equal(6)
            evaluate("12 + 4 + 4").should.equal(20)
            evaluate("12 + 4 + 4 - 6 + 3").should.equal(17)
        }
    }
})

fun evaluate(expression: String): Int {
    val tokens = expression.split(" ")
    val startValue = tokens[0].toInt()
    return tokens.subList(1, tokens.size)
        .mapIndexed { index, _ -> index + 1 }
        .filter { it % 2 != 0 }
        .map { listOf(tokens[it], tokens[it + 1]) }
        .fold(startValue) { acc, seg -> calculate(acc, seg[0], seg[1].toInt())}
}

private fun calculate(left: Int, operator: String, right: Int): Int =
        when (operator) {
            "+" -> left + right
            "*" -> left * right
            "/" -> left / right
            else -> left - right
        }
