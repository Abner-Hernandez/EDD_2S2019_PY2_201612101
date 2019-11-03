
import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.swing.JOptionPane;

class Nodo_Matriz
{
    Nodo_Matriz up;
    Nodo_Matriz down;
    Nodo_Matriz next;
    Nodo_Matriz previous;
    String id;
    String father;
    String son;
    String value;
    AVLTree files;

    public Nodo_Matriz() {
        this.up = null;
        this.down = null;
        this.next = null;
        this.previous = null;
        this.id = "";
        this.father = "";
        this.son = "";
        this.value = "";
        this.files = new AVLTree();
    }
    
    public Nodo_Matriz(String father, String son, String value, String id){
        this.up = null;
        this.down = null;
        this.next = null;
        this.previous = null;
        this.father = father;
        this.son = son;
        this.value = value;
        this.files = new AVLTree();
        this.id = id;
    }
}

public class Matriz {
    Nodo_Matriz header;
    
    public Matriz()
    {
        this.header = new Nodo_Matriz();
        
        Nodo_Matriz yInsertF = new Nodo_Matriz("", "", "/", "a" + String.valueOf(Main_Class.number_nodo++));
        yInsertF.up = this.header;
        this.header.down = yInsertF;
        
        Nodo_Matriz xInsertF = new Nodo_Matriz("", "", "/", "a" + String.valueOf(Main_Class.number_nodo++));
        xInsertF.previous = this.header;
        this.header.next = xInsertF;
        
    }
    
    public void insert_folder(ArrayList<String> path)
    {        
        if (this.header.next != null && this.header.down != null)
        {
            //folder in root
            int size = path.size();
            if(size == 2)
            {
                Nodo_Matriz aux_x = this.header.next;
                while(true)
                {
                    if(aux_x.next == null)
                        break;
                    aux_x = aux_x.next;
                }

                Nodo_Matriz xInsertS = new Nodo_Matriz("", "", path.get(1), "a" + String.valueOf(Main_Class.number_nodo++));
                xInsertS.previous = aux_x;
                aux_x.next = xInsertS;   

                Nodo_Matriz aux_root_x = this.header.down.next;
                if(this.header.down.next != null)
                {
                    while(true)
                    {
                        if(aux_root_x.next == null)
                            break;
                        aux_root_x = aux_root_x.next;
                    }
                }else
                    aux_root_x = this.header.down;
                

                Nodo_Matriz insert = new Nodo_Matriz(path.get(0), path.get(1), "", "a" + String.valueOf(Main_Class.number_nodo++));
                aux_root_x.next = insert;
                insert.previous = aux_root_x;
                xInsertS.down = insert;
                insert.up = xInsertS;

                Nodo_Matriz aux_y = this.header.down;
                while(true)
                {
                    if(aux_y.down == null)
                        break;
                    aux_y = aux_y.down;
                }

                Nodo_Matriz yInsertS = new Nodo_Matriz(path.get(0), "", path.get(1), "a" + String.valueOf(Main_Class.number_nodo++));
                yInsertS.up = aux_y;
                aux_y.down = yInsertS;

            }else
            {
                int number_match = 0, cont = 0;;

                Nodo_Matriz aux_y = this.header.down;
                String father = path.get(cont++);
                String son = path.get(cont);
                while(aux_y != null)
                {
                    if(father.equals(aux_y.father) && son.equals(aux_y.value))
                    {
                        number_match++;
                        father = path.get(cont++);
                        son = path.get(cont);
                    }

                    if(number_match == size - 2)
                    {
                        Nodo_Matriz aux_x = this.header.next;
                        while(true)
                        {
                            if(aux_x.next == null)
                                break;
                            aux_x = aux_x.next;
                        }

                        Nodo_Matriz xInsertS = new Nodo_Matriz("", "", path.get(size-1), "a" + String.valueOf(Main_Class.number_nodo++));
                        xInsertS.previous = aux_x;
                        aux_x.next = xInsertS;   

                        Nodo_Matriz aux_root_x;
                        if(aux_y.next != null)
                            aux_root_x = aux_y.next;
                        else
                            aux_root_x = aux_y;

                        while(true)
                        {
                            if(aux_root_x.next == null)
                                break;
                            aux_root_x = aux_root_x.next;
                        }

                        Nodo_Matriz insert = new Nodo_Matriz(path.get(size-2), path.get(size-1), "", "a" + String.valueOf(Main_Class.number_nodo++));
                        aux_root_x.next = insert;
                        insert.previous = aux_root_x;
                        xInsertS.down = insert;
                        insert.up = xInsertS;

                        Nodo_Matriz yInsertS = new Nodo_Matriz(path.get(size-2), "", path.get(size-1), "a" + String.valueOf(Main_Class.number_nodo++));
                        yInsertS.up = aux_y;

                        if(aux_y.down != null)
                        {
                            yInsertS.down = aux_y.down;
                            aux_y.down.up = yInsertS;
                        }

                        aux_y.down = yInsertS;
                        break;
                    }

                    aux_y = aux_y.down;
                }

            }
        }
    }

    public void insert_file(ArrayList<String> path, String file, String content)
    {
        int size = path.size();
        
        if(size > 1)
        {
            int number_match = 0, cont = 0;;
            Nodo_Matriz aux_y = this.header.down;
            String father = path.get(cont++);
            String son = path.get(cont);
            while(aux_y != null)
            {
                if(father.equals(aux_y.father) && son.equals(aux_y.value))
                {
                    if(cont < size-1)
                    {
                        father = path.get(cont++);
                        son = path.get(cont);
                    }
                    number_match++;
                }

                if(number_match == size -2)
                {
                    Nodo_Matriz aux_xD = aux_y.next;
                    while(aux_xD != null)
                    {
                        if(aux_xD.son.equals(path.get(size-1)))
                        {
                            aux_xD.files.insert_file(file, content);
                            break;
                        }
                        aux_xD = aux_xD.next;
                    }
                }
                aux_y = aux_y.down;
            }
        }else
        {
            this.header.down.files.insert_file(file, content);
        }

    }

    public void modify_file(ArrayList<String> path, String file, String content)
    {
        int size = path.size()-1;
        if(size > 1)
        {
            int number_match = 0, cont = 0;;
            Nodo_Matriz aux_y = this.header.down;
            String father = path.get(cont++);
            String son = path.get(cont);
            while(aux_y != null)
            {
                if(father.equals(aux_y.father) && son.equals(aux_y.value))
                {
                    if(cont < size-2)
                    {
                        father = path.get(cont++);
                        son = path.get(cont);
                    }
                    number_match++;
                }

                if(number_match == size -2)
                {
                    Nodo_Matriz aux_xD = aux_y.next;
                    while(aux_xD != null)
                    {
                        if(aux_xD.son.equals(path.get(size-1)))
                        {
                            aux_xD.files.modify_file(path.get(size), content);
                            break;
                        }
                        aux_xD = aux_xD.next;
                    }
                }
                aux_y = aux_y.down;
            }
        }else
        {
            this.header.down.files.modify_file(path.get(size), content);
        }
        
    }

    public void delete_file(ArrayList<String> path)
    {
        int size = path.size()-1;
        if(size > 1)
        {
            int number_match = 0, cont = 0;;
            Nodo_Matriz aux_y = this.header.down;
            String father = path.get(cont++);
            String son = path.get(cont);
            while(aux_y != null)
            {
                if(father.equals(aux_y.father) && son.equals(aux_y.value))
                {
                    if(cont < size-2)
                    {
                        father = path.get(cont++);
                        son = path.get(cont);
                    }
                    number_match++;
                }

                if(number_match == size -2)
                {
                    Nodo_Matriz aux_xD = aux_y.next;
                    while(aux_xD != null)
                    {
                        if(aux_xD.son.equals(path.get(size-1)))
                        {
                            aux_xD.files.delete_file(path.get(size));
                            break;
                        }
                        aux_xD = aux_xD.next;
                    }
                }
                aux_y = aux_y.down;
            }
        }else
        {
            this.header.down.files.delete_file(path.get(size));
        }
        
    }
    
    public String get_content_file(ArrayList<String> path, String file)
    {
        int size = path.size()-1;
        if(size > 1)
        {
            int number_match = 0, cont = 0;;
            Nodo_Matriz aux_y = this.header.down;
            String father = path.get(cont++);
            String son = path.get(cont);
            while(aux_y != null)
            {
                if(father.equals(aux_y.father) && son.equals(aux_y.value))
                {
                    if(cont < size-2)
                    {
                        father = path.get(cont++);
                        son = path.get(cont);
                    }
                    number_match++;
                }

                if(number_match == size -2)
                {
                    Nodo_Matriz aux_xD = aux_y.next;
                    while(aux_xD != null)
                    {
                        if(aux_xD.son.equals(path.get(size-1)))
                        {
                            return aux_xD.files.get_content(file);
                        }
                        aux_xD = aux_xD.next;
                    }
                }
                aux_y = aux_y.down;
            }
        }
        else
        {
            return this.header.down.files.get_content(file);
        }

        return null;
    }
    
    public void deleteFolder(ArrayList<String> path, int number_folders_del)
    {
        int size = path.size();
        int number_match = 0, cont = 0;;

        Nodo_Matriz aux_y = this.header.down;
        String father = path.get(cont++);
        String son = path.get(cont);

        if(size > 1)
        {
            while(aux_y != null)
            {
                if(father.equals(aux_y.father) && son.equals(aux_y.value))
                {
                    if(cont < size-1)
                    {
                        father = path.get(cont++);
                        son = path.get(cont);
                    }
                    number_match++;
                }

                if(number_match == size -2)
                {
                    Nodo_Matriz aux_xD = aux_y.next;
                    while(aux_xD != null)
                    {
                        if(aux_xD.son.equals(path.get(size-1)))
                        {
                            if(aux_xD.up.next != null)
                                aux_xD.up.next.previous = aux_xD.up.previous;
                            aux_xD.up.previous.next = aux_xD.up.next;
                            if(aux_xD.next != null)
                                aux_xD.next.previous = aux_xD.previous;
                            aux_xD.previous.next = aux_xD.next;
                            break;
                        }
                        aux_xD = aux_xD.next;
                    }
                }

                if(number_match == size -1)
                {
                    Nodo_Matriz aux_y2 = aux_y;

                    while(number_folders_del > 0)
                    {
                        if(aux_y2.next != null)
                        {
                            Nodo_Matriz aux_xD = aux_y2.next;
                            while(aux_xD != null)
                            {
                                if(aux_xD.up.next != null)
                                    aux_xD.up.next.previous = aux_xD.up.previous;
                                aux_xD.up.previous.next = aux_xD.up.next;
                                aux_xD = aux_xD.next;
                            }
                        }
                        number_folders_del--;
                        aux_y2 = aux_y2.down;
                    }
                    if (aux_y2 != null)
                        aux_y2.up = aux_y.up;
                    aux_y.up.down = aux_y2;
                    break;
                }

                aux_y = aux_y.down;
            }
        }else
        {

            Nodo_Matriz aux_xD = aux_y.next;
            while(aux_xD != null)
            {
                if(aux_xD.son.equals(path.get(size-1)))
                {
                    if(aux_xD.up.next != null)
                        aux_xD.up.next.previous = aux_xD.up.previous;
                    aux_xD.up.previous.next = aux_xD.up.next;
                }
                aux_xD = aux_xD.next;
            }
            while(aux_y != null)
            {
                if(father.equals(aux_y.father) && son.equals(aux_y.value))
                {
                    Nodo_Matriz aux_y2 = aux_y.next;
                    while(aux_y2 != null)
                    {

                        aux_xD = aux_xD.next;
                    }
                }
                aux_y = aux_y.down;
            }
        }

    }

    public void modify_folder(ArrayList<String> path, String name)
    {
        int size = path.size();
        int number_match = 0, cont = 0;;

        Nodo_Matriz aux_y = this.header.down;
        String father = path.get(cont++);
        String son = path.get(cont);
        while(aux_y != null)
        {
            if(father.equals(aux_y.father) && son.equals(aux_y.value))
            {
                if(cont < size-1)
                {
                    father = path.get(cont++);
                    son = path.get(cont);
                }
                number_match++;
            }

            if(number_match == size -2)
            {
                Nodo_Matriz aux_xD = aux_y.next;
                while(aux_xD != null)
                {
                    if(aux_xD.son.equals(path.get(size-1)))
                    {
                        aux_xD.up.value = name;
                        aux_xD.son = name;
                        break;
                    }

                    aux_xD = aux_xD.next;
                }
            }

            if(number_match == size -1)
            {
                aux_y.value = name;
                Nodo_Matriz aux_x = aux_y.next;
                while(aux_x != null)
                {
                    aux_x.father = name;
                    aux_x = aux_x.next;
                }
                break;
            }

            aux_y = aux_y.down;
        }
    }

    public void graficar(String postGraph)
    {
        if(this.header.next != null || this.header.down != null)
        {
            String txtArchivo;
            txtArchivo ="";
            txtArchivo += "digraph Mass{\n";
            txtArchivo += "rankdir = TB; \n";
            txtArchivo += "node[shape = rectangle, height = 1, width = 1]; \n";
            txtArchivo += "graph[nodesep = 0.5]; \n";

            Nodo_Matriz auxNext = this.header.next;
            txtArchivo += "header[shape = record  label= \"Header\"]; \n";

            while(auxNext != null)
            {
                txtArchivo += auxNext.id + "[label= \""+auxNext.value+"\"";
                txtArchivo += "];\n";
                auxNext = auxNext.next;
            }
            txtArchivo += "header -> "+this.header.next.id + " [constraint=false, dir=\"both\"]; \n";

            Nodo_Matriz auxDown = this.header.down;

            while(auxDown != null)
            {
                txtArchivo += auxDown.id + "[label= \""+auxDown.value+"\"";
                txtArchivo += "];\n";
                auxDown = auxDown.down;
            }
            txtArchivo += "header -> "+ this.header.down.id + " [dir=\"both\"]; \n";

            auxDown = this.header.down;

            while(auxDown != null)
            {
                if(auxDown.down != null)
                    txtArchivo += auxDown.id + " -> "+ auxDown.down.id + " [dir=\"both\"]; \n";

                if(auxDown.next == null)
                {
                    auxDown = auxDown.down;
                    continue;
                }
                Nodo_Matriz aux = auxDown.next;

                txtArchivo += aux.id + "[label= ";
                txtArchivo += "\""+ aux.father + "/" + aux.son +"\"";
                txtArchivo += "];\n";

                aux = aux.next;
                while(aux != null)
                {
                    txtArchivo += aux.id + "[label= ";
                    txtArchivo += "\""+ aux.father + "/" + aux.son +"\"";
                    txtArchivo += "];\n";

                    txtArchivo += aux.previous.id +" -> "+ aux.id +" [ constraint=false, dir=\"both\"]; \n";
                    aux = aux.next;
                }

                //graficarRecursivo(auxDown.next,txtArchivo);


                if(auxDown.next != null)
                    txtArchivo += auxDown.id + " -> "+ auxDown.next.id + " [constraint=false, dir=\"both\"]; \n";
                auxDown = auxDown.down;
            }

            auxNext = this.header.next;

            while(auxNext != null)
            {
                Nodo_Matriz aux = auxNext.down;
                while(aux != null)
                {
                    if(aux.down != null)
                        txtArchivo += aux.id + " -> " + aux.down.id + "; \n";
                    aux = aux.down;
                }

                if (auxNext.down != null)
                    txtArchivo += auxNext.id + " -> " + auxNext.down.id+" [dir=\"both\"]; \n";

                if(auxNext.next != null)
                    txtArchivo += auxNext.id+" -> "+ auxNext.next.id + " [dir=\"both\"]; \n";
                auxNext = auxNext.next;
            }

            // Align
            auxNext = this.header.next;

            txtArchivo += "{ rank=same; header ";
            while (auxNext != null)
            {
                txtArchivo += auxNext.id+ " ";
                auxNext = auxNext.next;
            }
            txtArchivo += "}\n";

            auxDown = this.header.down;
            while(auxDown != null)
            {

                auxNext = auxDown.next;
                txtArchivo += "{ rank=same;"+auxDown.id+" ";
                while (auxNext != null)
                {
                    txtArchivo += auxNext.id+ " ";
                    auxNext = auxNext.next;
                }
                txtArchivo += "}\n";
                auxDown = auxDown.down;
            }


            txtArchivo += "\n}";

            save_file(txtArchivo , postGraph);
        }
    }

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
