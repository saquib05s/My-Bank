/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bank_application;

import javax.swing.JOptionPane;

/**
 *
 * @author SAQUIB SUGLATWALA
 */
public class FundTransfer extends javax.swing.JFrame {

    /**
     * Creates new form FundTransfer
     */
    public FundTransfer() {
        initComponents();
    
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
        lblEA = new javax.swing.JLabel();
        lblTAN = new javax.swing.JLabel();
        txtfldEA = new javax.swing.JTextField();
        txtfldTAN = new javax.swing.JTextField();
        btnTransfer = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Fund Transfer");

        lblEA.setText("Enter Amount");

        lblTAN.setText("To Account Number");

        btnTransfer.setText("Transfer");
        btnTransfer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTransferActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblTAN, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
                            .addComponent(lblEA, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(33, 33, 33)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtfldTAN, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtfldEA, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(145, 145, 145)
                        .addComponent(btnTransfer, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(175, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTAN, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtfldTAN, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtfldEA, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEA, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(btnTransfer, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(72, Short.MAX_VALUE))
        );

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
    public boolean ValidateField()
    {
        if(txtfldTAN.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(this, "Enter Account Number");
            txtfldTAN.grabFocus();
            return false;
        }
        if(txtfldEA.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(this, "Enter Amount");
            txtfldEA.grabFocus();
            return false;
        }
        if(Integer.parseInt(txtfldEA.getText())==0){
            JOptionPane.showMessageDialog(this, "Amount should be greater than 0");
            txtfldEA.grabFocus();
            return false;
        }
        else
            return true;
    }
    private void btnTransferActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTransferActionPerformed
        // TODO add your handling code here:
        String Num1=StartingFrame.txtfldLogin.getText();
        if(ValidateField())
        {
            String Num2=txtfldTAN.getText();
            Double Amt=Double.parseDouble(txtfldEA.getText());
            
            java.util.Date d=new java.util.Date();
            String dates[]=d.toString().split(" ");
            
            Bank_Application GLD=new Bank_Application();
            String ans=GLD.transfer(Num1, Num2, Amt);
            if(ans=="Account Does Not Exist"){
                JOptionPane.showMessageDialog(this,"Sorry, Transaction cannot be processed as account does not Exist");
                txtfldEA.grabFocus();
            }
            else if(ans=="Amount inefficient"){
                JOptionPane.showMessageDialog(this,"Sorry, Transaction cannot be processed as your balance is inefficient");
                txtfldEA.grabFocus();
            }
            if(ans=="Payment Successfull"){
                String date=d.getDate()+"-"+(d.getMonth()+1)+"-"+dates[5];System.out.println(date);
                String acc1[]=GLD.getLoginDetails(Num1).split(" ");
                String acc2[]=GLD.getLoginDetails(Num2).split(" ");
                
                String des1="to "+acc2[2];
                String rec1=acc1[2]+" "+date+" "+des1+" "+Amt+" "+0+" "+acc1[3];
                
                String des2="from "+acc1[2];
                String rec2=acc2[2]+" "+date+" "+des2+" "+0+" "+Amt+" "+acc2[3];
                GLD.records(rec1,rec2);
                
                FundTransfer ft=new FundTransfer();
                int choice=JOptionPane.showOptionDialog(this, "Payment Successfull.\nDo you want another transaction?", ans, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
                if(choice==JOptionPane.YES_OPTION){
                    txtfldTAN.setText("");
                    txtfldEA.setText("");
                    txtfldTAN.grabFocus();
                }
                else if(choice==JOptionPane.NO_OPTION)
                    dispose();
            }
        }
    }//GEN-LAST:event_btnTransferActionPerformed

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
            java.util.logging.Logger.getLogger(FundTransfer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FundTransfer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FundTransfer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FundTransfer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FundTransfer().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnTransfer;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblEA;
    private javax.swing.JLabel lblTAN;
    private javax.swing.JTextField txtfldEA;
    private javax.swing.JTextField txtfldTAN;
    // End of variables declaration//GEN-END:variables

}
