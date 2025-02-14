/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DlgPenyakit.java
 *
 * Created on May 23, 2010, 12:57:16 AM
 */

package rekammedis;

import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.validasi;
import fungsi.akses;
import fungsi.sekuel;
import inventory.DlgPeresepanDokter;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import keuangan.Jurnal;

/**
 *
 * @author dosen
 */
public final class MasterCariTemplateRacikan extends javax.swing.JDialog {
    private final DefaultTableModel tabMode,tabModeObatUmum,tabModeObatRacikan,tabModeDetailObatRacikan;
    private validasi Valid=new validasi();
    private sekuel Sequel=new sekuel();
    private Connection koneksi=koneksiDB.condb();
    private PreparedStatement ps,ps2;
    private ResultSet rs,rs2;
    private int i=0;
    private String la="",ld="",pa="",pd="",kodedokter="",tanggaldilakukan="",jamdilakukan="",noperawatan="",norm="",nomor="",
            Suspen_Piutang_Tindakan_Ralan="",Tindakan_Ralan="",Beban_Jasa_Medik_Dokter_Tindakan_Ralan="",Utang_Jasa_Medik_Dokter_Tindakan_Ralan="",
            Beban_KSO_Tindakan_Ralan="",Utang_KSO_Tindakan_Ralan="",Beban_Jasa_Sarana_Tindakan_Ralan="",Utang_Jasa_Sarana_Tindakan_Ralan="",
            HPP_BHP_Tindakan_Ralan="",Persediaan_BHP_Tindakan_Ralan="",Beban_Jasa_Menejemen_Tindakan_Ralan="",Utang_Jasa_Menejemen_Tindakan_Ralan="";
    private boolean sukses=true;
    private double ttljmdokter=0,ttlkso=0,ttljasasarana=0,ttlbhp=0,ttlmenejemen=0,ttlpendapatan=0;
    private Jurnal jur=new Jurnal();
    /** Creates new form DlgPenyakit
     * @param parent
     * @param modal */
    public MasterCariTemplateRacikan(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocation(10,2);
        setSize(656,250);

        Object[] row={"No.Template","Kode Dokter","Nama Dokter","Subjek","Objek","Asesmen","Plan","Instruksi","Evaluasi"};
        tabMode=new DefaultTableModel(null,row){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        tbDokter.setModel(tabMode);

        tbDokter.setPreferredScrollableViewportSize(new Dimension(800,800));
        tbDokter.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (i = 0; i < 9; i++) {
            TableColumn column = tbDokter.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(120);
            }else if(i==1){
                column.setPreferredWidth(90);
            }else if(i==2){
                column.setPreferredWidth(150);
            }else{
                column.setPreferredWidth(200);
            }
        }
        tbDokter.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabModeObatUmum=new DefaultTableModel(null,new Object[]{
                "Jumlah","Kode Barang","Nama Barang","Satuan","Komposisi","Jenis Obat","Aturan Pakai","I.F.","Kapasitas"
            }){
             @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
             Class[] types = new Class[] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, 
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class,
                java.lang.Double.class
             };
             @Override
             public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
             }
        };
        tbObatNonRacikan.setModel(tabModeObatUmum);
        tbObatNonRacikan.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbObatNonRacikan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        for (i = 0; i < 9; i++) {
            TableColumn column = tbObatNonRacikan.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(45);
            }else if(i==1){
                column.setPreferredWidth(70);
            }else if(i==2){
                column.setPreferredWidth(240);
            }else if(i==3){
                column.setPreferredWidth(75);
            }else if(i==4){
                column.setPreferredWidth(110);
            }else if(i==5){
                column.setPreferredWidth(110);
            }else if(i==6){
                column.setPreferredWidth(130);
            }else if(i==7){
                column.setPreferredWidth(100);
            }else if(i==8){
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }                
        }
        tbObatNonRacikan.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabModeObatRacikan=new DefaultTableModel(null,new Object[]{"No","Nama Racikan","Kode Racik","Metode Racik","Jml.Racik","Aturan Pakai","Keterangan"}){
             @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
             Class[] types = new Class[] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, 
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
             };
             @Override
             public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
             }
        };

        tbObatRacikan.setModel(tabModeObatRacikan);
        tbObatRacikan.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbObatRacikan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);        
        
        for (i = 0; i < 7; i++) {
            TableColumn column = tbObatRacikan.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(25);
            }else if(i==1){
                column.setPreferredWidth(250);
            }else if(i==2){
                column.setMinWidth(0);
                column.setMaxWidth(0);
            }else if(i==3){
                column.setPreferredWidth(100);
            }else if(i==4){
                column.setPreferredWidth(60);
            }else if(i==5){
                column.setPreferredWidth(200);
            }else if(i==6){
                column.setPreferredWidth(250);
            }
        }
        tbObatRacikan.setDefaultRenderer(Object.class, new WarnaTable());
        
        tabModeDetailObatRacikan=new DefaultTableModel(null,new Object[]{
                "No","Kode Barang","Nama Barang","Satuan","Jenis Obat","Kps","P1","/","P2","Kandungan","Jml","I.F.","Komposisi"
            }){
             @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}            
             Class[] types = new Class[] {
                java.lang.Object.class,java.lang.Object.class,java.lang.Object.class,
                java.lang.Object.class,java.lang.Object.class,java.lang.Double.class,
                java.lang.Double.class,java.lang.Object.class,java.lang.Double.class,
                java.lang.Object.class,java.lang.Double.class,java.lang.Object.class,
                java.lang.Object.class
             };
             @Override
             public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
             }
        };

        tbDetailObatRacikan.setModel(tabModeDetailObatRacikan);
        tbDetailObatRacikan.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbDetailObatRacikan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);        
        
        for (i = 0; i < 13; i++) {
            TableColumn column = tbDetailObatRacikan.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(25);
            }else if(i==1){
                column.setPreferredWidth(75);
            }else if(i==2){
                column.setPreferredWidth(240);
            }else if(i==3){
                column.setPreferredWidth(45);
            }else if(i==4){
                column.setPreferredWidth(110);
            }else if(i==5){
                column.setPreferredWidth(40);
            }else if(i==6){
                column.setPreferredWidth(25);
            }else if(i==7){
                column.setMinWidth(11);
                column.setMaxWidth(11);
            }else if(i==8){
                column.setPreferredWidth(25);
            }else if(i==9){
                column.setPreferredWidth(60);
            }else if(i==10){
                column.setPreferredWidth(40);
            }else if(i==11){
                column.setPreferredWidth(100);
            }else if(i==12){
                column.setPreferredWidth(150);
            }
        }

        tbDetailObatRacikan.setDefaultRenderer(Object.class, new WarnaTable());
        
        TCari.setDocument(new batasInput((byte)100).getKata(TCari));
        if(koneksiDB.CARICEPAT().equals("aktif")){
            TCari.getDocument().addDocumentListener(new javax.swing.event.DocumentListener(){
                @Override
                public void insertUpdate(DocumentEvent e) {
                    if(TCari.getText().length()>2){
                        tampil();
                    }
                }
                @Override
                public void removeUpdate(DocumentEvent e) {
                    if(TCari.getText().length()>2){
                        tampil();
                    }
                }
                @Override
                public void changedUpdate(DocumentEvent e) {
                    if(TCari.getText().length()>2){
                        tampil();
                    }
                }
            });
        } 
        
        try {
            ps=koneksi.prepareStatement(
                "select set_akun_ralan.Suspen_Piutang_Tindakan_Ralan,set_akun_ralan.Tindakan_Ralan,set_akun_ralan.Beban_Jasa_Medik_Dokter_Tindakan_Ralan,"+
                "set_akun_ralan.Utang_Jasa_Medik_Dokter_Tindakan_Ralan,set_akun_ralan.Beban_KSO_Tindakan_Ralan,set_akun_ralan.Utang_KSO_Tindakan_Ralan,"+
                "set_akun_ralan.Beban_Jasa_Sarana_Tindakan_Ralan,set_akun_ralan.Utang_Jasa_Sarana_Tindakan_Ralan,set_akun_ralan.Beban_Jasa_Menejemen_Tindakan_Ralan,"+
                "set_akun_ralan.Utang_Jasa_Menejemen_Tindakan_Ralan,set_akun_ralan.HPP_BHP_Tindakan_Ralan,set_akun_ralan.Persediaan_BHP_Tindakan_Ralan from set_akun_ralan");
            try {
                rs=ps.executeQuery();
                while(rs.next()){
                    Suspen_Piutang_Tindakan_Ralan=rs.getString("Suspen_Piutang_Tindakan_Ralan");
                    Tindakan_Ralan=rs.getString("Tindakan_Ralan");
                    Beban_Jasa_Medik_Dokter_Tindakan_Ralan=rs.getString("Beban_Jasa_Medik_Dokter_Tindakan_Ralan");
                    Utang_Jasa_Medik_Dokter_Tindakan_Ralan=rs.getString("Utang_Jasa_Medik_Dokter_Tindakan_Ralan");
                    Beban_KSO_Tindakan_Ralan=rs.getString("Beban_KSO_Tindakan_Ralan");
                    Utang_KSO_Tindakan_Ralan=rs.getString("Utang_KSO_Tindakan_Ralan");
                    Beban_Jasa_Sarana_Tindakan_Ralan=rs.getString("Beban_Jasa_Sarana_Tindakan_Ralan");
                    Utang_Jasa_Sarana_Tindakan_Ralan=rs.getString("Utang_Jasa_Sarana_Tindakan_Ralan");
                    Beban_Jasa_Menejemen_Tindakan_Ralan=rs.getString("Beban_Jasa_Menejemen_Tindakan_Ralan");
                    Utang_Jasa_Menejemen_Tindakan_Ralan=rs.getString("Utang_Jasa_Menejemen_Tindakan_Ralan");
                    HPP_BHP_Tindakan_Ralan=rs.getString("HPP_BHP_Tindakan_Ralan");
                    Persediaan_BHP_Tindakan_Ralan=rs.getString("Persediaan_BHP_Tindakan_Ralan");
                }
            } catch (Exception e) {
                System.out.println("Notif Rekening : "+e);
            } finally{
                if(rs!=null){
                    rs.close();
                }
                if(ps!=null){
                    ps.close();
                }
            }            
        } catch (Exception e) {
            System.out.println(e);
        }   
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        internalFrame1 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbDokter = new widget.Table();
        panelisi3 = new widget.panelisi();
        label9 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        BtnAll = new widget.Button();
        BtnSimpan = new widget.Button();
        label10 = new widget.Label();
        LCount = new widget.Label();
        BtnTambah = new widget.Button();
        BtnKeluar = new widget.Button();
        scrollPane2 = new widget.ScrollPane();
        FormInput = new widget.PanelBiasa();
        jLabel19 = new widget.Label();
        Scroll9 = new widget.ScrollPane();
        tbObatNonRacikan = new widget.Table();
        jLabel20 = new widget.Label();
        Scroll10 = new widget.ScrollPane();
        tbObatRacikan = new widget.Table();
        Scroll11 = new widget.ScrollPane();
        tbDetailObatRacikan = new widget.Table();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Master Template Racikan Dokter ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);
        Scroll.setPreferredSize(new java.awt.Dimension(310, 402));

        tbDokter.setAutoCreateRowSorter(true);
        tbDokter.setName("tbDokter"); // NOI18N
        tbDokter.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbDokterMouseClicked(evt);
            }
        });
        Scroll.setViewportView(tbDokter);

        internalFrame1.add(Scroll, java.awt.BorderLayout.WEST);

        panelisi3.setName("panelisi3"); // NOI18N
        panelisi3.setPreferredSize(new java.awt.Dimension(100, 43));
        panelisi3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 4, 9));

        label9.setText("Key Word :");
        label9.setName("label9"); // NOI18N
        label9.setPreferredSize(new java.awt.Dimension(68, 23));
        panelisi3.add(label9);

        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(312, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelisi3.add(TCari);

        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('1');
        BtnCari.setToolTipText("Alt+1");
        BtnCari.setName("BtnCari"); // NOI18N
        BtnCari.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCariActionPerformed(evt);
            }
        });
        BtnCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCariKeyPressed(evt);
            }
        });
        panelisi3.add(BtnCari);

        BtnAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll.setMnemonic('2');
        BtnAll.setToolTipText("2Alt+2");
        BtnAll.setName("BtnAll"); // NOI18N
        BtnAll.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAllActionPerformed(evt);
            }
        });
        BtnAll.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnAllKeyPressed(evt);
            }
        });
        panelisi3.add(BtnAll);

        BtnSimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16i.png"))); // NOI18N
        BtnSimpan.setMnemonic('S');
        BtnSimpan.setToolTipText("Tambahkan ke resep (alt+s)");
        BtnSimpan.setName("BtnSimpan"); // NOI18N
        BtnSimpan.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpanActionPerformed(evt);
            }
        });
        BtnSimpan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnSimpanKeyPressed(evt);
            }
        });
        panelisi3.add(BtnSimpan);

        label10.setText("Record :");
        label10.setName("label10"); // NOI18N
        label10.setPreferredSize(new java.awt.Dimension(60, 23));
        panelisi3.add(label10);

        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(50, 23));
        panelisi3.add(LCount);

        BtnTambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/plus_16.png"))); // NOI18N
        BtnTambah.setMnemonic('3');
        BtnTambah.setToolTipText("Tambah template resep");
        BtnTambah.setName("BtnTambah"); // NOI18N
        BtnTambah.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnTambahActionPerformed(evt);
            }
        });
        panelisi3.add(BtnTambah);

        BtnKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar.setMnemonic('4');
        BtnKeluar.setToolTipText("keluar (alt+4)");
        BtnKeluar.setName("BtnKeluar"); // NOI18N
        BtnKeluar.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluarActionPerformed(evt);
            }
        });
        panelisi3.add(BtnKeluar);

        internalFrame1.add(panelisi3, java.awt.BorderLayout.PAGE_END);

        scrollPane2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(239, 244, 234)), "Detail Template :", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 50, 50))); // NOI18N
        scrollPane2.setName("scrollPane2"); // NOI18N

        FormInput.setBackground(new java.awt.Color(255, 255, 255));
        FormInput.setBorder(null);
        FormInput.setName("FormInput"); // NOI18N
        FormInput.setPreferredSize(new java.awt.Dimension(730, 2733));
        FormInput.setLayout(null);

        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel19.setText("Obat Non Racikan :");
        jLabel19.setName("jLabel19"); // NOI18N
        FormInput.add(jLabel19);
        jLabel19.setBounds(30, 20, 270, 23);

        Scroll9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)));
        Scroll9.setName("Scroll9"); // NOI18N
        Scroll9.setOpaque(true);

        tbObatNonRacikan.setName("tbObatNonRacikan"); // NOI18N
        Scroll9.setViewportView(tbObatNonRacikan);

        FormInput.add(Scroll9);
        Scroll9.setBounds(30, 40, 700, 223);

        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel20.setText("Obat Racikan :");
        jLabel20.setName("jLabel20"); // NOI18N
        FormInput.add(jLabel20);
        jLabel20.setBounds(30, 270, 270, 23);

        Scroll10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)));
        Scroll10.setName("Scroll10"); // NOI18N
        Scroll10.setOpaque(true);

        tbObatRacikan.setName("tbObatRacikan"); // NOI18N
        Scroll10.setViewportView(tbObatRacikan);

        FormInput.add(Scroll10);
        Scroll10.setBounds(30, 290, 700, 103);

        Scroll11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)));
        Scroll11.setName("Scroll11"); // NOI18N
        Scroll11.setOpaque(true);

        tbDetailObatRacikan.setName("tbDetailObatRacikan"); // NOI18N
        Scroll11.setViewportView(tbDetailObatRacikan);

        FormInput.add(Scroll11);
        Scroll11.setBounds(30, 400, 700, 223);

        scrollPane2.setViewportView(FormInput);

        internalFrame1.add(scrollPane2, java.awt.BorderLayout.CENTER);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);
        internalFrame1.getAccessibleContext().setAccessibleName("::[ Master Template Pemeriksaan Dokter ]::");

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            BtnCariActionPerformed(null);
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            BtnCari.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            BtnKeluar.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_UP){
            tbDokter.requestFocus();
        }
}//GEN-LAST:event_TCariKeyPressed

    private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
        tampil();
}//GEN-LAST:event_BtnCariActionPerformed

    private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnCariActionPerformed(null);
        }else{
            Valid.pindah(evt, TCari, BtnAll);
        }
}//GEN-LAST:event_BtnCariKeyPressed

    private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
        TCari.setText("");
        tampil();
}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnAllActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnCari, TCari);
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
    }//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnTambahActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));        
        MasterTemplatePemeriksaanDokter form=new MasterTemplatePemeriksaanDokter(null,false);
        form.setSize(internalFrame1.getWidth()-20,internalFrame1.getHeight()-20);
        form.setLocationRelativeTo(internalFrame1);
        form.setAlwaysOnTop(false);
        form.emptTeks();
        form.isCek();
        form.setVisible(true);
        this.setCursor(Cursor.getDefaultCursor());   
        
    }//GEN-LAST:event_BtnTambahActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        emptTeks();
    }//GEN-LAST:event_formWindowActivated

    private void tbDokterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbDokterMouseClicked
        if(tabMode.getRowCount()!=0){
            if(tbDokter.getSelectedRow()>-1){
                try {
                    Valid.tabelKosong(tabModeObatUmum);
                    ps=koneksi.prepareStatement(
                            "select template_pemeriksaan_dokter_resep.kode_brng,databarang.nama_brng,kodesatuan.satuan,template_pemeriksaan_dokter_resep.jml,template_pemeriksaan_dokter_resep.aturan_pakai,jenis.nama,industrifarmasi.nama_industri, "+
                            "databarang.kapasitas,databarang.letak_barang from template_pemeriksaan_dokter_resep inner join databarang on template_pemeriksaan_dokter_resep.kode_brng=databarang.kode_brng inner join kodesatuan on kodesatuan.kode_sat=databarang.kode_sat "+
                            "inner join jenis on databarang.kdjns=jenis.kdjns inner join industrifarmasi on industrifarmasi.kode_industri=databarang.kode_industri where template_pemeriksaan_dokter_resep.no_template=? order by databarang.nama_brng");
                    try {
                        ps.setString(1,tabMode.getValueAt(tbDokter.getSelectedRow(),0).toString());
                        rs=ps.executeQuery();
                        while(rs.next()){
                            tabModeObatUmum.addRow(new Object[]{
                                rs.getString("jml"),rs.getString("kode_brng"),rs.getString("nama_brng"),rs.getString("satuan"),rs.getString("letak_barang"),rs.getString("nama"),rs.getString("aturan_pakai"),rs.getString("nama_industri"),rs.getDouble("kapasitas")
                            });
                        }
                    } catch (Exception e) {
                        System.out.println("Notif : "+e);
                    } finally{
                        if(rs!=null){
                            rs.close();
                        }
                        if(ps!=null){
                            ps.close();
                        }
                    }
                    
                    Valid.tabelKosong(tabModeObatRacikan);
                    Valid.tabelKosong(tabModeDetailObatRacikan);
                    ps=koneksi.prepareStatement(
                            "select template_pemeriksaan_dokter_resep_racikan.no_racik,template_pemeriksaan_dokter_resep_racikan.kd_racik,template_pemeriksaan_dokter_resep_racikan.nama_racik,metode_racik.nm_racik,template_pemeriksaan_dokter_resep_racikan.jml_dr,template_pemeriksaan_dokter_resep_racikan.aturan_pakai,"+
                            "template_pemeriksaan_dokter_resep_racikan.keterangan from template_pemeriksaan_dokter_resep_racikan inner join metode_racik on metode_racik.kd_racik=template_pemeriksaan_dokter_resep_racikan.kd_racik where template_pemeriksaan_dokter_resep_racikan.no_template=? "+
                            "order by template_pemeriksaan_dokter_resep_racikan.no_racik");
                    try {
                        ps.setString(1,tabMode.getValueAt(tbDokter.getSelectedRow(),0).toString());
                        rs=ps.executeQuery();
                        while(rs.next()){
                            tabModeObatRacikan.addRow(new Object[]{
                                rs.getString("no_racik"),rs.getString("nama_racik"),rs.getString("kd_racik"),rs.getString("nm_racik"),rs.getString("jml_dr"),rs.getString("aturan_pakai"),rs.getString("keterangan")
                            });
                            try {
                                ps2=koneksi.prepareStatement(
                                        "select template_pemeriksaan_dokter_resep_racikan_detail.kode_brng,databarang.nama_brng,kodesatuan.satuan,template_pemeriksaan_dokter_resep_racikan_detail.jml,jenis.nama,"+
                                        "databarang.kapasitas,template_pemeriksaan_dokter_resep_racikan_detail.p1,template_pemeriksaan_dokter_resep_racikan_detail.p2,template_pemeriksaan_dokter_resep_racikan_detail.kandungan,"+
                                        "industrifarmasi.nama_industri,databarang.letak_barang from template_pemeriksaan_dokter_resep_racikan_detail inner join databarang on template_pemeriksaan_dokter_resep_racikan_detail.kode_brng=databarang.kode_brng "+
                                        "inner join jenis on databarang.kdjns=jenis.kdjns inner join kodesatuan on kodesatuan.kode_sat=databarang.kode_sat inner join industrifarmasi on industrifarmasi.kode_industri=databarang.kode_industri "+
                                        "where template_pemeriksaan_dokter_resep_racikan_detail.no_template=? and template_pemeriksaan_dokter_resep_racikan_detail.no_racik=? order by template_pemeriksaan_dokter_resep_racikan_detail.kode_brng");
                                ps2.setString(1,tabMode.getValueAt(tbDokter.getSelectedRow(),0).toString());
                                ps2.setString(2,rs.getString("no_racik"));
                                rs2=ps2.executeQuery();
                                while(rs2.next()){
                                    tabModeDetailObatRacikan.addRow(new Object[]{
                                        rs.getString("no_racik"),rs2.getString("kode_brng"),rs2.getString("nama_brng"),rs2.getString("satuan"),
                                        rs2.getString("nama"),rs2.getDouble("kapasitas"),rs2.getDouble("p1"),"/",rs2.getDouble("p2"),rs2.getString("kandungan"),
                                        rs2.getDouble("jml"),rs2.getString("nama_industri"),rs2.getString("letak_barang")
                                    });
                                }
                            } catch (Exception e) {
                                System.out.println("Notif : "+e);
                            } finally{
                                if(rs2!=null){
                                    rs2.close();
                                }
                                if(ps2!=null){
                                    ps2.close();
                                }
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("Notif : "+e);
                    } finally{
                        if(rs!=null){
                            rs.close();
                        }
                        if(ps!=null){
                            ps.close();
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Notif : "+e);
                }
            }
        }
    }//GEN-LAST:event_tbDokterMouseClicked

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if(tbDokter.getSelectedRow()>-1){
            String nmDokter = Sequel.cariIsi("SELECT nm_dokter from dokter where kd_dokter ='"+kodedokter+"'");
            String[] waktu = jamdilakukan.split(":");
            String jam = waktu[0];
            String menit = waktu[1];
            String detik = waktu[2];
            DlgPeresepanDokter resep=new DlgPeresepanDokter(null,false);
            resep.setSize(internalFrame1.getWidth(),internalFrame1.getHeight());
            resep.setLocationRelativeTo(internalFrame1);
            resep.setNoRm(noperawatan,Valid.SetTgl2(tanggaldilakukan),jam,menit,detik,kodedokter,nmDokter,"ralan");
            resep.isCek();
            resep.tampilobatracikan(tabMode.getValueAt(tbDokter.getSelectedRow(),0).toString());
            resep.setVisible(true);
            setVisible(false);
        }else{
            JOptionPane.showMessageDialog(rootPane,"Silahkan pilih data template racikan terlebih dahulu..!!");
        }
    }//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_SPACE){
            BtnSimpanActionPerformed(null);
        }
    }//GEN-LAST:event_BtnSimpanKeyPressed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            MasterCariTemplateRacikan dialog = new MasterCariTemplateRacikan(new javax.swing.JFrame(), true);
            dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    System.exit(0);
                }
            });
            dialog.setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private widget.Button BtnAll;
    private widget.Button BtnCari;
    private widget.Button BtnKeluar;
    private widget.Button BtnSimpan;
    private widget.Button BtnTambah;
    private widget.PanelBiasa FormInput;
    private widget.Label LCount;
    private widget.ScrollPane Scroll;
    private widget.ScrollPane Scroll10;
    private widget.ScrollPane Scroll11;
    private widget.ScrollPane Scroll9;
    private widget.TextBox TCari;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel19;
    private widget.Label jLabel20;
    private widget.Label label10;
    private widget.Label label9;
    private widget.panelisi panelisi3;
    private widget.ScrollPane scrollPane2;
    public widget.Table tbDetailObatRacikan;
    private widget.Table tbDokter;
    public widget.Table tbObatNonRacikan;
    public widget.Table tbObatRacikan;
    // End of variables declaration//GEN-END:variables

    public void tampil() {
        Valid.tabelKosong(tabMode);
        try{
            ps=koneksi.prepareStatement(
                    "select template_pemeriksaan_dokter.no_template,template_pemeriksaan_dokter.kd_dokter,dokter.nm_dokter,"+
                    "template_pemeriksaan_dokter.keluhan,template_pemeriksaan_dokter.pemeriksaan,template_pemeriksaan_dokter.penilaian,"+
                    "template_pemeriksaan_dokter.rencana,template_pemeriksaan_dokter.instruksi,template_pemeriksaan_dokter.evaluasi "+
                    "from template_pemeriksaan_dokter inner join dokter on dokter.kd_dokter=template_pemeriksaan_dokter.kd_dokter "+
                    "where template_pemeriksaan_dokter.kd_dokter=? "+(TCari.getText().equals("")?"":"and (template_pemeriksaan_dokter.no_template like ? or "+
                    "template_pemeriksaan_dokter.keluhan like ? or template_pemeriksaan_dokter.pemeriksaan like ? or "+
                    "template_pemeriksaan_dokter.penilaian like ? or template_pemeriksaan_dokter.rencana like ? or "+
                    "template_pemeriksaan_dokter.instruksi like ? or template_pemeriksaan_dokter.evaluasi like ?) ")+
                    "order by template_pemeriksaan_dokter.no_template");
                
            try {
                ps.setString(1,kodedokter);
                if(!TCari.getText().equals("")){
                    ps.setString(2,"%"+TCari.getText().trim()+"%");
                    ps.setString(3,"%"+TCari.getText().trim()+"%");
                    ps.setString(4,"%"+TCari.getText().trim()+"%");
                    ps.setString(5,"%"+TCari.getText().trim()+"%");
                    ps.setString(6,"%"+TCari.getText().trim()+"%");
                    ps.setString(7,"%"+TCari.getText().trim()+"%");
                    ps.setString(8,"%"+TCari.getText().trim()+"%");
                }
                
                rs=ps.executeQuery();
                while(rs.next()){
                    tabMode.addRow(new Object[]{
                        rs.getString("no_template"),rs.getString("kd_dokter"),rs.getString("nm_dokter"),rs.getString("keluhan"),rs.getString("pemeriksaan"),rs.getString("penilaian"),rs.getString("rencana"),rs.getString("instruksi"),rs.getString("evaluasi")
                    });
                }
            } catch (Exception e) {
                System.out.println(e);
            } finally{
                if(rs!=null){
                    rs.close();
                }
                if(ps!=null){
                    ps.close();
                }
            }
        }catch(Exception e){
            System.out.println("Notifikasi : "+e);
        }
        LCount.setText(""+tabMode.getRowCount());
    }
    
    public void emptTeks() {
        TCari.requestFocus();
    }

    public JTable getTable(){
        return tbDokter;
    }
    
    public void setDokter(String kode,String tanggal, String jam,String norawat,String nomorrm){
        this.kodedokter=kode;
        this.tanggaldilakukan=tanggal;
        this.jamdilakukan=jam;
        this.noperawatan=norawat;
        this.norm=nomorrm;
    }
    
    public void isCek(){        
        BtnTambah.setEnabled(akses.gettemplate_pemeriksaan());
    }
}
