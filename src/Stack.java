import java.sql.Timestamp;

/**
 *
 * @author abner
 */
class Nodo
{
    Nodo next, previous;
    String description, timestamp, user_modify;
    
    Nodo(String description, String user_modify)
    {
        this.previous = null;
        this.next = null;
        Timestamp times = new Timestamp(System.currentTimeMillis());
        this.timestamp = times.toString();
        this.description = description;
        this.user_modify = user_modify;
    }
}

public class Stack {
    Nodo first;
    
    public Stack()
    {
        this.first = null;
    }
    
    public void insert(String description, String user_modify)
    {
        Nodo nuevo = new Nodo(description,user_modify);

        if(this.first == null)
        {
            this.first = nuevo;
            this.first.next = this.first;
            this.first.previous = this.first;
        }
        else
        {
            if(this.first.next == this.first)
            {
                this.first.next = nuevo;
                this.first.previous = nuevo;
                nuevo.next = this.first;
                nuevo.previous = this.first;
            }else
            {
                nuevo.previous = this.first.previous;
                this.first.previous.next = nuevo;
                nuevo.next = this.first;
                this.first.previous = nuevo;
            }
        }
    }
    
    public void graph_table()
    {
        String txtfile = "digraph Mass{\n";
        txtfile += "rankdir = LR;";
        
        txtfile += "node[shape = record , height = 0.5, width = 1]; ";

        Nodo aux = this.first;
        txtfile += "node1[label= \"User:" + aux.user_modify + " Description:" + aux.description+ "\\nTimestamp:" + aux.timestamp + ""; 
            aux = aux.next;
        do{
            txtfile += "|User:" + aux.user_modify + " Description:" + aux.description+ "\\nTimestamp:" + aux.timestamp + ""; 
            aux = aux.next;
        }while(aux != first);
        txtfile += "\"];";
        txtfile += "node2[label= \"The last change made by the user is inserted last\\nbut is extracted first. LIFO\"];\n}";
        Main_Class.save_file(txtfile, "binnacle");

    }
}
