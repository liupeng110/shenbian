//
//  CHServiceDetailModel.m
//  shenbianapp
//
//  Created by book on 2017/12/11.
//  Copyright © 2017 helinkeji. All rights reserved.
//

#import "CHServiceDetailModel.h"

@interface CHServiceDetailModel()
@property(nonatomic,strong)RACCommand *addToCartCommand;
@end

@implementation CHServiceDetailModel
@synthesize loadPagedata = _loadPagedata;
@synthesize focusCommand = _focusCommand;
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

-(RACCommand *)addToCartCommand{
    if ( _addToCartCommand == nil) {
        _addToCartCommand = [[RACCommand alloc]initWithSignalBlock:^RACSignal *(id input) {
            RACSignal *signal = [CHNetWork loadDataWithParam:input withUrlString:@"/v1/user/cart/add.htm"];
            return signal;
        }];
    }
    return _addToCartCommand;
}

-(RACCommand *)focusCommand{
    if ( _focusCommand == nil) {
        _focusCommand = [[RACCommand alloc]initWithSignalBlock:^RACSignal *(id input) {

            return [CHNetWork loadDataWithParam:input withUrlString:@"/v1/collect/addOrRemove.htm"];
            
        }];
    }
    return _focusCommand;
}


@end
