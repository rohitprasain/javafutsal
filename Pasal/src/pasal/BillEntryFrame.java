package pasal;

import dal.Bill;
import dal.BillDetail;
import dal.BillRepository;
import dal.Product;
import dal.ProductRepository;
import java.awt.Color;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Aman
 */
public class BillEntryFrame extends javax.swing.JFrame {

    private ArrayList<BillDetail> billDetails;
    private BillDetailsTableModel billDetailsTableModel;
    
    private HashMap<Integer, Product> productsMap;

    /**
     * Creates new form BillEntryFrame
     */
    public BillEntryFrame() {
        initComponents();
        priceTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                priceTextFieldTextChanged();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                priceTextFieldTextChanged();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                priceTextFieldTextChanged();
            }
        });
        quantityTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                quantityTextFieldTextChanged(e);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                quantityTextFieldTextChanged(e);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                quantityTextFieldTextChanged(e);
            }
        });
        showProductPrice();
        initializeTable();
        loadProductsMap();
    }

    private Product[] loadProducts() {
        try {
            ProductRepository productRepository = new ProductRepository();
            return productRepository
                    .getAll()
                    .toArray(new Product[0]);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        return null;
    }

    private void initializeTable() {
        billDetails = new ArrayList<>();
        billDetailsTableModel = new BillDetailsTableModel();
        table.setModel(billDetailsTableModel);
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.removeColumn(columnModel.getColumn(0));       
    }
    
    private void loadProductsMap() {
        productsMap = new HashMap<>();
        try {
            ProductRepository productRepository = new ProductRepository();
            ArrayList<Product> products = productRepository.getAll();
            for(Product product : products) {
                productsMap.put(product.getId(), product);
            }
        }
        catch(SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    } 

    private class BillDetailsTableModel extends AbstractTableModel {
        
        private final String[] columns = { 
            "Bill Id", "Product", "Price", "Quantity", "Total"
        };

        @Override
        public String getColumnName(int index) {
            return columns[index];
        }

//        @Override
//        public Class<?> getColumnClass(int columnIndex) {
//            switch(columnIndex) {
//                case 2:
//                    return BigDecimal.class;
//                case 3:
//                    return Integer.class;
//                case 4:
//                    return BigDecimal.class;
//            }
//            return super.getColumnClass(columnIndex); 
//        }

        @Override
        public int getRowCount() {
            return billDetails.size();
        }

        @Override
        public int getColumnCount() {
            return 5;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            BillDetail billDetail = billDetails.get(rowIndex);
            switch(columnIndex) {
                case 0:
                    return billDetail.getBillId();
                case 1:
                    Product product = productsMap.get(billDetail.getProductId());
                    return product.getProductName();
                case 2:
                    return billDetail.getPrice();
                case 3:
                    return billDetail.getQuantity();
                case 4:
                    return billDetail.getTotal();
            }
            return null;
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        customerDetailsPanel = new javax.swing.JPanel();
        nameLabel = new javax.swing.JLabel();
        customerNameTextField = new javax.swing.JTextField();
        addressLabel = new javax.swing.JLabel();
        customerAddressTextField = new javax.swing.JTextField();
        LineItemPanel = new javax.swing.JPanel();
        productLabel = new javax.swing.JLabel();
        productComboBox = new javax.swing.JComboBox<Product>(loadProducts());
        priceLabel = new javax.swing.JLabel();
        priceTextField = new javax.swing.JTextField();
        quantityLabel = new javax.swing.JLabel();
        quantityTextField = new javax.swing.JTextField();
        totalLabel = new javax.swing.JLabel();
        totalTextField = new javax.swing.JTextField();
        addLineItemButton = new javax.swing.JButton();
        scrollPane = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        saveButton = new javax.swing.JButton();
        grandTotalLabel = new javax.swing.JLabel();
        grandTotalTextField = new javax.swing.JTextField();
        jMenuBar1 = new javax.swing.JMenuBar();
        fileMenuItem = new javax.swing.JMenu();
        saveMenuItem = new javax.swing.JMenuItem();
        exitMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        customerDetailsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Customer Details"));

        nameLabel.setText("Name");

        addressLabel.setText("Address");

        customerAddressTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customerAddressTextFieldActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout customerDetailsPanelLayout = new javax.swing.GroupLayout(customerDetailsPanel);
        customerDetailsPanel.setLayout(customerDetailsPanelLayout);
        customerDetailsPanelLayout.setHorizontalGroup(
            customerDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(customerDetailsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(customerDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(addressLabel)
                    .addComponent(nameLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(customerDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(customerNameTextField)
                    .addComponent(customerAddressTextField))
                .addContainerGap())
        );
        customerDetailsPanelLayout.setVerticalGroup(
            customerDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(customerDetailsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(customerDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameLabel)
                    .addComponent(customerNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(customerDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addressLabel)
                    .addComponent(customerAddressTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        LineItemPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Line Item"));

        productLabel.setText("Product");

        productComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                productComboBoxItemStateChanged(evt);
            }
        });
        productComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                productComboBoxActionPerformed(evt);
            }
        });

        priceLabel.setText("Price");

        priceTextField.setEditable(false);
        priceTextField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        priceTextField.setFocusable(false);

        quantityLabel.setText("Quantity");

        quantityTextField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        quantityTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quantityTextFieldActionPerformed(evt);
            }
        });

        totalLabel.setText("Total");

        totalTextField.setEditable(false);
        totalTextField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        totalTextField.setFocusable(false);

        addLineItemButton.setText("Add Line Item");
        addLineItemButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addLineItemButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout LineItemPanelLayout = new javax.swing.GroupLayout(LineItemPanel);
        LineItemPanel.setLayout(LineItemPanelLayout);
        LineItemPanelLayout.setHorizontalGroup(
            LineItemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LineItemPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(LineItemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(productComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(productLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(LineItemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(priceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(priceLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(LineItemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(quantityTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(quantityLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(LineItemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(totalTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(totalLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addLineItemButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        LineItemPanelLayout.setVerticalGroup(
            LineItemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(LineItemPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(LineItemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(productLabel)
                    .addComponent(priceLabel)
                    .addComponent(quantityLabel)
                    .addComponent(totalLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(LineItemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(productComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(priceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(quantityTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(totalTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addLineItemButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        scrollPane.setViewportView(table);

        saveButton.setText("Save");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        grandTotalLabel.setText("Grand Total:");

        grandTotalTextField.setEditable(false);
        grandTotalTextField.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        grandTotalTextField.setText("0.00");

        fileMenuItem.setMnemonic('F');
        fileMenuItem.setText("File");

        saveMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        saveMenuItem.setMnemonic('S');
        saveMenuItem.setText("Save");
        saveMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveMenuItemActionPerformed(evt);
            }
        });
        fileMenuItem.add(saveMenuItem);

        exitMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_W, java.awt.event.InputEvent.CTRL_MASK));
        exitMenuItem.setMnemonic('E');
        exitMenuItem.setText("Exit");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        fileMenuItem.add(exitMenuItem);

        jMenuBar1.add(fileMenuItem);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollPane)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(saveButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(grandTotalLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(grandTotalTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(LineItemPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(customerDetailsPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(customerDetailsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LineItemPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(grandTotalLabel)
                    .addComponent(grandTotalTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(saveButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void productComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_productComboBoxItemStateChanged
        showProductPrice();
    }//GEN-LAST:event_productComboBoxItemStateChanged

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitMenuItemActionPerformed

    private void quantityTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quantityTextFieldActionPerformed
        addLineItem();
    }//GEN-LAST:event_quantityTextFieldActionPerformed

    private void addLineItemButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addLineItemButtonActionPerformed
        addLineItem();
    }//GEN-LAST:event_addLineItemButtonActionPerformed

    private void saveMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveMenuItemActionPerformed
        saveBill();
    }//GEN-LAST:event_saveMenuItemActionPerformed

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        saveBill();
    }//GEN-LAST:event_saveButtonActionPerformed

    private void productComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_productComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_productComboBoxActionPerformed

    private void customerAddressTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customerAddressTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_customerAddressTextFieldActionPerformed

    private void priceTextFieldTextChanged() {
        showTotal();
    }

    private void quantityTextFieldTextChanged(DocumentEvent evt) {
        showTotal();
    }

    public void showTotal() {
        quantityTextField.setForeground(Color.BLACK);
        try {
            BigDecimal price = new BigDecimal(priceTextField.getText());
            BigDecimal quantity = new BigDecimal(quantityTextField.getText());
            BigDecimal total = price.multiply(quantity);
            totalTextField.setText(total.toString());
        } catch (NumberFormatException e) {
            quantityTextField.setForeground(Color.RED);
            totalTextField.setText("0.00");
        }
    }

    private void showProductPrice() {
        Product product = productComboBox.getItemAt(productComboBox.getSelectedIndex());
        priceTextField.setText(product.getPrice().toString());
        quantityTextField.setText("");
    }

    private void addLineItem() {
        Product product = productComboBox.getItemAt(productComboBox.getSelectedIndex());
        int productId = product.getId();
        BigDecimal price = new BigDecimal(priceTextField.getText());
        int quantity = Integer.parseInt(quantityTextField.getText());
        billDetails.add(new BillDetail(productId, price, quantity));
        billDetailsTableModel.fireTableDataChanged();
        productComboBox.requestFocusInWindow();
        showGrandTotal();
    }
    
    private void showGrandTotal() {
        BigDecimal grandTotal = BigDecimal.ZERO;
        for(BillDetail billDetail : billDetails) {
            grandTotal = grandTotal.add(billDetail.getTotal());
        }
        grandTotalTextField.setText(grandTotal.toString());
    }
    
    private void saveBill() {
        String customerName = customerNameTextField.getText();
        String customerAddress = customerAddressTextField.getText();
        Bill bill = new Bill(customerName, customerAddress);  
        
        try {
            BillRepository billRepository = new BillRepository();
            if(!billRepository.insert(bill, billDetails)) {
                JOptionPane.showMessageDialog(this, "Save failed");
                return;
            }
            JOptionPane.showMessageDialog(this, "Bill Saved");
            customerNameTextField.setText("");
            customerAddressTextField.setText("");
            quantityTextField.setText("");
            initializeTable();
            billDetailsTableModel.fireTableDataChanged();
        }
        catch(SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(BillEntryFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BillEntryFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BillEntryFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BillEntryFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new BillEntryFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel LineItemPanel;
    private javax.swing.JButton addLineItemButton;
    private javax.swing.JLabel addressLabel;
    private javax.swing.JTextField customerAddressTextField;
    private javax.swing.JPanel customerDetailsPanel;
    private javax.swing.JTextField customerNameTextField;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu fileMenuItem;
    private javax.swing.JLabel grandTotalLabel;
    private javax.swing.JTextField grandTotalTextField;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JLabel priceLabel;
    private javax.swing.JTextField priceTextField;
    private javax.swing.JComboBox<Product> productComboBox;
    private javax.swing.JLabel productLabel;
    private javax.swing.JLabel quantityLabel;
    private javax.swing.JTextField quantityTextField;
    private javax.swing.JButton saveButton;
    private javax.swing.JMenuItem saveMenuItem;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JTable table;
    private javax.swing.JLabel totalLabel;
    private javax.swing.JTextField totalTextField;
    // End of variables declaration//GEN-END:variables

}
