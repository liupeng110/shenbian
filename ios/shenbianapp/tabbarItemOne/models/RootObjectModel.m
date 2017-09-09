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
                self.loadModels = @{
                                    @"quik_search":@[@"寄快递",@"洗车",@"家教",@"海报设计",@"找律师",@"搬家",@"美妆",@"结婚"],@"category_item":@[@{@"item_name":@"找服务",@"iconimage_url":@""},@{@"item_name":@"找人",@"iconimage_url":@"" },@{@"item_name":@"找活动",@"iconimage_url":@"" },@{@"item_name":@"找工作",@"iconimage_url":@"" },@{@"item_name":@"找租房",@"iconimage_url":@"" },@{@"item_name":@"学技能",@"iconimage_url":@"" },@{@"item_name":@"修手机、修电脑",@"iconimage_url":@""},@{@"item_name":@"全部分类",@"iconimage_url":@"" }],
                                    
                                    @"over_balance":@[@{@"cover_url":@"title",@"cover_url":@"title",},@{@"cover_url":@"title",@"cover_url":@"title",}],
                                    
                                    @"merchent":@[@{@"icon_url":@"",@"rating":@"4.8(122)",@"distance":@(300),@"merchent_name":@"永和打印店",@"content":@"大家好，世界就好",@"sold_out":@(980),@"tag_name":@"设计",@"":@"",},@{@"icon_url":@"",@"rating":@"4.8(122)",@"distance":@(300),@"merchent_name":@"嘉和一品店",@"content":@"大家好，世界就好，你来了就好",@"sold_out":@(980),@"tag_name":@"设计",@"":@"",}],
                                    
                                    };
                
            }];
            
            return singal;
        }];
    }
    
    return _loadPagedata;
}

@end
