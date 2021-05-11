package com.luna.rabbitmq.controller;

import com.luna.common.dto.ResultDTO;
import com.luna.common.dto.ResultDTOUtils;
import com.luna.rabbitmq.dto.RabbitMessageSendDTO;
import com.luna.rabbitmq.providerservice.RabbitProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author luna@mac
 * 2021年04月20日 13:18
 */
@RestController
public class MessageController {

    @Autowired
    private RabbitProvider rabbitProvider;

    @PostMapping("/send")
    public ResultDTO<Void> send(@RequestBody RabbitMessageSendDTO rabbitMessageSendDTO) {
        rabbitProvider.send(rabbitMessageSendDTO.getExchange(), rabbitMessageSendDTO.getRoutingKey(),
            rabbitMessageSendDTO.getMessage());
        return ResultDTOUtils.success();
    }
}
