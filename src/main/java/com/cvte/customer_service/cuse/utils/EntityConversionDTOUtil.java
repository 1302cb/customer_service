package com.cvte.customer_service.cuse.utils;

import com.cvte.customer_service.cuse.dto.CustomerServiceAnswerDTO;
import com.cvte.customer_service.cuse.entity.CustomerServiceAnswer;

import java.util.ArrayList;
import java.util.List;

public class EntityConversionDTOUtil {
    /*
    答案entity类转化成dto
     */
    public static CustomerServiceAnswerDTO conversionToAnswerDTO(CustomerServiceAnswer answer) {
        CustomerServiceAnswerDTO res = new CustomerServiceAnswerDTO();
        res.setUid(answer.getUid());
        res.setAnswer(answer.getAnswer());
        res.setQuestion(answer.getQuestion());
        return res;
    }

    /*
    答案entity列表转化成dto列表
     */
    public static List<CustomerServiceAnswerDTO> conversionToAnswerDTOList(List<CustomerServiceAnswer> answers) {
        if (answers == null) {
            return null;
        }
        List<CustomerServiceAnswerDTO> res = new ArrayList<>();
        for (CustomerServiceAnswer answer : answers) {
            res.add(conversionToAnswerDTO(answer));
        }
        return res;
    }
}
