import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;

public class RectangleRotation extends JFrame {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 400;

    private JTextField rotateAngleInput;

    public RectangleRotation() {
        setTitle("Rechteck Rotieren");
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

                // rotationswinkel input
                double rotateAngle = Math.toRadians(Double.parseDouble(rotateAngleInput.getText()));

                transform.rotate(rotateAngle, x + width / 2, y + height / 2);

                Shape transformedRectangle = transform.createTransformedShape(new Rectangle(x, y, width, height));

                g2d.setColor(Color.RED);
                g2d.fill(transformedRectangle);
            }
        };

        // textfelder und button
        rotateAngleInput = new JTextField("0.0", 5);
        JButton rotateButton = new JButton("Rotieren");

        // actionlistener für button
        rotateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // panel neu berechnen on button press
                panel.repaint();
            }
        });

        // input feld ins panel
        panel.add(new JLabel("Rotationswinkel: "));
        panel.add(rotateAngleInput);
        panel.add(rotateButton);

        // panel ins frame
        add(panel);

        // layout manager setten
        panel.setLayout(new FlowLayout());

        // frame sichtbar machen
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RectangleRotation());
    }
}
