package com.codebind;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/* ------- KALKULATOR ------
 * jeśli użytkownik wciśnie operator kilka razy z rzędu,
 * program uznaje, że zmienił zdanie i nadpisuje operator na ostatni wciśnięty,
 * czekając aż użytkownik wpisze jakąś liczbę
 */
public class CalculatorGUI {
    private double total1;
    private String prevOperator;

    private JPanel panel1;
    private JButton btnDivide;
    private JButton btn2;
    private JButton btn3;
    private JButton btn5;
    private JButton btn8;
    private JButton btnPlus;
    private JButton btn6;
    private JButton btn9;
    private JButton btnMinus;
    private JButton btnMultiply;
    private JButton btnEquals;
    private JButton btn1;
    private JButton btn4;
    private JButton btn7;
    private JButton btn0;
    private JButton btnC;
    private JButton btnDecimal;
    private JTextField txtField1;
    private JTextField txtField2;

    // wykonuje poprzednio dodaną operację
    private void performOperation(String oper) {
        try {
            switch (oper) {
                case "+":
                    total1 += Double.parseDouble(txtField1.getText());
                    break;
                case "-":
                    total1 -= Double.parseDouble(txtField1.getText());
                    break;
                case "/":
                    // nie trzeba obsługiwać wyjątku dzielenia przez 0 bo jest double
                    total1 /= Double.parseDouble(txtField1.getText());
                    break;
                case "*":
                    total1 *= Double.parseDouble(txtField1.getText());
                    break;
                case "=":
                    txtField1.setText(Double.toString(total1));
                    break;
                default:
                    total1 = Double.parseDouble(txtField1.getText());
                    break;
            }
            txtField1.setText("");
            txtField2.setText(Double.toString(total1));
        }
        catch (NumberFormatException e) {
            prevOperator = "=";

            // to znaczy, że po poprzednim operatorze nie był wpisany żadny tekst,
            // tylko kolejny operator */+/... lub =
            // program nie wyrzuca błędu, tylko nadpisuje operator na "="
            // i czeka aż użytkownik wpisze liczbę
            // również mogła być wpisana zła liczba typu "1..2" - tu też ignoruje i czeka na prawidłowy input
        }
    }

    private void setPreviousOper(String oper) {
        prevOperator = oper;
    }

    public CalculatorGUI() {
        this.total1 = 0;
        this.prevOperator = "0"; // cokolwiek różne od "+"/"-"/...

        // przyciski 0 - 9 : nie wiem, jak uniknąć redundancji
        // chiałoby się załatwić wszystkie jednym action listenerem...
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtField1.setText(txtField1.getText() + btn1.getText());
            }
        });
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtField1.setText( txtField1.getText() + btn2.getText());
            }
        });
        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtField1.setText(txtField1.getText() + btn3.getText());
            }
        });
        btn4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtField1.setText(txtField1.getText() + btn4.getText());
            }
        });
        btn5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtField1.setText(txtField1.getText() + btn5.getText());
            }
        });
        btn6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtField1.setText(txtField1.getText() + btn6.getText());
            }
        });
        btn7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtField1.setText(txtField1.getText() + btn7.getText());
            }
        });
        btn8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtField1.setText(txtField1.getText() + btn8.getText());
            }
        });
        btn9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtField1.setText(txtField1.getText() + btn9.getText());
            }
        });
        btn0.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtField1.setText(txtField1.getText() + btn0.getText());
            }
        });

        btnDecimal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtField1.setText(txtField1.getText() + btnDecimal.getText());
            }
        });

        // operatory:
        btnPlus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performOperation(prevOperator);
                setPreviousOper(btnPlus.getText());
            }
        });
        btnMinus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performOperation(prevOperator);
                setPreviousOper(btnMinus.getText());
            }
        });
        btnMultiply.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performOperation(prevOperator);
                setPreviousOper(btnMultiply.getText());
            }
        });
        btnDivide.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performOperation(prevOperator);
                setPreviousOper(btnDivide.getText());
            }
        });
        btnEquals.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performOperation(prevOperator);
                setPreviousOper("0"); // żeby wskoczyło do default
            }
        });

        btnC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                total1 = 0.0;
                txtField1.setText("");
                txtField2.setText("");
                setPreviousOper("0"); // żeby wskoczyło do default
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Calculator");
        frame.setContentPane(new CalculatorGUI().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        CalculatorGUI calc = new CalculatorGUI();
    }
}
