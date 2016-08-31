package func.types.json;

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
 * create on 2016-08-31-10-35.
 */
public class JsonListAdapter extends RecyclerView.Adapter<JsonListAdapter.JHolder> {
    private Context mContext;
    private List<Tg> mTgs = new ArrayList<>();

    public JsonListAdapter(Context context, List<Tg> tgs) {
        mContext = context;
        mTgs = tgs;
    }

    @Override
    public JHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        JHolder jHolder = new JHolder(LayoutInflater.from(mContext).inflate(R.layout.json_item, parent, false));
        return jHolder;
    }

    @Override
    public void onBindViewHolder(JHolder holder, int position) {
        if (mTgs.size() > 0) {
            Tg tg = mTgs.get(position);
            holder.name.setText(tg.getName());
            holder.description.setText(tg.getDescription());
        }
    }


    @Override
    public int getItemCount() {
        return mTgs.size();
    }

    class JHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView description;


        public JHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.tv_json_name);
            description = (TextView) itemView.findViewById(R.id.tv_json_description);
        }
    }
}
