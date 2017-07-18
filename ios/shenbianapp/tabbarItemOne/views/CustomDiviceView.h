//
//  CustomDiviceView.h
//  Miaohi
//
//  Created by 杨绍智 on 16/12/6.
//  Copyright © 2016年 haiqiu. All rights reserved.
//
#import <UIKit/UIKit.h>
#import "RootObjectModel.h"
@protocol CustomDiviceViewdelegate <NSObject>
- (void)CustomDiviceViewClickIndex:(NSInteger)index;
@end
@interface CustomDiviceView : UIView<UIScrollViewDelegate>
@property (nonatomic,weak)id<CustomDiviceViewdelegate>delegate;
@property (nonatomic,strong)UIImageView * seachIcon;
@property (nonatomic,strong)UIView * seachView;
@property (nonatomic,strong)UIScrollView * navBarScroll;
@property (nonatomic,assign)float contOffSetLeng;
@property (nonatomic,strong)NSMutableArray *MhSquareCollectionArray;
@property (nonatomic,strong)UILabel * lineLabel;
- (void)setCustomDiviceViewByArray:(NSArray*)lableArray andCureentObject:(NSString*)kind_name;
- (void)setBtnValue:(NSInteger)index;
@end
