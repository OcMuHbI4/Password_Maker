import javax.swing.*;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class MainWindowClass  extends JFrame implements ItemListener
{
     static Object [] columnName = new Object[1];
     static Object [][] rowsWithGeneratedPasswords = new Object[5][1];
     static JTable passwordsTextArea;
     static JCheckBox numbersCheckBox = new JCheckBox("Numbers");
     static JCheckBox lettersCheckBox = new JCheckBox("Small Letters");
     static JCheckBox bigLettersCheckBox = new JCheckBox("Big Letters");
     static JCheckBox specSymbolsCheckBox = new JCheckBox("Special Symbols");
     static JPanel checkBoxesPanel = new JPanel(new GridLayout(5,1));
     static JPanel passwordsTextAreasPanel = new JPanel(new GridLayout(2,1));
     static JFrame mainFrame = new JFrame("Passwords Generator");
     static JLabel quantityOfSymbolsLabel = new JLabel("Quantity of Symbols");
     static JLabel notMoreSixtyFourLabel = new JLabel("Not more than 64");
     static JLabel errorLabel = new JLabel();
     static JTextField quantityOfSymbolsTextField = new JTextField();
     static JButton generateButton = new JButton("Generate Passwords");


    MainWindowClass()
    {
        columnName[0] = "passwords";

        for (int i = 0; i < 5; i++) {
            rowsWithGeneratedPasswords[i][0] = "";
        }

        passwordsTextArea = new JTable(rowsWithGeneratedPasswords,columnName);
        TableColumnModel columnModel = passwordsTextArea.getColumnModel();

        passwordsTextArea.setShowGrid(false);

        generateButton.addActionListener(e -> PasswordGeneration.GeneratePasswords());

        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        mainFrame.setSize(200,200);
        mainFrame.setResizable(false);

        numbersCheckBox.addItemListener(this);
        lettersCheckBox.addItemListener(this);
        bigLettersCheckBox.addItemListener(this);
        specSymbolsCheckBox.addItemListener(this);

        checkBoxesPanel.add(numbersCheckBox);
        checkBoxesPanel.add(lettersCheckBox);
        checkBoxesPanel.add(bigLettersCheckBox);
        checkBoxesPanel.add(specSymbolsCheckBox);
        checkBoxesPanel.add(quantityOfSymbolsLabel);
        checkBoxesPanel.add(quantityOfSymbolsTextField);
        checkBoxesPanel.add(notMoreSixtyFourLabel);
        checkBoxesPanel.add(errorLabel);

        columnModel.getColumn(0).setPreferredWidth(500);

        passwordsTextAreasPanel.add(passwordsTextArea);
        passwordsTextAreasPanel.add(generateButton);

        mainFrame.setLayout(new GridLayout(1,2,4,0));

        mainFrame.add(checkBoxesPanel);
        mainFrame.add(passwordsTextAreasPanel);

        mainFrame.pack();
        mainFrame.setVisible(true);
        mainFrame.setLocationRelativeTo(null);

    }

    @Override
    public void itemStateChanged(ItemEvent e) {

        if (e.getSource() == numbersCheckBox) {
            PasswordGeneration.numbers = e.getStateChange() == ItemEvent.SELECTED;
        }

        if (e.getSource() == lettersCheckBox) {
            PasswordGeneration.letters = e.getStateChange() == ItemEvent.SELECTED;
        }

        if (e.getSource() == bigLettersCheckBox) {
            PasswordGeneration.bigLetters = e.getStateChange() == ItemEvent.SELECTED;
        }

        if (e.getSource() == specSymbolsCheckBox) {
            PasswordGeneration.specSymbols = e.getStateChange() == ItemEvent.SELECTED;
        }

    }
}
