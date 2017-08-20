//
//  UIView+Dashline.h
//  shenbianapp
//
//  Created by book on 2017/8/20.
//  Copyright © 2017年 杨绍智. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface UIView (Dashline)

+ (void)drawDashLine:(UIView *)lineView lineLength:(int)lineLength lineSpacing:(int)lineSpacing lineColor:(UIColor *)lineColor;

@end
