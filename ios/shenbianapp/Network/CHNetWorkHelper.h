//
//  CHNetWorkHelper.h
//  shenbianapp
//
//  Created by book on 2017/8/27.
//  Copyright © 2017年 陈坚. All rights reserved.
//

#import <Foundation/Foundation.h>

#define CHNetWork [CHNetWorkHelper shareInstance]

#define DomainURL @"https://www.yixiang.com"

@interface CHNetWorkHelper : NSObject
+(CHNetWorkHelper*)shareInstance;

@property (nonatomic, strong, readonly) AFHTTPRequestSerializer* requestSerializer;
@property (nonatomic, strong, readonly) AFHTTPSessionManager* jsonHTTPSClient;

/*加载首页数据*/
- (RACSignal*)loadHomePageDataWithParam:(NSDictionary*)param;

@end
