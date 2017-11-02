//
//  CHMerchentModel.h
//  shenbianapp
//
//  Created by book on 2017/9/3.
//  Copyright © 2017年 陈坚. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface CHMerchentModel : NSObject
@property(nonatomic,copy)NSString *iconUrl;//图标
@property(nonatomic,copy)NSString *merchentName;//商家名称
@property(nonatomic,copy)NSString *content;//内容
@property(nonatomic,assign)NSString *rating;//评星
@property(nonatomic,assign)NSUInteger slodOut;//已售
@property(nonatomic,assign)CGFloat distance;//距离
@property(nonatomic,copy)NSString *tagName;//标签

@property(nonatomic,copy)NSString *placeHolder;//占位图名字

@end
