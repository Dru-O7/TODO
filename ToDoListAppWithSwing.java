import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToDoListAppWithSwing extends JFrame {
    private DefaultListModel<String> taskListModel;
    private JList<String> taskList;
    private JTextField taskTextField;
    private JButton addButton;
    private JButton removeButton;
    private JButton prioritizeButton;
    private int taskCounter = 1;

    public ToDoListAppWithSwing() {
        setTitle("ToDo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        taskListModel = new DefaultListModel<>();
        taskList = new JList<>(taskListModel);
        JScrollPane scrollPane = new JScrollPane(taskList);

        taskTextField = new JTextField(20);
        addButton = new JButton("Add Task");
        removeButton = new JButton("Remove Task");
        prioritizeButton = new JButton("Prioritize Task");

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String task = taskTextField.getText();
                if (!task.isEmpty()) {
                    taskListModel.addElement(taskCounter + ". " + task);
                    taskTextField.setText("");
                    taskCounter++;
                }
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = taskList.getSelectedIndex();
                if (selectedIndex != -1) {
                    taskListModel.remove(selectedIndex);
                    // Update task indexes
                    updateTaskIndexes();
                }
            }
        });

        prioritizeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = taskList.getSelectedIndex();
                if (selectedIndex != -1) {
                    String task = taskListModel.remove(selectedIndex);
                    taskListModel.add(0, task);
                    // Update task indexes
                    updateTaskIndexes();
                }
            }
        });

        JPanel inputPanel = new JPanel();
        inputPanel.add(taskTextField);
        inputPanel.add(addButton);
        inputPanel.add(removeButton);
        inputPanel.add(prioritizeButton);

        add(scrollPane, BorderLayout.CENTER);
        add(inputPanel, BorderLayout.SOUTH);

        setSize(400, 300);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void updateTaskIndexes() {
        taskCounter = 1;
        for (int i = 0; i < taskListModel.size(); i++) {
            String task = taskListModel.getElementAt(i);
            taskListModel.setElementAt(taskCounter + ". " + task.substring(3), i);
            taskCounter++;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ToDoListAppWithSwing());

    }
}
