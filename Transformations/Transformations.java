import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;

public class Transformations extends JFrame {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 400;

    // translation
    private JTextField translateXInput;
    private JTextField translateYInput;

    // skalierung
    private JTextField scaleXInput;
    private JTextField scaleYInput;

    // rotation
    private JTextField rotateAngleInput;

    // scherung
    private JTextField skewXInput;
    private JTextField skewYInput;

    public Transformations() {
        setTitle("Transformationen von Rechtecken");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // panel erzeugen
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;

                int centerX = getWidth() / 2;
                int centerY = getHeight() / 2;

                int width = 100;
                int height = 60;

                int x = centerX - width / 2;
                int y = centerY - height / 2;

                g2d.setColor(Color.BLUE);
                g2d.fillRect(x, y, width, height);

                AffineTransform transform = new AffineTransform();

                // translation
                double translateX = Double.parseDouble(translateXInput.getText());
                double translateY = Double.parseDouble(translateYInput.getText());
                transform.translate(translateX, translateY);

                // skalierung
                double scaleX = Double.parseDouble(scaleXInput.getText());
                double scaleY = Double.parseDouble(scaleYInput.getText());

                // Translate to the origin
                transform.translate(centerX, centerY);

                // Scale at the origin
                transform.scale(scaleX, scaleY);

                // Translate back to the original position
                transform.translate(-centerX, -centerY);

                // rotation
                double rotateAngle = Math.toRadians(Double.parseDouble(rotateAngleInput.getText()));
                transform.rotate(rotateAngle, centerX, centerY);

                // scherung
                double skewX = Double.parseDouble(skewXInput.getText());
                double skewY = Double.parseDouble(skewYInput.getText());
                transform.shear(skewX, skewY);

                Shape transformedRectangle = transform.createTransformedShape(new Rectangle(x, y, width, height));

                g2d.setColor(Color.RED);
                g2d.fill(transformedRectangle);
            }

        };

        // textfelder und buttons für translation
        translateXInput = new JTextField("0", 5);
        translateYInput = new JTextField("0", 5);
        JButton translateButton = new JButton("Verschieben");

        translateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.repaint();
            }
        });

        // textfelder und buttons für scaling
        scaleXInput = new JTextField("1.0", 5);
        scaleYInput = new JTextField("1.0", 5);
        JButton scaleButton = new JButton("Skalieren");

        scaleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.repaint();
            }
        });

        // textfelder und buttons für rotation
        rotateAngleInput = new JTextField("0.0", 5);
        JButton rotateButton = new JButton("Rotieren");

        rotateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.repaint();
            }
        });

        // textfelder und buttons für skewing
        skewXInput = new JTextField("0", 5);
        skewYInput = new JTextField("0", 5);
        JButton skewButton = new JButton("Scheren");

        skewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel.repaint();
            }
        });

        // inputfelder ins panel
        panel.add(new JLabel("X-Verschiebung: "));
        panel.add(translateXInput);
        panel.add(new JLabel("Y-Verschiebung: "));
        panel.add(translateYInput);
        panel.add(translateButton);

        panel.add(new JLabel("Skalierungsfaktor X: "));
        panel.add(scaleXInput);
        panel.add(new JLabel("Skalierungsfaktor Y: "));
        panel.add(scaleYInput);
        panel.add(scaleButton);

        panel.add(new JLabel("Rotationswinkel: "));
        panel.add(rotateAngleInput);
        panel.add(rotateButton);

        panel.add(new JLabel("X-Scherung: "));
        panel.add(skewXInput);
        panel.add(new JLabel("Y-Scherung: "));
        panel.add(skewYInput);
        panel.add(skewButton);

        // panel ins frame
        add(panel);

        // layout-manager setzen
        panel.setLayout(new FlowLayout());

        // frame sichtbar machen
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Transformations());
    }
}
