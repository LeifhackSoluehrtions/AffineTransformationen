import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;

public class AffineTransformationen extends JFrame {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 400;

    private JTextField translateXInput;
    private JTextField translateYInput;
    private JTextField scaleXInput;
    private JTextField scaleYInput;
    private JTextField rotateAngleInput;
    private JTextField skewXInput;
    private JTextField skewYInput;

    private boolean rectangleSelected = true; // on startup rechteck
    private boolean circleSelected = false;

    public AffineTransformationen() {
        setTitle("Affine Transformationen");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;

                int centerX = getWidth() / 2;
                int centerY = getHeight() / 2;

                AffineTransform transform = new AffineTransform();

                double translateX = Double.parseDouble(translateXInput.getText());
                double translateY = Double.parseDouble(translateYInput.getText());
                transform.translate(translateX, translateY);

                double scaleX = Double.parseDouble(scaleXInput.getText());
                double scaleY = Double.parseDouble(scaleYInput.getText());
                transform.scale(scaleX, scaleY);

                transform.rotate(Math.toRadians(Double.parseDouble(rotateAngleInput.getText())), centerX, centerY);

                double skewX = Double.parseDouble(skewXInput.getText());
                double skewY = Double.parseDouble(skewYInput.getText());
                transform.shear(skewX, skewY);

                if (rectangleSelected) {
                    // originales rechteck
                    int width = 100;
                    int height = 60;
                    int x = centerX - width / 2;
                    int y = centerY - height / 2;

                    g2d.setColor(Color.BLUE);
                    g2d.fillRect(x, y, width, height);

                    // transformiertes rechteck
                    Shape transformedRectangle = transform.createTransformedShape(new Rectangle(x, y, width, height));

                    g2d.setColor(Color.RED);
                    g2d.fill(transformedRectangle);
                } else if (circleSelected) {
                    // original kreis
                    int diameter = 80;
                    int x = centerX - diameter / 2;
                    int y = centerY - diameter / 2;

                    g2d.setColor(Color.BLUE);
                    g2d.fillOval(x, y, diameter, diameter);

                    // transformierter kreis
                    Shape transformedCircle = transform
                            .createTransformedShape(new Ellipse2D.Double(x, y, diameter, diameter));

                    g2d.setColor(Color.RED);
                    g2d.fill(transformedCircle);
                } else {
                    // original dreieck
                    Path2D.Double triangle = new Path2D.Double();
                    int size = 100;

                    triangle.moveTo(centerX - size / 2, centerY + size / 2);
                    triangle.lineTo(centerX + size / 2, centerY + size / 2);
                    triangle.lineTo(centerX, centerY - size / 2);
                    triangle.closePath();

                    g2d.setColor(Color.BLUE);
                    g2d.fill(triangle);

                    // transformiertes dreieck
                    Shape transformedTriangle = transform.createTransformedShape(triangle);

                    g2d.setColor(Color.RED);
                    g2d.fill(transformedTriangle);
                }
            }
        };

        translateXInput = new JTextField("0", 5);
        translateYInput = new JTextField("0", 5);
        scaleXInput = new JTextField("1.0", 5);
        scaleYInput = new JTextField("1.0", 5);
        rotateAngleInput = new JTextField("0.0", 5);
        skewXInput = new JTextField("0", 5);
        skewYInput = new JTextField("0", 5);

        JButton translateButton = new JButton("Verschieben");
        JButton scaleButton = new JButton("Skalieren");
        JButton skewButton = new JButton("Scheren");
        JButton rotateButton = new JButton("Rotieren");

        JButton rectangleButton = new JButton("Rechteck");
        JButton circleButton = new JButton("Kreis");
        JButton triangleButton = new JButton("Dreieck");

        translateButton.addActionListener(e -> panel.repaint());
        scaleButton.addActionListener(e -> panel.repaint());
        rotateButton.addActionListener(e -> panel.repaint());
        skewButton.addActionListener(e -> panel.repaint());

        rectangleButton.addActionListener(e -> {
            rectangleSelected = true;
            circleSelected = false;
            panel.repaint();
        });

        circleButton.addActionListener(e -> {
            rectangleSelected = false;
            circleSelected = true;
            panel.repaint();
        });

        triangleButton.addActionListener(e -> {
            rectangleSelected = false;
            circleSelected = false;
            panel.repaint();
        });

        panel.add(new JLabel("X-Translation: "));
        panel.add(translateXInput);
        panel.add(new JLabel("Y-Translation: "));
        panel.add(translateYInput);
        panel.add(translateButton);

        panel.add(new JLabel("Skalierungsfaktor X: "));
        panel.add(scaleXInput);
        panel.add(new JLabel("Skalierungsfaktor Y: "));
        panel.add(scaleYInput);
        panel.add(scaleButton);

        panel.add(new JLabel("X-Scherungskoeffizient: "));
        panel.add(skewXInput);
        panel.add(new JLabel("Y-Scherungskoeffizient: "));
        panel.add(skewYInput);
        panel.add(skewButton);

        panel.add(new JLabel("Rotationswinkel: "));
        panel.add(rotateAngleInput);
        panel.add(rotateButton);

        panel.add(rectangleButton);
        panel.add(circleButton);
        panel.add(triangleButton);

        add(panel);
        panel.setLayout(new FlowLayout());
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AffineTransformationen::new);
    }
}
