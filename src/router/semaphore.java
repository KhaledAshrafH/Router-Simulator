
package router;

import java.util.logging.Level;
import java.util.logging.Logger;

public class semaphore {

    
    private int Val = 0 ;
    int maxConnections;
    protected semaphore() {
        Val = 0 ;
    }

    protected semaphore(int max) {
        Val = max ;
        maxConnections=max;
    }

    public synchronized void P() {
        Val--;
        if (Val < 0)
             {
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(semaphore.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
    }

    public synchronized void V() throws InterruptedException {
        Val++;
        if (Val<=0){
            notify();
        }
    }
    
    public int getVal(){
        return Val;
    }
}