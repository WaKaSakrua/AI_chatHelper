package com.bdq.android.ui.chat;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bdq.android.R;
import com.bdq.android.data.model.Message;
import com.bdq.android.viewmodel.ChatViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * 对话详情�? */
public class ChatActivity extends AppCompatActivity {

    public static final String EXTRA_CONVERSATION_ID = "conversation_id";
    public static final String EXTRA_TITLE = "title";

    private RecyclerView rvMessages;
    private MessageAdapter adapter;
    private EditText etInput;
    private TextView tvSend;
    private TextView tvVoice;
    private TextView tvBack;
    private TextView tvTitle;
    private ChatViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        // 获取传过来的会话信息
        String conversationId = getIntent().getStringExtra(EXTRA_CONVERSATION_ID);
        String title = getIntent().getStringExtra(EXTRA_TITLE);

        // 初始化控�?        rvMessages = findViewById(R.id.rvMessages);
        etInput = findViewById(R.id.etInput);
        tvSend = findViewById(R.id.tvSend);
        tvVoice = findViewById(R.id.tvVoice);
        tvBack = findViewById(R.id.tvBack);
        tvTitle = findViewById(R.id.tvTitle);

        // 设置标题
        if (!TextUtils.isEmpty(title)) {
            tvTitle.setText(title);
        }

        // 初始�?ViewModel
        viewModel = new ViewModelProvider(this).get(ChatViewModel.class);
        viewModel.init(conversationId);

        // 设置列表
        setupRecyclerView();

        // 观察消息数据
        observeViewModel();

        // 返回按钮
        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // 发送按�?        tvSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });

        // 语音按钮（占位，后面实现�?        tvVoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ChatActivity.this, "语音输入功能开发中...", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupRecyclerView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        // 从底部开始显�?        layoutManager.setStackFromEnd(true);
        rvMessages.setLayoutManager(layoutManager);

        adapter = new MessageAdapter(new ArrayList<Message>());
        rvMessages.setAdapter(adapter);
    }

    private void observeViewModel() {
        viewModel.getMessages().observe(this, new Observer<List<Message>>() {
            @Override
            public void onChanged(List<Message> messages) {
                adapter.setData(messages);
                // 自动滚动到底�?                if (messages != null && messages.size() > 0) {
                    rvMessages.scrollToPosition(messages.size() - 1);
                }
            }
        });
    }

    private void sendMessage() {
        String content = etInput.getText().toString().toString().trim();
        if (TextUtils.isEmpty(content)) {
            return;
        }
        viewModel.sendMessage(content);
        etInput.setText("");
    }
}
