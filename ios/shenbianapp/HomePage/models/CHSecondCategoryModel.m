//
//  CHSecondCategoryModel.m
//  shenbianapp
//
//  Created by book on 2017/12/27.
//  Copyright © 2017年 helinkeji. All rights reserved.
//

#import "CHSecondCategoryModel.h"

@interface CHSecondCategoryModel()
@property(nonatomic,strong)RACCommand *getClassifyCommand;
@end

@implementation CHSecondCategoryModel

@synthesize loadPagedata = _loadPagedata;

-(RACCommand *)loadPagedata{
    if (_loadPagedata == nil) {
        _loadPagedata = [[RACCommand alloc]initWithSignalBlock:^RACSignal *(id input) {
            
            RACSignal *signal = [CHNetWork loadDataWithParam:input withUrlString:@"/v1/classification/service/query.htm"];
            
            return signal;
            
        }];
    }
    return _loadPagedata;
}

-(RACCommand *)getClassifyCommand{
    
    if (_getClassifyCommand == nil) {
        
        _getClassifyCommand = [[RACCommand alloc]initWithSignalBlock:^RACSignal *(id input) {
            
            return [RACSignal createSignal:^RACDisposable *(id<RACSubscriber> subscriber) {
                
                RACSignal *signal = [CHNetWork loadDataWithParam:input withUrlString:@"/v1/all/classification/query.htm"];
                
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
    return _getClassifyCommand;
}

@end
