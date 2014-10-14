package ClientSide;


import Net.Logging.XMLSingleBranchedConfigurator;

import java.io.File;
import java.io.IOException;

/**
 * Created by svt on 03.10.2014.
 */
public class GlobalConfig {

    private static GlobalConfig ourInstance = new GlobalConfig();
    private String ip;
    private String port;
    private String seanceName;
    private final XMLSingleBranchedConfigurator configer = new XMLSingleBranchedConfigurator();
    private final String CONFIG_FILE_NAME = "ClientConfig.cfg";

    public static GlobalConfig getInstance() {
        return ourInstance;
    }

    private GlobalConfig() {
        //TODO
        try {
            ip = configer.readConfig(CONFIG_FILE_NAME,"ip");
            port = configer.readConfig(CONFIG_FILE_NAME,"port");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getIP() {
        return ip;
    }

    public void setIP(String IP) {
        ip = IP;
        try {
            configer.writeConfig(CONFIG_FILE_NAME,"ip",IP);
        } catch (IOException e) {
            e.printStackTrace();
        }
        catch (Exception e){
            setIP("");
            setPort("");
        }
    }

    public String getSeanceName(){
        return seanceName;
    }

    public void setSeanceName(String seanceName){
        this.seanceName = seanceName;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
        try {
            configer.writeConfig(CONFIG_FILE_NAME,"port", port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
