import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;

public class RectangleSkewing extends JFrame {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 400;

    private JTextField skewXInput;
    private JTextField skewYInput;

    public RectangleSkewing() {
        setTitle("Scherung von Rechtecken");
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

                // Scherung input
                double skewX = Double.parseDouble(skewXInput.getText());
                double skewY = Double.parseDouble(skewYInput.getText());

                transform.shear(skewX, skewY);

                Shape skewedRectangle = transform.createTransformedShape(new Rectangle(x, y, width, height));

                g2d.setColor(Color.RED);
                g2d.fill(skewedRectangle);
            }
        };

        // textfelder und button
        skewXInput = new JTextField("0", 5);
        skewYInput = new JTextField("0", 5);
        JButton skewButton = new JButton("Scheren");

        // actionlistener für button
        skewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // panel neu berechnen on button press
                panel.repaint();
            }
        });

        // input felder ins panel
        panel.add(new JLabel("X-Scherung: "));
        panel.add(skewXInput);
        panel.add(new JLabel("Y-Scherung: "));
        panel.add(skewYInput);
        panel.add(skewButton);

        // panel ins frame
        add(panel);

        // layout manager setten
        panel.setLayout(new FlowLayout());

        // frame sichtbar machen
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RectangleSkewing());
    }
}
