import java.util.ArrayList;
class Nodo_Matriz
{
    Nodo_Matriz up;
    Nodo_Matriz down;
    Nodo_Matriz next;
    Nodo_Matriz previous;
    String id;
    String id_like_node;
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
        this.id_like_node = null;
        this.father = "";
        this.son = "";
        this.value = "";
        this.files = new AVLTree();
    }
    
    public Nodo_Matriz(String father, String son, String value, String id, String id_like_node){
        this.up = null;
        this.down = null;
        this.next = null;
        this.previous = null;
        this.father = father;
        this.son = son;
        this.value = value;
        this.files = new AVLTree();
        this.id = id;
        this.id_like_node = id_like_node;
    }
}

public class Matriz {
    Nodo_Matriz header;
    int num_nodo;
    String user_name;
    
    public Matriz()
    {
        this.header = new Nodo_Matriz();
        this.num_nodo = 0;
        this.user_name = null;
    }
    
    public void insert_root()
    {
        Nodo_Matriz yInsertF = new Nodo_Matriz("", "", "/", "a" + String.valueOf(num_nodo++), null);
        yInsertF.id_like_node = yInsertF.id;
        yInsertF.up = this.header;
        this.header.down = yInsertF;
        
        Nodo_Matriz xInsertF = new Nodo_Matriz("", "", "/", "a" + String.valueOf(num_nodo++), null);
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

                Nodo_Matriz xInsertS = new Nodo_Matriz("", "", path.get(1), "a" + String.valueOf(num_nodo++), null);
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
                

                Nodo_Matriz insert = new Nodo_Matriz(path.get(0), path.get(1), "", "a" + String.valueOf(num_nodo++), null);
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

                Nodo_Matriz yInsertS = new Nodo_Matriz(path.get(0), "", path.get(1), "a" + String.valueOf(num_nodo++), insert.id);
                yInsertS.up = aux_y;
                aux_y.down = yInsertS;

            }else
            {
                int number_match = 0, cont = 0;

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

                        Nodo_Matriz xInsertS = new Nodo_Matriz("", "", path.get(size-1), "a" + String.valueOf(num_nodo++), null);
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

                        Nodo_Matriz insert = new Nodo_Matriz(path.get(size-2), path.get(size-1), "", "a" + String.valueOf(num_nodo++), null);
                        aux_root_x.next = insert;
                        insert.previous = aux_root_x;
                        xInsertS.down = insert;
                        insert.up = xInsertS;

                        Nodo_Matriz yInsertS = new Nodo_Matriz(path.get(size-2), "", path.get(size-1), "a" + String.valueOf(num_nodo++), insert.id);
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

    public void insert_file(ArrayList<String> path, String file, String content, String owner)
    {
        int size = path.size();
        
        if(size > 1)
        {
            int number_match = 0, cont = 0;
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
                            aux_xD.files.insert_file(file, content, owner);
                            break;
                        }
                        aux_xD = aux_xD.next;
                    }
                }
                aux_y = aux_y.down;
            }
        }else
        {
            this.header.down.files.insert_file(file, content, owner);
        }

    }

    public boolean insert_file_shared(String file, String content, String owner, String timestamp)
    {
        if(!this.header.down.files.exists_file(file))
        {
            this.header.down.files.insert_file(file, content, owner, timestamp);
            return true;
        }else
            return false;
    }
    
    public void modify_file(ArrayList<String> path, String file, String content)
    {
        int size = path.size()-1;
        if(size > 1)
        {
            int number_match = 0, cont = 0;
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
            int number_match = 0, cont = 0;
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
            int number_match = 0, cont = 0;
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
    
    public String get_timestamp(ArrayList<String> path, String file)
    {
        int size = path.size()-1;
        if(size > 1)
        {
            int number_match = 0, cont = 0;
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
                            return aux_xD.files.get_timestamp(file);
                        }
                        aux_xD = aux_xD.next;
                    }
                }
                aux_y = aux_y.down;
            }
        }
        else
        {
            return this.header.down.files.get_timestamp(file);
        }

        return null;
    }
    
    public void deleteFolder(ArrayList<String> path, int number_folders_del)
    {
        int size = path.size();
        int number_match = 0, cont = 0;

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
        }
    }

    public void modify_folder(ArrayList<String> path, String name)
    {
        int size = path.size();
        
        int number_match = 0, cont = 0;
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

    public void create_avl_tree(ArrayList<String> path)
    {
        int size = path.size();
        
        if(size > 1)
        {
            int number_match = 0, cont = 0;
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
                            aux_xD.files.create_avl();
                            aux_xD.files.tree_graph();
                            break;
                        }

                        aux_xD = aux_xD.next;
                    }
                }
                aux_y = aux_y.down;
            }
        }else
        {
            this.header.down.files.create_avl();
            this.header.down.files.tree_graph();
        }
    }
    
    public void graph_matrix()
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

                //graph_matrixRecursivo(auxDown.next,txtArchivo);


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

            Main_Class.save_file(txtArchivo , "matrix_adj");
        }
    }

    public void graph()
    {
        if(header.next != null)
        {
            String txtArchivo;
            txtArchivo ="";
            txtArchivo += "digraph Mass{\n";
            txtArchivo += "node[shape = oval, height = 1, width = 1]; \n";

            Nodo_Matriz aux = this.header.down;
            txtArchivo += aux.id + "[label= \"";
            txtArchivo += aux.value +"\"";
            txtArchivo += "];\n";
            while(aux != null)
            {
                if(aux.next != null)
                {
                    Nodo_Matriz aux_x = aux.next;
                    while(aux_x != null)
                    {
                        txtArchivo += aux_x.id + "[label= \"";
                        txtArchivo += aux_x.son +"\"";
                        txtArchivo += "];\n";
                        txtArchivo += aux.id_like_node+" -> "+ aux_x.id + "; \n";
                        aux_x = aux_x.next;
                    }
                }
                
                aux = aux.down;
            }
            txtArchivo += "}";
            Main_Class.save_file(txtArchivo, "graph");
        }
    }
}
