//
//  CHShopingModel.h
//  shenbianapp
//
//  Created by book on 2017/12/19.
//  Copyright © 2017 helinkeji. All rights reserved.
//

#import "CHBaseViewCModel.h"
#import "CHOrderModel.h"
@interface CHShopingModel : CHBaseViewCModel
@property(nonatomic,copy)NSString *storeName;
@property(nonatomic,copy)NSString *iconUrl;
@property(nonatomic,copy)NSMutableArray<CHOrderModel*> *orderList;
@end
