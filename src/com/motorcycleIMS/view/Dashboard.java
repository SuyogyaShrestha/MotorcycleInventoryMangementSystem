/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.motorcycleIMS.view;

import com.motorcycleIMS.controller.algorithms.BinarySearch;
import com.motorcycleIMS.model.ItemsModel;
import com.motorcycleIMS.util.ValidationUtil;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import com.motorcycleIMS.controller.algorithms.SelectionSort;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Suyogya Shrestha
 */
public class Dashboard extends javax.swing.JFrame {

    private ArrayList<ItemsModel> item = new ArrayList<>();
    private SelectionSort selectionSort;
    private double sellingPrice;
    private double VAT = 0.13;
    private double TotalRevenue = 0;
    private int TotalSold = 0;
    
    public Dashboard() {
        initComponents();
        registerItems(new ItemsModel(47825,"Crash bars",10,5000,"SW Motech","KTM 250 Duke"));
        registerItems(new ItemsModel(93214,"Akrapovič full system Exhaust",7,60000,"Akrapovič","Any"));
        registerItems(new ItemsModel(15768,"Michelin Road 6 Tires",20,22000,"Michelin","Any"));
        registerItems(new ItemsModel(82490,"R2R Pro Fullface Helmet",15,26000,"KYT","-"));
        registerItems(new ItemsModel(58401,"Pro Skid Plate",36,15500,"Moose Racing","Honda CRF300L"));
        registerItems(new ItemsModel(39359,"Front Brake Pads",49,11000,"EBC Brakes","BMW S1000RR"));
        selectionSort = new SelectionSort();
        DataUpdates();
    }
    private void registerItems(ItemsModel items){
        item.add(items);
        javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) ItemsTable.getModel();
        model.addRow(new Object[]{items.getItemCode(),items.getName(),
            items.getQuantity(),items.getmPrice(),items.getSupplier(),
            items.getBikeModel()});
    }
    private void addItems() {
        boolean isValid = ValidationProcess();
        if(isValid==true){
            try{
                int itemCode = Integer.parseInt(ItemCodeTF.getText());
                String name = NameTF.getText();
                int quantity = Integer.parseInt(QuantityTF.getText());
                double mPrice = Double.parseDouble(MarkedPriceTF.getText());
                String supplier = SupplierTF.getText();
                String bikeModel = BikeModelTF.getText();
        

                for (ItemsModel products : item) {
                    if (products.getItemCode()==itemCode) {
                        JOptionPane.showMessageDialog(this, "Item with this code already exists!");
                        return;
                    }
                }

                ItemsModel newItem = new ItemsModel(itemCode,name,quantity,mPrice,supplier,bikeModel);
                item.add(newItem);
                javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) ItemsTable.getModel();
                model.addRow(new Object[]{newItem.getItemCode(),newItem.getName(),newItem.getQuantity(),newItem.getmPrice(),
                    newItem.getSupplier(),newItem.getBikeModel()});
                ItemCodeTF.setText("");
                NameTF.setText("");
                QuantityTF.setText("");
                MarkedPriceTF.setText("");
                SupplierTF.setText("");
                BikeModelTF.setText("");
                JOptionPane.showMessageDialog(this, "Item added successfully!");
            }
            catch(NumberFormatException n){
                JOptionPane.showMessageDialog(this, "Invalid input! Please enter numeric value");
            }
        }
    }
    private void updateItems() {
        boolean isValid = ValidationProcess();
        if(isValid==true){
            try{
                int selectedRow = ItemsTable.getSelectedRow();
                if (selectedRow == -1) {
                    JOptionPane.showMessageDialog(this, "Please select a row to update.");
                    return;
                }

                int itemCode = Integer.parseInt(ItemCodeTF.getText());
                String name = NameTF.getText();
                int quantity = Integer.parseInt(QuantityTF.getText());
                double mPrice = Double.parseDouble(MarkedPriceTF.getText());
                String supplier = SupplierTF.getText();
                String bikeModel = BikeModelTF.getText();

                ItemsModel products = item.get(selectedRow);
                products.setName(name);
                products.setQuantity(quantity);
                products.setmPrice(mPrice);
                products.setSupplier(supplier);
                products.setBikeModel(bikeModel);
        
                javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) ItemsTable.getModel();
                model.setValueAt(itemCode, selectedRow, 0);
                model.setValueAt(name, selectedRow, 1);
                model.setValueAt(quantity, selectedRow, 2);
                model.setValueAt(mPrice, selectedRow, 3);
                model.setValueAt(supplier, selectedRow, 4);
                model.setValueAt(bikeModel, selectedRow, 5);

                ItemCodeTF.setText("");
                NameTF.setText("");
                QuantityTF.setText("");
                MarkedPriceTF.setText("");
                SupplierTF.setText("");
                BikeModelTF.setText("");
                JOptionPane.showMessageDialog(this, "Item updated!");
            }
            catch(NumberFormatException n){
                JOptionPane.showMessageDialog(this, "Invalid input! Please enter numeric value");
            }
        }
    }
    
    private void removeItem() {
        int selectedRow = ItemsTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a row to delete.");
            return;
        }

        item.remove(selectedRow);
        javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) ItemsTable.getModel();
        model.removeRow(selectedRow);
        
        JOptionPane.showMessageDialog(this, "Item removed!");
    }
    
    private boolean ValidationProcess(){
        int num;
        num = ValidationUtil.validateItemCode(ItemCodeTF.getText());
        if(num==1){
            itemCodeError.setText("cannot be empty!");
        }
        else if(num==2){
            itemCodeError.setText("must be 5 digits!");
        }
        else if(num==3){
            itemCodeError.setText("should be integers!");
        }
        else{
            itemCodeError.setText("");
        }
        
        int num2;
        num2 = ValidationUtil.validateName(NameTF.getText());
        if(num2==1){
            NameError.setText("cannot be empty!");
        } 
        else{
            NameError.setText("");
        }
        
        int num3;
        num3 = ValidationUtil.validateQuantity(QuantityTF.getText());
        if(num3==1){
            QuantityError.setText("cannot be empty!");
        }
        else if(num3==2){
            QuantityError.setText("cannot be negative or zero!");
        }
        else if(num3==3){
            QuantityError.setText("cannot be string!");
        }
        else{
            QuantityError.setText("");
        }
        
        int num4;
        num4 = ValidationUtil.validateMP(MarkedPriceTF.getText());
        if(num4==1){
            MpError.setText("cannot be empty!");
        }
        else if(num4==2){
            MpError.setText("cannot be negative or zero!");
        }
        else if(num4==3){
            MpError.setText("cannot be string!");
        }
        else{
            MpError.setText("");
        }
        
        int num5;
        num5 = ValidationUtil.validateName(SupplierTF.getText());
        if(num5==1){
            SupplierError.setText("cannot be empty!");
        }
        else{
            SupplierError.setText("");
        }
        
        int num6;
        num6 = ValidationUtil.validateName(BikeModelTF.getText());
        if(num6==1){
            BikeModelError.setText("cannot be empty!");
        }
        else{
            BikeModelError.setText("");
        }
        
        if(num==0 && num2==0 && num3==0 && num4==0 && num5==0 && num6==0){
            return true;
        }
        else{
            return false;
        }
    }
    
    private void DataUpdates(){ 
        TotalUnqItems.setText(String.valueOf(item.size()));
        int total_item_quantity = 0;
        for (int i = 0; i < item.size(); i++) {
            total_item_quantity += item.get(i).getQuantity();
        }
        TotItemQuantity.setText(String.valueOf(total_item_quantity));
        TotalSoldLabel.setText(String.valueOf(TotalSold));
        TotalRevenueLabel.setText(String.valueOf(TotalRevenue));
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        MenuButton = new javax.swing.JButton();
        Logo = new javax.swing.JLabel();
        LogoutButton = new javax.swing.JButton();
        BikeIcon = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        WelcomeMessage = new javax.swing.JLabel();
        jPanel21 = new javax.swing.JPanel();
        Banner = new javax.swing.JLabel();
        jPanel22 = new javax.swing.JPanel();
        jPanel23 = new javax.swing.JPanel();
        Product1 = new javax.swing.JLabel();
        jPanel24 = new javax.swing.JPanel();
        Product2 = new javax.swing.JLabel();
        jPanel25 = new javax.swing.JPanel();
        Product3 = new javax.swing.JLabel();
        jPanel26 = new javax.swing.JPanel();
        Product4 = new javax.swing.JLabel();
        jPanel27 = new javax.swing.JPanel();
        Product5 = new javax.swing.JLabel();
        jPanel28 = new javax.swing.JPanel();
        KYTlogo = new javax.swing.JLabel();
        AkrapovicLogo = new javax.swing.JLabel();
        BMWlogo = new javax.swing.JLabel();
        alpinestarsLogo = new javax.swing.JLabel();
        KTMlogo = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        TotalSoldLabel = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jLabel34 = new javax.swing.JLabel();
        TotalRevenueLabel = new javax.swing.JLabel();
        jPanel14 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        TotalUnqItems = new javax.swing.JLabel();
        TotItemQuantity = new javax.swing.JLabel();
        jPanel16 = new javax.swing.JPanel();
        jPanel29 = new javax.swing.JPanel();
        ManagementLogo = new javax.swing.JLabel();
        GoToIMS = new javax.swing.JButton();
        jPanel18 = new javax.swing.JPanel();
        BStextfield = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        goSearch = new javax.swing.JButton();
        jLabel28 = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        searchedName = new javax.swing.JLabel();
        searchedQuantity = new javax.swing.JLabel();
        searchedPrice = new javax.swing.JLabel();
        searchedSupplier = new javax.swing.JLabel();
        searchedModel = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ItemsTable = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        SearchField = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        SortComboBox = new javax.swing.JComboBox<>();
        jLabel23 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        ItemCodeTF = new javax.swing.JTextField();
        MarkedPriceTF = new javax.swing.JTextField();
        NameTF = new javax.swing.JTextField();
        SupplierTF = new javax.swing.JTextField();
        QuantityTF = new javax.swing.JTextField();
        BikeModelTF = new javax.swing.JTextField();
        AddButton = new javax.swing.JButton();
        UpdateButton = new javax.swing.JButton();
        DeleteButton = new javax.swing.JButton();
        ClearButton = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        sellButton = new javax.swing.JButton();
        itemCodeError = new javax.swing.JLabel();
        MpError = new javax.swing.JLabel();
        SupplierError = new javax.swing.JLabel();
        BikeModelError = new javax.swing.JLabel();
        NameError = new javax.swing.JLabel();
        QuantityError = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel30 = new javax.swing.JPanel();
        jPanel31 = new javax.swing.JPanel();
        jLabel38 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jPanel32 = new javax.swing.JPanel();
        Logo1 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        UserIcon = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        CopyrightLabel = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(51, 153, 0));
        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        MenuButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/motorcycleIMS/resources/menubutton1.png"))); // NOI18N
        MenuButton.setFocusable(false);
        MenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuButtonActionPerformed(evt);
            }
        });

        Logo.setFont(new java.awt.Font("Ink Free", 1, 48)); // NOI18N
        Logo.setForeground(new java.awt.Color(0, 0, 102));
        Logo.setText("REVEL");

        LogoutButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/motorcycleIMS/resources/logout1.png"))); // NOI18N
        LogoutButton.setBorderPainted(false);
        LogoutButton.setFocusPainted(false);
        LogoutButton.setFocusable(false);
        LogoutButton.setRequestFocusEnabled(false);
        LogoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LogoutButtonActionPerformed(evt);
            }
        });

        BikeIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/motorcycleIMS/resources/bike13.jpg"))); // NOI18N
        BikeIcon.setText("jLabel2");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(MenuButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Logo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BikeIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 789, Short.MAX_VALUE)
                .addComponent(LogoutButton)
                .addGap(15, 15, 15))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(Logo)
                            .addComponent(BikeIcon)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(MenuButton)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(LogoutButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1120, 70));

        jTabbedPane1.setBackground(new java.awt.Color(0, 153, 153));
        jTabbedPane1.setForeground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setAutoscrolls(true);
        jTabbedPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTabbedPane1.setFocusable(false);
        jTabbedPane1.setRequestFocusEnabled(false);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jPanel19.setBackground(new java.awt.Color(153, 204, 255));

        jPanel20.setBackground(new java.awt.Color(102, 0, 102));

        WelcomeMessage.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        WelcomeMessage.setForeground(new java.awt.Color(255, 255, 255));
        WelcomeMessage.setText("Welcome to REVEL Motorcycles Parts & Accessories Management System");

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(144, 144, 144)
                .addComponent(WelcomeMessage)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(WelcomeMessage)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        Banner.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/motorcycleIMS/resources/MotorcycleR1M.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Banner, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Banner, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jPanel22.setBackground(new java.awt.Color(0, 204, 204));

        jPanel23.setBackground(new java.awt.Color(255, 255, 255));

        Product1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/motorcycleIMS/resources/KYTr2rPro.png"))); // NOI18N

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Product1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Product1, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel24.setBackground(new java.awt.Color(255, 255, 255));

        Product2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/motorcycleIMS/resources/arkrapovicExhaust01.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Product2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Product2, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jPanel25.setBackground(new java.awt.Color(255, 255, 255));

        Product3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/motorcycleIMS/resources/crashbars01.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Product3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Product3, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel26.setBackground(new java.awt.Color(255, 255, 255));

        Product4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/motorcycleIMS/resources/tires01.png"))); // NOI18N

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Product4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Product4, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel27.setBackground(new java.awt.Color(255, 255, 255));

        Product5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/motorcycleIMS/resources/proskidplate01.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Product5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Product5, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(17, 17, 17))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel28.setBackground(new java.awt.Color(51, 0, 153));

        KYTlogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/motorcycleIMS/resources/kytlogo.png"))); // NOI18N

        AkrapovicLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/motorcycleIMS/resources/akhrapovic1234.png"))); // NOI18N

        BMWlogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/motorcycleIMS/resources/BMWlogo.png"))); // NOI18N

        alpinestarsLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/motorcycleIMS/resources/alpinestars20.png"))); // NOI18N

        KTMlogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/motorcycleIMS/resources/KTM12.png"))); // NOI18N

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(AkrapovicLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(97, 97, 97)
                .addComponent(KYTlogo, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(92, 92, 92)
                .addComponent(BMWlogo, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(alpinestarsLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(97, 97, 97)
                .addComponent(KTMlogo, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(KYTlogo, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
            .addComponent(AkrapovicLogo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(BMWlogo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(alpinestarsLogo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(KTMlogo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Home", jPanel3);

        jPanel15.setBackground(new java.awt.Color(153, 204, 255));

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));

        jLabel35.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel35.setText("Total Items Sold:");

        TotalSoldLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addComponent(jLabel35)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(TotalSoldLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel35)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TotalSoldLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(84, Short.MAX_VALUE))
        );

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));

        jLabel34.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel34.setText("Total Revenue:");

        TotalRevenueLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel34)
                        .addGap(0, 146, Short.MAX_VALUE))
                    .addComponent(TotalRevenueLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel34)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TotalRevenueLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(84, Short.MAX_VALUE))
        );

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));

        jLabel24.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel24.setText("Current Inventory Levels:");

        jLabel25.setText("Total Item Quantity:");

        jLabel26.setText("Total Unique Items:");

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel24)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jLabel25)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(TotItemQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(TotalUnqItems, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(48, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel24)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(TotItemQuantity))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(TotalUnqItems))
                .addContainerGap(83, Short.MAX_VALUE))
        );

        jPanel16.setBackground(new java.awt.Color(0, 102, 153));

        jPanel29.setBackground(new java.awt.Color(51, 153, 255));

        ManagementLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/motorcycleIMS/resources/INVMS.png"))); // NOI18N

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ManagementLogo, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ManagementLogo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        GoToIMS.setText("Manage Inventory");
        GoToIMS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GoToIMSActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(GoToIMS, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(GoToIMS, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanel18.setBackground(new java.awt.Color(0, 102, 153));

        BStextfield.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BStextfieldActionPerformed(evt);
            }
        });

        jLabel27.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("Search for specific Product Details:");

        goSearch.setText("Go");
        goSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                goSearchActionPerformed(evt);
            }
        });

        jLabel28.setBackground(new java.awt.Color(255, 255, 255));
        jLabel28.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setText("Enter Item Code:");

        jPanel17.setBackground(new java.awt.Color(255, 255, 255));

        jLabel29.setText("Item Name:");

        jLabel30.setText("Quantity:");

        jLabel31.setText("Marked Price:");

        jLabel32.setText("Supplier:");

        jLabel33.setText("Bike Model:");

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel29)
                    .addComponent(jLabel32)
                    .addComponent(jLabel33)
                    .addComponent(jLabel31)
                    .addComponent(jLabel30))
                .addGap(62, 62, 62)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(searchedSupplier, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(searchedPrice, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(searchedQuantity, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(searchedName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
                    .addComponent(searchedModel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(43, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(searchedName))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchedQuantity))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(searchedPrice))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(searchedSupplier))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(searchedModel))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addComponent(jLabel28)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(BStextfield, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(goSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel27)
                    .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel27)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BStextfield, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(goSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel28))
                .addGap(10, 10, 10)
                .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel14.setText(" Admin Dashboard");

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(36, 36, 36)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(22, 22, 22))))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel14)
                .addGap(27, 27, 27)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel18, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Dashboard", jPanel6);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        jPanel10.setBackground(new java.awt.Color(153, 204, 255));
        jPanel10.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 204, 255), 2, true));

        ItemsTable.setBackground(new java.awt.Color(102, 153, 255));
        ItemsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Item Code", "Name", "Quantity", "Marked Price", "Supplier", "Bike Model"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        ItemsTable.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane1.setViewportView(ItemsTable);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel5.setText("Parts & Accessories List:");

        SearchField.setBackground(new java.awt.Color(153, 204, 255));
        SearchField.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 102)));
        SearchField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchFieldActionPerformed(evt);
            }
        });
        SearchField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SearchFieldKeyPressed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel15.setText("Search:");

        SortComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item Code (Asc)", "Item Code (Dsc)", "Name (Asc)", "Name (Dsc)", "Price (Low to High)", "Price (High to Low)" }));
        SortComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SortComboBoxActionPerformed(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel23.setText("Sort by:");

        jLabel37.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel37.setText("13% VAT included ");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel37)
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 895, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel10Layout.createSequentialGroup()
                            .addComponent(jLabel5)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel15)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(SearchField, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(116, 116, 116)
                            .addComponent(jLabel23)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(SortComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(SearchField, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SortComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jLabel37)
                .addContainerGap())
        );

        jPanel11.setBackground(new java.awt.Color(0, 204, 204));
        jPanel11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 204), 2));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setText("Product Form:");

        ItemCodeTF.setBackground(new java.awt.Color(102, 204, 255));
        ItemCodeTF.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 153), 2));
        ItemCodeTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ItemCodeTFActionPerformed(evt);
            }
        });

        MarkedPriceTF.setBackground(new java.awt.Color(102, 204, 255));
        MarkedPriceTF.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 153), 2));

        NameTF.setBackground(new java.awt.Color(102, 204, 255));
        NameTF.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 153), 2));

        SupplierTF.setBackground(new java.awt.Color(102, 204, 255));
        SupplierTF.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 153), 2));

        QuantityTF.setBackground(new java.awt.Color(102, 204, 255));
        QuantityTF.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 153), 2));

        BikeModelTF.setBackground(new java.awt.Color(102, 204, 255));
        BikeModelTF.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 153), 2));

        AddButton.setBackground(new java.awt.Color(0, 0, 102));
        AddButton.setFont(new java.awt.Font("Segoe UI Semibold", 1, 12)); // NOI18N
        AddButton.setForeground(new java.awt.Color(255, 255, 255));
        AddButton.setText("ADD");
        AddButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddButtonActionPerformed(evt);
            }
        });

        UpdateButton.setBackground(new java.awt.Color(0, 0, 102));
        UpdateButton.setFont(new java.awt.Font("Segoe UI Semibold", 1, 12)); // NOI18N
        UpdateButton.setForeground(new java.awt.Color(255, 255, 255));
        UpdateButton.setText("UPDATE");
        UpdateButton.setPreferredSize(new java.awt.Dimension(72, 23));
        UpdateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UpdateButtonActionPerformed(evt);
            }
        });

        DeleteButton.setBackground(new java.awt.Color(0, 0, 102));
        DeleteButton.setFont(new java.awt.Font("Segoe UI Semibold", 1, 12)); // NOI18N
        DeleteButton.setForeground(new java.awt.Color(255, 255, 255));
        DeleteButton.setText("DELETE");
        DeleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteButtonActionPerformed(evt);
            }
        });

        ClearButton.setBackground(new java.awt.Color(0, 0, 102));
        ClearButton.setFont(new java.awt.Font("Segoe UI Semibold", 1, 12)); // NOI18N
        ClearButton.setForeground(new java.awt.Color(255, 255, 255));
        ClearButton.setText("CLEAR");
        ClearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClearButtonActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI Semibold", 1, 12)); // NOI18N
        jLabel7.setText("Item Code:");

        jLabel8.setFont(new java.awt.Font("Segoe UI Semibold", 1, 12)); // NOI18N
        jLabel8.setText("Name:");

        jLabel9.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N
        jLabel9.setText("Quantity:");

        jLabel10.setFont(new java.awt.Font("Segoe UI Semibold", 1, 12)); // NOI18N
        jLabel10.setText("Marked Price:");

        jLabel11.setFont(new java.awt.Font("Segoe UI Semibold", 1, 12)); // NOI18N
        jLabel11.setText("Supplier:");

        jLabel12.setFont(new java.awt.Font("Segoe UI Semibold", 1, 12)); // NOI18N
        jLabel12.setText("Bike Model:");

        sellButton.setBackground(new java.awt.Color(0, 102, 102));
        sellButton.setFont(new java.awt.Font("Segoe UI Semibold", 1, 12)); // NOI18N
        sellButton.setForeground(new java.awt.Color(255, 255, 255));
        sellButton.setText("SELL ITEM");
        sellButton.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true));
        sellButton.setBorderPainted(false);
        sellButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sellButtonActionPerformed(evt);
            }
        });

        itemCodeError.setForeground(new java.awt.Color(255, 0, 0));

        MpError.setForeground(new java.awt.Color(255, 51, 0));

        SupplierError.setForeground(new java.awt.Color(255, 0, 0));

        BikeModelError.setForeground(new java.awt.Color(255, 51, 51));

        NameError.setForeground(new java.awt.Color(255, 51, 51));

        QuantityError.setForeground(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ItemCodeTF, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(MarkedPriceTF, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel10)
                    .addComponent(itemCodeError)
                    .addComponent(MpError))
                .addGap(38, 38, 38)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(SupplierTF, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                        .addComponent(NameTF))
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(SupplierError)
                    .addComponent(NameError))
                .addGap(40, 40, 40)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jLabel12)
                    .addComponent(BikeModelTF, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(QuantityTF, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(QuantityError)
                    .addComponent(BikeModelError))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sellButton, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(DeleteButton, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
                            .addComponent(AddButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(ClearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(UpdateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(20, 20, 20))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(jLabel6)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel8)
                                .addComponent(jLabel9))
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ItemCodeTF, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(NameTF, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(QuantityTF, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(NameError)
                            .addComponent(QuantityError)
                            .addComponent(itemCodeError))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(MarkedPriceTF, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(SupplierTF, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BikeModelTF, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(MpError)
                                    .addComponent(SupplierError))
                                .addGap(25, 25, 25))
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BikeModelError)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(sellButton, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(AddButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(UpdateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(DeleteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ClearButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(42, 42, 42))))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, 215, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Item Management", jPanel7);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 940, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 555, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Settings", jPanel5);

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jPanel30.setBackground(new java.awt.Color(153, 204, 255));

        jPanel31.setBackground(new java.awt.Color(255, 255, 255));
        jPanel31.setForeground(new java.awt.Color(0, 204, 204));

        jLabel38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/motorcycleIMS/resources/aboutUS123.jpg"))); // NOI18N
        jLabel38.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 204, 204)));

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jTextArea1.setText("The REVEL platform is built by people who are passionate about motorcycles and dedicated to enhancing your experience, whether you're a \ndealer, distributor, or a motorcycle enthusiast. Our system is designed to simplify the way you manage, track, and access motorcycle parts and \naccessories, making your operations smoother and more efficient.\n\nOur goal is to provide the motorcycling community with modernized tools that rationalize management of inventory as well as improve\ncustomer satisfaction which is our number one priority. With REVEL, you can easily manage your inventory, track product availability, and \nensure flawless transactions.\n\nOur belief in innovation, reliability, and community outlines the company's vision. Built for motorcycle enthusiasts by motorcycle enthusiasts, \nREVEL is developed with both technical expertise and deep understanding of the motorcycling community.\n\nJoin us in revolutionizing the way motorcycle parts and accessories are managed. Whether you're running a bustling shop or searching for \nthe perfect part, REVEL is here to help you ride further, faster, and more confidently.\n");
        jTextArea1.setAutoscrolls(false);
        jTextArea1.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jScrollPane2.setViewportView(jTextArea1);

        jLabel39.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel39.setText("Unleash Your Inner Rebel");

        jLabel40.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel40.setText("Ignite Your Passion");

        javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
        jPanel31.setLayout(jPanel31Layout);
        jPanel31Layout.setHorizontalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel31Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 772, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel31Layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addComponent(jLabel39)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel40)))
                .addContainerGap(17, Short.MAX_VALUE))
        );
        jPanel31Layout.setVerticalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel31Layout.createSequentialGroup()
                        .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel31Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel38, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel31Layout.createSequentialGroup()
                                .addGap(118, 118, 118)
                                .addComponent(jLabel40)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel31Layout.createSequentialGroup()
                        .addComponent(jLabel39)
                        .addGap(116, 116, 116)))
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanel32.setBackground(new java.awt.Color(0, 51, 102));
        jPanel32.setForeground(new java.awt.Color(0, 51, 102));

        Logo1.setFont(new java.awt.Font("Ink Free", 1, 36)); // NOI18N
        Logo1.setForeground(new java.awt.Color(255, 255, 255));
        Logo1.setText("REVEL");

        javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
        jPanel32.setLayout(jPanel32Layout);
        jPanel32Layout.setHorizontalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addGap(402, 402, 402)
                .addComponent(Logo1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel32Layout.setVerticalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel32Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(Logo1))
        );

        javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
        jPanel30.setLayout(jPanel30Layout);
        jPanel30Layout.setHorizontalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel32, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addGap(61, 61, 61)
                .addComponent(jPanel31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(60, Short.MAX_VALUE))
        );
        jPanel30Layout.setVerticalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addComponent(jPanel32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jPanel31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("About Us", jPanel4);

        jPanel1.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 70, 940, 590));

        jPanel8.setBackground(new java.awt.Color(0, 102, 153));
        jPanel8.setForeground(new java.awt.Color(255, 255, 255));

        UserIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/motorcycleIMS/resources/user12.png"))); // NOI18N

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Logged in as:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("ADMIN");

        CopyrightLabel.setForeground(new java.awt.Color(255, 255, 255));
        CopyrightLabel.setText("Copyright © REVEL IMS");

        jPanel9.setBackground(new java.awt.Color(0, 0, 102));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 180, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 224, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(UserIcon)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel1))))
                .addGap(49, 49, 49))
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(CopyrightLabel)
                .addContainerGap(32, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(UserIcon)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 149, Short.MAX_VALUE)
                .addComponent(CopyrightLabel)
                .addContainerGap())
        );

        jPanel1.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 180, 590));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void MenuButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MenuButtonActionPerformed

    private void LogoutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LogoutButtonActionPerformed
        // TODO add your handling code here:
        this.dispose();
        new LoginForm().setVisible(true);
    }//GEN-LAST:event_LogoutButtonActionPerformed

    private void sellButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sellButtonActionPerformed
        // TODO add your handling code here:
        int selectedRow = ItemsTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select an item sell.");
            return;
        }
        int Aquantity = item.get(selectedRow).getQuantity();
        double price = item.get(selectedRow).getmPrice();
        JTextField itemQuantityField = new JTextField();
        Object[] fields = {"Quantity",itemQuantityField};
        int result = JOptionPane.showConfirmDialog(null,fields,"Sell Item",JOptionPane.OK_CANCEL_OPTION);
        try{
            if(result==JOptionPane.OK_OPTION){
                int quantity = Integer.parseInt(itemQuantityField.getText());
                if(quantity<1){
                    JOptionPane.showMessageDialog(this, "Required Quantity must be greater than 0.");
                    return;
                }
                else if(quantity>Aquantity){
                    JOptionPane.showMessageDialog(this, "Required Quantity must be within the available stock.");
                    return;
                }
                else{
                    sellingPrice = price*quantity + price*quantity*VAT;
                    TotalRevenue += sellingPrice;
                    TotalSold += quantity;
                }
                int netQuant = Aquantity - quantity;
                ItemsModel products = item.get(selectedRow);
                products.setQuantity(netQuant);

                javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) ItemsTable.getModel();
                model.setValueAt(netQuant, selectedRow, 2);
                JOptionPane.showMessageDialog(this, "Item Sold!");
            }
        }
        catch(NumberFormatException n){
            JOptionPane.showMessageDialog(this, "Invalid input! Please enter numeric value");
        }
        DataUpdates();
    }//GEN-LAST:event_sellButtonActionPerformed

    private void ClearButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClearButtonActionPerformed
        // TODO add your handling code here:
        ItemCodeTF.setText("");
        NameTF.setText("");
        QuantityTF.setText("");
        MarkedPriceTF.setText("");
        SupplierTF.setText("");
        BikeModelTF.setText("");
    }//GEN-LAST:event_ClearButtonActionPerformed

    private void DeleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteButtonActionPerformed
        // TODO add your handling code here:
        removeItem();
        DataUpdates();
    }//GEN-LAST:event_DeleteButtonActionPerformed

    private void UpdateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_UpdateButtonActionPerformed
        // TODO add your handling code here:
        updateItems();
        DataUpdates();
    }//GEN-LAST:event_UpdateButtonActionPerformed

    private void AddButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddButtonActionPerformed
        // TODO add your handling code here:
        addItems();
        DataUpdates();
    }//GEN-LAST:event_AddButtonActionPerformed

    private void ItemCodeTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ItemCodeTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ItemCodeTFActionPerformed

    private void SortComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SortComboBoxActionPerformed
        // TODO add your handling code here:
        try{
            if (SortComboBox.getSelectedItem()=="Item Code (Asc)"){
                ArrayList<ItemsModel> sortedList = selectionSort.sortByItemCode(item, false);

                javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) ItemsTable.getModel();
                model.setRowCount(0);
                sortedList.forEach(product -> model.addRow(new Object[]{
                    product.getItemCode(),
                    product.getName(),
                    product.getQuantity(),
                    product.getmPrice(),
                    product.getSupplier(),
                    product.getBikeModel()
                }));
            }
            else if (SortComboBox.getSelectedItem()=="Item Code (Dsc)"){
                ArrayList<ItemsModel> sortedList = selectionSort.sortByItemCode(item, true);

                javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) ItemsTable.getModel();
                model.setRowCount(0);
                sortedList.forEach(product -> model.addRow(new Object[]{
                    product.getItemCode(),
                    product.getName(),
                    product.getQuantity(),
                    product.getmPrice(),
                    product.getSupplier(),
                    product.getBikeModel()
                }));
            }
            else if (SortComboBox.getSelectedItem()=="Name (Asc)"){
                ArrayList<ItemsModel> sortedList = selectionSort.sortByName(item, false);

                javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) ItemsTable.getModel();
                model.setRowCount(0);
                sortedList.forEach(product -> model.addRow(new Object[]{
                    product.getItemCode(),
                    product.getName(),
                    product.getQuantity(),
                    product.getmPrice(),
                    product.getSupplier(),
                    product.getBikeModel()
                }));
            }

            else if (SortComboBox.getSelectedItem()=="Name (Dsc)"){
                ArrayList<ItemsModel> sortedList = selectionSort.sortByName(item, true);

                javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) ItemsTable.getModel();
                model.setRowCount(0);
                sortedList.forEach(product -> model.addRow(new Object[]{
                    product.getItemCode(),
                    product.getName(),
                    product.getQuantity(),
                    product.getmPrice(),
                    product.getSupplier(),
                    product.getBikeModel()
                }));
            }

            else if (SortComboBox.getSelectedItem()=="Price (Low to High)"){
                ArrayList<ItemsModel> sortedList = selectionSort.sortByPrice(item, false);

                javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) ItemsTable.getModel();
                model.setRowCount(0);
                sortedList.forEach(product -> model.addRow(new Object[]{
                    product.getItemCode(),
                    product.getName(),
                    product.getQuantity(),
                    product.getmPrice(),
                    product.getSupplier(),
                    product.getBikeModel()
                }));
            }

            else if (SortComboBox.getSelectedItem()=="Price (High to Low)"){
                ArrayList<ItemsModel> sortedList = selectionSort.sortByPrice(item, true);

                javax.swing.table.DefaultTableModel model = (javax.swing.table.DefaultTableModel) ItemsTable.getModel();
                model.setRowCount(0);
                sortedList.forEach(product -> model.addRow(new Object[]{
                    product.getItemCode(),
                    product.getName(),
                    product.getQuantity(),
                    product.getmPrice(),
                    product.getSupplier(),
                    product.getBikeModel()
                }));
            }
        }
        catch(IllegalArgumentException e){
            JOptionPane.showMessageDialog(this, "Item list cannot be null or empty.");
        }
    }//GEN-LAST:event_SortComboBoxActionPerformed

    private void SearchFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SearchFieldKeyPressed
        // TODO add your handling code here:
        javax.swing.table.DefaultTableModel search = (javax.swing.table.DefaultTableModel) ItemsTable.getModel();
        TableRowSorter<DefaultTableModel> searches = new TableRowSorter<>(search);
        ItemsTable.setRowSorter(searches);
        searches.setRowFilter(RowFilter.regexFilter(SearchField.getText()));
    }//GEN-LAST:event_SearchFieldKeyPressed

    private void SearchFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_SearchFieldActionPerformed

    private void goSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_goSearchActionPerformed
        // TODO add your handling code here:
        SelectionSort selectionSort = new SelectionSort();
        ArrayList<ItemsModel> sortedList = selectionSort.sortByItemCode(item, false);
        BinarySearch search = new BinarySearch();
        ItemsModel searchedData = search.searchByItemCode(Integer.parseInt(BStextfield.getText()), sortedList, 0, sortedList.size()-1);
        if(searchedData!=null){
            searchedName.setText(searchedData.getName());
            searchedQuantity.setText(Integer.toString(searchedData.getQuantity()));
            searchedPrice.setText(Double.toString(searchedData.getmPrice()));
            searchedSupplier.setText(searchedData.getSupplier());
            searchedModel.setText(searchedData.getBikeModel());
        }else{
            JOptionPane.showMessageDialog(this, "Item Not Found!");
            searchedName.setText("");
            searchedQuantity.setText("");
            searchedPrice.setText("");
            searchedSupplier.setText("");
            searchedModel.setText("");
        }
    }//GEN-LAST:event_goSearchActionPerformed

    private void BStextfieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BStextfieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_BStextfieldActionPerformed

    private void GoToIMSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GoToIMSActionPerformed
        // TODO add your handling code here:
        jTabbedPane1.setSelectedIndex(2);
    }//GEN-LAST:event_GoToIMSActionPerformed

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
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Dashboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Dashboard().setVisible(true);
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddButton;
    private javax.swing.JLabel AkrapovicLogo;
    private javax.swing.JLabel BMWlogo;
    private javax.swing.JTextField BStextfield;
    private javax.swing.JLabel Banner;
    private javax.swing.JLabel BikeIcon;
    private javax.swing.JLabel BikeModelError;
    private javax.swing.JTextField BikeModelTF;
    private javax.swing.JButton ClearButton;
    private javax.swing.JLabel CopyrightLabel;
    private javax.swing.JButton DeleteButton;
    private javax.swing.JButton GoToIMS;
    private javax.swing.JTextField ItemCodeTF;
    private javax.swing.JTable ItemsTable;
    private javax.swing.JLabel KTMlogo;
    private javax.swing.JLabel KYTlogo;
    private javax.swing.JLabel Logo;
    private javax.swing.JLabel Logo1;
    private javax.swing.JButton LogoutButton;
    private javax.swing.JLabel ManagementLogo;
    private javax.swing.JTextField MarkedPriceTF;
    private javax.swing.JButton MenuButton;
    private javax.swing.JLabel MpError;
    private javax.swing.JLabel NameError;
    private javax.swing.JTextField NameTF;
    private javax.swing.JLabel Product1;
    private javax.swing.JLabel Product2;
    private javax.swing.JLabel Product3;
    private javax.swing.JLabel Product4;
    private javax.swing.JLabel Product5;
    private javax.swing.JLabel QuantityError;
    private javax.swing.JTextField QuantityTF;
    private javax.swing.JTextField SearchField;
    private javax.swing.JComboBox<String> SortComboBox;
    private javax.swing.JLabel SupplierError;
    private javax.swing.JTextField SupplierTF;
    private javax.swing.JLabel TotItemQuantity;
    private javax.swing.JLabel TotalRevenueLabel;
    private javax.swing.JLabel TotalSoldLabel;
    private javax.swing.JLabel TotalUnqItems;
    private javax.swing.JButton UpdateButton;
    private javax.swing.JLabel UserIcon;
    private javax.swing.JLabel WelcomeMessage;
    private javax.swing.JLabel alpinestarsLogo;
    private javax.swing.JButton goSearch;
    private javax.swing.JLabel itemCodeError;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel searchedModel;
    private javax.swing.JLabel searchedName;
    private javax.swing.JLabel searchedPrice;
    private javax.swing.JLabel searchedQuantity;
    private javax.swing.JLabel searchedSupplier;
    private javax.swing.JButton sellButton;
    // End of variables declaration//GEN-END:variables
}
