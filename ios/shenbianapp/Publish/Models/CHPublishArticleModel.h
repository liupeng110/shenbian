//
//  CHPublishArticleModel.h
//  shenbianapp
//
//  Created by book on 2017/9/24.
//  Copyright © 2017 . All rights reserved.
//

#import "CHBaseViewCModel.h"

@interface CHPublishArticleModel : CHBaseViewCModel

@property(nonatomic,strong,readonly)RACCommand *uploadComand;

@end
