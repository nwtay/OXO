import java.util.ArrayList;

public class AssertTests {

    public static void main(String[] args) {
        // Tricky bit of code to check that assertions have been enabled !
        boolean assertionsEnabled = false;
        assert(assertionsEnabled = true);
        if (assertionsEnabled) {
            OXOModel model = new OXOModel(3,3,3);
            assert(model.getNumberOfRows()==3);
            assert(model.getNumberOfColumns()==3);
            assert(model.getWinThreshold()==3);

            OXOModel model2 = new OXOModel(3,19,21);
            assert(model2.getNumberOfRows()==3);
            assert(model2.getNumberOfColumns()==19);
            assert(model2.getWinThreshold()==21);

            OXOPlayer play1 = new OXOPlayer('a');
            OXOPlayer play2 = new OXOPlayer('b');
            OXOPlayer play3 = new OXOPlayer('c');
            OXOPlayer play4 = new OXOPlayer('d');

            assert(model2.getNumberOfPlayers()==0);
            model2.addPlayer(play1);
            assert(model2.getNumberOfPlayers()==1);
            model2.addPlayer(play2);
            assert(model2.getNumberOfPlayers()==2);
            model2.addPlayer(play3);
            assert(model2.getNumberOfPlayers()==3);
            model2.addPlayer(play4);
            assert(model2.getNumberOfPlayers()==4);

            assert(model2.getPlayerByNumber(1).letter == 'a');
            assert(model2.getPlayerByNumber(2).letter == 'b');
            assert(model2.getPlayerByNumber(3).letter == 'c');
            assert(model2.getPlayerByNumber(4).letter == 'd');

            model2.setCurrentPlayer(play2);
            assert(model2.getCurrentPlayer().letter=='b');
            model2.nextPlayer();
            assert(model2.getCurrentPlayer().letter=='c');
            model2.nextPlayer();
            assert(model2.getCurrentPlayer().letter=='d');
            model2.nextPlayer();
            assert(model2.getCurrentPlayer().letter=='a');
            model2.nextPlayer();
            assert(model2.getCurrentPlayer().letter=='b');
            model2.nextPlayer();
            assert(model2.getCurrentPlayer().letter=='c');
            model2.nextPlayer();
            assert(model2.getCurrentPlayer().letter=='d');
            model2.nextPlayer();
            assert(model2.getCurrentPlayer().letter=='a');


            assert(model2.getCellOwner(0,0)==null);
            model2.setCellOwner(0,0,play1);
            assert(model2.getCellOwner(0,0).letter=='a');
            model2.setCellOwner(0,1,play2);
            assert(model2.getCellOwner(0,1).letter=='b');
            model2.setCellOwner(1,1,play3);
            assert(model2.getCellOwner(1,1).letter=='c');


            ArrayList<OXOPlayer> players2 = new ArrayList<>(2);
            players2.add(0, play1);
            players2.add(1, play2);
            int x =  players2.size();
            assert(x == 2);
            /* Adding more players */
            players2.add(2, play3);
            players2.add(3, play4);
            x =  players2.size();
            assert(x == 4);
            /* Getting player number 3 */
            assert(players2.get(2).getPlayingLetter() == 'c');


            String command = "a1";
            char c1 = command.charAt(0);
            char c2 = command.charAt(1);
            int col = Character.getNumericValue(c2) - 1;
            assert(col == 0);

            String command2 = "b3";
            char c3 = command2.charAt(0);
            int row2 = c3 - 97;
            char c4 = command2.charAt(1);
            int col2 = Character.getNumericValue(c4) - 1;
            assert(col2 == 2);

            OXOModel model3 = new OXOModel(3,3,3);
            model3.addPlayer(play1);
            model3.addPlayer(play2);
            model3.addPlayer(play3);
            model3.addPlayer(play4);

            OXOController control = new OXOController(model3);


            try{
                control.handleIncomingCommand("a1");
            }
            catch (Exception e){
                System.out.println("Exception?");
            }

            try{
                control.handleIncomingCommand("b1");
            }
            catch (Exception e){
                System.out.println("Exception?");
            }

            try{
                control.handleIncomingCommand("a3");
            }
            catch (Exception e){
                System.out.println("Exception?");
            }
            assert(!model3.horizontalWin(0,2));
            assert(model3.getWinner()==null);


            try{
                control.handleIncomingCommand("b2");
            }
            catch (Exception e){
                System.out.println("Exception?");
            }

            assert(!model3.horizontalWin(1,1));

            try{
                control.handleIncomingCommand("c2");
            }
            catch (Exception e){
                System.out.println("Exception?");
            }

            assert(!model3.horizontalWin(2,1));

            try{
                control.handleIncomingCommand("b3");
            }
            catch (Exception e){
                System.out.println("Exception?");
            }

            try{
                control.handleIncomingCommand("c1");
            }
            catch (Exception e){
                System.out.println("Exception?");
            }

            try{
                control.handleIncomingCommand("a2");
            }
            catch (Exception e){
                System.out.println("Exception?");
            }

            assert(model3.isGameDrawn()==false);

            try{
                control.handleIncomingCommand("c3");
            }
            catch (Exception e){
                System.out.println("Exception?");
            }

            assert(model3.getPopulation()==9);
            assert(model3.isGameDrawn()==true);



            char tmp_c = (char)(97);
            System.out.println(tmp_c);




            System.out.println("SUCCESS: All tests passed !!!");
        }
        else {
            System.out.println("You MUST run java with assertions enabled (-ea) to test your program !");
        }
    }





}
