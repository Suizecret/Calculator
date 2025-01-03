import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class Calculator implements ActionListener {

    JFrame frame;
    JLabel label;
    JButton[] numberButtons = new JButton[10];
    JButton[] functionButtons = new JButton[9];
    JButton addButton,subButton,mulButton,divButton;
    JButton decButton,equButton,delButton,clrButton, negButton;
    JPanel panel;

    Font myFont = new Font("DialogInput", Font.BOLD,30);
    double num1 = 0,num2=0,result=0;
    char operator;

    Calculator(){

        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(550,550);
        frame.setLayout(null);
        frame.setResizable(false);

        label = new JLabel();
        label.setBounds(50,25,450,50);
        label.setFont(myFont);
        label.setText("0");

        functionButtons[0] = addButton = new JButton("+");
        functionButtons[1] = subButton = new JButton("-");
        functionButtons[2] = mulButton = new JButton("*");
        functionButtons[3] = divButton = new JButton("/");
        functionButtons[4] = decButton = new JButton(",");
        functionButtons[5] = equButton = new JButton("=");
        functionButtons[6] = delButton = new JButton("Delete");
        functionButtons[7] = clrButton = new JButton("Clear");
        functionButtons[8] = negButton = new JButton("(-)");

        for(JButton button : functionButtons){
            button.addActionListener(this);
            button.setFont(myFont);
            button.setFocusable(false);
        }
        for (int i = 0; i <10; i++){
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
            numberButtons[i].setFont(myFont);
            numberButtons[i].setFocusable(false);
        }

        negButton.setBounds(50,430,110,50);
        delButton.setBounds(175,430,150,50);
        clrButton.setBounds(340,430,150,50);

        clickOnKey(addButton,"add",KeyEvent.VK_ADD,KeyEvent.VK_PLUS);
        clickOnKey(subButton,"add",KeyEvent.VK_SUBTRACT,KeyEvent.VK_MINUS);
        clickOnKey(mulButton,"add",KeyEvent.VK_MULTIPLY);
        clickOnKey(divButton,"add",KeyEvent.VK_DIVIDE);
        clickOnKey(decButton,"add",KeyEvent.VK_DECIMAL,KeyEvent.VK_COMMA);
        clickOnKey(equButton,"add",KeyEvent.VK_EQUALS,KeyEvent.VK_ENTER);
        clickOnKey(delButton,"add",8);

        clickOnKey(numberButtons[0],"0",KeyEvent.VK_NUMPAD0,KeyEvent.VK_0);
        clickOnKey(numberButtons[1],"1",KeyEvent.VK_NUMPAD1,KeyEvent.VK_1);
        clickOnKey(numberButtons[2],"2",KeyEvent.VK_NUMPAD2,KeyEvent.VK_2);
        clickOnKey(numberButtons[3],"3",KeyEvent.VK_NUMPAD3,KeyEvent.VK_3);
        clickOnKey(numberButtons[4],"4",KeyEvent.VK_NUMPAD4,KeyEvent.VK_4);
        clickOnKey(numberButtons[5],"5",KeyEvent.VK_NUMPAD5,KeyEvent.VK_5);
        clickOnKey(numberButtons[6],"6",KeyEvent.VK_NUMPAD6,KeyEvent.VK_6);
        clickOnKey(numberButtons[7],"7",KeyEvent.VK_NUMPAD7,KeyEvent.VK_7);
        clickOnKey(numberButtons[8],"8",KeyEvent.VK_NUMPAD8,KeyEvent.VK_8);
        clickOnKey(numberButtons[9],"9",KeyEvent.VK_NUMPAD9,KeyEvent.VK_9);

        panel = new JPanel();
        panel.setBounds(50,100,450,300);
        panel.setLayout(new GridLayout(4,4,10,10));

        panel.add(numberButtons[1]);
        panel.add(numberButtons[2]);
        panel.add(numberButtons[3]);
        panel.add(addButton);
        panel.add(numberButtons[4]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[6]);
        panel.add(subButton);
        panel.add(numberButtons[7]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[9]);
        panel.add(mulButton);
        panel.add(decButton);
        panel.add(numberButtons[0]);
        panel.add(equButton);
        panel.add(divButton);

        frame.add(panel);
        frame.add(delButton);
        frame.add(negButton);
        frame.add(clrButton);
        frame.add(label);
        frame.setVisible(true);
    }

    public static void clickOnKey(final AbstractButton button, String actionName, int key , int secondKey ) {
        button.getInputMap( JButton.WHEN_IN_FOCUSED_WINDOW )
                .put( KeyStroke.getKeyStroke( secondKey, 0 ), actionName );
        clickOnKey(button,actionName,key);
    }

    public static void clickOnKey(
            final AbstractButton button, String actionName, int key)
    {
        button.getInputMap( JButton.WHEN_IN_FOCUSED_WINDOW )
                .put( KeyStroke.getKeyStroke( key, 0 ), actionName );

        button.getActionMap().put( actionName, new AbstractAction()
        {
            @Override
            public void actionPerformed( ActionEvent e )
            {
                button.doClick();
            }
        } );
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i<10; i++){
            if (e.getSource() == numberButtons[i]){
                label.setText(label.getText().concat(String.valueOf(i)));
                num2 = 0;
            }
        }
        if (!label.getText().isEmpty()) {
            if (e.getSource() == decButton) {
                label.setText(label.getText().concat("."));
            }
            if (e.getSource() == addButton) {
                num1 = Double.parseDouble(label.getText());
                operator = '+';
                label.setText("");
            }
            if (e.getSource() == subButton) {
                num1 = Double.parseDouble(label.getText());
                operator = '-';
                label.setText("");
            }
            if (e.getSource() == mulButton) {
                num1 = Double.parseDouble(label.getText());
                operator = '*';
                label.setText("");
            }
            if (e.getSource() == divButton) {
                num1 = Double.parseDouble(label.getText());
                operator = '/';
                label.setText("");
            }
            if (e.getSource() == equButton) {
                if (num2 == 0){
                    num2 = Double.parseDouble(label.getText());
                }
                switch (operator) {
                    case '+': {
                        result = num1 + num2;
                        break;
                    }
                    case '-': {
                        result = num1 - num2;
                        break;
                    }
                    case '*': {
                        result = num1 * num2;
                        break;
                    }
                    case '/': {
                        if (num2 != 0) {
                            result = num1 / num2;
                        } else {
                            result = 0;
                        }
                        break;
                    }
                }
                label.setText(String.valueOf(result));
                num1 = result;
            }
            if (e.getSource() == clrButton) {
                label.setText("");
            }
            if (e.getSource() == delButton) {
                String freshString = label.getText();
                label.setText(freshString.substring(0, freshString.length() - 1));
            }
            if (e.getSource() == negButton) {
                double temp = Double.parseDouble(label.getText());
                temp *= -1;
                label.setText(String.valueOf(temp));
            }
        }
    }
}

class Main {
    public static void main(String[] args) {
        Calculator calc = new Calculator();
    }
}