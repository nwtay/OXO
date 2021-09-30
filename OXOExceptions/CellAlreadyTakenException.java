package OXOExceptions;

public class CellAlreadyTakenException extends OXOMoveException {

    public CellAlreadyTakenException()
    {
        super();
    }

    public String toString()
    {
        return super.toString() + ": This cell is already taken. Please choose another cell. ";
    }
}
