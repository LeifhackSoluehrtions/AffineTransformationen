import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;

public class RectangleScaling extends JFrame {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 400;

    private JTextField scaleXInput;
    private JTextField scaleYInput;

    public RectangleScaling() {
        setTitle("Rechtecke Skalieren");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // panel erzeugen
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;

                int x = 50;
                int y = 50;
                int width = 100;
                int height = 60;

                g2d.setColor(Color.BLUE);
                g2d.fillRect(x, y, width, height);

                AffineTransform transform = new AffineTransform();

                // input für skalierungsfaktoren
                double scaleX = Double.parseDouble(scaleXInput.getText());
                double scaleY = Double.parseDouble(scaleYInput.getText());

                transform.scale(scaleX, scaleY);

                Shape transformedRectangle = transform.createTransformedShape(new Rectangle(x, y, width, height));

                g2d.setColor(Color.RED);
                g2d.fill(transformedRectangle);
            }
        };

        // textfelder und button
        scaleXInput = new JTextField("1.0", 5);
        scaleYInput = new JTextField("1.0", 5);
        JButton scaleButton = new JButton("Skalieren");

        // actionlistener für button
        scaleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // panel neu berechnen on button press
                panel.repaint();
            }
        });

        // input felder ins panel
        panel.add(new JLabel("Skalierungsfaktor X: "));
        panel.add(scaleXInput);
        panel.add(new JLabel("Skalierungsfaktor Y: "));
        panel.add(scaleYInput);
        panel.add(scaleButton);

        // panel ins frame
        add(panel);

        // layout manager setten
        panel.setLayout(new FlowLayout());

        // frame sichtbar machen
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RectangleScaling());
    }
}
