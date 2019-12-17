package com.cvte.customer_service.cuse;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketConfig;
import com.corundumstudio.socketio.SocketIOServer;
import com.cvte.customer_service.cuse.listenner.EventListenner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
*在整个application之前需要启动
*@author chenbo
*@Date 2019/12/17 10:13 上午
*/
@Component
public class PushServer implements InitializingBean {


    @Resource
    private EventListenner eventListenner;

    private static Logger logger = LoggerFactory.getLogger(PushServer.class);

    @Override
    public void afterPropertiesSet() throws Exception {
        Configuration config = new Configuration();
        int serverPort = 8081;
        config.setPort(serverPort);

        SocketConfig socketConfig = new SocketConfig();
        socketConfig.setReuseAddress(true);
        socketConfig.setTcpNoDelay(true);
        socketConfig.setSoLinger(0);
        config.setSocketConfig(socketConfig);
        config.setHostname("localhost");

        SocketIOServer server = new SocketIOServer(config);
        server.addListeners(eventListenner);
        server.start();
        logger.info("启动正常");
    }
}