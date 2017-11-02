//
//  CHNetWorkHelper.h
//  shenbianapp
//
//  Created by book on 2017/8/27.
//  Copyright © 2017年 陈坚. All rights reserved.
//

#import <Foundation/Foundation.h>

#define CHNetWork [CHNetWorkHelper shareInstance]

#define HomeTopData @"https://www.helinkeji.cn/v1/index/query.htm"//首页顶部数据
#define HomeBottomData @"https://www.helinkeji.cn/v1/index/service/query.htm" //首页底部数据

#define SendValidCode @"https://www.helinkeji.cn/v1/login/send.htm"//发送验证码
#define LoginVerify @"https://www.helinkeji.cn/v1/login/verifyLogin.htm"//登录验证
#define addService @"https://www.helinkeji.cn/v1/service/addService.htm"//添加服务和文章


@interface CHNetWorkHelper : NSObject
+(CHNetWorkHelper*)shareInstance;

@property (nonatomic, strong, readonly) AFHTTPRequestSerializer* requestSerializer;
@property (nonatomic, strong, readonly) AFHTTPSessionManager* jsonHTTPSClient;

/*加载首页数据*/
-(RACSignal*)loadHomePageDataWithParam:(NSDictionary *)param withUrlString:(NSString *)urlString;

-(RACSignal*)uploadServiceAndArticleWithParam:(NSDictionary*)param;

@end
