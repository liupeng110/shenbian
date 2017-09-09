//
//  CHNetWorkHelper.m
//  shenbianapp
//
//  Created by book on 2017/8/27.
//  Copyright © 2017年 陈坚. All rights reserved.
//

#import "CHNetWorkHelper.h"

@implementation CHNetWorkHelper
+(CHNetWorkHelper*)shareInstance{
    
    static CHNetWorkHelper *instance = nil;
    static dispatch_once_t onceToken ;
    dispatch_once(&onceToken, ^{
        instance = [[CHNetWorkHelper alloc]init];
    });
    return instance;
}

-(RACSignal*)loadHomePageDataWithParam:(NSDictionary *)param withUrlString:(NSString *)urlString{
    
    AFHTTPSessionManager *manager = [[AFHTTPSessionManager alloc]init];
    
    AFSecurityPolicy *security = [AFSecurityPolicy policyWithPinningMode:AFSSLPinningModeNone];
    
    [security setValidatesDomainName:NO];
    
    security.allowInvalidCertificates = YES;
//
    manager.securityPolicy = security;
    manager.responseSerializer.acceptableContentTypes = [manager.responseSerializer.acceptableContentTypes setByAddingObject:@"text/html"];
//    
//    AFHTTPRequestSerializer *requestSerializer = [AFHTTPRequestSerializer serializer];
//    [requestSerializer setValue:@"*/*" forHTTPHeaderField:@"accept"];
//    [requestSerializer setValue:@"*/*; charset=utf-8" forHTTPHeaderField:@"Content-type"];
//    [requestSerializer setValue:@"Keep-Alive" forHTTPHeaderField:@"connection"];
//    
//    manager.requestSerializer = requestSerializer;
    
    
    RACSignal *signal = [manager rac_POST:urlString parameters:param];
    
    signal = [signal flattenMap:^RACStream *(RACTuple *tuple) {
        RACTupleUnpack(NSDictionary *json) = tuple;
        return [RACSignal createSignal:^RACDisposable *(id<RACSubscriber> subscriber) {
            
            if (json != nil) {
                [subscriber sendNext:json];
                [subscriber sendCompleted];
            } else {
                NSError *error = [NSError errorWithDomain:@"json 出错" code:10000 userInfo:nil];
                [subscriber sendError:error];
            }
            return nil;
        }];
    }];
    
    return signal;
}

@end
