import OXOExceptions.*;

class OXOController
{
    OXOModel gameModel;
    private char tmp_c;
    private int done_flag = 0;
    int cnt = 0;

    public OXOController(OXOModel model)
    {
        gameModel = model;
    }

    private void vacant_check(int rowNumber, int colNumber) throws CellAlreadyTakenException
    {
        if(gameModel.getCellOwner(rowNumber, colNumber)!=null){
            throw new CellAlreadyTakenException();
        }
    }

    private void char_check(char c1, char c2) throws InvalidIdentifierCharacterException
    {
        if(c1 < 'a' || c1 > 'i'){
            throw new InvalidIdentifierCharacterException(c1, RowOrColumn.ROW);
        }

        if(c2 < '1' || c2 > '9'){
            throw new InvalidIdentifierCharacterException(c2, RowOrColumn.COLUMN);
        }
    }

    private void range_check(char c1, char c2) throws OutsideCellRangeException
    {
        tmp_c = (char)(97 + gameModel.getNumberOfRows());
        if(c1 < 'a' || c1 >= tmp_c){
            throw new OutsideCellRangeException((int)c1, RowOrColumn.ROW);
        }

        tmp_c = (char)(49 + gameModel.getNumberOfColumns());
        if(c2 < '1' || c2 >= tmp_c){
            throw new OutsideCellRangeException((int)c2, RowOrColumn.COLUMN);
        }
    }

    private void str_check(String command) throws InvalidIdentifierLengthException
    {
        if(command.length() > 2){
            throw new InvalidIdentifierLengthException(command.length());
        }
        if(command.length() < 2){
            throw new InvalidIdentifierLengthException(command.length());
        }
    }

    public void handleIncomingCommand(String command) throws OXOMoveException {
        
        if (!gameModel.gameWon() && !gameModel.isGameDrawn()) {
            str_check(command);
            char c1 = command.charAt(0);
            c1 = Character.toLowerCase(c1);
            int row = (int) c1 - 97;
            char c2 = command.charAt(1);
            int col = Character.getNumericValue(c2) - 1;
            char_check(c1, c2);
            range_check(c1, c2);
            vacant_check(row, col);
            gameModel.setCellOwner(row, col, gameModel.getCurrentPlayer());
            if(gameModel.gameWon() || gameModel.isGameDrawn()){
                return;
            }
            gameModel.nextPlayer();
            cnt++;
        }
    }

}
