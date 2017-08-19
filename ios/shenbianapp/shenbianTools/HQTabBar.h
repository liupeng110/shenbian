//
//  HQTabBar.h
//  HaiqiuZhibo
//
//  Created by 申飞飞 on 16/4/1.
//  Copyright © 2016年 申飞飞. All rights reserved.
//

#import <UIKit/UIKit.h>
@class HQTabBar;
@protocol HQTabBardelegate <UITabBarDelegate>

@optional
-(void)TabBarDidClickPlusButton:(HQTabBar *)tabBar;

@end

@interface HQTabBar : UITabBar
@property(nonatomic,weak)id<HQTabBardelegate>delegate;
@end