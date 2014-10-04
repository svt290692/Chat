package ClientSide;

import ClientSide.Interfaces.Configuration;

/**
 * Created by svt on 03.10.2014.
 */
public class GlobalConfig implements Configuration{
    private static GlobalConfig ourInstance = new GlobalConfig();

    private String ip;
    private int port;

    public static GlobalConfig getInstance() {
        return ourInstance;
    }

    private GlobalConfig() {
        //TODO
        ip = "127.0.0.1";
        port = 5511;
    }

    @Override
    public String getIP() {
        return ip;
    }

    @Override
    public void setIP(String IP) {
        ip = IP;
    }

    @Override
    public int getPort() {
        return port;
    }

    @Override
    public void setPort(int port) {
        this.port = port;
    }
}
