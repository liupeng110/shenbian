//
//  LXButton.m
//  Artisan
//
//  Created by laixuan.liu on 15/12/7.
//  Copyright © 2015年 dianjiang. All rights reserved.
//

#import "LXButton.h"

@implementation LXButton

- (BOOL)pointInside:(CGPoint)point withEvent:(UIEvent*)event
{
  CGRect bounds = self.bounds;
  CGFloat widthDelta = MAX(44.0 - bounds.size.width, 0);
  CGFloat heightDelta = MAX(44.0 - bounds.size.height, 0);
  bounds = CGRectInset(bounds, -0.5 * widthDelta, -0.5 * heightDelta);
  return CGRectContainsPoint(bounds, point);
}

@end
