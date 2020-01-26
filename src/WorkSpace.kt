import InterpreterFile.Interpreter2
import InterpreterFile.Laxer
import InterpreterFile.Parser

fun main() {
    val string = "7 + (((3 + --2)))"
    //println(string+" = ${InterpreterFile.Interpreter(string).expr()}")

    var lexer = Laxer(string)
    var parser = Parser(lexer)
    var interpreter = Interpreter2(parser)
    var result = interpreter.interpret()
    print(result)




}
