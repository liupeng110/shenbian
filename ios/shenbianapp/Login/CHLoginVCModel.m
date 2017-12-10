//
//  CHLoginVCModel.m
//  shenbianapp
//
//  Created by book on 2017/9/9.
//  Copyright © 2017年 陈坚. All rights reserved.
//

#import "CHLoginVCModel.h"


@implementation CHLoginVCModel


-(RACCommand *)sendValidCode{
    
    if (_sendValidCode == nil) {
        _sendValidCode = [[RACCommand alloc]initWithSignalBlock:^RACSignal *(NSDictionary *input) {
            
            RACSignal *signal = [CHNetWork loadDataWithParam:input withUrlString:SendValidCode];
            
            return [RACSignal createSignal:^RACDisposable *(id<RACSubscriber> subscriber) {
                NSLog(@"haha我来了");
                [signal subscribeNext:^(id x) {
                    NSInteger status = [[x objectForKey:@"status"] integerValue];
                    if (status == 0) {
                        self.msgSessionId = [[x objectForKey:@"data"] objectForKey:@"smsSessionId"];

                        [subscriber sendNext:x];
                        [subscriber sendCompleted];
                    } else {
                        NSError *error = [NSError errorWithDomain:[x objectForKey:@"error"] code:100 userInfo:nil];
                        [subscriber sendError:error];
                        [subscriber sendCompleted];
                        NSLog(@"错误%@",error);

                    }
                } error:^(NSError *error) {
                    NSLog(@"错误%@",error);
                    [subscriber sendError:error];
                    [subscriber sendCompleted];
                } completed:^{
                    
                }];
                
                return nil;
            }];
                        
        }];
    }
    return _sendValidCode;
}

-(RACCommand *)loginCommand{
    
    if (_loginCommand == nil) {
        _loginCommand = [[RACCommand alloc]initWithSignalBlock:^RACSignal *(id input) {
            
            RACSignal *signal = [CHNetWork loadDataWithParam:input withUrlString:LoginVerify];
            
            return [RACSignal createSignal:^RACDisposable *(id<RACSubscriber> subscriber) {
                NSLog(@"我要登录....");
                [signal subscribeNext:^(id x) {
                    if ([[x objectForKey:@"status"] intValue] == 0) {
                        NSUserDefaults *ud = [NSUserDefaults standardUserDefaults];
                        [ud setBool:YES forKey:@"login"];
                        NSString *token = [x objectForKey:@"token"];
                        [ud setObject:token forKey:@"server_token"];
                        [ud synchronize];
                        [subscriber sendNext:x];
                        [subscriber sendCompleted];
                    } else {
                        NSLog(@"error:%@",[x objectForKey:@"error"]);
                        [subscriber sendError:[NSError errorWithDomain:[x objectForKey:@"error"] code:100 userInfo:nil]];
                        [subscriber sendCompleted];
                    }
                } error:^(NSError *error) {
                    [subscriber sendError:error];
                    [subscriber sendCompleted];
                } completed:^{
                    
                }];
                return nil;
            }];
        }];
    }
    return  _loginCommand;
}

@end
