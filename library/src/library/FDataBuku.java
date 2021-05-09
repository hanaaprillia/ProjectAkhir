/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ASUS
 */
public class FDataBuku extends javax.swing.JFrame {

    /**
     * Creates new form FDataBuku
     */
    public FDataBuku() {
        initComponents();
        load_data();
        IDOtomatis();
        
    }
    private void load_data() 
    {
        Connection kon=koneksi.koneksiDb();
        Object header[]={"ID BUKU","NAMA BUKU","JENIS BUKU","PENGARANG","PENERBIT"};
        DefaultTableModel data=new DefaultTableModel(null,header);
        TabelBuku.setModel(data);
        String sql_data="SELECT * FROM tbl_buku";
        try 
        {
            Statement st=kon.createStatement();
            ResultSet rs=st.executeQuery(sql_data);
            while(rs.next())
            {
                String d1=rs.getString(1);
                String d2=rs.getString(2);
                String d3=rs.getString(3);
                String d4=rs.getString(4);
                String d5=rs.getString(5);
                
                String d[]={d1,d2,d3,d4,d5};
                data.addRow(d);
            }
        }
        catch(Exception e)
                {
                    JOptionPane.showMessageDialog(null, e);
                }
    }
    private void IDOtomatis(){
        try{
            Connection kon=koneksi.koneksiDb();
            Statement st=kon.createStatement();
            String sql_id="SELECT * FROM tbl_buku ORDER BY id desc";
            ResultSet rs=st.executeQuery (sql_id);
            if(rs.next()){
                String id=rs.getString("id").substring(1);
                String AN=""+(Integer.parseInt(id)+1);
                String Nol="";
                if (AN.length()==1){
                    Nol="0000";
                }
                else if(AN.length()==2){
                    Nol="000";
                }
                else if(AN.length()==3){
                    Nol="00";
                }
                ID.setText ("B"+Nol+AN);
            }
            else {
                ID.setText("B00001");
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
    private void input_data(){
        try{
            Connection kon=koneksi.koneksiDb();
            Statement st=kon.createStatement();
            String sql="INSERT INTO tbl_buku VALUES ('"+ID.getText()
                    +"','"+NBUKU.getText()
                    +"','"+JBUKU.getSelectedItem()
                    +"','"+PENGARANG.getText()
                    +"','"+PENERBIT.getText()
                    +"')";
            st.execute(sql);
            JOptionPane.showMessageDialog(null,"Data Buku Berhasil Dimasukan");
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    public void clear(){
        NBUKU.setText("");
        JBUKU.setSelectedItem("");
        PENGARANG.setText("");
        PENERBIT.setText("");
    }
    private void delete(){
        try{
            Connection kon=koneksi.koneksiDb();
            Statement st=kon.createStatement();
            String sql_delete="DELETE FROM tbl_buku WHERE "+"id='"+ID.getText()+"'";
            st.execute(sql_delete);
            JOptionPane.showMessageDialog(null,"Data Buku Berhasil Dihapus");
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void cari()
    {
        
          Connection kon = koneksi.koneksiDb();
            Object header[]={"ID","NAMA BUKU","JENIS BUKU","PENGARANG","PENERBIT"};
            DefaultTableModel data= new DefaultTableModel(null,header);
            TabelBuku.setModel(data);
            String sql="SELECT * FROM tbl_buku WHERE id LIKE '%"+CBUKU.getText()
                    +"' OR nama_buku LIKE '%"+CBUKU.getText()
                    +"%' OR jenis_buku LIKE '%"+CBUKU.getText()
                    +"' OR pengarang LIKE '"+CBUKU.getText()
                    +"' OR penerbit LIKE '"+CBUKU.getText()+"' ORDER BY id";
            try
            {
                Statement st = kon.createStatement();
                ResultSet rs = st.executeQuery(sql);
                while(rs.next())
                {

                    String k1=rs.getString(1);
                    String k2=rs.getString(2);
                    String k3=rs.getString(3);
                    String k4=rs.getString(4);
                    String k5=rs.getString(5);

                    String k[]={k1,k2,k3,k4,k5};
                    data.addRow(k);
                }
            }
            catch(Exception e)
            {

            }
        
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        ID = new javax.swing.JTextField();
        NBUKU = new javax.swing.JTextField();
        PENGARANG = new javax.swing.JTextField();
        PENERBIT = new javax.swing.JTextField();
        BInput = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TabelBuku = new javax.swing.JTable();
        BDelete = new javax.swing.JButton();
        JBUKU = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        CBUKU = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(55, 110, 111));

        jLabel1.setFont(new java.awt.Font("CentSchbkCyrill BT", 3, 25)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("DATA BUKU PERPUSTAKAAN");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(28, 51, 52));
        jLabel2.setText("NO BUKU");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(28, 51, 52));
        jLabel3.setText("NAMA BUKU");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(28, 51, 52));
        jLabel4.setText("JENIS BUKU");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(28, 51, 52));
        jLabel5.setText("PENGARANG");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(28, 51, 52));
        jLabel6.setText("PENERBIT");

        ID.setEditable(false);
        ID.setBackground(new java.awt.Color(28, 51, 52));
        ID.setFont(new java.awt.Font("CentSchbkCyrill BT", 1, 12)); // NOI18N
        ID.setForeground(new java.awt.Color(204, 204, 204));
        ID.setBorder(null);

        NBUKU.setBackground(new java.awt.Color(28, 51, 52));
        NBUKU.setFont(new java.awt.Font("CentSchbkCyrill BT", 1, 12)); // NOI18N
        NBUKU.setForeground(new java.awt.Color(204, 204, 204));
        NBUKU.setBorder(null);

        PENGARANG.setBackground(new java.awt.Color(28, 51, 52));
        PENGARANG.setFont(new java.awt.Font("CentSchbkCyrill BT", 1, 12)); // NOI18N
        PENGARANG.setForeground(new java.awt.Color(204, 204, 204));
        PENGARANG.setBorder(null);

        PENERBIT.setBackground(new java.awt.Color(28, 51, 52));
        PENERBIT.setFont(new java.awt.Font("CentSchbkCyrill BT", 1, 12)); // NOI18N
        PENERBIT.setForeground(new java.awt.Color(204, 204, 204));
        PENERBIT.setBorder(null);

        BInput.setBackground(new java.awt.Color(0, 102, 102));
        BInput.setFont(new java.awt.Font("CentSchbkCyrill BT", 1, 12)); // NOI18N
        BInput.setForeground(new java.awt.Color(28, 51, 52));
        BInput.setIcon(new javax.swing.ImageIcon("D:\\KK3\\Project\\img\\1x\\add.png")); // NOI18N
        BInput.setText("TAMBAH BUKU");
        BInput.setBorder(null);
        BInput.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BInputMouseClicked(evt);
            }
        });
        BInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BInputActionPerformed(evt);
            }
        });

        TabelBuku.setBackground(new java.awt.Color(28, 51, 52));
        TabelBuku.setFont(new java.awt.Font("CentSchbkCyrill BT", 0, 12)); // NOI18N
        TabelBuku.setForeground(new java.awt.Color(204, 204, 204));
        TabelBuku.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        TabelBuku.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabelBukuMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TabelBuku);

        BDelete.setBackground(new java.awt.Color(0, 102, 102));
        BDelete.setFont(new java.awt.Font("CentSchbkCyrill BT", 1, 12)); // NOI18N
        BDelete.setForeground(new java.awt.Color(28, 51, 52));
        BDelete.setIcon(new javax.swing.ImageIcon("D:\\KK3\\Project\\img\\1x\\delete.png")); // NOI18N
        BDelete.setText("DELETE");
        BDelete.setBorder(null);
        BDelete.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BDeleteMouseClicked(evt);
            }
        });
        BDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BDeleteActionPerformed(evt);
            }
        });

        JBUKU.setBackground(new java.awt.Color(28, 51, 52));
        JBUKU.setFont(new java.awt.Font("CentSchbkCyrill BT", 1, 12)); // NOI18N
        JBUKU.setForeground(new java.awt.Color(204, 204, 204));
        JBUKU.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Fiksi", "Non Fiksi" }));
        JBUKU.setBorder(null);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(28, 51, 52));
        jLabel7.setText("CARI BUKU");

        CBUKU.setBackground(new java.awt.Color(28, 51, 52));
        CBUKU.setFont(new java.awt.Font("CentSchbkCyrill BT", 1, 12)); // NOI18N
        CBUKU.setForeground(new java.awt.Color(204, 204, 204));
        CBUKU.setBorder(null);
        CBUKU.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CBUKUActionPerformed(evt);
            }
        });

        jButton1.setBackground(new java.awt.Color(0, 102, 102));
        jButton1.setFont(new java.awt.Font("CentSchbkCyrill BT", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(28, 51, 52));
        jButton1.setIcon(new javax.swing.ImageIcon("D:\\KK3\\Project\\img\\1x\\exit.png")); // NOI18N
        jButton1.setText("KELUAR");
        jButton1.setBorder(null);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4))
                                .addGap(29, 29, 29)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(NBUKU, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(ID, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(JBUKU, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(99, 99, 99)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 49, Short.MAX_VALUE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(jLabel8)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(BInput, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(BDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel7)
                                .addGap(31, 31, 31)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(PENERBIT)
                                .addComponent(PENGARANG, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(CBUKU, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)))
                .addGap(68, 68, 68))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(ID, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(NBUKU, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(JBUKU, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addGap(26, 26, 26)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel5)
                                .addComponent(PENGARANG, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(26, 26, 26)
                            .addComponent(jLabel6))
                        .addComponent(PENERBIT, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(72, 72, 72)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(BInput, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BDelete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(CBUKU))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BInputMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BInputMouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_BInputMouseClicked

    private void BDeleteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BDeleteMouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_BDeleteMouseClicked

    private void BDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BDeleteActionPerformed
        // TODO add your handling code here:
        int delete = JOptionPane.showOptionDialog(this,
                "Apakah Data Ingin Di hapus?",
                "Hapus Data",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,null,null,null);
        if(delete==JOptionPane.YES_OPTION){
            delete();
            load_data();
            clear();
            IDOtomatis();
            BInput.setEnabled(true);
            BDelete.setEnabled(false);
        }
    }//GEN-LAST:event_BDeleteActionPerformed

    private void TabelBukuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabelBukuMouseClicked
        // TODO add your handling code here:
        int bar=TabelBuku.getSelectedRow();
        String a=TabelBuku.getValueAt(bar, 0).toString();
        String b=TabelBuku.getValueAt(bar, 1).toString();
        String c=TabelBuku.getValueAt(bar, 2).toString();
        String d=TabelBuku.getValueAt(bar, 3).toString();
        String e=TabelBuku.getValueAt(bar, 4).toString();
        
        ID.setText(a);
        NBUKU.setText(b);
        JBUKU.setSelectedItem(c);
        PENGARANG.setText(d);
        PENERBIT.setText(e);
        BInput.setEnabled(false);
        BDelete.setEnabled(true);
        
    }//GEN-LAST:event_TabelBukuMouseClicked

    private void BInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BInputActionPerformed
        // TODO add your handling code here:
        int simpan = JOptionPane.showOptionDialog(this,
                "Apakah Data Sudah Benar? Simpan ?",
                "Simpan Data",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,null,null,null);
        if(simpan==JOptionPane.YES_OPTION){
            input_data();
            load_data();
            clear();
            IDOtomatis();
        }
    }//GEN-LAST:event_BInputActionPerformed

    private void CBUKUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CBUKUActionPerformed
        // TODO add your handling code here:
        cari();
    }//GEN-LAST:event_CBUKUActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        FUtamaAdmin utamaAdmin=new FUtamaAdmin();
            utamaAdmin.show();
            this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(FDataBuku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FDataBuku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FDataBuku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FDataBuku.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FDataBuku().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BDelete;
    private javax.swing.JButton BInput;
    private javax.swing.JTextField CBUKU;
    private javax.swing.JTextField ID;
    private javax.swing.JComboBox<String> JBUKU;
    private javax.swing.JTextField NBUKU;
    private javax.swing.JTextField PENERBIT;
    private javax.swing.JTextField PENGARANG;
    private javax.swing.JTable TabelBuku;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
