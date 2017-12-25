//
//  CHServiceCellModel.h
//  shenbianapp
//
//  Created by book on 2017/9/16.
//  Copyright © 2017 . All rights reserved.
//

#import <Foundation/Foundation.h>

@interface CHServiceCellModel : NSObject
@property(nonatomic,copy)NSString *serviceTitle;
@property(nonatomic,copy)NSString *serviceContent;
@property(nonatomic,copy)NSString *servicePrice;
@property(nonatomic,assign)NSInteger serviceType;
@property(nonatomic,copy)NSString *userIconUrl;
@property(nonatomic,copy)NSString *commentCount;
@property(nonatomic,copy)NSArray *commentList;
@property(nonatomic,copy)NSString *locationStr;
@property(nonatomic,copy)NSString *userName;
@property(nonatomic,assign)NSInteger starRating;
@property(nonatomic,strong)NSArray *recommondList;
@end
