//
//  CHFocusModel.m
//  shenbianapp
//
//  Created by book on 2018/2/3.
//  Copyright © 2018年 helinkeji. All rights reserved.
//

#import "CHFocusModel.h"

@implementation CHFocusModel

@synthesize  loadPagedata = _loadPagedata;

-(RACCommand *)loadPagedata{
    
    if (!_loadPagedata) {
     
        _loadPagedata = [[RACCommand alloc]initWithSignalBlock:^RACSignal *(id input) {
            return [RACSignal createSignal:^RACDisposable *(id<RACSubscriber> subscriber) {
               
               RACSignal *signal = [CHNetWork loadDataWithParam:input withUrlString:@"/v1/my/concern/list.htm"];
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
