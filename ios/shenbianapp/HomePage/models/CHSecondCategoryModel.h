//
//  CHSecondCategoryModel.h
//  shenbianapp
//
//  Created by book on 2017/12/27.
//  Copyright © 2017年 helinkeji. All rights reserved.
//

#import "CHBaseViewCModel.h"

@interface CHSecondCategoryModel : CHBaseViewCModel
@property(nonatomic,strong,readonly)RACCommand *getClassifyCommand;
@end
