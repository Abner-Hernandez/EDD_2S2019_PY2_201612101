
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

public class Main_Class {
    
    public static Users users;
    public static Show_Image images;
    public static String folder;
    public static List stack;
    
    public static void main(String[] args) {
        users = new Users();
        folder = create_folder_reports();
        stack = new List();
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
    
    public static void set_user_matrix(String name_user, Matriz changes)
    {
        for(int i = 0 ; i < users.users.length ; i++)
        {
            if(users.users[i] == null)
                continue;
            if(users.users[i].user.equals(name_user))
                users.users[i].folders_files = changes;
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
                Thread.sleep(200);
            } catch (InterruptedException ex) {
                Logger.getLogger(Main_Class.class.getName()).log(Level.SEVERE, null, ex);
            }
            images = new Show_Image(folder + "\\" + name +".jpg");
            images.setVisible(true);
            images.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        } catch (IOException e) {}
    }
    
    public static void shared_file(String name_user, ArrayList<String> path, String file, String content, String owner)
    {
        for(int i = 0 ; i < users.users.length  && users.users[i] != null; i++)
            if(users.users[i].user.equals(name_user))
                users.users[i].folders_files.insert_file(path, file, content, owner);
    }
}
