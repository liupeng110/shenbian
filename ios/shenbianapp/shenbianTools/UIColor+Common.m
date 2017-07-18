//
//  UIColor+Common.m
//  wowotuan
//
//  Created by li lin on 12-5-17.
//  Copyright (c) 2012年 __MyCompanyName__. All rights reserved.
//

#import "UIColor+Common.h"

@implementation UIColor(Common)
+(UIColor*)colorWithHexColor:(NSString*)hexColor{
    unsigned int red, green, blue;
    NSRange range;
    range.length =2;
    if ([hexColor hasPrefix:@"#"]) {
        hexColor = [hexColor substringFromIndex:1];
    }
    if (hexColor.length == 6) {
        range.location =0;
        [[NSScanner scannerWithString:[hexColor substringWithRange:range]]scanHexInt:&red];
        range.location =2;
        [[NSScanner scannerWithString:[hexColor substringWithRange:range]]scanHexInt:&green];
        range.location =4;
        [[NSScanner scannerWithString:[hexColor substringWithRange:range]]scanHexInt:&blue];
        
        return [UIColor colorWithRed:(float)(red/255.0f) green:(float)(green/255.0f) blue:(float)(blue/255.0f) alpha:1.0f];
    }
    else if (hexColor.length == 10 && [hexColor rangeOfString:@"|"].length>0){//增加了最后2位为透明度的方法
        float alpha;
        range.location =0;
        [[NSScanner scannerWithString:[hexColor substringWithRange:range]]scanHexInt:&red];
        range.location =2;
        [[NSScanner scannerWithString:[hexColor substringWithRange:range]]scanHexInt:&green];
        range.location =4;
        [[NSScanner scannerWithString:[hexColor substringWithRange:range]]scanHexInt:&blue];
        
        [[NSScanner scannerWithString:[hexColor substringWithRange:NSMakeRange(7, 3)]] scanFloat:&alpha];
        return [UIColor colorWithRed:(float)(red/255.0f)green:(float)(green/255.0f)blue:(float)(blue/255.0f)alpha:alpha];
    }
    else{
        return [UIColor blackColor];
    }
    

}
+ (UIColor *)defaultBlueColor{
    return [self colorWithHexColor:@"#7a90ac"];
}
+ (UIColor *)defaultBlackColor{
    return [self colorWithHexColor:@"#000000"];
}
+ (UIColor *)redColorFromWangYi{
    return [self colorWithHexColor:@"#d22b37"];
}
+ (UIColor *)blueColorFromTengXun{
    return [self colorWithHexColor:@"#1a4b82"];
}
+ (UIColor *)grayColorFromWangYi{
    return [self colorWithHexColor:@"#f1f1f1"];
}
+ (UIColor *)grayColorFromXinLang{
    return [self colorWithHexColor:@"#b0b5c0"];
}
+ (UIColor *)yellowColorFromTaoBao{
    return [self colorWithHexColor:@"#f48800"];
}
+ (UIColor *)pinkColorFromMeiLi{
    return [self colorWithHexColor:@"#ff71a0"];
}
+ (UIColor *)greenColorFromShuCheng{
    return [self colorWithHexColor:@"#54b0d2"];
}
+(UIColor*)whiteColorFromTableCell{
    return [self colorWithHexColor:@"#f7f7f7"];
}
+(UIColor*)grayColorFromTableCell{
    return [self colorWithHexColor:@"#f0f0f0"];
}
+(UIColor*)bgColor{
    return [self colorWithHexColor:@"#f7f7f7"];
}
+ (UIColor*)getColorFromImg:(UIImage*)img Point:(CGPoint)point
{
    CGImageRef imgRef = [img CGImage]; 
	CGSize size = [img size];
    //使用上面的函数创建上下文
	CGContextRef context = NULL; 
	CGColorSpaceRef colorSpace; 
	void *bitmapData; //内存空间的指针，该内存空间的大小等于图像使用RGB通道所占用的字节数。
	int bitmapByteCount; 
	int bitmapBytesPerRow;
    
	size_t pixelsWide = CGImageGetWidth(imgRef); //获取横向的像素点的个数
	size_t pixelsHigh = CGImageGetHeight(imgRef); 
    
    
    
	bitmapBytesPerRow	= (int)(pixelsWide * 4); //每一行的像素点占用的字节数，每个像素点的ARGB四个通道各占8个bit(0-255)的空间
	bitmapByteCount	= (int)(bitmapBytesPerRow * pixelsHigh); //计算整张图占用的字节数
    
	colorSpace = CGColorSpaceCreateDeviceRGB();//创建依赖于设备的RGB通道
	//分配足够容纳图片字节数的内存空间
	bitmapData = malloc( bitmapByteCount ); 
    //创建CoreGraphic的图形上下文，该上下文描述了bitmaData指向的内存空间需要绘制的图像的一些绘制参数
	context = CGBitmapContextCreate (bitmapData, 
                                     pixelsWide, 
                                     pixelsHigh, 
                                     8, 
                                     bitmapBytesPerRow, 
                                     colorSpace, 
                                     kCGImageAlphaPremultipliedLast);
    //Core Foundation中通过含有Create、Alloc的方法名字创建的指针，需要使用CFRelease()函数释放
	CGColorSpaceRelease( colorSpace ); 
    
    
	
	CGRect rect = {{0,0},{size.width, size.height}};
    //将目标图像绘制到指定的上下文，实际为上下文内的bitmapData。
	CGContextDrawImage(context, rect, imgRef); 
	unsigned char *data = CGBitmapContextGetData (context); 
    //释放上面的函数创建的上下文
	CGContextRelease(context);
    
    int i = 4 * img.size.width * point.y + 4 * point.x;
    int r = (unsigned char)data[i];
    int g = (unsigned char)data[i+1];
    int b = (unsigned char)data[i+2];
    UIColor *color=[UIColor colorWithRed:r/255.0 green:g/255.0 blue:b/255.0 alpha:1];
    return color;
}
@end
