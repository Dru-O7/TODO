import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ToDoListAppWithSwing extends JFrame {
    private DefaultListModel<String> taskListModel;
    private JList<String> taskList;
    private JTextField taskTextField;
    private JButton addButton;
    private JToggleButton darkModeToggle;

    public ToDoListAppWithSwing() {
        setTitle("To-Do List");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        taskListModel = new DefaultListModel<>();
        taskList = new JList<>(taskListModel);
        taskList.setFont(new Font("Arial", Font.PLAIN, 16));
        taskList.setCellRenderer(new TaskCellRenderer());

        JScrollPane scrollPane = new JScrollPane(taskList);

        taskTextField = new JTextField(20);
        addButton = new JButton("Add Task");
        darkModeToggle = new JToggleButton("Dark Mode");

        addButton.addActionListener(e -> {
            String task = taskTextField.getText();
            if (!task.isEmpty()) {
                taskListModel.addElement(task);
                taskTextField.setText("");
            }
        });

        darkModeToggle.addActionListener(e -> {
            if (darkModeToggle.isSelected()) {
                getContentPane().setBackground(Color.DARK_GRAY);
                taskList.setForeground(Color.WHITE);
            } else {
                getContentPane().setBackground(Color.WHITE);
                taskList.setForeground(Color.BLACK);
            }
        });

        JPanel inputPanel = new JPanel();
        inputPanel.setBackground(Color.WHITE);
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        inputPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        inputPanel.add(taskTextField);
        inputPanel.add(addButton);
        inputPanel.add(darkModeToggle);

        add(scrollPane, BorderLayout.CENTER);
        add(inputPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private class TaskCellRenderer extends JPanel implements ListCellRenderer<String> {
        private JButton removeButton;
        private JButton prioritizeButton;
        private JLabel taskLabel;

        public TaskCellRenderer() {
            setLayout(new BorderLayout());
            setOpaque(true);

            removeButton = new JButton("Remove");
            prioritizeButton = new JButton("Prioritize");
            taskLabel = new JLabel();

            JPanel buttonPanel = new JPanel();
            buttonPanel.add(removeButton);
            buttonPanel.add(prioritizeButton);

            add(taskLabel, BorderLayout.CENTER);
            add(buttonPanel, BorderLayout.EAST);

            removeButton.addActionListener(e -> {
                String task = taskLabel.getText();
                taskListModel.removeElement(task);
            });

            prioritizeButton.addActionListener(e -> {
                String task = taskLabel.getText();
                taskListModel.removeElement(task);
                taskListModel.add(0, task);
            });
        }

        @Override
        public Component getListCellRendererComponent(JList<? extends String> list, String value, int index,
                                                      boolean isSelected, boolean cellHasFocus) {
            taskLabel.setText(value);
            return this;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ToDoListAppWithSwing());
    }
}
