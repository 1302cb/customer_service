package com.cvte.customer_service.cuse.service.impl;

import com.cvte.customer_service.cuse.clause.*;
import com.cvte.customer_service.cuse.service.AnswerService;
import com.cvte.customer_service.cuse.service.BotService;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BotServiceImpl implements BotService {

    private static Logger logger = LoggerFactory.getLogger(BotServiceImpl.class);

    @Autowired
    private AnswerService answerService;

    //后期修改配置，或者放到数据库的配置表中
    private final String CLAUSE_IP = "127.0.0.1";
    private final int CLAUSE_PORT = 8056;
    private final String chatbotID = "avtr002";
    final String intentName = "orderTakeOut";

    @Override
    public ChatMessage chat(String msg) {

        logger.info("聊天信息->" + msg);
        TTransport transport;
        try {
            transport = new TFramedTransport(new TSocket(CLAUSE_IP, CLAUSE_PORT));

            TProtocol protocol = new TBinaryProtocol(transport);

            Serving.Client client = new Serving.Client(protocol);
            transport.open(); // 建立连接

            ChatMessage resp = new ChatMessage();

            Data request = new Data();
            request.chatbotID = chatbotID;
            // 创建 session
            request = new Data();
            ChatSession session = new ChatSession();
            session.chatbotID = chatbotID;
            //后期换成自己的，先用测试的
            session.uid = "java"; // 用户唯一的标识
            session.channel = "testclient"; // 自定义，代表该用户渠道由字母组成
            session.branch = "dev"; // 测试分支，有连个选项：dev, 测试分支；pro，生产分支
            request.session = session;

            final Data s = client.putSession(request);
            logger.info("putSession >> \n{}", s.toString());

            // 发送消息
            request = new Data();
            request.session = s.session;
            ChatMessage message = new ChatMessage();
            message.textMessage = msg;
            request.message = message;
            logger.info("message >> \n{}", message.toString());
            Data response = client.chat(request);
            logger.info("chat >> \n{}", response.toString());
            resp.setSession_id(response.session.id);
            resp.setChatbotID(chatbotID);
            //如果是没有相对应的意图，那就返回兜底回复
            // TODO: 2019/12/15 后期想在这里加入知识库的模块，往知识库里面找不到合适的才返回兜底的回复
            if (response.message.textMessage != null) {
                resp.setTextMessage(response.message.textMessage);
            } else {
//                List<String> res = answerService.getCommendFromDatabase(msg);
//                logger.info("res->"+res);
//                if(res==null){
//                    resp.setTextMessage("我不懂你的意思");
//                }else {
//                    int len = res.size();
//                    StringBuffer stringBuffer = new StringBuffer(res.get(0));
//                    for(int i=1;i<len;i++){
//                        if(i!=len){
//                            stringBuffer.append("***");
//                        }
//                        stringBuffer.append(res.get(i));
//                    }
//                }
                resp.setTextMessage("我不懂你的意思");
            }
            logger.info("resp->" + resp);
            return resp;
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return null;
    }


    @Override
    public void refSysDict() {
        TTransport transport;
        try {
            transport = new TFramedTransport(new TSocket(CLAUSE_IP, CLAUSE_PORT));

            TProtocol protocol = new TBinaryProtocol(transport);

            Serving.Client client = new Serving.Client(protocol);
            transport.open(); // 建立连接
            // 引用系统的地点
            Data request = new Data();
            Dict sysdict = new Dict();
            sysdict.name = "@LOC";
            request.sysdict = sysdict;
            request.chatbotID = chatbotID;
            Data response = client.refSysDict(request);
            logger.info("refSysDict >> \n{}", response.toString());
            // 引用系统的时间
            request = new Data();
            sysdict = new Dict();
            sysdict.name = "@TIME";
            request.sysdict = sysdict;
            request.chatbotID = chatbotID;
            response = client.refSysDict(request);
            logger.info("refSysDict >> \n{}", response.toString());
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    @Override
    public void createDict(String dictName) {
        TTransport transport;
        try {
            transport = new TFramedTransport(new TSocket(CLAUSE_IP, CLAUSE_PORT));

            TProtocol protocol = new TBinaryProtocol(transport);

            Serving.Client client = new Serving.Client(protocol);
            transport.open(); // 建立连接
            Data response;
            Data request = new Data();
            Dict customdict = new Dict();
            customdict.name = dictName;
            customdict.chatbotID = chatbotID;
            request.customdict = customdict;
            response = client.postCustomDict(request);
            logger.info("postCustomDict >> \n{}", response.toString());
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    @Override
    public void addDictWort(String dictName, String words) {
//        TTransport transport;
//        try {
//            transport = new TFramedTransport(new TSocket(CLAUSE_IP, CLAUSE_PORT));
//
//            TProtocol protocol = new TBinaryProtocol(transport);
//
//            Serving.Client client = new Serving.Client(protocol);
//            transport.open(); // 建立连接
//            // 在自定义词典中添加词条
//            Data request = new Data();
//            DictWord word = new DictWord();
//            word.word = "西红柿";
//            word.synonyms = "狼桃;柿子;番茄";
//            request.customdict = customdict;
//            request.dictword = word;
//            request.chatbotID = chatbotID;
//            response = client.putDictWord(request);
//            logger.info("putDictWord >> \n{}", response.toString());
//        }catch (Exception e){
//            logger.error(e.getMessage());
//        }
    }

    @Override
    public void createIntent(String chatBotId, String intentName) {
        TTransport transport;
        try {
            transport = new TFramedTransport(new TSocket(CLAUSE_IP, CLAUSE_PORT));

            TProtocol protocol = new TBinaryProtocol(transport);

            Serving.Client client = new Serving.Client(protocol);
            transport.open(); // 建立连接
            Data request = new Data();
            final Intent intent = new Intent();
            intent.chatbotID = chatbotID;
            intent.name = intentName;
            request.intent = intent;
            Data response = client.postIntent(request);
            logger.info("postIntent >> \n{}", response.toString());
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    @Override
    public void createIntentSlot(String slotName, String dictName, Intent intent) {
        TTransport transport;
        try {
            transport = new TFramedTransport(new TSocket(CLAUSE_IP, CLAUSE_PORT));

            TProtocol protocol = new TBinaryProtocol(transport);

            Serving.Client client = new Serving.Client(protocol);
            transport.open(); // 建立连接
            Data request = new Data();
            IntentSlot slot = new IntentSlot();
            slot.name = slotName;
            slot.requires = true;
            slot.setRequires(true);
            slot.question = "您需要什么配菜";
            Dict slotDict1 = new Dict();
            slotDict1.chatbotID = chatbotID;
            slotDict1.name = dictName;
            request.intent = intent;
            request.slot = slot;
            request.customdict = slotDict1;
            Data response = client.postSlot(request);
            logger.info("postSlot >> \n{}", response.toString());
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    @Override
    public void addIntentUtter(String utterName, Intent intent) {
        TTransport transport;
        try {
            transport = new TFramedTransport(new TSocket(CLAUSE_IP, CLAUSE_PORT));

            TProtocol protocol = new TBinaryProtocol(transport);

            Serving.Client client = new Serving.Client(protocol);
            transport.open(); // 建立连接
            Data request = new Data();
            IntentUtter utter = new IntentUtter();
            utter.utterance = "我想点外卖";
            request.intent = intent;
            request.utter = utter;
            Data response = client.postUtter(request);
            logger.info("postUtter >> \n{}", response.toString());
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    @Override
    public void trainBot(String chatBotId) {
        TTransport transport;
        try {
            transport = new TFramedTransport(new TSocket(CLAUSE_IP, CLAUSE_PORT));

            TProtocol protocol = new TBinaryProtocol(transport);

            Serving.Client client = new Serving.Client(protocol);
            transport.open(); // 建立连接
            Data request = new Data();
            request.chatbotID = chatbotID;
            Data response = client.train(request);
            logger.info("train >> \n{}", response.toString());
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
}
