package com.bdq.android.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bdq.android.R;
import com.bdq.android.data.model.Conversation;
import com.bdq.android.ui.chat.ChatActivity;
import com.bdq.android.viewmodel.MainViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * 会话列表页（主页面）
 *
 * 对应 MVVM 中的 View 层：
 * - 只负责显示数据和响应点击
 * - 不持有数据，数据�?ViewModel 观察得来
 * - 用户操作只转发给 ViewModel
 */
public class MainActivity extends AppCompatActivity {

    private RecyclerView rvConversation;
    private ConversationAdapter adapter;
    private TextView tvNewChat;
    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 初始化控�?        rvConversation = findViewById(R.id.rvConversation);
        tvNewChat = findViewById(R.id.tvNewChat);
        // 1. 获取 ViewModel（通过 ViewModelProvider，不能直�?new�?        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        // 2. 设置 RecyclerView
        setupRecyclerView();
        // 3. 观察 ViewModel 中的数据（数据驱�?UI�?        observeViewModel();
        // 4. 加载数据
        viewModel.loadConversations();
        // 5. 新建对话按钮点击 �?只告�?ViewModel，不自己处理
        tvNewChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.createNewConversation();
            }
        });
    }

    /**
     * 设置 RecyclerView
     */
    private void setupRecyclerView() {
        rvConversation.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ConversationAdapter(new ArrayList<Conversation>());

        adapter.setOnItemClickListener(new ConversationAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Conversation conversation) {
                // 跳转到对话详情页
                Intent intent = new Intent(MainActivity.this, ChatActivity.class);
                intent.putExtra(ChatActivity.EXTRA_CONVERSATION_ID, conversation.getId());
                intent.putExtra(ChatActivity.EXTRA_TITLE, conversation.getTitle());
                startActivity(intent);
            }
        });

        rvConversation.setAdapter(adapter);
    }

    /**
     * 观察 ViewModel 中的 LiveData
     *
     * 核心思想：数据变了，UI 自动更新
     * Activity 不关心数据什么时候变、怎么变，只关�?数据来了我怎么显示"
     */
    private void observeViewModel() {
        viewModel.getConversations().observe(this, new Observer<List<Conversation>>() {
            @Override
            public void onChanged(List<Conversation> conversations) {
                // 数据变了，自动刷新列�?                adapter.setData(conversations);
            }
        });
    }
}
