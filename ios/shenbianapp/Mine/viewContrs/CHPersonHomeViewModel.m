//
//  CHMyArticleAndServiceViewModel.m
//  shenbianapp
//
//  Created by book on 2017/9/24.
//  Copyright © 2017 . All rights reserved.
//

#import "CHPersonHomeViewModel.h"
@interface CHPersonHomeViewModel()

@end;

@implementation CHPersonHomeViewModel
@synthesize focusCommand = _focusCommand;
@synthesize loadPagedata = _loadPagedata;
-(RACCommand *)focusCommand{
    
    if ( _focusCommand == nil) {
        _focusCommand = [[RACCommand alloc]initWithSignalBlock:^RACSignal *(id input) {
            
            return [CHNetWork loadDataWithParam:input withUrlString:@"/v1/user/concern.htm"];
            
        }];
    }
    return _focusCommand;
}

-(RACCommand *)loadPagedata{
    if (_loadPagedata == nil) {
        
        _loadPagedata = [[RACCommand alloc]initWithSignalBlock:^RACSignal *(NSDictionary *param) {
            
            RACSignal *singal = [CHNetWork loadDataWithParam:param withUrlString:@"/v1/my/all/query.htm"];
            
            return [RACSignal createSignal:^RACDisposable *(id<RACSubscriber> subscriber) {
                
                [singal subscribeNext:^(id x) {
                    [subscriber sendNext:x];
                    [subscriber sendCompleted];
                    
                } error:^(NSError *error) {
                    NSLog(@"error:%@",error);
                    [subscriber sendError:error];
                    [subscriber sendCompleted];
                    //模拟数据
                }];
                
                return  nil;
            }];
            
        }];
        
    }
    return _loadPagedata;
    
}

@end
