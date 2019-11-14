import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.swing.JOptionPane;

public class Users {
    User users[];
    
    public Users()
    {
        this.users = new User[7];
    }

    public void reziseUsers()
    {
        int nextPrime = this.calculate_prime(this.users.length);
        User aux_User[] = new User[nextPrime];

        for (User user : users) {
            if(user == null)
                continue;
            int pos = this.funtion_hash(this.codify(user.user));
            int no_loop = 0, index = 0;
            int last_index[] = new int[4];
            while(true)
            {
                if(pos > aux_User.length)
                {
                    pos = get_new_index(pos, aux_User.length);
                    if (loop_index(last_index, pos))
                        no_loop++;
                }
                if(aux_User[pos] != null)
                {
                    if (index < 4)
                        last_index[index++] = pos;
                    else
                        index = 0;

                    pos *= pos;

                    if (loop_index(last_index, pos))
                        no_loop++;
                    if(no_loop > 5)
                    {
                        no_loop = 0;
                        pos += 1;
                    }
                }else
                {
                    aux_User[pos] = user;
                    last_index = new int[4];
                    break;
                }
            }
        }

        //System.arraycopy(this.users, 0, aux_User, 0, this.users.length);
        this.users = aux_User;
    }

    private int calculate_prime(int ini)
    {
        for(int i = ini+1 ; ini <= 9999999 ; i++)
        {
            boolean isPrime = true;
            for(int j = 2 ; j < i ; j++)
                if(i % j == 0)
                    isPrime = false;
            if(isPrime)
                return i;
        }
        return 0;
    }

    private int codify(String str)
    {
        int scii_number = 0;
        for(int i = 0 ; i < str.length() ; i++)
        {
            scii_number += (int) str.charAt(i);
        }
        return scii_number;
    }

    public int funtion_hash(int user_codificated)
    {
        return user_codificated % this.users.length;
    }

    private int get_new_index(int index, int lengh_array)
    {
        while(true)
        {
          if(index > lengh_array)
              index -= lengh_array;
          else
              break;
        }
        return index;
    }

    public String get_hash_pass(String pass) {
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(pass.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch(UnsupportedEncodingException | NoSuchAlgorithmException ex){
           throw new RuntimeException(ex);
        }
    }

    private void redimension_users()
    {
        int count = 0;
        for(int i = 0 ; i < this.users.length ; i++)
        {
            if(users[i] != null)
                count++;
        }
        float occupied = ((float)count / users.length);
        if( occupied > 0.7)
            this.reziseUsers();
    }

    public void insertUser(String user_name, String pass)
    {
        int no_loop = 0, index = 0;
        int last_index[] = new int[4];
        int pos = this.funtion_hash(this.codify(user_name));
        while(true)
        {
            if(pos > users.length)
            {
                pos = get_new_index(pos, users.length);
                if (loop_index(last_index, pos))
                    no_loop++;
            }
            if(this.users[pos] != null)
            {
                if (index < 4)
                    last_index[index++] = pos;
                else
                    index = 0;
                
                pos *= pos;
                
                if (loop_index(last_index, pos))
                    no_loop++;
                if(no_loop > 5)
                {
                    no_loop = 0;
                    pos += 1;
                }
            }else
            {
                users[pos] = new User(user_name, this.get_hash_pass(pass));
                last_index = new int[4];
                this.redimension_users();
                break;
            }
        }
    }

    private boolean loop_index(int pos_prev[], int actual_pos)
    {
        for (int i : pos_prev) {
            if(actual_pos == i)
                return true;
        }
        return false;
    }
    
    public boolean search_user(String user)
    {
            for (User user1 : this.users) {
                if (user1 != null && user1.user.equals(user))
                    return true;
            }
            return false;
    }

    public void upload_users(String csv)
    {
        int ini = 0;
        String user = "", not_inserted = "";
        for(int i = 0 ; i < csv.length() ; i++)
        {
            if(csv.charAt(i) == ',')
            {
                user = csv.substring(ini,i);
                ini = i+1;
            }else if (csv.charAt(i) == '\n')
            {
                if(user.toLowerCase().equals("usuario"))
                {
                    ini = i+1;
                    user = "";
                    continue;
                }

                boolean insert = this.search_user(user);
                if(insert)
                    not_inserted += "The User: " + user + "was not created because the name already exists.\n";
                else
                {
                    if(csv.substring(ini, i-1).length() < 8)
                        not_inserted += "The User: " + user + " was not created because password must be greater than 8 characters.\n";
                    else
                        this.insertUser(user, csv.substring(ini, i));
                }       
                ini = i+1;
            }
        }
        if(!not_inserted.equals(""))
            JOptionPane.showMessageDialog(null, not_inserted);
    }

    public void graph_table()
    {
        String txtfile = "digraph Mass{\n";
        txtfile += "aHtmlTable [\nshape=plaintext\ncolor=black\n";
        txtfile += "label=<\n";
        
        txtfile += "<table border='1' cellborder='1'>\n";
        txtfile += "<tr><td>Pos.</td><td>User's Data</td></tr>\n";

        for (int i = 0 ; i < this.users.length ; i++) {
            if(this.users[i] == null)
                txtfile += "<tr><td>" + String.valueOf(i) + "</td><td></td></tr>\n";
            else
                txtfile += "<tr><td>" + String.valueOf(i) + "</td><td>Name:" + this.users[i].user + " Pass:" + this.users[i].pass + " Timestamp:" + this.users[i].timestamp + "</td></tr>\n"; 
        }
        txtfile += "</table>\n";
        txtfile += "\n>\n];\n}";
        Main_Class.save_file(txtfile, "users");

    }

}
