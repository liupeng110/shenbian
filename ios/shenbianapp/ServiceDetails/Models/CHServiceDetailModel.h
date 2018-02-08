//
//  CHServiceDetailModel.h
//  shenbianapp
//
//  Created by book on 2017/12/11.
//  Copyright © 2017 helinkeji. All rights reserved.
//

#import "CHBaseViewCModel.h"

@interface CHServiceDetailModel : CHBaseViewCModel

@property(nonatomic,copy)NSArray *coverImageUrlList;
@property(nonatomic,copy)NSString *serviceTitle;
@property(nonatomic,copy)NSString *serviceContent;
@property(nonatomic,copy)NSString *servicePrice;
@property(nonatomic,assign)NSInteger serviceType;
@property(nonatomic,copy)NSString *userIconUrl;
@property(nonatomic,copy)NSString *commentCount;
@property(nonatomic,copy)NSArray *commentList;
@property(nonatomic,copy)NSString *locationStr;
@property(nonatomic,copy)NSString *userName;
@property(nonatomic,copy)NSArray *recommendList;
@property(nonatomic,copy)NSArray *advertisementList;
@property(nonatomic,copy)NSString *starRating;
@property(nonatomic,copy)NSString *userId;

@property(nonatomic,strong,readonly)RACCommand *addToCartCommand;
@property(nonatomic,strong,readonly)RACCommand *focusCommand;
@end
