import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreeSelectionModel;

/**
 * @author Abner Hernandez
 */

public class Drive_Interface extends javax.swing.JFrame {

    int cont_folders_delete = 0;
    Matriz folders_files;
    String user_name;
   
    public Drive_Interface(Matriz folders, String user) {
        initComponents();
        this.folders_files = folders;
        this.user_name = user;
        
        if(folders_files.header.next.next != null)
            jTreeFiles.setModel(Main_Class.get_user_model(user));
            //refresh_jtree();
        
        if(!user.equals("Admin"))
        {
            masive_label.setVisible(false);
            load_label.setVisible(false);
        }
        
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent ev) {
                TreeModel root = jTreeFiles.getModel();
                Main_Class.set_user_matrix_model(user_name, folders_files, root);
                login log = new login();
                log.setVisible(true);
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        principal_panel = new javax.swing.JPanel();
        folder_label = new javax.swing.JLabel();
        modify_folder = new javax.swing.JButton();
        delete_folder = new javax.swing.JButton();
        create_folder = new javax.swing.JButton();
        create_file = new javax.swing.JButton();
        modify_file = new javax.swing.JButton();
        delete_file = new javax.swing.JButton();
        upload_file = new javax.swing.JButton();
        file_label = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTreeFiles = new javax.swing.JTree();
        report_label = new javax.swing.JLabel();
        report_combobox = new javax.swing.JComboBox<>();
        generate_praph = new javax.swing.JButton();
        share_file = new javax.swing.JButton();
        masive_label = new javax.swing.JLabel();
        load_label = new javax.swing.JButton();
        download_file = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        principal_panel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        folder_label.setText("Folders");

        modify_folder.setText("Modify");
        modify_folder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modify_folderActionPerformed(evt);
            }
        });

        delete_folder.setText("Delete");
        delete_folder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delete_folderActionPerformed(evt);
            }
        });

        create_folder.setText("Create");
        create_folder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                create_folderActionPerformed(evt);
            }
        });

        create_file.setText("Create");
        create_file.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                create_fileActionPerformed(evt);
            }
        });

        modify_file.setText("Modify");
        modify_file.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modify_fileActionPerformed(evt);
            }
        });

        delete_file.setText("Delete");
        delete_file.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delete_fileActionPerformed(evt);
            }
        });

        upload_file.setText("Upload");
        upload_file.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                upload_fileActionPerformed(evt);
            }
        });

        file_label.setText("Files");

        jLabel1.setText("USAC FILE DRIVE");

        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("/");
        jTreeFiles.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        jTreeFiles.setAutoscrolls(true);
        jScrollPane1.setViewportView(jTreeFiles);

        report_label.setText("Select Report:");

        report_combobox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hash Table", "Graph", "Adjacency Matrix", "Avl Tree", "binnacle" }));
        report_combobox.setToolTipText("");

        generate_praph.setText("Generate");
        generate_praph.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generate_praphActionPerformed(evt);
            }
        });

        share_file.setText("Share");
        share_file.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                share_fileActionPerformed(evt);
            }
        });

        masive_label.setText("Massive load");

        load_label.setText("Load");
        load_label.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                load_labelActionPerformed(evt);
            }
        });

        download_file.setText("Download");
        download_file.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                download_fileActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout principal_panelLayout = new javax.swing.GroupLayout(principal_panel);
        principal_panel.setLayout(principal_panelLayout);
        principal_panelLayout.setHorizontalGroup(
            principal_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(principal_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(principal_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(folder_label)
                    .addComponent(file_label)
                    .addComponent(create_folder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(modify_folder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(delete_folder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(create_file, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(modify_file, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(delete_file, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(upload_file, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(share_file, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(masive_label, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
                    .addComponent(load_label, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(download_file, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(principal_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(principal_panelLayout.createSequentialGroup()
                        .addComponent(report_label)
                        .addGap(18, 18, 18)
                        .addComponent(report_combobox, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(generate_praph, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 575, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        principal_panelLayout.setVerticalGroup(
            principal_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(principal_panelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(principal_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(principal_panelLayout.createSequentialGroup()
                        .addComponent(folder_label)
                        .addGap(12, 12, 12)
                        .addComponent(create_folder)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(modify_folder)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(delete_folder)
                        .addGap(18, 18, 18)
                        .addComponent(file_label)
                        .addGap(18, 18, 18)
                        .addComponent(create_file)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(modify_file)
                        .addGap(18, 18, 18)
                        .addComponent(delete_file)
                        .addGap(18, 18, 18)
                        .addComponent(upload_file)
                        .addGap(18, 18, 18)
                        .addComponent(share_file)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(download_file)
                        .addGap(15, 15, 15)
                        .addComponent(masive_label))
                    .addGroup(principal_panelLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(principal_panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(report_label)
                    .addComponent(report_combobox, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(generate_praph)
                    .addComponent(load_label))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(principal_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(principal_panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void create_fileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_create_fileActionPerformed
        TreeSelectionModel selec = jTreeFiles.getSelectionModel();

        if(selec.getSelectionCount() == 0 || selec.getSelectionCount() > 1)
        {
            JOptionPane.showMessageDialog(null, "You have to select a folder");
        }else
        {
            DefaultMutableTreeNode SelectedNode = (DefaultMutableTreeNode) jTreeFiles.getSelectionPath().getLastPathComponent();
            
            if(this.check_extension(SelectedNode.getUserObject().toString()))
            {
                JOptionPane.showMessageDialog(null, "You cannot create a file within a file");
                return;
            }                    
            String file_name = JOptionPane.showInputDialog("File's name");
        
            while(file_name == null || !this.check_extension(file_name))
                    file_name = JOptionPane.showInputDialog("Enter a correct name, Remember you need a extension");
                    
            String content = JOptionPane.showInputDialog("File's content");
        
            while(content == null)
                    content = JOptionPane.showInputDialog("Enter a content to file");
            
            if(SelectedNode.getChildCount() > 0)
            {
                TreeNode[] nodes = new TreeNode[SelectedNode.getChildCount()];
                for (int i = 0; i < SelectedNode.getChildCount(); i++) {
                    nodes[i] = SelectedNode.getChildAt(i);
                }
                if(exist_node(nodes, file_name))
                {
                    JOptionPane.showMessageDialog(null, "The File exists");
                    return;
                }
            }
            
            DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(file_name);
            SelectedNode.add(newNode);
            
            DefaultTreeModel model = (DefaultTreeModel) jTreeFiles.getModel();
            model.reload();
            
            Object[] paths = SelectedNode.getPath();
            List<String> insert_path = new List();
            for (Object path : paths) 
                insert_path.add(path.toString());
            
            
            this.folders_files.insert_file(insert_path, file_name, content, user_name);
            Main_Class.stack.insert("created a new folder:" + file_name, user_name);
        }
    }//GEN-LAST:event_create_fileActionPerformed

    private void modify_fileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modify_fileActionPerformed
        TreeSelectionModel selec = jTreeFiles.getSelectionModel();

        if(selec.getSelectionCount() == 0 || selec.getSelectionCount() > 1)
        {
            JOptionPane.showMessageDialog(null, "You have to select a file");
        }else
        {
            DefaultMutableTreeNode SelectedNode = (DefaultMutableTreeNode) jTreeFiles.getSelectionPath().getLastPathComponent();
            
            if(!this.check_extension(SelectedNode.getUserObject().toString()))
            {
                JOptionPane.showMessageDialog(null, "You cannot modify a folder, selected modify a file");
                return;
            }               
            Object[] paths = SelectedNode.getPath();
            List<String> insert_path = new List();
            for (Object path : paths)
                insert_path.add(path.toString());
            
            String file_name = JOptionPane.showInputDialog("Enter File's new name");
        
            while(file_name == null || !this.check_extension(file_name))
                    file_name = JOptionPane.showInputDialog("Enter a correct name, Remember you need a extension");
            
            String content = JOptionPane.showInputDialog("File's Content", this.folders_files.get_content_file(insert_path, SelectedNode.getUserObject().toString()));
        
            while(content == null)
                    content = JOptionPane.showInputDialog("Enter a content to file");
            
            if(SelectedNode.getChildCount() > 0)
            {
                TreeNode[] nodes = new TreeNode[SelectedNode.getChildCount()];
                for (int i = 0; i < SelectedNode.getChildCount(); i++) {
                    nodes[i] = SelectedNode.getChildAt(i);
                }
                if(exist_node(nodes, file_name))
                {
                    JOptionPane.showMessageDialog(null, "The file exists");
                    return;
                }
            }
            
            this.folders_files.modify_file(insert_path, content, file_name);            
            SelectedNode.setUserObject(file_name);
            
            DefaultTreeModel model = (DefaultTreeModel) jTreeFiles.getModel();
            model.reload();
            Main_Class.stack.insert("Modified a file:" + file_name, user_name);
        }
    }//GEN-LAST:event_modify_fileActionPerformed

    private void delete_fileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delete_fileActionPerformed
        TreeSelectionModel selec = jTreeFiles.getSelectionModel();

        if(selec.getSelectionCount() == 0 || selec.getSelectionCount() > 1)
        {
            JOptionPane.showMessageDialog(null, "You have to select a file");
        }else
        {
            DefaultMutableTreeNode SelectedNode = (DefaultMutableTreeNode) jTreeFiles.getSelectionPath().getLastPathComponent();
            if(!this.check_extension(SelectedNode.getUserObject().toString()))
            {
                JOptionPane.showMessageDialog(null, "You selected to delete a file not a folder");
                return;
            }                
            Object[] paths = SelectedNode.getPath();
            List<String> insert_path = new List();
            for (Object path : paths)
                insert_path.add(path.toString());
            
            this.folders_files.delete_file(insert_path);            
            
            DefaultTreeModel model = (DefaultTreeModel) jTreeFiles.getModel();
            model.removeNodeFromParent(SelectedNode);
            model.reload();
            Main_Class.stack.insert("deleted a file:" + insert_path.get(insert_path.size-1), user_name);
        }
    }//GEN-LAST:event_delete_fileActionPerformed

    private void upload_fileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_upload_fileActionPerformed
        TreeSelectionModel selec = jTreeFiles.getSelectionModel();

        if(selec.getSelectionCount() == 0 || selec.getSelectionCount() > 1)
        {
            JOptionPane.showMessageDialog(null, "You have to select a folder");
        }else
        {
            DefaultMutableTreeNode SelectedNode = (DefaultMutableTreeNode) jTreeFiles.getSelectionPath().getLastPathComponent();
            
            if(this.check_extension(SelectedNode.getUserObject().toString()))
            {
                JOptionPane.showMessageDialog(null, "You cannot create a files within a file");
                return;
            }
            
            Object[] paths = SelectedNode.getPath();
            List<String> insert_path = new List();
            for (Object path : paths) 
                insert_path.add(path.toString());
            
            String csv = this.get_content_csv();
            while(csv.equals("Error"))
                csv = this.get_content_csv();
            
            int ini = 0;
            String file_name = "";
            for(int i = 0 ; i < csv.length() ; i++)
            {
                if(csv.charAt(i) == ',')
                {
                    file_name = csv.substring(ini,i);
                    ini = i+1;
                }else if (csv.charAt(i) == '\n' || i == csv.length()-1)
                {
                    if(file_name.toLowerCase().equals("archivo"))
                    {
                        ini = i+1;
                        file_name = "";
                        continue;
                    }
                    String content = csv.substring(ini, i-1);

                    if(SelectedNode.getChildCount() > 0)
                    {
                        TreeNode[] nodes = new TreeNode[SelectedNode.getChildCount()];
                        for (int j = 0; j < SelectedNode.getChildCount(); j++) {
                            nodes[j] = SelectedNode.getChildAt(j);
                        }
                        if(exist_node(nodes, file_name))
                        {
                            int resp = JOptionPane.showConfirmDialog(null, "¿Want you remplace file '"+ file_name +"'?");
                            if(resp == 1)
                                this.folders_files.modify_file(insert_path, file_name, content);
                            else
                            {
                                ini = i+1;
                                continue;
                            }
                        }
                    }

                    DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(file_name);
                    SelectedNode.add(newNode);

                    this.folders_files.insert_file(insert_path, file_name, content, user_name);
                    
                    ini = i+1;
                }
            }
            DefaultTreeModel model = (DefaultTreeModel) jTreeFiles.getModel();
            model.reload();
            Main_Class.stack.insert("Uploaded masive files", user_name);            
        }        
        
        
        

    
    }//GEN-LAST:event_upload_fileActionPerformed

    private void create_folderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_create_folderActionPerformed
        TreeSelectionModel selec = jTreeFiles.getSelectionModel();

        if(selec.getSelectionCount() == 0 || selec.getSelectionCount() > 1)
        {
            JOptionPane.showMessageDialog(null, "You have to select a folder");
        }else
        {
            DefaultMutableTreeNode SelectedNode = (DefaultMutableTreeNode) jTreeFiles.getSelectionPath().getLastPathComponent();
            
            if(this.check_extension(SelectedNode.getUserObject().toString()))
            {
                JOptionPane.showMessageDialog(null, "You cannot create a folder within a file");
                return;
            }            
            String file_name = JOptionPane.showInputDialog("Folder's name");
        
            while(file_name == null || this.check_extension(file_name))
                file_name = JOptionPane.showInputDialog("Enter a correct name, Remember not extension and not empty name");
        
            
            if(SelectedNode.getChildCount() > 0)
            {
                TreeNode[] nodes = new TreeNode[SelectedNode.getChildCount()];
                for (int i = 0; i < SelectedNode.getChildCount(); i++) {
                    nodes[i] = SelectedNode.getChildAt(i);
                }
                if(exist_node(nodes, file_name))
                {
                    JOptionPane.showMessageDialog(null, "The folder exists");
                    return;
                }
            }
            
            DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(file_name);
            SelectedNode.add(newNode);
            
            DefaultTreeModel model = (DefaultTreeModel) jTreeFiles.getModel();
            model.reload();
            
            Object[] paths = SelectedNode.getPath();
            List<String> insert_path = new List();
            for (Object path : paths) 
                insert_path.add(path.toString());
            insert_path.add(file_name);
            this.folders_files.insert_folder(insert_path);
            Main_Class.stack.insert("created a folder:" + file_name, user_name);
        }


    }//GEN-LAST:event_create_folderActionPerformed

    private void modify_folderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modify_folderActionPerformed
        TreeSelectionModel selec = jTreeFiles.getSelectionModel();

        if(selec.getSelectionCount() == 0 || selec.getSelectionCount() > 1)
        {
            JOptionPane.showMessageDialog(null, "You have to select a folder");
        }else
        {
            DefaultMutableTreeNode SelectedNode = (DefaultMutableTreeNode) jTreeFiles.getSelectionPath().getLastPathComponent();
            
            if(jTreeFiles.getModel().getRoot().toString().endsWith(SelectedNode.getUserObject().toString()))
            {
                JOptionPane.showMessageDialog(null, "You cannot Modify root folder");
                return;
            }
            
            if(this.check_extension(SelectedNode.getUserObject().toString()))
            {
                JOptionPane.showMessageDialog(null, "You have to select a folder not a file");
                return;
            }
            
            String file_name= JOptionPane.showInputDialog("Enter the folder's new name");
        
            while(file_name == null || this.check_extension(file_name))
                    file_name = JOptionPane.showInputDialog("Enter a correct name, Remember not extension and not empty name");
        
            
            if(SelectedNode.getChildCount() > 0)
            {
                TreeNode[] nodes = new TreeNode[SelectedNode.getChildCount()];
                for (int i = 0; i < SelectedNode.getChildCount(); i++) {
                    nodes[i] = SelectedNode.getChildAt(i);
                }
                if(exist_node(nodes, file_name))
                {
                    JOptionPane.showMessageDialog(null, "The folder exists");
                    return;
                }
            }
            
            Object[] paths = SelectedNode.getPath();
            List<String> insert_path = new List();
            for (Object path : paths)
                insert_path.add(path.toString());
            
            this.folders_files.modify_folder(insert_path, file_name);
            
            SelectedNode.setUserObject(file_name);
            DefaultTreeModel model = (DefaultTreeModel) jTreeFiles.getModel();
            model.reload();
            Main_Class.stack.insert("Modified a folder:" + file_name, user_name);
        }
    }//GEN-LAST:event_modify_folderActionPerformed

    private void delete_folderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delete_folderActionPerformed
        TreeSelectionModel selec = jTreeFiles.getSelectionModel();

        if(selec.getSelectionCount() == 0 || selec.getSelectionCount() > 1)
        {
            JOptionPane.showMessageDialog(null, "You have to select a folder");
        }else
        {
            DefaultMutableTreeNode SelectedNode = (DefaultMutableTreeNode) jTreeFiles.getSelectionPath().getLastPathComponent();
            
            if(jTreeFiles.getModel().getRoot().toString().endsWith(SelectedNode.getUserObject().toString()))
            {
                JOptionPane.showMessageDialog(null, "You cannot delete root file");
                return;
            }
            
            if(this.check_extension(SelectedNode.getUserObject().toString()))
            {
                JOptionPane.showMessageDialog(null, "You selected to delete a folder not a file");
                return;
            }  
            
            this.cont_folders_delete(SelectedNode);
            
            
            
            Object[] paths = SelectedNode.getPath();
            List<String> insert_path = new List();
            for (Object path : paths) 
                insert_path.add(path.toString());
            
            this.folders_files.deleteFolder(insert_path, this.cont_folders_delete+1);
            this.cont_folders_delete = 0 ;
            
            DefaultTreeModel model = (DefaultTreeModel) jTreeFiles.getModel();
            model.removeNodeFromParent(SelectedNode);
            model.reload();
            Main_Class.stack.insert("deleted a folder:" + insert_path.get(insert_path.size-1), user_name);
        }
    }//GEN-LAST:event_delete_folderActionPerformed

    private void generate_praphActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generate_praphActionPerformed
        int index = report_combobox.getSelectedIndex();
        switch (index) {
            case 0: // Hash table
                if(!this.user_name.equals("Admin"))
                    JOptionPane.showMessageDialog(null, "You need to be an admin user");
                else
                    Main_Class.users.graph_table();
                break;
            case 1: //graph
                this.folders_files.graph();
                break;
            case 2: //adjacency matrix
                this.folders_files.graph_matrix();
                break;
            case 3: //Avl Tree
                TreeSelectionModel selec = jTreeFiles.getSelectionModel();

                if(selec.getSelectionCount() == 0 || selec.getSelectionCount() > 1)
                {
                    JOptionPane.showMessageDialog(null, "You have to select a folder");
                }else
                {
                    DefaultMutableTreeNode SelectedNode = (DefaultMutableTreeNode) jTreeFiles.getSelectionPath().getLastPathComponent();

                    if(this.check_extension(SelectedNode.getUserObject().toString()))
                    {
                        JOptionPane.showMessageDialog(null, "You selected to delete a folder not a file");
                        return;
                    }  

                    Object[] paths = SelectedNode.getPath();
                    List<String> insert_path = new List();
                    for (Object path : paths) 
                        insert_path.add(path.toString());

                    this.folders_files.create_avl_tree(insert_path);
                }
                break;
            case 4: //binnacle
                Main_Class.stack.graph_table();
                break;
            default:
                break;
        }
    }//GEN-LAST:event_generate_praphActionPerformed

    private void share_fileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_share_fileActionPerformed
        TreeSelectionModel selec = jTreeFiles.getSelectionModel();

        if(selec.getSelectionCount() == 0 || selec.getSelectionCount() > 1)
        {
            JOptionPane.showMessageDialog(null, "You have to select a file to share");
        }else
        {
            DefaultMutableTreeNode SelectedNode = (DefaultMutableTreeNode) jTreeFiles.getSelectionPath().getLastPathComponent();
            
            if(!this.check_extension(SelectedNode.getUserObject().toString()))
            {
                JOptionPane.showMessageDialog(null, "You cannot share a folder, selected a file to share");
                return;
            }               
            Object[] paths = SelectedNode.getPath();
            List<String> insert_path = new List<>();
            for (Object path : paths)
                insert_path.add(path.toString());
            
            String user_share = JOptionPane.showInputDialog("Enter User Name");
            if(!Main_Class.exist_user(user_share))
            {
                JOptionPane.showMessageDialog(null, "Username doesn't exist");
                return;
            }
            
            String timestamp = this.folders_files.get_timestamp(insert_path, SelectedNode.getUserObject().toString());
            String content = this.folders_files.get_content_file(insert_path, SelectedNode.getUserObject().toString());
            Main_Class.shared_file(user_share , insert_path.first.previous.data.toString(), content, timestamp, user_name);
        }
    }//GEN-LAST:event_share_fileActionPerformed

    private void load_labelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_load_labelActionPerformed
        String content_users = this.get_content_csv();
        Main_Class.users.upload_users(content_users);
    }//GEN-LAST:event_load_labelActionPerformed

    private void download_fileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_download_fileActionPerformed
        TreeSelectionModel selec = jTreeFiles.getSelectionModel();

        if(selec.getSelectionCount() == 0 || selec.getSelectionCount() > 1)
        {
            JOptionPane.showMessageDialog(null, "You have to select a file");
        }else
        {
            DefaultMutableTreeNode SelectedNode = (DefaultMutableTreeNode) jTreeFiles.getSelectionPath().getLastPathComponent();
            
            if(!this.check_extension(SelectedNode.getUserObject().toString()))
            {
                JOptionPane.showMessageDialog(null, "You cannot download a folder, selected a file");
                return;
            }               
            Object[] paths = SelectedNode.getPath();
            List<String> insert_path = new List<>();
            for (Object path : paths)
                insert_path.add(path.toString());
            
            String content = this.folders_files.get_content_file(insert_path, SelectedNode.getUserObject().toString());
            Main_Class.download_file(content, insert_path.first.previous.data.toString());
        }
    }//GEN-LAST:event_download_fileActionPerformed

    
    private String get_content_csv()
    {
        JFileChooser jf = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV ", "csv");
        jf.setFileFilter(filter);
        int seleccion = jf.showOpenDialog(jf);

        if (seleccion == JFileChooser.APPROVE_OPTION) {
            File selectedFile = jf.getSelectedFile();
            Scanner sc; 
            try {
                sc = new Scanner(selectedFile);
                sc.useDelimiter("\\Z"); 
                String content_csv = sc.next();

                return content_csv;
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Drive_Interface.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return "Error";
    }
    
    private void cont_folders_delete(TreeNode node)
    {
        cont_folders_delete += node.getChildCount();
        if(node.getChildCount() >0)
            for (int i = 0 ; i < node.getChildCount() ; i++)
                this.cont_folders_delete(node.getChildAt(i));
    }
    
    private boolean check_extension(String name)
    {
        int lenght = name.length();
        
        for(int i = lenght -1  ; i > -1 ; i--)
        {
            if(name.charAt(i) == '.' && i < lenght-1)
                return true;
        }
        return false;
    }
    
    private boolean exist_node(TreeNode[] nodes, String name)
    {
        for (TreeNode node : nodes) {
            if(node.toString().equals(name))
                return true;
        }
        return false;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton create_file;
    private javax.swing.JButton create_folder;
    private javax.swing.JButton delete_file;
    private javax.swing.JButton delete_folder;
    private javax.swing.JButton download_file;
    private javax.swing.JLabel file_label;
    private javax.swing.JLabel folder_label;
    private javax.swing.JButton generate_praph;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTree jTreeFiles;
    private javax.swing.JButton load_label;
    private javax.swing.JLabel masive_label;
    private javax.swing.JButton modify_file;
    private javax.swing.JButton modify_folder;
    private javax.swing.JPanel principal_panel;
    private javax.swing.JComboBox<String> report_combobox;
    private javax.swing.JLabel report_label;
    private javax.swing.JButton share_file;
    private javax.swing.JButton upload_file;
    // End of variables declaration//GEN-END:variables
}
