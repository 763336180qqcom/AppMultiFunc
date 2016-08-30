package func.types.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import func.types.R;

/**
 * author-ZKC
 * create on 2016-08-19-17-01.
 */
public class HomeListAdapter extends RecyclerView.Adapter<HomeListAdapter.MyViewHolder> {
    private List<String> mData = new ArrayList<>();
    private Context mContext;
    private  CustomItemClickListener  mListener;
    public interface  CustomItemClickListener{
        void _click(View v,int position);
        void _longClick(View v,int position);
    }
    public void setCustomItemClickListener(CustomItemClickListener listener){
        mListener=listener;
    }

    public HomeListAdapter(Context context, List<String> lists) {
        mData = lists;
        mContext = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                mContext).inflate(R.layout.item_text_list, parent,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        ViewGroup.LayoutParams lp = holder.tv.getLayoutParams();
        lp.height = (int) (100+Math.random() * 400);
        holder.tv.setLayoutParams(lp);
        holder.tv.setText(mData.get(position));
        if(mListener!=null) {
            holder.tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener._click(view, position);
                }
            });
            holder.tv.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    mListener._longClick(view, position);
                    return false;
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv;

        public MyViewHolder(View view) {
            super(view);
            tv = (TextView) view.findViewById(R.id.id_func);
        }
    }
}
