package OXOExceptions;

public class InvalidIdentifierLengthException extends InvalidIdentifierException{

    int length;

    public InvalidIdentifierLengthException(int length)
    {
        super();
        this.length = length;
    }

    public String toString(){
        return super.toString() + " Invalid length of input string. You entered a string with length: "
                + length + ". Was expecting length of string of size 2";
    }


}
