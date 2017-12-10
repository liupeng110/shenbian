//
//  CHDiscoverModel.m
//  shenbianapp
//
//  Created by book on 2017/10/29.
//  Copyright © 2017年 helinkeji. All rights reserved.
//

#import "CHDiscoverModel.h"

@implementation CHDiscoverModel
@synthesize loadPagedata = _loadPagedata;

-(RACCommand *)loadPagedata{
    if (_loadPagedata == nil) {
        _loadPagedata = [[RACCommand alloc]initWithSignalBlock:^RACSignal *(NSDictionary* input) {
           
            return [RACSignal createSignal:^RACDisposable *(id<RACSubscriber> subscriber) {
                
                RACSignal *signal = [CHNetWork loadDataWithParam:input withUrlString:DisscoverUrl];

                [signal subscribeNext:^(id x) {
                    [subscriber sendNext:x];
                    [subscriber sendCompleted];
                } error:^(NSError *error) {
                    [subscriber sendError:error];
                    [subscriber sendCompleted];
                }];
                return nil;
            }];
        }];
    }
    
    return _loadPagedata;
    
}

@end
