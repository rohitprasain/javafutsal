package calculator;


import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.Stack;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Calculator extends JFrame {

    private GridBagConstraints gbc(
            int row, int column,
            int rowSpan, int columnSpan) {
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = column;
        c.gridy = row;
        c.insets = new Insets(5, 5, 5, 5);
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1;
        c.weighty = 1;
        c.gridwidth = columnSpan;
        c.gridheight = rowSpan;
        
        return c;
    }

    private GridBagConstraints gbc(int row, int column) {
        return gbc(row, column, 1, 1);
    }

    private JLabel numberLabel;
    private Stack<Double> stack = new Stack<>();
    private String lastOperator = "+";
    private boolean lastCharacterWasNumber = false;

    public Calculator() {
        stack.push(0.0);

        setLayout(new GridBagLayout());
    

        numberLabel = new JLabel("0", JLabel.RIGHT);
        add(numberLabel, gbc(0, 0, 1, 5));

        JButton deleteButton = new JButton("DEL");
        add(deleteButton, gbc(1, 0));
        JButton ceButton = new JButton("CE");
        add(ceButton, gbc(1, 1));
        JButton cButton = new JButton("C");
        add(cButton, gbc(1, 2));
        JButton plusMinusButton = new JButton("+/-");
        add(plusMinusButton, gbc(1, 3));
        JButton sqrtButton = new JButton("SQRT");
        add(sqrtButton, gbc(1, 4));

        JButton squareButton = new JButton("x^2");
        add(squareButton, gbc(2, 0));
        JButton exponentialButton = new JButton("ln");
        add(exponentialButton, gbc(2, 1));
        JButton sinButton = new JButton("sin");
        add(sinButton, gbc(2, 2));
        JButton cosButton = new JButton("cos");
        add(cosButton, gbc(2, 3));
        JButton tanButton = new JButton("tan");
        add(tanButton, gbc(2, 4));

        JButton sevenButton = new JButton("7");
        add(sevenButton, gbc(3, 0));
        JButton eightButton = new JButton("8");
        add(eightButton, gbc(3, 1));
        JButton nineButton = new JButton("9");
        add(nineButton, gbc(3, 2));
        JButton divideButton = new JButton("/");
        add(divideButton, gbc(3, 3));
        JButton percentButton = new JButton("%");
        add(percentButton, gbc(3, 4));

        JButton fourButton = new JButton("4");
        add(fourButton, gbc(4, 0));
        JButton fiveButton = new JButton("5");
        add(fiveButton, gbc(4, 1));
        JButton sixButton = new JButton("6");
        add(sixButton, gbc(4, 2));
        JButton multiplyButton = new JButton("*");
        add(multiplyButton, gbc(4, 3));
        JButton inverseButton = new JButton("1/x");
        add(inverseButton, gbc(4, 4));

        JButton oneButton = new JButton("1");
        add(oneButton, gbc(5, 0));
        JButton twoButton = new JButton("2");
        add(twoButton, gbc(5, 1));
        JButton threeButton = new JButton("3");
        add(threeButton, gbc(5, 2));
        JButton minusButton = new JButton("-");
        add(minusButton, gbc(5, 3));
        JButton equalsButton = new JButton("=");
        add(equalsButton, gbc(5, 4, 2, 1));

        JButton zeroButton = new JButton("0");
        add(zeroButton, gbc(6, 0, 1, 2));
        JButton decimalButton = new JButton(".");
        add(decimalButton, gbc(6, 2));
        JButton plusButton = new JButton("+");
        add(plusButton, gbc(6, 3));
        
       
        

        for (Component c : getContentPane().getComponents()) {
            if (c instanceof JButton) {
                JButton button = (JButton) c;
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        buttonClicked(button);
                    }
                });
            }
        }
    }

    private void buttonClicked(JButton button) {
        String text = button.getText();
        switch (text) {
            case "0":
            case "1":
            case "2":
            case "3":
            case "4":
            case "5":
            case "6":
            case "7":
            case "8":
            case "9":
            case ".":
                handleNumbers(text);
                break;
            case "SQRT":
                handleSQRT();
                break;
            case "x^2":
                handlesquare();
                break;
            case "ln":
                handleln();
                break;
            
            case "1/x":
                handleInverse();
                break;
            case "+/-":
                handlePlusMinus();
                break;
            case "DEL":
                handleDelete();
                break;
            case "CE":
                handleClearError();
                break;
            case "C":
                handleClear();
                break;
            case"sin":
                handleSin();
                break;
            case"cos":
                handleCos();
                break;
            case"tan":
               handleTan();
              break;
            case "+":
            case "-":
            case "*":
            case "/":
            case "=":

                handleArithmeticOperator(text);
                break;
        }
    }

    private void handleClear() {
        stack.clear();
        stack.add(0.0);
        lastCharacterWasNumber = false;
        lastOperator = "+";
        numberLabel.setText("0");
    }

    private void handleClearError() {
        if (!lastCharacterWasNumber) {
            return;
        }
        numberLabel.setText("0");

        lastCharacterWasNumber = false;
    }

    private void handleNumbers(String newText) {
        if (!lastCharacterWasNumber) {
            numberLabel.setText("0");
        }

        lastCharacterWasNumber = true;

        String oldText = numberLabel.getText();
        if (oldText.equals("0") && !newText.equals(".")) {
            oldText = "";
        }
        numberLabel.setText(oldText + newText);
    }

    private void handleSQRT() {
        double number = Double.parseDouble(
                numberLabel.getText());
        double sqrt = Math.sqrt(number);
        numberLabel.setText(String.valueOf(sqrt));
    }

    private void handleInverse() {
        double number = Double.parseDouble(
                numberLabel.getText());
        double inverse = 1 / number;
        numberLabel.setText(String.valueOf(inverse));
    }

    private void handlePlusMinus() {
        String text = numberLabel.getText();
        if (text.equals("0")) {
            return;
        }

        if (text.charAt(0) != '-') {
            numberLabel.setText("-" + text);
        } else {
            numberLabel.setText(text.substring(1));
        }
    }

    private void handleDelete() {
        String text = numberLabel.getText();
        if (text.length() == 1) {
            numberLabel.setText("0");
            return;
        }
        if (text.length() == 2 && text.charAt(0) == '-') {
            numberLabel.setText("0");
            return;
        }
        numberLabel.setText(
                text.substring(0, text.length() - 1));
    }

    private void handleArithmeticOperator(String operator) {
        if (!lastCharacterWasNumber) {
            lastOperator = operator;
            return;
        }

        lastCharacterWasNumber = false;

        double operand1 = stack.pop();
        double operand2 = Double.parseDouble(
                numberLabel.getText());
        double result = performArithmeticOperation(
                operand1, lastOperator, operand2);

        DecimalFormat df = new DecimalFormat("0.#");
        numberLabel.setText(df.format(result));

        stack.push(result);
        if (!operator.equals("=")) {
            lastOperator = operator;
        }
    }

    private void handlesquare() {
        double number = Double.parseDouble(
                numberLabel.getText());
        double square = Math.pow(number,2);
        numberLabel.setText(String.valueOf(square));

    }
     private void handleln() {
          double number = Double.parseDouble(
                numberLabel.getText());
        double ln= Math.log(number);
        numberLabel.setText(String.valueOf(ln));   
    }
    private void handleSin() {
          double number = Double.parseDouble(
               numberLabel.getText());
        double sin= Math.sin(number);
        numberLabel.setText(String.valueOf(sin));  
     } 
    private void handleCos() {
          double number = Double.parseDouble(
                numberLabel.getText());
        double cos= Math.cos(number);
        numberLabel.setText(String.valueOf(cos));   
    } 
    private void handleTan() {
          double number = Double.parseDouble(
                numberLabel.getText());
        double tan= Math.tan(number);
        numberLabel.setText(String.valueOf(tan)); 
      
    }  
    private double performArithmeticOperation(double operand1, String operator,double operand2) {
        switch (operator) {
            case "+":
                return operand1 + operand2;
            case "-":
                return operand1 - operand2;
            case "*":
                return operand1 * operand2;
            case "/":
                return operand1 / operand2;
            default:
                return 0;
        }
    }
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        calculator.pack();
        calculator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        calculator.setVisible(true);
        calculator.setTitle("Calculator");
        
 
      
    }
}
