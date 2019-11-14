class Node_List<T>
{
    Node_List next, previous;
    T data;
    
    Node_List(T name)
    {
        this.previous = null;
        this.next = null;
        this.data = name;
    }
}

public class List<T> {
    Node_List first;
    int size;
    
    public List()
    {
        this.first = null;
        this.size = 0 ;
    }
    
    public void add(T data)
    {
        Node_List<T> nuevo = new Node_List<>(data);

        if(this.first == null)
        {
            this.first = nuevo;
            this.first.next = this.first;
            this.first.previous = this.first;
            size++;
        }
        else
        {
            if(this.first.next == this.first)
            {
                this.first.next = nuevo;
                this.first.previous = nuevo;
                nuevo.next = this.first;
                nuevo.previous = this.first;
                size++;
            }else
            {
                nuevo.previous = this.first.previous;
                this.first.previous.next = nuevo;
                nuevo.next = this.first;
                this.first.previous = nuevo;
                size++;
            }
        }
    }
    
    public T get(int index)
    {
        Node_List<T> aux = first;
        int cont = 0 ;
        do{
            if(cont == index)
                return aux.data;
            cont++;
            aux = aux.next;
        }while(aux != this.first);
        
        return aux.data;
    }
    
}
