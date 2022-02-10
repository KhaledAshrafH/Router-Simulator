
package router;

import java.io.File;
import java.io.FileOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Device extends Thread {
    public static String all="";
    File file=new File("States.txt");
    public static semaphore S;
    static int count=0;
    public String Name;
    public DevicesType type;
    int ID;

    public Device(String name, DevicesType type,semaphore S) {
        this.Name = name;
        this.type = type;
        this.S=S;
    }

    public Device(semaphore S) {
        this.S = S;
    }

    public void setType(DevicesType type) {
        this.type = type;
    }

    public DevicesType getType() {
        return type;
    }

//
    public void connect() throws InterruptedException {
        S.P();
    }
//

    public void Disconnect() throws InterruptedException {
        S.V();
    }

    public void PerformOnlineActivity() throws Exception {
        System.out.println("- "+Name + " Perform Online Activity");
        FWrite("- "+Name + " Perform Online Activity");
    }

    public void run() {
        count++;
        ID=count;
        if(ID>S.maxConnections) {
            count-=2;ID=count;
        }
        if(S.maxConnections==1) ID=S.maxConnections;  
        try {
            if(S.getVal()-1<0){
                System.out.println("- "+"("+Name +")("+type.toString()+")"+ " arrived and waiting");
                FWrite("- "+"("+Name +")("+type.toString()+")"+ " arrived and waiting");
            } 
            else {
                System.out.println("- "+"("+Name +")("+type.toString()+")"+ " arrived");
                FWrite("- "+"("+Name +")("+type.toString()+")"+ " arrived");
            }
            
            System.out.println("- "+"Connection "+ID+" : "+Name + " Occupied");
            FWrite("- "+"Connection "+ID+" : "+Name + " Occupied");
            connect();
            System.out.println("- "+"Connection "+ID+" : "+Name + " Login");
            FWrite("- "+"Connection "+ID+" : "+Name + " Login");
            try {
                this.PerformOnlineActivity();
                Thread.sleep(2000);
            } 
            finally {
                System.out.println( "- "+"Connection "+ID+" : "+Name + " Logged Out");
                FWrite( "- "+"Connection "+ID+" : "+Name + " Logged Out");
                FWriteAll(file);
                Disconnect();
            }
        } catch (InterruptedException e) {
        } catch (Exception ex) {
            Logger.getLogger(Device.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    public static void FWrite(String r) throws Exception {
        all+=(r+"\n");
    }
    public static void FWriteAll(File file) throws Exception {
        FileOutputStream Write = new FileOutputStream(file);
        byte[] names = all.getBytes();
        Write.write(names);
        Write.close();
    }
    
}



