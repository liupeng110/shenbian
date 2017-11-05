package com.henlinkeji.shenbian;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.henlinkeji.shenbian.base.amap.LocationBean;
import com.henlinkeji.shenbian.base.application.MyApplication;
import com.henlinkeji.shenbian.base.ui.BaseActivity;
import com.henlinkeji.shenbian.base.utils.InputTools;
import com.henlinkeji.shenbian.base.utils.LogUtil;
import com.henlinkeji.shenbian.base.utils.PermissionsChecker;
import com.henlinkeji.shenbian.base.utils.ToastUtils;
import com.henlinkeji.shenbian.base.utils.UriUtils;
import com.henlinkeji.shenbian.base.view.PictureAndTextEditorView;
import com.henlinkeji.shenbian.bean.Sub;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;

public class AddArticleActivity extends BaseActivity {
    @BindView(R.id.back)
    ImageView backImg;
    @BindView(R.id.publish)
    TextView publishTv;
    @BindView(R.id.title_edt)
    EditText titleEdt;
    @BindView(R.id.edit_text)
    PictureAndTextEditorView pictureEdt;
    @BindView(R.id.add_picture)
    TextView addPictureTv;
    @BindView(R.id.add_classfy)
    TextView addClassfyTv;
    @BindView(R.id.add_position)
    TextView addPositionTv;

    private static final int REQUEST_CODE = 1; // 请求码
    private static final int SELECT_PHOTO = 2; // 请求码
    private static final int SELECT_POSITION = 3; // 请求码

    // 所需的全部权限
    static final String[] PERMISSIONS = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};

    private PermissionsChecker mPermissionsChecker; // 权限检测器

    private boolean isPermisson = false;

    private ArrayList<Sub> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<Sub>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<Sub>>> options3Items = new ArrayList<>();
    private OptionsPickerView pvOptions;

    private MyApplication application;

    private int cityId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void initLayout(Bundle savedInstanceState) {
        setContentView(R.layout.activity_add_article);
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
        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                InputTools.HideKeyboard(v);
            }
        });
        addPictureTv.setOnClickListener(new View.OnClickListener() {
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
                    Intent intent = new Intent(AddArticleActivity.this, MultiImageSelectorActivity.class);
                    intent.putExtra(MultiImageSelectorActivity.EXTRA_SHOW_CAMERA, true);
                    intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_COUNT, 1);
                    intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_MODE, MultiImageSelectorActivity.MODE_SINGLE);
                    startActivityForResult(intent, SELECT_PHOTO);
                }
            }
        });
        addPositionTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddArticleActivity.this, SelectPositionActivity.class);
                startActivityForResult(intent, SELECT_POSITION);
            }
        });
        addClassfyTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputTools.HideKeyboard(v);
                pvOptions.show();
            }
        });
    }

    private void startPermissionsActivity() {
        PermissionsActivity.startActivityForResult(this, REQUEST_CODE, PERMISSIONS);
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
        pvOptions.setTitle("选择分类");
        pvOptions.setCyclic(false, false, false);
        //设置默认选中的三级项目
        //监听确定选择按钮
        pvOptions.setSelectOptions(0, 0, 0);
        pvOptions.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {

            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {
                //返回的分别是三个级别的选中位置
                String tx = options1Items.get(options1).getPickerViewText() + options2Items.get(options1).get(option2).getPickerViewText() + options3Items.get(options1).get(option2).get(options3).getPickerViewText();
                ToastUtils.disPlayShort(AddArticleActivity.this,"选择的分类是"+tx);
                if (options3Items.get(options1).get(option2).get(options3).getId() == 0) {
                    cityId = options2Items.get(options1).get(option2).getId();
                } else {
                    cityId = options3Items.get(options1).get(option2).get(options3).getId();
                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 拒绝时, 关闭页面, 缺少主要权限, 无法运行
        if (requestCode == REQUEST_CODE && resultCode == PermissionsActivity.PERMISSIONS_DENIED) {
            ToastUtils.disPlayLongCenter(this, "未授予打开相机和访问储存卡的权限");
        } else {
            if (isPermisson) {
                Intent intent = new Intent(AddArticleActivity.this, MultiImageSelectorActivity.class);
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
                        pictureEdt.insertBitmap(imageResults.get(0));
                    }
                    break;
                case SELECT_POSITION:
                    LocationBean bean = (LocationBean) data.getSerializableExtra("bean");
                    ToastUtils.disPlayShort(AddArticleActivity.this,"选择的地址是"+bean.getTitle());
                    LogUtil.e("===位置==" + bean.getLat() + bean.getTitle() + bean.getLon());
                    break;
            }
        }
    }
}
