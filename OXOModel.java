import java.lang.reflect.Array;
import java.util.*;

class OXOModel
{
    final ArrayList<ArrayList<OXOPlayer>> cells;
    final ArrayList<OXOPlayer> players;
    private OXOPlayer currentPlayer;
    private OXOPlayer winner = null;
    private boolean gameDrawn;
    private int winThreshold;
    private int index;
    private OXOPlayer checkPlayer = null;

    public OXOModel(int numberOfRows, int numberOfColumns, int winThresh)
    {
        winThreshold = winThresh;
        cells = new ArrayList<>(numberOfRows);
        for(int y = 0; y < numberOfRows; y++) {
            cells.add(new ArrayList<>(numberOfColumns));
            for(int x = 0; x < numberOfColumns; x++){
                cells.get(y).add(null);
            }
        }
        players = new ArrayList<>(2);
        setWinThreshold(winThresh);
        index = 0;
    }


    public int getNumberOfPlayers()
    {
        return players.size();
    }

    public void addPlayer(OXOPlayer player)
    {
        players.add(player);
        /* If first player added, set this player as current player */
        if(players.size() == 1){
            setCurrentPlayer(player);
        }
    }

    public OXOPlayer getPlayerByNumber(int number)
    {
        return players.get(number-1);
    }

    public OXOPlayer getWinner()
    {
        return winner;
    }

    public void setWinner(OXOPlayer player)
    {
        winner = player;
    }

    public OXOPlayer getCurrentPlayer()
    {
        return currentPlayer;
    }

    public void setCurrentPlayer(OXOPlayer player)
    {
        currentPlayer = player;
    }

    public int getNumberOfRows()
    {
        return cells.size();
    }

    public int getNumberOfColumns()
    {
        return cells.get(0).size();
    }

    public OXOPlayer getCellOwner(int rowNumber, int colNumber)
    {
        return cells.get(rowNumber).get(colNumber);
    }

    public void setCellOwner(int rowNumber, int colNumber, OXOPlayer player)
    {
        cells.get(rowNumber).set(colNumber, player);
    }

    public void setWinThreshold(int winThresh)
    {
        winThreshold = winThresh;
    }

    public int getWinThreshold()
    {
        return winThreshold;
    }

    public void setGameDrawn()
    {
        gameDrawn = true;
    }

    public boolean isGameDrawn()
    {
        if(getPopulation() == getNumberOfColumns()*getNumberOfRows()){
            setGameDrawn();
        }
        return gameDrawn;
    }

    public void nextPlayer()
    {
        index = players.lastIndexOf(currentPlayer);
        index ++;

        if (index >= players.size() || players.get(index) == null) {
            index = 0;
        }
        setCurrentPlayer(players.get(index));
    }

    public int getPopulation()
    {
        int population = 0;
        for (ArrayList<OXOPlayer> cell : cells) {
            for (OXOPlayer oxoPlayer : cell) {
                if (oxoPlayer != null) {
                    population++;
                }
            }
        }
        return population;
    }

    public boolean horizontalWin(int rowNumber, int colNumber)
    {
        int cnt = 1;
        int col = colNumber;

        OXOPlayer p = getCellOwner(rowNumber, colNumber);

        if(p == null){
            return false;
        }

        col = colNumber + 1;
        /* Go right first ... */
        while (inbounds(rowNumber, col) && getCellOwner(rowNumber,col) == p){
                cnt++;
                col ++;
        }

        col = colNumber - 1;
        /* Then go left ... */
        while (inbounds(rowNumber, col) && getCellOwner(rowNumber,col) == p){
            cnt++;
            col --;
        }

        return cnt >= getWinThreshold();
    }

    public boolean verticalWin(int rowNumber, int colNumber)
    {
        int cnt = 1;
        int row = rowNumber;
        OXOPlayer p = getCellOwner(rowNumber, colNumber);

        if(p == null){
            return false;
        }

        row = rowNumber + 1;
        /* Go down first ... */
        while (inbounds(row, colNumber)  && getCellOwner(row,colNumber) == p){
            cnt++;
            row++;
        }

        row = rowNumber - 1;
        /* Then go up ... */
        while (inbounds(row, colNumber) && getCellOwner(row,colNumber) == p){
            cnt++;
            row--;
        }

        return cnt >= getWinThreshold();
    }

    public int directionCount(int rowNumber, int colNumber, Direction d)
    {
        int row_inc = 0, col_inc = 0, row, col;
        int cnt = 0;

        OXOPlayer p = getCellOwner(rowNumber, colNumber);

        if(p == null){
            return 0;
        }

        switch (d){
            case NORTHEAST:
                row_inc = -1;
                col_inc = 1;
                break;
            case NORTHWEST:
                row_inc = -1;
                col_inc = -1;
                break;
            case SOUTHEAST:
                row_inc = 1;
                col_inc = 1;
                break;
            case SOUTHWEST:
                row_inc = 1;
                col_inc = -1;
                break;
        }

        row = rowNumber + row_inc;
        col = colNumber + col_inc;

        while (inbounds(row, col) && getCellOwner(row,col) == p){
            cnt++;
            row += row_inc;
            col += col_inc;
        }
        return cnt;
    }

    public boolean diagWin(int rowNumber, int colNumber)
    {
        int cnt = 1;

        /* NW and SE */
        /* go NW first, then SE */

        cnt += directionCount(rowNumber, colNumber, Direction.NORTHWEST);
        cnt += directionCount(rowNumber, colNumber, Direction.SOUTHEAST);

        if(cnt >= getWinThreshold()){
            return true;
        }

        cnt = 1;

        cnt += directionCount(rowNumber, colNumber, Direction.NORTHEAST);
        cnt += directionCount(rowNumber, colNumber, Direction.SOUTHWEST);

        return cnt >= getWinThreshold();

    }

    public boolean inbounds(int rowNumber, int colNumber)
    {
        if(rowNumber < 0 || rowNumber >= getNumberOfRows() || colNumber < 0 || colNumber >= getNumberOfColumns()){
            return false;
        }
        return true;
    }

    public boolean gameWon()
    {
        int row;
        int col;

        for(row = 0; row < getNumberOfRows(); row++){
            for(col = 0; col < getNumberOfColumns(); col++){
                if(cells.get(row).get(col) != null) {
                    if (horizontalWin(row, col) || verticalWin(row, col) || diagWin(row, col)) {
                        checkPlayer = cells.get(row).get(col);
                        setWinner(checkPlayer);
                        return true;
                    }
                }
            }
        }
        return false;
    }

}




