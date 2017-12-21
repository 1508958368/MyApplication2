package com.bawei.chenkai.day14rikao.Gouwuche.view.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.chenkai.day14rikao.Gouwuche.model.bean.CartBean;
import com.bawei.chenkai.day14rikao.Gouwuche.model.bean.CountPriceBean;
import com.bawei.chenkai.day14rikao.R;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Dash on 2017/12/12.
 */
public class MyAdapter extends BaseExpandableListAdapter{
    private Handler handler;
    private Context context;
    private List<CartBean.DataBean> listGroup;
    private List<List<CartBean.DataBean.ListBean>> listChilds;
    public MyAdapter(Context context, List<CartBean.DataBean> listGroup, List<List<CartBean.DataBean.ListBean>> listChilds, Handler handler) {
        this.context = context;
        this.listGroup = listGroup;
        this.listChilds = listChilds;
        this.handler = handler;
    }

    @Override
    public int getGroupCount() {
        return listGroup.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return listChilds.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return listGroup.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return listChilds.get(groupPosition).get(childPosition);
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
        return true;
    }

    @Override
    public View getGroupView(final int groupPosition, boolean b, View view, ViewGroup viewGroup) {
        GroupHolder holder;
        if (view == null){
            view = View.inflate(context, R.layout.group_item_layout,null);
            holder = new GroupHolder();

            holder.check_group = view.findViewById(R.id.check_group);
            holder.text_group = view.findViewById(R.id.text_group);

            view.setTag(holder);

        }else {
            holder = (GroupHolder) view.getTag();
        }

        final CartBean.DataBean dataBean = listGroup.get(groupPosition);
        //赋值
        holder.check_group.setChecked(dataBean.isGroupChecked());
        holder.text_group.setText(dataBean.getSellerName());

        //设置点击事件
        holder.check_group.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //2.1改变当前一级选中的状态
                dataBean.setGroupChecked(! dataBean.isGroupChecked());
                //2.2根据当前一级的状态,改变该组里面二级列表的状态
                changeChildState(groupPosition,dataBean.isGroupChecked());
                //2.3通过判断所有的一级组是否选中,来决定是否全选选中
                changeAllState(isAllGroupChecked());
                //2.4发送价格个数量:
                sendPriceAndCount();
                //刷新适配器
                notifyDataSetChanged();
            }
        });

        return view;
    }

    /**
     * 根据所有一级列表是否选中,确定外面的全选是否选中
     * @param allGroupChecked
     */
    private void changeAllState(boolean allGroupChecked) {
        Message msg = Message.obtain();
        msg.what =1;
        msg.obj = allGroupChecked;
        handler.sendMessage(msg);

    }

    /**
     * 所有的一级列表是否选中
     * @return
     */
    private boolean isAllGroupChecked() {
        for (int i=0;i<listGroup.size();i++){

            if (! listGroup.get(i).isGroupChecked()){
                return false;
            }
        }

        return true;
    }

    /**
     * 根据当前一级列表的状态,,,改变当前组中所有二级列表的状态
     * @param groupPosition
     * @param groupChecked
     */
    private void changeChildState(int groupPosition, boolean groupChecked) {
        List<CartBean.DataBean.ListBean> listBeans = listChilds.get(groupPosition);

        for (int i=0;i<listBeans.size();i++){

            listBeans.get(i).setSelected(groupChecked? 1:0);
        }

    }

    @Override
    public View getChildView(final int groupPosition, int childPosition, boolean b, View view, ViewGroup viewGroup) {
        ChildHolder holder;
        if (view == null){
            view = View.inflate(context, R.layout.child_item_layout,null);
            holder = new ChildHolder();

            holder.text_add = view.findViewById(R.id.text_add);
            holder.text_num = view.findViewById(R.id.text_num);
            holder.text_jian = view.findViewById(R.id.text_jian);
            holder.text_title = view.findViewById(R.id.text_title);
            holder.text_price = view.findViewById(R.id.text_price);
            holder.image_good = view.findViewById(R.id.image_good);
            holder.check_child = view.findViewById(R.id.check_child);

            view.setTag(holder);

        }else {
            holder = (ChildHolder) view.getTag();
        }

       //赋值
        final CartBean.DataBean.ListBean listBean = listChilds.get(groupPosition).get(childPosition);

        holder.text_num.setText(listBean.getNum()+"");//......注意
        holder.text_price.setText("¥"+listBean.getPrice());
        holder.text_title.setText(listBean.getTitle());
        //listBean.getSelected().....0false,,,1true
        //设置checkBox选中状态
        holder.check_child.setChecked(listBean.getSelected()==0? false:true);

        /*implementation 'com.github.bumptech.glide:glide:4.4.0'
        annotationProcessor 'com.github.bumptech.glide:compiler:4.4.0'*/
        Glide.with(context).load(listBean.getImages().split("\\|")[0]).into(holder.image_good);

        //设置点击事件
        holder.check_child.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //3.1点击改变当前子条目状态:....实际是改变当前的数据,,,刷新适配器
                listBean.setSelected(listBean.getSelected() ==0? 1:0);
                //3.2发送价钱和数量给界面显示
                sendPriceAndCount();
                //3.3判断当前子条目是否选中
                if (listBean.getSelected() == 1){
                    //判断一下当前组中所有的子条目是否全部选中
                    if (isAllChildSelected(groupPosition)){
                        //如果全部选中改变一下当前组的状态
                        changeGroupState(groupPosition,true);
                        //.确定是否改变全选
                        changeAllState(isAllGroupChecked());
                    }

                }else {
                    //如果没有选中改变一下当前组的状态
                    changeGroupState(groupPosition,false);
                    //.确定是否改变全选
                    changeAllState(isAllGroupChecked());
                }

                //刷新适配器
                notifyDataSetChanged();
            }
        });

        //加号:
        holder.text_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //改变当前位置 中商品的数量
                listBean.setNum(listBean.getNum()+1);

                //判断一下是否选中...计算价格数量
                if (listBean.getSelected() == 1){
                    sendPriceAndCount();
                }

                //
                notifyDataSetChanged();
            }
        });

        //减号
        holder.text_jian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int num = listBean.getNum();

                if (num == 1){
                    return;
                }

                listBean.setNum(num -1);

                //判断是否选中
                if (listBean.getSelected() == 1){
                    sendPriceAndCount();
                }

                notifyDataSetChanged();
            }
        });

        return view;
    }

    /**
     * 改变当前组的状态
     * @param groupPosition
     * @param b
     */
    private void changeGroupState(int groupPosition, boolean b) {


        listGroup.get(groupPosition).setGroupChecked(b);

    }

    /**
     * 判断当前组中所有的二级是否选中
     * @param groupPosition
     * @return
     */
    private boolean isAllChildSelected(int groupPosition) {
        List<CartBean.DataBean.ListBean> listBeans = listChilds.get(groupPosition);

        for (int i=0;i<listBeans.size();i++){
            if (listBeans.get(i).getSelected() == 0){

                return false;
            }
        }

        return true;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;//子条目是否可以点击
    }

    /**
     * 设置是否全选
     * @param checked
     */
    public void setIfCheckAll(boolean checked) {

        for (int i = 0;i<listGroup.size();i++){
            CartBean.DataBean dataBean = listGroup.get(i);
            //设置组上面的checkBox是否选中
            dataBean.setGroupChecked(checked);

            List<CartBean.DataBean.ListBean> listBeans = dataBean.getList();
            for (int j = 0; j< listBeans.size(); j++){
                //改变是否选中的状态...数据应该变的是
                listBeans.get(j).setSelected(checked? 1:0);
            }

        }

        //计算价钱和数量并且发送到mainActivity显示
        sendPriceAndCount();

        //刷新适配器
        notifyDataSetChanged();

    }

    /**
     * 计算总价和数量,,,发送显示
     */
    private void sendPriceAndCount() {
        double price = 0;
        int count = 0;

        for (int i=0;i<listGroup.size();i++){
            List<CartBean.DataBean.ListBean> listBeans = listGroup.get(i).getList();
            for (int j = 0;j<listBeans.size();j++){

                CartBean.DataBean.ListBean listBean = listBeans.get(j);
                if (listBean.getSelected()==1){

                    price += listBean.getPrice()* listBean.getNum();
                    count += listBean.getNum();

                }
            }
        }

        CountPriceBean countPriceBean = new CountPriceBean(price, count);
        //显示到activity页面
        Message msg = Message.obtain();
        msg.what = 0;
        msg.obj = countPriceBean;
        handler.sendMessage(msg);

    }

    private class GroupHolder{
        CheckBox check_group;
        TextView text_group;
    }

    private class ChildHolder{
        CheckBox check_child;
        ImageView image_good;
        TextView text_title;
        TextView text_price;
        TextView text_jian;
        TextView text_num;
        TextView text_add;
    }
}
