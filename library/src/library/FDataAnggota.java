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
public class FDataAnggota extends javax.swing.JFrame {

    /**
     * Creates new form FDataAnggota
     */
    public FDataAnggota() {
        initComponents();
        load_data();
        IDOtomatis();
        LoadTingkat();
        LoadJurusan();
        LoadKelas();
        
        BEdit.setEnabled(false);
        BDelete.setEnabled(false);
        
    }
    
    private void load_data() 
    {
        Connection kon=koneksi.koneksiDb();
        Object header[]={"ID ANGGOTA","NIS","NAMA","JK","TINGKAT","JURUSAN","KELAS","NO HP","STATUS"};
        DefaultTableModel data=new DefaultTableModel(null,header);
        TabelAnggota.setModel(data);
        String sql_data="SELECT * FROM tbl_anggota";
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
                String d6=rs.getString(6);
                String d7=rs.getString(7);
                String d8=rs.getString(8);
                String d9=rs.getString(9);
                
                String d[]={d1,d2,d3,d4,d5,d6,d7,d8,d9};
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
            String sql_id="SELECT * FROM tbl_anggota ORDER BY id_anggota desc";
            ResultSet rs=st.executeQuery (sql_id);
            if(rs.next()){
                String id_anggota=rs.getString("id_anggota").substring(1);
                String AN=""+(Integer.parseInt(id_anggota)+1);
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
                ID.setText ("P"+Nol+AN);
            }
            else {
                ID.setText("P00001");
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
    }
    
    public void LoadTingkat(){
        try{
            Connection kon=koneksi.koneksiDb();
            Statement st=kon.createStatement();
            String sql_tingkat="SELECT * FROM tbl_tingkat";
            ResultSet rs=st.executeQuery(sql_tingkat);
            while (rs.next()){
                KTINGKAT.addItem(rs.getString("id_tingkat"));
            }
            rs.close();
        }
        catch(Exception e)
        {
            
        }
    }
    
    public void NamaTingkat(){
        try{
          Connection kon=koneksi.koneksiDb();
            Statement st=kon.createStatement();
            String sql_tingkat="SELECT tingkat FROM tbl_tingkat WHERE id_tingkat='"+KTINGKAT.getSelectedItem()+"'";
            ResultSet rs=st.executeQuery(sql_tingkat);
            while (rs.next()){
               NTINGKAT.setText(rs.getString("tingkat"));
            }
        }
        catch(Exception e)
        {
            
        }
    }
    public void LoadJurusan(){
        try{
            Connection kon=koneksi.koneksiDb();
            Statement st=kon.createStatement();
            String sql_tingkat="SELECT * FROM tbl_jurusan";
            ResultSet rs=st.executeQuery(sql_tingkat);
            while (rs.next()){
                KJURUSAN.addItem(rs.getString("kd_jurusan"));
            }
            rs.close();
        }
        catch(Exception e)
        {
            
        }
    }
    
    public void NamaJurusan(){
        try{
          Connection kon=koneksi.koneksiDb();
            Statement st=kon.createStatement();
            String sql_tingkat="SELECT jurusan FROM tbl_jurusan WHERE kd_jurusan='"+KJURUSAN.getSelectedItem()+"'";
            ResultSet rs=st.executeQuery(sql_tingkat);
            while (rs.next()){
               NJURUSAN.setText(rs.getString("jurusan"));
            }
        }
        catch(Exception e)
        {
            
        }
    }
    public void LoadKelas(){
        try{
            Connection kon=koneksi.koneksiDb();
            Statement st=kon.createStatement();
            String sql_tingkat="SELECT id_kelas FROM tbl_kelas";
            ResultSet rs=st.executeQuery(sql_tingkat);
            while (rs.next()){
                KKELAS.addItem(rs.getString("id_kelas"));
            }
            rs.close();
        }
        catch(Exception e)
        {
            
        }
    }
    
    private void input_data(){
        try{
            Connection kon=koneksi.koneksiDb();
            Statement st=kon.createStatement();
            String jk="";
            if (JKP.isSelected()){
                jk=JKP.getText();
            }
            else{
                jk=JKW.getText();
            }
            String sql="INSERT INTO tbl_anggota VALUES ('"+ID.getText()
                    +"','"+NIS.getText()
                    +"','"+NAMA.getText()
                    +"','"+jk
                    +"','"+KTINGKAT.getSelectedItem()
                    +"','"+KJURUSAN.getSelectedItem()
                    +"','"+KKELAS.getSelectedItem()
                    +"','"+NOPE.getText()
                    +"','"+STATUS.getSelectedItem()
                    +"')";
            st.execute(sql);
            JOptionPane.showMessageDialog(null,"Data Anggota Berhasil Dimasukan");
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    public void clear(){
        NIS.setText("");
        NAMA.setText("");
        NOPE.setText("");
        JKP.setSelected(rootPaneCheckingEnabled);
        KTINGKAT.setSelectedItem(1);
        KJURUSAN.setSelectedItem("RPL");
        KTINGKAT.setSelectedItem(1);
        KTINGKAT.setSelectedItem("AKTIF");
    }
    
    private void update(){
        try{
            Connection kon=koneksi.koneksiDb();
            Statement st=kon.createStatement();
            String jk="";
            if (JKP.isSelected()){
                jk=JKP.getText();
            }
            else{
                jk=JKW.getText();
            }
            String sql_update="UPDATE tbl_anggota SET nis='"+NIS.getText()
                    +"',nama_anggota='"+NAMA.getText()
                    +"',jk='"+jk
                    +"',id_tingkat='"+KTINGKAT.getSelectedItem()
                    +"',kd_jurusan='"+KJURUSAN.getSelectedItem()
                    +"',id_kelas='"+KKELAS.getSelectedItem()
                    +"',nope='"+NOPE.getText()
                    +"',status='"+STATUS.getSelectedItem()
                    +"'where id_anggota='"+ID.getText()+"'";
            st.execute(sql_update);
            JOptionPane.showMessageDialog(null,"Data Anggota Berhasil Diupdate");
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void delete(){
        try{
            Connection kon=koneksi.koneksiDb();
            Statement st=kon.createStatement();
            String sql_delete="DELETE FROM tbl_anggota WHERE "+"id_anggota='"+ID.getText()+"'";
            st.execute(sql_delete);
            JOptionPane.showMessageDialog(null,"Data Anggota Berhasil Dihapus");
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        ID = new javax.swing.JTextField();
        NIS = new javax.swing.JTextField();
        NAMA = new javax.swing.JTextField();
        JKP = new javax.swing.JRadioButton();
        JKW = new javax.swing.JRadioButton();
        KTINGKAT = new javax.swing.JComboBox<>();
        KJURUSAN = new javax.swing.JComboBox<>();
        KKELAS = new javax.swing.JComboBox<>();
        NOPE = new javax.swing.JTextField();
        STATUS = new javax.swing.JComboBox<>();
        NTINGKAT = new javax.swing.JTextField();
        NJURUSAN = new javax.swing.JTextField();
        BInput = new javax.swing.JButton();
        BEdit = new javax.swing.JButton();
        BDelete = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TabelAnggota = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(55, 110, 111));
        jPanel1.setMaximumSize(new java.awt.Dimension(900, 900));

        jLabel1.setFont(new java.awt.Font("CentSchbkCyrill BT", 3, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(28, 51, 52));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("DATA ANGGOTA PERPUSTAKAAN");

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

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(28, 51, 52));
        jLabel2.setText("ID ANGGOTA");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(28, 51, 52));
        jLabel3.setText("NIS");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(28, 51, 52));
        jLabel4.setText("NAMA ANGGOTA");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(28, 51, 52));
        jLabel5.setText("JENIS KELAMIN");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(28, 51, 52));
        jLabel6.setText("TINGKAT");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(28, 51, 52));
        jLabel7.setText("JURUSAN");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(28, 51, 52));
        jLabel8.setText("KELAS");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(28, 51, 52));
        jLabel9.setText("NO HP");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(28, 51, 52));
        jLabel10.setText("STATUS");

        ID.setBackground(new java.awt.Color(28, 51, 52));
        ID.setFont(new java.awt.Font("CentSchbkCyrill BT", 1, 12)); // NOI18N
        ID.setForeground(new java.awt.Color(204, 204, 204));
        ID.setBorder(null);
        ID.setEnabled(false);

        NIS.setBackground(new java.awt.Color(28, 51, 52));
        NIS.setFont(new java.awt.Font("CentSchbkCyrill BT", 1, 12)); // NOI18N
        NIS.setForeground(new java.awt.Color(204, 204, 204));
        NIS.setBorder(null);

        NAMA.setBackground(new java.awt.Color(28, 51, 52));
        NAMA.setFont(new java.awt.Font("CentSchbkCyrill BT", 1, 12)); // NOI18N
        NAMA.setForeground(new java.awt.Color(204, 204, 204));
        NAMA.setBorder(null);

        JKP.setBackground(new java.awt.Color(28, 51, 52));
        JKP.setFont(new java.awt.Font("CentSchbkCyrill BT", 1, 12)); // NOI18N
        JKP.setForeground(new java.awt.Color(204, 204, 204));
        JKP.setText("PRIA");

        JKW.setBackground(new java.awt.Color(28, 51, 52));
        JKW.setFont(new java.awt.Font("CentSchbkCyrill BT", 1, 12)); // NOI18N
        JKW.setForeground(new java.awt.Color(204, 204, 204));
        JKW.setText("WANITA");

        KTINGKAT.setBackground(new java.awt.Color(28, 51, 52));
        KTINGKAT.setFont(new java.awt.Font("CentSchbkCyrill BT", 1, 12)); // NOI18N
        KTINGKAT.setForeground(new java.awt.Color(204, 204, 204));
        KTINGKAT.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                KTINGKATMouseClicked(evt);
            }
        });
        KTINGKAT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                KTINGKATActionPerformed(evt);
            }
        });

        KJURUSAN.setBackground(new java.awt.Color(28, 51, 52));
        KJURUSAN.setFont(new java.awt.Font("CentSchbkCyrill BT", 1, 12)); // NOI18N
        KJURUSAN.setForeground(new java.awt.Color(204, 204, 204));
        KJURUSAN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                KJURUSANActionPerformed(evt);
            }
        });

        KKELAS.setBackground(new java.awt.Color(28, 51, 52));
        KKELAS.setFont(new java.awt.Font("CentSchbkCyrill BT", 1, 12)); // NOI18N
        KKELAS.setForeground(new java.awt.Color(204, 204, 204));

        NOPE.setBackground(new java.awt.Color(28, 51, 52));
        NOPE.setFont(new java.awt.Font("CentSchbkCyrill BT", 1, 12)); // NOI18N
        NOPE.setForeground(new java.awt.Color(204, 204, 204));
        NOPE.setBorder(null);

        STATUS.setBackground(new java.awt.Color(28, 51, 52));
        STATUS.setFont(new java.awt.Font("CentSchbkCyrill BT", 1, 12)); // NOI18N
        STATUS.setForeground(new java.awt.Color(204, 204, 204));
        STATUS.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "AKTIF", "TIDAK AKTIF" }));

        NTINGKAT.setEditable(false);
        NTINGKAT.setBackground(new java.awt.Color(28, 51, 52));
        NTINGKAT.setFont(new java.awt.Font("CentSchbkCyrill BT", 1, 12)); // NOI18N
        NTINGKAT.setForeground(new java.awt.Color(204, 204, 204));
        NTINGKAT.setBorder(null);

        NJURUSAN.setEditable(false);
        NJURUSAN.setBackground(new java.awt.Color(28, 51, 52));
        NJURUSAN.setFont(new java.awt.Font("CentSchbkCyrill BT", 1, 12)); // NOI18N
        NJURUSAN.setForeground(new java.awt.Color(204, 204, 204));
        NJURUSAN.setBorder(null);

        BInput.setBackground(new java.awt.Color(0, 102, 102));
        BInput.setFont(new java.awt.Font("CentSchbkCyrill BT", 1, 12)); // NOI18N
        BInput.setForeground(new java.awt.Color(28, 51, 52));
        BInput.setIcon(new javax.swing.ImageIcon("D:\\KK3\\Project\\img\\1x\\post_add.png")); // NOI18N
        BInput.setText("INPUT");
        BInput.setBorder(null);
        BInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BInputActionPerformed(evt);
            }
        });

        BEdit.setBackground(new java.awt.Color(0, 102, 102));
        BEdit.setFont(new java.awt.Font("CentSchbkCyrill BT", 1, 12)); // NOI18N
        BEdit.setForeground(new java.awt.Color(28, 51, 52));
        BEdit.setIcon(new javax.swing.ImageIcon("D:\\KK3\\Project\\img\\1x\\edit.png")); // NOI18N
        BEdit.setText("EDIT");
        BEdit.setBorder(null);
        BEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BEditActionPerformed(evt);
            }
        });

        BDelete.setBackground(new java.awt.Color(0, 102, 102));
        BDelete.setFont(new java.awt.Font("CentSchbkCyrill BT", 1, 12)); // NOI18N
        BDelete.setForeground(new java.awt.Color(28, 51, 52));
        BDelete.setIcon(new javax.swing.ImageIcon("D:\\KK3\\Project\\img\\1x\\delete.png")); // NOI18N
        BDelete.setText("DELETE");
        BDelete.setBorder(null);
        BDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BDeleteActionPerformed(evt);
            }
        });

        TabelAnggota.setBackground(new java.awt.Color(28, 51, 52));
        TabelAnggota.setFont(new java.awt.Font("CentSchbkCyrill BT", 0, 12)); // NOI18N
        TabelAnggota.setForeground(new java.awt.Color(204, 204, 204));
        TabelAnggota.setModel(new javax.swing.table.DefaultTableModel(
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
        TabelAnggota.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabelAnggotaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TabelAnggota);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel4)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jButton1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(51, 51, 51)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(KJURUSAN, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(NJURUSAN))
                    .addComponent(NAMA)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(NIS, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(ID, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(NOPE, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(STATUS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(KKELAS, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(KTINGKAT, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(NTINGKAT, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(JKP, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(10, 10, 10)
                                    .addComponent(JKW)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(BInput, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(30, 30, 30)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1030, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(145, 145, 145))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2)
                            .addComponent(ID, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3)
                            .addComponent(NIS, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4)
                            .addComponent(NAMA, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(JKW, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel5)
                                .addComponent(JKP, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(NTINGKAT, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel6)
                                .addComponent(KTINGKAT, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(KJURUSAN, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(NJURUSAN, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(KKELAS, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(NOPE, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(STATUS, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(46, 46, 46)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BInput, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1530, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        FUtamaPustakawan utamaPustakawan=new FUtamaPustakawan();
            utamaPustakawan.show();
            this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void KTINGKATMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_KTINGKATMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_KTINGKATMouseClicked

    private void KTINGKATActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_KTINGKATActionPerformed
        // TODO add your handling code here:
        NamaTingkat();
    }//GEN-LAST:event_KTINGKATActionPerformed

    private void KJURUSANActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_KJURUSANActionPerformed
        // TODO add your handling code here:
        NamaJurusan();
    }//GEN-LAST:event_KJURUSANActionPerformed

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

    private void TabelAnggotaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabelAnggotaMouseClicked
        // TODO add your handling code here:
        int bar=TabelAnggota.getSelectedRow();
        String a=TabelAnggota.getValueAt(bar, 0).toString();
        String b=TabelAnggota.getValueAt(bar, 1).toString();
        String c=TabelAnggota.getValueAt(bar, 2).toString();
        String d=TabelAnggota.getValueAt(bar, 3).toString();
        String e=TabelAnggota.getValueAt(bar, 4).toString();
        String f=TabelAnggota.getValueAt(bar, 5).toString();
        String g=TabelAnggota.getValueAt(bar, 6).toString();
        String h=TabelAnggota.getValueAt(bar, 7).toString();
        String i=TabelAnggota.getValueAt(bar, 8).toString();
        
        ID.setText(a);
        NIS.setText(b);
        NAMA.setText(c);
        if("PRIA".equals(d))
        {
            JKP.setSelected(true);
        } else {
            JKW.setSelected(true);
        }
        KTINGKAT.setSelectedItem(e);
        KJURUSAN.setSelectedItem(f);
        KKELAS.setSelectedItem(g);
        NOPE.setText(h);
        
        if("AKTIF".equals(i)){
            STATUS.setSelectedItem(i);
        } else{
            STATUS.setSelectedItem(i);
        }
        BInput.setEnabled(false);
        BEdit.setEnabled(true);
        BDelete.setEnabled(true);
    }//GEN-LAST:event_TabelAnggotaMouseClicked

    private void BEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BEditActionPerformed
        // TODO add your handling code here:
        int update = JOptionPane.showOptionDialog(this,
                "Apakah Data Ingin Di edit?",
                "Edit Data",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,null,null,null);
        if(update==JOptionPane.YES_OPTION){
            update();
            load_data();
            clear();
            IDOtomatis();
            
            BInput.setEnabled(true);
            BEdit.setEnabled(false);
            BDelete.setEnabled(false);
        }
        
    }//GEN-LAST:event_BEditActionPerformed

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
            BEdit.setEnabled(false);
            BDelete.setEnabled(false);
        }
    }//GEN-LAST:event_BDeleteActionPerformed

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
            java.util.logging.Logger.getLogger(FDataAnggota.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FDataAnggota.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FDataAnggota.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FDataAnggota.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FDataAnggota().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BDelete;
    private javax.swing.JButton BEdit;
    private javax.swing.JButton BInput;
    private javax.swing.JTextField ID;
    private javax.swing.JRadioButton JKP;
    private javax.swing.JRadioButton JKW;
    private javax.swing.JComboBox<String> KJURUSAN;
    private javax.swing.JComboBox<String> KKELAS;
    private javax.swing.JComboBox<String> KTINGKAT;
    private javax.swing.JTextField NAMA;
    private javax.swing.JTextField NIS;
    private javax.swing.JTextField NJURUSAN;
    private javax.swing.JTextField NOPE;
    private javax.swing.JTextField NTINGKAT;
    private javax.swing.JComboBox<String> STATUS;
    private javax.swing.JTable TabelAnggota;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
