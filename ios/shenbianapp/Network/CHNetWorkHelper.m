//
//  CHNetWorkHelper.m
//  shenbianapp
//
//  Created by book on 2017/8/27.
//  Copyright © 2017年 陈坚. All rights reserved.
//

#import "CHNetWorkHelper.h"
@interface CHNetWorkHelper ()

@property(nonatomic,strong)AFHTTPSessionManager *sessionManager;

@end

@implementation CHNetWorkHelper
+(CHNetWorkHelper*)shareInstance{
    
    static CHNetWorkHelper *instance = nil;
    static dispatch_once_t onceToken ;
    dispatch_once(&onceToken, ^{
        instance = [[CHNetWorkHelper alloc]init];
    });
    return instance;
}

-(instancetype)init{
    if (self = [super init]) {
        self.sessionManager = [[AFHTTPSessionManager alloc]init];
        
        AFSecurityPolicy *security = [AFSecurityPolicy policyWithPinningMode:AFSSLPinningModeNone];
        
        [security setValidatesDomainName:NO];
        
        security.allowInvalidCertificates = YES;
        //
        self.sessionManager.securityPolicy = security;

        self.sessionManager.responseSerializer.acceptableContentTypes = [self.sessionManager.responseSerializer.acceptableContentTypes setByAddingObject:@"text/html"];
        //
        //    AFHTTPRequestSerializer *requestSerializer = [AFHTTPRequestSerializer serializer];
        //    [requestSerializer setValue:@"*/*" forHTTPHeaderField:@"accept"];
        //    [requestSerializer setValue:@"*/*; charset=utf-8" forHTTPHeaderField:@"Content-type"];
        //    [requestSerializer setValue:@"Keep-Alive" forHTTPHeaderField:@"connection"];
        //
        //    manager.requestSerializer = requestSerializer;
    }
    return self;
}

-(RACSignal*)postRequestWithParam:(NSDictionary*)param withUrlString:(NSString*)urlString{

    RACSignal *signal =  [self.sessionManager rac_POST:urlString parameters:param];
    
    signal = [signal flattenMap:^RACStream *(RACTuple *tuple) {
        
        RACTupleUnpack(NSDictionary *json) = tuple;
        
        return [RACSignal createSignal:^RACDisposable *(id<RACSubscriber> subscriber) {
            
            if (json != nil) {
                [subscriber sendNext:json];
                [subscriber sendCompleted];
            } else {
                NSError *error = [NSError errorWithDomain:@"json 出错" code:10000 userInfo:nil];
                [subscriber sendError:error];
                [subscriber sendCompleted];
                
            }
            return  nil;
        }];
    }];
    
    [signal subscribeError:^(NSError *error) {
      
        NSLog(@"服务器错误：%@",error);
    }];
    
    return signal;

}

-(RACSignal*)loadHomePageDataWithParam:(NSDictionary *)param withUrlString:(NSString *)urlString{
    
    RACSignal *signal = [self postRequestWithParam:param withUrlString:urlString];
    
    return signal;
}

-(RACSignal *)uploadServiceAndArticleWithParam:(NSDictionary *)param{

    NSString *urlString = [NSString stringWithFormat:@"%@",addService];
    RACSignal *singal = [self postRequestWithParam:param withUrlString:urlString];
    
    return singal;
}

@end
