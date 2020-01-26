package InterpreterFile

import java.lang.Exception

class Parser(private var laxer: Laxer) {
    private  var currentToken: Token = laxer.getNextToken()

    fun error(){
        throw  Exception("语法不合法")
    }
    fun eat(tokenType: Type){
        if (currentToken.type==tokenType){
            currentToken=laxer.getNextToken()
        }else error()
    }
    /**
     * factor : INTEGER | LPAREN expr RPAREN
     * */
    fun factor(): Any {//最高优先级的括号
        val token = currentToken
       // print(token.toString())
        when {

            token.type == Type.PLUS ->{
                eat(Type.PLUS)
                var node = UnaryOp(token, factor())
                return node
            }


            token.type == Type.MINUS ->{
                eat(Type.MINUS)
                var node = UnaryOp(token, factor())
                return node
            }


            token.type == Type.INTEGER -> {
                eat(Type.INTEGER)
                return BinOp(null, token, null)
            }
            token.type == Type.LPAREN -> {
                eat(Type.LPAREN)
                var node = expr()
                eat(Type.RPAREN)
                return node
            }


        }
        error()
        return BinOp(null, null, null)

    }
/**
 * term : factor ((MUL | DIV) factor)*
 * */
    fun term(): Any {//优先级更高的乘除
        var node = factor()
        while (currentToken.type == Type.MULTI || currentToken.type == Type.DIVISION){
            val token = currentToken
            if (token.type == Type.MULTI){
                eat(Type.MULTI)

            }else if (token.type == Type.DIVISION){
                eat(Type.DIVISION)

            }
            node = BinOp(left = node, op = token, right = factor())
        }
        return node
    }


 /**
  *  expr   : term ((PLUS | MINUS) term)*
    term   : factor ((MUL | DIV) factor)*
    factor : (PLUS|MINUS)factor|INTEGER | LPAREN expr RPAREN*/
    fun expr(): Any {//最低等级
        // currentToken =laxer.getNextToken()
        var node = term()
        /* val left = currentToken
         eat(InterpreterFile.Type.INTEGER)
         var ans = left?.value.toString().toInt()*/
     while (currentToken.type== Type.PLUS ||currentToken.type== Type.MINUS){
            val token = currentToken
            if (token.type == Type.PLUS){
                eat(Type.PLUS)
            }else if (token.type == Type.MINUS){
                eat(Type.MINUS)
            }
            node = BinOp(left = node, op = token, right = term())
        }
        return node
    }
    fun parse(): Any {
        return expr()
    }
}