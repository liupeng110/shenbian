//
//  RootObjectModel.m
//  shenbianapp
//
//  Created by 杨绍智 on 17/7/14.
//  Copyright © 2017年 杨绍智. All rights reserved.
//

#import "RootObjectModel.h"

@interface RootObjectModel ()


@end

@implementation RootObjectModel

@synthesize loadPagedata = _loadPagedata;//初始化父类属性需要这样写
@synthesize loadModels = _loadModels;

-(RACCommand *)loadPagedata{
    if (_loadPagedata == nil) {
        
        _loadPagedata = [[RACCommand alloc]initWithSignalBlock:^RACSignal *(NSDictionary *param) {
            
            RACSignal *singal = [CHNetWork loadHomePageDataWithParam:param];
            
            [singal subscribeNext:^(id x) {
                
                
                self.loadModels = x;
                
            } error:^(NSError *error) {
                NSLog(@"error:%@",error);
                //模拟数据
                self.loadModels = @[@"寄快递",@"洗车",@"家教",@"海报设计",@"找律师",@"搬家",@"美妆",@"结婚"];
            }];
            
            return singal;
        }];
    }
    
    return _loadPagedata;
}

@end
