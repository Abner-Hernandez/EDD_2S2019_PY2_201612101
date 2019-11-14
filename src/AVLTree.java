import java.sql.Timestamp;

class Node
{
    String name_file;
    String content;
    String timestamp;
    String owner;
    Node left;
    Node right;
    public Node()
    {
        this.name_file = null;
        this.content = null;
        this.timestamp = null;
        this.left = null;
        this.right = null;
        this.owner = null;
    }
    
    public Node(String name_file, String content, String timestamp, String owner)
    {
        this.name_file = name_file;
        this.content = content;
        this.timestamp = timestamp;
        this.owner = owner;
        this.left = null;
        this.right = null;
    }
}

class File_
{
    String name_file;
    String content;
    String timestamp;
    String owner;
    
    public File_(String name, String content, String timestamp, String owner)
    {
        this.name_file = name;
        this.content = content;
        this.timestamp = timestamp;
        this.owner = owner;
    }
}

public class AVLTree {
    Node root;
    String txtfile;
    List<File_> files;
    
    public List<String> get_files()
    {
        List<String> files_names = new List<>();
        
        Node_List<File_> aux = files.first;
        
        do
        {
            files_names.add(aux.data.name_file);
        }while(aux != files.first);
        
        return files_names;
    }
    
    public void insert_file(String name, String content, String owner)
    {
        Timestamp times = new Timestamp(System.currentTimeMillis());
        this.files.add(new File_(name, content, times.toString(), owner));
    }
    
    public void insert_file(String name, String content, String owner, String timestamp)
    {
        this.files.add(new File_(name, content, timestamp, owner));
    }
    
    public void delete_file(String name)
    {
        Node_List<File_> aux = files.first;
        
        do
        {
            if(name.equals(aux.data.name_file))
            {
                if(aux == files.first && aux.next == files.first)
                {
                    files = new List<File_>();
                    files.size--;
                }
                else
                {
                    aux.previous.next = aux.next;
                    aux.next.previous = aux.previous;
                    files.size--;
                    if(aux == files.first)
                    {
                        files.first = aux.next;
                    }
                }
                
            }
            aux = aux.next;
        }while(aux != files.first);
    }
    
    public boolean exists_file(String name)
    {
        if(files.size == 0)
            return false;
        
        Node_List<File_> aux = files.first;
        
        do
        {
            if(name.equals(aux.data.name_file))
                return true;
            aux = aux.next;
        }while(aux != files.first);
        return false;
    }
    
    public String get_timestamp(String name)
    {
        Node_List<File_> aux = files.first;
        
        do
        {
            if(name.equals(aux.data.name_file))
                return aux.data.timestamp;
            aux = aux.next;
        }while(aux != files.first);
        return null;
    }
    
    public void modify_file(String name, String new_content, String new_name)
    {
        Node_List<File_> aux = files.first;
        
        do
        {
            if(name.equals(aux.data.name_file))
            {
                aux.data.content = new_content;
                aux.data.name_file = new_name;
            }
            aux = aux.next;
        }while(aux != files.first);
    }
    
    public String get_content(String name)
    {
        Node_List<File_> aux = files.first;
        
        do
        {
            if(name.equals(aux.data.name_file))
                return aux.data.content;
            aux = aux.next;
        }while(aux != files.first);
        return null;
    }
    
    public void create_avl()
    {
        this.root = null;
        this.txtfile = "";
        Node_List<File_> aux = files.first;
        
        do
        {
            this.insert(aux.data.name_file, aux.data.content, aux.data.timestamp, aux.data.owner);
            aux = aux.next;
        }while(aux != files.first);
    }
    
    public AVLTree()
    {
        this.root = null;
        this.txtfile = "";
        this.files = new List<>();
    }
    
    private int calculate_height(Node node)
    {
        if(node == null)
            return -1;
        else
            return 1 + Integer.max(this.calculate_height(node.left), this.calculate_height(node.right));
    }
    
    private void simple_rot(Node node, boolean left)
    {
        Node aux = new Node();
        Node side = new Node();
        Node node_copy = new Node();
        if(!left)
        {
            copy_data_node(aux, node.left);
            copy_data_node(side, aux.right);
            
            if(aux.right != null)
            {
                node.left = side;
                copy_data_node(aux.right, node);
            }
            else
            {
                node.left = null;
                copy_data_node(node_copy, node);
                aux.right = node_copy;
            }
        }else
        {
            copy_data_node(aux, node.right);
            copy_data_node(side, aux.left);
            
            if(aux.left != null)
            {
                node.right = side;
                copy_data_node(node_copy, node);
                aux.left = node_copy;
            }
            else
            {
                node.right = null;
                copy_data_node(node_copy, node);
                aux.left = node_copy;
            }
        }
        copy_data_node(node, aux);
    }
    
    private void copy_data_node(Node original, Node copy)
    {
        if(copy == null)
            return;
        original.content = copy.content;
        original.name_file = copy.name_file;
        original.timestamp = copy.timestamp;
        if(copy.right != null)
        {
            original.right = new Node();
            copy_data_node(original.right, copy.right);
        }else
            original.right = null;
        
        if(copy.left != null)
        {
            original.left = new Node();
            copy_data_node(original.left, copy.left);
        }else
            original.left = null;
    }
    
    private void doubble_rot(Node node, boolean right)
    {
        if(right)
        {
            this.simple_rot(node.right, false);
            this.simple_rot(node, true);
        }else
        {
            this.simple_rot(node.left, true);
            this.simple_rot(node, false);
        }
    }
    
    private void insert(String name_file, String content, String timestamp, String owner)
    {
        if(root == null)
            this.root = new Node(name_file, content, timestamp, owner);
        else
            this.insert(this.root, name_file, content, timestamp, owner);
    }
    
    private void insert(Node pivot, String name_file, String content, String timestamp, String owner)
    {
        if(pivot.name_file.compareTo(name_file) < 0)
        {
            if(pivot.right == null)
                pivot.right = new Node(name_file, content, timestamp, owner);
            else
                this.insert(pivot.right, name_file, content, timestamp, owner);
            
            if((this.calculate_height(pivot.right) - this.calculate_height(pivot.left)) == 2)
            {
                if(name_file.compareTo(pivot.right.name_file) > 0)
                    this.simple_rot(pivot, true);
                else
                    this.doubble_rot(pivot, true);
            }
        }else if(pivot.name_file.compareTo(name_file) > 0)
        {
            if(pivot.left == null)
                pivot.left = new Node(name_file, content, timestamp, owner);
            else
                this.insert(pivot.left, name_file, content, timestamp, owner);

            if((this.calculate_height(pivot.left) - this.calculate_height(pivot.right)) == 2)
            {
                if(name_file.compareTo(pivot.left.name_file) < 0)
                    this.simple_rot(pivot, false);
                else
                    this.doubble_rot(pivot, false);
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

            int root_height = this.calculate_height(this.root);
            int root_right = this.calculate_height(this.root.right);
            int root_left = this.calculate_height(this.root.left);

            
            txtfile += this.root.name_file.replace('.', '_') + "[label= \"  <A0> |";
            txtfile +=  "Name: " + this.root.name_file + "\\nContent: " + this.root.content + "\\nAltura: " + String.valueOf(root_height) + "\\nFE: " + String.valueOf(root_right - root_left ) + "\\nTimestamp: " + this.root.timestamp+ "\\nOwner: " +this.root.owner + " | <A1> \"];\n";
            this.tree_graph(root);
            txtfile += "}";
            
            Main_Class.save_file(txtfile, "avl_of_files");

        }
    }
    
    private void tree_graph(Node node)
    {
        if (node != null)
        {
            if(node.left != null)
            {
                txtfile += node.left.name_file.replace('.', '_')  + "[label= \"  <A0> |";
                txtfile += "Name: " + node.left.name_file + "\\nContent: " + node.left.content + "\\nAltura: " + String.valueOf(this.calculate_height(node.left)) + "\\nFE: " + String.valueOf(this.calculate_height(node.left.right) - this.calculate_height(node.left.left) )  + " | <A1> \"];\n";
                txtfile += node.name_file.replace('.', '_')  + ":A0" +" -> "+ node.left.name_file.replace('.', '_')  +";\n";
            }
            if(node.right != null)
            {
                txtfile += node.right.name_file.replace('.', '_')  + "[label= \"  <A0> |";
                txtfile += "Name: " + node.right.name_file + "\\nContent: " + node.right.content + "\\nAltura: " + String.valueOf(this.calculate_height(node.right)) + "\\nFE: " + String.valueOf(this.calculate_height(node.right.right) - this.calculate_height(node.right.left) )  + " | <A1> \"];\n";
                txtfile += node.name_file.replace('.', '_')  + ":A1" +" -> "+ node.right.name_file.replace('.', '_')  +";\n";
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

}
