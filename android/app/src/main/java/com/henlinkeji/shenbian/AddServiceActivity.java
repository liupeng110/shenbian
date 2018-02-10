package com.henlinkeji.shenbian;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.blankj.utilcode.utils.ScreenUtils;
import com.facebook.drawee.view.SimpleDraweeView;
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
import com.henlinkeji.shenbian.base.view.ExpandableListViewForScrollView;
import com.henlinkeji.shenbian.base.view.PictureAndTextEditorView;
import com.henlinkeji.shenbian.base.view.ShowDialog;
import com.henlinkeji.shenbian.bean.AddArticle;
import com.henlinkeji.shenbian.bean.Classfy;
import com.henlinkeji.shenbian.bean.GetUpToken;
import com.henlinkeji.shenbian.bean.MyInfo;
import com.henlinkeji.shenbian.bean.PicTextBean;
import com.henlinkeji.shenbian.bean.QueryCart;
import com.henlinkeji.shenbian.bean.Sub;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadManager;
import com.sendtion.xrichtext.RichTextEditor;
import com.zhy.autolayout.utils.AutoUtils;

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

public class AddServiceActivity extends BaseActivity {
    @BindView(R.id.back)
    ImageView backImg;
    @BindView(R.id.add_img_iv)
    ImageView addImgIv;
    @BindView(R.id.publish)
    TextView publishTv;
    @BindView(R.id.edit_text)
    RichTextEditor pictureEdt;
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
    @BindView(R.id.agree)
    TextView agreeTv;
    @BindView(R.id.title_edt)
    EditText titleEdt;
    @BindView(R.id.price_edt)
    EditText priceEdt;
    @BindView(R.id.class_rl)
    RelativeLayout classSelectRl;
    @BindView(R.id.class_tv)
    TextView classTv;
    @BindView(R.id.classfy_list)
    ExpandableListViewForScrollView classfyLsit;

    // 所需的全部权限
    static final String[] PERMISSIONS = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};

    private PermissionsChecker mPermissionsChecker; // 权限检测器

    private boolean isPermisson = false;


    private static final int SELECT_POSITION = 1; // 请求码
    private static final int SELECT_PHOTO = 2; // 请求码
    private static final int REQUEST_CODE = 1; // 请求码

    private Subscription subsInsert;

    private String uploadToken;

    private UploadManager uploadManager;

    private HashMap<String, String> pathKeyMap = new HashMap<>();

    private boolean isContentNull;

    private LocationBean locationBean;

    private int serviceType;

    private int parentClassificationId;
    private int classificationId;

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
    }

    @Override
    protected void initData() {
        String str = "发表服务代表您同意<font color='#009698'>《用户服务协议》</font>";
        agreeTv.setText(Html.fromHtml(str));
        getClassfy();
    }

    @Override
    protected void initListener() {
        clasfyRl.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddServiceActivity.this, SelectAddressActivity.class);
                intent.putExtra("tag",1);
                startActivity(intent);
                InputTools.HideKeyboard(v);
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
        addImgIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getQiNiuToken();
            }
        });
        classSelectRl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (classfyLsit.getVisibility() == View.GONE) {
                    classfyLsit.setVisibility(View.VISIBLE);
                } else {
                    classfyLsit.setVisibility(View.GONE);
                }
            }
        });
        classTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (classfyLsit.getVisibility() == View.GONE) {
                    classfyLsit.setVisibility(View.VISIBLE);
                } else {
                    classfyLsit.setVisibility(View.GONE);
                }
            }
        });
        onlineTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onlineTv.setBackgroundResource(R.drawable.add_service_type_back1);
                onlineTv.setTextColor(Color.parseColor("#ffffff"));
                doorTv.setBackgroundResource(R.drawable.add_service_type_back2);
                doorTv.setTextColor(Color.parseColor("#8f959c"));
                shopTv.setBackgroundResource(R.drawable.add_service_type_back2);
                shopTv.setTextColor(Color.parseColor("#8f959c"));
                serviceType = 0;
            }
        });
        doorTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doorTv.setBackgroundResource(R.drawable.add_service_type_back1);
                doorTv.setTextColor(Color.parseColor("#ffffff"));
                onlineTv.setBackgroundResource(R.drawable.add_service_type_back2);
                onlineTv.setTextColor(Color.parseColor("#8f959c"));
                shopTv.setBackgroundResource(R.drawable.add_service_type_back2);
                shopTv.setTextColor(Color.parseColor("#8f959c"));
                serviceType = 1;
            }
        });
        shopTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shopTv.setBackgroundResource(R.drawable.add_service_type_back1);
                shopTv.setTextColor(Color.parseColor("#ffffff"));
                onlineTv.setBackgroundResource(R.drawable.add_service_type_back2);
                onlineTv.setTextColor(Color.parseColor("#8f959c"));
                doorTv.setBackgroundResource(R.drawable.add_service_type_back2);
                doorTv.setTextColor(Color.parseColor("#8f959c"));
                serviceType = 2;
            }
        });
        publishTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isContentNull = true;
                List<RichTextEditor.EditData> editList = pictureEdt.buildEditData();
                for (RichTextEditor.EditData itemData : editList) {
                    if (!TextUtils.isEmpty(itemData.inputStr)) {
                        isContentNull = false;
                        break;
                    } else if (!TextUtils.isEmpty(itemData.imagePath)) {
                        isContentNull = false;
                        break;
                    }
                }
                if (TextUtils.isEmpty(titleEdt.getText().toString())) {
                    ToastUtils.disPlayShort(AddServiceActivity.this, "标题未填写");
                    return;
                } else if (isContentNull) {
                    ToastUtils.disPlayShort(AddServiceActivity.this, "内容未填写");
                    return;
                }else if (TextUtils.isEmpty(classTv.getText().toString())) {
                    ToastUtils.disPlayShort(AddServiceActivity.this, "分类未选择");
                    return;
                } else if (TextUtils.isEmpty(priceEdt.getText().toString())) {
                    ToastUtils.disPlayShort(AddServiceActivity.this, "价格未填写");
                    return;
                } else if (locationBean == null) {
                    ToastUtils.disPlayShort(AddServiceActivity.this, "位置未填写");
                    return;
                } else if (TextUtils.isEmpty(classfyTv.getText().toString())) {
                    ToastUtils.disPlayShort(AddServiceActivity.this, "频道信息未填写");
                    return;
                } else {
                    addArticle();
                }
            }
        });
    }

    private void getQiNiuToken() {
        final LoadingDialog loadingDialog = new LoadingDialog(AddServiceActivity.this, true);
        loadingDialog.show("");
        Map<String, String> params = new HashMap<>();
        params.put("token", SPUtils.getToken(this));
        HttpUtils.post(this, MyConfig.GET_UPLOAD_TOKEN, params, new HttpUtils.HttpPostCallBackListener() {
            @Override
            public void onSuccess(String response) {
                if (loadingDialog != null) {
                    loadingDialog.exit();
                }
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
                        Intent intent = new Intent(AddServiceActivity.this, MultiImageSelectorActivity.class);
                        intent.putExtra(MultiImageSelectorActivity.EXTRA_SHOW_CAMERA, true);
                        intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_COUNT, 1);
                        intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_MODE, MultiImageSelectorActivity.MODE_SINGLE);
                        startActivityForResult(intent, SELECT_PHOTO);
                    }
                }
            }

            @Override
            public void onFailure(String response) {
                if (loadingDialog != null) {
                    loadingDialog.exit();
                }
                ShowDialog.showTipPopup(AddServiceActivity.this, "请重新点击上传", R.string.sure, new OperationCallback() {
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
                isPermisson=false;
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
                            ToastUtils.disPlayShort(AddServiceActivity.this, "选择文件失败");
                        }
                    }
                    break;
                case SELECT_POSITION:
                    locationBean = (LocationBean) data.getSerializableExtra("bean");
                    positionTv.setText(locationBean.getTitle());
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
        final LoadingDialog loadingDialog = new LoadingDialog(AddServiceActivity.this, true);
        loadingDialog.show("正在插入图片");
        subsInsert = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                try {
                    pictureEdt.measure(0, 0);
                    int width = ScreenUtils.getScreenWidth(AddServiceActivity.this);
                    int height = ScreenUtils.getScreenHeight(AddServiceActivity.this);
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
                        if (loadingDialog != null) {
                            loadingDialog.exit();
                        }
                        pictureEdt.addEditTextAtIndex(pictureEdt.getLastIndex(), " ");
                        ToastUtils.disPlayShort(AddServiceActivity.this, "图片插入成功");
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (loadingDialog != null) {
                            loadingDialog.exit();
                        }
                        ToastUtils.disPlayShort(AddServiceActivity.this, "图片插入失败:" + e.getMessage());
                    }

                    @Override
                    public void onNext(String imagePath) {
                        pictureEdt.insertImage(imagePath, pictureEdt.getMeasuredWidth());
                    }
                });
    }

    /**
     * 负责处理编辑数据提交等事宜，请自行实现
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

    private void upload(String uploadFilePath, final Intent data) {
        final LoadingDialog loadingDialog = new LoadingDialog(AddServiceActivity.this, true);
        loadingDialog.show("正在上传图片");
        if (this.uploadManager == null) {
            this.uploadManager = new UploadManager();
        }
        File uploadFile = new File(uploadFilePath);
        this.uploadManager.put(uploadFile, UUID.randomUUID().toString().replaceAll("-", ""), uploadToken, new UpCompletionHandler() {
            @Override
            public void complete(String key, ResponseInfo respInfo, JSONObject jsonData) {
                if (loadingDialog != null) {
                    loadingDialog.exit();
                }
                if (respInfo.isOK()) {
                    //异步方式插入图片
                    insertImagesSync(data, jsonData.toString());
                } else {
                    ToastUtils.disPlayShort(AddServiceActivity.this, "上传文件失败");
                }
            }

        }, null);
    }

    private void addArticle() {
        final LoadingDialog loadingDialog = new LoadingDialog(this, false);
        loadingDialog.show("正在发布");
        Map<String, String> params = new HashMap<>();
        params.put("token", SPUtils.getToken(AddServiceActivity.this));
        params.put("title", titleEdt.getText().toString());
        params.put("descriptions", new Gson().toJson(getEditData()));
        params.put("serviceFlag", 2 + "");
        params.put("serviceType", serviceType + "");
        params.put("categoryId", 1 + "");
        params.put("price", priceEdt.getText().toString());
        params.put("center", locationBean.getLon() + "," + locationBean.getLat());
        params.put("address", locationBean.getTitle());
        params.put("cityName", SPUtils.getDataString("city", "", AddServiceActivity.this));
        params.put("contact",SPUtils.getDataString("name", "", AddServiceActivity.this));
        params.put("houseName",  SPUtils.getDataString("detail", "", AddServiceActivity.this));
        params.put("mobilePhone",  SPUtils.getDataString("phone", "", AddServiceActivity.this).replace(" ", ""));
        params.put("detailStreet",  SPUtils.getDataString("address", "", AddServiceActivity.this));
        params.put("receivableAccount",  SPUtils.getDataString("account", "", AddServiceActivity.this));
        params.put("classificationId", classificationId+"");
        params.put("parentClassificationId", parentClassificationId+"");
        HttpUtils.post(this, MyConfig.ADD_ARTICLE_SERVICE, params, new HttpUtils.HttpPostCallBackListener() {
            @Override
            public void onSuccess(String response) {
                if (loadingDialog != null) {
                    loadingDialog.exit();
                }
                AddArticle addArticle = new Gson().fromJson(response, AddArticle.class);
                if (addArticle.getStatus().equals("0000")) {
                    ToastUtils.disPlayShort(AddServiceActivity.this, "发布成功");
                    finish();
                } else {
                    ShowDialog.showTipPopup(AddServiceActivity.this, addArticle.getError(), R.string.sure, new OperationCallback() {
                        @Override
                        public void execute() {

                        }
                    });
                }
            }

            @Override
            public void onFailure(String response) {
                if (loadingDialog != null) {
                    loadingDialog.exit();
                }
                ShowDialog.showTipPopup(AddServiceActivity.this, "服务器发生错误，请重新点击上传", R.string.sure, new OperationCallback() {
                    @Override
                    public void execute() {

                    }
                });
            }
        });
    }

    private void getClassfy() {
        Map<String, String> params = new HashMap<>();
        HttpUtils.post(this, MyConfig.GET_CLASSFY, params, new HttpUtils.HttpPostCallBackListener() {
            @Override
            public void onSuccess(String response) {
                final Classfy classfy = new Gson().fromJson(response, Classfy.class);
                if (classfy.getDate().size() > 0) {
                    classfyLsit.setAdapter(new ClassfyAdapter(classfy.getDate(), AddServiceActivity.this));
                    classfyLsit.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

                        @Override
                        public void onGroupExpand(int groupPosition) {
                            for (int i = 0; i < classfy.getDate().size(); i++) {
                                if (groupPosition != i) {
                                    classfyLsit.collapseGroup(i);
                                }
                            }

                        }

                    });
                }
            }

            @Override
            public void onFailure(String response) {
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        handler.post(runnable);
    }

    @Override
    public void onResume() {
        super.onResume();
        handler.post(runnable);
    }


    private Runnable runnable = new Runnable() {
        public void run() {
            handler.sendEmptyMessage(1);
        }
    };
    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 1:
                    String address = "";
                    if (!TextUtils.isEmpty(SPUtils.getDataString("name", "", AddServiceActivity.this))) {
                        address = address + SPUtils.getDataString("name", "", AddServiceActivity.this);
                    }
                    if (!TextUtils.isEmpty(SPUtils.getDataString("phone", "", AddServiceActivity.this))) {
                        address = address + SPUtils.getDataString("phone", "", AddServiceActivity.this);
                    }
                    if (!TextUtils.isEmpty(SPUtils.getDataString("city", "", AddServiceActivity.this))) {
                        address = address + SPUtils.getDataString("city", "", AddServiceActivity.this);
                    }
                    if (!TextUtils.isEmpty(SPUtils.getDataString("address", "", AddServiceActivity.this))) {
                        address = address + SPUtils.getDataString("address", "", AddServiceActivity.this);
                    }
                    if (!TextUtils.isEmpty(SPUtils.getDataString("detail", "", AddServiceActivity.this))) {
                        address = address + SPUtils.getDataString("detail", "", AddServiceActivity.this);
                    }
                    classfyTv.setText(address);
                    break;
            }
        }
    };

    public class ClassfyAdapter extends BaseExpandableListAdapter {

        private List<Classfy.DateBean> groups;
        private Context context;

        /**
         * 构造函数
         *
         * @param groups  组元素列表
         * @param context
         */
        public ClassfyAdapter(List<Classfy.DateBean> groups, Context context) {
            this.groups = groups;
            this.context = context;
        }

        @Override
        public int getGroupCount() {
            return groups.size();
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return groups.get(groupPosition).getSub().size();
        }

        @Override
        public Object getGroup(int groupPosition) {
            return groups.get(groupPosition);
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            List<Classfy.DateBean.SubBean> childs = groups.get(groupPosition).getSub();
            return childs.get(childPosition);
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

            final GroupViewHolder gholder;
            if (convertView == null) {
                gholder = new GroupViewHolder();
                convertView = View.inflate(context, R.layout.textview_layout, null);
                AutoUtils.autoSize(convertView);
                gholder.name = (TextView) convertView.findViewById(R.id.name);
                convertView.setTag(gholder);
            } else {
                gholder = (GroupViewHolder) convertView.getTag();
            }
            final Classfy.DateBean group = (Classfy.DateBean) getGroup(groupPosition);
            gholder.name.setText(group.getServiceClassification());
            notifyDataSetChanged();
            return convertView;
        }

        @Override
        public View getChildView(final int groupPosition, final int childPosition, final boolean isLastChild, View convertView, final ViewGroup parent) {
            final ChildViewHolder cholder;
            if (convertView == null) {
                cholder = new ChildViewHolder();
                convertView = View.inflate(context, R.layout.textview_layout2, null);
                AutoUtils.autoSize(convertView);
                cholder.name = (TextView) convertView.findViewById(R.id.name);
                cholder.item = (RelativeLayout) convertView.findViewById(R.id.item);
                convertView.setTag(cholder);
            } else {
                cholder = (ChildViewHolder) convertView.getTag();
            }

            final Classfy.DateBean.SubBean goodsInfo = (Classfy.DateBean.SubBean) getChild(groupPosition, childPosition);
            cholder.name.setText(goodsInfo.getServiceClassification());
            cholder.item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    parentClassificationId=groups.get(groupPosition).getId();
                    classificationId=groups.get(groupPosition).getSub().get(childPosition).getId();
                    classfyLsit.setVisibility(View.GONE);
                    classTv.setText(groups.get(groupPosition).getSub().get(childPosition).getServiceClassification());
                }
            });

            return convertView;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return false;
        }

        /**
         * 组元素绑定器
         */
        private class GroupViewHolder {
            TextView name;
        }

        /**
         * 子元素绑定器
         */
        private class ChildViewHolder {
            TextView name;
            RelativeLayout item;
        }

    }
}
