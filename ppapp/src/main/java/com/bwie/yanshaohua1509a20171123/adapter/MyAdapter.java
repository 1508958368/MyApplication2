package com.bwie.yanshaohua1509a20171123.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwie.yanshaohua1509a20171123.R;
import com.bwie.yanshaohua1509a20171123.bean.GoodsBean;
import com.bwie.yanshaohua1509a20171123.eventbus.CheckBoxEventBus;
import com.bwie.yanshaohua1509a20171123.eventbus.SumEventBus;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Created by 闫少华 on 2017/11/23.
 * 购物车适配器 实现二级列表
 */

public class MyAdapter extends BaseExpandableListAdapter {

    private List<GoodsBean.DataBean> list;
    private Context context;


    public MyAdapter(Context context, List<GoodsBean.DataBean> data) {
        this.context = context;
        this.list = data;
    }

    @Override
    public int getGroupCount() {
        return list.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return list.get(i).getList().size();
    }

    @Override
    public Object getGroup(int i) {
        return list.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return list.get(i).getList().get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(final int position, boolean b, View view, ViewGroup viewGroup) {
        final GroupViewHolder holder;
        if (view == null){
            view = View.inflate(context, R.layout.group_item,null);
            holder = new GroupViewHolder();
            holder.group_check = view.findViewById(R.id.group_check);
            holder.group_title = view.findViewById(R.id.sellerName);
            view.setTag(holder);
        }else{
            holder = (GroupViewHolder) view.getTag();
        }
        final GoodsBean.DataBean dataBean = list.get(position);
        holder.group_check.setChecked(dataBean.isGroupChecked());
        holder.group_title.setText(dataBean.getSellerName());
        holder.group_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataBean.setGroupChecked(holder.group_check.isChecked());
                changeChild(position,holder.group_check.isChecked());
                changeAc(isGroupCheckedAll());
                changeSum();
                notifyDataSetChanged();
            }
        });
        return view;
    }

    @Override
    public View getChildView(final int groupPosition, int childPosition, boolean b, View view, ViewGroup viewGroup) {
        final ChildViewHolder holder;
        if (view == null){
            view = View.inflate(context, R.layout.child_item,null);
            holder = new ChildViewHolder();
            holder.child_check = view.findViewById(R.id.child_check);
            holder.child_title = view.findViewById(R.id.child_title);
            holder.child_price = view.findViewById(R.id.child_price);
            holder.child_img = view.findViewById(R.id.child_img);
            view.setTag(holder);
        }else{
            holder = (ChildViewHolder) view.getTag();
        }
        final GoodsBean.DataBean.ListBean listBean = list.get(groupPosition).getList().get(childPosition);
        holder.child_price.setText(listBean.getBargainPrice()+"");
        holder.child_check.setChecked(listBean.ischildChecked());
        holder.child_title.setText(listBean.getTitle());
        String images = listBean.getImages();
        String[] split = images.split("\\|");
        ImageLoader.getInstance().displayImage(split[0],holder.child_img);
        holder.child_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listBean.setIschildChecked(holder.child_check.isChecked());
                changeGroup(groupPosition,isChildCheckedAll(groupPosition));
                changeAc(isGroupCheckedAll());
                changeSum();
                notifyDataSetChanged();
            }
        });
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
    class GroupViewHolder{
        TextView group_title;
        CheckBox group_check;
    }
    class ChildViewHolder{
        CheckBox child_check;
        ImageView child_img;
        TextView child_title;
        TextView child_price;
    }
    //改变一级
    public void changeGroup(int groupPosition,boolean flag){
        list.get(groupPosition).setGroupChecked(flag);
    }
    //选中所有er级
    public void changeChild(int groupPostiton,boolean flag){
        List<GoodsBean.DataBean.ListBean> childlist = this.list.get(groupPostiton).getList();
        for (int i = 0; i < childlist.size(); i++) {
            childlist.get(i).setIschildChecked(flag);
        }
    }
    //判断一级列表是否选中
    public boolean isGroupCheckedAll(){
        for (int i = 0; i <list.size() ; i++) {
            if (!list.get(i).isGroupChecked()){
                return false;
            }
        }

        return true;
    }
    //判断一级列表是否选中
    public boolean isChildCheckedAll(int groupPosition){
        List<GoodsBean.DataBean.ListBean> childlist = this.list.get(groupPosition).getList();
        for (int i = 0; i <childlist.size() ; i++) {
            if (!childlist.get(i).ischildChecked()){
                return false;
            }
        }

        return true;
    }
    //改变主页面总价
    public void changeSum(){
        int sum = 0;
        for (int i = 0; i <list.size() ; i++) {
            GoodsBean.DataBean dataBean = list.get(i);
            List<GoodsBean.DataBean.ListBean> list = dataBean.getList();
            for (int j = 0; j < list.size(); j++) {
                if (list.get(j).ischildChecked()){
                    sum += list.get(j).getBargainPrice();
                }
            }
        }
        SumEventBus sumEventBus = new SumEventBus();
        sumEventBus.setSum(sum);
        EventBus.getDefault().post(sumEventBus);
    }
    //改变Activity的全选按钮
    public void changeAc(boolean flag){
        CheckBoxEventBus checkBoxEventBus = new CheckBoxEventBus();
        checkBoxEventBus.setIscheckedAll(flag);
        EventBus.getDefault().post(checkBoxEventBus);
    }
    //改变所有按钮
    public void changeAll(boolean flag){
        Log.e("TAG", flag+"" );
        for (int i = 0; i < list.size(); i++) {
            changeGroup(i,flag);
            changeChild(i,flag);
        }
        changeSum();
        notifyDataSetChanged();
    }

}
