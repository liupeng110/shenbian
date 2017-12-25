//
//  HQTabBar.m
//  HaiqiuZhibo
//
//  Created by 申飞飞 on 16/4/1.
//  Copyright © 2016 申飞飞. All rights reserved.
//

#import "HQTabBar.h"

@interface HQTabBar()
@property(nonatomic,weak)UIButton *plusBtn;

@end

@implementation HQTabBar
@synthesize delegate;
-(instancetype)initWithFrame:(CGRect)frame{
    if (self = [super initWithFrame:frame]) {
        UIButton *plusBtn = [UIButton buttonWithType:UIButtonTypeCustom];
        [plusBtn setImage:[UIImage imageNamed:@"sy_plus"] forState:UIControlStateNormal];
        [plusBtn setImage:[UIImage imageNamed:@"sy_plus"] forState:UIControlStateFocused];
        plusBtn.frame = CGRectMake(0, 0, 70,35);
        [plusBtn addTarget:self action:@selector(plusBtnClick) forControlEvents:UIControlEventTouchUpInside];
        [self addSubview:plusBtn];
        self.plusBtn = plusBtn;
    }
    return self;
}

- (void)plusBtnClick{
    //通知代理
    if ([self.delegate respondsToSelector:@selector(TabBarDidClickPlusButton:)]) {
        [self.delegate TabBarDidClickPlusButton:self];
    }
}

// 重新布局
- (void)layoutSubviews{
    [super layoutSubviews];
    // 1 设置加号按钮的位置
    _plusBtn.centerX = self.width*0.5;
//    NSInteger number ;
//    if (IPHONE6S) {
//        number = 4;
//    }else if(IPHONE6P){
//        number = 5;
//    }else{
//         number = 4;
//    }
    _plusBtn.centerY = self.height *0.5;// - number;
    // 2 设置其他tabBarButton的frame
    CGFloat tabBarButtonW = self.width/5;
    CGFloat tabBarButtonIndex = 0;
    for (UIView *child in self.subviews) {
        Class class = NSClassFromString(@"UITabBarButton");
        if ([child isKindOfClass:class]) {
            //设置x
            child.x = tabBarButtonIndex *tabBarButtonW;
            //设置宽度
            child.width = tabBarButtonW;
            //增加索引
            tabBarButtonIndex++;
            if (tabBarButtonIndex == 2) {
                tabBarButtonIndex++;
            }
            
        }
    }
    
}
@end
