/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Engine;

/**
 *
 * @author Anat
 */
public class HumanPlayer extends Player
{
    public HumanPlayer(int playerNum, String playerName, GameBoard board, LoadedFrom source)
    {
        super(playerNum, playerName, board, source);
    }

    @Override
    public PlayerType getType()
    {
        return PlayerType.HUMAN;
    }
    
    @Override
    public int chooseSoldierToMove()
    {
        // No actual use in HumanPlayer.
        return 0;
    }    
}
