package app.form.Calcolatrice;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

public class Calcolatrice {
    private JPanel JPanel;
    private JButton btn7;
    private JButton btn8;
    private JButton btn9;
    private JButton btn6;
    private JButton btn4;
    private JButton btn5;
    private JButton btn1;
    private JButton btn2;
    private JButton btn3;
    private JButton btn0;
    private JButton btnUguale;
    private JButton btnDivisione;
    private JButton btnSottrazione;
    private JButton btnMoltiplicazione;
    private JButton btnSomma;
    private JLabel labelStampa;
    private JButton btnClear;
    private JButton btnRPN;
    private JLabel labelrisultato;
    private JButton btnParentesichiusa;
    private JButton btnParentesiAperta;
    private JPanel risultato;
    private JRadioButton rdbNormale;
    private JRadioButton rdbRPN;



    public Calcolatrice() {

        // creo gli eventi per tutti gli oggetti presenti nel JPanel
        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            labelrisultato.setText("");
            labelStampa.setText("");
            }
        });
        btn7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PressionePulsanteNumerico("7");
            }
        });
        btn8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PressionePulsanteNumerico("8");
            }
        });
        btn9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PressionePulsanteNumerico("9");
            }
        });
        btn4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PressionePulsanteNumerico("4");
            }
        });
        btn5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PressionePulsanteNumerico("5");
            }
        });
        btn6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PressionePulsanteNumerico("6");
            }
        });
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PressionePulsanteNumerico("1");
            }
        });
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PressionePulsanteNumerico("2");
            }
        });
        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PressionePulsanteNumerico("3");
            }
        });
        btnMoltiplicazione.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PressioneOperatore("*");
            }
        });
        btnDivisione.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PressioneOperatore("/");
            }
        });
        btnSottrazione.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PressioneOperatore("-");
            }
        });
        btnSomma.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PressioneOperatore("+");
            }
        });
        btnUguale.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PressioneOperatore("=");

               labelrisultato.setText(CalcolaRPN(TraduciInRPN(labelStampa.getText().trim()).toString().trim()));
            }
        });

        btn0.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PressionePulsanteNumerico("0");
            }
        });
        btnRPN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // creo una stringa con gli spazi tra ogni carattere
                String input = labelStampa.getText();
                StringBuilder stringaspaziata = new StringBuilder();
                for (int i = 0; i < input.length(); i++) {
                    stringaspaziata.append(input.charAt(i));
                    if (i < input.length() - 1) {
                        stringaspaziata.append(" ");
                    }
                }
                labelStampa.setText(stringaspaziata.toString());
                labelrisultato.setText(CalcolaRPN(labelStampa.getText().trim()));
            }
        });
        btnParentesiAperta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PressioneOperatore("(");
            }
        });
        btnParentesichiusa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PressioneOperatore(")");
            }
        });
        rdbRPN.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(rdbNormale.isSelected()){
                    btnRPN.setEnabled(false);
                    btnUguale.setEnabled((true));
                }
                else if(rdbRPN.isSelected()){
                    btnUguale.setEnabled((false));
                    btnRPN.setEnabled((true));
                }
            }
        });
        rdbNormale.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(rdbNormale.isSelected()){
                    btnRPN.setEnabled(false);
                    btnUguale.setEnabled((true));
                }
                else if(rdbRPN.isSelected()){
                    btnUguale.setEnabled((false));
                    btnRPN.setEnabled((true));
                }
            }
        });

        if (labelStampa.getText().isEmpty()) {
            // La labelStampa è vuota, quindi disabilito i tasti RPN ed Uguale
            btnRPN.setEnabled(false);
            btnUguale.setEnabled(false);
        }

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Calcolatrice");
        frame.setContentPane(new Calcolatrice().JPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }

    private void PressionePulsanteNumerico(String numero) {
        // Aggiungo il testo alla label
        labelStampa.setText(labelStampa.getText() + numero);
    }

    private void PressioneOperatore(String operatore) {

        labelStampa.setText(labelStampa.getText() + operatore);
    }

    public StringBuilder TraduciInRPN(String not_infissa) {
        StringBuilder notazione_rpn = new StringBuilder();
        Stack<Character> stack_operatori = new Stack<>();

        for (int i = 0; i < not_infissa.length(); i++) {
            char elemento = not_infissa.charAt(i);

            if (Character.isDigit(elemento)) { // controllo se è un numero e se lo è lo aggiungo stringa
                notazione_rpn.append(elemento);

                while (i + 1 < not_infissa.length() && (Character.isDigit(not_infissa.charAt(i + 1)))) { // controllo se ci sono cifre consecutive
                    i++;
                    notazione_rpn.append(not_infissa.charAt(i));
                }
                notazione_rpn.append(" "); // spazio i caratteri
            } else if (elemento == '(') {
                stack_operatori.push(elemento); // se il carattere è parentesi aperta aggiungo allo stack
            } else if (elemento == ')') {
                while (!stack_operatori.isEmpty() && stack_operatori.peek() != '(') { // se è parentesi chiusa invece estraggo gli operatori dallo stack fino a trovare la parentesi aperta
                    notazione_rpn.append(stack_operatori.pop()).append(" ");
                }
                stack_operatori.pop();
            } else if (elemento == '+' || elemento == '-') { // gestico la precedenza dei segni
                while (!stack_operatori.isEmpty() && (stack_operatori.peek() == '+' || stack_operatori.peek() == '-' || stack_operatori.peek() == '*' || stack_operatori.peek() == '/')) {
                    notazione_rpn.append(stack_operatori.pop()).append(" ");
                }
                stack_operatori.push(elemento);
            } else if (elemento == '*' || elemento == '/') {
                while (!stack_operatori.isEmpty() && (stack_operatori.peek() == '*' || stack_operatori.peek() == '/')) {
                    notazione_rpn.append(stack_operatori.pop()).append(" ");
                }
                stack_operatori.push(elemento);
            }
        }

        while (!stack_operatori.isEmpty()) { // estraggo tutti gli operatori rimanenti nello stack
            notazione_rpn.append(stack_operatori.pop()).append(" ");
        }

        return notazione_rpn;
    }



    public String CalcolaRPN(String not_postfissa){
        Stack<String> stack = new Stack<>(); // memorizzo gli operandi
        String[] elementi = not_postfissa.split("\\s+"); // suddivido la stringa usando gli spazi come elementi separatori

        for (String elemento : elementi) {
            if (VerificaNumero(elemento)) {
                stack.push(elemento); // se l'elememto è un numero lo aggiungo allo stack
            } else if (VerificaOperatore2(elemento)) {
                String operando2 = stack.pop(); // se l'elemento è un operatore estraggo 2 operandi e faccio l'operazione
                String operando1 = stack.pop();
                Double result = Calcolo(Double.parseDouble(operando1), Double.parseDouble(operando2), elemento);
                stack.push(String.valueOf(result)); // aggiungo il risultato allo stack
            }
        }

        return stack.pop();
    }

    private static boolean VerificaNumero(String elemento) { // controllo che sia un numero
        try {
            Double.parseDouble(elemento);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean VerificaOperatore2(String elemento) { // controllo che sia un operatore
        return elemento.equals("+") || elemento.equals("-") || elemento.equals("*") || elemento.equals("/");

    }

    private static double Calcolo(double operand1, double operand2, String operator) { // faccio le operazioni
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
               return 0.0;
        }
    }

}
