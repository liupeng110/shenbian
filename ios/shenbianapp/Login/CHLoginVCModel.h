//
//  CHLoginVCModel.h
//  shenbianapp
//
//  Created by book on 2017/9/9.
//  Copyright © 2017 . All rights reserved.
//

#import "CHBaseViewCModel.h"

@interface CHLoginVCModel : CHBaseViewCModel
@property(nonatomic,strong) RACCommand *sendValidCode;
@property(nonatomic,strong) RACCommand *loginCommand;

@property(nonatomic,copy) NSString *msgSessionId;

@end
