//
//  CHPublishArticleModel.m
//  shenbianapp
//
//  Created by book on 2017/9/24.
//  Copyright © 2017年 陈坚. All rights reserved.
//

#import "CHPublishArticleModel.h"

@implementation CHPublishArticleModel
@synthesize uploadComand = _uploadComand;
@synthesize obtainToken = _obtainToken;

-(RACCommand *)obtainToken{
    
    if (!_obtainToken) {
        _obtainToken = [[RACCommand alloc]initWithSignalBlock:^RACSignal *(NSDictionary *input) {
            
            AFHTTPSessionManager *manager = [[AFHTTPSessionManager alloc]init];
            
            RACSignal *signal =  [manager rac_POST:[input objectForKey:@"urlString"] parameters:[input objectForKey:@"param"]];
            
            return signal;
        }];
    }
    return _obtainToken;
}

-(RACCommand *)uploadComand{
    
    if (!_uploadComand) {
        _uploadComand = [[RACCommand alloc]initWithSignalBlock:^RACSignal *(id input) {
            RACSignal *signal = [RACSignal createSignal:^RACDisposable *(id<RACSubscriber> subscriber) {
                
                return [RACDisposable disposableWithBlock:^{
                    
                }];
            }];
            
            return signal;
        }];
    }
    
    
    return _uploadComand;
}

@end
