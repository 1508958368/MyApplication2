package com.bwie.test.yuekaodemo1.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwie.test.yuekaodemo1.R;
import com.bwie.test.yuekaodemo1.bean.PriceAndCount;
import com.bwie.test.yuekaodemo1.bean.ShowCarBean;
import com.bwie.test.yuekaodemo1.view.SecondActivity;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * author:Created by 董博 on 2017/12/20.
 */

public class ElvAdapter extends BaseExpandableListAdapter {
    private List<ShowCarBean.DataBean> group;
    private List<List<ShowCarBean.DataBean.ListBean>> child;
    private Context context;
    private LayoutInflater inflater;
    private Onclick onclick;


    public interface Onclick {
        void Onclik(String pid);
    }

    public void setOnclick(Onclick onclick) {
        this.onclick = onclick;
    }
    public ElvAdapter(List<ShowCarBean.DataBean> group, List<List<ShowCarBean.DataBean.ListBean>> child, Context context) {
        this.group = group;
        this.child = child;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getGroupCount() {
        return group.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return child.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return group.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return child.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        View view;
        final GroupViewHolder holder;
        if (convertView == null) {
            view = inflater.inflate(R.layout.group_item, null);
            holder = new GroupViewHolder();
            holder.group_check = view.findViewById(R.id.group_check);
            holder.tv_seller = view.findViewById(R.id.tv_seller);
            view.setTag(holder);
        } else {
            view = convertView;
            holder = (GroupViewHolder) view.getTag();
        }
        final ShowCarBean.DataBean dataBean = group.get(groupPosition);
        holder.group_check.setChecked(dataBean.isGroupChecked());
        holder.tv_seller.setText(dataBean.getSellerName());
        holder.group_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //需要改变三个checkbox的状态值
                //1.一级列表的checkbox状态值
                dataBean.setGroupChecked(holder.group_check.isChecked());
                //2.二级列表的checkbox状态值
                setChildrenCb(groupPosition, holder.group_check.isChecked());
                //3.全选的checkbox状态值
                ((SecondActivity) context).setAllChecked(isAllGroupCbChecked());
                //计算钱和数量并显示
                setPriceAndCount();
                //刷新界面
                notifyDataSetChanged();

            }
        });
        return view;
    }

    @Override
    public View getChildView(final int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        View view;
        final ChildViewHolder holder;
        if (convertView == null) {
            view = inflater.inflate(R.layout.child_item, null);
            holder = new ChildViewHolder();
            holder.child_check = view.findViewById(R.id.child_check);
            holder.child_iv = view.findViewById(R.id.child_iv);
            holder.tv_title = view.findViewById(R.id.tv_title);
            holder.tv_price = view.findViewById(R.id.tv_price);
            holder.tvNum = view.findViewById(R.id.tvNum);
            holder.iv_add = view.findViewById(R.id.iv_add);
            holder.iv_del = view.findViewById(R.id.iv_del);
            view.setTag(holder);
        } else {
            view = convertView;
            holder = (ChildViewHolder) view.getTag();
        }
        final ShowCarBean.DataBean.ListBean bean = child.get(groupPosition).get(childPosition);
        String images = bean.getImages();
        String[] split = images.split("\\|");
        ImageLoader.getInstance().displayImage(split[0], holder.child_iv);
        holder.child_check.setChecked(bean.isChildChecked());
        holder.tv_title.setText(bean.getTitle());
        holder.tv_price.setText(bean.getPrice() + "");
        holder.tvNum.setText(bean.getCount()+"");
        holder.child_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //需要改变三个checkbox的状态值
                //1.二级列表的checkbox状态值
                bean.setChildChecked(holder.child_check.isChecked());
                //2.一级列表的checkbox状态值
                group.get(groupPosition).setGroupChecked(isAllChildCbChecked(groupPosition));
                //3.全选的checkbox状态值
                ((SecondActivity) context).setAllChecked(isAllGroupCbChecked());
                //计算钱和数量并显示
                setPriceAndCount();
                //刷新界面
                notifyDataSetChanged();
            }
        });
        holder.iv_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = bean.getCount();
                count++;
                bean.setCount(count);
                setPriceAndCount();
                notifyDataSetChanged();
            }
        });
        holder.iv_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = bean.getCount();
                if (count <= 1) {
                    count = 1;
                } else {
                    count--;
                }
                bean.setCount(count);
                setPriceAndCount();
                notifyDataSetChanged();
            }
        });

        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    class GroupViewHolder {
        CheckBox group_check;
        TextView tv_seller;
    }

    class ChildViewHolder {
        CheckBox child_check;
        ImageView child_iv;
        TextView tv_title;
        TextView tv_price;
        TextView tvNum;
        ImageView iv_del;
        ImageView iv_add;
    }

    /**
     * 设置一级列表对应的二级列表checkbox状态
     *
     * @param groupPosition
     * @param bool
     */
    private void setChildrenCb(int groupPosition, boolean bool) {
        List<ShowCarBean.DataBean.ListBean> listBeans = child.get(groupPosition);
        for (int i = 0; i < listBeans.size(); i++) {
            listBeans.get(i).setChildChecked(bool);
        }
    }

    /**
     * 判断一级列表checkbox状态
     *
     * @return
     */
    private boolean isAllGroupCbChecked() {
        if (group.size() == 0) {
            return false;
        }
        for (int i = 0; i < group.size(); i++) {
            if (!group.get(i).isGroupChecked()) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断二级列表checkbox状态
     *
     * @return
     */
    private boolean isAllChildCbChecked(int groupPosition) {
        List<ShowCarBean.DataBean.ListBean> listBeans = child.get(groupPosition);
        for (int i = 0; i < listBeans.size(); i++) {
            if (!listBeans.get(i).isChildChecked()) {
                return false;
            }
        }
        return true;
    }

    /**
     * 设置钱和数量
     */
    private void setPriceAndCount() {
        ((SecondActivity) context).setPriceAndCount(compute());
    }

    /**
     * 计算钱和数量
     */
    private PriceAndCount compute() {
        double price = 0;
        int count = 0;
        for (int i = 0; i < group.size(); i++) {
            List<ShowCarBean.DataBean.ListBean> listBeans = child.get(i);
            for (int j = 0; j < listBeans.size(); j++) {
                if (listBeans.get(j).isChildChecked()) {
                    price += listBeans.get(j).getPrice() * listBeans.get(j).getCount();
                    count += listBeans.get(j).getCount();
                }
            }
        }
        return new PriceAndCount(price, count);
    }

    /**
     * 全选或者全不选
     *
     * @param bool
     */
    public void AllOrNone(boolean bool) {
        for (int i = 0; i < group.size(); i++) {
            group.get(i).setGroupChecked(bool);
            setChildrenCb(i, bool);
        }
        setPriceAndCount();
        notifyDataSetChanged();
    }
}
