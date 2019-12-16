package com.cvte.customer_service.cuse.service;

import com.cvte.customer_service.cuse.clause.ChatMessage;
import com.cvte.customer_service.cuse.clause.Intent;
import org.springframework.stereotype.Service;

/**
 * 聊天接口
 *
 * @author chenbo
 * @Date 2019/12/15 9:21 下午
 */
@Service
public interface BotService {
    /**
     * 聊天接口
     */
    ChatMessage chat(String msg);

    /**
     * 应用系统词典
     */
    void refSysDict();

    /**
     * 创建词典
     */
    void createDict(String dictName);

    /**
     * 在创建的词典里面添加词条
     */
    void addDictWort(String dictName, String words);

    /**
     * 创建意图
     */
    void createIntent(String chatBotId, String intentName);

    /**
     * 创建意图槽位
     */
    void createIntentSlot(String slotName, String dictName, Intent intent);

    /**
     * 添加意图说法
     */
    void addIntentUtter(String utterName, Intent intent);

    /**
     * 训练机器人
     */
    void trainBot(String chatBotId);
}
