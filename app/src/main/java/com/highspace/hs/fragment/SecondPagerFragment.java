package com.highspace.hs.fragment;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.highspace.hs.R;
import com.highspace.hs.activity.StartAddressActivity;
import com.highspace.hs.adapter.AddressAdapter;
import com.highspace.hs.db.AddressInfoSQLiteHelper;

import static android.app.Activity.RESULT_OK;

/**
 * Created by wenyue on 2016/10/9.
 */

public class SecondPagerFragment extends Fragment implements AdapterView.OnItemLongClickListener {
    private ListView address_list;
    private Button add_button;
    private View mView;
    private AddressAdapter addressAdapter;
    //private List<AddressMessage> addressMessageList  = new ArrayList<AddressMessage>();
    private AddressInfoSQLiteHelper addressHelper;
    private Cursor cursor;
    private Dialog dialog;
    private int itemPosition;
    private  static int unit = 0;
    //SQLiteDatabase db = addressHelper.getReadableDatabase();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView == null)
            mView = inflater.inflate(R.layout.first_pager_fragment, null);
        addressHelper  = AddressInfoSQLiteHelper.getmHelperInstance(getActivity(), "Address.db", null, 1);
        initView(mView);
        initEvent();
        refreshList();
        return mView;

    }

    private void refreshList() {
        SQLiteDatabase db = addressHelper.getWritableDatabase();
        cursor = db.query("end", null, null, null, null, null, null);
        addressAdapter = new AddressAdapter(getActivity(), cursor, 0);
        address_list.setAdapter(addressAdapter);
    }


    private void initEvent() {
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(getActivity(), StartAddressActivity.class), 1);

            }
        });

        address_list.setOnItemLongClickListener(this);

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    Bundle bundle = data.getExtras();
                    //AddressMessage addMes = new AddressMessage();
                    SQLiteDatabase db = addressHelper.getWritableDatabase();
                    ContentValues values = new ContentValues();
                    values.put("addressName", bundle.getString("name"));
                    values.put("phoneNumber", bundle.getString("number"));
                    values.put("addressLocation", bundle.getString("address") + bundle.getString("location"));
                    db.insert("end", null, values);
                    cursor = db.query("end", null, null, null, null, null, null);
                    addressAdapter.changeCursor(cursor);
                    initEvent();
                }
                break;
            default:
        }
    }

    private void initView(View view) {
        address_list = (ListView) view.findViewById(R.id.addressList);
        add_button = (Button) view.findViewById(R.id.add_address);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        itemPosition = position + unit;
        dialog = new Dialog(getActivity(), R.style.Item_Dialog);
        View dialogView = LayoutInflater.from(getActivity()).inflate(R.layout.item_dialog, null);
        dialog.setContentView(dialogView);
        dialog.show();
        TextView deleteText = (TextView) dialogView.findViewById(R.id.delete);
        TextView editText = (TextView) dialogView.findViewById(R.id.edit);
       /*删除子项并更新数据库数据*/
        deleteText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = addressHelper.getWritableDatabase();
                db.delete("end", "_id = ?", new String[]{itemPosition + 1 + ""});
                unit = unit +  1;
                cursor = db.query("end", null, null, null, null, null, null);
                addressAdapter.changeCursor(cursor);
                //refreshList();
                dialog.dismiss();
                dialog = null;
            }
        });
        return false;
    }
}
