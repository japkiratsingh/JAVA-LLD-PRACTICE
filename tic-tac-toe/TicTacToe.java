import java.util.*;

//Game 
//Board

enum Symbol {
    X, O, EMPTY
}

class Player {
    String playerName;
    Symbol playerSymbol;

    Player(String playerName, Symbol playerSymbol) {
        this.playerName = playerName;
        this.playerSymbol = playerSymbol;
    }
}

interface WinStrategy {
    public boolean checkWin(Board board, Symbol symbol);
}

class RowWinStrategy implements WinStrategy {

    @Override
    public boolean checkWin(Board board, Symbol symbol) {
        boolean isWin = true;
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                if (board.getCell(i, j) != symbol) {
                    isWin = false;
                }
            }
            if (isWin)
                return true;
        }
        return isWin;
    }

}

class ColumnWinStrategy implements WinStrategy {
    @Override
    public boolean checkWin(Board board, Symbol symbol) {
        boolean isWin = true;
        for (int i = 0; i < board.getSize(); i++) {
            for (int j = 0; j < board.getSize(); j++) {
                if (board.getCell(j, i) != symbol) {
                    isWin = false;
                }
            }
            if (isWin)
                return true;
        }
        return isWin;
    }
}

class DiagonalWinStrategy implements WinStrategy {
    @Override
    public boolean checkWin(Board board, Symbol symbol) {
        boolean isWin = true;
        for (int i = 0; i < board.getSize(); i++) {
            if (board.getCell(i, i) != symbol || board.getCell(i, board.getSize() - 1 - i) != symbol) {
                isWin = false;
            }
        }
        return isWin;
    }
}

class Board {
    private int size;
    private Symbol[][] board;
    private List<WinStrategy> winStrategies;

    public Board(int size) {
        this.size = size;
        board = new Symbol[size][size];
        winStrategies = new ArrayList<>();
        winStrategies.add(new RowWinStrategy());
        winStrategies.add(new ColumnWinStrategy());
        winStrategies.add(new DiagonalWinStrategy());
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = Symbol.EMPTY;
            }
        }
    }

    public void printBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void updateBoard(int row, int col, Symbol symbol) {
        board[row][col] = symbol;
    }

    public boolean isBoardFull() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] == Symbol.EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean checkWin(Symbol symbol) {
        for (WinStrategy winStrategy : winStrategies) {
            if (winStrategy.checkWin(this, symbol)) {
                return true;
            }
        }
        return false;
    }

    public Symbol getCell(int row, int col) {
        return board[row][col];
    }

    public void setCell(int row, int col, Symbol symbol) {
        board[row][col] = symbol;
    }

    public int getSize() {
        return size;
    }
}

class Game {
    private Board board;
    private Deque<Player> players;
    private Player winner;

    public Game(int boardSize, Player p1, Player p2) {
        this.board = new Board(boardSize);
        this.players = new ArrayDeque<>();
        players.addLast(p1);
        players.addLast(p2);
    }

    public Player getCurrentPlayer() {
        return players.peekFirst();
    }

    public void switchTurn() {
        Player current = players.pollFirst();
        players.offerLast(current);
    }

    public boolean makeMove(int row, int col) {
        Player currentPlayer = getCurrentPlayer();

        if (board.getCell(row, col) != Symbol.EMPTY) {
            return false; // invalid move
        }

        board.setCell(row, col, currentPlayer.playerSymbol);

        if (board.checkWin(currentPlayer.playerSymbol)) {
            winner = currentPlayer;
            return true;
        }

        switchTurn();
        return true;
    }

    public boolean isGameOver() {
        return winner != null || board.isBoardFull();
    }

    public Player getWinner() {
        return winner;
    }

    public void printBoard() {
        board.printBoard();
    }
}


public class TicTacToe {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Player p1 = new Player("Player-1", Symbol.X);
        Player p2 = new Player("Player-2", Symbol.O);

        Game game = new Game(3, p1, p2);

        while (!game.isGameOver()) {
            game.printBoard();

            Player current = game.getCurrentPlayer();
            System.out.println(
                current.playerName + " (" + current.playerSymbol + ") - Enter row and column:"
            );

            int row = sc.nextInt();
            int col = sc.nextInt();

            boolean moveSuccess = game.makeMove(row, col);

            if (!moveSuccess) {
                System.out.println("Invalid move. Try again.");
            }
        }

        game.printBoard();

        if (game.getWinner() != null) {
            System.out.println("Winner is: " + game.getWinner().playerName);
        } else {
            System.out.println("Game Draw!");
        }

        sc.close();
    }
}
