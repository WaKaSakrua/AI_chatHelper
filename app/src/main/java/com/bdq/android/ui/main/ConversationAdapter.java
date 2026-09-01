package com.bdq.android.ui.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bdq.android.R;
import com.bdq.android.data.model.Conversation;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * 会话列表�?Adapter
 * 作用：把数据绑定�?RecyclerView 的每一项上
 *
 * 面试考点�? * 1. ViewHolder 模式：减�?findViewById 调用
 * 2. 复用机制：滑出去�?item 会被复用，滑回来时重新绑定数�? */
public class ConversationAdapter extends RecyclerView.Adapter<ConversationAdapter.ConversationViewHolder> {

    private List<Conversation> dataList;
    private OnItemClickListener listener;
    // �?SimpleDateFormat 提出来，只创建一次（性能优化�?    private final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.getDefault());

    // 点击事件回调接口
    public interface OnItemClickListener {
        void onItemClick(Conversation conversation);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    // 构造方法：传入数据列表
    public ConversationAdapter(List<Conversation> dataList) {
        this.dataList = dataList;
    }

    /**
     * 更新数据（MVVM �?LiveData 变化时调用）
     */
    public void setData(List<Conversation> newData) {
        this.dataList = newData;
        notifyDataSetChanged(); // 简单粗暴刷新，以后可以�?DiffUtil 优化
    }

    /**
     * 创建 ViewHolder
     * 作用：把 item 的布局 XML 加载�?View，包装成 ViewHolder
     * 调用时机：需要新�?item 时（比如刚开始显示、滑动出现新类型�?     */
    @NonNull
    @Override
    public ConversationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_conversation, parent, false);
        return new ConversationViewHolder(view);
    }

    /**
     * 绑定数据
     * 作用：把 position 对应的数据，设置�?ViewHolder 的控件上
     * 调用时机：item 即将显示在屏幕上�?     *
     * 面试考点：这里不能做耗时操作、不能创建新对象（会频繁 GC�?     */
    @Override
    public void onBindViewHolder(@NonNull ConversationViewHolder holder, int position) {
        Conversation item = dataList.get(position);
        // 时间格式化在这里做（因为 sdf 是外部类的成员，static ViewHolder 访问不到�?        holder.tvTime.setText(sdf.format(new Date(item.getUpdatedAt())));
        // 其他绑定和点击事件交�?ViewHolder �?bind 方法
        holder.bind(item, listener);
    }

    /**
     * 返回数据总数
     */
    @Override
    public int getItemCount() {
        return dataList == null ? 0 : dataList.size();
    }

    /**
     * ViewHolder：缓�?item 的控件引�?     * 作用：避免每次都调用 findViewById
     *
     * 面试考点：ViewHolder 为什么要�?static�?     * 答：非静态内部类默认持有外部类引用，可能导致内存泄漏�?     *    �?static 后不持有外部类引用，更安全�?     *
     * 面试考点：ViewHolder 为什么能优化性能�?     * 答：findViewById 是遍�?View 树，比较耗时�?     *    ViewHolder 把找到的控件缓存起来，复用时直接用，不用再找�?     */
    static class ConversationViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        TextView tvLastMessage;
        TextView tvTime;

        public ConversationViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvLastMessage = itemView.findViewById(R.id.tvLastMessage);
            tvTime = itemView.findViewById(R.id.tvTime);
        }

        /**
         * 绑定数据和点击事�?         * 因为 ViewHolder �?static 的，不能直接访问外部类成员，
         * 所以通过参数传进�?         */
        void bind(Conversation item, OnItemClickListener listener) {
            tvTitle.setText(item.getTitle());
            tvLastMessage.setText(item.getLastMessage());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null && getAdapterPosition() != RecyclerView.NO_POSITION) {
                        listener.onItemClick(item);
                    }
                }
            });
        }
    }
}
