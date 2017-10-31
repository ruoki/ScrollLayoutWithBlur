package com.ruoki.scrolllayoutwithblur.adapter;

import android.content.Context;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.ruoki.scrolllayoutwithblur.R;
import com.ruoki.scrolllayoutwithblur.bean.MainPageMenu;
import com.ruoki.scrolllayoutwithblur.utils.UIUtil;

import java.util.List;

/**
 * Created by 01 on 2017/3/17.
 */
public class HomeGridMoreAdapter extends BaseAdapter {
    private final Context mContext;
    List<MainPageMenu> datas;
    private int hidePosition = AdapterView.INVALID_POSITION;

    public HomeGridMoreAdapter(List<MainPageMenu> datas, Context context) {
        this.datas = datas;
        this.mContext = context;
    }

    public void updateGridView(List<MainPageMenu> datas){
        this.datas = datas;
        notifyDataSetChanged();
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return datas.get(position);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return datas.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MainPageMenu mMainPageModel = datas.get(position);
        final ViewHolder holder;
        if(convertView == null){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_grid_more_item, parent, false);
            holder.text = convertView.findViewById(R.id.form_main_function_item_text);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        if (position != hidePosition) {
            convertView.setVisibility(View.VISIBLE);
            Rect rect = new Rect(0,0,35,35);
            UIUtil.setDrawableWithView(mContext,mMainPageModel.icon,rect, holder.text,UIUtil.Derection.TOP);
            holder.text.setText(mMainPageModel.name);
        } else {
            convertView.setVisibility(View.GONE);
        }

        return convertView;
    }

    public void hideView(int pos) {
        hidePosition = pos;
        notifyDataSetChanged();
    }

    public void showHideView() {
        hidePosition = AdapterView.INVALID_POSITION;
        notifyDataSetChanged();
    }

    //更新拖动时的gridView
    public void swapView(int draggedPos, int destPos) {
        if (draggedPos > datas.size() - 1) {
            return;
        }
        //从前向后拖动，其他item依次前移
        if (draggedPos < destPos) {
            datas.add(destPos + 1, datas.get(draggedPos));
            datas.remove(draggedPos);
        }
        //从后向前拖动，其他item依次后移
        else if (draggedPos > destPos) {
            datas.add(destPos, datas.get(draggedPos));
            datas.remove(draggedPos + 1);
        }
        hidePosition = destPos;
        notifyDataSetChanged();
    }

    public List<MainPageMenu> getDatas() {
        return datas;
    }

    private class ViewHolder {
        TextView text;
    }
}
