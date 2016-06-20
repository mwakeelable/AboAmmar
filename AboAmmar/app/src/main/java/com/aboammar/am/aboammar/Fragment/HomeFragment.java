package com.aboammar.am.aboammar.Fragment;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.aboammar.am.aboammar.R;
import com.aboammar.am.aboammar.UI.MainActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mahmoud on 22-May-16.
 */
public class HomeFragment extends Fragment {
    MainActivity activity;
    RecyclerView recyclerView;
    GridLayoutManager gridLayoutManager;

    public HomeFragment() {

    }

    class ItemObject {
        private String name;
        private int photo;

        public ItemObject(String name, int photo) {
            this.name = name;
            this.photo = photo;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getPhoto() {
            return photo;
        }

        public void setPhoto(int photo) {
            this.photo = photo;
        }
    }

    private List<ItemObject> getAllItemList() {
        List<ItemObject> allItems = new ArrayList<ItemObject>();
        allItems.add(new ItemObject(" ", R.drawable.cover));
        allItems.add(new ItemObject(activity.getString(R.string.category1), R.drawable.album1));
        allItems.add(new ItemObject(activity.getString(R.string.category2), R.drawable.album2));
        allItems.add(new ItemObject(activity.getString(R.string.category3), R.drawable.album3));
        allItems.add(new ItemObject(activity.getString(R.string.category4), R.drawable.album4));
        allItems.add(new ItemObject(activity.getString(R.string.category5), R.drawable.album5));
        allItems.add(new ItemObject(activity.getString(R.string.category6), R.drawable.orange));
        return allItems;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        activity = (MainActivity) getActivity();
        return inflater.inflate(R.layout.home_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        List<ItemObject> rowListItem = getAllItemList();
        recyclerView = (RecyclerView) view.findViewById(R.id.categoryRV);
        gridLayoutManager = new GridLayoutManager(activity, 2);
        //make a header for grid
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup(){

            @Override
            public int getSpanSize(int position) {
                return position == 0 ? 2 : 1;
            }
        });
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);
        HomeAdapter adapter = new HomeAdapter(activity, rowListItem);
        recyclerView.setAdapter(adapter);
        recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeDetailsFragment homeDetailsFragment= new HomeDetailsFragment();
                FragmentTransaction ft  = getFragmentManager().beginTransaction();
                ft.replace(R.id.containerView, homeDetailsFragment);
                ft.addToBackStack(null);
                ft.commit();
            }
        });
    }

    class HomeAdapter extends RecyclerView.Adapter<ViewHolder> {
        private List<ItemObject> itemList;
        private Context context;

        public HomeAdapter(Context context, List<ItemObject> itemList) {
            this.itemList = itemList;
            this.context = context;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_item, null);
            ViewHolder rcv = new ViewHolder(layoutView);
            return rcv;
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            Typeface typeface = Typeface.DEFAULT.createFromAsset(activity.getAssets(),"fonts/mobily.ttf");
            holder.mNameTextView.setTypeface(typeface);
            holder.mNameTextView.setTextSize(20);
            holder.mNameTextView.setText(itemList.get(position).getName());
            holder.mImageView.setImageResource(itemList.get(position).getPhoto());
        }

        @Override
        public int getItemCount() {
            return this.itemList.size();
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageView;
        private TextView mNameTextView;

        private ViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.home_image);
            mNameTextView = (TextView) itemView.findViewById(R.id.home_name);
        }
    }
}
