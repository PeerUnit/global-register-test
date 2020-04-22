/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.inria.globalregister.test;

import fr.inria.peerunit.parser.SetGlobals;
import fr.inria.peerunit.parser.TestStep;
import fr.inria.peerunit.remote.GlobalVariables;
import java.net.InetAddress;
import java.net.UnknownHostException;
import org.greg.client.Greg;
import org.greg.server.GregServer;

/**
 *
 * @author sunye
 */
public class GlobalRegisterTest {

    private GlobalVariables globals;
    private InetAddress host;

    @SetGlobals
    public void setGlobals(GlobalVariables gv) {
        globals = gv;
    }

    @TestStep(range = "1", order = 1)
    public void startGregServer() throws Exception {
        //String[] args = new String[] {"-port","5676"};
        globals.put(0, InetAddress.getLocalHost());
        //GregServer.main(args);
    }

    @TestStep(range = "*", order = 2)
    public void configureLogger() throws Exception {
        host = (InetAddress) globals.get(0);
        System.setProperty("greg.server", host.getHostName());
        System.setProperty("greg.port", "5676");
    }

    @TestStep(range = "*", order = 3)
    public void logSomeData() throws Exception {
        for(int i = 0; i <= 100; i++) {
            Greg.log("Hello my friend: "+i);
        }
    }
}
