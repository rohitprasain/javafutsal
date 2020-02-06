package notesapplication;

import dal.User;
import dal.UserRepository;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

public class UsersDialog extends JDialog {

    private JPanel buttonsPanel;
    private JPanel contentPanel;
    private JPanel inputPanel;

    private JButton newButton;
    private JButton editButton;
    private JButton deleteButton;
    private JButton saveButton;

    private JTable table;
    private JScrollPane scrollPane;

    private ArrayList<User> users;

    private UsersTableModel usersTableModel;

    private JTextField usernameTextField;
    private JPasswordField passwordField;

    private boolean isEditMode = false;

    private User currentUser;

    public UsersDialog() {
        setModalityType(DEFAULT_MODALITY_TYPE);
        setTitle("Users");

        // Dialog
        buttonsPanel = new JPanel();
        add(buttonsPanel, BorderLayout.NORTH);
        contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        add(contentPanel, BorderLayout.CENTER);

        // Content Panel
        inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(2, 2));
        contentPanel.add(inputPanel, BorderLayout.NORTH);
        loadData();
        usersTableModel = new UsersTableModel();
        table = new JTable(usersTableModel);
        scrollPane = new JScrollPane(table);
        contentPanel.add(scrollPane, BorderLayout.CENTER);
        // Buttons Panel
        
        //new button
        newButton = new JButton("New");
        newButton.addActionListener((e) -> {
            newButtonClicked();
        });
        buttonsPanel.add(newButton);
        //edit button
        editButton = new JButton("Edit");
        editButton.addActionListener((e) -> {
            editButtonClicked();
        });
        buttonsPanel.add(editButton);
        //delete button
        deleteButton = new JButton("Delete");
        deleteButton.addActionListener((e) -> {
            deleteButtonClicked();
        });
        buttonsPanel.add(deleteButton);
        //save button
        saveButton = new JButton("Save");
        saveButton.addActionListener((e) -> {
            saveButtonClicked();
        });
        buttonsPanel.add(saveButton);

        // Input Panel
        inputPanel.add(new JLabel("Username: "));
        usernameTextField = new JTextField();
        inputPanel.add(usernameTextField);
        inputPanel.add(new JLabel("Password: "));
        passwordField = new JPasswordField();
        inputPanel.add(passwordField);
        pack();
    }

    private void loadData() {
        try {
            UserRepository userRepository = new UserRepository();
            users = userRepository.getAll();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void refreshData() {
        loadData();
        usersTableModel.fireTableDataChanged();
    }

    private void newButtonClicked() {
        isEditMode = false;    //no edit mode
        currentUser = null;    //for safety
        usernameTextField.setText("");
        passwordField.setText("");
    }

    private void saveButtonClicked() {
        if (!isEditMode) {
            insertUser();
        } else {
            updateUser();
        }
    }

    private void deleteButtonClicked() {
        isEditMode = true;
        if (table.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(this, "No row selected to delete");
            return;
        }
        if (users.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No records found to delete");
            return;
        }
        if (isEditMode) {
            deleteUser();
        }
    }

    private void editButtonClicked() {
        isEditMode = true;   //edit mode on
        if (users.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No records found");
            return;
        }
        if (table.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(this, "No row selected");
            return;
        }
        currentUser = users.get(table.getSelectedRow());
        usernameTextField.setText(currentUser.username);
        passwordField.setText(currentUser.password);
    }

    private void updateUser() {
        String username = usernameTextField.getText();
        String password = new String(passwordField.getPassword());
        try {
            UserRepository userRepository = new UserRepository();
            if (!userRepository.update(currentUser.id, username, password)) {
                JOptionPane.showMessageDialog(this, "save failed while update!!!!!");
                return;
            }
            refreshData();
            JOptionPane.showMessageDialog(this, "User saved");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void deleteUser() {
        currentUser = users.get(table.getSelectedRow());
        try {
            UserRepository userRepository = new UserRepository();
            if (!userRepository.delete(currentUser.id)) {
                JOptionPane.showMessageDialog(this, "delete failed!!!!!");
                return;
            }
            refreshData();
            JOptionPane.showMessageDialog(this, "User deleted");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void insertUser() {
        String username = usernameTextField.getText();
        String password = new String(passwordField.getPassword());
        try {
            UserRepository userRepository = new UserRepository();
            if (!userRepository.insert(username, password)) {
                JOptionPane.showMessageDialog(this, "save failed! while insert!!!!");
                return;
            }
            refreshData();
            JOptionPane.showMessageDialog(this, "User saved");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private class UsersTableModel extends AbstractTableModel {
        private String[] columns = {
            "Id", "Username", "Password"
        };
        
        @Override
        public String getColumnName(int index) {
            return columns[index];
        }

        @Override
        public int getRowCount() {
            return users.size();
        }

        @Override
        public int getColumnCount() {
            return 3;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            User user = users.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return user.id;
                case 1:
                    return user.username;
                case 2:
                    return user.password;
            }
            return null;
        }

        private void fireTableChanged() {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

    }

}
