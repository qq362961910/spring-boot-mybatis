package com.wxsk.platform.game.config.properties;


import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "application.dubbo")
public class DubboProperties {

    /**
     * applicationName
     * */
    private String applicationName;

    /**
     * client name
     * */
    private String clientName;

    /**
     * registryAddress
     * */
    private String registryAddress;

    /**
     * port
     * */
    private int port;

    public String getApplicationName() {
        return applicationName;
    }

    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getRegistryAddress() {
        return registryAddress;
    }

    public void setRegistryAddress(String registryAddress) {
        this.registryAddress = registryAddress;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

}
