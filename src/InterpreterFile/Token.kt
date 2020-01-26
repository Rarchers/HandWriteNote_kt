package InterpreterFile

class Token(val type: Type, val value:Any?) {
    override fun toString():String{
        return "InterpreterFile.Token($type,$value)"
    }
}