//
//  CHServiceDetailModel.m
//  shenbianapp
//
//  Created by book on 2017/12/11.
//  Copyright © 2017 helinkeji. All rights reserved.
//

#import "CHServiceDetailModel.h"

@implementation CHServiceDetailModel
@synthesize loadPagedata = _loadPagedata;
-(RACCommand *)loadPagedata{
    
    if (_loadPagedata == nil) {
        _loadPagedata = [[RACCommand alloc]initWithSignalBlock:^RACSignal *(id input) {
            
            RACSignal *signal = [CHNetWork loadDataWithParam:input withUrlString:@"/v1/service/details.htm"];
            return [RACSignal createSignal:^RACDisposable *(id<RACSubscriber> subscriber) {
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
