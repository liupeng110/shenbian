//
//  CHNetWorkHelper.h
//  shenbianapp
//
//  Created by book on 2017/8/27.
//  Copyright © 2017 . All rights reserved.
//

#import <Foundation/Foundation.h>

#define CHNetWork [CHNetWorkHelper shareInstance]

#define HomeTopData @"/v1/index/query.htm"//首页顶部数据
#define HomeBottomData @"/v1/index/service/query.htm" //首页底部数据
#define SendValidCode @"/v1/login/send.htm"//发送验证码
#define LoginVerify @"/v1/login/verifyLogin.htm"//登录验证
#define addService @"/v1/service/addService.htm"//添加服务和文章
#define DisscoverUrl @"/v1/service/find/query.htm"

@interface CHNetWorkHelper : NSObject
+(CHNetWorkHelper*)shareInstance;
-(NSString*)getHostUrlString;
@property (nonatomic, strong, readonly) AFHTTPRequestSerializer* requestSerializer;
@property (nonatomic, strong, readonly) AFHTTPSessionManager* jsonHTTPSClient;


-(RACSignal*)loadDataWithParam:(NSDictionary *)param withUrlString:(NSString *)urlString;

-(RACSignal*)uploadServiceAndArticleWithParam:(NSDictionary*)param;

@end
