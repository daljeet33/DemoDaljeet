package com.example.dimagibot.ui.main.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.dimagibot.R;
import com.example.dimagibot.config.Constants;
import com.example.dimagibot.data.model.ChatMessageObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChatViewAdapter extends RecyclerView.Adapter<ChatViewAdapter.ItemViewHolder> {


    private List<ChatMessageObject> chatList;

    public ChatViewAdapter(List<ChatMessageObject> chatList) {
        this.chatList = chatList;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.chat_list_item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        ChatMessageObject chatMessageObject= chatList.get(position);
        if(chatMessageObject.getType().equalsIgnoreCase(Constants.SENT)){
            holder.userChatText.setVisibility(View.VISIBLE);
            holder.userChatText.setText(chatMessageObject.getChatMessage());
            holder.botChatText.setVisibility(View.GONE);
        }else{
            holder.userChatText.setVisibility(View.GONE);
            holder.botChatText.setText(chatMessageObject.getChatMessage());
            holder.botChatText.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return chatList.size();
    }

    public void updateList(List<ChatMessageObject> chatList) {
        this.chatList=chatList;
        notifyItemInserted(chatList.size()-1);
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.bot_chat_text)
        TextView botChatText;
        @BindView(R.id.user_chat_text)
        TextView userChatText;
        ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
