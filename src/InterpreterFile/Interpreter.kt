package InterpreterFile

import java.lang.Exception

/**
 * InterpreterFile.Interpreter: 解释器，接受词法分析器的Token，并验证是否合法的同时得出答案
 * Author：Rarcher
 *
* */


class Interpreter(input:String) {
    private val laxer = Laxer(input)
    private var currentToken: Token = laxer.getNextToken()


    fun error(){
        throw  Exception("语法不合法")
    }
    fun eat(tokenType: Type){
        if (currentToken.type==tokenType){
            currentToken=laxer.getNextToken()
        }else error()
    }

    fun factor():Int{//最高优先级的括号
        val token = currentToken
        when {
            token.type == Type.INTEGER -> {
                eat(Type.INTEGER)
                return token.value.toString().toInt()
            }
            token.type == Type.LPAREN -> {
                eat(Type.LPAREN)
                var result = expr()
                eat(Type.RPAREN)
                return result
            }
            token.type == Type.RPAREN -> error()
        }
        error()
        return (token.value as Char).toInt()

    }

    fun term():Int{//优先级更高的乘除
        var result = factor()
        while (currentToken.type == Type.MULTI || currentToken.type == Type.DIVISION){
            val token = currentToken
            if (token.type == Type.MULTI){
                eat(Type.MULTI)
                result *= factor()
            }else{
                eat(Type.DIVISION)
                result /= factor()
            }
        }
        return result
    }

    fun expr():Int{//最低等级
       // currentToken =laxer.getNextToken()
        var res = term()
       /* val left = currentToken
        eat(InterpreterFile.Type.INTEGER)
        var ans = left?.value.toString().toInt()*/
        while (currentToken.type== Type.PLUS ||currentToken.type== Type.MINUS){
            val token = currentToken
            if (token.type == Type.PLUS){
                eat(Type.PLUS)
                res+=term()
            }else{
                eat(Type.MINUS)
                res-=term()
            }
        }
        return res
    }
}