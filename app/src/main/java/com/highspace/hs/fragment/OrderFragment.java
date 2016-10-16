package com.highspace.hs.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.highspace.hs.R;
import com.highspace.hs.adapter.MyOrderApater;
import com.highspace.hs.db.MyContentObserver;
import com.highspace.hs.view.LoadProgressDialog;

/**
 * Created by wenyue on 2016/10/11.
 */

public class OrderFragment extends Fragment {
   //listview的数据源
        private ListView orderListView;
        private View orderFragmentView;
        private LinearLayout emtyLayout;
        private MyContentObserver myContentObserver;
        private MyOrderApater mMyOrderApater;   //listview De adapter
        private LoadProgressDialog mDialog;
        private Handler handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                mDialog.dismiss();
                orderListView.setEmptyView(emtyLayout);
                //initEvent();
            }
        };

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            mDialog = LoadProgressDialog.Create(getActivity(), "加载刷新中...");
            if (!mDialog.isShowing())
                mDialog.show();
            Thread thread = new Thread(new MyThread());
            thread.start();
        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            if (orderFragmentView == null)
                orderFragmentView = inflater.inflate(R.layout.myorder_fragment, null);
            initView(orderFragmentView);
            return orderFragmentView;
        }

        private void initView(View view) {
            orderListView = (ListView) view.findViewById(R.id.my_order_lv);
            emtyLayout = (LinearLayout) view.findViewById(R.id.my_order_empty_view);

        }

       /* private void initEvent() {
           //获取订单数据
           Cursor cursor = getActivity().getContentResolver().query(GoodsContenProvider.URI_Goods_ALL, null, null, null, null);

            myContentObserver = new MyContentObserver(null, cursor);

            cursor.registerContentObserver(myContentObserver);


            mMyOrderApater = new MyOrderApater(getActivity(), cursor, 0);
            orderListView.setAdapter(mMyOrderApater);

        }*/

        class MyThread implements Runnable{

            @Override
            public void run() {
                try {
                    Thread.sleep(1500);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                handler.sendEmptyMessage(0);
            }
    }


}
