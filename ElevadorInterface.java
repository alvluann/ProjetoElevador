import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

public class ElevadorInterface extends JFrame {
    private Elevador elevador;
    private JTextArea statusArea;
    private JPanel elevadorPanel;
    private JLabel elevadorLabel;
    private JPanel displayPanel;
    private JLabel displayLabel;
    private JButton[] floorButtons;
    private JButton iniciarButton; // Botão de iniciar

    public ElevadorInterface() {
        setTitle("Simulação de Elevador");
        setSize(600, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        elevador = new Elevador(10, 10);

        // Painel de status e botão iniciar
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());

        iniciarButton = new JButton("Iniciar Movimentação");
        iniciarButton.setFont(new Font("Arial", Font.BOLD, 16));
        iniciarButton.setBackground(new Color(0, 128, 255));
        iniciarButton.setForeground(Color.WHITE);
        iniciarButton.setFocusPainted(false);
        iniciarButton.setPreferredSize(new Dimension(200, 40));
        iniciarButton.addActionListener(e -> iniciarMovimentacao());

        statusArea = new JTextArea(10, 30);
        statusArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(statusArea);

        // Painel do elevador com o display digital e posição do elevador
        elevadorPanel = new JPanel();
        elevadorPanel.setLayout(new BorderLayout());
        elevadorPanel.setPreferredSize(new Dimension(250, 500));
        elevadorPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 3));
        elevadorPanel.setBackground(Color.LIGHT_GRAY);

        elevadorLabel = new JLabel("Elevador", SwingConstants.CENTER);
        elevadorLabel.setOpaque(true);
        elevadorLabel.setBackground(Color.DARK_GRAY);
        elevadorLabel.setForeground(Color.WHITE);
        elevadorLabel.setFont(new Font("Arial", Font.BOLD, 18));
        elevadorLabel.setPreferredSize(new Dimension(200, 60));

        elevadorPanel.add(elevadorLabel, BorderLayout.SOUTH);

        // Painel de botões de andar, com botões maiores
        JPanel floorButtonsPanel = new JPanel();
        floorButtonsPanel.setLayout(new GridLayout(10, 1, 5, 5));
        floorButtonsPanel.setPreferredSize(new Dimension(100, 500));
        floorButtons = new JButton[10];

        for (int i = 9; i >= 0; i--) {
            JButton floorButton = new JButton(String.valueOf(i));
            floorButton.setFont(new Font("Arial", Font.PLAIN, 20));
            floorButton.setBackground(Color.WHITE);
            floorButton.setForeground(Color.BLACK);
            floorButton.setFocusPainted(false);
            floorButton.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
            floorButton.setPreferredSize(new Dimension(80, 50)); // Botões maiores

            int andar = i;
            floorButton.addActionListener(e -> solicitarAndar(andar));
            floorButtons[i] = floorButton;
            floorButtonsPanel.add(floorButton);
        }

        // Display digital para o andar atual
        displayPanel = new JPanel();
        displayPanel.setBackground(Color.BLACK);
        displayPanel.setPreferredSize(new Dimension(250, 60));
        displayLabel = new JLabel("Andar: 0", SwingConstants.CENTER);
        displayLabel.setForeground(Color.GREEN);
        displayLabel.setFont(new Font("Digital-7", Font.BOLD, 24));
        displayPanel.add(displayLabel);

        // Adicionando componentes à interface
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.add(displayPanel, BorderLayout.NORTH);
        leftPanel.add(floorButtonsPanel, BorderLayout.CENTER);
        leftPanel.add(iniciarButton, BorderLayout.SOUTH);

        add(leftPanel, BorderLayout.WEST);
        add(elevadorPanel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);

        elevadorLabel.setLocation(0, 400);
    }

    private void solicitarAndar(int andar) {
        elevador.solicitarAndar(andar);
        statusArea.append("Andar " + andar + " solicitado.\n");
    }

    private void iniciarMovimentacao() {
        statusArea.append("Iniciando movimentação...\n");
        new Thread(() -> elevador.iniciarMovimentacao(this)).start();
    }

    public void atualizarElevador(int andar) {
        int alturaPainel = elevadorPanel.getHeight();
        int alturaAndar = alturaPainel / 10;
        int y = alturaPainel - (andar * alturaAndar) - 50;

        SwingUtilities.invokeLater(() -> {
            for (JButton btn : floorButtons) {
                btn.setBackground(Color.WHITE);
                btn.setForeground(Color.BLACK);
            }
            floorButtons[andar].setBackground(new Color(0, 128, 255));
            floorButtons[andar].setForeground(Color.WHITE);

            displayLabel.setText("Andar: " + andar);

            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                public void run() {
                    elevadorLabel.setLocation(0, y);
                }
            };
            timer.schedule(task, 500); // Animação suave entre andares
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ElevadorInterface().setVisible(true));
    }
}
