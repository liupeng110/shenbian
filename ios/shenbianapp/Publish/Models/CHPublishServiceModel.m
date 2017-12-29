//
//  CHPublishServiceModel.m
//  shenbianapp
//
//  Created by book on 2017/10/15.
//  Copyright © 2017 . All rights reserved.
//

#import "CHPublishServiceModel.h"

@interface CHPublishServiceModel()
@property(nonatomic,strong)RACCommand *getTokenComand;

@property(nonatomic,strong)RACCommand *getClassifyCommand;

@end

@implementation CHPublishServiceModel

@synthesize uploadComand = _uploadComand;

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

-(RACCommand *)getTokenComand{
    
    if (!_getTokenComand) {
        _getTokenComand = [[RACCommand alloc]initWithSignalBlock:^RACSignal *(NSDictionary *param) {
            
            return [RACSignal createSignal:^RACDisposable *(id<RACSubscriber> subscriber) {
                RACSignal *signal = [CHNetWork loadDataWithParam:param withUrlString:@"/v1/service/materialUploadToken.htm"];
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
    
    return _getTokenComand;
    
}

-(RACCommand *)uploadComand{
    
    if (!_uploadComand) {
        _uploadComand = [[RACCommand alloc]initWithSignalBlock:^RACSignal *(NSDictionary *param) {
            return [RACSignal createSignal:^RACDisposable *(id<RACSubscriber> subscriber) {
                RACSignal *signal = [CHNetWork loadDataWithParam:param withUrlString:@"/v1/service/addService.htm"];
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
    
    return _uploadComand;
}

@end
