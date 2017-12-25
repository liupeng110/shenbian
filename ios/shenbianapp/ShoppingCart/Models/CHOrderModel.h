//
//  CHOrderModel.h
//  shenbianapp
//
//  Created by book on 2017/12/11.
//  Copyright © 2017 helinkeji. All rights reserved.
//

#import "CHBaseViewCModel.h"

@interface CHOrderModel : CHBaseViewCModel

@property(nonatomic,copy)NSString *serviceTitle;
@property(nonatomic,copy)NSString *servicePrice;
@property(nonatomic,assign)NSString *serviceAmount;
@end
