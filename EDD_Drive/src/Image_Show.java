import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

class Image_Show extends JFrame
{
   public Image_Show(String path)
  {

     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     setTitle("Image view");

     ImageIcon image = new ImageIcon(path);
     image.getImage().flush();
     JLabel label = new JLabel(image);
     JScrollPane scrollPane = new JScrollPane(label);
     scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
     scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
     add(scrollPane, BorderLayout.CENTER);
     pack();
  }
}
