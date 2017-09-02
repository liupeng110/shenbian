package com.henlinkeji.shenbian;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.henlinkeji.shenbian.base.application.MyApplication;
import com.henlinkeji.shenbian.base.callback.OperationCallback;
import com.henlinkeji.shenbian.base.ui.BaseActivity;
import com.henlinkeji.shenbian.base.utils.HttpUtils;
import com.henlinkeji.shenbian.base.utils.Utils;
import com.henlinkeji.shenbian.base.view.ShowDialog;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends BaseActivity {
    @BindView(R.id.close)
    ImageView closeImg;
    @BindView(R.id.login_phone)
    EditText phoneEdt;
    @BindView(R.id.login_password)
    EditText passwordEdt;
    @BindView(R.id.register_retrieve_code)
    Button retrieveCodeBtn;
    @BindView(R.id.agree)
    TextView agreeTv;

    private CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void initLayout(Bundle savedInstanceState) {
        setContentView(R.layout.activity_login);
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
    }

    @Override
    protected void initInstence() {
        MyApplication.getInstance().addActivity(this);
        countDownTimer = new CountDownTimer(60000, 1000) {
            public void onTick(long millisUntilFinished) {
                retrieveCodeBtn.setText(millisUntilFinished / 1000 + "s");
                retrieveCodeBtn.setEnabled(false);
            }

            public void onFinish() {
                retrieveCodeBtn.setText("获取验证码");
                retrieveCodeBtn.setEnabled(true);
            }
        };
    }

    @Override
    protected void initData() {
        String str="温馨提示：未注册身边的手机号，登录时将自动注册，且代表您已同意<font color='#009698'>《用户服务协议》</font>";
        agreeTv.setText(Html.fromHtml(str));
    }

    @Override
    protected void initListener() {
        closeImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        retrieveCodeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (phoneEdt.getText().toString().length() > 0) {
                    if (Utils.isMobileNumber(phoneEdt.getText().toString().replace(" ", ""))) {
                        ShowDialog.showSelectNoTitlePopup(LoginActivity.this, "我们将验证码发送至"+ phoneEdt.getText().toString().replace(" ", ""), R.string.sure, R.string.cancel, new OperationCallback() {
                            @Override
                            public void execute() {
                                requestVerifyCode(phoneEdt.getText().toString().replace(" ", ""));
                            }
                        }, new OperationCallback() {
                            @Override
                            public void execute() {

                            }
                        });
                    } else {
                        ShowDialog.showTipPopup(LoginActivity.this, "手机号格式不正确", R.string.sure, new OperationCallback() {
                            @Override
                            public void execute() {
                            }
                        });
                    }
                } else {
                    ShowDialog.showTipPopup(LoginActivity.this, "未输入手机号", R.string.sure, new OperationCallback() {
                        @Override
                        public void execute() {

                        }
                    });
                }
            }
        });
        phoneEdt.addTextChangedListener(new TextWatcher() {
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
                    phoneEdt.setText(sb.toString());
                    phoneEdt.setSelection(index);
                }
                countDownTimer.cancel();
                retrieveCodeBtn.setText("获取验证码");
                retrieveCodeBtn.setEnabled(true);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void requestVerifyCode(String phone) {
        Map<String, String> params = new HashMap<>();
        params.put("phone", phone);
        countDownTimer.start();
//        HttpUtils.post(this, "", params, new HttpUtils.HttpPostCallBackListener() {
//            @Override
//            public void onSuccess(String response) {
//
//            }
//
//            @Override
//            public void onFailure(String response) {
//                ShowDialog.showTipPopup(LoginActivity.this, "验证码发送失败请重新发送", R.string.sure, new OperationCallback() {
//                    @Override
//                    public void execute() {
//
//                    }
//                });
//            }
//        });
    }

}
