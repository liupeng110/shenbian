//
//  CHGlobalDataHelper.h
//  shenbianapp
//
//  Created by book on 2017/10/28.
//  Copyright © 2017 . All rights reserved.
//

#import <Foundation/Foundation.h>

#import "CHMessageModel.h"

typedef void(^CaculateReuslt)(NSString *distance);
typedef void(^GetCurrentLocation)(CLLocation *location);
#define GlobalData [CHGlobalDataHelper shareInstance]

@interface CHGlobalDataHelper : NSObject

+(CHGlobalDataHelper*)shareInstance;

@property(nonatomic,strong) NSArray<CHMessageModel*> *messagelist;

- (void)distacewithLocation:(NSString*)locationStr result:(CaculateReuslt)caculateResult;

- (void)getCurrentLocation:(GetCurrentLocation)complted;
@end
