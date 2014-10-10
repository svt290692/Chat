package ClientSide;

import ClientSide.Interfaces.Configuration;
import Net.Logging.XMLSingleBranchedConfigurator;

import java.io.File;
import java.io.IOException;

/**
 * Created by svt on 03.10.2014.
 */
public class GlobalConfig implements Configuration{

    private static GlobalConfig ourInstance = new GlobalConfig();
    private String ip;
    private int port;
    private final XMLSingleBranchedConfigurator configer = new XMLSingleBranchedConfigurator();
    private final String CONFIG_FILE_NAME = "ClientConfig.cfg";

    public static GlobalConfig getInstance() {
        return ourInstance;
    }

    private GlobalConfig() {
        //TODO
        try {
            ip = configer.readConfig(CONFIG_FILE_NAME,"ip");
            port = Integer.parseInt(configer.readConfig(CONFIG_FILE_NAME,"port"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getIP() {
        return ip;
    }

    @Override
    public void setIP(String IP) {
        ip = IP;
        try {
            configer.writeConfig(CONFIG_FILE_NAME,"ip",IP);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getPort() {
        return port;
    }

    @Override
    public void setPort(int port) {
        this.port = port;
        try {
            configer.writeConfig(CONFIG_FILE_NAME,"port", Integer.toString(port));
        } catch (IOException e) {

            e.printStackTrace();
        }
    }
}
