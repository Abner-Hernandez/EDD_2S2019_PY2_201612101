import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

class Node
{
    String name_file;
    String content;
    String timestamp;
    Node left;
    Node right;
    public Node()
    {
        this.name_file = null;
        this.content = null;
        this.timestamp = null;
        this.left = null;
        this.right = null;
    }
    
    public Node(String name_file, String content, String timestamp)
    {
        this.name_file = name_file;
        this.content = content;
        this.timestamp = timestamp;
        this.left = null;
        this.right = null;
    }
}

class File_
{
    String name_file;
    String content;
    String timestamp;
    
    public File_(String name, String content, String timestamp)
    {
        this.name_file = name;
        this.content = content;
        this.timestamp = timestamp;
    }
}

public class AVLTree {
    Node root;
    String txtfile;
    List<File_> files;
    
    public void insert_file(String name, String content)
    {
        Timestamp times = new Timestamp(System.currentTimeMillis());
        this.files.add(new File_(name, content, times.toString()));
    }
    
    public void delete_file(String name)
    {
        for (int i = 0 ; i < files.size() ; i++) {
            if(files.get(i).name_file.equals(name))
            {
                files.remove(i);
                return;
            }
        }
    }
    
    private boolean exist_file(String name)
    {
        for (int i = 0 ; i < files.size() ; i++) {
            if(files.get(i).name_file.equals(name))
              return true;
        }
        return false;
    }
    
    public void modify_file(String name, String new_content)
    {
        for (int i = 0 ; i < files.size() ; i++) {
            if(files.get(i).name_file.equals(name))
            {
                files.get(i).content = new_content;
                return;
            }
        }
    }
    
    public String get_content(String name)
    {
        for (int i = 0 ; i < files.size() ; i++) {
            if(files.get(i).name_file.equals(name))
            {
                return files.get(i).content;
            }
        }
        return null;
    }
    
    public void create_avl()
    {
        for (File_ file : files) {
            this.insert(file.name_file, file.content, file.timestamp);
        }
    }
    
    public AVLTree()
    {
        this.root = null;
        this.txtfile = "";
        this.files = new ArrayList<>();
    }
    
    private int calculate_height(Node node)
    {
        if(node == null)
            return -1;
        else
            return 1 + Integer.max(this.calculate_height(node.left), this.calculate_height(node.right));
    }
    
    private Node simple_rot(Node node, boolean left)
    {
        Node aux = new Node();
        if(!left)
        {
            aux = node.left;
            node.left = aux.right;
            aux.right = node;
        }else
        {
            aux = node.right;
            node.right = aux.left;
            aux.left = node;
        }
        node = aux;
        return node;
    }
    
    private Node doubble_rot(Node node, boolean right)
    {
        if(right)
        {
            this.simple_rot(node.right, false);
            return this.simple_rot(node, true);
        }else
        {
            this.simple_rot(node.left, true);
            return this.simple_rot(node, false);
        }
    }
    
    private void insert(String name_file, String content, String timestamp)
    {
        if(root == null)
            this.root = new Node(name_file, content, timestamp);
        else
            this.insert(this.root, name_file, content, timestamp, null, false);
    }
    
    private void insert(Node pivot, String name_file, String content, String timestamp, Node parent, boolean left)
    {
        if(pivot.name_file.compareTo(name_file) < 0)
        {
            if(pivot.right == null)
                pivot.right = new Node(name_file, content, timestamp);
            else
                this.insert(pivot.right, name_file, content, timestamp, pivot , false);
            
            if((this.calculate_height(pivot.right) - this.calculate_height(pivot.left)) == 2)
            {
                if(name_file.compareTo(pivot.right.name_file) > 0)
                {
                    if(left)
                        parent.left = this.simple_rot(pivot, true);
                    else
                        parent.right = this.simple_rot(pivot, true);
                }
                else
                {
                    
                    if(left)
                        parent.left = this.doubble_rot(pivot, true);
                    else
                        parent.right = this.doubble_rot(pivot, true);
                }
            }
        }else if(pivot.name_file.compareTo(name_file) > 0)
        {
            if(pivot.left == null)
                pivot.left = new Node(name_file, content, timestamp);
            else
                this.insert(pivot.left, name_file, content, timestamp, pivot, true);

            if((this.calculate_height(pivot.left) - this.calculate_height(pivot.right)) == 2)
            {
                if(name_file.compareTo(pivot.right.name_file) < 0)
                {
                    if(left)
                        parent.left = this.simple_rot(pivot, false);
                    else
                        parent.right = this.simple_rot(pivot, false);
                }
                else
                {
                    
                    if(left)
                        parent.left = this.doubble_rot(pivot, false);
                    else
                        parent.right = this.doubble_rot(pivot, false);
                }
            }        
        }
    }
    
    public void tree_graph()
    {
        if (this.root != null)
        {
            txtfile = "digraph Mass{\n";
            txtfile += "node[shape = record, height = 0.5, width = 1]; \n";
            txtfile += "graph[nodesep = 0.5]; \n";

            txtfile += this.root.name_file + "[label= \"  <A0> |";
            txtfile +=  "Name: " + this.root.name_file + "\\nContent: " + this.root.content + "\\nAltura: " + String.valueOf(this.calculate_height(this.root)) + "\\nFE: " + String.valueOf(this.calculate_height(this.root.right) - this.calculate_height(this.root.left) ) + "\\nTimestamp: " + this.root.timestamp+ " | <A1> \"];\n";
            this.tree_graph(root);
            txtfile += "}";
            this.save_file(txtfile, "tree");
        }
    }
    
    private void tree_graph(Node node)
    {
        if (node != null)
        {
            if(node.left != null)
            {
                txtfile += node.left.name_file + "[label= \"  <A0> |";
                txtfile += "Name: " + node.left.name_file + "\\nContent: " + node.left.content + "\\nAltura: " + String.valueOf(this.calculate_height(node.left)) + "\\nFE: " + String.valueOf(this.calculate_height(node.left.right) - this.calculate_height(node.left.left) )  + " | <A1> \"];\n";
                txtfile += node.name_file + ":A0" +" -> "+ node.left.name_file +";\n";
            }
            if(node.right != null)
            {
                txtfile += node.right.name_file + "[label= \"  <A0> |";
                txtfile += "Name: " + node.right.name_file + "\\nContent: " + node.right.content + "\\nAltura: " + String.valueOf(this.calculate_height(node.right)) + "\\nFE: " + String.valueOf(this.calculate_height(node.right.right) - this.calculate_height(node.right.left) )  + " | <A1> \"];\n";
                txtfile += node.name_file + ":A1" +" -> "+ node.right.name_file +";\n";
            }
            this.tree_graph(node.left);
            this.tree_graph(node.right);
        }
    }
     
    private boolean deleteNodo(Node padre, Node pivote, String file, int lado) {
        if (pivote != null) {
            //Node aux = pivote;
            if (pivote.name_file.equals(file) && lado == 0 && pivote.right != null) {
                Node aux = pivote.right;
                Node aux2 = pivote;
                if(aux.left != null)
                {
                    while (aux.left != null) {
                        aux2 = aux;
                        aux = aux.left;
                    }
                    if (aux.right != null)
                        aux2.left = aux.right;

                    aux.right = padre.right;
                    aux.left = padre.left;
                padre = aux;
                //delete aux
                return true;
                }
                padre = pivote.right;
            }else if (pivote.name_file.equals(file) && lado == 0) {
                padre.right = null;
            }else if (pivote.name_file.equals(file) && lado == 1 && pivote.left != null) {
                Node aux = pivote.left;
                Node aux2 = pivote;
                if(aux.right != null)
                {
                    while (aux.right != null) {
                        aux2 = aux;
                        aux = aux.right;
                    }
                    if (aux.left != null)
                        aux2.right = aux.left;

                    aux.right = padre.right;
                    aux.left = padre.left;
                    padre = aux;
                    //delete aux
                }
                padre = pivote.left;
            }else if (pivote.name_file.equals(file) && lado == 1) {
                padre.left = null;
            }else if (pivote.name_file.compareTo(file) < 0 && pivote.right != null)
                return deleteNodo(pivote, pivote.right, file, 0);
            else if (pivote.name_file.compareTo(file) > 0 && pivote.left != null)
                return deleteNodo(pivote, pivote.left, file, 1);
        }
        return false;
    }

    public boolean delete(String file) {
        if (this.root.name_file.equals(file) && this.root.right == null && this.root.left == null) {
            this.root = null;
            return true;
        }else if (this.root.name_file.equals(file) && this.root.right == null && this.root.left != null) {
            this.root = this.root.left;
            return true;
        }else if (this.root.name_file.equals(file) && this.root.right != null && this.root.left == null) {
            this.root = this.root.right;
            return true;
        }else if (this.root.name_file.equals(file)) {
            //std::cout << "mierda";

                Node aux = this.root.right;
                if(aux.left != null)
                {
                Node aux2 = this.root;
                    while (aux.left != null) {
                        aux2 = aux;
                        aux = aux.left;
                    }
                    if (aux.right != null)
                        aux2.left = aux.right;

                aux.right = this.root.right;
            //std::cout << "mierda" << this.root.right.file;
                aux.left = this.root.left;
            //std::cout << "mierda" << this.root.left.file;
                this.root = aux;
                //delete(&aux); return true;
                }
                this.root.right.left = this.root.left;
                this.root = this.root.right;

        }else if (this.root.name_file.compareTo(file) < 0 && this.root.right != null)
            return deleteNodo(this.root, this.root.right, file, 0);
        else if (this.root.name_file.compareTo(file) > 0 && this.root.left != null)
            return deleteNodo(this.root, this.root.left, file, 1);
        return false;
    }

    /*
    public void upload_files(String csv)
    {
        int ini = 0;
        String file_name = "", not_inserted = "";
        for(int i = 0 ; i < csv.length() ; i++)
        {
            if(csv.charAt(i) == ',')
            {
                file_name = csv.substring(ini,i);
                ini = i+1;
            }else if (csv.charAt(i) == '\n')
            {
                if(file_name.toLowerCase().equals("archivo"))
                {
                    ini = i+1;
                    file_name = "";
                    continue;
                }

                if(this.exist_file(file_name))
                {
                    int resp = JOptionPane.showConfirmDialog(null, "¿Want you remplace file?");
                    if(resp == 1)
                        this.modify_file(file_name, csv.substring(ini, i));
                }
                ini = i+1;
            }
        }
    }
    */

    private void save_file(String cadena,String name)
    {
        try
        {
            FileWriter fichero = new FileWriter(name + ".dot");
            PrintWriter pw = new PrintWriter(fichero);
            pw.print(cadena);
            fichero.close(); 
            Runtime.getRuntime().exec(String.format("dot -Tpng "+ name +".dot -o "+ name +".png"));
            File file = new File( name +".png" );
            Desktop.getDesktop().open( file );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
