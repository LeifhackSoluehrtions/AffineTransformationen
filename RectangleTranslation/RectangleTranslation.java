import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;

public class RectangleTranslation extends JFrame {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 400;

    private JTextField translateXInput;
    private JTextField translateYInput;

    public RectangleTranslation() {
        setTitle("Translation von Rechtecken");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // panel erzeugen
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;

                int x = 150;
                int y = 150;
                int width = 100;
                int height = 60;

                g2d.setColor(Color.BLUE);
                g2d.fillRect(x, y, width, height);

                AffineTransform transform = new AffineTransform();

                // translation input
                double translateX = Double.parseDouble(translateXInput.getText());
                double translateY = Double.parseDouble(translateYInput.getText());

                transform.translate(translateX, translateY);

                Shape transformedRectangle = transform.createTransformedShape(new Rectangle(x, y, width, height));

                g2d.setColor(Color.RED);
                g2d.fill(transformedRectangle);
            }
        };

        // textfelder und button
        translateXInput = new JTextField("0", 5);
        translateYInput = new JTextField("0", 5);
        JButton translateButton = new JButton("Verschieben");

        // actionlistener für button
        translateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // panel neu berechnen on button press
                panel.repaint();
            }
        });

        // input felder ins panel
        panel.add(new JLabel("X-Verschiebung: "));
        panel.add(translateXInput);
        panel.add(new JLabel("Y-Verschiebung: "));
        panel.add(translateYInput);
        panel.add(translateButton);

        // panel ins frame
        add(panel);

        // layout manager setten
        panel.setLayout(new FlowLayout());

        // frame sichtbar machen
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RectangleTranslation());
    }
}
