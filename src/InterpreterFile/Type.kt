package InterpreterFile

enum class Type(type:String) {
    ERROR("Error"),//解析错误节点
    EOF("Eof"),//解析结束节点
    INTEGER("Integer"),   //数字
    PLUS("Plus"),    //加法
    MINUS("Minus"), //减法
    MULTI("Multi"),//乘法
    DIVISION("Division"),//除法
    LPAREN("("),//左括号
    RPAREN(")"),//右括号
}