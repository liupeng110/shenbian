//
//  CHSubmitOrderModel.m
//  shenbianapp
//
//  Created by book on 2017/12/18.
//  Copyright © 2017 helinkeji. All rights reserved.
//

#import "CHSubmitOrderModel.h"

@implementation CHSubmitOrderModel

-(RACCommand *)addOrderCommand{
    
    if (_addOrderCommand == nil) {
        _addOrderCommand = [[RACCommand alloc]initWithSignalBlock:^RACSignal *(id input) {
            
            return [RACSignal createSignal:^RACDisposable *(id<RACSubscriber> subscriber) {
                
                RACSignal *signal = [CHNetWork loadDataWithParam:input withUrlString:@"/v1/order/add.htm"];
                
                [signal subscribeNext:^(id x) {
                    [subscriber sendNext:x];
                    [subscriber sendCompleted];
                } error:^(NSError *error) {
                    [subscriber sendError:error];
                    [subscriber sendCompleted];
                    NSLog(@"add order error%@",error);
                }];
                return nil;
            }];
        }];
    }
    
    return _addOrderCommand;
}

-(RACCommand *)payCommand{
    
    if (_payCommand == nil) {
        _payCommand = [[RACCommand alloc]initWithSignalBlock:^RACSignal *(id input) {
            
            return [RACSignal createSignal:^RACDisposable *(id<RACSubscriber> subscriber) {
                
                RACSignal *signal = [CHNetWork loadDataWithParam:input withUrlString:@"/v1/wxpay/unifiedOrder.htm"];
                
                [signal subscribeNext:^(id x) {
                    [subscriber sendNext:x];
                    [subscriber sendCompleted];
                } error:^(NSError *error) {
                    [subscriber sendError:error];
                    [subscriber sendCompleted];
                    NSLog(@"pay error%@",error);
                }];
                return nil;
            }];
        }];
    }
    return _payCommand;
}

@end
