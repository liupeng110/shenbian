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
            
            RACSignal *signal = [CHNetWork loadHomePageDataWithParam:input withUrlString:SendValidCode];
            [signal subscribeNext:^(id x) {
                
                if ([[x objectForKey:@"status"] intValue] == 0) {
                    
                    self.msgSessionId = [[x objectForKey:@"data"] objectForKey:@"smsSessionId"];
                } else {
                    UIAlertView *alert = [[UIAlertView alloc]init];
                    alert.title = @"发送提示";
                    alert.message =  [x objectForKey:@"success"];
                    [alert addButtonWithTitle:@"确定"];
                    [alert show];

                }
            } error:^(NSError *error) {
                
            }];
            
            return signal;
        }];
    }
    return _sendValidCode;
}


-(RACCommand *)loginCommand{
    
    if (_loginCommand == nil) {
        _loginCommand = [[RACCommand alloc]initWithSignalBlock:^RACSignal *(id input) {
            
            RACSignal *signal = [CHNetWork loadHomePageDataWithParam:input withUrlString:LoginVerify];
            [signal subscribeNext:^(id x) {
                if ([[x objectForKey:@"status"] intValue] == 0) {
                    
                }
            } error:^(NSError *error) {
                
            }];
            return signal;
        }];
    }
    return  _loginCommand;
}

@end
