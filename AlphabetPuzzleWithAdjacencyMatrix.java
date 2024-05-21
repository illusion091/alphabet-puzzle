import java.awt.*;
import java.awt.event.*;
import javax.swing.JOptionPane;

public class AlphabetPuzzleWithAdjacencyMatrix extends Frame implements ActionListener {
    Button[] buttons;
    char[] alphabet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', ' '};
    boolean[][] adjacencyMatrix = {
        //   A    B    C    D    E    F    G    H    I
        {false, true, false, false, false, false, false, false, false}, // A
        {true, false, true, false, false, false, false, false, false}, // B
        {false, true, false, true, false, false, false, false, false}, // C
        {false, false, true, false, true, false, false, false, false}, // D
        {false, false, false, true, false, true, false, false, false}, // E
        {false, false, false, false, true, false, true, false, false}, // F
        {false, false, false, false, false, true, false, true, false}, // G
        {false, false, false, false, false, false, true, false, true}, // H
        {false, false, false, false, false, false, false, true, false}  // I
    };

    AlphabetPuzzleWithAdjacencyMatrix() {
        super("Alphabet Puzzle Game");
        buttons = new Button[9];

        for (int i = 0; i < 9; i++) {
            buttons[i] = new Button(Character.toString(alphabet[i]));
            buttons[i].setBounds(50 + (i % 3) * 50, 100 + (i / 3) * 50, 40, 40);
            buttons[i].addActionListener(this);
            add(buttons[i]);
        }

        setSize(200, 250);
        setLayout(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        Button clickedButton = (Button) e.getSource();
        String label = clickedButton.getLabel();
        int emptyIndex = -1;
        int clickedIndex = -1;

        // Find the index of the empty space and clicked button
        for (int i = 0; i < 9; i++) {
            if (alphabet[i] == ' ') {
                emptyIndex = i;
            }
            if (label.charAt(0) == alphabet[i]) {
                clickedIndex = i;
            }
        }

        // Check if the clicked button is adjacent to the empty space
        if (adjacencyMatrix[emptyIndex][clickedIndex]) {
            alphabet[emptyIndex] = label.charAt(0);
            alphabet[clickedIndex] = ' ';
            buttons[emptyIndex].setLabel(Character.toString(alphabet[emptyIndex]));
            buttons[clickedIndex].setLabel(Character.toString(alphabet[clickedIndex]));
        }

        // Check if the puzzle is solved
        if (isPuzzleSolved()) {
            JOptionPane.showMessageDialog(this, "Congratulations! You solved the puzzle.");
        }
    }

    private boolean isPuzzleSolved() {
        for (int i = 0; i < 8; i++) {
            if (alphabet[i] != (char) ('A' + i)) {
                return false;
            }
        }
        return alphabet[8] == ' ';
    }

    public static void main(String[] args) {
        new AlphabetPuzzleWithAdjacencyMatrix();
    }
}