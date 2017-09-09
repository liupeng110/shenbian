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
                        
            
            
            return nil;
        }];
    }
    
    return _loadPagedata;
}


-(RACCommand *)loadTopData{
    
    if (_loadTopData == nil) {
        _loadTopData = [[RACCommand alloc]initWithSignalBlock:^RACSignal *(NSDictionary *param) {
           
            RACSignal *singal = [CHNetWork loadHomePageDataWithParam:param withUrlString:HomeTopData];
            
            [singal subscribeNext:^(id x) {
                
                self.topDataList = x;
                
            } error:^(NSError *error) {
                NSLog(@"error:%@",error);
                //模拟数据
                self.topDataList = @{
                                    @"categories":@[@"寄快递",@"洗车",@"家教",@"海报设计",@"找律师",@"搬家",@"美妆",@"结婚"],@"imgInfo":@[@{@"text":@"找服务",@"url":@""},@{@"text":@"找人",@"url":@"" },@{@"text":@"找活动",@"url":@"" },@{@"text":@"找工作",@"url":@"" },@{@"text":@"找租房",@"url":@"" },@{@"text":@"学技能",@"url":@"" },@{@"text":@"修手机、修电脑",@"url":@""},@{@"text":@"全部分类",@"url":@"" }],};
                
            }];
            
            return singal;
        }];
    }
    
    return _loadTopData;
}


-(RACCommand *)loadBottomData{


    if (_loadBottomData == nil) {
        
        _loadBottomData = [[RACCommand alloc]initWithSignalBlock:^RACSignal *(NSDictionary *param) {
            RACSignal *singal = [CHNetWork loadHomePageDataWithParam:param withUrlString:HomeBottomData];
            
            [singal subscribeNext:^(id x) {
                
                self.bottomDataList = x;
                
                
            } error:^(NSError *error) {
                NSLog(@"error:%@",error);
                //模拟数据
                self.bottomDataList = @{@"over_balance":@[@{@"cover_url":@"title",@"cover_url":@"title",},@{@"cover_url":@"title",@"cover_url":@"title",}],
                                    
                                    @"merchent":@[@{@"icon_url":@"",@"rating":@"4.8(122)",@"distance":@(300),@"merchent_name":@"永和打印店",@"content":@"大家好，世界就好",@"sold_out":@(980),@"tag_name":@"设计",@"":@"",},@{@"icon_url":@"",@"rating":@"4.8(122)",@"distance":@(300),@"merchent_name":@"嘉和一品店",@"content":@"大家好，世界就好，你来了就好",@"sold_out":@(980),@"tag_name":@"设计",@"":@"",}],
                                    };
                
            }];
            return singal;
        }];
            
    }
    return _loadBottomData;
}


@end
