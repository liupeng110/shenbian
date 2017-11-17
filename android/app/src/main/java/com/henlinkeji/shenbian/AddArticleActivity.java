package com.henlinkeji.shenbian;

import android.Manifest;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.blankj.utilcode.utils.ScreenUtils;
import com.google.gson.Gson;
import com.henlinkeji.shenbian.base.amap.LocationBean;
import com.henlinkeji.shenbian.base.application.MyApplication;
import com.henlinkeji.shenbian.base.callback.OperationCallback;
import com.henlinkeji.shenbian.base.config.MyConfig;
import com.henlinkeji.shenbian.base.load.LoadingDialog;
import com.henlinkeji.shenbian.base.ui.BaseActivity;
import com.henlinkeji.shenbian.base.utils.HttpUtils;
import com.henlinkeji.shenbian.base.utils.ImageUtils;
import com.henlinkeji.shenbian.base.utils.InputTools;
import com.henlinkeji.shenbian.base.utils.LogUtil;
import com.henlinkeji.shenbian.base.utils.PermissionsChecker;
import com.henlinkeji.shenbian.base.utils.SDCardUtil;
import com.henlinkeji.shenbian.base.utils.SPUtils;
import com.henlinkeji.shenbian.base.utils.ToastUtils;
import com.henlinkeji.shenbian.base.view.ShowDialog;
import com.henlinkeji.shenbian.bean.AddArticle;
import com.henlinkeji.shenbian.bean.GetUpToken;
import com.henlinkeji.shenbian.bean.PicTextBean;
import com.henlinkeji.shenbian.bean.Sub;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadManager;
import com.sendtion.xrichtext.RichTextEditor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class AddArticleActivity extends BaseActivity {
    @BindView(R.id.back)
    ImageView backImg;
    @BindView(R.id.publish)
    TextView publishTv;
    @BindView(R.id.title_edt)
    EditText titleEdt;
    @BindView(R.id.edit_text)
    RichTextEditor pictureEdt;
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

    private Subscription subsInsert;

    private String uploadToken;

    private UploadManager uploadManager;

    private HashMap<String, String> pathKeyMap = new HashMap<>();

    private LocationBean locationBean;

    private boolean isContentNull;

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
                getQiNiuToken();
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
        publishTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isContentNull=true;
                List<RichTextEditor.EditData> editList = pictureEdt.buildEditData();
                for (RichTextEditor.EditData itemData : editList) {
                    if (!TextUtils.isEmpty(itemData.inputStr)) {
                        isContentNull=false;
                        break;
                    } else if (!TextUtils.isEmpty(itemData.imagePath)) {
                       isContentNull=false;
                        break;
                    }
                }
                if (TextUtils.isEmpty(titleEdt.getText().toString())) {
                    ToastUtils.disPlayShort(AddArticleActivity.this, "标题未填写");
                    return;
                } else if (isContentNull) {
                    ToastUtils.disPlayShort(AddArticleActivity.this, "内容未填写");
                    return;
                } else if (locationBean == null) {
                    ToastUtils.disPlayShort(AddArticleActivity.this, "位置未填写");
                    return;
                } else {
                    addArticle();
                }
            }
        });
    }

    private void getQiNiuToken() {
        Map<String, String> params = new HashMap<>();
        params.put("token", SPUtils.getToken(this));
        HttpUtils.post(this, MyConfig.GET_UPLOAD_TOKEN, params, new HttpUtils.HttpPostCallBackListener() {
            @Override
            public void onSuccess(String response) {
                GetUpToken generalBean = new Gson().fromJson(response, GetUpToken.class);
                if (generalBean.getStatus().equals("0000")) {
                    uploadToken = generalBean.getData();
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
            }

            @Override
            public void onFailure(String response) {
                ShowDialog.showTipPopup(AddArticleActivity.this, "服务器发生错误，请重新点击上传", R.string.sure, new OperationCallback() {
                    @Override
                    public void execute() {

                    }
                });
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
                ToastUtils.disPlayShort(AddArticleActivity.this, "选择的分类是" + tx);
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
                        ArrayList<String> photos = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
                        if (photos.size() > 0) {
                            upload(photos.get(0), data);
                        } else {
                            ToastUtils.disPlayShort(AddArticleActivity.this, "选择文件失败");
                        }
                    }
                    break;
                case SELECT_POSITION:
                    locationBean = (LocationBean) data.getSerializableExtra("bean");
                    break;
            }
        }
    }

    /**
     * 异步方式插入图片
     *
     * @param data
     */
    private void insertImagesSync(final Intent data, final String json) {
        final LoadingDialog loadingDialog = new LoadingDialog(AddArticleActivity.this, true);
        loadingDialog.show("正在插入图片");
        subsInsert = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                try {
                    pictureEdt.measure(0, 0);
                    int width = ScreenUtils.getScreenWidth(AddArticleActivity.this);
                    int height = ScreenUtils.getScreenHeight(AddArticleActivity.this);
                    ArrayList<String> photos = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
                    //可以同时插入多张图片
                    for (String imagePath : photos) {
                        Bitmap bitmap = ImageUtils.getSmallBitmap(imagePath, width, height);//压缩图片
                        imagePath = SDCardUtil.saveToSdCard(bitmap);
                        pathKeyMap.put(imagePath, json);
                        subscriber.onNext(imagePath);
                    }
                    subscriber.onCompleted();
                } catch (Exception e) {
                    e.printStackTrace();
                    subscriber.onError(e);
                }
            }
        }).onBackpressureBuffer().subscribeOn(Schedulers.io())//生产事件在io
                .observeOn(AndroidSchedulers.mainThread())//消费事件在UI线程
                .subscribe(new Observer<String>() {
                    @Override
                    public void onCompleted() {
                        loadingDialog.exit();
                        pictureEdt.addEditTextAtIndex(pictureEdt.getLastIndex(), " ");
                        ToastUtils.disPlayShort(AddArticleActivity.this, "图片插入成功");
                    }

                    @Override
                    public void onError(Throwable e) {
                        loadingDialog.exit();
                        ToastUtils.disPlayShort(AddArticleActivity.this, "图片插入失败:" + e.getMessage());
                    }

                    @Override
                    public void onNext(String imagePath) {
                        pictureEdt.insertImage(imagePath, pictureEdt.getMeasuredWidth());
                    }
                });
    }

    /**
     * 获得编辑框内容
     */
    private List<PicTextBean> getEditData() {
        List<PicTextBean> list = new ArrayList<>();
        List<RichTextEditor.EditData> editList = pictureEdt.buildEditData();
        for (RichTextEditor.EditData itemData : editList) {
            PicTextBean bean = new PicTextBean();
            if (itemData.inputStr != null) {
                bean.setText(itemData.inputStr);
            } else if (itemData.imagePath != null) {
                if (pathKeyMap.containsKey(itemData.imagePath)) {
                    String qiniu = pathKeyMap.get(itemData.imagePath);
                    PicTextBean b1 = new Gson().fromJson(qiniu, PicTextBean.class);
                    bean.setKey(b1.getKey());
                    bean.setHash(b1.getHash());
                    bean.setBucket(b1.getBucket());
                    bean.setFsize(b1.getFsize());
                }
            }
            list.add(bean);
        }
        return list;
    }

    private void upload(final String uploadFilePath, final Intent data) {
        final LoadingDialog loadingDialog = new LoadingDialog(AddArticleActivity.this, true);
        loadingDialog.show("正在上传图片");
        if (this.uploadManager == null) {
            this.uploadManager = new UploadManager();
        }
        File uploadFile = new File(uploadFilePath);
        this.uploadManager.put(uploadFile, UUID.randomUUID().toString().replaceAll("-", ""), uploadToken, new UpCompletionHandler() {
            @Override
            public void complete(String key, ResponseInfo respInfo, JSONObject jsonData) {
                loadingDialog.exit();
                if (respInfo.isOK()) {
                    //异步方式插入图片
                    insertImagesSync(data,jsonData.toString());
                } else {
                    ToastUtils.disPlayShort(AddArticleActivity.this, "上传文件失败");
                }
            }

        }, null);
    }

    private void addArticle() {
        Map<String, String> params = new HashMap<>();
        params.put("token", SPUtils.getToken(AddArticleActivity.this));
        params.put("title", titleEdt.getText().toString());
        params.put("descriptions", new Gson().toJson(getEditData()));
        params.put("serviceFlag", 1 + "");
        params.put("serviceType", 1 + "");
        params.put("categoryId", 1 + "");
        params.put("price", 1 + "");
        params.put("center", locationBean.getLon() + "," + locationBean.getLat());
        HttpUtils.post(this, MyConfig.ADD_ARTICLE_SERVICE, params, new HttpUtils.HttpPostCallBackListener() {
            @Override
            public void onSuccess(String response) {
                AddArticle addArticle=new Gson().fromJson(response,AddArticle.class);
                if (addArticle.getStatus().equals("0000")){
                    ToastUtils.disPlayShort(AddArticleActivity.this,"发布成功");
                    finish();
                }else {
                    ShowDialog.showTipPopup(AddArticleActivity.this, addArticle.getError(), R.string.sure, new OperationCallback() {
                        @Override
                        public void execute() {

                        }
                    });
                }
            }

            @Override
            public void onFailure(String response) {
                ShowDialog.showTipPopup(AddArticleActivity.this, "服务器发生错误，请重新点击上传", R.string.sure, new OperationCallback() {
                    @Override
                    public void execute() {

                    }
                });
            }
        });
    }

}
