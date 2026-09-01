package com.bdq.android.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.bdq.android.data.model.Message;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 对话详情页的 ViewModel
 */
public class ChatViewModel extends ViewModel {

    private final MutableLiveData<List<Message>> _messages = new MutableLiveData<>();
    public LiveData<List<Message>> getMessages() {
        return _messages;
    }

    private String conversationId;
    private List<Message> messageList = new ArrayList<>();

    /**
     * 初始化，传入会话 ID
     */
    public void init(String conversationId) {
        this.conversationId = conversationId;
        // 加载历史消息（现在是假数据，以后从数据库读）
        loadMockMessages();
    }

    /**
     * 加载假的历史消息
     */
    private void loadMockMessages() {
        messageList.add(new Message(
                UUID.randomUUID().toString(),
                conversationId,
                "你好！我�?AI 助手，有什么可以帮你的�?,
                false,
                System.currentTimeMillis() - 60000
        ));
        _messages.setValue(messageList);
    }

    /**
     * 发送消�?     */
    public void sendMessage(String content) {
        if (content == null || content.trim().isEmpty()) {
            return;
        }

        // 1. 添加用户消息
        Message userMsg = new Message(
                UUID.randomUUID().toString(),
                conversationId,
                content.trim(),
                true,
                System.currentTimeMillis()
        );
        messageList.add(userMsg);
        _messages.setValue(messageList);

        // 2. 模拟 AI 回复（延�?1 秒，以后换成真实 API 调用�?        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Message aiMsg = new Message(
                        UUID.randomUUID().toString(),
                        conversationId,
                        "收到你的消息：\"" + content.trim() + "\"\n（这是模拟回复，以后接入真实 API�?,
                        false,
                        System.currentTimeMillis()
                );
                messageList.add(aiMsg);
                _messages.postValue(messageList);
            }
        }, 1000);
    }
}
