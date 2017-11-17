package com.henlinkeji.shenbian;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.google.gson.Gson;
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
import com.henlinkeji.shenbian.base.utils.SPUtils;
import com.henlinkeji.shenbian.base.utils.ToastUtils;
import com.henlinkeji.shenbian.base.view.ShowDialog;
import com.henlinkeji.shenbian.bean.GetUpToken;
import com.henlinkeji.shenbian.bean.PicTextBean;
import com.henlinkeji.shenbian.bean.UserInfo;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadManager;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;

public class EditInfoActivity extends BaseActivity {
    private static final int REQUEST_CODE = 0; // 请求码
    private static final int SELECT_PICTURE = 1;
    private static final int SELECT_CAMER = 2;
    @BindView(R.id.title)
    TextView titleTv;
    @BindView(R.id.right)
    TextView rightTv;
    @BindView(R.id.back)
    ImageView backIv;
    @BindView(R.id.toolbar_rl)
    RelativeLayout toolbarRl;

    @BindView(R.id.shop_avator_rl)
    RelativeLayout avatorRl;
    @BindView(R.id.shop_avator)
    SimpleDraweeView avatorIv;
    @BindView(R.id.shop_name)
    EditText nameEdt;
    @BindView(R.id.shop_tag)
    EditText tagEdt;
    @BindView(R.id.shop_type)
    EditText typeEdt;
    @BindView(R.id.shop_intr_et)
    EditText intrEdt;
    @BindView(R.id.shop_address)
    EditText addressEdt;
    @BindView(R.id.shop_phone)
    EditText phoneEdt;
    @BindView(R.id.shop_account)
    EditText accountEdt;

    // 所需的全部权限
    static final String[] PERMISSIONS = new String[]{Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};

    private PermissionsChecker mPermissionsChecker; // 权限检测器

    private String picturePath;

    private boolean isSelectPic = false;

    private LoadingDialog loadingDialog;

    private String uploadToken;

    private UploadManager uploadManager;

    private String hash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initLayout(Bundle savedInstanceState) {
        setContentView(R.layout.activity_edit_info);
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initInstence() {
        MyApplication.getInstance().addActivity(this);
        mPermissionsChecker = new PermissionsChecker(this);

        titleTv.setText("编辑商店信息");
        rightTv.setText("保存");
        toolbarRl.setBackgroundColor(Color.parseColor("#c80920"));
        rightTv.setTextColor(Color.parseColor("#ffffff"));
        titleTv.setTextColor(Color.parseColor("#ffffff"));
        backIv.setImageResource(R.mipmap.back2);

        loadingDialog = new LoadingDialog(this, true);
        loadingDialog.show("身边");
    }

    @Override
    protected void initData() {
        getDetail(SPUtils.getToken(this));
    }

    @Override
    protected void initListener() {
        backIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        avatorRl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show(v);
            }
        });
        rightTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(picturePath)) {
                    commitInfo();
                } else {
                    getQiNiuToken();
                }
            }
        });
    }

    private void commitInfo() {
        Map<String, String> params = new HashMap<>();
        if (TextUtils.isEmpty(nameEdt.getText().toString())) {
            ToastUtils.disPlayShort(EditInfoActivity.this, "商店名称不能为空！");
            return;
        } else {
            params.put("userName", nameEdt.getText().toString());
        }
        if (TextUtils.isEmpty(tagEdt.getText().toString())) {
            ToastUtils.disPlayShort(EditInfoActivity.this, "商店标签不能为空！");
            return;
        } else {
            params.put("userTags", tagEdt.getText().toString());
        }
        if (TextUtils.isEmpty(typeEdt.getText().toString())) {
            ToastUtils.disPlayShort(EditInfoActivity.this, "服务类别不能为空！");
            return;
        } else {
            params.put("userTags", typeEdt.getText().toString());
        }
        if (TextUtils.isEmpty(intrEdt.getText().toString())) {
            ToastUtils.disPlayShort(EditInfoActivity.this, "商店简介不能为空！");
            return;
        } else {
            params.put("userDescription", intrEdt.getText().toString());
        }
        if (TextUtils.isEmpty(addressEdt.getText().toString())) {
            ToastUtils.disPlayShort(EditInfoActivity.this, "商店地址不能为空！");
            return;
        } else {
            params.put("merchantAddress", addressEdt.getText().toString());
        }
        if (TextUtils.isEmpty(phoneEdt.getText().toString())) {
            ToastUtils.disPlayShort(EditInfoActivity.this, "联系电话不能为空！");
            return;
        } else {
            params.put("merchantMobile", phoneEdt.getText().toString());
        }
        if (TextUtils.isEmpty(accountEdt.getText().toString())) {
            ToastUtils.disPlayShort(EditInfoActivity.this, "收款账户不能为空！");
            return;
        } else {
            params.put("merchantReceivableAccount", accountEdt.getText().toString());
        }
        if (!TextUtils.isEmpty(hash)) {
            params.put("userIcon", hash);
        }
        params.put("token", SPUtils.getToken(this));
        loadingDialog.show("身边");
        HttpUtils.post(this, MyConfig.EDIT_INFO, params, new HttpUtils.HttpPostCallBackListener() {
            @Override
            public void onSuccess(String response) {
                loadingDialog.exit();
                GetUpToken getUpToken = new Gson().fromJson(response, GetUpToken.class);
                if (getUpToken.getStatus().equals("0000")) {
                    ToastUtils.disPlayShort(EditInfoActivity.this, "保存成功");
                    finish();
                } else {
                    ShowDialog.showTipPopup(EditInfoActivity.this, getUpToken.getError(), R.string.sure, new OperationCallback() {
                        @Override
                        public void execute() {

                        }
                    });
                }
            }

            @Override
            public void onFailure(String response) {
                loadingDialog.exit();
            }
        });
    }

    private void getDetail(String token) {
        Map<String, String> params = new HashMap<>();
        params.put("token", token);
        HttpUtils.post(this, MyConfig.QUERY_INFO, params, new HttpUtils.HttpPostCallBackListener() {
            @Override
            public void onSuccess(String response) {
                loadingDialog.exit();
                UserInfo userInfo = new Gson().fromJson(response, UserInfo.class);
                if (userInfo.getStatus().equals("0000")) {
                    initUserInfo(userInfo.getData());
                }
            }

            @Override
            public void onFailure(String response) {
                loadingDialog.exit();
            }
        });
    }

    private void initUserInfo(UserInfo.DataBean userInfo) {
        if (userInfo.getUserIcon() != null) {
            if (!TextUtils.isEmpty(userInfo.getUserIcon())) avatorIv.setImageURI(Uri.parse(userInfo.getUserIcon()));
        }
        if (userInfo.getUserName() != null) {
            if (!TextUtils.isEmpty(userInfo.getUserName())) nameEdt.setText(userInfo.getUserName());
        }
        if (userInfo.getUserTags() != null) {
            if (!TextUtils.isEmpty(userInfo.getUserTags())) tagEdt.setText(userInfo.getUserTags());
        }
        if (userInfo.getUserType() != null) {
            if (!TextUtils.isEmpty(userInfo.getUserType())) typeEdt.setText(userInfo.getUserType());
        }
        if (userInfo.getUserDescription() != null) {
            if (!TextUtils.isEmpty(userInfo.getUserDescription())) intrEdt.setText(userInfo.getUserDescription());
        }
        if (userInfo.getMerchantAddress() != null) {
            if (!TextUtils.isEmpty(userInfo.getMerchantAddress())) addressEdt.setText(userInfo.getMerchantAddress());
        }
        if (userInfo.getMobilePhone() != null) {
            if (!TextUtils.isEmpty(userInfo.getMobilePhone())) phoneEdt.setText(userInfo.getMobilePhone());
        }
        if (userInfo.getMerchantReceivableAccount() != null) {
            if (!TextUtils.isEmpty(userInfo.getMerchantReceivableAccount()))
                accountEdt.setText(userInfo.getMerchantReceivableAccount());
        }
    }

    public void show(View view) {
        final Dialog dialog = new Dialog(this, R.style.ActionSheetDialogStyle);
        //填充对话框的布局
        View inflate = LayoutInflater.from(this).inflate(R.layout.select_image_layout, null);
        //初始化控件
        TextView choosePhoto = (TextView) inflate.findViewById(R.id.choosePhoto);
        TextView takePhoto = (TextView) inflate.findViewById(R.id.takePhoto);
        choosePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 缺少权限时, 进入权限配置页面
                if (mPermissionsChecker.lacksPermissions(PERMISSIONS)) {
                    //首先判断版本号是否大于等于6.0
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        startPermissionsActivity();
                    } else {
                        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                        intent.addCategory(Intent.CATEGORY_OPENABLE);
                        intent.setType("image/*");
                        startActivityForResult(Intent.createChooser(intent, "选择图片"), SELECT_PICTURE);
                    }
                } else {
                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.addCategory(Intent.CATEGORY_OPENABLE);
                    intent.setType("image/*");
                    startActivityForResult(Intent.createChooser(intent, "选择图片"), SELECT_PICTURE);
                }
                isSelectPic = true;
                dialog.dismiss();
            }
        });
        takePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 缺少权限时, 进入权限配置页面
                if (mPermissionsChecker.lacksPermissions(PERMISSIONS)) {
                    //首先判断版本号是否大于等于6.0
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        startPermissionsActivity();
                    } else {
                        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        if (takePictureIntent.resolveActivity(EditInfoActivity.this.getPackageManager()) != null) {
                            File photoFile = null;
                            photoFile = createImageFile();
                            if (photoFile != null) {
                                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
                            }
                        }
                        startActivityForResult(takePictureIntent, SELECT_CAMER);
                    }
                } else {
                    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    if (takePictureIntent.resolveActivity(EditInfoActivity.this.getPackageManager()) != null) {
                        File photoFile = null;
                        photoFile = createImageFile();
                        if (photoFile != null) {
                            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
                        }
                    }
                    startActivityForResult(takePictureIntent, SELECT_CAMER);
                }
                isSelectPic = false;
                dialog.dismiss();
            }
        });
        //将布局设置给Dialog
        dialog.setContentView(inflate);
        //获取当前Activity所在的窗体
        Window dialogWindow = dialog.getWindow();
        //设置Dialog从窗体底部弹出
        dialogWindow.setGravity(Gravity.BOTTOM);
        //获得窗体的属性
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.y = 20;//设置Dialog距离底部的距离
        dialogWindow.setAttributes(lp);
        dialog.show();//显示对话框
    }

    private void startPermissionsActivity() {
        PermissionsActivity.startActivityForResult(this, REQUEST_CODE, PERMISSIONS);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // 拒绝时, 关闭页面, 缺少主要权限, 无法运行
        if (requestCode == REQUEST_CODE && resultCode == PermissionsActivity.PERMISSIONS_DENIED) {
            ToastUtils.disPlayLongCenter(this, "重要权限未授予会导致应用基础功能无法使用，建议授予必要权限");
            return;
        }
        if (requestCode == REQUEST_CODE && resultCode == PermissionsActivity.PERMISSIONS_GRANTED) {
            if (isSelectPic) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "选择图片"), SELECT_PICTURE);
            } else {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(EditInfoActivity.this.getPackageManager()) != null) {
                    File photoFile = null;
                    photoFile = createImageFile();
                    if (photoFile != null) {
                        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photoFile));
                    }
                }
                startActivityForResult(takePictureIntent, SELECT_CAMER);
            }
            return;
        }

        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                Uri uri = data.getData();
                avatorIv.setImageURI(uri);
                String[] filePathColumn = {MediaStore.Images.Media.DATA};

                Cursor cursor = getContentResolver().query(uri, filePathColumn, null, null, null);
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                picturePath = cursor.getString(columnIndex);
                cursor.close();
            } else if (requestCode == SELECT_CAMER) {
                Uri uri = Uri.parse("file://" + picturePath);
                avatorIv.setImageURI(uri);
            }
            LogUtil.e("==picturePath==" + picturePath);
        }
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
                    if (!TextUtils.isEmpty(picturePath)) {
                        upload(picturePath);
                    }
                } else {
                    ToastUtils.disPlayShort(EditInfoActivity.this, "保存失败请重新保存");
                }
            }

            @Override
            public void onFailure(String response) {
            }
        });
    }

    private void upload(final String uploadFilePath) {
        if (this.uploadManager == null) {
            this.uploadManager = new UploadManager();
        }
        File uploadFile = new File(uploadFilePath);
        this.uploadManager.put(uploadFile, UUID.randomUUID().toString().replaceAll("-", ""), uploadToken, new UpCompletionHandler() {
            @Override
            public void complete(String key, ResponseInfo respInfo, JSONObject jsonData) {
                loadingDialog.exit();
                if (respInfo.isOK()) {
                    PicTextBean b1 = new Gson().fromJson(jsonData.toString(), PicTextBean.class);
                    hash = b1.getHash();
                    commitInfo();
                } else {
                    ToastUtils.disPlayShort(EditInfoActivity.this, "保存失败请重新保存");
                }
            }

        }, null);
    }

    private File createImageFile() {
        File storageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
        File image = null;
        try {
            image = File.createTempFile(generateFileName(),  /* prefix */
                    ".jpg",         /* suffix */
                    storageDir      /* directory */);
        } catch (IOException e) {
            e.printStackTrace();
        }
        picturePath = image.getAbsolutePath();
        return image;
    }

    public static String generateFileName() {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        return imageFileName;
    }

}
