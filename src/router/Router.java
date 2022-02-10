
package router;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Router {
    int numOfConnections=0;
    ArrayList<Device> List;
    Router(int num){
        numOfConnections=num;
        List=new ArrayList();
    }

    Router() {
        
    }
    public void AddConnection(Device device){
        List.add(device);
    }
    
    public synchronized void StartConnection() throws InterruptedException{
        for(int i=0 ;i<numOfConnections;i++){
                List.get(i).start();
        }        
    }

    public synchronized void FinishConnection() throws InterruptedException{
        for(int i=0;i<numOfConnections;i++){
                List.get(i).Disconnect();
        }
        
    }

}