//
//  CHMyArticleAndServiceViewModel.h
//  shenbianapp
//
//  Created by book on 2017/9/24.
//  Copyright © 2017 . All rights reserved.
//

#import "CHBaseViewCModel.h"
typedef NS_ENUM(NSInteger, CHProvideType) {
    ProvideTypeService,
    ProvideTypeArticle,
};

@interface CHPersonHomeViewModel : CHBaseViewCModel
@property(nonatomic,assign)CHProvideType provideTye;

@property(nonatomic,strong,readonly)RACCommand *focusCommand;
@end
