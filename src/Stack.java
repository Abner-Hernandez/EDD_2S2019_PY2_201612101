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
        txtfile += "aHtmlTable [\nshape=plaintext\ncolor=black\n";
        txtfile += "label=<\n";
        
        txtfile += "<table border='1' cellborder='1'>\n";
        txtfile += "<tr><td>user</td><td>Description</td></tr>\n";

        Nodo aux = this.first;
        
        do{
            txtfile += "<tr><td>" + aux.user_modify + "</td><td>Description:" + aux.description+ " Timestamp:" + aux.timestamp + "</td></tr>\n"; 
            aux = aux.next;
        }while(aux != first);
        txtfile += "</table>\n";
        txtfile += "\n>\n];\n}";
        Main_Class.save_file(txtfile, "binnacle");

    }
}
