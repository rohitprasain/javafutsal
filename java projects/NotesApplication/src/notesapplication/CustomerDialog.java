package notesapplication;

import dal.Customer;
import dal.CustomerRepository;
import java.awt.BorderLayout;
import java.awt.Color;
import static java.awt.Dialog.DEFAULT_MODALITY_TYPE;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

public class CustomerDialog  extends JDialog implements  KeyListener {

    private JPanel buttonsPanel;
    private JPanel contentPanel;
    private JPanel inputPanel;

    private JButton newButton;
    private JButton editButton;
    private JButton deleteButton;
    private JButton saveButton;

    private JTable table;
    private JScrollPane scrollPane;

    public ArrayList<Customer> customers;

    public UsersTableModel usersTableModel;

    private JTextField nameTextField;
    private JTextField contacttextField;
    private JTextField timetextField;

    private boolean isEditMode = false;

    private Customer currentCustomer;

    public CustomerDialog() {
        setModalityType(DEFAULT_MODALITY_TYPE);
        setTitle("Manang Booking");

        // Dialog
        buttonsPanel = new JPanel();
        add(buttonsPanel, BorderLayout.NORTH);
        contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        add(contentPanel, BorderLayout.CENTER);

        // Content Panel
        inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 3));
        contentPanel.add(inputPanel, BorderLayout.NORTH);
        loadData();
        usersTableModel = new UsersTableModel();
        table = new JTable(usersTableModel);
        scrollPane = new JScrollPane(table);
        contentPanel.add(scrollPane, BorderLayout.CENTER);
        // Buttons Panel

        //new button
        newButton = new JButton("NewBook");
        newButton.addActionListener((e) -> {
            newButtonClicked();
        });
        buttonsPanel.add(newButton);
        //edit button
        editButton = new JButton("EditBook");
        editButton.addActionListener((e) -> {
            editButtonClicked();
        });
        buttonsPanel.add(editButton);
        //delete button
        deleteButton = new JButton("DeleteBook");
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
        inputPanel.add(new JLabel("Name: "));
        nameTextField = new JTextField();
        
       
        inputPanel.add(nameTextField);

        inputPanel.add(new JLabel("Contact: "));
        contacttextField = new JTextField();
        inputPanel.add(contacttextField);
        contacttextField.addKeyListener(this);
        

        inputPanel.add(new JLabel("Time: "));
        timetextField = new JTextField();
        inputPanel.add(timetextField);
        pack();
    }

    private void loadData() {
        try {
            CustomerRepository customerRepository = new CustomerRepository();
            customers = customerRepository.getAll();
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
        currentCustomer = null;    //for safety
        nameTextField.setText("");
        contacttextField.setText("");
        timetextField.setText("");
    }

    private void editButtonClicked() {
        isEditMode = true;   //edit mode on
        if (customers.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No Records Found");
            return;
        }
        if (table.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(this, "No Row Selected");
            return;
        }
        currentCustomer = customers.get(table.getSelectedRow());
        nameTextField.setText(currentCustomer.name);
        contacttextField.setText(currentCustomer.contact);
        timetextField.setText(currentCustomer.time);
    }

    private void deleteButtonClicked() {
        isEditMode = true;
        if (table.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(this, "No Row Selected");
            return;
        }
        if (customers.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No Records Found");
            return;
        }
        if (isEditMode) {
            deleteCustomer();
        }
    }

    private void saveButtonClicked() {
        if (!isEditMode) {
            insertCustomer();
        } else {
            updateCustomer();
        }
    }

    private void insertCustomer() {

        try {
            String name = nameTextField.getText();
            String contact = new String(contacttextField.getText());
            String time = new String(timetextField.getText());
            CustomerRepository customerRepository = new CustomerRepository();
            if (!customerRepository.insert(name, contact, time)) {
                JOptionPane.showMessageDialog(this, "BOOKING FAILED");
                return;
            }
            refreshData();
            JOptionPane.showMessageDialog(this, "BOOKING SAVED");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Booking exists");
        }

    }

    private void updateCustomer() {
        String name = nameTextField.getText();
        String contact = new String(contacttextField.getText());
        String time = new String(timetextField.getText());
        try {
            CustomerRepository customerRepository = new CustomerRepository();
            if (!customerRepository.update(currentCustomer.id, name, contact, time)) {
                JOptionPane.showMessageDialog(this, "SAVE FAILED!!!");
                return;
            }
            refreshData();
            JOptionPane.showMessageDialog(this, "BOOKING SAVED");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "BOOKING EXISTS");
        }
    }

    private void deleteCustomer() {
        currentCustomer = customers.get(table.getSelectedRow());
        try {
            CustomerRepository customerRepository = new CustomerRepository();
            if (!customerRepository.delete(currentCustomer.id)) {
                JOptionPane.showMessageDialog(this, "DELETE FAILED!!");
                return;
            }
            refreshData();
            JOptionPane.showMessageDialog(this, "BOOKING DELETED");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if(e.getKeyChar()!='0'&& e.getKeyChar()!='1' && e.getKeyChar()!='2'
                && e.getKeyChar()!='3'&& e.getKeyChar()!='4'
                && e.getKeyChar()!='5'&& e.getKeyChar()!='6'&& e.getKeyChar()!='7'
                && e.getKeyChar()!='8'&& e.getKeyChar()!='9')
        {
          e.consume();   
          Toolkit.getDefaultToolkit().beep();
        }          
    }

    @Override
    public void keyPressed(KeyEvent e) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent e) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private class UsersTableModel extends AbstractTableModel {

        private String[] columns = {
            "id", "Name", "Contact", "Time"};

        @Override
        public String getColumnName(int index) {
            return columns[index];
        }

        @Override
        public int getRowCount() {
            return customers.size();
        }

        @Override
        public int getColumnCount() {
            return 4;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Customer customer = customers.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return customer.id;
                case 1:
                    return customer.name;
                case 2:
                    return customer.contact;
                case 3:
                    return customer.time;
            }
            return null;
        }
    }
}
