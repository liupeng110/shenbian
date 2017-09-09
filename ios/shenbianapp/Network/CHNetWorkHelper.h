//
//  CHNetWorkHelper.h
//  shenbianapp
//
//  Created by book on 2017/8/27.
//  Copyright © 2017年 陈坚. All rights reserved.
//

#import <Foundation/Foundation.h>

#define CHNetWork [CHNetWorkHelper shareInstance]

#define HomeTopData @"http://60.205.220.80:8081/v1/index/query.htm"//首页顶部数据
#define HomeBottomData @"http://60.205.220.80:8081/v1/service/query.htm" //首页底部数据

@interface CHNetWorkHelper : NSObject
+(CHNetWorkHelper*)shareInstance;

@property (nonatomic, strong, readonly) AFHTTPRequestSerializer* requestSerializer;
@property (nonatomic, strong, readonly) AFHTTPSessionManager* jsonHTTPSClient;

/*加载首页数据*/
-(RACSignal*)loadHomePageDataWithParam:(NSDictionary *)param withUrlString:(NSString *)urlString;

@end
