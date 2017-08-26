package com.sbh.upload;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.qiniu.util.Base64;
import com.qiniu.util.StringMap;
import com.qiniu.util.UrlSafeBase64;
import com.sbh.vo.service.ImgDescription;
import com.sbh.vo.service.ImgTextVO;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.*;

/**
 * Created by Administrator on 2017/8/20.
 */
@Service
public class MaterialUpLoad {
   private  String accessKey = "kFGJHxc1WzK8mScBAMl4MKE20GNkQjrYuzeT8kw4";
   private  String secretKey = "eyCTtstGnBQfxTjzNOFicqy8sUPp_p7fmNcvLiyR";
   private  String bucketName = "yxtest";
    Auth auth = Auth.create(accessKey,secretKey);
   String key ="";
    public DefaultPutRet upload(byte [] byteImg){
        Configuration cfg = new Configuration(Zone.zone0());
        UploadManager uploadManager = new UploadManager(cfg);
        String upToken = auth.uploadToken(bucketName);
        DefaultPutRet putRet= null;
        try {
            Response response = uploadManager.put(byteImg, key, upToken);
            //解析上传成功的结果
             putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            System.out.println(putRet.key);
            System.out.println(putRet.hash);
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
        }
      /*  FileInputStream fis = null;
        int l = (int) (new File(pathFile).length());
        byte[] src = new byte[l];
        try {
            fis = new FileInputStream(new File(pathFile));
            fis.read(src);
            String file64 = Base64.encodeToString(src, 0);
            String url = "http://upload.qiniu.com/putb64/" + l+"/key/"+ UrlSafeBase64.encodeToString(key);
            //非华东空间需要根据注意事项 1 修改上传域名
            RequestBody rb = RequestBody.create(null, file64);
            Request request = new Request.Builder().
                    url(url).
                    addHeader("Content-Type", "application/octet-stream")
                    .addHeader("Authorization", "UpToken " + getUpToken())
                    .post(rb).build();
            System.out.println(request.headers());
            OkHttpClient client = new OkHttpClient();
            okhttp3.Response response = client.newCall(request).execute();
            System.out.println(response);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException ex){
            ex.printStackTrace();
        }*/
        return putRet;
    }
    public String getUpToken() {
        return auth.uploadToken(bucketName, null, 3600, new StringMap().put("insertOnly", 1));
    }

    public List<ImgTextVO> appUpload( List<ImgDescription> photoes){
        List<ImgTextVO>  vos = new ArrayList<ImgTextVO>();
       // request.setCharacterEncoding("utf-8");
       // response.setCharacterEncoding("utf-8");
       // response.setContentType("text/html");
        Map<String, Object> map = new HashMap<String, Object>();
       // System.out.println("userId:"+userId);
        //System.out.println("photo:"+photo);
        try {

            // 对base64数据进行解码 生成 字节数组，不能直接用Base64.decode（）；进行解密
           // String [] photoArr = photoes.split(",");

            for (int i=0;i<photoes.size();i++){
                Map<String,Object> resultMap =  new HashMap<String,Object>();
                  ImgDescription img = photoes.get(i);
                  ImgTextVO vo = new ImgTextVO();
                 if(img.getImg()!=null&&!"".equals(img.getImg())){
                     byte[] photoimg = new BASE64Decoder().decodeBuffer(img.getImg());
                     for (int j = 0; j < photoimg.length; ++j) {
                         if (photoimg[j] < 0) {
                             // 调整异常数据
                             photoimg[j] += 256;
                         }

                     }
                     DefaultPutRet ret = upload(photoimg);
                     vo.setRet(ret);


                 }
                vo.setImgDescription(img);



               // map.put(ret.hash,photo);
                 vos.add(vo);
       /*         // 获取项目运行路径
                String pathRoot = request.getSession().getServletContext().getRealPath("")+"/images/";
                File dir= new File(pathRoot);
                if  (!dir .exists() && !dir .isDirectory())
                {
                    System.out.println("//不存在");
                    dir .mkdir();
                }
                // 生成uuid作为文件名称
                String uuid = UUID.randomUUID().toString().replaceAll("-", "");
                String path =uuid + ".png";
                // byte[] photoimg =
                // Base64.decode(photo);//此处不能用Base64.decode（）方法解密，我调试时用此方法每次解密出的数据都比原数据大
                // 所以用上面的函数进行解密，在网上直接拷贝的，花了好几个小时才找到这个错误（菜鸟不容易啊）
                System.out.println("图片的大小：" + photoimg.length);
                File file = new File(pathRoot + path);
                if (!file.exists()) {
                    file.createNewFile();
                }
                FileOutputStream out = new FileOutputStream(file);
                out.write(photoimg);
                out.flush();
                out.close()*/;


            }
        } catch (Exception e) {

            e.printStackTrace();
        }
       return vos;

    }
}