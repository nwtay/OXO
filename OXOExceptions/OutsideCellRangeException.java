package OXOExceptions;

public class OutsideCellRangeException extends CellDoesNotExistException {
   private RowOrColumn t;
   private int position;
   private char c;

    public OutsideCellRangeException(int position, RowOrColumn t)
    {
        this.position = position;
        this.c = (char)position;
        this.t = t;
    }

    public String toString(){
        return super.toString() + " Your entry for " + t + " was outside the cell range. The position" +
                " you tried to enter was: " + c;
    }
}
