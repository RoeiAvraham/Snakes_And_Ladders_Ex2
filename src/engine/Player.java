/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Engine;

import java.util.Random;

/**
 *
 * @author Anat
 */
public abstract class Player
{
    public static final int NUM_SOLDIERS = 4;
    public static final int COMP_PLAYER_SOLDIER = 0;
    private static final int DICE_VALUE = 6;
    public final int FIRST_CELL = 1;
    private int m_playerNum;
    private String m_playerName;
    protected int[] m_soldiersPos;
    private GameBoard m_board;
    private static final int EMPTY = 0;

    public enum LoadedFrom
    {
        REG, XML
    }

    public enum PlayerType
    {
        COMP, HUMAN
    }

    public int[] getSoldiersPos()
    {
        return m_soldiersPos;
    }

    public Player(final int playerNum, final String playerName, GameBoard board, LoadedFrom source)
    {
        m_playerNum = playerNum;
        m_playerName = playerName;
        m_soldiersPos = new int[NUM_SOLDIERS];
        m_board = board;

        int i;

        if (source == LoadedFrom.REG)
        {
            for (i = 0; i < NUM_SOLDIERS; i++)
            {
                m_soldiersPos[i] = FIRST_CELL;
            }
        }
        else if (source == LoadedFrom.XML)
        {
            for (i = 0; i < NUM_SOLDIERS; i++)
            {
                m_soldiersPos[i] = EMPTY;
            }
        }
    }

    /**
     * @return the m_playerName
     */
    public final String getPlayerName()
    {
        return m_playerName;
    }

    public abstract int chooseSoldierToMove();

    public TurnData playTurn(int soldierNum)
    {
        TurnData turnData;
        if (soldierNum == COMP_PLAYER_SOLDIER)
        {
            // CompPlayer decides on it's own, which soldier to move.
            soldierNum = chooseSoldierToMove();
            turnData = move(soldierNum);
        }
        else
        {
            // HumanPlayer gets soldier to move from Controller.
            turnData = move(soldierNum);
        }

        return turnData;
    }

    public void removePlayerSoldiersFromGame()
    {
        int i;
        for (i=0;i<NUM_SOLDIERS;i++)
        {
           m_board.getCell(m_soldiersPos[i]).removeSoldier(m_playerNum);
        }        
    }
    public final int calcDestCellNum(final int diceRes, final int soldierNum)
    {
        int currCell = m_soldiersPos[soldierNum - 1];
        int destCell = currCell + diceRes;
        if (destCell > m_board.getLastCellNum())
        {
            destCell = m_board.getLastCellNum();
        }
        int finalDest = m_board.getCell(destCell).getDest();
        if (finalDest != Cell.NO_DEST)
        {
            destCell = finalDest;
        }

        return destCell;
    }

    public TurnData move(final int soldierNum)
    {
        int diceRes = throwDice();
        int destCell = calcDestCellNum(diceRes, soldierNum);

        m_board.getCell(m_soldiersPos[soldierNum - 1]).removeSoldier(m_playerNum);
        m_board.getCell(destCell).insertSoldier(m_playerNum);
        m_soldiersPos[soldierNum - 1] = destCell;

        return (new TurnData(diceRes, soldierNum, destCell));
    }

    public abstract PlayerType getType();

    public final int throwDice()
    {
        Random r = new Random();
        return (r.nextInt(DICE_VALUE) + 1);
    }

    public int getPlayerNum()
    {
        return m_playerNum;
    }
}