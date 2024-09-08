import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class PolarToCartesianConverter extends JFrame {
    private JTextField radiusField, angleField;
    private JLabel xLabel, yLabel;

    public PolarToCartesianConverter() {
        // Создаем панель для размещения компонентов
        JPanel panel = new JPanel();

        // Создаем метки и поля ввода для ввода полярных координат
        JLabel radiusLabel = new JLabel("Радиус:");
        radiusField = new JTextField(10);

        JLabel angleLabel = new JLabel("Угол (в градусах):");
        angleField = new JTextField(10);

        // Создаем метки для вывода декартовых координат
        xLabel = new JLabel("X:");
        yLabel = new JLabel("Y:");

        // Создаем кнопку для преобразования координат
        JButton convertButton = new JButton("Преобразовать");
        convertButton.addActionListener(e -> convertPolarToCartesian());

        // Добавляем компоненты на панель
        panel.add(radiusLabel);
        panel.add(radiusField);
        panel.add(angleLabel);
        panel.add(angleField);
        panel.add(convertButton);
        panel.add(xLabel);
        panel.add(yLabel);

        // Добавляем панель на фрейм
        add(panel);

        // Настраиваем фрейм
        setTitle("Преобразование полярных координат в декартовы");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 150);
        setLocationRelativeTo(null);
    }

    private void convertPolarToCartesian() {
        // Проверяем, что поля ввода не пустые
        if (radiusField.getText().isEmpty() || angleField.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Пожалуйста, заполните все поля.");
            return;
        }

        // Получаем значения полярных координат из полей ввода
        double radius;
        try {
            radius = Double.parseDouble(radiusField.getText());
        }

        catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Неверный формат радиуса. Пожалуйста, введите число.");
            return;
        }
        if (radius < 0 ) {
            JOptionPane.showMessageDialog(this, "Введите возможный радиус.");
            return;
        }

        double angle;
        try {
            angle = Math.toRadians(Double.parseDouble(angleField.getText()));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Неверный формат угла. Пожалуйста, введите число от 0 до 360.");
            return;
        }

        // Проверяем, что угол находится в допустимом диапазоне
        if (angle < 0 || angle > 2 * Math.PI) {
            JOptionPane.showMessageDialog(this, "Угол должен быть в диапазоне от 0 до 360 градусов.");
            return;
        }

        // Преобразуем полярные координаты в декартовы
        double x = radius * Math.cos(angle);
        double y = radius * Math.sin(angle);

        // Выводим декартовы координаты в метки
        xLabel.setText(String.format("X: %.2f", x));
        yLabel.setText(String.format("Y: %.2f", y));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PolarToCartesianConverter().setVisible(true));
    }
}
