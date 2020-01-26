package InterpreterFile

class BinOp(var left: Any?, var op: Token?, var right: Any?): AST() {
    var token  = op
}