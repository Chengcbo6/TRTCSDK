package com.tencent.liteav.trtcvideocalldemo.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tencent.liteav.trtcvideocalldemo.R;
import com.tencent.liteav.trtcvideocalldemo.ui.TRTCVideoCallSelectContactActivity;

import java.util.List;

public class SearchContactAdapter extends RecyclerView.Adapter<SearchContactAdapter.ViewHolder> {
    private static final String TAG = "SearchContactAdapter";

    private Context mContext;
    private List<TRTCVideoCallSelectContactActivity.ContactsEntity> mList;
    private TRTCVideoCallSelectContactActivity.OnItemClickListener   onItemClickListener;

    public SearchContactAdapter(Context context, List<TRTCVideoCallSelectContactActivity.ContactsEntity> list,
                                TRTCVideoCallSelectContactActivity.OnItemClickListener onItemClickListener) {
        this.mContext = context;
        this.mList = list;
        this.onItemClickListener = onItemClickListener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private Button mContactsButton;
        private ImageView mAvatarImage;
        private TextView mUserNameText;

        public ViewHolder(View itemView) {
            super(itemView);
            mContactsButton = (Button) itemView.findViewById(R.id.cb_contact);
            mAvatarImage = (ImageView) itemView.findViewById(R.id.img_avatar);
            mUserNameText = (TextView) itemView.findViewById(R.id.tv_user_name);
        }

        public void bind(final TRTCVideoCallSelectContactActivity.ContactsEntity model,
                         final TRTCVideoCallSelectContactActivity.OnItemClickListener listener) {
            Picasso.get().load(model.userModel.userAvatar).into(mAvatarImage);
            mUserNameText.setText(model.userModel.userName);
            if (model.isSelected) {
                mContactsButton.setActivated(true);
            } else {
                mContactsButton.setActivated(false);
            }
            mContactsButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(getLayoutPosition());
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(getLayoutPosition());
                }
            });
        }
    }

    @Override
    public SearchContactAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.trtcvideocall_recycle_item_select_contact, parent, false);
        return new SearchContactAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchContactAdapter.ViewHolder holder, int position) {
        TRTCVideoCallSelectContactActivity.ContactsEntity item = mList.get(position);
        holder.bind(item, onItemClickListener);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
