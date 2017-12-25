//
//  UIColor+Common.h
//  wowotuan
//
//  Created by li lin on 12-5-17.
//  Copyright (c) 2012 __MyCompanyName__. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface UIColor (Common)
+(UIColor *)colorWithHexColor:(NSString*)hexColor;
+ (UIColor *)defaultBlueColor;
+ (UIColor *)defaultBlackColor;
+ (UIColor *)redColorFromWangYi;
+ (UIColor *)blueColorFromTengXun;
+ (UIColor *)grayColorFromWangYi;
+ (UIColor *)grayColorFromXinLang;
+ (UIColor *)yellowColorFromTaoBao;
+ (UIColor *)pinkColorFromMeiLi;
+ (UIColor *)greenColorFromShuCheng;
+(UIColor*)whiteColorFromTableCell;
+(UIColor*)grayColorFromTableCell;
+(UIColor*)bgColor;
+ (UIColor*)getColorFromImg:(UIImage*)img Point:(CGPoint)point;
@end
