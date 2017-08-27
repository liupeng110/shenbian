//
//  RootObjectModel.h
//  shenbianapp
//
//  Created by 杨绍智 on 17/7/14.
//  Copyright © 2017年 杨绍智. All rights reserved.
//
#import "RootHeaderViewController.h"

#import <Foundation/Foundation.h>

@interface RootObjectModel : CHBaseViewCModel

@property(nonatomic,strong)RACCommand *loadPagedata;

@property(nonatomic,strong) NSArray *loadModels;

@end
