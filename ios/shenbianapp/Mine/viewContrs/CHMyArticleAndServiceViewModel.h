//
//  CHMyArticleAndServiceViewModel.h
//  shenbianapp
//
//  Created by book on 2017/9/24.
//  Copyright © 2017年 陈坚. All rights reserved.
//

#import "CHBaseViewCModel.h"
typedef NS_ENUM(NSInteger, CHProvideType) {
    ProvideTypeArticle,
    ProvideTypeService
};

@interface CHMyArticleAndServiceViewModel : CHBaseViewCModel
@property(nonatomic,assign)CHProvideType provideTye;
@end
