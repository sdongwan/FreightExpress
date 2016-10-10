package com.highspace.hs.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.highspace.hs.R;
import com.highspace.hs.activity.StartAddressActivity;
import com.highspace.hs.adapter.AddressAdapter;
import com.highspace.hs.bean.AddressMessage;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

/**
 * Created by wenyue on 2016/10/9.
 */

public class SecondPagerFragment extends Fragment {
    private ListView address_list;
    private Button add_button;
    private View mView;
    private AddressAdapter addressAdapter;
    private List<AddressMessage> addressMessageList  = new ArrayList<AddressMessage>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView == null)
            mView = inflater.inflate(R.layout.first_pager_fragment, null);
        initView(mView);
        initEvent();
        return mView;

    }


    private void initEvent() {
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "增添发货地址", Toast.LENGTH_SHORT).show();
                //startActivity(new Intent(getActivity(), StartAddressActivity.class));
                startActivityForResult(new Intent(getActivity(), StartAddressActivity.class), 1);
            }
        });
        addressAdapter = new AddressAdapter(getActivity(), addressMessageList);
        address_list.setAdapter(addressAdapter);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    Bundle bundle = data.getExtras();
                    AddressMessage addMes = new AddressMessage();
                    addMes.setName(bundle.getString("name"));
                    addMes.setPhoneNumber(bundle.getString("number"));
                    addMes.setAds(bundle.getString("address") + bundle.getString("location"));
                    addressMessageList.add(addMes);
                    addressAdapter.notifyDataSetChanged();
                    address_list.setSelection(addressMessageList.size() - 1);
                }
                break;
            default:
        }
    }

    private void initView(View view) {
        address_list = (ListView) view.findViewById(R.id.addressList);
        add_button = (Button) view.findViewById(R.id.add_address);
    }
}
