package com.bdq.android.ui.chat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bdq.android.R;
import com.bdq.android.data.model.Message;

import java.util.List;

/**
 * 消息列表 Adapter
 *
 * 多类�?ViewHolder：用户消息和 AI 消息布局不一�? * 面试考点：getItemViewType + 多类�?ViewHolder
 */
public class MessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_USER = 0;  // 用户消息
    private static final int TYPE_AI = 1;    // AI 消息

    private List<Message> messageList;

    public MessageAdapter(List<Message> messageList) {
        this.messageList = messageList;
    }

    public void setData(List<Message> newData) {
        this.messageList = newData;
        notifyDataSetChanged();
    }

    /**
     * 返回指定位置�?item 类型
     * 面试考点：多类型列表的实现方�?     */
    @Override
    public int getItemViewType(int position) {
        Message msg = messageList.get(position);
        return msg.isUser() ? TYPE_USER : TYPE_AI;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_USER) {
            // 用户消息布局
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_message_user, parent, false);
            return new UserMessageViewHolder(view);
        } else {
            // AI 消息布局
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_message_ai, parent, false);
            return new AiMessageViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Message msg = messageList.get(position);

        if (holder instanceof UserMessageViewHolder) {
            // 用户消息
            ((UserMessageViewHolder) holder).tvContent.setText(msg.getContent());
        } else if (holder instanceof AiMessageViewHolder) {
            // AI 消息
            ((AiMessageViewHolder) holder).tvContent.setText(msg.getContent());
        }
    }

    @Override
    public int getItemCount() {
        return messageList == null ? 0 : messageList.size();
    }

    // ---------- ViewHolder ----------

    /**
     * 用户消息 ViewHolder
     */
    static class UserMessageViewHolder extends RecyclerView.ViewHolder {
        TextView tvContent;

        UserMessageViewHolder(@NonNull View itemView) {
            super(itemView);
            tvContent = itemView.findViewById(R.id.tvContent);
        }
    }

    /**
     * AI 消息 ViewHolder
     */
    static class AiMessageViewHolder extends RecyclerView.ViewHolder {
        TextView tvContent;

        AiMessageViewHolder(@NonNull View itemView) {
            super(itemView);
            tvContent = itemView.findViewById(R.id.tvContent);
        }
    }
}
