//
//  CHPublishServiceModel.m
//  shenbianapp
//
//  Created by book on 2017/10/15.
//  Copyright © 2017年 陈坚. All rights reserved.
//

#import "CHPublishServiceModel.h"

@implementation CHPublishServiceModel

@synthesize uploadComand = _uploadComand;

-(RACCommand *)uploadComand{
    
    if (!_uploadComand) {
        _uploadComand = [[RACCommand alloc]initWithSignalBlock:^RACSignal *(NSDictionary *param) {
            RACSignal *signal = [CHNetWork uploadServiceAndArticleWithParam:param];
                
            return signal;
        }];
    }
    
    
    return _uploadComand;
}

@end
