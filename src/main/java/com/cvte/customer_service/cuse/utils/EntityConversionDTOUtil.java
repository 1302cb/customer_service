package com.cvte.customer_service.cuse.utils;

import com.cvte.customer_service.cuse.dto.CustomerServiceAnswerDTO;
import com.cvte.customer_service.cuse.entity.CustomerServiceAnswer;

public class EntityConversionDTOUtil {
    public static CustomerServiceAnswerDTO conversionToAnswerDTO(CustomerServiceAnswer answer) {
        CustomerServiceAnswerDTO res = new CustomerServiceAnswerDTO();
        res.setUid(answer.getUid());
        res.setAnswer(answer.getAnswer());
        res.setQuestion(answer.getQuestion());
        return res;
    }
}
