import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ElevadorInterface extends JFrame {
    private Elevador elevador;
    private JTextField andarInput;
    private JTextArea statusArea;

    public ElevadorInterface() {
        setTitle("Simulação de Elevador");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        elevador = new Elevador(10, 10); // Elevador com capacidade para 10 andares e limite de 10 andares

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());
        andarInput = new JTextField(5);
        JButton solicitarButton = new JButton("Solicitar Andar");
        solicitarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                solicitarAndar();
            }
        });
        inputPanel.add(new JLabel("Andar:"));
        inputPanel.add(andarInput);
        inputPanel.add(solicitarButton);

        JButton iniciarButton = new JButton("Iniciar Movimentação");
        iniciarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                iniciarMovimentacao();
            }
        });

        statusArea = new JTextArea(10, 30);
        statusArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(statusArea);

        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(iniciarButton, BorderLayout.SOUTH);
    }

    private void solicitarAndar() {
        try {
            int andar = Integer.parseInt(andarInput.getText());
            elevador.solicitarAndar(andar);
            statusArea.append("Andar " + andar + " solicitado.\n");
        } catch (NumberFormatException ex) {
            statusArea.append("Por favor, insira um número válido para o andar.\n");
        }
        andarInput.setText("");
    }

    private void iniciarMovimentacao() {
        statusArea.append("Iniciando movimentação...\n");
        elevador.iniciarMovimentacao();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ElevadorInterface().setVisible(true);
            }
        });
    }
}
