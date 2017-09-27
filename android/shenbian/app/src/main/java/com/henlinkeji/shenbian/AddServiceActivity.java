package com.henlinkeji.shenbian;

import android.Manifest;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.henlinkeji.shenbian.base.amap.LocationBean;
import com.henlinkeji.shenbian.base.application.MyApplication;
import com.henlinkeji.shenbian.base.ui.BaseActivity;
import com.henlinkeji.shenbian.base.utils.InputTools;
import com.henlinkeji.shenbian.base.utils.LogUtil;
import com.henlinkeji.shenbian.base.utils.PermissionsChecker;
import com.henlinkeji.shenbian.base.utils.ToastUtils;
import com.henlinkeji.shenbian.base.view.PictureAndTextEditorView;
import com.henlinkeji.shenbian.bean.Sub;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;

public class AddServiceActivity extends BaseActivity {
    @BindView(R.id.back)
    ImageView backImg;
    @BindView(R.id.add_img_iv)
    ImageView addImgIv;
    @BindView(R.id.publish)
    TextView publishTv;
    @BindView(R.id.edit_text)
    PictureAndTextEditorView contentEdt;
    @BindView(R.id.classfy_rl)
    RelativeLayout clasfyRl;
    @BindView(R.id.classfy_tv)
    TextView classfyTv;
    @BindView(R.id.position_rl)
    RelativeLayout positionRl;
    @BindView(R.id.position_tv)
    TextView positionTv;
    @BindView(R.id.residue_tv)
    TextView residueTv;
    @BindView(R.id.online_service_tv)
    TextView onlineTv;
    @BindView(R.id.door_service_tv)
    TextView doorTv;
    @BindView(R.id.shop_service_tv)
    TextView shopTv;

    // 所需的全部权限
    static final String[] PERMISSIONS = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};

    private PermissionsChecker mPermissionsChecker; // 权限检测器

    private boolean isPermisson = false;

    private ArrayList<Sub> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<Sub>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<Sub>>> options3Items = new ArrayList<>();
    private OptionsPickerView pvOptions;

    private MyApplication application;

    private static final int SELECT_POSITION = 1; // 请求码
    private static final int SELECT_PHOTO = 2; // 请求码
    private static final int REQUEST_CODE = 1; // 请求码

    private int cityId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void initLayout(Bundle savedInstanceState) {
        setContentView(R.layout.activity_add_service);
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initInstence() {
        MyApplication.getInstance().addActivity(this);
        mPermissionsChecker = new PermissionsChecker(this);
        application = (MyApplication) getApplication();
        initOptionPicker();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        //点击弹出选项选择器
        clasfyRl.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                InputTools.HideKeyboard(v);
                pvOptions.show();
            }
        });
        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                InputTools.HideKeyboard(v);
            }
        });
        positionRl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddServiceActivity.this, SelectPositionActivity.class);
                startActivityForResult(intent, SELECT_POSITION);
            }
        });

        contentEdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                residueTv.setText(s.toString().length() + "/500");
            }
        });
        addImgIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 缺少权限时, 进入权限配置页面
                if (mPermissionsChecker.lacksPermissions(PERMISSIONS)) {
                    //首先判断版本号是否大于等于6.0
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        startPermissionsActivity();
                        isPermisson = true;
                    }
                } else {
                    isPermisson = false;
                    Intent intent = new Intent(AddServiceActivity.this, MultiImageSelectorActivity.class);
                    intent.putExtra(MultiImageSelectorActivity.EXTRA_SHOW_CAMERA, true);
                    intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_COUNT, 1);
                    intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_MODE, MultiImageSelectorActivity.MODE_SINGLE);
                    startActivityForResult(intent, SELECT_PHOTO);
                }
            }
        });
        onlineTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onlineTv.setBackgroundResource(R.drawable.add_service_type_back1);
                onlineTv.setTextColor(Color.parseColor("#ffffff"));
                doorTv.setBackgroundResource(R.drawable.add_service_type_back2);
                doorTv.setTextColor(Color.parseColor("#333333"));
                shopTv.setBackgroundResource(R.drawable.add_service_type_back2);
                shopTv.setTextColor(Color.parseColor("#333333"));
            }
        });
        doorTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doorTv.setBackgroundResource(R.drawable.add_service_type_back1);
                doorTv.setTextColor(Color.parseColor("#ffffff"));
                onlineTv.setBackgroundResource(R.drawable.add_service_type_back2);
                onlineTv.setTextColor(Color.parseColor("#333333"));
                shopTv.setBackgroundResource(R.drawable.add_service_type_back2);
                shopTv.setTextColor(Color.parseColor("#333333"));
            }
        });
        shopTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shopTv.setBackgroundResource(R.drawable.add_service_type_back1);
                shopTv.setTextColor(Color.parseColor("#ffffff"));
                onlineTv.setBackgroundResource(R.drawable.add_service_type_back2);
                onlineTv.setTextColor(Color.parseColor("#333333"));
                doorTv.setBackgroundResource(R.drawable.add_service_type_back2);
                doorTv.setTextColor(Color.parseColor("#333333"));
            }
        });
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
        pvOptions.setTitle("选择城市");
        pvOptions.setCyclic(false, false, false);
        //设置默认选中的三级项目
        //监听确定选择按钮
        pvOptions.setSelectOptions(0, 0, 0);
        pvOptions.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {

            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {
                //返回的分别是三个级别的选中位置
                String tx = options1Items.get(options1).getPickerViewText() + options2Items.get(options1).get(option2).getPickerViewText() + options3Items.get(options1).get(option2).get(options3).getPickerViewText();
                classfyTv.setText(tx);
                if (options3Items.get(options1).get(option2).get(options3).getId() == 0) {
                    cityId = options2Items.get(options1).get(option2).getId();
                } else {
                    cityId = options3Items.get(options1).get(option2).get(options3).getId();
                }
            }
        });
    }

    private void startPermissionsActivity() {
        PermissionsActivity.startActivityForResult(this, REQUEST_CODE, PERMISSIONS);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 拒绝时, 关闭页面, 缺少主要权限, 无法运行
        if (requestCode == REQUEST_CODE && resultCode == PermissionsActivity.PERMISSIONS_DENIED) {
            ToastUtils.disPlayLongCenter(this, "未授予打开相机和访问储存卡的权限");
        } else {
            if (isPermisson) {
                Intent intent = new Intent(AddServiceActivity.this, MultiImageSelectorActivity.class);
                intent.putExtra(MultiImageSelectorActivity.EXTRA_SHOW_CAMERA, true);
                intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_COUNT, 1);
                intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_MODE, MultiImageSelectorActivity.MODE_SINGLE);
                startActivityForResult(intent, SELECT_PHOTO);
            }
        }
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case SELECT_PHOTO:
                    if (data != null) {
                        ArrayList<String> imageResults = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
                        contentEdt.insertBitmap(imageResults.get(0));
                    }
                    break;
                case SELECT_POSITION:
                    LocationBean bean = (LocationBean) data.getSerializableExtra("bean");
                    positionTv.setText(bean.getTitle());
                    break;
            }
        }
    }
}
