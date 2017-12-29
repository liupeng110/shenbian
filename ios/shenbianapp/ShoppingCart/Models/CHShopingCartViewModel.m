//
//  CHShopingCartViewModel.m
//  shenbianapp
//
//  Created by book on 2017/12/28.
//  Copyright © 2017年 helinkeji. All rights reserved.
//

#import "CHShopingCartViewModel.h"

@implementation CHShopingCartViewModel
@synthesize loadPagedata = _loadPagedata;

-(RACCommand *)loadPagedata{
    
    if (_loadPagedata == nil) {
        _loadPagedata = [[RACCommand alloc]initWithSignalBlock:^RACSignal *(id input) {
            RACSignal *signal = [CHNetWork loadDataWithParam:input withUrlString:@"/v1/user/cart/query.htm"];
            return signal;
        }];
    }
    return _loadPagedata;
}

@end
