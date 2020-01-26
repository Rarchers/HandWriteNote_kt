package InterpreterFile

/**
 * InterpreterFile.Laxer：词法分析器，将输入的字符串解析成一段一段的Token，便于解释器对其进行解析
 * Author：Rarcher
* */
class Laxer(input: String) {
    val text = input
    var pos = 0
    var currentChar: Char? = text[pos]


    fun advance(){//指向下一个字符
        pos++
        currentChar = if (pos>text.length-1)
            null
        else{
            text[pos]
        }

    }

    fun skipSpace(){//跳过空格
        while (currentChar!=null&&currentChar==' '){
            advance()
        }
    }

    fun integer():Long{ //输入多位数
        var res = ""
        while (currentChar!=null&&currentChar!!.isDigit()){
            res+=currentChar
            advance()
        }

        return res.toLong()
    }

    fun getNextToken(): Token {
        while (currentChar!=null){
            if (currentChar==' '){//跳过空格
                skipSpace()
                continue
            }
            if (currentChar!!.isDigit()){//数字
                return Token(Type.INTEGER, integer())
            }else if (currentChar == '+'){//加号
                advance()
                return Token(Type.PLUS, '+')
            }else if (currentChar == '-'){//减号
                advance()
                return Token(Type.MINUS, '-')
            }else if (currentChar == '*'){
                advance()
                return Token(Type.MULTI, '*')
            }else if (currentChar == '/'){
                advance()
                return Token(Type.DIVISION, '/')
            }else if (currentChar == '('){
                advance()
                return Token(Type.LPAREN, '(')
            }else if (currentChar == ')'){
                advance()
                return Token(Type.RPAREN, ')')
            }
            else{
                return Token(Type.ERROR, ' ')
            }
        }
        return Token(Type.EOF, null)
    }
}