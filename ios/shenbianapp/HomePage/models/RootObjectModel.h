//
//  RootObjectModel.h
//  shenbianapp
//
//  Created by   on 17/7/14.
//  Copyright © 2017  . All rights reserved.
//
#import "RootHeaderViewController.h"

#import <Foundation/Foundation.h>

@interface RootObjectModel : CHBaseViewCModel

@property(nonatomic,strong) RACCommand *loadTopData;
@property(nonatomic,strong) RACCommand *loadBottomData;

@property(nonatomic,strong) NSDictionary *topDataList;
@property(nonatomic,strong) NSDictionary *bottomDataList;
@end
