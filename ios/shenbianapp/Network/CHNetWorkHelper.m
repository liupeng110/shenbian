//
//  CHNetWorkHelper.m
//  shenbianapp
//
//  Created by book on 2017/8/27.
//  Copyright © 2017 . All rights reserved.
//

#define HOST_URL @"https://www.helinkeji.cn"



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

-(NSString *)getHostUrlString{
    
    return @"https://www.helinkeji.cn";
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
    
    return [RACSignal createSignal:^RACDisposable *(id<RACSubscriber> subscriber) {
        
        
        
       
        NSURLSessionDataTask *task =  [self.sessionManager POST:urlString parameters:param progress:^(NSProgress* uploadProgress){
            
        } success:^(NSURLSessionDataTask *task, id responseObject) {
            if (responseObject != nil) {
                [subscriber sendNext:responseObject];
                [subscriber sendCompleted];
            } else {
                NSError *error = [NSError errorWithDomain:@"服务器错误 出错" code:10000 userInfo:nil];
                [subscriber sendError:error];
                [subscriber sendCompleted];
                
            }
        } failure:^(NSURLSessionDataTask *task, NSError *error) {
            [subscriber sendError:error];
            [subscriber sendCompleted];
        }];
        [task resume];
        
        return [RACDisposable disposableWithBlock:^{
            [task cancel];
        }];
    }] ;
    
}

-(RACSignal*)loadDataWithParam:(NSDictionary *)param withUrlString:(NSString *)urlString{
    
    NSString *resultURLString = [NSString stringWithFormat:@"%@%@",HOST_URL,urlString];
    RACSignal *signal = [self postRequestWithParam:param withUrlString:resultURLString];

    return signal;
}

-(RACSignal *)uploadServiceAndArticleWithParam:(NSDictionary *)param{
    
    NSString *urlString = [NSString stringWithFormat:@"%@%@",HOST_URL,addService];
    RACSignal *singal = [self postRequestWithParam:param withUrlString:urlString];
    
    return singal;
}

@end
