
package router;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.border.Border;

/**
 *
 * @author Khaled Ashraf,
 * @author Ahmed Sayed,
 * @author Ebrahim Muhammed.
 * 
 */
class myRouter
        extends JFrame
        implements ActionListener {
    
    private final Container c;
    private final JLabel title;
    private final JLabel numOfConnectionsArea;
    private final JTextField numOfConnectionsContent;
    private final JLabel numOfDevicesArea;
    private final JTextField numOfDevicesContent;
    private JLabel devicesArea;
    private JTextArea devicesContent;
    private final JButton submit;
    File file=new File("States.txt");
    int maxRunning;
    int numOfDevices;
    ArrayList Devices = new ArrayList();
    
    public static String FRead(File file) throws Exception {
        FileInputStream read = new FileInputStream(file);
        int i=0;String s="";
        while ((i=read.read())!=-1){
            s=s+(char)i;
        }
        read.close();
        return s;
    }
    
    public myRouter() {
        setTitle("My Router");
        setBounds(600, 100, 450, 535);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        Border border = BorderFactory.createLineBorder(Color.black, 1);
        
        c = getContentPane();
        c.setLayout(null);
        title = new JLabel("My Router");
        title.setFont(new Font("Arial", Font.PLAIN, 28));
        title.setSize(200, 30);
        title.setLocation(160, 30);
        c.add(title);
        c.setBackground(Color.white);
        numOfConnectionsArea = new JLabel("What is the number of WI-FI Connections?");
        numOfConnectionsArea.setSize(250, 20);
        numOfConnectionsArea.setLocation(100, 100);
        c.add(numOfConnectionsArea);
        
        numOfConnectionsContent = new JTextField();
        numOfConnectionsContent.setFont(new Font("Arial", Font.PLAIN, 15));
        numOfConnectionsContent.setSize(250, 20);
        numOfConnectionsContent.setLocation(100, 120);
        c.add(numOfConnectionsContent);
        numOfConnectionsContent.setBorder(border);
        
        numOfDevicesArea = new JLabel("What is the number of devices?");
        numOfDevicesArea.setSize(300, 20);
        numOfDevicesArea.setLocation(100, 160);
        c.add(numOfDevicesArea);

        numOfDevicesContent = new JTextField();
        numOfDevicesContent.setFont(new Font("Arial", Font.PLAIN, 15));
        numOfDevicesContent.setSize(250, 20);
        numOfDevicesContent.setLocation(100, 180);
        c.add(numOfDevicesContent);
        numOfDevicesContent.setBorder(border);

        devicesArea = new JLabel("Devices");
        devicesArea.setSize(250, 20);
        devicesArea.setFont(new Font("Arial", Font.PLAIN, 15));
        devicesArea.setForeground(Color.black);
        devicesArea.setLocation(195, 210);
        c.add(devicesArea);
       
        
        devicesContent = new JTextArea();
        devicesContent.setSize(200, 200);
        devicesContent.setLocation(125, 230);
        devicesContent.setLineWrap(true);
        c.add(devicesContent);
        devicesContent.setBorder(border);
        
        submit = new JButton("Submit");
        submit.setFont(new Font("Arial", Font.PLAIN, 15));
        submit.setSize(100, 20);
        submit.setLocation(170, 450);
        submit.addActionListener(this);
        c.add(submit);
        submit.setBackground(Color.LIGHT_GRAY);
        submit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == submit) {
            maxRunning = Integer.parseInt(numOfConnectionsContent.getText());
            numOfDevices = Integer.parseInt(numOfDevicesContent.getText());
            Devices.addAll(Arrays.asList(devicesContent.getText().split("\\n")));
            semaphore S = new semaphore(maxRunning);
            Router R = new Router(numOfDevices);
            for (int i = 0; i < numOfDevices; i++) {
                int j = 0;
                String device = "", type;
                String str = Devices.get(i).toString();
                while (str.charAt(j) != ' ') {
                    device += str.charAt(j);
                    j++;
                }
                type = Devices.get(i).toString().substring(j + 1);
                Device d = new Device(device, DevicesType.valueOf(type), S);
                R.AddConnection(d);
            }
            try {
                
                R.StartConnection();
            } catch (InterruptedException ex) {
                Logger.getLogger(myRouter.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                Thread.sleep(numOfDevices*2100);
                c.removeAll();
                Border border = BorderFactory.createLineBorder(Color.black, 1);
                devicesArea = new JLabel("States of Devices\n");
                devicesArea.setSize(250, 20);
                devicesArea.setFont(new Font("Arial", Font.PLAIN, 15));
                devicesArea.setForeground(Color.black);
                devicesArea.setLocation(195, 210);
                c.add(devicesArea);


                devicesContent = new JTextArea(27,35);
                devicesContent.setLocation(125, 230);
                devicesContent.setLineWrap(true);
                c.add(devicesContent);
                devicesContent.setBorder(border);
                c.setLayout(new FlowLayout());
                
                JScrollPane scrollableTextArea = new JScrollPane(devicesContent);  
  
                scrollableTextArea.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);  
                scrollableTextArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);  
                
                c.add(scrollableTextArea); 
                
                String str=FRead(file);
                devicesContent.setText(str);
            } catch (Exception ex) {
                Logger.getLogger(myRouter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}

public class Network {

    public static void main(String[] args) throws InterruptedException {
         myRouter MyRouter = new myRouter();
    }

}
