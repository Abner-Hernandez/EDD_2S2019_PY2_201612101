import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeModel;

public class Main_Class {
    
    public static Users users;
    public static String folder;
    public static Stack stack;
    
    public static void main(String[] args) {
        users = new Users();
        folder = create_folder_reports();
        stack = new Stack();
        users.insertUser("Admin", "Admin");
        login ini = new login();
        ini.setVisible(true);        
    }
    
    public static boolean exist_user(String name_user)
    {
        for(int i = 0 ; i < users.users.length ; i++)
        {
            if(users.users[i] == null)
                continue;
            if(users.users[i].user.equals(name_user))
                return true;
        }
            
        return false;
    }
    
    public static Matriz get_user_matrix(String name_user)
    {
        for(int i = 0 ; i < users.users.length ; i++)
        {
            if(users.users[i] == null)
                continue;
            if(users.users[i].user.equals(name_user))
                return users.users[i].folders_files;
        }

        
        return new Matriz();
    }
    
    public static TreeModel get_user_model(String name_user)
    {
        for(int i = 0 ; i < users.users.length ; i++)
        {
            if(users.users[i] == null)
                continue;
            if(users.users[i].user.equals(name_user))
                return users.users[i].model;
        }

        
        return null;
    }
    
    public static void set_user_matrix_model(String name_user, Matriz changes, TreeModel model)
    {
        for(int i = 0 ; i < users.users.length ; i++)
        {
            if(users.users[i] == null)
                continue;
            if(users.users[i].user.equals(name_user))
            {
                users.users[i].folders_files = changes;
                users.users[i].model = model;
                break;
            }
        }

    }
    
    private static String create_folder_reports()
    {
        File file = new File("Reports");
        if (!file.exists()) {
            if (file.mkdir()) {
                System.out.println("Directory is created!");
                return file.getAbsolutePath();
            } else {
                System.out.println("Failed to create directory!");
            }
        }
        return file.getAbsolutePath();
    }
    
    public static void save_file(String cadena,String name)
    {
        try
        {
            FileWriter fichero = new FileWriter(folder + "\\" + name +".dot");
            PrintWriter pw = new PrintWriter(fichero);
            pw.print(cadena);
            fichero.close(); 
            Runtime.getRuntime().exec(String.format("dot -Tjpg " + folder + "\\" + name +".dot -o "+ folder + "\\" + name +".jpg"));
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(Main_Class.class.getName()).log(Level.SEVERE, null, ex);
            }
            Image_Show img = new Image_Show(folder + "\\" + name +".jpg");
            img.setVisible(true);
            img.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        } catch (IOException e) {}
    }
    
    public static void shared_file(String name_user, String file, String content, String timestamp, String owner)
    {
        for(int i = 0 ; i < users.users.length; i++)
        {
            if(users.users[i] == null)
                continue;
            if(users.users[i].user.equals(name_user))
            {
                users.users[i].folders_files.insert_file_shared(file, content, owner, timestamp);
                DefaultMutableTreeNode root = (DefaultMutableTreeNode) users.users[i].model.getRoot();
                DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(file);
                root.add(newNode);
                break;
            }
        }
    }
    
    public static void download_file(String content, String name_file)
    {
        FileWriter fichero;
        try {
            fichero = new FileWriter(folder + "\\" + name_file);
            PrintWriter pw = new PrintWriter(fichero);
            pw.print(content);
            fichero.close();
            
            Files_Visualization file = new Files_Visualization(content, name_file);
            file.setVisible(true);
            file.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        } catch (IOException ex) {
            Logger.getLogger(Main_Class.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
