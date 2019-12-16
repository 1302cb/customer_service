package com.cvte.customer_service.cuse.controller;

import com.cvte.customer_service.cuse.clause.ChatMessage;
import com.cvte.customer_service.cuse.entity.ResultData;
import com.cvte.customer_service.cuse.service.BotService;
import com.cvte.customer_service.cuse.vo.ChatMessageVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController("/bot")
public class BotChatController {

    private static Logger logger = LoggerFactory.getLogger(BotChatController.class);

    @Autowired
    private BotService botService;

    @PostMapping("/chat")
    public ResultData bootchat(String msg) {
        logger.info("message->" + msg);
        if (msg == null || "".equals(msg)) {
            return new ResultData("前端传值为空", ResultData.FAIL);
        }
        ChatMessage message = botService.chat(msg);
        logger.info("controller message->" + message);
        ChatMessageVO res = ChatMessageVO.fromChatMessage(message);
        return new ResultData(res, "请求成功", ResultData.SUCCESS);
    }

}
