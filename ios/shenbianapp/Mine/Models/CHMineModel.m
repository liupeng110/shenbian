//
//  CHMineModel.m
//  shenbianapp
//
//  Created by book on 2017/11/2.
//  Copyright © 2017 helinkeji. All rights reserved.
//

#import "CHMineModel.h"

@implementation CHMineModel
@synthesize loadPagedata = _loadPagedata;

-(RACCommand *)loadPagedata{
    
    if (_loadPagedata == nil) {
        _loadPagedata = [[RACCommand alloc]initWithSignalBlock:^RACSignal *(id input) {
            
            return [RACSignal createSignal:^RACDisposable *(id<RACSubscriber> subscriber) {
                
              RACSignal *signal =  [CHNetWork loadDataWithParam:input withUrlString:@"/v1/my/query.htm"];
                [signal subscribeNext:^(id x) {
                    [subscriber sendNext:x];
                    [subscriber sendCompleted];
                } error:^(NSError *error) {
                    [subscriber sendError:error];
                    [subscriber sendCompleted];
                }];
                return [RACDisposable disposableWithBlock:^{
                    [signal rac_willDeallocSignal];
                }];
            }];
        }];
    }
    return _loadPagedata;
}

@end
