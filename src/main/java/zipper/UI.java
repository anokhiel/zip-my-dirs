package zipper;

import zipper.action.SourceButtonClickListener;
import zipper.action.StartButtonClickListener;
import zipper.action.TargetButtonClickListener;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

public class UI {
    public static Model model = new Model();
    public static boolean disableButton=false;
    private JFrame mainFrame;
    private JLabel headerLabel;// Надпись сверху
    public static JLabel statusLabel;// Надпись с откликом
    public static JLabel fileLabel;// Выбор папки
    public static JLabel fileLabel1;// Выбор папки
    private JPanel controlPanel;
    private GraphicsConfiguration gc;
    public JButton sourceButton;//Выбор папки
    public JButton targetButton;//Выбор папки
    public static JButton okButton;// Кнопка "Поехали"

    // private ButtonClickListener BCL;


    public UI() {
        mainFrame = new JFrame(gc);
        mainFrame.setTitle("Zip-my-dir");
        mainFrame.setBounds(300, 200, 600, 400);
        mainFrame.setLayout(new GridLayout(1, 1));
        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });
        try {
            Image image = ImageIO.read(new File("images/index.png"));
            mainFrame.setIconImage(image);
        } catch (IOException ex) {
        }

        controlPanel = new JPanel();
        controlPanel.setBackground(Color.cyan);
        GridBagLayout layout = new GridBagLayout();
        controlPanel.setLayout(layout);
        GridBagConstraints c = new GridBagConstraints();
        Font font = new Font("Verdana", Font.PLAIN, 18);
        Font font1 = new Font("Verdana", Font.PLAIN, 14);
        c.anchor = GridBagConstraints.NORTH;
        c.fill = GridBagConstraints.NONE;
        c.gridheight = 1;
        c.gridwidth = GridBagConstraints.NONE;
        c.gridx = 0;
        c.gridy = GridBagConstraints.RELATIVE;
        c.insets = new Insets(0, 0, 40, 0);
        c.ipadx = 0;
        c.ipady = 0;
        c.weightx = 0.0;
        c.weighty = 0.0;
        headerLabel = new JLabel(
                "<htmL><h2 style='text-aling:center'> ZIP-MY-DIRS</h2></html>", SwingConstants.CENTER);
        headerLabel.setSize(700, 300);
        headerLabel.setFont(font);
        statusLabel = new JLabel("");
        // statusLabel.setSize(350,20);
        fileLabel = new JLabel("", JLabel.CENTER);
       // fileLabel.setSize(100, 20);
        fileLabel1 = new JLabel("", JLabel.CENTER);

        //   scrollPane.setSize(300, 300);
        sourceButton = new JButton("Source folder ...", new ImageIcon("images/Open16.gif"));
        sourceButton.setFont(font1);
        sourceButton.setActionCommand("fc");
        sourceButton.addActionListener(new SourceButtonClickListener());

        targetButton = new JButton("Target folder ...", new ImageIcon("images/Open16.gif"));
        targetButton.setFont(font1);
        targetButton.setActionCommand("fc");
        targetButton.addActionListener(new TargetButtonClickListener());

        // fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        okButton = new JButton("Start");
       okButton.setBackground(Color.WHITE);
        okButton.setActionCommand("OK");
        okButton.setFont(font1);
        okButton.setFocusPainted(false);
        okButton.setPreferredSize(new Dimension(150, 40));
        okButton.addActionListener(new StartButtonClickListener());
        layout.setConstraints(headerLabel, c);
        layout.setConstraints(fileLabel, c);
        layout.setConstraints(fileLabel1, c);
        layout.setConstraints(okButton, c);
        layout.setConstraints(statusLabel, c);
        controlPanel.add(headerLabel);
        controlPanel.add(sourceButton);
        controlPanel.add(fileLabel);
        controlPanel.add(targetButton);
        controlPanel.add(fileLabel1);
        controlPanel.add(okButton);
        controlPanel.add(statusLabel);
        controlPanel.setBackground(Color.white);
        mainFrame.add(controlPanel);
        mainFrame.setVisible(true);

    }
    public static void warn(){
        okButton.setBackground(Color.RED);
        okButton.setText("Abort");
    }
    public  static void resetButton(){
        okButton.setBackground(Color.WHITE);
        okButton.setText("Start");
    }
}
