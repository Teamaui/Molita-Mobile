package com.molita.molita.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.molita.molita.R;
import com.molita.molita.model.data.Message;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Message> messages;
    private static final int VIEW_TYPE_SENT = 1;
    private static final int VIEW_TYPE_RECEIVED = 2;

    public ChatAdapter(List<Message> messages) {
        this.messages = messages;
    }

    public void updateMessages(List<Message> newMessages) {
        this.messages = newMessages;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        return messages.get(position).isSent() ? VIEW_TYPE_SENT : VIEW_TYPE_RECEIVED;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_SENT) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat_sent, parent, false);
            return new SentMessageViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat_received, parent, false);
            return new ReceivedMessageViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Message message = messages.get(position);
        if (holder instanceof SentMessageViewHolder) {
            ((SentMessageViewHolder) holder).bind(message);
        } else if (holder instanceof ReceivedMessageViewHolder) {
            ((ReceivedMessageViewHolder) holder).bind(message);
        }
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    static class SentMessageViewHolder extends RecyclerView.ViewHolder {
        private final TextView textViewMessageSent, textViewTimeSent;

        SentMessageViewHolder(View itemView) {
            super(itemView);
            textViewMessageSent = itemView.findViewById(R.id.textViewMessageSent);
            textViewTimeSent = itemView.findViewById(R.id.textViewTimeSent);
        }

        void bind(Message message) {
            textViewMessageSent.setText(message.getMessageText());
            textViewTimeSent.setText(message.getTime());
        }
    }

    static class ReceivedMessageViewHolder extends RecyclerView.ViewHolder {
        private final TextView textViewMessageReceived, textViewTimeReceived;

        ReceivedMessageViewHolder(View itemView) {
            super(itemView);
            textViewMessageReceived = itemView.findViewById(R.id.textViewMessageReceived);
            textViewTimeReceived = itemView.findViewById(R.id.textViewTimeReceived);
        }

        void bind(Message message) {
            textViewMessageReceived.setText(message.getMessageText());
            textViewTimeReceived.setText(message.getTime());
        }
    }
}
