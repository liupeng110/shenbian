//
//  CustomDiviceView.h
//  Miaohi
//
//  Created by 杨绍智 on 16/12/6.
//  Copyright © 2016年 haiqiu. All rights reserved.
//
#import <UIKit/UIKit.h>
#import "CHCategoryItemModel.h"

@protocol CustomDiviceViewdelegate <NSObject>
- (void)CustomDiviceViewClickIndex:(NSInteger)index;
@end
@interface CustomDiviceView : UIView<UIScrollViewDelegate>

@property (nonatomic,weak)id<CustomDiviceViewdelegate>delegate;
@property (nonatomic,copy)NSArray *categoryItemList;

@end
