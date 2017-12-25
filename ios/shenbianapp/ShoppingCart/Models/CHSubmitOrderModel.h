//
//  CHSubmitOrderModel.h
//  shenbianapp
//
//  Created by book on 2017/12/18.
//  Copyright © 2017 helinkeji. All rights reserved.
//

#import "CHBaseViewCModel.h"

@interface CHSubmitOrderModel : CHBaseViewCModel
@property(nonatomic,strong) RACCommand *addOrderCommand;
@property(nonatomic,strong)RACCommand *payCommand;
@end
