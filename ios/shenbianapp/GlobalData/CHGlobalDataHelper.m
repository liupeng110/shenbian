//
//  CHGlobalDataHelper.m
//  shenbianapp
//
//  Created by book on 2017/10/28.
//  Copyright © 2017年 陈坚. All rights reserved.
//

#import "CHGlobalDataHelper.h"

@implementation CHGlobalDataHelper

+ (CHGlobalDataHelper*)shareInstance{
    
    static CHGlobalDataHelper *instance = nil;
    static dispatch_once_t onceToken ;
    dispatch_once(&onceToken, ^{
        instance = [[CHGlobalDataHelper alloc]init];
    });
    return instance;
}

@end
