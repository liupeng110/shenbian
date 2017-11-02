//
//  CHGlobalDataHelper.h
//  shenbianapp
//
//  Created by book on 2017/10/28.
//  Copyright © 2017年 陈坚. All rights reserved.
//

#import <Foundation/Foundation.h>

#import "CHMessageModel.h"


#define GlobalData [CHGlobalDataHelper shareInstance]


@interface CHGlobalDataHelper : NSObject

+(CHGlobalDataHelper*)shareInstance;

@property(nonatomic,strong) NSArray<CHMessageModel*> *messagelist;

@end
