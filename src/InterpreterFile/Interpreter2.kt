package InterpreterFile

class Interpreter2(var parser: Parser) {

    fun visit_BinOp(node:Any):Int{
        if (node is BinOp) {
            if (node.left==null&&node.right==null){
                return node.op?.value.toString().toInt()
            }else{
                if (node.op?.type == Type.PLUS)
                    return visit_BinOp((node.left)!!) + visit_BinOp(node.right!!)
                else if (node.op?.type == Type.MINUS)
                    return visit_BinOp(node.left!!) - visit_BinOp(node.right!!)
                else if (node.op?.type == Type.MULTI)
                    return visit_BinOp(node.left!!) * visit_BinOp(node.right!!)
                else  (node.op?.type == Type.DIVISION)
                return visit_BinOp(node.left!!) / visit_BinOp(node.right!!)
            }
        }else if (node is UnaryOp){
           var op = node.op.type
            if(op == Type.PLUS)
                return +visit_BinOp(node.expr)
            else return -visit_BinOp(node.expr)
        }
        else return 0
    }
    fun interpret():Int{
        val tree = parser.parse()
        return visit_BinOp(tree)
    }



}