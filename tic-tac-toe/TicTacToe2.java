import java.util.*;



public class TicTacToe2 {
    public static void main(String[] args) {
        Board board = new Board(3);
        Game game = new Game(board);

        game.playGame();
    }
}

enum Symbol {
    X, O, EMPTY;
}

class Player {
    private String playerName;
    private Symbol playerSymbol;

    public Player(String playerName, Symbol playerSymbol) {
        this.playerName = playerName;
        this.playerSymbol = playerSymbol;
    }

    public String getPlayerName() {
        return playerName;
    }

    public Symbol getPlayerSymbol() {
        return playerSymbol;
    }
}

interface WinStrategy {
    boolean checkWin(Symbol symbol, Board board);
}

class RowWinStrategy implements WinStrategy {
    @Override
    public boolean checkWin(Symbol symbol, Board board) {
        int size = board.getSize();
        boolean isWin = true;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board.getBoardCell(i, j) != symbol) {
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
    public boolean checkWin(Symbol symbol, Board board) {
        int size = board.getSize();
        boolean isWin = true;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board.getBoardCell(j, i) != symbol) {
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
    public boolean checkWin(Symbol symbol, Board board) {
        int size = board.getSize();
        boolean isWin = true;
        for (int i = 0; i < size; i++) {
            if (board.getBoardCell(i, i) != symbol || board.getBoardCell(size - i - 1, i) != symbol)
                return false;
        }
        return isWin;
    }
}

class Board {
    private int size;
    private Symbol board[][];
    private List<WinStrategy> strategies;

    public Board(int size) {
        this.size = size;
        board = new Symbol[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = Symbol.EMPTY;
            }
        }
        strategies.add(new RowWinStrategy());
        strategies.add(new DiagonalWinStrategy());
        strategies.add(new ColumnWinStrategy());
    }

    public int getSize() {
        return size;
    }

    public Symbol getBoardCell(int row, int col) {
        return board[row][col];
    }

    public boolean checkWin(Symbol symbol) {
        for (WinStrategy strategy : strategies) {
            if (strategy.checkWin(symbol, this))
                return true;
        }
        return false;
    }

    public boolean isValidMove(int row, int col) {
        return board[row][col] == Symbol.EMPTY;
    }

    public void displayBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                System.out.print(" [ " + board[i][j] + " ] ");
            }
            System.out.println();
        }
    }

    public boolean playMove(int row, int col, Symbol symbol) {
        if (!isValidMove(row, col))
            return false;
        board[row][col] = symbol;
        return true;
    }

    public boolean checkDraw() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] == Symbol.EMPTY)
                    return false;
            }
        }
        return true;
    }

}

enum GAME_STATUS {
    WIN, IN_PROGRESS, DRAW
}

class Game {
    Queue<Player> players = new ArrayDeque<>();
    Board board;
    private Player winner;
    GAME_STATUS status;

    public Game(Board board) {
        this.board = board;
        status = GAME_STATUS.IN_PROGRESS;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public Player getCurrPlayer() {
        return players.element();
    }

    public void switchTurn() {
        Player currentPlayer = players.poll();
        players.add(currentPlayer);
    }

    public boolean makeMove(int row, int col) {
        Player currentPlayer = getCurrPlayer();
        Symbol currentPlayerSumbol = currentPlayer.getPlayerSymbol();

        if (board.getBoardCell(row, col) != Symbol.EMPTY)
            return false;

        board.playMove(row, col, currentPlayerSumbol);

        if (board.checkWin(currentPlayerSumbol)) {
            winner = currentPlayer;
            status = GAME_STATUS.WIN;
        }

        if (board.checkDraw()) {
            status = GAME_STATUS.DRAW;
        }

        switchTurn();

        return true;
    }

    public boolean isGameOver() {
        return status == GAME_STATUS.IN_PROGRESS;
    }

    public Player getWinner() {
        return winner;
    }

    public void printBoard() {
        board.displayBoard();
    }

    public void playGame() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Player 1 name : ");
        String playerOneName = scanner.next();
        System.out.print("Player 2 name : ");
        String playerTwoName = scanner.next();

        Player p1 = new Player(playerOneName, Symbol.O);
        Player p2 = new Player(playerTwoName, Symbol.X);

        addPlayer(p1);
        addPlayer(p2);

        while (status == GAME_STATUS.IN_PROGRESS) {
            printBoard();

            Player currentPlayer = getCurrPlayer();

            System.out.println(
                    currentPlayer.getPlayerName() + " (" + currentPlayer.getPlayerSymbol()
                            + ") - Enter row and column:");

            int row = scanner.nextInt();
            int col = scanner.nextInt();

            boolean move = makeMove(row, col);

            if (!move) {
                System.out.println("Invalid move. Try again.");
            }

        }

        if (status == GAME_STATUS.WIN) {
            System.out.println("Winner is: " + getWinner().getPlayerName());
        }

        if (status == GAME_STATUS.DRAW) {
            System.out.println("Winner is: " + getWinner().getPlayerName());
        }
        scanner.close();
    }

}
