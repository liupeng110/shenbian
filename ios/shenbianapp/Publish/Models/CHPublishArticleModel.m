//
//  CHPublishArticleModel.m
//  shenbianapp
//
//  Created by book on 2017/9/24.
//  Copyright © 2017 . All rights reserved.
//

#import "CHPublishArticleModel.h"

@implementation CHPublishArticleModel
@synthesize uploadComand = _uploadComand;

-(RACCommand *)uploadComand{
    
    if (!_uploadComand) {
        _uploadComand = [[RACCommand alloc]initWithSignalBlock:^RACSignal *(NSDictionary *param) {
            RACSignal *signal = [RACSignal createSignal:^RACDisposable *(id<RACSubscriber> subscriber) {
                
                [CHNetWork uploadServiceAndArticleWithParam:param];
                
                return [RACDisposable disposableWithBlock:^{
                    
                }];
            }];
            
            return signal;
        }];
    }
    
    
    return _uploadComand;
}

@end
