//
//  CHMapView.h
//  shenbianapp
//
//  Created by book on 2017/9/2.
//  Copyright © 2017 . All rights reserved.
//

typedef void(^SeeAllLocantion)(void);

#import <UIKit/UIKit.h>

@interface CHMapView : UIView
@property(nonatomic,copy) SeeAllLocantion seeAllLocation;
- (void)setMapZoomSacle:(float)zoomScale  animated:(BOOL)animated;
@end
