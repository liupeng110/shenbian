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
            [signal doNext:^(id x) {
                
                if ([[x objectForKey:@"status"] intValue] == 0) {
                    
                    self.msgSessionId = [[x objectForKey:@"data"] objectForKey:@"smsSessionId"];
                    
                } else {
                    UIAlertView *alert = [[UIAlertView alloc]init];
                    alert.title = @"发送提示";
                    alert.message =  [x objectForKey:@"error"];
                    [alert addButtonWithTitle:@"确定"];
                    [alert show];
                    
                }
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
            [signal doNext:^(id x) {
                if ([[x objectForKey:@"status"] intValue] == 0) {
                    NSUserDefaults *ud = [NSUserDefaults standardUserDefaults];
                    [ud setBool:YES forKey:@"login"];
                    NSString *token = [x objectForKey:@"token"];
                    [ud setObject:token forKey:@"server_token"];
                    [ud synchronize];
                }
            }];
            return signal;
        }];
    }
    return  _loginCommand;
}

@end
