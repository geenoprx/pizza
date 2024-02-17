import javax.swing.SwingUtilities;

public class GameController {
    private game_cooking game;

    public GameController(game_cooking game) {
        this.game = game;
        
    }

    public void restartGame() {
        SwingUtilities.invokeLater(() -> {
            game.startGame();
        });
    }    

}
