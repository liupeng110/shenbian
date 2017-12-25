//
//  CHSubmitOrderViewController.h
//  shenbianapp
//
//  Created by book on 2017/11/6.
//  Copyright © 2017 helinkeji. All rights reserved.
//

#import "CHBaseNavgationViewController.h"
#import "CHOrderModel.h"
@interface CHSubmitOrderViewController : CHBaseNavgationViewController
@property(nonatomic,strong)NSArray<CHOrderModel*> *orderModelList;
@end
