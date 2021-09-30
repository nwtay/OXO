package OXOExceptions;

public class InvalidIdentifierCharacterException extends InvalidIdentifierException {

    private char c;
    private RowOrColumn t;

    public InvalidIdentifierCharacterException(char c, RowOrColumn t)
    {
        super();
        this.c = c;
        this.t = t;
    }

    public String toString(){

        return super.toString() + " Invalid " + t + " entry! You entered " +
                c + " which is not a valid " + t + " character";
    }
}
