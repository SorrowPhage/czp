package com.sorrowphage.czp.controller;


import com.sorrowphage.czp.entity.CzpMessage;
import com.sorrowphage.czp.entity.ResultMessage;
import com.sorrowphage.czp.service.CzpMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author SorrowPhage
 * @since 2023-11-22
 */
@RestController
@RequestMapping("/czp-message")
@RequiredArgsConstructor
public class CzpMessageController {

    //消息
    private final CzpMessageService czpMessageService;

    /**
     * 获取id用户的聊天列表
     *
     * @param id 用户id
     * @return 数据
     */
    @GetMapping("/chat-list")
    public ResultMessage getChatList(@RequestParam("id") String id) {
        return czpMessageService.getChatList(id);
    }

    /**
     * 获取u1用户与u2用户的聊天信息列表
     *
     * @param u1 用户1 查询者
     * @param u2 用户2
     * @return 数据
     */
    @GetMapping("/message-list")
    public ResultMessage getMessageList(@RequestParam("u1") String u1, @RequestParam("u2") String u2) {
        return czpMessageService.getMessageList(u1, u2);
    }

    /**
     * 获取群聊信息
     * @param toId
     * @return
     */
    @GetMapping("/gh-list")
    public ResultMessage getGangHsienMessageList(@RequestParam("toId") String toId) {
        return czpMessageService.getGangHsienMessageList(toId);
    }


    /**
     * 发送消息
     * @param message
     * @return
     */
    @PostMapping("/send")
    public ResultMessage sendMessage(@RequestBody CzpMessage message) {
        return czpMessageService.sendMessage(message);
    }

    /**
     * 发送消息
     * @param message
     * @return
     */
    @PostMapping("/gh-send")
    public ResultMessage sendGangHsienMessage(@RequestBody CzpMessage message) {
        return czpMessageService.sendGangHsienMessage(message);
    }
}
