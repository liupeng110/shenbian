//
//  CHMyOrderViewModel.m
//  shenbianapp
//
//  Created by book on 2017/12/28.
//  Copyright © 2017年 helinkeji. All rights reserved.
//

#import "CHMyOrderViewModel.h"

@implementation CHMyOrderViewModel
//v1/order/query.htm
@synthesize loadPagedata = _loadPagedata;

-(RACCommand *)loadPagedata{
    
    if (_loadPagedata == nil) {
        _loadPagedata = [[RACCommand alloc]initWithSignalBlock:^RACSignal *(id input) {
            RACSignal *signal = [CHNetWork loadDataWithParam:input withUrlString:@"/v1/order/query.htm"];
            return signal;
        }];
    }
    return _loadPagedata;
}
@end
