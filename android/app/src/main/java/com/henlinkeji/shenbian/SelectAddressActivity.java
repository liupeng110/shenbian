package com.henlinkeji.shenbian;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.henlinkeji.shenbian.base.application.MyApplication;
import com.henlinkeji.shenbian.base.callback.OperationCallback;
import com.henlinkeji.shenbian.base.ui.BaseActivity;
import com.henlinkeji.shenbian.base.utils.SPUtils;
import com.henlinkeji.shenbian.base.utils.ToastUtils;
import com.henlinkeji.shenbian.base.utils.Utils;
import com.henlinkeji.shenbian.base.view.ShowDialog;
import com.henlinkeji.shenbian.bean.Sub;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SelectAddressActivity extends BaseActivity {
    @BindView(R.id.title)
    TextView titleTv;
    @BindView(R.id.right)
    TextView rightTv;
    @BindView(R.id.back)
    ImageView backIv;
    @BindView(R.id.toolbar_rl)
    RelativeLayout toolbarRl;
    @BindView(R.id.name_et)
    EditText nameEt;
    @BindView(R.id.phone_et)
    EditText phoneEt;
    @BindView(R.id.city_et)
    EditText cityEt;
    @BindView(R.id.address_et)
    EditText addressEt;
    @BindView(R.id.detail_et)
    EditText detailEt;
    @BindView(R.id.account_rl)
    RelativeLayout accountRl;
    @BindView(R.id.account)
    EditText accountEt;

    private ArrayList<Sub> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<Sub>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<Sub>>> options3Items = new ArrayList<>();
    private OptionsPickerView pvOptions;

    private MyApplication application;

    private  int tag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initLayout(Bundle savedInstanceState) {
        setContentView(R.layout.activity_select_address);
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initInstence() {
        MyApplication.getInstance().addActivity(this);
        titleTv.setText("添加位置");
        rightTv.setText("保存");
        toolbarRl.setBackgroundColor(Color.parseColor("#009698"));
        rightTv.setTextColor(Color.parseColor("#ffffff"));
        titleTv.setTextColor(Color.parseColor("#ffffff"));
        backIv.setImageResource(R.mipmap.back2);
        application = (MyApplication) getApplication();
        initOptionPicker();
        Intent intent = getIntent();
        tag = intent.getIntExtra("tag", -1);
        if (tag == 1) {
            accountRl.setVisibility(View.VISIBLE);
        } else {
            accountRl.setVisibility(View.GONE);
        }
    }

    @Override
    protected void initData() {
        if (!TextUtils.isEmpty(SPUtils.getDataString("name", "", this))) {
            nameEt.setText(SPUtils.getDataString("name", "", this));
        }
        if (!TextUtils.isEmpty(SPUtils.getDataString("phone", "", this))) {
            phoneEt.setText(SPUtils.getDataString("phone", "", this));
        }
        if (!TextUtils.isEmpty(SPUtils.getDataString("city", "", this))) {
            cityEt.setText(SPUtils.getDataString("city", "", this));
        }
        if (!TextUtils.isEmpty(SPUtils.getDataString("address", "", this))) {
            addressEt.setText(SPUtils.getDataString("address", "", this));
        }
        if (!TextUtils.isEmpty(SPUtils.getDataString("detail", "", this))) {
            detailEt.setText(SPUtils.getDataString("detail", "", this));
        }
        if (!TextUtils.isEmpty(SPUtils.getDataString("account", "", this))) {
            accountEt.setText(SPUtils.getDataString("account", "", this));
        }
    }

    @Override
    protected void initListener() {
        backIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
//        addressRl.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (pvOptions != null) {
//                    pvOptions.show();
//                }
//            }
//        });
        rightTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });

        phoneEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s == null || s.length() == 0) return;
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < s.length(); i++) {
                    if (i != 3 && i != 8 && s.charAt(i) == ' ') {
                        continue;
                    } else {
                        sb.append(s.charAt(i));
                        if ((sb.length() == 4 || sb.length() == 9) && sb.charAt(sb.length() - 1) != ' ') {
                            sb.insert(sb.length() - 1, ' ');
                        }
                    }
                }
                if (!sb.toString().equals(s.toString())) {
                    int index = start + 1;
                    if (sb.charAt(start) == ' ') {
                        if (before == 0) {
                            index++;
                        } else {
                            index--;
                        }
                    } else {
                        if (before == 1) {
                            index--;
                        }
                    }
                    phoneEt.setText(sb.toString());
                    phoneEt.setSelection(index);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void save() {
        if (TextUtils.isEmpty(nameEt.getText().toString())) {
            ToastUtils.disPlayShort(SelectAddressActivity.this, "未输入联系人");
            return;
        } else {
            SPUtils.setDataString("name", nameEt.getText().toString(), this);
        }
        if (phoneEt.getText().toString().length() <= 0) {
            ToastUtils.disPlayShort(SelectAddressActivity.this, "未输入手机号");
            return;
        } else if (!Utils.isMobileNumber(phoneEt.getText().toString().replace(" ", ""))) {
            ToastUtils.disPlayShort(SelectAddressActivity.this, "手机号格式不正确");
            return;
        } else {
            SPUtils.setDataString("phone", phoneEt.getText().toString(), this);
        }
        if (TextUtils.isEmpty(cityEt.getText().toString())) {
            ToastUtils.disPlayShort(SelectAddressActivity.this, "未输入城市");
            return;
        } else {
            SPUtils.setDataString("city", cityEt.getText().toString(), this);
        }
        if (TextUtils.isEmpty(addressEt.getText().toString())) {
            ToastUtils.disPlayShort(SelectAddressActivity.this, "未输入地址");
            return;
        } else {
            SPUtils.setDataString("address", addressEt.getText().toString(), this);
        }
        if (TextUtils.isEmpty(detailEt.getText().toString())) {
            ToastUtils.disPlayShort(SelectAddressActivity.this, "未输入门牌号");
            return;
        } else {
            SPUtils.setDataString("detail", detailEt.getText().toString(), this);
        }
        if (tag == 1) {
            if (TextUtils.isEmpty(accountEt.getText().toString())) {
                ToastUtils.disPlayShort(SelectAddressActivity.this, "未输入收款账户");
                return;
            } else {
                SPUtils.setDataString("account", accountEt.getText().toString(), this);
            }
        }
        finish();
    }

    public void initOptionPicker() {
        options1Items = application.getOptions1Items();
        options2Items = application.getOptions2Items();
        options3Items = application.getOptions3Items();

        //选项选择器
        pvOptions = new OptionsPickerView(this);
        //三级联动效果
        pvOptions.setPicker(options1Items, options2Items, options3Items, true);
        //设置选择的三级单位
        pvOptions.setTitle("选择地址");
        pvOptions.setCyclic(false, false, false);
        //设置默认选中的三级项目
        //监听确定选择按钮
        pvOptions.setSelectOptions(0, 0, 0);
        pvOptions.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {

            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {
                //返回的分别是三个级别的选中位置
                String tx = options1Items.get(options1).getPickerViewText() + options2Items.get(options1).get(option2).getPickerViewText() + options3Items.get(options1).get(option2).get(options3).getPickerViewText();
//                addressTv.setText(tx);
            }
        });
    }
}
