package com.henlinkeji.shenbian.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.henlinkeji.shenbian.CommitOrderActivity;
import com.henlinkeji.shenbian.Constants;
import com.henlinkeji.shenbian.MainActivity;
import com.henlinkeji.shenbian.OrderListActivity;
import com.henlinkeji.shenbian.base.application.MyApplication;
import com.henlinkeji.shenbian.base.utils.LogUtil;
import com.henlinkeji.shenbian.base.utils.SPUtils;
import com.tencent.mm.sdk.constants.ConstantsAPI;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.sdk.openapi.WXAPIFactory;


public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {
	
    private IWXAPI api;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    	api = WXAPIFactory.createWXAPI(this, Constants.APP_ID);
        api.handleIntent(getIntent(), this);
    }

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		setIntent(intent);
        api.handleIntent(intent, this);
	}

	@Override
	public void onReq(BaseReq req) {
	}

	@Override
	public void onResp(BaseResp resp) {
		if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
			if (resp.errCode==0){
				Toast.makeText(this,"支付成功", Toast.LENGTH_SHORT).show();
			}else if (resp.errCode==-2){
				Toast.makeText(this,"支付取消", Toast.LENGTH_SHORT).show();
			}else {
				Toast.makeText(this,"支付异常", Toast.LENGTH_SHORT).show();
			}
			if (SPUtils.getNeedDo(this)){
				startActivity(new Intent(WXPayEntryActivity.this,OrderListActivity.class));
				CommitOrderActivity.commitOrderActivity.finish();
			}
			finish();
		}
	}

}